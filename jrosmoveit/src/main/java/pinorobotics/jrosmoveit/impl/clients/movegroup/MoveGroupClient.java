/*
 * Copyright 2022 jrosmoveit project
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
package pinorobotics.jrosmoveit.impl.clients.movegroup;

import pinorobotics.jrosmoveit.entities.Plan;
import pinorobotics.jrosmoveit.exceptions.JRosMoveItException;
import pinorobotics.jrosmoveit.impl.MotionRequest;

/**
 * @author lambdaprime intid@protonmail.com
 */
public interface MoveGroupClient<P extends Plan> extends AutoCloseable {
    P plan(MotionRequest planRequest) throws JRosMoveItException;

    void move(MotionRequest planRequest) throws JRosMoveItException;
}
