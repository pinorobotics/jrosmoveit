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

import id.jros1messages.geometry_msgs.PoseStampedMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.Int32Message;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for moveit_msgs/VisibilityConstraint The constraint is useful to maintain visibility
 * to a disc (the target) in a particular frame. # This disc forms the base of a visibiliy cone
 * whose tip is at the origin of the sensor. # Maintaining visibility is done by ensuring the robot
 * does not obstruct the visibility cone. # Note: # This constraint does NOT enforce minimum or
 * maximum distances between the sensor # and the target, nor does it enforce the target to be in
 * the field of view of # the sensor. A PositionConstraint can (and probably should) be used for
 * such purposes.
 */
@MessageMetadata(
        name = VisibilityConstraintMessage.NAME,
        md5sum = "62cda903bfe31ff2e5fcdc3810d577ad")
public class VisibilityConstraintMessage implements Message {

    static final String NAME = "moveit_msgs/VisibilityConstraint";

    public enum UnknownType {
        /**
         * The axis that is assumed to indicate the direction of view for the sensor X = 2, Y = 1, Z
         * = 0
         */
        SENSOR_Z,

        SENSOR_Y,

        SENSOR_X,
    }

    /** The radius of the disc that should be maintained visible */
    public double target_radius;

    /**
     * The pose of the disc; as the robot moves, the pose of the disc may change as well This can be
     * in the frame of a particular robot link, for example
     */
    public PoseStampedMessage target_pose = new PoseStampedMessage();

    /**
     * From the sensor origin towards the target, the disc forms a visibility cone This cone is
     * approximated using many sides. For example, when using 4 sides, that in fact makes the
     * visibility region be a pyramid. This value should always be 3 or more.
     */
    public Int32Message cone_sides = new Int32Message();

    /**
     * The pose in which visibility is to be maintained. The frame id should represent the robot
     * link to which the sensor is attached. It is assumed the sensor can look directly at the
     * target, in any direction. This assumption is usually not true, but additional
     * PositionConstraints can resolve this issue.
     */
    public PoseStampedMessage sensor_pose = new PoseStampedMessage();

    /**
     * Even though the disc is maintained visible, the visibility cone can be very small because of
     * the orientation of the disc with respect to the sensor. It is possible for example to view
     * the disk almost from a side, in which case the visibility cone can end up having close to 0
     * volume. The view angle is defined to be the angle between the normal to the visibility disc
     * and the direction vector from the sensor origin. The value below represents the minimum
     * desired view angle. For a perfect view, this value will be 0 (the two vectors are exact
     * opposites). For a completely obstructed view this value will be Pi/2 (the vectors are
     * perpendicular). This value defined below is the maximum view angle to be maintained. This
     * should be a value in the open interval (0, Pi/2). If 0 is set, the view angle is NOT
     * enforced.
     */
    public double max_view_angle;

    /**
     * This angle is used similarly to max_view_angle but limits the maximum angle between the
     * sensor origin direction vector and the axis that connects the sensor origin to the target
     * frame origin. The value is again in the range (0, Pi/2) and is NOT enforced if set to 0.
     */
    public double max_range_angle;

    public byte sensor_view_direction;

    /**
     * A weighting factor for this constraint (denotes relative importance to other constraints.
     * Closer to zero means less important)
     */
    public double weight;

    public VisibilityConstraintMessage withTargetRadius(double target_radius) {
        this.target_radius = target_radius;
        return this;
    }

    public VisibilityConstraintMessage withTargetPose(PoseStampedMessage target_pose) {
        this.target_pose = target_pose;
        return this;
    }

    public VisibilityConstraintMessage withConeSides(Int32Message cone_sides) {
        this.cone_sides = cone_sides;
        return this;
    }

    public VisibilityConstraintMessage withSensorPose(PoseStampedMessage sensor_pose) {
        this.sensor_pose = sensor_pose;
        return this;
    }

    public VisibilityConstraintMessage withMaxViewAngle(double max_view_angle) {
        this.max_view_angle = max_view_angle;
        return this;
    }

    public VisibilityConstraintMessage withMaxRangeAngle(double max_range_angle) {
        this.max_range_angle = max_range_angle;
        return this;
    }

    public VisibilityConstraintMessage withSensorViewDirection(byte sensor_view_direction) {
        this.sensor_view_direction = sensor_view_direction;
        return this;
    }

    public VisibilityConstraintMessage withWeight(double weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                target_radius,
                target_pose,
                cone_sides,
                sensor_pose,
                max_view_angle,
                max_range_angle,
                sensor_view_direction,
                weight);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (VisibilityConstraintMessage) obj;
        return target_radius == other.target_radius
                && Objects.equals(target_pose, other.target_pose)
                && Objects.equals(cone_sides, other.cone_sides)
                && Objects.equals(sensor_pose, other.sensor_pose)
                && max_view_angle == other.max_view_angle
                && max_range_angle == other.max_range_angle
                && sensor_view_direction == other.sensor_view_direction
                && weight == other.weight;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "target_radius", target_radius,
                "target_pose", target_pose,
                "cone_sides", cone_sides,
                "sensor_pose", sensor_pose,
                "max_view_angle", max_view_angle,
                "max_range_angle", max_range_angle,
                "sensor_view_direction", sensor_view_direction,
                "weight", weight);
    }
}
