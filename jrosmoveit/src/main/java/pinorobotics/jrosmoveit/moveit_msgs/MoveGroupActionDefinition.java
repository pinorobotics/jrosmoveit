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
/*
 * Authors:
 * - aeon_flux <aeon_flux@eclipso.ch>
 */
package pinorobotics.jrosmoveit.moveit_msgs;

import pinorobotics.jrosactionlib.msgs.ActionDefinition;
import pinorobotics.jrosactionlib.msgs.ActionGoalMessage;
import pinorobotics.jrosactionlib.msgs.ActionResultMessage;

public class MoveGroupActionDefinition implements ActionDefinition<MoveGroupGoalMessage, MoveGroupResultMessage> {

    @Override
    public Class<? extends ActionGoalMessage<MoveGroupGoalMessage>> getActionGoalMessage() {
        return MoveGroupActionGoalMessage.class;
    }

    @Override
    public Class<? extends ActionResultMessage<MoveGroupResultMessage>> getActionResultMessage() {
        return MoveGroupActionResultMessage.class;
    }

}
