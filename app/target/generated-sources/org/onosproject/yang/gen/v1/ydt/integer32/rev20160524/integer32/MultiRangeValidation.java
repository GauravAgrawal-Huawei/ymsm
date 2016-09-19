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

package org.onosproject.yang.gen.v1.ydt.integer32.rev20160524.integer32;

import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of multiRangeValidation.
 */
public interface MultiRangeValidation {

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
     * Identify the leaf of MultiRangeValidation.
     */
    public enum LeafIdentifier {

        /**
         * Represents integer.
         */
        INTEGER(1),
        /**
         * Represents unInteger.
         */
        UNINTEGER(2),
        /**
         * Represents revInteger.
         */
        REVINTEGER(3),
        /**
         * Represents revUnInteger.
         */
        REVUNINTEGER(4);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute integer.
     *
     * @return value of integer
     */
    int integer();

    /**
     * Returns the attribute unInteger.
     *
     * @return value of unInteger
     */
    long unInteger();

    /**
     * Returns the attribute revInteger.
     *
     * @return value of revInteger
     */
    int revInteger();

    /**
     * Returns the attribute revUnInteger.
     *
     * @return value of revUnInteger
     */
    long revUnInteger();

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
     * Checks if the passed MultiRangeValidation maps the content match query condition.
     *
     * @param multiRangeValidation multiRangeValidation being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    MultiRangeValidation processSubtreeFiltering(MultiRangeValidation multiRangeValidation, boolean
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
     * Builder for multiRangeValidation.
     */
    interface MultiRangeValidationBuilder {

        /**
         * Returns the attribute integer.
         *
         * @return value of integer
         */
        int integer();

        /**
         * Returns the attribute unInteger.
         *
         * @return value of unInteger
         */
        long unInteger();

        /**
         * Returns the attribute revInteger.
         *
         * @return value of revInteger
         */
        int revInteger();

        /**
         * Returns the attribute revUnInteger.
         *
         * @return value of revUnInteger
         */
        long revUnInteger();

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the builder object of integer.
         *
         * @param integer value of integer
         * @return builder object of integer
         */
        MultiRangeValidationBuilder integer(int integer);

        /**
         * Returns the builder object of unInteger.
         *
         * @param unInteger value of unInteger
         * @return builder object of unInteger
         */
        MultiRangeValidationBuilder unInteger(long unInteger);

        /**
         * Returns the builder object of revInteger.
         *
         * @param revInteger value of revInteger
         * @return builder object of revInteger
         */
        MultiRangeValidationBuilder revInteger(int revInteger);

        /**
         * Returns the builder object of revUnInteger.
         *
         * @param revUnInteger value of revUnInteger
         * @return builder object of revUnInteger
         */
        MultiRangeValidationBuilder revUnInteger(long revUnInteger);

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        MultiRangeValidationBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);


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
        MultiRangeValidationBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of multiRangeValidation.
         *
         * @return object of multiRangeValidation.
         */
        MultiRangeValidation build();
    }
}
