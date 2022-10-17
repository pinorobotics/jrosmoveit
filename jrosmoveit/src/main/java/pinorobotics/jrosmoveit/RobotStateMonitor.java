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
package pinorobotics.jrosmoveit;

import id.jros1messages.sensor_msgs.JointStateMessage;
import id.jrosclient.JRosClient;
import id.jrosclient.TopicSubscriber;
import id.xfunction.Preconditions;
import id.xfunction.function.Unchecked;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow.Subscription;
import pinorobotics.jrosmoveit.entities.RobotState;

/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class RobotStateMonitor implements Closeable {

    private JRosClient client;
    private CompletableFuture<RobotState> robotState = new CompletableFuture<>();
    private TopicSubscriber<JointStateMessage> jointStateSubscriber;
    private boolean isStarted;

    public RobotStateMonitor(JRosClient client) {
        this.client = client;
        this.jointStateSubscriber =
                new TopicSubscriber<JointStateMessage>(JointStateMessage.class, "/joint_states") {
                    @Override
                    public void onNext(JointStateMessage jointState) {
                        var myState =
                                robotState.isDone() ? getCurrentRobotState() : new RobotState();
                        myState.setJointPositions(jointState.position);
                        if (!robotState.isDone()) {
                            robotState.complete(myState);
                        }
                    }
                };
    }

    public void start() throws Exception {
        client.subscribe(jointStateSubscriber);
        isStarted = true;
    }

    public RobotState getCurrentRobotState() {
        Preconditions.isTrue(isStarted, "Monitor is not started");
        return Unchecked.get(robotState::get);
    }

    @Override
    public void close() throws IOException {
        jointStateSubscriber.getSubscription().ifPresent(Subscription::cancel);
    }
}
