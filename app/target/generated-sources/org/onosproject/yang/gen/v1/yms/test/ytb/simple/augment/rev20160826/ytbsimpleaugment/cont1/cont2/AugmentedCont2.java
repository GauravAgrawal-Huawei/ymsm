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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2;

import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2
            .augmentedcont2.Cont1s;

/**
 * Abstraction of an entity which represents the functionality of augmentedCont2.
 */
public interface AugmentedCont2 {

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
     * Identify the leaf of AugmentedCont2.
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
    int leaf4();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Returns the attribute cont1s.
     *
     * @return value of cont1s
     */
    Cont1s cont1s();

    /**
     * Checks if the passed AugmentedCont2 maps the content match query condition.
     *
     * @param augmentedCont2 augmentedCont2 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    AugmentedCont2 processSubtreeFiltering(AugmentedCont2 augmentedCont2, boolean isSelectAllSchemaChild);

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
     * Builder for augmentedCont2.
     */
    interface AugmentedCont2Builder {

        /**
         * Returns the attribute leaf4.
         *
         * @return value of leaf4
         */
        int leaf4();

        /**
         * Returns the attribute onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        OnosYangNodeOperationType onosYangNodeOperationType();

        /**
         * Returns the attribute cont1s.
         *
         * @return value of cont1s
         */
        Cont1s cont1s();

        /**
         * Returns the builder object of leaf4.
         *
         * @param leaf4 value of leaf4
         * @return builder object of leaf4
         */
        AugmentedCont2Builder leaf4(int leaf4);

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        AugmentedCont2Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType);

        /**
         * Returns the builder object of cont1s.
         *
         * @param cont1s value of cont1s
         * @return builder object of cont1s
         */
        AugmentedCont2Builder cont1s(Cont1s cont1s);


        /**
         * Set a leaf to be selected.
         *
         * @param leaf leaf needs to be selected
         * @return builder object for select leaf
         */
        AugmentedCont2Builder selectLeaf(LeafIdentifier leaf);

        /**
         * Builds object of augmentedCont2.
         *
         * @return object of augmentedCont2.
         */
        AugmentedCont2 build();
    }
}
