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
 * Generated for ROS msg file: moveit_msgs/ObjectColor
 */

package pinorobotics.jrosmoveit.moveit_msgs;

import java.util.Objects;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.kineticstreamer.annotations.Streamed;
import id.xfunction.XJson;

import id.jrosmessages.std_msgs.ColorRGBAMessage;
import id.jrosmessages.std_msgs.StringMessage;

/**
 * Definition for moveit_msgs/ObjectColor
 */
@MessageMetadata(
    type = ObjectColorMessage.NAME,
    md5sum = "ec3bd6f103430e64b2ae706a67d8488e")
public class ObjectColorMessage implements Message {
   
   static final String NAME = "moveit_msgs/ObjectColor";

   /**
    * The object id for which we specify color
    */
   @Streamed
   public StringMessage id = new StringMessage();
   
   /**
    * The value of the color
    */
   @Streamed
   public ColorRGBAMessage color = new ColorRGBAMessage();
   
   public ObjectColorMessage withId(StringMessage id) {
       this.id = id;
       return this;
   }
   
   public ObjectColorMessage withColor(ColorRGBAMessage color) {
       this.color = color;
       return this;
   }
   
   @Override
   public int hashCode() {
       return Objects.hash(
           id,
           color
       );
   }
   
   @Override
   public boolean equals(Object obj) {
       var other = (ObjectColorMessage) obj;
       return
           Objects.equals(id, other.id) &&
           Objects.equals(color, other.color)
       ;
   }
   
   @Override
   public String toString() {
       return XJson.asString(
           "id", id,
           "color", color
       );
   }
   
}