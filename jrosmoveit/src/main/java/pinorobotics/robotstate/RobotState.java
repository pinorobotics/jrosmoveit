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

import id.xfunction.XJson;
import java.util.Arrays;

/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class RobotState implements Cloneable {

    private RobotModel model;
    private double[] positions;

    public void setJointPositions(double[] positions) {
        this.positions = positions;
    }

    public RobotModel getModel() {
        return model;
    }

    @Override
    public String toString() {
        return XJson.asString("positions", positions);
    }

    @Override
    public RobotState clone() throws CloneNotSupportedException {
        var tmp = new RobotState();
        tmp.setJointPositions(Arrays.copyOf(positions, positions.length));
        return tmp;
    }
}
