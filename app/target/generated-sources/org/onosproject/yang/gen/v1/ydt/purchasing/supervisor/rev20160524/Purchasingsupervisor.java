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

package org.onosproject.yang.gen.v1.ydt.purchasing.supervisor.rev20160524;

import org.onosproject.yang.gen.v1.ydt.purchasing.supervisor.rev20160524.purchasingsupervisor.Supervisor;

/**
 * Abstraction of an entity which represents the functionality of purchasingsupervisor.
 */
public interface Purchasingsupervisor {

    /**
     * Specify the node specific operation in protocols like NETCONF.
     * Applicable in protocol edit operation, not applicable in query operation
     */
    public enum OnosYangNodeOperationType {
        MERGE,
        REPLACE,
        CREATE,
        DELETE,
        REMOVE,
        NONE
    }

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Returns the attribute supervisor.
     *
     * @return value of supervisor
     */
    Supervisor supervisor();

    /**
     * Checks if the passed Purchasingsupervisor maps the content match query condition.
     *
     * @param purchasingsupervisor purchasingsupervisor being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Purchasingsupervisor processSubtreeFiltering(Purchasingsupervisor purchasingsupervisor, boolean
            isSelectAllSchemaChild);
}
