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

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826
            .ytbderivedtypewithbitsandbinary.Derivedbinarya;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826
            .ytbderivedtypewithbitsandbinary.Derivedbitsa;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826
            .ytbderivedtypewithbitsandbinary.ForunionUnion;

/**
 * Represents the implementation of ytbDerivedTypeWithBitsAndBinary.
 */
public class YtbDerivedTypeWithBitsAndBinaryOpParam implements YtbDerivedTypeWithBitsAndBinary {

    protected Derivedbinarya forbinary;
    protected Derivedbitsa forbits;
    protected ForunionUnion forunion;
    protected List<Derivedbinarya> forbinarylist;
    protected List<Derivedbitsa> forbitslist;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();
    public Derivedbinarya forbinary() {
        return forbinary;
    }
    public Derivedbitsa forbits() {
        return forbits;
    }
    public ForunionUnion forunion() {
        return forunion;
    }
    public List<Derivedbinarya> forbinarylist() {
        return forbinarylist;
    }
    public List<Derivedbitsa> forbitslist() {
        return forbitslist;
    }
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(forbinary, forbits, forunion, forbinarylist, forbitslist, onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbDerivedTypeWithBitsAndBinaryOpParam) {
            YtbDerivedTypeWithBitsAndBinaryOpParam other = (YtbDerivedTypeWithBitsAndBinaryOpParam) obj;
            return
                 Objects.equals(forbinary, other.forbinary) &&
                 Objects.equals(forbits, other.forbits) &&
                 Objects.equals(forunion, other.forunion) &&
                 Objects.equals(forbinarylist, other.forbinarylist) &&
                 Objects.equals(forbitslist, other.forbitslist) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("forbinary", forbinary)
            .add("forbits", forbits)
            .add("forunion", forunion)
            .add("forbinarylist", forbinarylist)
            .add("forbitslist", forbitslist)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .toString();
    }

