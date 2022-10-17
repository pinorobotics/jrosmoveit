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

/** Definition for moveit_msgs/TrajectoryConstraints */
@MessageMetadata(
        name = TrajectoryConstraintsMessage.NAME,
        md5sum = "a8fd55b45c3918e857080ca125d29e9c")
public class TrajectoryConstraintsMessage implements Message {

    static final String NAME = "moveit_msgs/TrajectoryConstraints";

    /** The array of constraints to consider along the trajectory */
    public ConstraintsMessage[] constraints = new ConstraintsMessage[0];

    public TrajectoryConstraintsMessage withConstraints(ConstraintsMessage... constraints) {
        this.constraints = constraints;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(constraints));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (TrajectoryConstraintsMessage) obj;
        return Arrays.equals(constraints, other.constraints);
    }

    @Override
    public String toString() {
        return XJson.asString("constraints", constraints);
    }
}
