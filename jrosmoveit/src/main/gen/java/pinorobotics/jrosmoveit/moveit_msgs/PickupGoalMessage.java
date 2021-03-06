/*
 * Copyright 2021 msgmonster project
 * 
 * Website: https://github.com/pinorobotics/msgmonster
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

/*
 * msgmonster autogenerated Message class for jrosclient
 * 
 * Generated for ROS msg file: moveit_msgs/PickupGoal
 */

package pinorobotics.jrosmoveit.moveit_msgs;

import java.util.Objects;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.kineticstreamer.annotations.Streamed;
import id.xfunction.XJson;

import id.jrosmessages.std_msgs.StringMessage;
import java.util.Arrays;

/**
 * Definition for moveit_msgs/PickupGoal
 * ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======
 * # An action for picking up an object
 */
@MessageMetadata(
    type = PickupGoalMessage.NAME,
    md5sum = "3a71f6f9bc6e640594ce6a411ccfe764")
public class PickupGoalMessage implements Message {
   
   static final String NAME = "moveit_msgs/PickupGoal";

   /**
    * The name of the object to pick up (as known in the planning scene)
    */
   @Streamed
   public StringMessage target_name = new StringMessage();
   
   /**
    * which group should be used to plan for pickup
    */
   @Streamed
   public StringMessage group_name = new StringMessage();
   
   /**
    * which end-effector to be used for pickup (ideally descending from the group above)
    */
   @Streamed
   public StringMessage end_effector = new StringMessage();
   
   /**
    * a list of possible grasps to be used. At least one grasp must be filled in
    */
   @Streamed
   public GraspMessage[] possible_grasps = new GraspMessage[0];
   
   /**
    * the name that the support surface (e.g. table) has in the collision map
    * can be left empty if no name is available
    */
   @Streamed
   public StringMessage support_surface_name = new StringMessage();
   
   /**
    * whether collisions between the gripper and the support surface should be acceptable
    * during move from pre-grasp to grasp and during lift. Collisions when moving to the
    * pre-grasp location are still not allowed even if this is set to true.
    */
   @Streamed
   public boolean allow_gripper_support_collision;
   
   /**
    * The names of the links the object to be attached is allowed to touch;
    * If this is left empty, it defaults to the links in the used end-effector
    */
   @Streamed
   public StringMessage[] attached_object_touch_links = new StringMessage[0];
   
   /**
    * Optionally notify the pick action that it should approach the object further,
    * as much as possible (this minimizing the distance to the object before the grasp)
    * along the approach direction; Note: this option changes the grasping poses
    * supplied in possible_grasps[] such that they are closer to the object when possible.
    */
   @Streamed
   public boolean minimize_object_distance;
   
   /**
    * Optional constraints to be imposed on every point in the motion plan
    */
   @Streamed
   public ConstraintsMessage path_constraints = new ConstraintsMessage();
   
   /**
    * The name of the motion planner to use. If no name is specified,
    * a default motion planner will be used
    */
   @Streamed
   public StringMessage planner_id = new StringMessage();
   
   /**
    * an optional list of obstacles that we have semantic information about
    * and that can be touched/pushed/moved in the course of grasping;
    * CAREFUL: If the object name 'all' is used, collisions with all objects are disabled during the approach and lift.
    */
   @Streamed
   public StringMessage[] allowed_touch_objects = new StringMessage[0];
   
   /**
    * The maximum amount of time the motion planner is allowed to plan for
    */
   @Streamed
   public double allowed_planning_time;
   
   /**
    * Planning options
    */
   @Streamed
   public PlanningOptionsMessage planning_options = new PlanningOptionsMessage();
   
   public PickupGoalMessage withTargetName(StringMessage target_name) {
       this.target_name = target_name;
       return this;
   }
   
   public PickupGoalMessage withGroupName(StringMessage group_name) {
       this.group_name = group_name;
       return this;
   }
   
