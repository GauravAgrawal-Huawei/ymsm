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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks.network;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.NodeId;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork.networks.network
            .node.SupportingNode;

/**
 * Represents the implementation of node.
 */
public class DefaultNode implements Node {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
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
     * Specify the node specific operation in protocols like NETCONF.
     * Applicable in protocol edit operation, will be ignored in query operation
     */
    private OnosYangNodeOperationType onosYangNodeOperationType;


    protected NodeId nodeId;
    protected List<SupportingNode> supportingNode = new ArrayList<>();
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }


    @Override
    public NodeId nodeId() {
        return nodeId;
    }

    @Override
    public List<SupportingNode> supportingNode() {
        return supportingNode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, supportingNode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultNode) {
            DefaultNode other = (DefaultNode) obj;
            return
                 Objects.equals(nodeId, other.nodeId) &&
                 Objects.equals(supportingNode, other.supportingNode);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("nodeId", nodeId)
            .add("supportingNode", supportingNode)
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
     * Creates an instance of node.
     *
     * @param builderObject builder object of node
     */
    protected DefaultNode(NodeBuilder builderObject) {
        this.nodeId = builderObject.nodeId();
        this.supportingNode = builderObject.supportingNode();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Node processSubtreeFiltering(Node appInstance, boolean isSelectAllSchemaChild) {
        NodeBuilder subTreeFilteringResultBuilder = new NodeBuilder();
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
    private boolean processLeafSubtreeFiltering(Node appInstance, NodeBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.NODEID.getLeafIndex())) {
            if (appInstance.nodeId() == null || !(nodeId().equals(appInstance.nodeId()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.nodeId(appInstance.nodeId());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.NODEID.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.nodeId(appInstance.nodeId());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(Node appInstance, NodeBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (SupportingNode supportingNode : appInstance.supportingNode()) {
                subTreeFilteringResultBuilder.addToSupportingNode(supportingNode);
            }
        } else if (supportingNode() != null) {
            isAnySelectOrContainmentNode = true;
            if (!supportingNode().isEmpty()) {
                if (appInstance.supportingNode() != null && !appInstance.supportingNode().isEmpty()) {
                    for (SupportingNode supportingNode : supportingNode()) {
                        for (SupportingNode supportingNode2 : appInstance.supportingNode()) {
                            SupportingNode result = supportingNode.processSubtreeFiltering(supportingNode2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToSupportingNode(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.supportingNode() != null && !appInstance.supportingNode().isEmpty()) {
                    for (SupportingNode supportingNode : appInstance.supportingNode()) {
                        subTreeFilteringResultBuilder.addToSupportingNode(supportingNode);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultNode.
     */
    protected DefaultNode() {
    }

    /**
     * Returns the attribute NodeBuilder.
     *
     * @return value of NodeBuilder
     */
    public static NodeBuilder builder() {
        return new NodeBuilder();
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
     * Represents the builder implementation of node.
     */
    public static class NodeBuilder implements Node.NodeBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected NodeId nodeId;
        protected List<SupportingNode> supportingNode = new ArrayList<>();
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();

        /**
         * Returns the onosYangNodeOperationType.
         *
         * @return value of onosYangNodeOperationType
         */
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        /**
         * Set node operation type.
         *
         * @param onosYangNodeOperationType node operation type
         * @return builder object for node operation type
         */
        public NodeBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        @Override
        public NodeId nodeId() {
            return nodeId;
        }

        @Override
        public List<SupportingNode> supportingNode() {
            return supportingNode;
        }

        @Override
        public NodeBuilder nodeId(NodeId nodeId) {
            getValueLeafFlags().set(LeafIdentifier.NODEID.getLeafIndex());
            this.nodeId = nodeId;
            return this;
        }

        @Override
        public NodeBuilder supportingNode(List<SupportingNode> supportingNode) {
            this.supportingNode = supportingNode;
            return this;
        }

        @Override
        public NodeBuilder addToSupportingNode(SupportingNode value) {
            supportingNode().add(value);
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
        public NodeBuilder selectLeaf(LeafIdentifier leaf) {
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
        public Node build() {
            return new DefaultNode(this);
        }

        /**
         * Creates an instance of nodeBuilder.
         */
        public NodeBuilder() {
        }
    }
}
