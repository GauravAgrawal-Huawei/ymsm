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

package org.onosproject.yang.gen.v1.ydt.integer16.rev20160524;

import java.util.List;
import org.onosproject.yang.gen.v1.ydt.integer16.rev20160524.integer16.MultiRangeValidation;

/**
 * Abstraction of an entity which represents the functionality of integer16.
 */
public interface Integer16 {

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
     * Identify the leaf of Integer16.
     */
    public enum LeafIdentifier {

        /**
         * Represents negInt.
         */
        NEGINT(1),
        /**
         * Represents posInt.
         */
        POSINT(2),
        /**
         * Represents minIntWithRange.
         */
        MININTWITHRANGE(3),
        /**
         * Represents midIntWithRange.
         */
        MIDINTWITHRANGE(4),
        /**
         * Represents maxIntWithRange.
         */
        MAXINTWITHRANGE(5),
        /**
         * Represents minUint.
         */
        MINUINT(6),
        /**
         * Represents maxUint.
         */
        MAXUINT(7),
        /**
         * Represents minUintWithRange.
         */
        MINUINTWITHRANGE(8),
        /**
         * Represents midUintWithRange.
         */
        MIDUINTWITHRANGE(9),
        /**
         * Represents maxUintWithRange.
         */
        MAXUINTWITHRANGE(10);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute negInt.
     *
     * @return value of negInt
     */
    short negInt();

    /**
     * Returns the attribute posInt.
     *
     * @return value of posInt
     */
    short posInt();

    /**
     * Returns the attribute minIntWithRange.
     *
     * @return value of minIntWithRange
     */
    short minIntWithRange();

    /**
     * Returns the attribute midIntWithRange.
     *
     * @return value of midIntWithRange
     */
    short midIntWithRange();

    /**
     * Returns the attribute maxIntWithRange.
     *
     * @return value of maxIntWithRange
     */
    short maxIntWithRange();

    /**
     * Returns the attribute minUint.
     *
     * @return value of minUint
     */
    int minUint();

    /**
     * Returns the attribute maxUint.
     *
     * @return value of maxUint
     */
    int maxUint();

    /**
     * Returns the attribute minUintWithRange.
     *
     * @return value of minUintWithRange
     */
    int minUintWithRange();

    /**
     * Returns the attribute midUintWithRange.
     *
     * @return value of midUintWithRange
     */
    int midUintWithRange();

    /**
     * Returns the attribute maxUintWithRange.
     *
     * @return value of maxUintWithRange
     */
    int maxUintWithRange();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Returns the attribute multiRangeValidation.
     *
     * @return list of multiRangeValidation
     */
    List<MultiRangeValidation> multiRangeValidation();

    /**
     * Checks if the passed Integer16 maps the content match query condition.
     *
     * @param integer16 integer16 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Integer16 processSubtreeFiltering(Integer16 integer16, boolean isSelectAllSchemaChild);

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
}
