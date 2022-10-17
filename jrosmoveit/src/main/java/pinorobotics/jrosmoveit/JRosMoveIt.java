/*
 * Copyright 2021 jrosmoveit project
 * 
 * Website: https://github.com/pinorobotics/jrosmoveit
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pinorobotics.jrosmoveit;

import id.jros1client.JRos1Client;
import id.jros1messages.geometry_msgs.PoseStampedMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.primitives.Time;
import id.jrosmessages.shape_msgs.SolidPrimitiveMessage;
import id.jrosmessages.std_msgs.Int32Message;
import id.xfunction.logging.XLogger;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import pinorobotics.jros1actionlib.JRos1ActionClientFactory;
import pinorobotics.jrosactionlib.JRosActionClient;
import pinorobotics.jrosmoveit.exceptions.JRosMoveItException;
import pinorobotics.jrosmoveit.impl.ActiveTargetType;
import pinorobotics.jrosmoveit.moveit_msgs.ConstraintsMessage;
import pinorobotics.jrosmoveit.moveit_msgs.ExecuteTrajectoryActionDefinition;
import pinorobotics.jrosmoveit.moveit_msgs.ExecuteTrajectoryGoalMessage;
import pinorobotics.jrosmoveit.moveit_msgs.ExecuteTrajectoryResultMessage;
import pinorobotics.jrosmoveit.moveit_msgs.MotionPlanRequestMessage;
import pinorobotics.jrosmoveit.moveit_msgs.MoveGroupActionDefinition;
import pinorobotics.jrosmoveit.moveit_msgs.MoveGroupGoalMessage;
import pinorobotics.jrosmoveit.moveit_msgs.MoveGroupResultMessage;
import pinorobotics.jrosmoveit.moveit_msgs.MoveItErrorCodesMessage;
import pinorobotics.jrosmoveit.moveit_msgs.OrientationConstraintMessage;
import pinorobotics.jrosmoveit.moveit_msgs.PlanningOptionsMessage;
import pinorobotics.jrosmoveit.moveit_msgs.PlanningSceneMessage;
import pinorobotics.jrosmoveit.moveit_msgs.PositionConstraintMessage;
import pinorobotics.jrosmoveit.moveit_msgs.RobotStateMessage;

/**
 * Client which allows to interact with ROS MoveIt.
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class JRosMoveIt implements Closeable {

    private static final XLogger LOGGER = XLogger.getLogger(JRosMoveIt.class);
    private JRosActionClient<MoveGroupGoalMessage, MoveGroupResultMessage> moveGroupActionClient;
    private JRosActionClient<ExecuteTrajectoryGoalMessage, ExecuteTrajectoryResultMessage>
            executeTrajectoryActionClient;
    private ActiveTargetType activeTarget = ActiveTargetType.JOINT;

    private MotionPlanRequestMessage request =
            new MotionPlanRequestMessage()
                    .withNumPlanningAttempts(new Int32Message().withData(1))
                    .withMaxVelocityScalingFactor(0.1)
                    .withMaxAccelerationScalingFactor(0.1)
                    .withAllowedPlanningTime(5.0);
    private MoveGroupGoalMessage goal =
            new MoveGroupGoalMessage()
                    .withPlanningOptions(
                            new PlanningOptionsMessage()
                                    .withLookAround(false)
                                    .withReplan(false)
                                    .withPlanningSceneDiff(
                                            new PlanningSceneMessage()
                                                    .withIsDiff(true)
                                                    .withRobotState(
                                                            new RobotStateMessage()
                                                                    .withIsDiff(true))))
                    .withRequest(request);
    private Map<String, PoseStampedMessage> poseTargets = new HashMap<>();

    private double toleranceAngleInDeg = 0.001;
    private double tolerancePoseInMm = 0.0000001;
    private RobotModel model;

    /**
     * Creates a new instance of the client
     *
     * @param client ROS client
     */
    public JRosMoveIt(JRos1Client client, String groupName, RobotModel model) {
        this.model = model;
        request.group_name.data = groupName;
        moveGroupActionClient =
                new JRos1ActionClientFactory()
                        .createClient(client, new MoveGroupActionDefinition(), "/move_group");
        executeTrajectoryActionClient =
                new JRos1ActionClientFactory()
                        .createClient(
                                client,
                                new ExecuteTrajectoryActionDefinition(),
                                "/execute_trajectory");
    }

    /**
     * Asks MoveIt to create a plan for moving robot to target pose. MoveIt will calculate and
     * create a trajectory. It will publish it to display_planned_path topic which can be visualized
     * in RViz
     */
    public Plan plan() throws JRosMoveItException {
        LOGGER.entering("plan");
        goal.planning_options.plan_only = true;
        populateMotionPlanRequest();
        MoveGroupResultMessage result;
        try {
            result = moveGroupActionClient.sendGoalAsync(goal).get();
        } catch (Exception e) {
            throw new JRosMoveItException(e);
        }
        verifyResult(result.error_code);
        var plan = createPlan(result);
        LOGGER.exiting("plan", plan);
        return plan;
    }

    /**
     * Let MoveIt to execute given plan which will cause robot to change its state to the new target
     * pose state.
     */
    public void execute(Plan plan) throws JRosMoveItException {
        LOGGER.entering("execute");
        var goal = new ExecuteTrajectoryGoalMessage();
        goal.trajectory = plan.getPlannedTrajectory();
        ExecuteTrajectoryResultMessage result;
        try {
            result = executeTrajectoryActionClient.sendGoalAsync(goal).get();
        } catch (Exception e) {
            throw new JRosMoveItException(e);
        }
        verifyResult(result.error_code);
        LOGGER.exiting("execute");
    }

    /**
     * This method combines two actions like calculating plan and executing it in one single action.
     * It will do only one call to MoveIt instead of two.
     */
    public void move() throws JRosMoveItException {
        LOGGER.entering("move");
        goal.planning_options.plan_only = false;
        populateMotionPlanRequest();
        MoveGroupResultMessage result;
        try {
            result = moveGroupActionClient.sendGoalAsync(goal).get();
        } catch (Exception e) {
            throw new JRosMoveItException(e);
        }
        verifyResult(result.error_code);
    }

    private Plan createPlan(MoveGroupResultMessage result) {
        var plan =
                new Plan()
                        .withTrajectory(result.planned_trajectory)
                        .withStartState(result.trajectory_start)
                        .withPlanningTime(result.planning_time);
        return plan;
    }

    /** http://docs.ros.org/en/melodic/api/moveit_msgs/html/definePlanningRequest.html */
    private void populateMotionPlanRequest() {
        switch (activeTarget) {
            case POSE:
                {
                    for (var entry : poseTargets.entrySet()) {
                        var goalConstraints = request.goal_constraints;
                        if (goalConstraints.length != 1) {
                            goalConstraints =
                                    Stream.of(new ConstraintsMessage())
                                            .toArray(ConstraintsMessage[]::new);
                            request.goal_constraints = goalConstraints;
                        }
                        populateGoalConstraints(
                                goalConstraints[0], entry.getKey(), entry.getValue());
                    }
                    break;
                }
            default:
                throw new UnsupportedOperationException();
        }
    }

    private void populateGoalConstraints(
            ConstraintsMessage goalConstraints, String linkName, PoseStampedMessage pose) {
        if (goalConstraints.position_constraints.length != 1) {
            goalConstraints.position_constraints =
                    Stream.of(new PositionConstraintMessage())
                            .toArray(PositionConstraintMessage[]::new);
        }
        populatePositionConstraints(goalConstraints.position_constraints[0], linkName, pose);

        if (goalConstraints.orientation_constraints.length != 1) {
            goalConstraints.orientation_constraints =
                    Stream.of(new OrientationConstraintMessage())
                            .toArray(OrientationConstraintMessage[]::new);
        }
        populateOrientationConstraints(goalConstraints.orientation_constraints[0], linkName, pose);
    }

    private void populateOrientationConstraints(
            OrientationConstraintMessage orientationConstraint,
            String linkName,
            PoseStampedMessage pose) {
        orientationConstraint.link_name.withData(linkName);
        orientationConstraint.header = pose.header;
        orientationConstraint.orientation = pose.pose.orientation;
        orientationConstraint.absolute_x_axis_tolerance = toleranceAngleInDeg;
        orientationConstraint.absolute_y_axis_tolerance = toleranceAngleInDeg;
        orientationConstraint.absolute_z_axis_tolerance = toleranceAngleInDeg;
        orientationConstraint.weight = 1.0;
    }

    private void populatePositionConstraints(
            PositionConstraintMessage positionConstraint,
            String linkName,
            PoseStampedMessage pose) {
        positionConstraint.link_name.withData(linkName);

        // reset all to 0 since those values could be changed previously
        positionConstraint.target_point_offset.x = 0;
        positionConstraint.target_point_offset.y = 0;
        positionConstraint.target_point_offset.z = 0;
        var primitives = positionConstraint.constraint_region.primitives;
        if (primitives.length != 1) {
            primitives = new SolidPrimitiveMessage[1];
            positionConstraint.constraint_region.primitives = primitives;
        }

        var sphere = primitives[0];
        if (sphere == null) {
            sphere = new SolidPrimitiveMessage();
            primitives[0] = sphere;
        }
        sphere.withShapeType(SolidPrimitiveMessage.ShapeType.SPHERE);
        int sphereDimsCount = SolidPrimitiveMessage.SphereDimensionType.values().length;
        var sphereDims = sphere.dimensions;
        if (sphereDims.length != sphereDimsCount) {
            sphereDims = new double[sphereDimsCount];
            sphere.dimensions = sphereDims;
        }
        sphereDims[SolidPrimitiveMessage.SphereDimensionType.SPHERE_RADIUS.ordinal()] =
                tolerancePoseInMm;

        positionConstraint.header = pose.header;
        var primitivePoses = positionConstraint.constraint_region.primitive_poses;
        if (primitivePoses.length != 1) {
            primitivePoses = Stream.of(new PoseMessage()).toArray(PoseMessage[]::new);
            positionConstraint.constraint_region.primitive_poses = primitivePoses;
        }
        primitivePoses[0].position = pose.pose.position;

        // orientation of constraint region does not affect anything, since it is a sphere
        positionConstraint.constraint_region.primitive_poses[0].orientation.x = 0.0;
        positionConstraint.constraint_region.primitive_poses[0].orientation.y = 0.0;
        positionConstraint.constraint_region.primitive_poses[0].orientation.z = 0.0;
        positionConstraint.constraint_region.primitive_poses[0].orientation.w = 1.0;
        positionConstraint.weight = 1.0;
    }

    @Override
    public void close() throws IOException {
        LOGGER.entering("close");
        moveGroupActionClient.close();
        executeTrajectoryActionClient.close();
        LOGGER.exiting("close");
    }

    /** Set the goal pose of the end-effector */
    public void setPoseTarget(PoseMessage targetPose, String endEffectorLink) {
        var stampedMessage = toStampedPose(targetPose);
        activeTarget = ActiveTargetType.POSE;
        poseTargets.put(endEffectorLink, stampedMessage);
    }

    private PoseStampedMessage toStampedPose(PoseMessage poseMessage) {
        var stampedMessage = new PoseStampedMessage();
        // dont set timestamp since it can expire and cause errors
        stampedMessage.header.stamp = new Time();
        stampedMessage.header.frame_id = model.getModelFrame();
        stampedMessage.pose = poseMessage;
        return stampedMessage;
    }

    private void verifyResult(MoveItErrorCodesMessage code) throws JRosMoveItException {
        if (!code.isOk()) {
            throw new JRosMoveItException(code.getCodeType().toString());
        }
    }
}
