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

/** Definition for moveit_msgs/JointConstraint */
@MessageMetadata(
        name = JointConstraintMessage.NAME,
        fields = {"joint_name", "position", "tolerance_above", "tolerance_below", "weight"},
        md5sum = "c02a15146bec0ce13564807805b008f0")
public class JointConstraintMessage implements Message {

    static final String NAME = "moveit_msgs/JointConstraint";

    /** Constrain the position of a joint to be within a certain bound */
    public StringMessage joint_name = new StringMessage();

    /** the bound to be achieved is [position - tolerance_below, position + tolerance_above] */
    public double position;

    public double tolerance_above;

    public double tolerance_below;

    /**
     * A weighting factor for this constraint (denotes relative importance to other constraints.
     * Closer to zero means less important)
     */
    public double weight;

    public JointConstraintMessage withJointName(StringMessage joint_name) {
        this.joint_name = joint_name;
        return this;
    }

    public JointConstraintMessage withPosition(double position) {
        this.position = position;
        return this;
    }

    public JointConstraintMessage withToleranceAbove(double tolerance_above) {
        this.tolerance_above = tolerance_above;
        return this;
    }

    public JointConstraintMessage withToleranceBelow(double tolerance_below) {
        this.tolerance_below = tolerance_below;
        return this;
    }

    public JointConstraintMessage withWeight(double weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(joint_name, position, tolerance_above, tolerance_below, weight);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (JointConstraintMessage) obj;
        return Objects.equals(joint_name, other.joint_name)
                && position == other.position
                && tolerance_above == other.tolerance_above
                && tolerance_below == other.tolerance_below
                && weight == other.weight;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "joint_name", joint_name,
                "position", position,
                "tolerance_above", tolerance_above,
                "tolerance_below", tolerance_below,
                "weight", weight);
    }
}
