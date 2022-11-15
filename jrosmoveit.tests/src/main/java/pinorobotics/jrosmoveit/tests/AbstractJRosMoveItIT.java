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
package pinorobotics.jrosmoveit.tests;

import id.jrosmessages.Message;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.geometry_msgs.QuaternionMessage;
import id.jrosmessages.std_msgs.StringMessage;
import id.jrosmessages.trajectory_msgs.JointTrajectoryPointMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pinorobotics.jrosmoveit.JRosMoveIt;
import pinorobotics.jrosmoveit.entities.Plan;
import pinorobotics.jrosrviztools.JRosRvizTools;
import pinorobotics.jrosrviztools.entities.Color;
import pinorobotics.jrosrviztools.entities.JRosRvizEntitiesTransformer;
import pinorobotics.jrosrviztools.entities.MarkerType;
import pinorobotics.jrosrviztools.entities.Point;
import pinorobotics.jrosrviztools.entities.Scales;
import pinorobotics.jrostf2.JRosTf2;
import pinorobotics.robotstate.RobotModel;
import pinorobotics.robotstate.RobotStateMonitor;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractJRosMoveItIT<T extends Message, P extends Plan> {

    private static final String BASE_FRAME = "world";
    private static final JRosRvizEntitiesTransformer rvizTransformer =
            new JRosRvizEntitiesTransformer();
    private JRosRvizTools rvizTools;
    private JRosMoveIt<P> moveIt;
    private JRosTf2<T> tf2;

    @BeforeEach
    public void setup() {
        rvizTools = createRVizTools(BASE_FRAME, "/visualization_marker_array");
        moveIt = createMoveIt("panda_arm", new RobotModel(BASE_FRAME));
        tf2 = createTf2();
    }

    @AfterEach
    public void clean() throws Exception {
        moveIt.close();
        rvizTools.close();
        tf2.close();
    }

    @Test
    @Order(1)
    public void test_plan() throws Exception {
        var points = new Point[] {new Point(0.28, 0.2, 0.5), new Point(0.28, -0.2, 0.5)};
        var startTransform = tf2.lookupTransform(BASE_FRAME, "panda_hand");
        rvizTools.publishMarkers(Color.RED, Scales.XLARGE, MarkerType.SPHERE, points);
        try (var monitor = createRobotStateMonitor()) {
            monitor.start();
            for (int i = 0; i < points.length; i++) {
                var targetPose = new PoseMessage();
                targetPose.position = rvizTransformer.toPointMessage(points[i]);
                targetPose.orientation = new QuaternionMessage().withW(-1.0);
                moveIt.setPoseTarget(targetPose, "panda_hand");

                System.out.println("Start planning");
                var plan = moveIt.plan();
                System.out.println(plan);

                Assertions.assertEquals(9, getJointStateNames(plan).length);
                Assertions.assertEquals("panda_joint4", getJointStateNames(plan)[3]);
                Assertions.assertEquals(9, getJointStatePositions(plan).length);
                Assertions.assertEquals(7, getJointTrajectoryNames(plan).length);
                Assertions.assertTrue(getJointTrajectoryPoints(plan).length > 5);

                var state = monitor.getLastRobotState().clone();
                System.out.println("Current state: " + state);

                moveIt.execute(plan);

                var newState = monitor.getLastRobotState().clone();
                System.out.println("New state: " + newState);
                Assertions.assertNotEquals(state, newState);
            }
        }
        moveIt.setPoseTarget(asPoseMessage(startTransform), "panda_hand");
        moveIt.move();
        var endTransform = tf2.lookupTransform(BASE_FRAME, "panda_hand");
        assertTransformEquals(startTransform, endTransform);
    }

    protected abstract JointTrajectoryPointMessage[] getJointTrajectoryPoints(P plan);

    protected abstract StringMessage[] getJointTrajectoryNames(P plan);

    protected abstract double[] getJointStatePositions(P plan);

    protected abstract String[] getJointStateNames(P plan);

    protected abstract PoseMessage asPoseMessage(T startTransform);

    protected abstract void assertTransformEquals(T expected, T actual);

    protected abstract RobotStateMonitor<?> createRobotStateMonitor();

    protected abstract JRosTf2<T> createTf2();

    protected abstract JRosMoveIt<P> createMoveIt(String groupName, RobotModel model);

    protected abstract JRosRvizTools createRVizTools(String baseFrame, String topicName);
}
