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

/**
 * Definition for moveit_msgs/Grasp This message contains a description of a grasp that would be
 * used with a particular end-effector to grasp an object, including how to approach it, grip it,
 * etc. This message does not contain any information about a "grasp point" (a position ON the
 * object). Whatever generates this message should have already combined information about grasp
 * points with information about the geometry of the end-effector to compute the grasp_pose in this
 * message.
 */
@MessageMetadata(name = GraspMessage.NAME, md5sum = "e26c8fb64f589c33c5d5e54bd7b5e4cb")
public class GraspMessage implements Message {

    static final String NAME = "moveit_msgs/Grasp";

    /** A name for this grasp */
    public StringMessage id = new StringMessage();

    /** The internal posture of the hand for the pre-grasp only positions are used */
    public JointTrajectoryMessage pre_grasp_posture = new JointTrajectoryMessage();

    /** The internal posture of the hand for the grasp positions and efforts are used */
    public JointTrajectoryMessage grasp_posture = new JointTrajectoryMessage();

    /**
     * The position of the end-effector for the grasp. This is the pose of the "parent_link" of the
     * end-effector, not actually the pose of any link *in* the end-effector. Typically this would
     * be the pose of the most distal wrist link before the hand (end-effector) links began.
     */
    public PoseStampedMessage grasp_pose = new PoseStampedMessage();

    /**
     * The estimated probability of success for this grasp, or some other measure of how "good" it
     * is.
     */
    public double grasp_quality;

    /** The approach direction to take before picking an object */
    public GripperTranslationMessage pre_grasp_approach = new GripperTranslationMessage();

    /** The retreat direction to take after a grasp has been completed (object is attached) */
    public GripperTranslationMessage post_grasp_retreat = new GripperTranslationMessage();

    /**
     * The retreat motion to perform when releasing the object; this information is not necessary
     * for the grasp itself, but when releasing the object, the information will be necessary. The
     * grasp used to perform a pickup is returned as part of the result, so this information is
     * available for later use.
     */
    public GripperTranslationMessage post_place_retreat = new GripperTranslationMessage();

    /** the maximum contact force to use while grasping (less or equal to 0 to disable) */
    public float max_contact_force;

    /**
     * an optional list of obstacles that we have semantic information about and that can be
     * touched/pushed/moved in the course of grasping
     */
    public StringMessage[] allowed_touch_objects = new StringMessage[0];

    public GraspMessage withId(StringMessage id) {
        this.id = id;
        return this;
    }

    public GraspMessage withPreGraspPosture(JointTrajectoryMessage pre_grasp_posture) {
        this.pre_grasp_posture = pre_grasp_posture;
        return this;
    }

    public GraspMessage withGraspPosture(JointTrajectoryMessage grasp_posture) {
        this.grasp_posture = grasp_posture;
        return this;
    }

    public GraspMessage withGraspPose(PoseStampedMessage grasp_pose) {
        this.grasp_pose = grasp_pose;
        return this;
    }

    public GraspMessage withGraspQuality(double grasp_quality) {
        this.grasp_quality = grasp_quality;
        return this;
    }

    public GraspMessage withPreGraspApproach(GripperTranslationMessage pre_grasp_approach) {
        this.pre_grasp_approach = pre_grasp_approach;
        return this;
    }

    public GraspMessage withPostGraspRetreat(GripperTranslationMessage post_grasp_retreat) {
        this.post_grasp_retreat = post_grasp_retreat;
        return this;
    }

    public GraspMessage withPostPlaceRetreat(GripperTranslationMessage post_place_retreat) {
        this.post_place_retreat = post_place_retreat;
        return this;
    }

    public GraspMessage withMaxContactForce(float max_contact_force) {
        this.max_contact_force = max_contact_force;
        return this;
    }

    public GraspMessage withAllowedTouchObjects(StringMessage... allowed_touch_objects) {
        this.allowed_touch_objects = allowed_touch_objects;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                pre_grasp_posture,
                grasp_posture,
                grasp_pose,
                grasp_quality,
                pre_grasp_approach,
                post_grasp_retreat,
                post_place_retreat,
                max_contact_force,
                Arrays.hashCode(allowed_touch_objects));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (GraspMessage) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(pre_grasp_posture, other.pre_grasp_posture)
                && Objects.equals(grasp_posture, other.grasp_posture)
                && Objects.equals(grasp_pose, other.grasp_pose)
                && grasp_quality == other.grasp_quality
                && Objects.equals(pre_grasp_approach, other.pre_grasp_approach)
                && Objects.equals(post_grasp_retreat, other.post_grasp_retreat)
                && Objects.equals(post_place_retreat, other.post_place_retreat)
                && max_contact_force == other.max_contact_force
                && Arrays.equals(allowed_touch_objects, other.allowed_touch_objects);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "id", id,
                "pre_grasp_posture", pre_grasp_posture,
                "grasp_posture", grasp_posture,
                "grasp_pose", grasp_pose,
                "grasp_quality", grasp_quality,
                "pre_grasp_approach", pre_grasp_approach,
                "post_grasp_retreat", post_grasp_retreat,
                "post_place_retreat", post_place_retreat,
                "max_contact_force", max_contact_force,
                "allowed_touch_objects", allowed_touch_objects);
    }
}
