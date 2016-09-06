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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network2.rev20151208.ietfnetwork2.networkup;

import java.util.Map;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network2.rev20151208.ietfnetwork2.networkup
            .networks.Network;

/**
 * Abstraction of an entity which represents the functionality of networks.
 */
public interface Networks {

    /**
     * Identify the leaf of Networks.
     */
    public enum LeafIdentifier {

        /**
         * Represents id.
         */
        ID(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute id.
     *
     * @return value of id
     */
    int id();

    /**
     * Returns the attribute network.
     *
     * @return value of network
     */
    Network network();

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
     * Checks if the passed Networks maps the content match query condition.
     *
     * @param networks networks being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Networks processSubtreeFiltering(Networks networks, boolean isSelectAllSchemaChild);

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
     * Builder for networks.
     */
    interface NetworksBuilder {

        /**
         * Returns the attribute id.
         *
         * @return value of id
         */
        int id();

        /**
         * Returns the attribute network.
         *
         * @return value of network
         */
        Network network();

        /**
         * Returns the builder object of id.
         *
         * @param id value of id
         * @return builder object of id
         */
        NetworksBuilder id(int id);

        /**
         * Returns the builder object of network.
         *
         * @param network value of network
         * @return builder object of network
         */
        NetworksBuilder network(Network network);


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
        NetworksBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of networks.
         *
         * @return object of networks.
         */
        Networks build();
    }
}
