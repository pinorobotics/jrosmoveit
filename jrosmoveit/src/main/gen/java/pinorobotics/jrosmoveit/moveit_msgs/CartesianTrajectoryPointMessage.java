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
import id.jrosmessages.primitives.Duration;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for moveit_msgs/CartesianTrajectoryPoint The definition of a cartesian point in a
 * trajectory. Defines the cartesian state of the point and it's time, # following the pattern of
 * the JointTrajectory message
 */
@MessageMetadata(
        name = CartesianTrajectoryPointMessage.NAME,
        md5sum = "e996ea294f646e6911b3e85e624f5acf")
public class CartesianTrajectoryPointMessage implements Message {

    static final String NAME = "moveit_msgs/CartesianTrajectoryPoint";

    public CartesianPointMessage point = new CartesianPointMessage();

    public Duration time_from_start = new Duration();

    public CartesianTrajectoryPointMessage withPoint(CartesianPointMessage point) {
        this.point = point;
        return this;
    }

    public CartesianTrajectoryPointMessage withTimeFromStart(Duration time_from_start) {
        this.time_from_start = time_from_start;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, time_from_start);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (CartesianTrajectoryPointMessage) obj;
        return Objects.equals(point, other.point)
                && Objects.equals(time_from_start, other.time_from_start);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "point", point,
                "time_from_start", time_from_start);
    }
}
