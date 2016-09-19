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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes;

import java.util.Map;

/**
 * Abstraction of an entity which represents the functionality of unrecognizedAttributes.
 */
public interface UnrecognizedAttributes {

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
     * Identify the leaf of UnrecognizedAttributes.
     */
    public enum LeafIdentifier {

        /**
         * Represents partial.
         */
        PARTIAL(1),
        /**
         * Represents transitive.
         */
        TRANSITIVE(2),
        /**
         * Represents type.
         */
        TYPE(3),
        /**
         * Represents value.
         */
        VALUE(4);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute partial.
     *
     * @return value of partial
     */
    boolean partial();

    /**
     * Returns the attribute transitive.
     *
     * @return value of transitive
     */
    boolean transitive();

    /**
     * Returns the attribute type.
     *
     * @return value of type
     */
    short type();

    /**
     * Returns the attribute value.
     *
     * @return value of value
     */
    byte[] value();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

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
     * Checks if the passed UnrecognizedAttributes maps the content match query condition.
     *
     * @param unrecognizedAttributes unrecognizedAttributes being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    UnrecognizedAttributes processSubtreeFiltering(UnrecognizedAttributes unrecognizedAttributes, boolean
            isSelectAllSchemaChild);

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
     * Builder for unrecognizedAttributes.
     */
    interface UnrecognizedAttributesBuilder {

        /**
         * Returns the attribute partial.
         *
         * @return value of partial
         */
        boolean partial();

        /**
         * Returns the attribute transitive.
         *
         * @return value of transitive
         */
        boolean transitive();

        /**
         * Returns the attribute type.
         *
         * @return value of type
         */
        short type();

        /**
         * Returns the attribute value.
         *
         * @return value of value
         */
        byte[] value();

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the builder object of partial.
         *
         * @param partial value of partial
         * @return builder object of partial
         */
        UnrecognizedAttributesBuilder partial(boolean partial);

        /**
         * Returns the builder object of transitive.
         *
         * @param transitive value of transitive
         * @return builder object of transitive
         */
        UnrecognizedAttributesBuilder transitive(boolean transitive);

        /**
         * Returns the builder object of type.
         *
         * @param type value of type
         * @return builder object of type
         */
        UnrecognizedAttributesBuilder type(short type);

        /**
         * Returns the builder object of value.
         *
         * @param value value of value
         * @return builder object of value
         */
        UnrecognizedAttributesBuilder value(byte[] value);

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        UnrecognizedAttributesBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);


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
        UnrecognizedAttributesBuilder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of unrecognizedAttributes.
         *
         * @return object of unrecognizedAttributes.
         */
        UnrecognizedAttributes build();
    }
}
