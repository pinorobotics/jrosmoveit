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

/**
 * Definition for moveit_msgs/MotionPlanDetailedResponse The representation of a solution to a
 * planning problem, including intermediate data
 */
@MessageMetadata(
        name = MotionPlanDetailedResponseMessage.NAME,
        md5sum = "24ca49329688ccb45f3f1e82de532220")
public class MotionPlanDetailedResponseMessage implements Message {

    static final String NAME = "moveit_msgs/MotionPlanDetailedResponse";

    /** The starting state considered for the robot solution path */
    public RobotStateMessage trajectory_start = new RobotStateMessage();

    /** The group used for planning (usually the same as in the request) */
    public StringMessage group_name = new StringMessage();

    /**
     * Multiple solution paths are reported, each reflecting intermediate steps in the trajectory
     * processing The list of reported trajectories
     */
    public RobotTrajectoryMessage[] trajectory = new RobotTrajectoryMessage[0];

    /** Description of the reported trajectories (name of processing step) */
    public StringMessage[] description = new StringMessage[0];

    /** The amount of time spent computing a particular step in motion plan computation */
    public double[] processing_time = new double[0];

    /** Status at the end of this plan */
    public MoveItErrorCodesMessage error_code = new MoveItErrorCodesMessage();

    public MotionPlanDetailedResponseMessage withTrajectoryStart(
            RobotStateMessage trajectory_start) {
        this.trajectory_start = trajectory_start;
        return this;
    }

    public MotionPlanDetailedResponseMessage withGroupName(StringMessage group_name) {
        this.group_name = group_name;
        return this;
    }

    public MotionPlanDetailedResponseMessage withTrajectory(RobotTrajectoryMessage... trajectory) {
        this.trajectory = trajectory;
        return this;
    }

    public MotionPlanDetailedResponseMessage withDescription(StringMessage... description) {
        this.description = description;
        return this;
    }

    public MotionPlanDetailedResponseMessage withProcessingTime(double... processing_time) {
        this.processing_time = processing_time;
        return this;
    }

    public MotionPlanDetailedResponseMessage withErrorCode(MoveItErrorCodesMessage error_code) {
        this.error_code = error_code;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                trajectory_start,
                group_name,
                Arrays.hashCode(trajectory),
                Arrays.hashCode(description),
                Arrays.hashCode(processing_time),
                error_code);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (MotionPlanDetailedResponseMessage) obj;
        return Objects.equals(trajectory_start, other.trajectory_start)
                && Objects.equals(group_name, other.group_name)
                && Arrays.equals(trajectory, other.trajectory)
                && Arrays.equals(description, other.description)
                && Arrays.equals(processing_time, other.processing_time)
                && Objects.equals(error_code, other.error_code);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "trajectory_start", trajectory_start,
                "group_name", group_name,
                "trajectory", trajectory,
                "description", description,
                "processing_time", processing_time,
                "error_code", error_code);
    }
}
