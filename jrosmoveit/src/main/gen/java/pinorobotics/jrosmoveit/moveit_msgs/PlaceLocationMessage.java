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
import id.jros1messages.trajectory_msgs.JointTrajectoryMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for moveit_msgs/PlaceLocation */
@MessageMetadata(name = PlaceLocationMessage.NAME, md5sum = "7b53f032c68481686026c3e9223d0713")
public class PlaceLocationMessage implements Message {

    static final String NAME = "moveit_msgs/PlaceLocation";

    /** A name for this grasp */
    public StringMessage id = new StringMessage();

    /** The internal posture of the hand for the grasp positions and efforts are used */
    public JointTrajectoryMessage post_place_posture = new JointTrajectoryMessage();

    /**
     * The position of the end-effector for the grasp relative to a reference frame (that is always
     * specified elsewhere, not in this message)
     */
    public PoseStampedMessage place_pose = new PoseStampedMessage();

    /**
     * The estimated probability of success for this place, or some other measure of how "good" it
     * is.
     */
    public double quality;

    /** The approach motion */
    public GripperTranslationMessage pre_place_approach = new GripperTranslationMessage();

    /** The retreat motion */
    public GripperTranslationMessage post_place_retreat = new GripperTranslationMessage();

    /**
     * an optional list of obstacles that we have semantic information about and that can be
     * touched/pushed/moved in the course of grasping
     */
    public StringMessage[] allowed_touch_objects = new StringMessage[0];

    public PlaceLocationMessage withId(StringMessage id) {
        this.id = id;
        return this;
    }

    public PlaceLocationMessage withPostPlacePosture(JointTrajectoryMessage post_place_posture) {
        this.post_place_posture = post_place_posture;
        return this;
    }

    public PlaceLocationMessage withPlacePose(PoseStampedMessage place_pose) {
        this.place_pose = place_pose;
        return this;
    }

    public PlaceLocationMessage withQuality(double quality) {
        this.quality = quality;
        return this;
    }

    public PlaceLocationMessage withPrePlaceApproach(GripperTranslationMessage pre_place_approach) {
        this.pre_place_approach = pre_place_approach;
        return this;
    }

    public PlaceLocationMessage withPostPlaceRetreat(GripperTranslationMessage post_place_retreat) {
        this.post_place_retreat = post_place_retreat;
        return this;
    }

    public PlaceLocationMessage withAllowedTouchObjects(StringMessage... allowed_touch_objects) {
        this.allowed_touch_objects = allowed_touch_objects;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                post_place_posture,
                place_pose,
                quality,
                pre_place_approach,
                post_place_retreat,
                Arrays.hashCode(allowed_touch_objects));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (PlaceLocationMessage) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(post_place_posture, other.post_place_posture)
                && Objects.equals(place_pose, other.place_pose)
                && quality == other.quality
                && Objects.equals(pre_place_approach, other.pre_place_approach)
                && Objects.equals(post_place_retreat, other.post_place_retreat)
                && Arrays.equals(allowed_touch_objects, other.allowed_touch_objects);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "id", id,
                "post_place_posture", post_place_posture,
                "place_pose", place_pose,
                "quality", quality,
                "pre_place_approach", pre_place_approach,
                "post_place_retreat", post_place_retreat,
                "allowed_touch_objects", allowed_touch_objects);
    }
}
