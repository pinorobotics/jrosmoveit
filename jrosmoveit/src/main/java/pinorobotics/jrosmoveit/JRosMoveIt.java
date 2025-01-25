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

import id.jrosmessages.geometry_msgs.PoseMessage;
import pinorobotics.jrosmoveit.entities.Plan;
import pinorobotics.jrosmoveit.exceptions.JRosMoveItException;

/**
 * Client which allows to interact with ROS MoveIt.
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public interface JRosMoveIt<P extends Plan> extends AutoCloseable {

    /** Set the goal pose of the end-effector */
    void setPoseTarget(PoseMessage targetPose, String endEffectorLink);

    /**
     * Asks MoveIt to create a plan for moving robot to target pose. MoveIt will calculate and
     * create a trajectory. It will publish it to display_planned_path topic which can be visualized
     * in RViz
     */
    P plan() throws JRosMoveItException;

    /**
     * Let MoveIt to execute given plan which will cause robot to change its state to the new target
     * pose state.
     */
    void execute(P plan) throws JRosMoveItException;

    /**
     * This method combines two actions like calculating plan and executing it in one single action.
     * It will do only one call to MoveIt instead of two.
     */
    void move() throws JRosMoveItException;

    @Override
    void close() throws JRosMoveItException;
}
