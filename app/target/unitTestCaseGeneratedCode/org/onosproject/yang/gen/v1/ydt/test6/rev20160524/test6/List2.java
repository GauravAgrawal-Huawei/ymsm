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

package org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6;

import java.util.List;
import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of list2.
 */
public interface List2 {

    /**
     * Identify the leaf of List2.
     */
    public enum LeafIdentifier {

        /**
         * Represents leaf5.
         */
        LEAF5(1),
        /**
         * Represents leaf6.
         */
        LEAF6(2),
        /**
         * Represents leaf7.
         */
        LEAF7(3);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute leaf5.
     *
     * @return value of leaf5
     */
    String leaf5();

    /**
     * Returns the attribute leaf6.
     *
     * @return value of leaf6
     */
    String leaf6();

    /**
     * Returns the attribute leaf7.
     *
     * @return value of leaf7
     */
    String leaf7();

    /**
     * Returns the attribute leaflist8.
     *
     * @return list of leaflist8
     */
    List<String> leaflist8();

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
     * Checks if the passed List2 maps the content match query condition.
     *
     * @param list2 list2 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    List2 processSubtreeFiltering(List2 list2, boolean isSelectAllSchemaChild);

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
     * Builder for list2.
     */
    interface List2Builder {


        /**
         * Adds to the list of leaflist8.
         *
         * @param value value of leaflist8
         * @return builder object of leaflist8
         */
        List2Builder addToLeaflist8(String value);
        /**
         * Returns the attribute leaf5.
         *
         * @return value of leaf5
         */
        String leaf5();

        /**
         * Returns the attribute leaf6.
         *
         * @return value of leaf6
         */
        String leaf6();

        /**
         * Returns the attribute leaf7.
         *
         * @return value of leaf7
         */
        String leaf7();

        /**
         * Returns the attribute leaflist8.
         *
         * @return list of leaflist8
         */
        List<String> leaflist8();

        /**
         * Returns the builder object of leaf5.
         *
         * @param leaf5 value of leaf5
         * @return builder object of leaf5
         */
        List2Builder leaf5(String leaf5);

        /**
         * Returns the builder object of leaf6.
         *
         * @param leaf6 value of leaf6
         * @return builder object of leaf6
         */
        List2Builder leaf6(String leaf6);

        /**
         * Returns the builder object of leaf7.
         *
         * @param leaf7 value of leaf7
         * @return builder object of leaf7
         */
        List2Builder leaf7(String leaf7);

        /**
         * Returns the builder object of leaflist8.
         *
         * @param leaflist8 list of leaflist8
         * @return builder object of leaflist8
         */
        List2Builder leaflist8(List<String> leaflist8);


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
        List2Builder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of list2.
         *
         * @return object of list2.
         */
        List2 build();
    }
}
