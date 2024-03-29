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
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for moveit_msgs/PlanningSceneComponents This message defines the components that make
 * up the PlanningScene message. # The values can be used as a bitfield to specify which parts of
 * the PlanningScene message # are of interest
 */
@MessageMetadata(
        name = PlanningSceneComponentsMessage.NAME,
        md5sum = "bc993e784476960b918b6e7ad5bb58ce")
public class PlanningSceneComponentsMessage implements Message {

    static final String NAME = "moveit_msgs/PlanningSceneComponents";

    /** Scene name, model name, model root */
    public int SCENE_SETTINGS;

    /** Joint values of the robot state */
    public int ROBOT_STATE;

    /** Attached objects (including geometry) for the robot state */
    public int ROBOT_STATE_ATTACHED_OBJECTS;

    /** The names of the world objects */
    public int WORLD_OBJECT_NAMES;

    /** The geometry of the world objects */
    public int WORLD_OBJECT_GEOMETRY;

    /** The maintained octomap */
    public int OCTOMAP;

    /** The maintained list of transforms */
    public int TRANSFORMS;

    /** The allowed collision matrix */
    public int ALLOWED_COLLISION_MATRIX;

    /** The default link padding and link scaling */
    public int LINK_PADDING_AND_SCALING;

    /** The stored object colors */
    public int OBJECT_COLORS;

    /** Bitfield combining options indicated above */
    public int components;

    public PlanningSceneComponentsMessage withSCENESETTINGS(int SCENE_SETTINGS) {
        this.SCENE_SETTINGS = SCENE_SETTINGS;
        return this;
    }

    public PlanningSceneComponentsMessage withROBOTSTATE(int ROBOT_STATE) {
        this.ROBOT_STATE = ROBOT_STATE;
        return this;
    }

    public PlanningSceneComponentsMessage withROBOTSTATEATTACHEDOBJECTS(
            int ROBOT_STATE_ATTACHED_OBJECTS) {
        this.ROBOT_STATE_ATTACHED_OBJECTS = ROBOT_STATE_ATTACHED_OBJECTS;
        return this;
    }

    public PlanningSceneComponentsMessage withWORLDOBJECTNAMES(int WORLD_OBJECT_NAMES) {
        this.WORLD_OBJECT_NAMES = WORLD_OBJECT_NAMES;
        return this;
    }

    public PlanningSceneComponentsMessage withWORLDOBJECTGEOMETRY(int WORLD_OBJECT_GEOMETRY) {
        this.WORLD_OBJECT_GEOMETRY = WORLD_OBJECT_GEOMETRY;
        return this;
    }

    public PlanningSceneComponentsMessage withOCTOMAP(int OCTOMAP) {
        this.OCTOMAP = OCTOMAP;
        return this;
    }

    public PlanningSceneComponentsMessage withTRANSFORMS(int TRANSFORMS) {
        this.TRANSFORMS = TRANSFORMS;
        return this;
    }

    public PlanningSceneComponentsMessage withALLOWEDCOLLISIONMATRIX(int ALLOWED_COLLISION_MATRIX) {
        this.ALLOWED_COLLISION_MATRIX = ALLOWED_COLLISION_MATRIX;
        return this;
    }

    public PlanningSceneComponentsMessage withLINKPADDINGANDSCALING(int LINK_PADDING_AND_SCALING) {
        this.LINK_PADDING_AND_SCALING = LINK_PADDING_AND_SCALING;
        return this;
    }

    public PlanningSceneComponentsMessage withOBJECTCOLORS(int OBJECT_COLORS) {
        this.OBJECT_COLORS = OBJECT_COLORS;
        return this;
    }

    public PlanningSceneComponentsMessage withComponents(int components) {
        this.components = components;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                SCENE_SETTINGS,
                ROBOT_STATE,
                ROBOT_STATE_ATTACHED_OBJECTS,
                WORLD_OBJECT_NAMES,
                WORLD_OBJECT_GEOMETRY,
                OCTOMAP,
                TRANSFORMS,
                ALLOWED_COLLISION_MATRIX,
                LINK_PADDING_AND_SCALING,
                OBJECT_COLORS,
                components);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (PlanningSceneComponentsMessage) obj;
        return SCENE_SETTINGS == other.SCENE_SETTINGS
                && ROBOT_STATE == other.ROBOT_STATE
                && ROBOT_STATE_ATTACHED_OBJECTS == other.ROBOT_STATE_ATTACHED_OBJECTS
                && WORLD_OBJECT_NAMES == other.WORLD_OBJECT_NAMES
                && WORLD_OBJECT_GEOMETRY == other.WORLD_OBJECT_GEOMETRY
                && OCTOMAP == other.OCTOMAP
                && TRANSFORMS == other.TRANSFORMS
                && ALLOWED_COLLISION_MATRIX == other.ALLOWED_COLLISION_MATRIX
                && LINK_PADDING_AND_SCALING == other.LINK_PADDING_AND_SCALING
                && OBJECT_COLORS == other.OBJECT_COLORS
                && components == other.components;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "SCENE_SETTINGS", SCENE_SETTINGS,
                "ROBOT_STATE", ROBOT_STATE,
                "ROBOT_STATE_ATTACHED_OBJECTS", ROBOT_STATE_ATTACHED_OBJECTS,
                "WORLD_OBJECT_NAMES", WORLD_OBJECT_NAMES,
                "WORLD_OBJECT_GEOMETRY", WORLD_OBJECT_GEOMETRY,
                "OCTOMAP", OCTOMAP,
                "TRANSFORMS", TRANSFORMS,
                "ALLOWED_COLLISION_MATRIX", ALLOWED_COLLISION_MATRIX,
                "LINK_PADDING_AND_SCALING", LINK_PADDING_AND_SCALING,
                "OBJECT_COLORS", OBJECT_COLORS,
                "components", components);
    }
}
