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

/** Definition for moveit_msgs/AllowedCollisionMatrix */
@MessageMetadata(
        name = AllowedCollisionMatrixMessage.NAME,
        md5sum = "aedce13587eef0d79165a075659c1879")
public class AllowedCollisionMatrixMessage implements Message {

    static final String NAME = "moveit_msgs/AllowedCollisionMatrix";

    /** The list of entry names in the matrix */
    public StringMessage[] entry_names = new StringMessage[0];

    /**
     * The individual entries in the allowed collision matrix square, symmetric, with same order as
     * entry_names
     */
    public AllowedCollisionEntryMessage[] entry_values = new AllowedCollisionEntryMessage[0];

    /**
     * In addition to the collision matrix itself, we also have the default entry value for each
     * entry name. If the allowed collision flag is queried for a pair of names (n1, n2) that is not
     * found in the collision matrix itself, the value of the collision flag is considered to be
     * that of the entry (n1 or n2) specified in the list below. If both n1 and n2 are found in the
     * list of defaults, the result is computed with an AND operation
     */
    public StringMessage[] default_entry_names = new StringMessage[0];

    public boolean[] default_entry_values = new boolean[0];

    public AllowedCollisionMatrixMessage withEntryNames(StringMessage... entry_names) {
        this.entry_names = entry_names;
        return this;
    }

    public AllowedCollisionMatrixMessage withEntryValues(
            AllowedCollisionEntryMessage... entry_values) {
        this.entry_values = entry_values;
        return this;
    }

    public AllowedCollisionMatrixMessage withDefaultEntryNames(
            StringMessage... default_entry_names) {
        this.default_entry_names = default_entry_names;
        return this;
    }

    public AllowedCollisionMatrixMessage withDefaultEntryValues(boolean... default_entry_values) {
        this.default_entry_values = default_entry_values;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                Arrays.hashCode(entry_names),
                Arrays.hashCode(entry_values),
                Arrays.hashCode(default_entry_names),
                Arrays.hashCode(default_entry_values));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (AllowedCollisionMatrixMessage) obj;
        return Arrays.equals(entry_names, other.entry_names)
                && Arrays.equals(entry_values, other.entry_values)
                && Arrays.equals(default_entry_names, other.default_entry_names)
                && Arrays.equals(default_entry_values, other.default_entry_values);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "entry_names", entry_names,
                "entry_values", entry_values,
                "default_entry_names", default_entry_names,
                "default_entry_values", default_entry_values);
    }
}
