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
import java.util.Arrays;
import java.util.Objects;

/** Definition for moveit_msgs/AllowedCollisionEntry */
@MessageMetadata(
        name = AllowedCollisionEntryMessage.NAME,
        md5sum = "90d1ae1850840724bb043562fe3285fc")
public class AllowedCollisionEntryMessage implements Message {

    static final String NAME = "moveit_msgs/AllowedCollisionEntry";

    /** whether or not collision checking is enabled */
    public boolean[] enabled = new boolean[0];

    public AllowedCollisionEntryMessage withEnabled(boolean... enabled) {
        this.enabled = enabled;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(enabled));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (AllowedCollisionEntryMessage) obj;
        return Arrays.equals(enabled, other.enabled);
    }

    @Override
    public String toString() {
        return XJson.asString("enabled", enabled);
    }
}
