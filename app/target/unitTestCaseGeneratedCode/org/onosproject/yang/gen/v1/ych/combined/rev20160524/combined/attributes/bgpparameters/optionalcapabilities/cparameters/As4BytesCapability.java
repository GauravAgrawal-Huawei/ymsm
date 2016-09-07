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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.optionalcapabilities
            .cparameters;

import java.util.Map;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.AsNum;

/**
 * Abstraction of an entity which represents the functionality of as4BytesCapability.
 */
public interface As4BytesCapability {

    /**
     * Identify the leaf of As4BytesCapability.
     */
    public enum LeafIdentifier {

        /**
         * Represents asNumber.
         */
        ASNUMBER(1);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute asNumber.
     *
     * @return value of asNumber
     */
    AsNum asNumber();

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
     * Checks if the passed As4BytesCapability maps the content match query condition.
     *
     * @param as4BytesCapability as4BytesCapability being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    As4BytesCapability processSubtreeFiltering(As4BytesCapability as4BytesCapability, boolean isSelectAllSchemaChild);

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
     * Builder for as4BytesCapability.
     */
    interface As4BytesCapabilityBuilder {

        /**
         * Returns the attribute asNumber.
         *
         * @return value of asNumber
         */
        AsNum asNumber();

        /**
         * Returns the builder object of asNumber.
         *
         * @param asNumber value of asNumber
         * @return builder object of asNumber
         */
        As4BytesCapabilityBuilder asNumber(AsNum asNumber);


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
        As4BytesCapabilityBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of as4BytesCapability.
         *
         * @return object of as4BytesCapability.
         */
        As4BytesCapability build();
    }
}
