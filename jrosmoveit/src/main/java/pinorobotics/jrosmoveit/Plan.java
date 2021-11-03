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
/*
 * Authors:
 * - aeon_flux <aeon_flux@eclipso.ch>
 */
package pinorobotics.jrosmoveit;

import id.xfunction.XJson;
import pinorobotics.jrosmoveit.moveit_msgs.RobotStateMessage;
import pinorobotics.jrosmoveit.moveit_msgs.RobotTrajectoryMessage;

public class Plan {

    private RobotStateMessage trajectoryStart;
    private RobotTrajectoryMessage plannedTrajectory;
    private double planningTime;

    public Plan withTrajectory(RobotTrajectoryMessage plannedTrajectory) {
        this.plannedTrajectory = plannedTrajectory;
        return this;
    }

    public Plan withStartState(RobotStateMessage trajectoryStart) {
        this.trajectoryStart = trajectoryStart;
        return this;
    }

    public Plan withPlanningTime(double planningTime) {
        this.planningTime = planningTime;
        return this;
    }

    public RobotTrajectoryMessage getPlannedTrajectory() {
        return plannedTrajectory;
    }

    public RobotStateMessage getTrajectoryStart() {
        return trajectoryStart;
    }

    public double getPlanningTime() {
        return planningTime;
    }

    @Override
    public String toString() {
        return XJson.asString(
            "trajectoryStart", trajectoryStart,
            "plannedTrajectory", plannedTrajectory
        );
    }
}