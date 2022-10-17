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
package pinorobotics.jrosmoveit.moveit_msgs;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for moveit_msgs/PickupAction ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION
 * DEFINITION ======
 */
@MessageMetadata(name = PickupActionMessage.NAME, md5sum = "dffb706c5fad660e690608a93e87541c")
public class PickupActionMessage implements Message {

    static final String NAME = "moveit_msgs/PickupAction";

    public PickupActionGoalMessage action_goal = new PickupActionGoalMessage();

    public PickupActionResultMessage action_result = new PickupActionResultMessage();

    public PickupActionMessage withActionGoal(PickupActionGoalMessage action_goal) {
        this.action_goal = action_goal;
        return this;
    }

    public PickupActionMessage withActionResult(PickupActionResultMessage action_result) {
        this.action_result = action_result;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(action_goal, action_result);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (PickupActionMessage) obj;
        return Objects.equals(action_goal, other.action_goal)
                && Objects.equals(action_result, other.action_result);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "action_goal", action_goal,
                "action_result", action_result);
    }
}
