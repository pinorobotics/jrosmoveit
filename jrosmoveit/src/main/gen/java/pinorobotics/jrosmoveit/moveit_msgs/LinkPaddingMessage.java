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

/** Definition for moveit_msgs/LinkPadding */
@MessageMetadata(
        name = LinkPaddingMessage.NAME,
        fields = {"link_name", "padding"},
        md5sum = "b3ea75670df55c696fedee97774d5947")
public class LinkPaddingMessage implements Message {

    static final String NAME = "moveit_msgs/LinkPadding";

    /** name for the link */
    public StringMessage link_name = new StringMessage();

    /** padding to apply to the link */
    public double padding;

    public LinkPaddingMessage withLinkName(StringMessage link_name) {
        this.link_name = link_name;
        return this;
    }

    public LinkPaddingMessage withPadding(double padding) {
        this.padding = padding;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(link_name, padding);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (LinkPaddingMessage) obj;
        return Objects.equals(link_name, other.link_name) && padding == other.padding;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "link_name", link_name,
                "padding", padding);
    }
}
