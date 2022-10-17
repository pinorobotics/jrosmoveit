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
 * Definition for moveit_msgs/ConstraintEvalResult This message contains result from constraint
 * evaluation # result specifies the result of constraint evaluation # (true indicates state
 * satisfies constraint, false indicates state violates constraint) # if false, distance specifies a
 * measure of the distance of the state from the constraint # if true, distance is set to zero
 */
@MessageMetadata(
        name = ConstraintEvalResultMessage.NAME,
        md5sum = "093643083d24f6488cb5a868bd47c090")
public class ConstraintEvalResultMessage implements Message {

    static final String NAME = "moveit_msgs/ConstraintEvalResult";

    public boolean result;

    public double distance;

    public ConstraintEvalResultMessage withResult(boolean result) {
        this.result = result;
        return this;
    }

    public ConstraintEvalResultMessage withDistance(double distance) {
        this.distance = distance;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, distance);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (ConstraintEvalResultMessage) obj;
        return result == other.result && distance == other.distance;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "result", result,
                "distance", distance);
    }
}
