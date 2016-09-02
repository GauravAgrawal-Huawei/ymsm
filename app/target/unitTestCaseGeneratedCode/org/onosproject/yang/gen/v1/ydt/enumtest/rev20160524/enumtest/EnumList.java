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

package org.onosproject.yang.gen.v1.ydt.enumtest.rev20160524.enumtest;

import java.util.Map;
import org.onosproject.yang.gen.v1.ydt.enumtest.rev20160524.enumtest.enumlist.EnumleafEnum;

/**
 * Abstraction of an entity which represents the functionality of enumList.
 */
public interface EnumList {

    /**
     * Identify the leaf of EnumList.
     */
    public enum LeafIdentifier {

        /**
         * Represents enumleaf.
         */
        ENUMLEAF(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute enumleaf.
     *
     * @return value of enumleaf
     */
    EnumleafEnum enumleaf();

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
     * Checks if the passed EnumList maps the content match query condition.
     *
     * @param enumList enumList being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    EnumList processSubtreeFiltering(EnumList enumList, boolean isSelectAllSchemaChild);

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
     * Builder for enumList.
     */
    interface EnumListBuilder {

        /**
         * Returns the attribute enumleaf.
         *
         * @return value of enumleaf
         */
        EnumleafEnum enumleaf();

        /**
         * Returns the builder object of enumleaf.
         *
         * @param enumleaf value of enumleaf
         * @return builder object of enumleaf
         */
        EnumListBuilder enumleaf(EnumleafEnum enumleaf);


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
        EnumListBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of enumList.
         *
         * @return object of enumList.
         */
        EnumList build();
    }
}
