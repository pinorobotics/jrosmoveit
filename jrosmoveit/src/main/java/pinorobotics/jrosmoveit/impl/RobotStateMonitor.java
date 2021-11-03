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
package pinorobotics.jrosmoveit.impl;

import java.io.Closeable;
import java.io.IOException;

import id.jrosclient.JRosClient;
import id.jrosclient.TopicSubscriber;
import id.jrosmessages.sensor_msgs.JointStateMessage;

public class RobotStateMonitor implements Closeable {

    private String planningGroup;
    private JRosClient client;
    private volatile JointStateMessage jointState;
    
    public RobotStateMonitor(JRosClient client, String planningGroup) {
        this.client = client;
        this.planningGroup = planningGroup;
    }

    public void start() throws Exception {
        client.subscribe(new TopicSubscriber<JointStateMessage>(JointStateMessage.class, null) {
            @Override
            public void onNext(JointStateMessage state) {
                jointState = state;
            }
        });
    }
    
    public JointStateMessage getJointState() {
        return jointState;
    }

    @Override
    public void close() throws IOException {
        // TODO Auto-generated method stub
        
    }
}
