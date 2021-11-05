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

package pinorobotics.jrosmoveit.tests;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.jrosclient.JRosClient;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.visualization_msgs.MarkerMessage;
import pinorobotics.jrosmoveit.JRosMoveIt;
import pinorobotics.jrosmoveit.RobotModel;
import pinorobotics.jrosrviztools.Colors;
import pinorobotics.jrosrviztools.JRosRvizTools;
import pinorobotics.jrosrviztools.Scales;

public class JRosMoveItIntegrationTests {

    private static final String BASE_FRAME = "world";
    private static JRosClient client;
    private JRosMoveIt moveIt;
    private JRosRvizTools rvizTools;

    @BeforeEach
    public void setup() throws MalformedURLException {
        client = new JRosClient("http://localhost:11311/");
        rvizTools = new JRosRvizTools(client, BASE_FRAME);
        moveIt = new JRosMoveIt(client, "panda_arm", new RobotModel(BASE_FRAME));
    }

    @AfterEach
    public void clean() throws Exception {
        moveIt.close();
        rvizTools.close();
        client.close();
    }

    @Test
    public void test_plan() throws Exception {
        var points = new PointMessage[] {
                new PointMessage(0.28, 0.2, 0.5),
                new PointMessage(0.28, -0.2, 0.5)};
        rvizTools.publishMarkers(Colors.RED, Scales.XLARGE, MarkerMessage.Type.SPHERE, points);
        
        for (var point: points) {
            var targetPose = new PoseMessage();
            targetPose.position = point;
            moveIt.setPoseTarget(targetPose, "panda_hand");
    
            var plan = moveIt.plan();
            System.out.println(plan);
            var jointState = plan.getTrajectoryStart().joint_state;
            Assertions.assertEquals(9, jointState.name.length);
            Assertions.assertEquals("panda_joint4", jointState.name[3]);
            Assertions.assertEquals(9, jointState.position.length);
            Assertions.assertEquals(-2.356, jointState.position[3]);
            var jointTrajectoryMessage = plan.getPlannedTrajectory().joint_trajectory;
            Assertions.assertEquals(7, jointTrajectoryMessage.joint_names.length);
            Assertions.assertTrue(jointTrajectoryMessage.points.length > 10);
        }
    }
}
