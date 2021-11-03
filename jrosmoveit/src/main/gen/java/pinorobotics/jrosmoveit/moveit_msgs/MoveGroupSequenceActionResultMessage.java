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
 * Generated for ROS msg file: moveit_msgs/MoveGroupSequenceActionResult
 */

package pinorobotics.jrosmoveit.moveit_msgs;

import java.util.Objects;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.HeaderMessage;
import id.kineticstreamer.annotations.Streamed;
import id.xfunction.XJson;
import pinorobotics.jrosactionlib.actionlib_msgs.GoalStatusMessage;

/**
 * Definition for moveit_msgs/MoveGroupSequenceActionResult
 * ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION DEFINITION ======
 */
@MessageMetadata(
    type = MoveGroupSequenceActionResultMessage.NAME,
    md5sum = "442e77fc4256b945798e27b22ccfd900")
public class MoveGroupSequenceActionResultMessage implements Message {
   
   static final String NAME = "moveit_msgs/MoveGroupSequenceActionResult";

   @Streamed
   public HeaderMessage header = new HeaderMessage();
   
   @Streamed
   public GoalStatusMessage status = new GoalStatusMessage();
   
   @Streamed
   public MoveGroupSequenceResultMessage result = new MoveGroupSequenceResultMessage();
   
   public MoveGroupSequenceActionResultMessage withHeader(HeaderMessage header) {
       this.header = header;
       return this;
   }
   
   public MoveGroupSequenceActionResultMessage withStatus(GoalStatusMessage status) {
       this.status = status;
       return this;
   }
   
   public MoveGroupSequenceActionResultMessage withResult(MoveGroupSequenceResultMessage result) {
       this.result = result;
       return this;
   }
   
   @Override
   public int hashCode() {
       return Objects.hash(
           header,
           status,
           result
       );
   }
   
   @Override
   public boolean equals(Object obj) {
       var other = (MoveGroupSequenceActionResultMessage) obj;
       return
           Objects.equals(header, other.header) &&
           Objects.equals(status, other.status) &&
           Objects.equals(result, other.result)
       ;
   }
   
   @Override
   public String toString() {
       return XJson.asString(
           "header", header,
           "status", status,
           "result", result
       );
   }
   
}
