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
package pinorobotics.jrosmoveit.impl.clients.executetrajectory;

import id.xfunction.logging.XLogger;
import pinorobotics.jrosactionlib.JRosActionClient;
import pinorobotics.jrosmoveit.entities.Plan;
import pinorobotics.jrosmoveit.exceptions.JRosMoveItException;
import pinorobotics.jrosmoveit.impl.JRosMoveItUtils;

/**
 * Client which allows to interact with ExecuteTrajectory action server.
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public abstract class AbstractExecuteTrajectoryClient<
                P extends Plan, G extends ExecuteTrajectoryGoal, R extends ExecuteTrajectoryResult>
        implements ExecuteTrajectoryClient<P> {

    private static final XLogger LOGGER = XLogger.getLogger(AbstractExecuteTrajectoryClient.class);
    private JRosMoveItUtils utils = new JRosMoveItUtils();

    private JRosActionClient<G, R> executeTrajectoryActionClient;

    /**
     * Creates a new instance of the client
     *
     * @param executeTrajectoryActionClient ROS client
     */
    public AbstractExecuteTrajectoryClient(JRosActionClient<G, R> executeTrajectoryActionClient) {
        this.executeTrajectoryActionClient = executeTrajectoryActionClient;
    }

    @Override
    public void execute(P plan) throws JRosMoveItException {
        LOGGER.entering("execute");
        R result;
        try {
            result =
                    executeTrajectoryActionClient
                            .sendGoalAsync(createExecuteTrajectoryRequest(plan))
                            .get();
        } catch (Exception e) {
            throw new JRosMoveItException(e);
        }
        utils.verifyResult(result.getErrorCode());
        LOGGER.exiting("execute");
    }

    @Override
    public void close() throws Exception {
        LOGGER.entering("close");
        executeTrajectoryActionClient.close();
        LOGGER.exiting("close");
    }

    protected abstract G createExecuteTrajectoryRequest(P plan);
}
