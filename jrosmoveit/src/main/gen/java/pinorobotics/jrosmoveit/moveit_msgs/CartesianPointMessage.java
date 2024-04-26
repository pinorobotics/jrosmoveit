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
import id.jrosmessages.geometry_msgs.AccelMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.geometry_msgs.TwistMessage;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for moveit_msgs/CartesianPoint This message defines a point in a cartesian trajectory
 */
@MessageMetadata(
        name = CartesianPointMessage.NAME,
        fields = {"pose", "velocity", "acceleration"},
        md5sum = "d3c213cdb4382c43adbff1f2dd2cf669")
public class CartesianPointMessage implements Message {

    static final String NAME = "moveit_msgs/CartesianPoint";

    public PoseMessage pose = new PoseMessage();

    public TwistMessage velocity = new TwistMessage();

    public AccelMessage acceleration = new AccelMessage();

    public CartesianPointMessage withPose(PoseMessage pose) {
        this.pose = pose;
        return this;
    }

    public CartesianPointMessage withVelocity(TwistMessage velocity) {
        this.velocity = velocity;
        return this;
    }

    public CartesianPointMessage withAcceleration(AccelMessage acceleration) {
        this.acceleration = acceleration;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pose, velocity, acceleration);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (CartesianPointMessage) obj;
        return Objects.equals(pose, other.pose)
                && Objects.equals(velocity, other.velocity)
                && Objects.equals(acceleration, other.acceleration);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "pose", pose,
                "velocity", velocity,
                "acceleration", acceleration);
    }
}
