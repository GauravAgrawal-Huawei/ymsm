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
            .networks.network.node;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile
            .networks.network.node.augmentedndnode.TerminationPoint;

/**
 * Represents the implementation of augmentedNdNode.
 */
public class DefaultAugmentedNdNode implements AugmentedNdNode {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<TerminationPoint> terminationPoint;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public List<TerminationPoint> terminationPoint() {
        return terminationPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, terminationPoint);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultAugmentedNdNode) {
            DefaultAugmentedNdNode other = (DefaultAugmentedNdNode) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(terminationPoint, other.terminationPoint);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("terminationPoint", terminationPoint)
            .toString();
    }

    /**
     * Creates an instance of augmentedNdNode.
     *
     * @param builderObject builder object of augmentedNdNode
     */
    protected DefaultAugmentedNdNode(AugmentedNdNodeBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.terminationPoint = builderObject.terminationPoint();
    }

    @Override
    public AugmentedNdNode processSubtreeFiltering(AugmentedNdNode appInstance, boolean isSelectAllSchemaChild) {
        AugmentedNdNodeBuilder subTreeFilteringResultBuilder = new AugmentedNdNodeBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processChildNodesSubTreeFiltering(AugmentedNdNode appInstance, AugmentedNdNodeBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (TerminationPoint terminationPoint : appInstance.terminationPoint()) {
                subTreeFilteringResultBuilder.addToTerminationPoint(terminationPoint);
            }
        } else if (terminationPoint() != null) {
            isAnySelectOrContainmentNode = true;
            if (!terminationPoint().isEmpty()) {
                if (appInstance.terminationPoint() != null && !appInstance.terminationPoint().isEmpty()) {
                    for (TerminationPoint terminationPoint : terminationPoint()) {
                        for (TerminationPoint terminationPoint2 : appInstance.terminationPoint()) {
                            TerminationPoint result = terminationPoint
            .processSubtreeFiltering(terminationPoint2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToTerminationPoint(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.terminationPoint() != null && !appInstance.terminationPoint().isEmpty()) {
                    for (TerminationPoint terminationPoint : appInstance.terminationPoint()) {
                        subTreeFilteringResultBuilder.addToTerminationPoint(terminationPoint);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultAugmentedNdNode.
     */
    protected DefaultAugmentedNdNode() {
    }

    /**
     * Returns the attribute AugmentedNdNodeBuilder.
     *
     * @return value of AugmentedNdNodeBuilder
     */
    public static AugmentedNdNodeBuilder builder() {
        return new AugmentedNdNodeBuilder();
    }


    /**
     * Represents the builder implementation of augmentedNdNode.
     */
    public static class AugmentedNdNodeBuilder implements AugmentedNdNode.AugmentedNdNodeBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<TerminationPoint> terminationPoint;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public List<TerminationPoint> terminationPoint() {
            return terminationPoint;
        }

        @Override
        public AugmentedNdNodeBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public AugmentedNdNodeBuilder terminationPoint(List<TerminationPoint> terminationPoint) {
            this.terminationPoint = terminationPoint;
            return this;
        }

        @Override
        public AugmentedNdNodeBuilder addToTerminationPoint(TerminationPoint value) {
            if (terminationPoint() == null) {
                terminationPoint(new ArrayList<>());
            }
            terminationPoint().add(value);
            return this;
        }
        @Override
        public AugmentedNdNode build() {
            return new DefaultAugmentedNdNode(this);
        }

        /**
         * Creates an instance of augmentedNdNodeBuilder.
         */
        public AugmentedNdNodeBuilder() {
        }
    }
}
