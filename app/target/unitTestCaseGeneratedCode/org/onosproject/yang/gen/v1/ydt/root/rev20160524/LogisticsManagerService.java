/*
 * Copyright 2016-present Open Networking Laboratory
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

package org.onosproject.yang.gen.v1.ydt.root.rev20160524;

/**
 * Abstraction of an entity which represents the functionality of logisticsManagerService.
 */
public interface LogisticsManagerService {

    /**
     * Returns the attribute LogisticsManager.
     *
     * @param logisticsManager value of LogisticsManager
     * @return value of LogisticsManager
     */
    LogisticsManager getLogisticsManager(LogisticsManagerOpParam logisticsManager);

    /**
     * Sets the value to attribute logisticsManager.
     *
     * @param logisticsManager value of logisticsManager
     */
    void setLogisticsManager(LogisticsManagerOpParam logisticsManager);

}
