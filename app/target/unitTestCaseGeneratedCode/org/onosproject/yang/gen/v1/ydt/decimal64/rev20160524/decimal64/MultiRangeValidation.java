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

package org.onosproject.yang.gen.v1.ydt.decimal64.rev20160524.decimal64;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of multiRangeValidation.
 */
public interface MultiRangeValidation {

    /**
     * Identify the leaf of MultiRangeValidation.
     */
    public enum LeafIdentifier {

        /**
         * Represents decimal.
         */
        DECIMAL(1),
        /**
         * Represents revDecimal.
         */
        REVDECIMAL(2);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute decimal.
     *
     * @return value of decimal
     */
    BigDecimal decimal();

    /**
     * Returns the attribute revDecimal.
     *
     * @return value of revDecimal
     */
    BigDecimal revDecimal();

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
         * Returns the attribute decimal.
         *
         * @return value of decimal
         */
        BigDecimal decimal();

        /**
         * Returns the attribute revDecimal.
         *
         * @return value of revDecimal
         */
        BigDecimal revDecimal();

        /**
         * Returns the builder object of decimal.
         *
         * @param decimal value of decimal
         * @return builder object of decimal
         */
        MultiRangeValidationBuilder decimal(BigDecimal decimal);

        /**
         * Returns the builder object of revDecimal.
         *
         * @param revDecimal value of revDecimal
         * @return builder object of revDecimal
         */
        MultiRangeValidationBuilder revDecimal(BigDecimal revDecimal);


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
