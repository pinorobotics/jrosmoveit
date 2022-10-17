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
import id.jrosmessages.geometry_msgs.Point32Message;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.xfunction.XJson;
import java.util.Objects;

/** Definition for moveit_msgs/OrientedBoundingBox */
@MessageMetadata(
        name = OrientedBoundingBoxMessage.NAME,
        md5sum = "da3bd98e7cb14efa4141367a9d886ee7")
public class OrientedBoundingBoxMessage implements Message {

    static final String NAME = "moveit_msgs/OrientedBoundingBox";

    /** the pose of the box */
    public PoseMessage pose = new PoseMessage();

    /** the extents of the box, assuming the center is at the origin */
    public Point32Message extents = new Point32Message();

    public OrientedBoundingBoxMessage withPose(PoseMessage pose) {
        this.pose = pose;
        return this;
    }

    public OrientedBoundingBoxMessage withExtents(Point32Message extents) {
        this.extents = extents;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pose, extents);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (OrientedBoundingBoxMessage) obj;
        return Objects.equals(pose, other.pose) && Objects.equals(extents, other.extents);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "pose", pose,
                "extents", extents);
    }
}
