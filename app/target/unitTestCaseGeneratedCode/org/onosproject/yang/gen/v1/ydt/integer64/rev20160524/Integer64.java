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

package org.onosproject.yang.gen.v1.ydt.integer64.rev20160524;

import java.math.BigInteger;
import java.util.List;
import org.onosproject.yang.gen.v1.ydt.integer64.rev20160524.integer64.MultiRangeValidation;

/**
 * Abstraction of an entity which represents the functionality of integer64.
 */
public interface Integer64 {

    /**
     * Identify the leaf of Integer64.
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
    long negInt();

    /**
     * Returns the attribute posInt.
     *
     * @return value of posInt
     */
    long posInt();

    /**
     * Returns the attribute minIntWithRange.
     *
     * @return value of minIntWithRange
     */
    long minIntWithRange();

    /**
     * Returns the attribute midIntWithRange.
     *
     * @return value of midIntWithRange
     */
    long midIntWithRange();

    /**
     * Returns the attribute maxIntWithRange.
     *
     * @return value of maxIntWithRange
     */
    long maxIntWithRange();

    /**
     * Returns the attribute minUint.
     *
     * @return value of minUint
     */
    BigInteger minUint();

    /**
     * Returns the attribute maxUint.
     *
     * @return value of maxUint
     */
    BigInteger maxUint();

    /**
     * Returns the attribute minUintWithRange.
     *
     * @return value of minUintWithRange
     */
    BigInteger minUintWithRange();

    /**
     * Returns the attribute midUintWithRange.
     *
     * @return value of midUintWithRange
     */
    BigInteger midUintWithRange();

    /**
     * Returns the attribute maxUintWithRange.
     *
     * @return value of maxUintWithRange
     */
    BigInteger maxUintWithRange();

    /**
     * Returns the attribute multiRangeValidation.
     *
     * @return list of multiRangeValidation
     */
    List<MultiRangeValidation> multiRangeValidation();

    /**
     * Checks if the passed Integer64 maps the content match query condition.
     *
     * @param integer64 integer64 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Integer64 processSubtreeFiltering(Integer64 integer64, boolean isSelectAllSchemaChild);

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
