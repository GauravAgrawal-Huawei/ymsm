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

package org.onosproject.yang.gen.v1.ydt.hello.onos.rev20160903.helloonos.helloworld.helloworldinput;

import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of stringList.
 */
public interface StringList {

    /**
     * Identify the leaf of StringList.
     */
    public enum LeafIdentifier {

        /**
         * Represents string1.
         */
        STRING1(1),
        /**
         * Represents string2.
         */
        STRING2(2),
        /**
         * Represents string3.
         */
        STRING3(3);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute string1.
     *
     * @return value of string1
     */
    String string1();

    /**
     * Returns the attribute string2.
     *
     * @return value of string2
     */
    String string2();

    /**
     * Returns the attribute string3.
     *
     * @return value of string3
     */
    String string3();

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
     * Checks if the passed StringList maps the content match query condition.
     *
     * @param stringList stringList being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    StringList processSubtreeFiltering(StringList stringList, boolean isSelectAllSchemaChild);

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
     * Builder for stringList.
     */
    interface StringListBuilder {

        /**
         * Returns the attribute string1.
         *
         * @return value of string1
         */
        String string1();

        /**
         * Returns the attribute string2.
         *
         * @return value of string2
         */
        String string2();

        /**
         * Returns the attribute string3.
         *
         * @return value of string3
         */
        String string3();

        /**
         * Returns the builder object of string1.
         *
         * @param string1 value of string1
         * @return builder object of string1
         */
        StringListBuilder string1(String string1);

        /**
         * Returns the builder object of string2.
         *
         * @param string2 value of string2
         * @return builder object of string2
         */
        StringListBuilder string2(String string2);

        /**
         * Returns the builder object of string3.
         *
         * @param string3 value of string3
         * @return builder object of string3
         */
        StringListBuilder string3(String string3);


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
        StringListBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of stringList.
         *
         * @return object of stringList.
         */
        StringList build();
    }
}