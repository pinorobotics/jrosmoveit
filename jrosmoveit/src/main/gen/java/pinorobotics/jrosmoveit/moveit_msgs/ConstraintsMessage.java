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

/**
 * Definition for moveit_msgs/Constraints This message contains a list of motion planning
 * constraints. # All constraints must be satisfied for a goal to be considered valid
 */
@MessageMetadata(name = ConstraintsMessage.NAME, md5sum = "cfd22a10c51e0dc2b28d98772d2b55d5")
public class ConstraintsMessage implements Message {

    static final String NAME = "moveit_msgs/Constraints";

    public StringMessage name = new StringMessage();

    public JointConstraintMessage[] joint_constraints = new JointConstraintMessage[0];

    public PositionConstraintMessage[] position_constraints = new PositionConstraintMessage[0];

    public OrientationConstraintMessage[] orientation_constraints =
            new OrientationConstraintMessage[0];

    public VisibilityConstraintMessage[] visibility_constraints =
            new VisibilityConstraintMessage[0];

    public ConstraintsMessage withName(StringMessage name) {
        this.name = name;
        return this;
    }

    public ConstraintsMessage withJointConstraints(JointConstraintMessage... joint_constraints) {
        this.joint_constraints = joint_constraints;
        return this;
    }

    public ConstraintsMessage withPositionConstraints(
            PositionConstraintMessage... position_constraints) {
        this.position_constraints = position_constraints;
        return this;
    }

    public ConstraintsMessage withOrientationConstraints(
            OrientationConstraintMessage... orientation_constraints) {
        this.orientation_constraints = orientation_constraints;
        return this;
    }

    public ConstraintsMessage withVisibilityConstraints(
            VisibilityConstraintMessage... visibility_constraints) {
        this.visibility_constraints = visibility_constraints;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                Arrays.hashCode(joint_constraints),
                Arrays.hashCode(position_constraints),
                Arrays.hashCode(orientation_constraints),
                Arrays.hashCode(visibility_constraints));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (ConstraintsMessage) obj;
        return Objects.equals(name, other.name)
                && Arrays.equals(joint_constraints, other.joint_constraints)
                && Arrays.equals(position_constraints, other.position_constraints)
                && Arrays.equals(orientation_constraints, other.orientation_constraints)
                && Arrays.equals(visibility_constraints, other.visibility_constraints);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "name", name,
                "joint_constraints", joint_constraints,
                "position_constraints", position_constraints,
                "orientation_constraints", orientation_constraints,
                "visibility_constraints", visibility_constraints);
    }
}
