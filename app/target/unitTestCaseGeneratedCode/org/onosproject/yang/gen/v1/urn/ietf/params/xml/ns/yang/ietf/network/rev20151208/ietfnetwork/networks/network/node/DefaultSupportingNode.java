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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks
            .network
            .node;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.Uri;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.NodeId;

/**
 * Represents the implementation of supportingNode.
 */
public class DefaultSupportingNode implements SupportingNode {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected Uri networkRef;
    protected NodeId nodeRef;
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

    @Override
    public Uri networkRef() {
        return networkRef;
    }

    @Override
    public NodeId nodeRef() {
        return nodeRef;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(networkRef, nodeRef, onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultSupportingNode) {
            DefaultSupportingNode other = (DefaultSupportingNode) obj;
            return
                 Objects.equals(networkRef, other.networkRef) &&
                 Objects.equals(nodeRef, other.nodeRef) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("networkRef", networkRef)
            .add("nodeRef", nodeRef)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
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
     * Creates an instance of supportingNode.
     *
     * @param builderObject builder object of supportingNode
     */
    protected DefaultSupportingNode(SupportingNodeBuilder builderObject) {
        this.networkRef = builderObject.networkRef();
        this.nodeRef = builderObject.nodeRef();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public SupportingNode processSubtreeFiltering(SupportingNode appInstance, boolean isSelectAllSchemaChild) {
        SupportingNodeBuilder subTreeFilteringResultBuilder = new SupportingNodeBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
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
    private boolean processLeafSubtreeFiltering(SupportingNode appInstance, SupportingNodeBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.NETWORKREF.getLeafIndex())) {
            if (appInstance.networkRef() == null || !(networkRef().equals(appInstance.networkRef()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.networkRef(appInstance.networkRef());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.NETWORKREF.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.networkRef(appInstance.networkRef());
        }

        if (getValueLeafFlags().get(LeafIdentifier.NODEREF.getLeafIndex())) {
            if (appInstance.nodeRef() == null || !(nodeRef().equals(appInstance.nodeRef()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.nodeRef(appInstance.nodeRef());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.NODEREF.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.nodeRef(appInstance.nodeRef());
        }

        return true;
    }


    /**
     * Creates an instance of defaultSupportingNode.
     */
    protected DefaultSupportingNode() {
    }

    /**
     * Returns the attribute SupportingNodeBuilder.
     *
     * @return value of SupportingNodeBuilder
     */
    public static SupportingNodeBuilder builder() {
        return new SupportingNodeBuilder();
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
     * Represents the builder implementation of supportingNode.
     */
    public static class SupportingNodeBuilder implements SupportingNode.SupportingNodeBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected Uri networkRef;
        protected NodeId nodeRef;
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


        @Override
        public Uri networkRef() {
            return networkRef;
        }

        @Override
        public NodeId nodeRef() {
            return nodeRef;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public SupportingNodeBuilder networkRef(Uri networkRef) {
            getValueLeafFlags().set(LeafIdentifier.NETWORKREF.getLeafIndex());
            this.networkRef = networkRef;
            return this;
        }

        @Override
        public SupportingNodeBuilder nodeRef(NodeId nodeRef) {
            getValueLeafFlags().set(LeafIdentifier.NODEREF.getLeafIndex());
            this.nodeRef = nodeRef;
            return this;
        }

        @Override
        public SupportingNodeBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
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
        public SupportingNodeBuilder selectLeaf(LeafIdentifier leaf) {
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
        public SupportingNode build() {
            return new DefaultSupportingNode(this);
        }

        /**
         * Creates an instance of supportingNodeBuilder.
         */
        public SupportingNodeBuilder() {
        }
    }
}
