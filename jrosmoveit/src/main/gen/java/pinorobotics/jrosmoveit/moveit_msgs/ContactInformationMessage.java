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

import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.geometry_msgs.Vector3Message;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Objects;

/** Definition for moveit_msgs/ContactInformation */
@MessageMetadata(name = ContactInformationMessage.NAME, md5sum = "116228ca08b0c286ec5ca32a50fdc17b")
public class ContactInformationMessage implements Message {

    static final String NAME = "moveit_msgs/ContactInformation";

    public enum UnknownType {
        ROBOT_LINK,

        WORLD_OBJECT,

        ROBOT_ATTACHED,
    }

    /**
     * Standard ROS header contains information about the frame in which this contact is specified
     */
    public HeaderMessage header = new HeaderMessage();

    /** Position of the contact point */
    public PointMessage position = new PointMessage();

    /** Normal corresponding to the contact point */
    public Vector3Message normal = new Vector3Message();

    /** Depth of contact point */
    public double depth;

    /**
     * Name of the first body that is in contact This could be a link or a namespace that represents
     * a body
     */
    public StringMessage contact_body_1 = new StringMessage();

    public int body_type_1;

    /**
     * Name of the second body that is in contact This could be a link or a namespace that
     * represents a body
     */
    public StringMessage contact_body_2 = new StringMessage();

    public int body_type_2;

    public ContactInformationMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public ContactInformationMessage withPosition(PointMessage position) {
        this.position = position;
        return this;
    }

    public ContactInformationMessage withNormal(Vector3Message normal) {
        this.normal = normal;
        return this;
    }

    public ContactInformationMessage withDepth(double depth) {
        this.depth = depth;
        return this;
    }

    public ContactInformationMessage withContactBody1(StringMessage contact_body_1) {
        this.contact_body_1 = contact_body_1;
        return this;
    }

    public ContactInformationMessage withBodyType1(int body_type_1) {
        this.body_type_1 = body_type_1;
        return this;
    }

    public ContactInformationMessage withContactBody2(StringMessage contact_body_2) {
        this.contact_body_2 = contact_body_2;
        return this;
    }

    public ContactInformationMessage withBodyType2(int body_type_2) {
        this.body_type_2 = body_type_2;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                header,
                position,
                normal,
                depth,
                contact_body_1,
                body_type_1,
                contact_body_2,
                body_type_2);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (ContactInformationMessage) obj;
        return Objects.equals(header, other.header)
                && Objects.equals(position, other.position)
                && Objects.equals(normal, other.normal)
                && depth == other.depth
                && Objects.equals(contact_body_1, other.contact_body_1)
                && body_type_1 == other.body_type_1
                && Objects.equals(contact_body_2, other.contact_body_2)
                && body_type_2 == other.body_type_2;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "position", position,
                "normal", normal,
                "depth", depth,
                "contact_body_1", contact_body_1,
                "body_type_1", body_type_1,
                "contact_body_2", contact_body_2,
                "body_type_2", body_type_2);
    }
}
