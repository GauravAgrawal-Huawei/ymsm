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

package org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826;

import java.util.List;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826
            .ytbderivedtypewithbitsandbinary.Derivedbinarya;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826
            .ytbderivedtypewithbitsandbinary.Derivedbitsa;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826
            .ytbderivedtypewithbitsandbinary.ForunionUnion;

/**
 * Abstraction of an entity which represents the functionality of ytbDerivedTypeWithBitsAndBinary.
 */
public interface YtbDerivedTypeWithBitsAndBinary {

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
     * Identify the leaf of YtbDerivedTypeWithBitsAndBinary.
     */
    public enum LeafIdentifier {

        /**
         * Represents forbinary.
         */
        FORBINARY(1),
        /**
         * Represents forbits.
         */
        FORBITS(2),
        /**
         * Represents forunion.
         */
        FORUNION(3);

        private int leafIndex;

        public int getLeafIndex() {
            return leafIndex;
        }

        LeafIdentifier(int value) {
            this.leafIndex = value;
        }
    }

    /**
     * Returns the attribute forbinary.
     *
     * @return value of forbinary
     */
    Derivedbinarya forbinary();

    /**
     * Returns the attribute forbits.
     *
     * @return value of forbits
     */
    Derivedbitsa forbits();

    /**
     * Returns the attribute forunion.
     *
     * @return value of forunion
     */
    ForunionUnion forunion();

    /**
     * Returns the attribute forbinarylist.
     *
     * @return list of forbinarylist
     */
    List<Derivedbinarya> forbinarylist();

    /**
     * Returns the attribute forbitslist.
     *
     * @return list of forbitslist
     */
    List<Derivedbitsa> forbitslist();

    /**
     * Returns the attribute onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    OnosYangNodeOperationType onosYangNodeOperationType();

    /**
     * Checks if the passed YtbDerivedTypeWithBitsAndBinary maps the content match query condition.
     *
     * @param ytbDerivedTypeWithBitsAndBinary ytbDerivedTypeWithBitsAndBinary being passed to check for content match
     * @param isSelectAllSchemaChild is select all schema child
     * @return match result
     */
    YtbDerivedTypeWithBitsAndBinary processSubtreeFiltering(YtbDerivedTypeWithBitsAndBinary
            ytbDerivedTypeWithBitsAndBinary, boolean isSelectAllSchemaChild);

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
