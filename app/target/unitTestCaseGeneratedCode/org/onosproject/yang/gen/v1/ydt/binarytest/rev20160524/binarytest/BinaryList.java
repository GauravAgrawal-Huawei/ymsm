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

package org.onosproject.yang.gen.v1.ydt.binarytest.rev20160524.binarytest;

import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of binaryList.
 */
public interface BinaryList {

    /**
     * Identify the leaf of BinaryList.
     */
    public enum LeafIdentifier {

        /**
         * Represents binary.
         */
        BINARY(1),
        /**
         * Represents binaryWithRange.
         */
        BINARYWITHRANGE(2),
        /**
         * Represents binaryWithMultiRange.
         */
        BINARYWITHMULTIRANGE(3);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute binary.
     *
     * @return value of binary
     */
    byte[] binary();

    /**
     * Returns the attribute binaryWithRange.
     *
     * @return value of binaryWithRange
     */
    byte[] binaryWithRange();

    /**
     * Returns the attribute binaryWithMultiRange.
     *
     * @return value of binaryWithMultiRange
     */
    byte[] binaryWithMultiRange();

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
     * Checks if the passed BinaryList maps the content match query condition.
     *
     * @param binaryList binaryList being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    BinaryList processSubtreeFiltering(BinaryList binaryList, boolean isSelectAllSchemaChild);

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
     * Builder for binaryList.
     */
    interface BinaryListBuilder {

        /**
         * Returns the attribute binary.
         *
         * @return value of binary
         */
        byte[] binary();

        /**
         * Returns the attribute binaryWithRange.
         *
         * @return value of binaryWithRange
         */
        byte[] binaryWithRange();

        /**
         * Returns the attribute binaryWithMultiRange.
         *
         * @return value of binaryWithMultiRange
         */
        byte[] binaryWithMultiRange();

        /**
         * Returns the builder object of binary.
         *
         * @param binary value of binary
         * @return builder object of binary
         */
        BinaryListBuilder binary(byte[] binary);

        /**
         * Returns the builder object of binaryWithRange.
         *
         * @param binaryWithRange value of binaryWithRange
         * @return builder object of binaryWithRange
         */
        BinaryListBuilder binaryWithRange(byte[] binaryWithRange);

        /**
         * Returns the builder object of binaryWithMultiRange.
         *
         * @param binaryWithMultiRange value of binaryWithMultiRange
         * @return builder object of binaryWithMultiRange
         */
        BinaryListBuilder binaryWithMultiRange(byte[] binaryWithMultiRange);


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
        BinaryListBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of binaryList.
         *
         * @return object of binaryList.
         */
        BinaryList build();
    }
}
