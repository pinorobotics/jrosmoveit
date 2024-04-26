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
import id.jrosmessages.geometry_msgs.Vector3Message;
import id.xfunction.XJson;
import java.util.Objects;

/** Definition for moveit_msgs/CostSource */
@MessageMetadata(
        name = CostSourceMessage.NAME,
        fields = {"cost_density", "aabb_min", "aabb_max"},
        md5sum = "abb7e013237dacaaa8b97e704102f908")
public class CostSourceMessage implements Message {

    static final String NAME = "moveit_msgs/CostSource";

    /** The density of the cost source */
    public double cost_density;

    /**
     * The volume of the cost source is represented as an axis-aligned bounding box (AABB) The AABB
     * is specified by two of its opposite corners
     */
    public Vector3Message aabb_min = new Vector3Message();

    public Vector3Message aabb_max = new Vector3Message();

    public CostSourceMessage withCostDensity(double cost_density) {
        this.cost_density = cost_density;
        return this;
    }

    public CostSourceMessage withAabbMin(Vector3Message aabb_min) {
        this.aabb_min = aabb_min;
        return this;
    }

    public CostSourceMessage withAabbMax(Vector3Message aabb_max) {
        this.aabb_max = aabb_max;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost_density, aabb_min, aabb_max);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (CostSourceMessage) obj;
        return cost_density == other.cost_density
                && Objects.equals(aabb_min, other.aabb_min)
                && Objects.equals(aabb_max, other.aabb_max);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "cost_density", cost_density,
                "aabb_min", aabb_min,
                "aabb_max", aabb_max);
    }
}
