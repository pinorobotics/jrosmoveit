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
import java.util.Arrays;
import java.util.Objects;

/** Definition for moveit_msgs/MotionSequenceResponse */
@MessageMetadata(
        name = MotionSequenceResponseMessage.NAME,
        md5sum = "3cfbd71b06d8199bf604ebb8d6627be1")
public class MotionSequenceResponseMessage implements Message {

    static final String NAME = "moveit_msgs/MotionSequenceResponse";

    /** An error code reflecting what went wrong */
    public MoveItErrorCodesMessage error_code = new MoveItErrorCodesMessage();

    /** The full starting state of the robot at the start of the sequence */
    public RobotStateMessage sequence_start = new RobotStateMessage();

    /** The trajectories that the planner produced for execution */
    public RobotTrajectoryMessage[] planned_trajectories = new RobotTrajectoryMessage[0];

    /** The amount of time it took to complete the motion plan */
    public double planning_time;

    public MotionSequenceResponseMessage withErrorCode(MoveItErrorCodesMessage error_code) {
        this.error_code = error_code;
        return this;
    }

    public MotionSequenceResponseMessage withSequenceStart(RobotStateMessage sequence_start) {
        this.sequence_start = sequence_start;
        return this;
    }

    public MotionSequenceResponseMessage withPlannedTrajectories(
            RobotTrajectoryMessage... planned_trajectories) {
        this.planned_trajectories = planned_trajectories;
        return this;
    }

    public MotionSequenceResponseMessage withPlanningTime(double planning_time) {
        this.planning_time = planning_time;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                error_code, sequence_start, Arrays.hashCode(planned_trajectories), planning_time);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (MotionSequenceResponseMessage) obj;
        return Objects.equals(error_code, other.error_code)
                && Objects.equals(sequence_start, other.sequence_start)
                && Arrays.equals(planned_trajectories, other.planned_trajectories)
                && planning_time == other.planning_time;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "error_code", error_code,
                "sequence_start", sequence_start,
                "planned_trajectories", planned_trajectories,
                "planning_time", planning_time);
    }
}
