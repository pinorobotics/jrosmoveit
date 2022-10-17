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

/** Definition for moveit_msgs/DisplayRobotState */
@MessageMetadata(name = DisplayRobotStateMessage.NAME, md5sum = "61c4e677a6fbbc83f0d5d9df2be85e3c")
public class DisplayRobotStateMessage implements Message {

    static final String NAME = "moveit_msgs/DisplayRobotState";

    /** The robot state to display */
    public RobotStateMessage state = new RobotStateMessage();

    /** Optionally, various links can be highlighted */
    public ObjectColorMessage[] highlight_links = new ObjectColorMessage[0];

    /** If true, suppress the display in visualizations (like rviz) */
    public boolean hide;

    public DisplayRobotStateMessage withState(RobotStateMessage state) {
        this.state = state;
        return this;
    }

    public DisplayRobotStateMessage withHighlightLinks(ObjectColorMessage... highlight_links) {
        this.highlight_links = highlight_links;
        return this;
    }

    public DisplayRobotStateMessage withHide(boolean hide) {
        this.hide = hide;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, Arrays.hashCode(highlight_links), hide);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (DisplayRobotStateMessage) obj;
        return Objects.equals(state, other.state)
                && Arrays.equals(highlight_links, other.highlight_links)
                && hide == other.hide;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "state", state,
                "highlight_links", highlight_links,
                "hide", hide);
    }
}
