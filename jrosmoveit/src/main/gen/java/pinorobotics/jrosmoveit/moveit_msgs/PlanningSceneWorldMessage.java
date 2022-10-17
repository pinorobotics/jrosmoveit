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

import id.jros1messages.octomap_msgs.OctomapWithPoseMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for moveit_msgs/PlanningSceneWorld */
@MessageMetadata(name = PlanningSceneWorldMessage.NAME, md5sum = "79457311445f53d410ab4e3781de8447")
public class PlanningSceneWorldMessage implements Message {

    static final String NAME = "moveit_msgs/PlanningSceneWorld";

    /** collision objects */
    public CollisionObjectMessage[] collision_objects = new CollisionObjectMessage[0];

    /** The octomap that represents additional collision data */
    public OctomapWithPoseMessage octomap = new OctomapWithPoseMessage();

    public PlanningSceneWorldMessage withCollisionObjects(
            CollisionObjectMessage... collision_objects) {
        this.collision_objects = collision_objects;
        return this;
    }

    public PlanningSceneWorldMessage withOctomap(OctomapWithPoseMessage octomap) {
        this.octomap = octomap;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(collision_objects), octomap);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (PlanningSceneWorldMessage) obj;
        return Arrays.equals(collision_objects, other.collision_objects)
                && Objects.equals(octomap, other.octomap);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "collision_objects", collision_objects,
                "octomap", octomap);
    }
}
