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
import java.util.Objects;

/**
 * Definition for moveit_msgs/MotionPlanResponse The representation of a solution to a planning
 * problem
 */
@MessageMetadata(name = MotionPlanResponseMessage.NAME, md5sum = "141570ca7cae4d1dac9775d42407292d")
public class MotionPlanResponseMessage implements Message {

    static final String NAME = "moveit_msgs/MotionPlanResponse";

    /** The corresponding robot state */
    public RobotStateMessage trajectory_start = new RobotStateMessage();

    /** The group used for planning (usually the same as in the request) */
    public StringMessage group_name = new StringMessage();

    /** A solution trajectory, if found */
    public RobotTrajectoryMessage trajectory = new RobotTrajectoryMessage();

    /** Planning time (seconds) */
    public double planning_time;

    /** Error code - encodes the overall reason for failure */
    public MoveItErrorCodesMessage error_code = new MoveItErrorCodesMessage();

    public MotionPlanResponseMessage withTrajectoryStart(RobotStateMessage trajectory_start) {
        this.trajectory_start = trajectory_start;
        return this;
    }

    public MotionPlanResponseMessage withGroupName(StringMessage group_name) {
        this.group_name = group_name;
        return this;
    }

    public MotionPlanResponseMessage withTrajectory(RobotTrajectoryMessage trajectory) {
        this.trajectory = trajectory;
        return this;
    }

    public MotionPlanResponseMessage withPlanningTime(double planning_time) {
        this.planning_time = planning_time;
        return this;
    }

    public MotionPlanResponseMessage withErrorCode(MoveItErrorCodesMessage error_code) {
        this.error_code = error_code;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trajectory_start, group_name, trajectory, planning_time, error_code);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (MotionPlanResponseMessage) obj;
        return Objects.equals(trajectory_start, other.trajectory_start)
                && Objects.equals(group_name, other.group_name)
                && Objects.equals(trajectory, other.trajectory)
                && planning_time == other.planning_time
                && Objects.equals(error_code, other.error_code);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "trajectory_start", trajectory_start,
                "group_name", group_name,
                "trajectory", trajectory,
                "planning_time", planning_time,
                "error_code", error_code);
    }
}
