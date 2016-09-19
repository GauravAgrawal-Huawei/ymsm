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

import java.util.List;
import org.onosproject.yang.gen.v1.ydt.root.rev20160524.logisticsmanager.MaterialSupervisor;
import org.onosproject.yang.gen.v1.ydt.root.rev20160524.logisticsmanager.PurchasingSupervisor;

/**
 * Abstraction of an entity which represents the functionality of logisticsManager.
 */
public interface LogisticsManager {

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
     * Identify the leaf of LogisticsManager.
     */
    public enum LeafIdentifier {

        /**
         * Represents customsSupervisor.
         */
        CUSTOMSSUPERVISOR(1),
        /**
         * Represents merchandiserSupervisor.
         */
        MERCHANDISERSUPERVISOR(2),
        /**
         * Represents tradingSupervisor.
         */
        TRADINGSUPERVISOR(3);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute customsSupervisor.
     *
     * @return value of customsSupervisor
     */
    String customsSupervisor();

    /**
     * Returns the attribute merchandiserSupervisor.
     *
     * @return value of merchandiserSupervisor
     */
    String merchandiserSupervisor();

    /**
     * Returns the attribute tradingSupervisor.
     *
     * @return value of tradingSupervisor
     */
    String tradingSupervisor();

    /**
     * Returns the attribute warehouseSupervisor.
     *
     * @return list of warehouseSupervisor
     */
    List<String> warehouseSupervisor();

    /**
     * Returns the attribute employeeId.
     *
     * @return list of employeeId
     */
    List<String> employeeId();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Returns the attribute materialSupervisor.
     *
     * @return list of materialSupervisor
     */
    List<MaterialSupervisor> materialSupervisor();

    /**
     * Returns the attribute purchasingSupervisor.
     *
     * @return value of purchasingSupervisor
     */
    PurchasingSupervisor purchasingSupervisor();

    /**
     * Checks if the passed LogisticsManager maps the content match query condition.
     *
     * @param logisticsManager logisticsManager being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    LogisticsManager processSubtreeFiltering(LogisticsManager logisticsManager, boolean isSelectAllSchemaChild);

    /**
     * Checks if the leaf value is set.
     *
     * @param leaf leaf whose value status needs to checked
     * @return result of leaf value set in object
     */
    boolean isLeafValueSet(LeafIdentifier leaf);

    /**
     * Checks if the leaf is set to be a selected leaf.
     *
     * @param leaf if leaf needs to be selected
     * @return result of leaf value set in object
     */
    boolean isSelectLeaf(LeafIdentifier leaf);
}
