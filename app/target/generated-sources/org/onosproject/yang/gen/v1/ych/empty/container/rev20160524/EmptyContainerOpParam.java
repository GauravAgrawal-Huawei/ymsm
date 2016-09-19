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

package org.onosproject.yang.gen.v1.ych.empty.container.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.Objects;

/**
 * Represents the implementation of emptyContainer.
 */
public class EmptyContainerOpParam implements EmptyContainer {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected org.onosproject.yang.gen.v1.ych.empty.container.rev20160524.emptycontainer
            .EmptyContainer emptyContainer;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public org.onosproject.yang.gen.v1.ych.empty.container.rev20160524
            .emptycontainer.EmptyContainer emptyContainer() {
        return emptyContainer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, emptyContainer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EmptyContainerOpParam) {
            EmptyContainerOpParam other = (EmptyContainerOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(emptyContainer, other.emptyContainer);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("emptyContainer", emptyContainer)
            .toString();
    }

    /**
     * Creates an instance of emptyContainer.
     *
     * @param builderObject builder object of emptyContainer
     */
    protected EmptyContainerOpParam(EmptyContainerBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.emptyContainer = builderObject.emptyContainer();
    }

    @Override
    public EmptyContainer processSubtreeFiltering(EmptyContainer appInstance, boolean isSelectAllSchemaChild) {
        EmptyContainerBuilder subTreeFilteringResultBuilder = new EmptyContainerBuilder();
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
    private boolean processChildNodesSubTreeFiltering(EmptyContainer appInstance, EmptyContainerBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (emptyContainer()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.emptyContainer() != null) {
                org.onosproject.yang.gen.v1.ych.empty.container.rev20160524.emptycontainer
            .EmptyContainer result = emptyContainer.processSubtreeFiltering(appInstance.emptyContainer(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.emptyContainer(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of emptyContainerOpParam.
     */
    protected EmptyContainerOpParam() {
    }

    /**
     * Returns the attribute EmptyContainerBuilder.
     *
     * @return value of EmptyContainerBuilder
     */
    public static EmptyContainerBuilder builder() {
        return new EmptyContainerBuilder();
    }


    /**
     * Represents the builder implementation of emptyContainer.
     */
    public static class EmptyContainerBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected org.onosproject.yang.gen.v1.ych.empty.container.rev20160524.emptycontainer
            .EmptyContainer emptyContainer;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public org.onosproject.yang.gen.v1.ych.empty.container.rev20160524
            .emptycontainer.EmptyContainer emptyContainer() {
            return emptyContainer;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public EmptyContainerBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of emptyContainer.
         *
         * @param emptyContainer value of emptyContainer
         * @return builder object of emptyContainer
         */
        public EmptyContainerBuilder emptyContainer(org.onosproject.yang.gen.v1.ych.empty.container.rev20160524
            .emptycontainer.EmptyContainer emptyContainer) {
            this.emptyContainer = emptyContainer;
            return this;
        }

        public EmptyContainer build() {
            return new EmptyContainerOpParam(this);
        }

        /**
         * Creates an instance of emptyContainerBuilder.
         */
        public EmptyContainerBuilder() {
        }
    }
}
