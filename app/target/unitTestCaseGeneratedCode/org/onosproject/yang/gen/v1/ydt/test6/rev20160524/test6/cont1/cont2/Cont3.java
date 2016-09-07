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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont1.cont2;

import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of cont3.
 */
public interface Cont3 {

    /**
     * Identify the leaf of Cont3.
     */
    public enum LeafIdentifier {

        /**
         * Represents leaf1.
         */
        LEAF1(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute leaf1.
     *
     * @return value of leaf1
     */
    String leaf1();

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
     * Checks if the passed Cont3 maps the content match query condition.
     *
     * @param cont3 cont3 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Cont3 processSubtreeFiltering(Cont3 cont3, boolean isSelectAllSchemaChild);

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
     * Builder for cont3.
     */
    interface Cont3Builder {

        /**
         * Returns the attribute leaf1.
         *
         * @return value of leaf1
         */
        String leaf1();

        /**
         * Returns the builder object of leaf1.
         *
         * @param leaf1 value of leaf1
         * @return builder object of leaf1
         */
        Cont3Builder leaf1(String leaf1);


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
        Cont3Builder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of cont3.
         *
         * @return object of cont3.
         */
        Cont3 build();
    }
}