   public PickupGoalMessage withEndEffector(StringMessage end_effector) {
       this.end_effector = end_effector;
       return this;
   }
   
   public PickupGoalMessage withPossibleGrasps(GraspMessage... possible_grasps) {
       this.possible_grasps = possible_grasps;
       return this;
   }
   
   public PickupGoalMessage withSupportSurfaceName(StringMessage support_surface_name) {
       this.support_surface_name = support_surface_name;
       return this;
   }
   
   public PickupGoalMessage withAllowGripperSupportCollision(boolean allow_gripper_support_collision) {
       this.allow_gripper_support_collision = allow_gripper_support_collision;
       return this;
   }
   
   public PickupGoalMessage withAttachedObjectTouchLinks(StringMessage... attached_object_touch_links) {
       this.attached_object_touch_links = attached_object_touch_links;
       return this;
   }
   
   public PickupGoalMessage withMinimizeObjectDistance(boolean minimize_object_distance) {
       this.minimize_object_distance = minimize_object_distance;
       return this;
   }
   
   public PickupGoalMessage withPathConstraints(ConstraintsMessage path_constraints) {
       this.path_constraints = path_constraints;
       return this;
   }
   
   public PickupGoalMessage withPlannerId(StringMessage planner_id) {
       this.planner_id = planner_id;
       return this;
   }
   
   public PickupGoalMessage withAllowedTouchObjects(StringMessage... allowed_touch_objects) {
       this.allowed_touch_objects = allowed_touch_objects;
       return this;
   }
   
   public PickupGoalMessage withAllowedPlanningTime(double allowed_planning_time) {
       this.allowed_planning_time = allowed_planning_time;
       return this;
   }
   
   public PickupGoalMessage withPlanningOptions(PlanningOptionsMessage planning_options) {
       this.planning_options = planning_options;
       return this;
   }
   
   @Override
   public int hashCode() {
       return Objects.hash(
           target_name,
           group_name,
           end_effector,
           Arrays.hashCode(possible_grasps),
           support_surface_name,
           allow_gripper_support_collision,
           Arrays.hashCode(attached_object_touch_links),
           minimize_object_distance,
           path_constraints,
           planner_id,
           Arrays.hashCode(allowed_touch_objects),
           allowed_planning_time,
           planning_options
       );
   }
   
   @Override
   public boolean equals(Object obj) {
       var other = (PickupGoalMessage) obj;
       return
           Objects.equals(target_name, other.target_name) &&
           Objects.equals(group_name, other.group_name) &&
           Objects.equals(end_effector, other.end_effector) &&
           Arrays.equals(possible_grasps, other.possible_grasps) &&
           Objects.equals(support_surface_name, other.support_surface_name) &&
           allow_gripper_support_collision == other.allow_gripper_support_collision &&
           Arrays.equals(attached_object_touch_links, other.attached_object_touch_links) &&
           minimize_object_distance == other.minimize_object_distance &&
           Objects.equals(path_constraints, other.path_constraints) &&
           Objects.equals(planner_id, other.planner_id) &&
           Arrays.equals(allowed_touch_objects, other.allowed_touch_objects) &&
           allowed_planning_time == other.allowed_planning_time &&
           Objects.equals(planning_options, other.planning_options)
       ;
   }
   
   @Override
   public String toString() {
       return XJson.asString(
           "target_name", target_name,
           "group_name", group_name,
           "end_effector", end_effector,
           "possible_grasps", possible_grasps,
           "support_surface_name", support_surface_name,
           "allow_gripper_support_collision", allow_gripper_support_collision,
           "attached_object_touch_links", attached_object_touch_links,
           "minimize_object_distance", minimize_object_distance,
           "path_constraints", path_constraints,
           "planner_id", planner_id,
           "allowed_touch_objects", allowed_touch_objects,
           "allowed_planning_time", allowed_planning_time,
           "planning_options", planning_options
       );
   }
   
}
