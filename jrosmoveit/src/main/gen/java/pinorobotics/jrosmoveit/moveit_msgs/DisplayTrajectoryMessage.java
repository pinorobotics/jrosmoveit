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
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/** Definition for moveit_msgs/DisplayTrajectory */
@MessageMetadata(name = DisplayTrajectoryMessage.NAME, md5sum = "41936b74e168ba754279ae683ce3f121")
public class DisplayTrajectoryMessage implements Message {

    static final String NAME = "moveit_msgs/DisplayTrajectory";

    /** The model id for which this path has been generated */
    public StringMessage model_id = new StringMessage();

    /**
     * The representation of the path contains position values for all the joints that are moving
     * along the path; a sequence of trajectories may be specified
     */
    public RobotTrajectoryMessage[] trajectory = new RobotTrajectoryMessage[0];

    /**
     * The robot state is used to obtain positions for all/some of the joints of the robot. It is
     * used by the path display node to determine the positions of the joints that are not specified
     * in the joint path message above. If the robot state message contains joint position
     * information for joints that are also mentioned in the joint path message, the positions in
     * the joint path message will overwrite the positions specified in the robot state message.
     */
    public RobotStateMessage trajectory_start = new RobotStateMessage();

    public DisplayTrajectoryMessage withModelId(StringMessage model_id) {
        this.model_id = model_id;
        return this;
    }

    public DisplayTrajectoryMessage withTrajectory(RobotTrajectoryMessage... trajectory) {
        this.trajectory = trajectory;
        return this;
    }

    public DisplayTrajectoryMessage withTrajectoryStart(RobotStateMessage trajectory_start) {
        this.trajectory_start = trajectory_start;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model_id, Arrays.hashCode(trajectory), trajectory_start);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (DisplayTrajectoryMessage) obj;
        return Objects.equals(model_id, other.model_id)
                && Arrays.equals(trajectory, other.trajectory)
                && Objects.equals(trajectory_start, other.trajectory_start);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "model_id", model_id,
                "trajectory", trajectory,
                "trajectory_start", trajectory_start);
    }
}
