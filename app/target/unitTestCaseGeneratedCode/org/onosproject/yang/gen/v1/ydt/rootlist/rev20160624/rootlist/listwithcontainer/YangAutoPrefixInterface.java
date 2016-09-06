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

package org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.rootlist.listwithcontainer;

import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of yangAutoPrefixInterface.
 */
public interface YangAutoPrefixInterface {

    /**
     * Identify the leaf of YangAutoPrefixInterface.
     */
    public enum LeafIdentifier {

        /**
         * Represents invalidinterval.
         */
        INVALIDINTERVAL(1),
        /**
         * Represents invalid.
         */
        INVALID(2);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute invalidinterval.
     *
     * @return value of invalidinterval
     */
    int invalidinterval();

    /**
     * Returns the attribute invalid.
     *
     * @return value of invalid
     */
    int invalid();

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
     * Checks if the passed YangAutoPrefixInterface maps the content match query condition.
     *
     * @param yangAutoPrefixInterface yangAutoPrefixInterface being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    YangAutoPrefixInterface processSubtreeFiltering(YangAutoPrefixInterface yangAutoPrefixInterface, boolean
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
     * Builder for yangAutoPrefixInterface.
     */
    interface YangAutoPrefixInterfaceBuilder {

        /**
         * Returns the attribute invalidinterval.
         *
         * @return value of invalidinterval
         */
        int invalidinterval();

        /**
         * Returns the attribute invalid.
         *
         * @return value of invalid
         */
        int invalid();

        /**
         * Returns the builder object of invalidinterval.
         *
         * @param invalidinterval value of invalidinterval
         * @return builder object of invalidinterval
         */
        YangAutoPrefixInterfaceBuilder invalidinterval(int invalidinterval);

        /**
         * Returns the builder object of invalid.
         *
         * @param invalid value of invalid
         * @return builder object of invalid
         */
        YangAutoPrefixInterfaceBuilder invalid(int invalid);


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
        YangAutoPrefixInterfaceBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of yangAutoPrefixInterface.
         *
         * @return object of yangAutoPrefixInterface.
         */
        YangAutoPrefixInterface build();
    }
}