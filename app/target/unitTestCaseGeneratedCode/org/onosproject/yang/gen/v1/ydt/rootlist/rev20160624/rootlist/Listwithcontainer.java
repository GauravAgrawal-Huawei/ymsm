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

package org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.rootlist;

import java.util.List;
import java.util.Map;
import org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.rootlist.listwithcontainer.YangAutoPrefixInterface;

/**
 * Abstraction of an entity which represents the functionality of listwithcontainer.
 */
public interface Listwithcontainer {

    /**
     * Identify the leaf of Listwithcontainer.
     */
    public enum LeafIdentifier {

        /**
         * Represents invalid.
         */
        INVALID(1),
        /**
         * Represents invalid1.
         */
        INVALID1(2);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute invalid.
     *
     * @return value of invalid
     */
    int invalid();

    /**
     * Returns the attribute invalid1.
     *
     * @return value of invalid1
     */
    int invalid1();

    /**
     * Returns the attribute invalidinterval.
     *
     * @return list of invalidinterval
     */
    List<Integer> invalidinterval();

    /**
     * Returns the attribute yangAutoPrefixInterface.
     *
     * @return value of yangAutoPrefixInterface
     */
    YangAutoPrefixInterface yangAutoPrefixInterface();

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
     * Checks if the passed Listwithcontainer maps the content match query condition.
     *
     * @param listwithcontainer listwithcontainer being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Listwithcontainer processSubtreeFiltering(Listwithcontainer listwithcontainer, boolean isSelectAllSchemaChild);

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
     * Builder for listwithcontainer.
     */
    interface ListwithcontainerBuilder {


        /**
         * Adds to the list of invalidinterval.
         *
         * @param value value of invalidinterval
         * @return builder object of invalidinterval
         */
        ListwithcontainerBuilder addToInvalidinterval(Integer value);
        /**
         * Returns the attribute invalid.
         *
         * @return value of invalid
         */
        int invalid();

        /**
         * Returns the attribute invalid1.
         *
         * @return value of invalid1
         */
        int invalid1();

        /**
         * Returns the attribute invalidinterval.
         *
         * @return list of invalidinterval
         */
        List<Integer> invalidinterval();

        /**
         * Returns the attribute yangAutoPrefixInterface.
         *
         * @return value of yangAutoPrefixInterface
         */
        YangAutoPrefixInterface yangAutoPrefixInterface();

        /**
         * Returns the builder object of invalid.
         *
         * @param invalid value of invalid
         * @return builder object of invalid
         */
        ListwithcontainerBuilder invalid(int invalid);

        /**
         * Returns the builder object of invalid1.
         *
         * @param invalid1 value of invalid1
         * @return builder object of invalid1
         */
        ListwithcontainerBuilder invalid1(int invalid1);

        /**
         * Returns the builder object of invalidinterval.
         *
         * @param invalidinterval list of invalidinterval
         * @return builder object of invalidinterval
         */
        ListwithcontainerBuilder invalidinterval(List<Integer> invalidinterval);

        /**
         * Returns the builder object of yangAutoPrefixInterface.
         *
         * @param yangAutoPrefixInterface value of yangAutoPrefixInterface
         * @return builder object of yangAutoPrefixInterface
         */
        ListwithcontainerBuilder yangAutoPrefixInterface(YangAutoPrefixInterface yangAutoPrefixInterface);


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
        ListwithcontainerBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of listwithcontainer.
         *
         * @return object of listwithcontainer.
         */
        Listwithcontainer build();
    }
}
