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

package org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.b.rev20160826.ytbmultimoduleb;

import java.util.List;
import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of ytbmultilistb.
 */
public interface Ytbmultilistb {

    /**
     * Identify the leaf of Ytbmultilistb.
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
     * Returns the attribute checkin.
     *
     * @return list of checkin
     */
    List<String> checkin();

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
     * Checks if the passed Ytbmultilistb maps the content match query condition.
     *
     * @param ytbmultilistb ytbmultilistb being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Ytbmultilistb processSubtreeFiltering(Ytbmultilistb ytbmultilistb, boolean isSelectAllSchemaChild);

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
     * Builder for ytbmultilistb.
     */
    interface YtbmultilistbBuilder {


        /**
         * Adds to the list of checkin.
         *
         * @param value value of checkin
         * @return builder object of checkin
         */
        YtbmultilistbBuilder addToCheckin(String value);
        /**
         * Returns the attribute checkin.
         *
         * @return list of checkin
         */
        List<String> checkin();

        /**
         * Returns the builder object of checkin.
         *
         * @param checkin list of checkin
         * @return builder object of checkin
         */
        YtbmultilistbBuilder checkin(List<String> checkin);


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
        YtbmultilistbBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of ytbmultilistb.
         *
         * @return object of ytbmultilistb.
         */
        Ytbmultilistb build();
    }
}
