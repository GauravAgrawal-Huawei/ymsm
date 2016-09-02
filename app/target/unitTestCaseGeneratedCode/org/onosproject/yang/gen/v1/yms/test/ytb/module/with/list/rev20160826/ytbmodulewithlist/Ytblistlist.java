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

package org.onosproject.yang.gen.v1.yms.test.ytb.module.with.list.rev20160826.ytbmodulewithlist;

import java.util.List;
import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of ytblistlist.
 */
public interface Ytblistlist {

    /**
     * Identify the leaf of Ytblistlist.
     */
    public enum LeafIdentifier {

        UNUSED(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute prediction.
     *
     * @return list of prediction
     */
    List<Find> prediction();

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
     * Checks if the passed Ytblistlist maps the content match query condition.
     *
     * @param ytblistlist ytblistlist being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Ytblistlist processSubtreeFiltering(Ytblistlist ytblistlist, boolean isSelectAllSchemaChild);

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
     * Builder for ytblistlist.
     */
    interface YtblistlistBuilder {


        /**
         * Adds to the list of prediction.
         *
         * @param value value of prediction
         * @return builder object of prediction
         */
        YtblistlistBuilder addToPrediction(Find value);
        /**
         * Returns the attribute prediction.
         *
         * @return list of prediction
         */
        List<Find> prediction();

        /**
         * Returns the builder object of prediction.
         *
         * @param prediction list of prediction
         * @return builder object of prediction
         */
        YtblistlistBuilder prediction(List<Find> prediction);


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
        YtblistlistBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of ytblistlist.
         *
         * @return object of ytblistlist.
         */
        Ytblistlist build();
    }
}
