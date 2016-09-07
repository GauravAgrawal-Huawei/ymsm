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

package org.onosproject.yang.gen.v1.ych.purchasing.supervisor.rev20160524.ychpurchasingsupervisor;

import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of ychPurchasingSupervisor.
 */
public interface YchPurchasingSupervisor {

    /**
     * Identify the leaf of YchPurchasingSupervisor.
     */
    public enum LeafIdentifier {

        /**
         * Represents ychPurchasingSpecialist.
         */
        YCHPURCHASINGSPECIALIST(1),
        /**
         * Represents ychPurchasingSupport.
         */
        YCHPURCHASINGSUPPORT(2);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute ychPurchasingSpecialist.
     *
     * @return value of ychPurchasingSpecialist
     */
    String ychPurchasingSpecialist();

    /**
     * Returns the attribute ychPurchasingSupport.
     *
     * @return value of ychPurchasingSupport
     */
    String ychPurchasingSupport();

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
     * Checks if the passed YchPurchasingSupervisor maps the content match query condition.
     *
     * @param ychPurchasingSupervisor ychPurchasingSupervisor being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    YchPurchasingSupervisor processSubtreeFiltering(YchPurchasingSupervisor ychPurchasingSupervisor, boolean
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
     * Builder for ychPurchasingSupervisor.
     */
    interface YchPurchasingSupervisorBuilder {

        /**
         * Returns the attribute ychPurchasingSpecialist.
         *
         * @return value of ychPurchasingSpecialist
         */
        String ychPurchasingSpecialist();

        /**
         * Returns the attribute ychPurchasingSupport.
         *
         * @return value of ychPurchasingSupport
         */
        String ychPurchasingSupport();

        /**
         * Returns the builder object of ychPurchasingSpecialist.
         *
         * @param ychPurchasingSpecialist value of ychPurchasingSpecialist
         * @return builder object of ychPurchasingSpecialist
         */
        YchPurchasingSupervisorBuilder ychPurchasingSpecialist(String ychPurchasingSpecialist);

        /**
         * Returns the builder object of ychPurchasingSupport.
         *
         * @param ychPurchasingSupport value of ychPurchasingSupport
         * @return builder object of ychPurchasingSupport
         */
        YchPurchasingSupervisorBuilder ychPurchasingSupport(String ychPurchasingSupport);


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
        YchPurchasingSupervisorBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of ychPurchasingSupervisor.
         *
         * @return object of ychPurchasingSupervisor.
         */
        YchPurchasingSupervisor build();
    }
}
