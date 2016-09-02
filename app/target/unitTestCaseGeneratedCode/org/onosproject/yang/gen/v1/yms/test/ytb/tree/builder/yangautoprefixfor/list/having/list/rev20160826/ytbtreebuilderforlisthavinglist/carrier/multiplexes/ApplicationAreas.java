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

package org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826
            .ytbtreebuilderforlisthavinglist.carrier.multiplexes;

import java.util.List;
import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of applicationAreas.
 */
public interface ApplicationAreas {

    /**
     * Identify the leaf of ApplicationAreas.
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
     * Returns the attribute destinationAreas.
     *
     * @return list of destinationAreas
     */
    List<byte[]> destinationAreas();

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
     * Checks if the passed ApplicationAreas maps the content match query condition.
     *
     * @param applicationAreas applicationAreas being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    ApplicationAreas processSubtreeFiltering(ApplicationAreas applicationAreas, boolean isSelectAllSchemaChild);

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
     * Builder for applicationAreas.
     */
    interface ApplicationAreasBuilder {


        /**
         * Adds to the list of destinationAreas.
         *
         * @param value value of destinationAreas
         * @return builder object of destinationAreas
         */
        ApplicationAreasBuilder addToDestinationAreas(byte[] value);
        /**
         * Returns the attribute destinationAreas.
         *
         * @return list of destinationAreas
         */
        List<byte[]> destinationAreas();

        /**
         * Returns the builder object of destinationAreas.
         *
         * @param destinationAreas list of destinationAreas
         * @return builder object of destinationAreas
         */
        ApplicationAreasBuilder destinationAreas(List<byte[]> destinationAreas);


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
        ApplicationAreasBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of applicationAreas.
         *
         * @return object of applicationAreas.
         */
        ApplicationAreas build();
    }
}
