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
 * Abstraction of an entity which represents the functionality of purchasingSupervisor.
 */
public interface PurchasingSupervisor {

    /**
     * Identify the leaf of PurchasingSupervisor.
     */
    public enum LeafIdentifier {

        /**
         * Represents purchasingSpecialist.
         */
        PURCHASINGSPECIALIST(1),
        /**
         * Represents support.
         */
        SUPPORT(2);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute purchasingSpecialist.
     *
     * @return value of purchasingSpecialist
     */
    String purchasingSpecialist();

    /**
     * Returns the attribute support.
     *
     * @return value of support
     */
    String support();

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
     * Checks if the passed PurchasingSupervisor maps the content match query condition.
     *
     * @param purchasingSupervisor purchasingSupervisor being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    PurchasingSupervisor processSubtreeFiltering(PurchasingSupervisor purchasingSupervisor, boolean
            isSelectAllSchemaChild);

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
     * Builder for purchasingSupervisor.
     */
    interface PurchasingSupervisorBuilder {

        /**
         * Returns the attribute purchasingSpecialist.
         *
         * @return value of purchasingSpecialist
         */
        String purchasingSpecialist();

        /**
         * Returns the attribute support.
         *
         * @return value of support
         */
        String support();

        /**
         * Returns the builder object of purchasingSpecialist.
         *
         * @param purchasingSpecialist value of purchasingSpecialist
         * @return builder object of purchasingSpecialist
         */
        PurchasingSupervisorBuilder purchasingSpecialist(String purchasingSpecialist);

        /**
         * Returns the builder object of support.
         *
         * @param support value of support
         * @return builder object of support
         */
        PurchasingSupervisorBuilder support(String support);


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
        PurchasingSupervisorBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of purchasingSupervisor.
         *
         * @return object of purchasingSupervisor.
         */
        PurchasingSupervisor build();
    }
}
