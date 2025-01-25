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
package pinorobotics.jrosmoveit.impl;

import id.jrosmessages.geometry_msgs.PoseMessage;
import id.xfunction.logging.XLogger;
import java.util.HashMap;
import java.util.Map;
import pinorobotics.jrosmoveit.JRosMoveIt;
import pinorobotics.jrosmoveit.entities.Plan;
import pinorobotics.jrosmoveit.exceptions.JRosMoveItException;
import pinorobotics.jrosmoveit.impl.clients.executetrajectory.ExecuteTrajectoryClient;
import pinorobotics.jrosmoveit.impl.clients.movegroup.MoveGroupClient;

/**
 * Abstract client which allows to interact with ROS MoveIt.
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public abstract class AbstractJRosMoveIt<P extends Plan> implements JRosMoveIt<P> {

    private static final XLogger LOGGER = XLogger.getLogger(AbstractJRosMoveIt.class);

    private MoveGroupClient<P> moveGroupClient;
    private ExecuteTrajectoryClient executeTrajectoryClient;
    private ActiveTargetType activeTarget = ActiveTargetType.JOINT;
    private Map<String, PoseMessage> poseTargets = new HashMap<>();

    /** Creates a new instance of the client */
    public AbstractJRosMoveIt(
            MoveGroupClient<P> moveGroupActionClient,
            ExecuteTrajectoryClient executeTrajectoryActionClient) {
        this.moveGroupClient = moveGroupActionClient;
        this.executeTrajectoryClient = executeTrajectoryActionClient;
    }

    @Override
    public P plan() throws JRosMoveItException {
        LOGGER.entering("plan");
        var plan = moveGroupClient.plan(new MotionRequest(activeTarget, poseTargets));
        LOGGER.exiting("plan", plan);
        return plan;
    }

    @Override
    public void execute(P plan) throws JRosMoveItException {
        LOGGER.entering("execute");
        executeTrajectoryClient.execute(plan);
        LOGGER.exiting("execute");
    }

    @Override
    public void move() throws JRosMoveItException {
        LOGGER.entering("move");
        moveGroupClient.move(new MotionRequest(activeTarget, poseTargets));
        LOGGER.exiting("move");
    }

    @Override
    public void setPoseTarget(PoseMessage targetPose, String endEffectorLink) {
        activeTarget = ActiveTargetType.POSE;
        poseTargets.put(endEffectorLink, targetPose);
    }

    @Override
    public void close() {
        LOGGER.entering("close");
        try {
            moveGroupClient.close();
            executeTrajectoryClient.close();
        } catch (Exception e) {
            throw new JRosMoveItException(e);
        }
        LOGGER.exiting("close");
    }
}
