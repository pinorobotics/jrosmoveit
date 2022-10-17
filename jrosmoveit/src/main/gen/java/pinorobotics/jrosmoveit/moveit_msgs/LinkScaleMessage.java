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

/** Definition for moveit_msgs/LinkScale */
@MessageMetadata(name = LinkScaleMessage.NAME, md5sum = "19faf226446bfb2f645a4da6f2a56166")
public class LinkScaleMessage implements Message {

    static final String NAME = "moveit_msgs/LinkScale";

    /** name for the link */
    public StringMessage link_name = new StringMessage();

    /** scaling to apply to the link */
    public double scale;

    public LinkScaleMessage withLinkName(StringMessage link_name) {
        this.link_name = link_name;
        return this;
    }

    public LinkScaleMessage withScale(double scale) {
        this.scale = scale;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(link_name, scale);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (LinkScaleMessage) obj;
        return Objects.equals(link_name, other.link_name) && scale == other.scale;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "link_name", link_name,
                "scale", scale);
    }
}
