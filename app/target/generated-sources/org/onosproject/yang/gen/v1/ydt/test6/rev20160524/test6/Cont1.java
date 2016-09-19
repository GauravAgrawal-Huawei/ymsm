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
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont1.Cont2;
import org.onosproject.yang.gen.v1.ydt.test6.rev20160524.test6.cont1.List1;

/**
 * Abstraction of an entity which represents the functionality of cont1.
 */
public interface Cont1 {

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
     * Identify the leaf of Cont1.
     */
    public enum LeafIdentifier {

        /**
         * Represents leaf4.
         */
        LEAF4(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute leaf4.
     *
     * @return value of leaf4
     */
    String leaf4();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Returns the attribute cont2.
     *
     * @return value of cont2
     */
    Cont2 cont2();

    /**
     * Returns the attribute list1.
     *
     * @return list of list1
     */
    List<List1> list1();

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
     * Checks if the passed Cont1 maps the content match query condition.
     *
     * @param cont1 cont1 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Cont1 processSubtreeFiltering(Cont1 cont1, boolean isSelectAllSchemaChild);

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
     * Builder for cont1.
     */
    interface Cont1Builder {


        /**
         * Adds to the list of list1.
         *
         * @param value value of list1
         * @return builder object of list1
         */
        Cont1Builder addToList1(List1 value);
        /**
         * Returns the attribute leaf4.
         *
         * @return value of leaf4
         */
        String leaf4();

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute cont2.
         *
         * @return value of cont2
         */
        Cont2 cont2();

        /**
         * Returns the attribute list1.
         *
         * @return list of list1
         */
        List<List1> list1();

        /**
         * Returns the builder object of leaf4.
         *
         * @param leaf4 value of leaf4
         * @return builder object of leaf4
         */
        Cont1Builder leaf4(String leaf4);

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        Cont1Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of cont2.
         *
         * @param cont2 value of cont2
         * @return builder object of cont2
         */
        Cont1Builder cont2(Cont2 cont2);

        /**
         * Returns the builder object of list1.
         *
         * @param list1 list of list1
         * @return builder object of list1
         */
        Cont1Builder list1(List<List1> list1);


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
        Cont1Builder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of cont1.
         *
         * @return object of cont1.
         */
        Cont1 build();
    }
}
