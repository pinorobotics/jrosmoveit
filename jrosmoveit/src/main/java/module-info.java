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
/**
 * Java module which allows to interact with <a href="https://moveit.ros.org/">MoveIt</a> in ROS
 * (Robot Operating System). It contains only interfaces and classes which are agnostic to ROS
 * version.
 *
 * <p>For usage examples see <a href="http://pinoweb.freetzi.com/jrosmoveit">Documentation</a>
 *
 * @see <a href="https://moveit.ros.org/">MoveIt</a>
 * @see <a href="http://pinoweb.freetzi.com/jrosmoveit">Documentation</a>
 * @see <a href="https://github.com/pinorobotics/jrosmoveit/releases">Download</a>
 * @see <a href="https://github.com/pinorobotics/jrosmoveit">GitHub repository</a>
 * @author aeon_flux aeon_flux@eclipso.ch
 */
module jrosmoveit {

    // since many of our API relies on jrosclient and jrosactionlib
    // classes we need to ensure that all modules reading this module
    // also read them
    requires transitive jrosclient;
    requires transitive jrosactionlib;
    requires id.xfunction;
    requires jrosmessages;

    exports pinorobotics.jrosmoveit;
    exports pinorobotics.jrosmoveit.entities;
    exports pinorobotics.jrosmoveit.moveit_msgs;
    exports pinorobotics.jrosmoveit.exceptions;
    exports pinorobotics.jrosmoveit.impl to
            jros1moveit,
            jros2moveit;
    exports pinorobotics.jrosmoveit.impl.clients.executetrajectory to
            jros1moveit,
            jros2moveit;
    exports pinorobotics.jrosmoveit.impl.clients.movegroup to
            jros1moveit,
            jros2moveit;
    exports pinorobotics.robotstate;
}
