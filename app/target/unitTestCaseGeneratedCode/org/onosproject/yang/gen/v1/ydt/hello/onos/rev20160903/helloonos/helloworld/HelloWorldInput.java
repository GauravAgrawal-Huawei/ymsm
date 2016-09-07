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

package org.onosproject.yang.gen.v1.ydt.hello.onos.rev20160903.helloonos.helloworld;

import java.util.List;
import java.util.Map;
import org.onosproject.yang.gen.v1.ydt.hello.onos.rev20160903.helloonos.helloworld.helloworldinput.StringList;

/**
 * Abstraction of an entity which represents the functionality of helloWorldInput.
 */
public interface HelloWorldInput {

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
     * Identify the leaf of HelloWorldInput.
     */
    public enum LeafIdentifier {

        /**
         * Represents name.
         */
        NAME(1),
        /**
         * Represents surName.
         */
        SURNAME(2),
        /**
         * Represents inputDefault.
         */
        INPUTDEFAULT(3);

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
     * Returns the attribute surName.
     *
     * @return value of surName
     */
    String surName();

    /**
     * Returns the attribute inputDefault.
     *
     * @return value of inputDefault
     */
    String inputDefault();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Returns the attribute stringList.
     *
     * @return list of stringList
     */
    List<StringList> stringList();

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
     * Checks if the passed HelloWorldInput maps the content match query condition.
     *
     * @param helloWorldInput helloWorldInput being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    HelloWorldInput processSubtreeFiltering(HelloWorldInput helloWorldInput, boolean isSelectAllSchemaChild);

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
     * Builder for helloWorldInput.
     */
    interface HelloWorldInputBuilder {


        /**
         * Adds to the list of stringList.
         *
         * @param value value of stringList
         * @return builder object of stringList
         */
        HelloWorldInputBuilder addToStringList(StringList value);
        /**
         * Returns the attribute name.
         *
         * @return value of name
         */
        String name();

        /**
         * Returns the attribute surName.
         *
         * @return value of surName
         */
        String surName();

        /**
         * Returns the attribute inputDefault.
         *
         * @return value of inputDefault
         */
        String inputDefault();

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute stringList.
         *
         * @return list of stringList
         */
        List<StringList> stringList();

        /**
         * Returns the builder object of name.
         *
         * @param name value of name
         * @return builder object of name
         */
        HelloWorldInputBuilder name(String name);

        /**
         * Returns the builder object of surName.
         *
         * @param surName value of surName
         * @return builder object of surName
         */
        HelloWorldInputBuilder surName(String surName);

        /**
         * Returns the builder object of inputDefault.
         *
         * @param inputDefault value of inputDefault
         * @return builder object of inputDefault
         */
        HelloWorldInputBuilder inputDefault(String inputDefault);

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        HelloWorldInputBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of stringList.
         *
         * @param stringList list of stringList
         * @return builder object of stringList
         */
        HelloWorldInputBuilder stringList(List<StringList> stringList);


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
        HelloWorldInputBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of helloWorldInput.
         *
         * @return object of helloWorldInput.
         */
        HelloWorldInput build();
    }
}
