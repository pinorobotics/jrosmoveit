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
import id.jrosmessages.std_msgs.ColorRGBAMessage;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Objects;

/** Definition for moveit_msgs/ObjectColor */
@MessageMetadata(
        name = ObjectColorMessage.NAME,
        fields = {"id", "color"},
        md5sum = "ec3bd6f103430e64b2ae706a67d8488e")
public class ObjectColorMessage implements Message {

    static final String NAME = "moveit_msgs/ObjectColor";

    /** The object id for which we specify color */
    public StringMessage id = new StringMessage();

    /** The value of the color */
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
        return Objects.hash(id, color);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (ObjectColorMessage) obj;
        return Objects.equals(id, other.id) && Objects.equals(color, other.color);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "id", id,
                "color", color);
    }
}