    /**
     * Creates an instance of ytbDerivedTypeWithBitsAndBinary.
     *
     * @param builderObject builder object of ytbDerivedTypeWithBitsAndBinary
     */
    protected YtbDerivedTypeWithBitsAndBinaryOpParam(YtbDerivedTypeWithBitsAndBinaryBuilder builderObject) {
        this.forbinary = builderObject.forbinary();
        this.forbits = builderObject.forbits();
        this.forunion = builderObject.forunion();
        this.forbinarylist = builderObject.forbinarylist();
        this.forbitslist = builderObject.forbitslist();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public YtbDerivedTypeWithBitsAndBinary processSubtreeFiltering(YtbDerivedTypeWithBitsAndBinary appInstance,
            boolean
            isSelectAllSchemaChild) {
        YtbDerivedTypeWithBitsAndBinaryBuilder subTreeFilteringResultBuilder = new
            YtbDerivedTypeWithBitsAndBinaryBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!processLeafListSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processLeafSubtreeFiltering(YtbDerivedTypeWithBitsAndBinary appInstance,
            YtbDerivedTypeWithBitsAndBinaryBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.FORBINARY.getLeafIndex())) {
            if (appInstance.forbinary() == null || !(forbinary().equals(appInstance.forbinary()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.forbinary(appInstance.forbinary());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.FORBINARY.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.forbinary(appInstance.forbinary());
        }

        if (getValueLeafFlags().get(LeafIdentifier.FORBITS.getLeafIndex())) {
            if (appInstance.forbits() == null || !(forbits().equals(appInstance.forbits()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.forbits(appInstance.forbits());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.FORBITS.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.forbits(appInstance.forbits());
        }

        if (getValueLeafFlags().get(LeafIdentifier.FORUNION.getLeafIndex())) {
            if (appInstance.forunion() == null || !(forunion().equals(appInstance.forunion()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.forunion(appInstance.forunion());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.FORUNION.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.forunion(appInstance.forunion());
        }

        return true;
    }

    private boolean processLeafListSubTreeFiltering(YtbDerivedTypeWithBitsAndBinary appInstance,
            YtbDerivedTypeWithBitsAndBinaryBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Derivedbinarya forbinarylist : appInstance.forbinarylist()) {
                subTreeFilteringResultBuilder.addToForbinarylist(forbinarylist);
            }
        } else if (forbinarylist() != null) {
            if (!forbinarylist().isEmpty()) {
                if (appInstance.forbinarylist() == null || appInstance.forbinarylist().isEmpty()) {
                    return false;
                }
                for (Derivedbinarya forbinarylist : forbinarylist()) {
                    boolean flag = false;
                    for (Derivedbinarya forbinarylist2 : appInstance.forbinarylist()) {
                        if (forbinarylist.equals(forbinarylist2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToForbinarylist(forbinarylist2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.forbinarylist() != null && !appInstance.forbinarylist().isEmpty()) {
                    for (Derivedbinarya forbinarylist : appInstance.forbinarylist()) {
                        subTreeFilteringResultBuilder.addToForbinarylist(forbinarylist);
                    }
                }
            }
        }

        if (isSelectAllSchemaChild) {
            for (Derivedbitsa forbitslist : appInstance.forbitslist()) {
                subTreeFilteringResultBuilder.addToForbitslist(forbitslist);
            }
        } else if (forbitslist() != null) {
            if (!forbitslist().isEmpty()) {
                if (appInstance.forbitslist() == null || appInstance.forbitslist().isEmpty()) {
                    return false;
                }
                for (Derivedbitsa forbitslist : forbitslist()) {
                    boolean flag = false;
                    for (Derivedbitsa forbitslist2 : appInstance.forbitslist()) {
                        if (forbitslist.equals(forbitslist2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToForbitslist(forbitslist2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.forbitslist() != null && !appInstance.forbitslist().isEmpty()) {
                    for (Derivedbitsa forbitslist : appInstance.forbitslist()) {
                        subTreeFilteringResultBuilder.addToForbitslist(forbitslist);
                    }
                }
            }
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(YtbDerivedTypeWithBitsAndBinary appInstance,
            YtbDerivedTypeWithBitsAndBinaryBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        return true;
    }


    /**
     * Creates an instance of ytbDerivedTypeWithBitsAndBinaryOpParam.
     */
    protected YtbDerivedTypeWithBitsAndBinaryOpParam() {
    }

    /**
     * Returns the attribute YtbDerivedTypeWithBitsAndBinaryBuilder.
     *
     * @return value of YtbDerivedTypeWithBitsAndBinaryBuilder
     */
    public static YtbDerivedTypeWithBitsAndBinaryBuilder builder() {
        return new YtbDerivedTypeWithBitsAndBinaryBuilder();
    }

    /**
     * Returns the valueLeafFlags.
     *
     * @return value of valueLeafFlags
     */
    public BitSet getValueLeafFlags() {
        return valueLeafFlags;
    }

    /**
     * Returns the selectLeafFlags.
     *
     * @return value of selectLeafFlags
     */
    public BitSet getSelectLeafFlags() {
        return selectLeafFlags;
    }


    @Override
    public boolean isLeafValueSet(LeafIdentifier leaf) {
        return getValueLeafFlags().get(leaf.getLeafIndex());
    }

    @Override
    public boolean isSelectLeaf(LeafIdentifier leaf) {
        return getSelectLeafFlags().get(leaf.getLeafIndex());
    }


    /**
     * Represents the builder implementation of ytbDerivedTypeWithBitsAndBinary.
     */
    public static class YtbDerivedTypeWithBitsAndBinaryBuilder {

        protected Derivedbinarya forbinary;
        protected Derivedbitsa forbits;
        protected ForunionUnion forunion;
        protected List<Derivedbinarya> forbinarylist;
        protected List<Derivedbitsa> forbitslist;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();

        public Derivedbinarya forbinary() {
            return forbinary;
        }
        public Derivedbitsa forbits() {
            return forbits;
        }
        public ForunionUnion forunion() {
            return forunion;
        }
        public List<Derivedbinarya> forbinarylist() {
            return forbinarylist;
        }
        public List<Derivedbitsa> forbitslist() {
            return forbitslist;
        }
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        /**
         * Returns the builder object of forbinary.
         *
         * @param forbinary value of forbinary
         * @return builder object of forbinary
         */
        public YtbDerivedTypeWithBitsAndBinaryBuilder forbinary(Derivedbinarya forbinary) {
            getValueLeafFlags().set(LeafIdentifier.FORBINARY.getLeafIndex());
            this.forbinary = forbinary;
            return this;
        }

        /**
         * Returns the builder object of forbits.
         *
         * @param forbits value of forbits
         * @return builder object of forbits
         */
        public YtbDerivedTypeWithBitsAndBinaryBuilder forbits(Derivedbitsa forbits) {
            getValueLeafFlags().set(LeafIdentifier.FORBITS.getLeafIndex());
            this.forbits = forbits;
            return this;
        }

        /**
         * Returns the builder object of forunion.
         *
         * @param forunion value of forunion
         * @return builder object of forunion
         */
        public YtbDerivedTypeWithBitsAndBinaryBuilder forunion(ForunionUnion forunion) {
            getValueLeafFlags().set(LeafIdentifier.FORUNION.getLeafIndex());
            this.forunion = forunion;
            return this;
        }

        /**
         * Returns the builder object of forbinarylist.
         *
         * @param forbinarylist list of forbinarylist
         * @return builder object of forbinarylist
         */
        public YtbDerivedTypeWithBitsAndBinaryBuilder forbinarylist(List<Derivedbinarya> forbinarylist) {
            this.forbinarylist = forbinarylist;
            return this;
        }

        /**
         * Returns the builder object of forbitslist.
         *
         * @param forbitslist list of forbitslist
         * @return builder object of forbitslist
         */
        public YtbDerivedTypeWithBitsAndBinaryBuilder forbitslist(List<Derivedbitsa> forbitslist) {
            this.forbitslist = forbitslist;
            return this;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public YtbDerivedTypeWithBitsAndBinaryBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }
        public YtbDerivedTypeWithBitsAndBinaryBuilder addToForbinarylist(Derivedbinarya value) {
            if (forbinarylist() == null) {
                forbinarylist(new ArrayList<>());
            }
            forbinarylist().add(value);
            return this;
        }
        public YtbDerivedTypeWithBitsAndBinaryBuilder addToForbitslist(Derivedbitsa value) {
            if (forbitslist() == null) {
                forbitslist(new ArrayList<>());
            }
            forbitslist().add(value);
            return this;
        }

        /**
         * Returns the valueLeafFlags.
         *
         * @return value of valueLeafFlags
         */
        public BitSet getValueLeafFlags() {
            return valueLeafFlags;
        }

        /**
         * Returns the selectLeafFlags.
         *
         * @return value of selectLeafFlags
         */
        public BitSet getSelectLeafFlags() {
            return selectLeafFlags;
        }



        public YtbDerivedTypeWithBitsAndBinaryBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public YtbDerivedTypeWithBitsAndBinary build() {
            return new YtbDerivedTypeWithBitsAndBinaryOpParam(this);
        }

        /**
         * Creates an instance of ytbDerivedTypeWithBitsAndBinaryBuilder.
         */
        public YtbDerivedTypeWithBitsAndBinaryBuilder() {
        }
    }
}
