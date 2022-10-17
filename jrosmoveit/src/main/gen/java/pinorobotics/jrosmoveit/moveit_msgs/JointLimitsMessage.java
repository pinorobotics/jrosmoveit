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
import java.util.Objects;

/** Definition for moveit_msgs/JointLimits */
@MessageMetadata(name = JointLimitsMessage.NAME, md5sum = "8ca618c7329ea46142cbc864a2efe856")
public class JointLimitsMessage implements Message {

    static final String NAME = "moveit_msgs/JointLimits";

    /**
     * This message contains information about limits of a particular joint (or control dimension)
     */
    public StringMessage joint_name = new StringMessage();

    /** true if the joint has position limits */
    public boolean has_position_limits;

    /** min and max position limits */
    public double min_position;

    public double max_position;

    /** true if joint has velocity limits */
    public boolean has_velocity_limits;

    /** max velocity limit */
    public double max_velocity;

    /** min_velocity is assumed to be -max_velocity true if joint has acceleration limits */
    public boolean has_acceleration_limits;

    /** max acceleration limit */
    public double max_acceleration;

    public JointLimitsMessage withJointName(StringMessage joint_name) {
        this.joint_name = joint_name;
        return this;
    }

    public JointLimitsMessage withHasPositionLimits(boolean has_position_limits) {
        this.has_position_limits = has_position_limits;
        return this;
    }

    public JointLimitsMessage withMinPosition(double min_position) {
        this.min_position = min_position;
        return this;
    }

    public JointLimitsMessage withMaxPosition(double max_position) {
        this.max_position = max_position;
        return this;
    }

    public JointLimitsMessage withHasVelocityLimits(boolean has_velocity_limits) {
        this.has_velocity_limits = has_velocity_limits;
        return this;
    }

    public JointLimitsMessage withMaxVelocity(double max_velocity) {
        this.max_velocity = max_velocity;
        return this;
    }

    public JointLimitsMessage withHasAccelerationLimits(boolean has_acceleration_limits) {
        this.has_acceleration_limits = has_acceleration_limits;
        return this;
    }

    public JointLimitsMessage withMaxAcceleration(double max_acceleration) {
        this.max_acceleration = max_acceleration;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                joint_name,
                has_position_limits,
                min_position,
                max_position,
                has_velocity_limits,
                max_velocity,
                has_acceleration_limits,
                max_acceleration);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (JointLimitsMessage) obj;
        return Objects.equals(joint_name, other.joint_name)
                && has_position_limits == other.has_position_limits
                && min_position == other.min_position
                && max_position == other.max_position
                && has_velocity_limits == other.has_velocity_limits
                && max_velocity == other.max_velocity
                && has_acceleration_limits == other.has_acceleration_limits
                && max_acceleration == other.max_acceleration;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "joint_name", joint_name,
                "has_position_limits", has_position_limits,
                "min_position", min_position,
                "max_position", max_position,
                "has_velocity_limits", has_velocity_limits,
                "max_velocity", max_velocity,
                "has_acceleration_limits", has_acceleration_limits,
                "max_acceleration", max_acceleration);
    }
}
