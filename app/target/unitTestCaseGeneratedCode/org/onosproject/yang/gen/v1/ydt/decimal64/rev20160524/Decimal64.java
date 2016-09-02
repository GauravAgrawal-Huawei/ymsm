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

package org.onosproject.yang.gen.v1.ydt.decimal64.rev20160524;

import java.math.BigDecimal;
import java.util.List;
import org.onosproject.yang.gen.v1.ydt.decimal64.rev20160524.decimal64.MultiRangeValidation;

/**
 * Abstraction of an entity which represents the functionality of decimal64.
 */
public interface Decimal64 {

    /**
     * Identify the leaf of Decimal64.
     */
    public enum LeafIdentifier {

        /**
         * Represents negInt.
         */
        NEGINT(1),
        /**
         * Represents negIntWithMaxFraction.
         */
        NEGINTWITHMAXFRACTION(2),
        /**
         * Represents negIntWithMinFraction.
         */
        NEGINTWITHMINFRACTION(3),
        /**
         * Represents posInt.
         */
        POSINT(4),
        /**
         * Represents posIntWithMaxFraction.
         */
        POSINTWITHMAXFRACTION(5),
        /**
         * Represents posIntWithMinFraction.
         */
        POSINTWITHMINFRACTION(6),
        /**
         * Represents minIntWithRange.
         */
        MININTWITHRANGE(7),
        /**
         * Represents midIntWithRange.
         */
        MIDINTWITHRANGE(8),
        /**
         * Represents maxIntWithRange.
         */
        MAXINTWITHRANGE(9);

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
    BigDecimal negInt();

    /**
     * Returns the attribute negIntWithMaxFraction.
     *
     * @return value of negIntWithMaxFraction
     */
    BigDecimal negIntWithMaxFraction();

    /**
     * Returns the attribute negIntWithMinFraction.
     *
     * @return value of negIntWithMinFraction
     */
    BigDecimal negIntWithMinFraction();

    /**
     * Returns the attribute posInt.
     *
     * @return value of posInt
     */
    BigDecimal posInt();

    /**
     * Returns the attribute posIntWithMaxFraction.
     *
     * @return value of posIntWithMaxFraction
     */
    BigDecimal posIntWithMaxFraction();

    /**
     * Returns the attribute posIntWithMinFraction.
     *
     * @return value of posIntWithMinFraction
     */
    BigDecimal posIntWithMinFraction();

    /**
     * Returns the attribute minIntWithRange.
     *
     * @return value of minIntWithRange
     */
    BigDecimal minIntWithRange();

    /**
     * Returns the attribute midIntWithRange.
     *
     * @return value of midIntWithRange
     */
    BigDecimal midIntWithRange();

    /**
     * Returns the attribute maxIntWithRange.
     *
     * @return value of maxIntWithRange
     */
    BigDecimal maxIntWithRange();

    /**
     * Returns the attribute multiRangeValidation.
     *
     * @return list of multiRangeValidation
     */
    List<MultiRangeValidation> multiRangeValidation();

    /**
     * Checks if the passed Decimal64 maps the content match query condition.
     *
     * @param decimal64 decimal64 being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    Decimal64 processSubtreeFiltering(Decimal64 decimal64, boolean isSelectAllSchemaChild);

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
