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

import id.jros1messages.trajectory_msgs.JointTrajectoryMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for moveit_msgs/AttachedCollisionObject */
@MessageMetadata(
        name = AttachedCollisionObjectMessage.NAME,
        md5sum = "30199ef516f64c8bc1edb1084ce4584e")
public class AttachedCollisionObjectMessage implements Message {

    static final String NAME = "moveit_msgs/AttachedCollisionObject";

    /** The CollisionObject will be attached with a fixed joint to this link */
    public StringMessage link_name = new StringMessage();

    /**
     * This contains the actual shapes and poses for the CollisionObject to be attached to the link
     * If action is remove and no object.id is set, all objects attached to the link indicated by
     * link_name will be removed
     */
    public CollisionObjectMessage object = new CollisionObjectMessage();

    /**
     * The set of links that the attached objects are allowed to touch by default - the link_name is
     * already considered by default
     */
    public StringMessage[] touch_links = new StringMessage[0];

    /**
     * If certain links were placed in a particular posture for this object to remain attached
     * (e.g., an end effector closing around an object), the posture necessary for releasing the
     * object is stored here
     */
    public JointTrajectoryMessage detach_posture = new JointTrajectoryMessage();

    /** The weight of the attached object, if known */
    public double weight;

    public AttachedCollisionObjectMessage withLinkName(StringMessage link_name) {
        this.link_name = link_name;
        return this;
    }

    public AttachedCollisionObjectMessage withObject(CollisionObjectMessage object) {
        this.object = object;
        return this;
    }

    public AttachedCollisionObjectMessage withTouchLinks(StringMessage... touch_links) {
        this.touch_links = touch_links;
        return this;
    }

    public AttachedCollisionObjectMessage withDetachPosture(JointTrajectoryMessage detach_posture) {
        this.detach_posture = detach_posture;
        return this;
    }

    public AttachedCollisionObjectMessage withWeight(double weight) {
        this.weight = weight;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                link_name, object, Arrays.hashCode(touch_links), detach_posture, weight);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (AttachedCollisionObjectMessage) obj;
        return Objects.equals(link_name, other.link_name)
                && Objects.equals(object, other.object)
                && Arrays.equals(touch_links, other.touch_links)
                && Objects.equals(detach_posture, other.detach_posture)
                && weight == other.weight;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "link_name", link_name,
                "object", object,
                "touch_links", touch_links,
                "detach_posture", detach_posture,
                "weight", weight);
    }
}
