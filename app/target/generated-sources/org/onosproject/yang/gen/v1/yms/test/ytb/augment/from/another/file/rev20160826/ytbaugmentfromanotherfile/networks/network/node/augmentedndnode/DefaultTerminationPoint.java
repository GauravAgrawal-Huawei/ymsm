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

package org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile
            .networks.network.node.augmentedndnode;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile
            .networks.network.node.augmentedndnode.terminationpoint.SupportingTerminationPoint;

/**
 * Represents the implementation of terminationPoint.
 */
public class DefaultTerminationPoint implements TerminationPoint {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected String tpId;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<SupportingTerminationPoint> supportingTerminationPoint;
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();

    @Override
    public String tpId() {
        return tpId;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public List<SupportingTerminationPoint> supportingTerminationPoint() {
        return supportingTerminationPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tpId, onosYangNodeOperationType, supportingTerminationPoint);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultTerminationPoint) {
            DefaultTerminationPoint other = (DefaultTerminationPoint) obj;
            return
                 Objects.equals(tpId, other.tpId) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(supportingTerminationPoint, other.supportingTerminationPoint);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("tpId", tpId)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("supportingTerminationPoint", supportingTerminationPoint)
            .toString();
    }

    @Override
    public Object yangAugmentedInfo(Class classObject) {
        return yangAugmentedInfoMap.get(classObject);
    }

    @Override
    public Map<Class<?>, Object> yangAugmentedInfoMap() {
        return yangAugmentedInfoMap;
    }

    /**
     * Creates an instance of terminationPoint.
     *
     * @param builderObject builder object of terminationPoint
     */
    protected DefaultTerminationPoint(TerminationPointBuilder builderObject) {
        this.tpId = builderObject.tpId();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.supportingTerminationPoint = builderObject.supportingTerminationPoint();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public TerminationPoint processSubtreeFiltering(TerminationPoint appInstance, boolean isSelectAllSchemaChild) {
        TerminationPointBuilder subTreeFilteringResultBuilder = new TerminationPointBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        for (Object yangAugmentedInfo : this.yangAugmentedInfoMap().values()) {
            Object yangAugmentedOpParamInfo = appInstance.yangAugmentedInfo(yangAugmentedInfo.getClass());
            Object processSubtreeFiltering;
            try {
                Class<?>[] interfaces = yangAugmentedInfo.getClass().getInterfaces();
                processSubtreeFiltering = yangAugmentedInfo.getClass()
                    .getMethod("processSubtreeFiltering", interfaces[0]).invoke(yangAugmentedInfo,
                        yangAugmentedOpParamInfo);
                if (processSubtreeFiltering != null) {
                    subTreeFilteringResultBuilder
            .addYangAugmentedInfo(processSubtreeFiltering, processSubtreeFiltering.getClass());
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                continue;
            }
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processLeafSubtreeFiltering(TerminationPoint appInstance, TerminationPointBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.TPID.getLeafIndex())) {
            if (appInstance.tpId() == null || !(tpId().equals(appInstance.tpId()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.tpId(appInstance.tpId());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.TPID.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.tpId(appInstance.tpId());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(TerminationPoint appInstance, TerminationPointBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (SupportingTerminationPoint supportingTerminationPoint : appInstance.supportingTerminationPoint()) {
                subTreeFilteringResultBuilder.addToSupportingTerminationPoint(supportingTerminationPoint);
            }
        } else if (supportingTerminationPoint() != null) {
            isAnySelectOrContainmentNode = true;
            if (!supportingTerminationPoint().isEmpty()) {
                if (appInstance.supportingTerminationPoint() != null && !appInstance
            .supportingTerminationPoint().isEmpty()) {
                    for (SupportingTerminationPoint supportingTerminationPoint : supportingTerminationPoint()) {
                        for (SupportingTerminationPoint supportingTerminationPoint2 : appInstance
            .supportingTerminationPoint()) {
                            SupportingTerminationPoint result = supportingTerminationPoint
            .processSubtreeFiltering(supportingTerminationPoint2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToSupportingTerminationPoint(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.supportingTerminationPoint() != null && !appInstance
            .supportingTerminationPoint().isEmpty()) {
                    for (SupportingTerminationPoint supportingTerminationPoint : appInstance
            .supportingTerminationPoint()) {
                        subTreeFilteringResultBuilder.addToSupportingTerminationPoint(supportingTerminationPoint);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultTerminationPoint.
     */
    protected DefaultTerminationPoint() {
    }

    /**
     * Returns the attribute TerminationPointBuilder.
     *
     * @return value of TerminationPointBuilder
     */
    public static TerminationPointBuilder builder() {
        return new TerminationPointBuilder();
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
     * Represents the builder implementation of terminationPoint.
     */
    public static class TerminationPointBuilder implements TerminationPoint.TerminationPointBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected String tpId;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<SupportingTerminationPoint> supportingTerminationPoint;
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();


        @Override
        public String tpId() {
            return tpId;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public List<SupportingTerminationPoint> supportingTerminationPoint() {
            return supportingTerminationPoint;
        }

        @Override
        public TerminationPointBuilder tpId(String tpId) {
            getValueLeafFlags().set(LeafIdentifier.TPID.getLeafIndex());
            this.tpId = tpId;
            return this;
        }

        @Override
        public TerminationPointBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public TerminationPointBuilder supportingTerminationPoint(List<SupportingTerminationPoint>
            supportingTerminationPoint) {
            this.supportingTerminationPoint = supportingTerminationPoint;
            return this;
        }

        @Override
        public TerminationPointBuilder addToSupportingTerminationPoint(SupportingTerminationPoint value) {
            if (supportingTerminationPoint() == null) {
                supportingTerminationPoint(new ArrayList<>());
            }
            supportingTerminationPoint().add(value);
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



        @Override
        public TerminationPointBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        @Override
        public void addYangAugmentedInfo(Object value, Class classObject) {
            yangAugmentedInfoMap.put(classObject, value);
        }

        @Override
        public Object yangAugmentedInfo(Class classObject) {
            return yangAugmentedInfoMap.get(classObject);
        }

        @Override
        public Map<Class<?>, Object> yangAugmentedInfoMap() {
            return yangAugmentedInfoMap;
        }
        @Override
        public TerminationPoint build() {
            return new DefaultTerminationPoint(this);
        }

        /**
         * Creates an instance of terminationPointBuilder.
         */
        public TerminationPointBuilder() {
        }
    }
}
