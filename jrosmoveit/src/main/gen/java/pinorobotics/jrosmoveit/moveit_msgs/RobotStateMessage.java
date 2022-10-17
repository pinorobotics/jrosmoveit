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

import id.jros1messages.sensor_msgs.JointStateMessage;
import id.jros1messages.sensor_msgs.MultiDOFJointStateMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for moveit_msgs/RobotState */
@MessageMetadata(name = RobotStateMessage.NAME, md5sum = "968156f4aa4cb4018f1f2293eebcea8f")
public class RobotStateMessage implements Message {

    static final String NAME = "moveit_msgs/RobotState";

    /**
     * This message contains information about the robot state, i.e. the positions of its joints and
     * links
     */
    public JointStateMessage joint_state = new JointStateMessage();

    /** Joints that may have multiple DOF are specified here */
    public MultiDOFJointStateMessage multi_dof_joint_state = new MultiDOFJointStateMessage();

    /** Attached collision objects (attached to some link on the robot) */
    public AttachedCollisionObjectMessage[] attached_collision_objects =
            new AttachedCollisionObjectMessage[0];

    /**
     * Flag indicating whether this scene is to be interpreted as a diff with respect to some other
     * scene This is mostly important for handling the attached bodies (whether or not to clear the
     * attached bodies of a moveit::core::RobotState before updating it with this message)
     */
    public boolean is_diff;

    public RobotStateMessage withJointState(JointStateMessage joint_state) {
        this.joint_state = joint_state;
        return this;
    }

    public RobotStateMessage withMultiDofJointState(
            MultiDOFJointStateMessage multi_dof_joint_state) {
        this.multi_dof_joint_state = multi_dof_joint_state;
        return this;
    }

    public RobotStateMessage withAttachedCollisionObjects(
            AttachedCollisionObjectMessage... attached_collision_objects) {
        this.attached_collision_objects = attached_collision_objects;
        return this;
    }

    public RobotStateMessage withIsDiff(boolean is_diff) {
        this.is_diff = is_diff;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                joint_state,
                multi_dof_joint_state,
                Arrays.hashCode(attached_collision_objects),
                is_diff);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (RobotStateMessage) obj;
        return Objects.equals(joint_state, other.joint_state)
                && Objects.equals(multi_dof_joint_state, other.multi_dof_joint_state)
                && Arrays.equals(attached_collision_objects, other.attached_collision_objects)
                && is_diff == other.is_diff;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "joint_state", joint_state,
                "multi_dof_joint_state", multi_dof_joint_state,
                "attached_collision_objects", attached_collision_objects,
                "is_diff", is_diff);
    }
}
