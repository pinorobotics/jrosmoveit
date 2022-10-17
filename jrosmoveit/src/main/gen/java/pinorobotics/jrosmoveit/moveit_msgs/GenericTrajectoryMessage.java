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

import id.jros1messages.std_msgs.HeaderMessage;
import id.jros1messages.trajectory_msgs.JointTrajectoryMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for moveit_msgs/GenericTrajectory A generic trajectory message that may either encode
 * a joint- or cartesian-space trajectory, or both # Trajectories encoded in this message are
 * considered semantically equivalent
 */
@MessageMetadata(name = GenericTrajectoryMessage.NAME, md5sum = "d68b5c73072efa2012238a77e49c2c58")
public class GenericTrajectoryMessage implements Message {

    static final String NAME = "moveit_msgs/GenericTrajectory";

    public HeaderMessage header = new HeaderMessage();

    public JointTrajectoryMessage[] joint_trajectory = new JointTrajectoryMessage[0];

    public CartesianTrajectoryMessage[] cartesian_trajectory = new CartesianTrajectoryMessage[0];

    public GenericTrajectoryMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public GenericTrajectoryMessage withJointTrajectory(
            JointTrajectoryMessage... joint_trajectory) {
        this.joint_trajectory = joint_trajectory;
        return this;
    }

    public GenericTrajectoryMessage withCartesianTrajectory(
            CartesianTrajectoryMessage... cartesian_trajectory) {
        this.cartesian_trajectory = cartesian_trajectory;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                header, Arrays.hashCode(joint_trajectory), Arrays.hashCode(cartesian_trajectory));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (GenericTrajectoryMessage) obj;
        return Objects.equals(header, other.header)
                && Arrays.equals(joint_trajectory, other.joint_trajectory)
                && Arrays.equals(cartesian_trajectory, other.cartesian_trajectory);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "joint_trajectory", joint_trajectory,
                "cartesian_trajectory", cartesian_trajectory);
    }
}
