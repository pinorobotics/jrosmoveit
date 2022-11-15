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
package pinorobotics.robotstate;

import id.jrosclient.JRosClient;
import id.jrosclient.TopicSubscriber;
import id.jrosmessages.Message;
import id.xfunction.Preconditions;
import id.xfunction.function.Unchecked;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Function;

/**
 * Subscribes to {@link #JOINT_STATES_TOPIC} and keeps last published robot state.
 *
 * <p>See ROS1 and ROS2 implementations in corresponding <b>jrosmoveit</b> packages.
 *
 * @param <S> message type which describes joint states
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class RobotStateMonitor<S extends Message> implements Closeable {

    private static final String JOINT_STATES_TOPIC = "/joint_states";

    private JRosClient client;
    private CompletableFuture<Void> future = new CompletableFuture<>();
    private volatile RobotState robotState;
    private TopicSubscriber<S> jointStateSubscriber;
    private boolean isStarted;

    protected RobotStateMonitor(
            JRosClient client, Class<S> jointStateType, Function<S, RobotState> transformer) {
        this.client = client;
        this.jointStateSubscriber =
                new TopicSubscriber<S>(jointStateType, JOINT_STATES_TOPIC) {
                    @Override
                    public void onNext(S jointState) {
                        robotState = transformer.apply(jointState);
                        if (!future.isDone()) future.complete(null);
                        getSubscription().get().request(1);
                    }
                };
    }

    public void start() throws Exception {
        client.subscribe(jointStateSubscriber);
        isStarted = true;
    }

    public RobotState getLastRobotState() {
        Preconditions.isTrue(isStarted, "Monitor is not started");
        if (!future.isDone()) {
            Unchecked.get(future::get);
        }
        return robotState;
    }

    @Override
    public void close() throws IOException {
        jointStateSubscriber.getSubscription().ifPresent(Subscription::cancel);
    }
}
