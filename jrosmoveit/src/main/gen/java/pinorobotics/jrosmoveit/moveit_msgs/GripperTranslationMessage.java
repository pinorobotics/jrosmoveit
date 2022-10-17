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

import id.jros1messages.geometry_msgs.Vector3StampedMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for moveit_msgs/GripperTranslation defines a translation for the gripper, used in
 * pickup or place tasks # for example for lifting an object off a table or approaching the table
 * for placing
 */
@MessageMetadata(name = GripperTranslationMessage.NAME, md5sum = "b53bc0ad0f717cdec3b0e42dec300121")
public class GripperTranslationMessage implements Message {

    static final String NAME = "moveit_msgs/GripperTranslation";

    /** the direction of the translation */
    public Vector3StampedMessage direction = new Vector3StampedMessage();

    /** the desired translation distance */
    public float desired_distance;

    /** the min distance that must be considered feasible before the grasp is even attempted */
    public float min_distance;

    public GripperTranslationMessage withDirection(Vector3StampedMessage direction) {
        this.direction = direction;
        return this;
    }

    public GripperTranslationMessage withDesiredDistance(float desired_distance) {
        this.desired_distance = desired_distance;
        return this;
    }

    public GripperTranslationMessage withMinDistance(float min_distance) {
        this.min_distance = min_distance;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, desired_distance, min_distance);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (GripperTranslationMessage) obj;
        return Objects.equals(direction, other.direction)
                && desired_distance == other.desired_distance
                && min_distance == other.min_distance;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "direction", direction,
                "desired_distance", desired_distance,
                "min_distance", min_distance);
    }
}
