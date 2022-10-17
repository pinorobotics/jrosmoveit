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
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for moveit_msgs/PlannerInterfaceDescription */
@MessageMetadata(
        name = PlannerInterfaceDescriptionMessage.NAME,
        md5sum = "3b93afb00ba165a83730c4eb03cd1ab7")
public class PlannerInterfaceDescriptionMessage implements Message {

    static final String NAME = "moveit_msgs/PlannerInterfaceDescription";

    /** The name of the planner interface */
    public StringMessage name = new StringMessage();

    /** The name of the planning pipeline */
    public StringMessage pipeline_id = new StringMessage();

    /** The names of the planner ids within the interface */
    public StringMessage[] planner_ids = new StringMessage[0];

    public PlannerInterfaceDescriptionMessage withName(StringMessage name) {
        this.name = name;
        return this;
    }

    public PlannerInterfaceDescriptionMessage withPipelineId(StringMessage pipeline_id) {
        this.pipeline_id = pipeline_id;
        return this;
    }

    public PlannerInterfaceDescriptionMessage withPlannerIds(StringMessage... planner_ids) {
        this.planner_ids = planner_ids;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pipeline_id, Arrays.hashCode(planner_ids));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (PlannerInterfaceDescriptionMessage) obj;
        return Objects.equals(name, other.name)
                && Objects.equals(pipeline_id, other.pipeline_id)
                && Arrays.equals(planner_ids, other.planner_ids);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "name", name,
                "pipeline_id", pipeline_id,
                "planner_ids", planner_ids);
    }
}
