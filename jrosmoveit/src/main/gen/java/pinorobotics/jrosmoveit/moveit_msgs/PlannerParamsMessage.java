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

/** Definition for moveit_msgs/PlannerParams */
@MessageMetadata(
        name = PlannerParamsMessage.NAME,
        fields = {"keys", "values", "descriptions"},
        md5sum = "cebdf4927996b9026bcf59a160d64145")
public class PlannerParamsMessage implements Message {

    static final String NAME = "moveit_msgs/PlannerParams";

    /** parameter names (same size as values) */
    public StringMessage[] keys = new StringMessage[0];

    /** parameter values (same size as keys) */
    public StringMessage[] values = new StringMessage[0];

    /** parameter description (can be empty) */
    public StringMessage[] descriptions = new StringMessage[0];

    public PlannerParamsMessage withKeys(StringMessage... keys) {
        this.keys = keys;
        return this;
    }

    public PlannerParamsMessage withValues(StringMessage... values) {
        this.values = values;
        return this;
    }

    public PlannerParamsMessage withDescriptions(StringMessage... descriptions) {
        this.descriptions = descriptions;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                Arrays.hashCode(keys), Arrays.hashCode(values), Arrays.hashCode(descriptions));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (PlannerParamsMessage) obj;
        return Arrays.equals(keys, other.keys)
                && Arrays.equals(values, other.values)
                && Arrays.equals(descriptions, other.descriptions);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "keys", keys,
                "values", values,
                "descriptions", descriptions);
    }
}
