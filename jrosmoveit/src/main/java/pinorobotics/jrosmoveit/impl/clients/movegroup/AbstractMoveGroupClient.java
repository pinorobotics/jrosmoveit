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
package pinorobotics.jrosmoveit.impl.clients.movegroup;

import id.jrosmessages.Message;
import id.xfunction.logging.XLogger;
import pinorobotics.jrosactionlib.JRosActionClient;
import pinorobotics.jrosmoveit.entities.Plan;
import pinorobotics.jrosmoveit.exceptions.JRosMoveItException;
import pinorobotics.jrosmoveit.impl.JRosMoveItUtils;
import pinorobotics.jrosmoveit.impl.MotionRequest;

/**
 * Client which allows to interact with MoveGroup action server.
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public abstract class AbstractMoveGroupClient<
                G extends Message, R extends Message & MoveGroupResult & Plan>
        implements MoveGroupClient<R> {

    private static final XLogger LOGGER = XLogger.getLogger(AbstractMoveGroupClient.class);
    private JRosMoveItUtils utils = new JRosMoveItUtils();
    private JRosActionClient<G, R> moveGroupActionClient;

    /**
     * Creates a new instance of the client
     *
     * @param moveGroupActionClient ROS client
     */
    public AbstractMoveGroupClient(JRosActionClient<G, R> moveGroupActionClient) {
        this.moveGroupActionClient = moveGroupActionClient;
    }

    @Override
    public R plan(MotionRequest planRequest) throws JRosMoveItException {
        LOGGER.entering("plan");
        R result;
        try {
            result =
                    moveGroupActionClient.sendGoalAsync(createGoalRequest(true, planRequest)).get();
        } catch (Exception e) {
            throw new JRosMoveItException(e);
        }
        utils.verifyResult(result.getErrorCode());
        LOGGER.exiting("plan", result);
        return result;
    }

    @Override
    public void move(MotionRequest planRequest) throws JRosMoveItException {
        LOGGER.entering("move");
        R result;
        try {
            result =
                    moveGroupActionClient
                            .sendGoalAsync(createGoalRequest(false, planRequest))
                            .get();
        } catch (Exception e) {
            throw new JRosMoveItException(e);
        }
        utils.verifyResult(result.getErrorCode());
    }

    @Override
    public void close() throws Exception {
        LOGGER.entering("close");
        moveGroupActionClient.close();
        LOGGER.exiting("close");
    }

    protected abstract G createGoalRequest(boolean planOnly, MotionRequest planRequest);
}
