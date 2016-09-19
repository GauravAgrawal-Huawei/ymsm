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

package org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput.activatesoftwareimage.output;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826
            .ytbaugmentforrpcinput
            .activatesoftwareimage.output.augmentedrpcoutput.Selection;

/**
 * Represents the implementation of augmentedRpcOutput.
 */
public class DefaultAugmentedRpcOutput implements AugmentedRpcOutput {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected Selection selection;

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public Selection selection() {
        return selection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, selection);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultAugmentedRpcOutput) {
            DefaultAugmentedRpcOutput other = (DefaultAugmentedRpcOutput) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(selection, other.selection);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("selection", selection)
            .toString();
    }

    /**
     * Creates an instance of augmentedRpcOutput.
     *
     * @param builderObject builder object of augmentedRpcOutput
     */
    protected DefaultAugmentedRpcOutput(AugmentedRpcOutputBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.selection = builderObject.selection();
    }

    @Override
    public AugmentedRpcOutput processSubtreeFiltering(AugmentedRpcOutput appInstance, boolean
            isSelectAllSchemaChild) {
        AugmentedRpcOutputBuilder subTreeFilteringResultBuilder = new AugmentedRpcOutputBuilder();
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
    private boolean processChildNodesSubTreeFiltering(AugmentedRpcOutput appInstance, AugmentedRpcOutputBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (selection()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.selection() != null) {
                Selection result = selection.processSubtreeFiltering(appInstance.selection(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.selection(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultAugmentedRpcOutput.
     */
    protected DefaultAugmentedRpcOutput() {
    }

    /**
     * Returns the attribute AugmentedRpcOutputBuilder.
     *
     * @return value of AugmentedRpcOutputBuilder
     */
    public static AugmentedRpcOutputBuilder builder() {
        return new AugmentedRpcOutputBuilder();
    }


    /**
     * Represents the builder implementation of augmentedRpcOutput.
     */
    public static class AugmentedRpcOutputBuilder implements AugmentedRpcOutput.AugmentedRpcOutputBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected Selection selection;


        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public Selection selection() {
            return selection;
        }

        @Override
        public AugmentedRpcOutputBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public AugmentedRpcOutputBuilder selection(Selection selection) {
            this.selection = selection;
            return this;
        }
        @Override
        public AugmentedRpcOutput build() {
            return new DefaultAugmentedRpcOutput(this);
        }

        /**
         * Creates an instance of augmentedRpcOutputBuilder.
         */
        public AugmentedRpcOutputBuilder() {
        }
    }
}
