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

package org.onosproject.yang.gen.v1.ydt.root.rev20160524.logisticsmanager;

import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of materialSupervisor.
 */
public interface MaterialSupervisor {

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
     * Identify the leaf of MaterialSupervisor.
     */
    public enum LeafIdentifier {

        /**
         * Represents name.
         */
        NAME(1),
        /**
         * Represents departmentId.
         */
        DEPARTMENTID(2);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute name.
     *
     * @return value of name
     */
    String name();

    /**
     * Returns the attribute departmentId.
     *
     * @return value of departmentId
     */
    String departmentId();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Returns the attribute yangAugmentedInfo.
     *
     * @param classObject value of Augmentedclass
     * @return value of YangAugmentedInfo
     */
    Object yangAugmentedInfo(Class classObject);

    /**
     * Returns the attribute yangAugmentedInfoMap.
     *
     * @return value of yangAugmentedInfoMap
     */
    Map<Class<?>, Object> yangAugmentedInfoMap();

    /**
     * Checks if the passed MaterialSupervisor maps the content match query condition.
     *
     * @param materialSupervisor materialSupervisor being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    MaterialSupervisor processSubtreeFiltering(MaterialSupervisor materialSupervisor, boolean isSelectAllSchemaChild);

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

    /**
     * Builder for materialSupervisor.
     */
    interface MaterialSupervisorBuilder {

        /**
         * Returns the attribute name.
         *
         * @return value of name
         */
        String name();

        /**
         * Returns the attribute departmentId.
         *
         * @return value of departmentId
         */
        String departmentId();

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the builder object of name.
         *
         * @param name value of name
         * @return builder object of name
         */
        MaterialSupervisorBuilder name(String name);

        /**
         * Returns the builder object of departmentId.
         *
         * @param departmentId value of departmentId
         * @return builder object of departmentId
         */
        MaterialSupervisorBuilder departmentId(String departmentId);

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        MaterialSupervisorBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);


        /**
         * Sets the value of yangAugmentedInfoMap.
         *
         * @param value value of yangAugmentedInfo
         * @param classObject value of Augmentedclass
         */
        void addYangAugmentedInfo(Object value, Class classObject);

        /**
         * Returns the attribute yangAugmentedInfo.
         *
         * @param classObject value of Augmentedclass
         * @return value of YangAugmentedInfo
         */
        Object yangAugmentedInfo(Class classObject);

        /**
         * Returns the attribute yangAugmentedInfoMap.
         *
         * @return value of yangAugmentedInfoMap
         */
        Map<Class<?>, Object> yangAugmentedInfoMap();

        /**
         * Set a leaf to be selected.
         *
         * @param leaf leaf needs to be selected
         * @return builder object for select leaf
         */
        MaterialSupervisorBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of materialSupervisor.
         *
         * @return object of materialSupervisor.
         */
        MaterialSupervisor build();
    }
}
