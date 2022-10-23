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
package pinorobotics.jrosmoveit.tests;

import id.jros1client.JRos1Client;
import id.jros1client.JRos1ClientFactory;
import id.jrosmessages.JRosMessagesTransformer;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.geometry_msgs.QuaternionMessage;
import id.xfunction.ResourceUtils;
import id.xfunction.XJson;
import id.xfunction.logging.XLogger;
import java.net.MalformedURLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pinorobotics.jros1rviztools.JRos1RvizTools;
import pinorobotics.jros1rviztools.JRos1RvizToolsFactory;
import pinorobotics.jrosmoveit.JRosMoveIt;
import pinorobotics.jrosmoveit.RobotStateMonitor;
import pinorobotics.jrosmoveit.entities.RobotModel;
import pinorobotics.jrosrviztools.entities.Color;
import pinorobotics.jrosrviztools.entities.JRosRvizEntitiesTransformer;
import pinorobotics.jrosrviztools.entities.MarkerType;
import pinorobotics.jrosrviztools.entities.Point;
import pinorobotics.jrosrviztools.entities.Scales;
import pinorobotics.jrostf2.JRosTf2;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JRosMoveItIntegrationTests {

    private static final ResourceUtils resourceUtils = new ResourceUtils();
    private static final String BASE_FRAME = "world";
    private static JRos1Client client;
    private JRosMoveIt moveIt;
    private JRos1RvizTools rvizTools;
    private JRosTf2 tf2;
    private JRosRvizEntitiesTransformer transformer = new JRosRvizEntitiesTransformer();

    @BeforeAll
    public static void setupAll() {
        XLogger.load("jrosmoveit-test.properties");
        XJson.setLimitDecimalPlaces(2);
        XJson.setNegativeZero(false);
    }

    @BeforeEach
    public void setup() throws MalformedURLException {
        client = new JRos1ClientFactory().createClient("http://localhost:11311/");
        rvizTools =
                new JRos1RvizToolsFactory()
                        .createRvizTools(client, BASE_FRAME, "/visualization_marker_array");
        moveIt = new JRosMoveIt(client, "panda_arm", new RobotModel(BASE_FRAME));
        tf2 = new JRosTf2(client);
    }

    @AfterEach
    public void clean() throws Exception {
        moveIt.close();
        rvizTools.close();
        tf2.close();
        client.close();
    }

    @Test
    @Order(1)
    public void test_robot_state_monitor() throws Exception {
        try (var monitor = new RobotStateMonitor(client)) {
            monitor.start();
            var state = monitor.getCurrentRobotState();
            Assertions.assertEquals(resourceUtils.readResource("robot-state"), state.toString());
        }
    }

    @Test
    @Order(2)
    public void test_plan() throws Exception {
        var points = new Point[] {new Point(0.28, 0.2, 0.5), new Point(0.28, -0.2, 0.5)};
        var startTransform = tf2.lookupTransform("world", "panda_hand").transform.transform;
        rvizTools.publishMarkers(Color.RED, Scales.XLARGE, MarkerType.SPHERE, points);
        try (var monitor = new RobotStateMonitor(client)) {
            monitor.start();
            for (int i = 0; i < points.length; i++) {
                var targetPose = new PoseMessage();
                targetPose.position = transformer.toPointMessage(points[i]);
                targetPose.orientation = new QuaternionMessage().withW(-1.0);
                moveIt.setPoseTarget(targetPose, "panda_hand");

                var plan = moveIt.plan();
                System.out.println(plan);
                var jointState = plan.getTrajectoryStart().joint_state;
                Assertions.assertEquals(9, jointState.name.length);
                Assertions.assertEquals("panda_joint4", jointState.name[3]);
                Assertions.assertEquals(9, jointState.position.length);
                var jointTrajectoryMessage = plan.getPlannedTrajectory().joint_trajectory;
                Assertions.assertEquals(7, jointTrajectoryMessage.joint_names.length);
                Assertions.assertTrue(jointTrajectoryMessage.points.length > 5);

                var state = monitor.getCurrentRobotState().clone();
                System.out.println("Current state: " + state);

                moveIt.execute(plan);

                var newState = monitor.getCurrentRobotState().clone();
                System.out.println("New state: " + newState);
                Assertions.assertNotEquals(state, newState);
            }
        }
        moveIt.setPoseTarget(
                new JRosMessagesTransformer().asPoseMessage(startTransform), "panda_hand");
        moveIt.move();
        var endTransform = tf2.lookupTransform("world", "panda_hand").transform.transform;
        Assertions.assertEquals(startTransform.toString(), endTransform.toString());
    }

    @Test
    @Order(3)
    public void test_documentation_example() throws Exception {
        try (var client = new JRos1ClientFactory().createClient("http://localhost:11311/");
                var moveIt = new JRosMoveIt(client, "panda_arm", new RobotModel("world"))) {
            var targetPose = new PoseMessage();
            targetPose.position = transformer.toPointMessage(new Point(0.28, -0.2, 0.5));
            targetPose.orientation = new QuaternionMessage().withW(-1.0);
            moveIt.setPoseTarget(targetPose, "panda_hand");
            var plan = moveIt.plan();
            moveIt.execute(plan);
        }
    }
}
