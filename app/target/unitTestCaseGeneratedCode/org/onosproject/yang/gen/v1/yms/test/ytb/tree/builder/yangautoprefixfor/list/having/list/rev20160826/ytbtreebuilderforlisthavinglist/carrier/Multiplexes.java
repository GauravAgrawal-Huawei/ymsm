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
            .ytbtreebuilderforlisthavinglist.carrier;

import java.util.List;
import java.util.Map;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826
            .ytbtreebuilderforlisthavinglist.carrier.multiplexes.ApplicationAreas;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826
            .ytbtreebuilderforlisthavinglist.carrier.multiplexes.TypesEnum;

/**
 * Abstraction of an entity which represents the functionality of multiplexes.
 */
public interface Multiplexes {

    /**
     * Identify the leaf of Multiplexes.
     */
    public enum LeafIdentifier {

        /**
         * Represents types.
         */
        TYPES(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute types.
     *
     * @return value of types
     */
    TypesEnum types();

    /**
     * Returns the attribute applicationAreas.
     *
     * @return list of applicationAreas
     */
    List<ApplicationAreas> applicationAreas();

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
     * Checks if the passed Multiplexes maps the content match query condition.
     *
     * @param multiplexes multiplexes being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Multiplexes processSubtreeFiltering(Multiplexes multiplexes, boolean isSelectAllSchemaChild);

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
     * Builder for multiplexes.
     */
    interface MultiplexesBuilder {


        /**
         * Adds to the list of applicationAreas.
         *
         * @param value value of applicationAreas
         * @return builder object of applicationAreas
         */
        MultiplexesBuilder addToApplicationAreas(ApplicationAreas value);
        /**
         * Returns the attribute types.
         *
         * @return value of types
         */
        TypesEnum types();

        /**
         * Returns the attribute applicationAreas.
         *
         * @return list of applicationAreas
         */
        List<ApplicationAreas> applicationAreas();

        /**
         * Returns the builder object of types.
         *
         * @param types value of types
         * @return builder object of types
         */
        MultiplexesBuilder types(TypesEnum types);

        /**
         * Returns the builder object of applicationAreas.
         *
         * @param applicationAreas list of applicationAreas
         * @return builder object of applicationAreas
         */
        MultiplexesBuilder applicationAreas(List<ApplicationAreas> applicationAreas);


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
        MultiplexesBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of multiplexes.
         *
         * @return object of multiplexes.
         */
        Multiplexes build();
    }
}
