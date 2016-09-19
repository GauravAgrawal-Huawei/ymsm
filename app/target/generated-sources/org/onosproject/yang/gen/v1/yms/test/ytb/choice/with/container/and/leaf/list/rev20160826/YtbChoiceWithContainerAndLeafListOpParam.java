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

package org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.BitSet;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826
            .ytbchoicewithcontainerandleaflist.ContentTest;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826
            .ytbchoicewithcontainerandleaflist.CurrentValue;

/**
 * Represents the implementation of ytbChoiceWithContainerAndLeafList.
 */
public class YtbChoiceWithContainerAndLeafListOpParam implements YtbChoiceWithContainerAndLeafList {

    protected byte[] refer;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected ContentTest contentTest;
    protected CurrentValue currentValue;
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();
    public byte[] refer() {
        return refer;
    }
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public ContentTest contentTest() {
        return contentTest;
    }
    public CurrentValue currentValue() {
        return currentValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(refer, onosYangNodeOperationType, contentTest, currentValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbChoiceWithContainerAndLeafListOpParam) {
            YtbChoiceWithContainerAndLeafListOpParam other = (YtbChoiceWithContainerAndLeafListOpParam) obj;
            return
                 Objects.equals(refer, other.refer) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(contentTest, other.contentTest) &&
                 Objects.equals(currentValue, other.currentValue);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("refer", refer)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("contentTest", contentTest)
            .add("currentValue", currentValue)
            .toString();
    }

    /**
     * Creates an instance of ytbChoiceWithContainerAndLeafList.
     *
     * @param builderObject builder object of ytbChoiceWithContainerAndLeafList
     */
    protected YtbChoiceWithContainerAndLeafListOpParam(YtbChoiceWithContainerAndLeafListBuilder builderObject) {
        this.refer = builderObject.refer();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.contentTest = builderObject.contentTest();
        this.currentValue = builderObject.currentValue();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public YtbChoiceWithContainerAndLeafList processSubtreeFiltering(YtbChoiceWithContainerAndLeafList appInstance,
            boolean
            isSelectAllSchemaChild) {
        YtbChoiceWithContainerAndLeafListBuilder subTreeFilteringResultBuilder = new
            YtbChoiceWithContainerAndLeafListBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
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
    private boolean processLeafSubtreeFiltering(YtbChoiceWithContainerAndLeafList appInstance,
            YtbChoiceWithContainerAndLeafListBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.REFER.getLeafIndex())) {
            if (appInstance.refer() == null || !(refer().equals(appInstance.refer()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.refer(appInstance.refer());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.REFER.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.refer(appInstance.refer());
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(YtbChoiceWithContainerAndLeafList appInstance,
            YtbChoiceWithContainerAndLeafListBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (contentTest()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.contentTest() != null) {
                ContentTest result = contentTest.processSubtreeFiltering(appInstance.contentTest(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.contentTest(result);
                }
            }
        }

        if (currentValue()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.currentValue() != null) {
                CurrentValue result = currentValue.processSubtreeFiltering(appInstance.currentValue(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.currentValue(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ytbChoiceWithContainerAndLeafListOpParam.
     */
    protected YtbChoiceWithContainerAndLeafListOpParam() {
    }

    /**
     * Returns the attribute YtbChoiceWithContainerAndLeafListBuilder.
     *
     * @return value of YtbChoiceWithContainerAndLeafListBuilder
     */
    public static YtbChoiceWithContainerAndLeafListBuilder builder() {
        return new YtbChoiceWithContainerAndLeafListBuilder();
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
     * Represents the builder implementation of ytbChoiceWithContainerAndLeafList.
     */
    public static class YtbChoiceWithContainerAndLeafListBuilder {

        protected byte[] refer;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected ContentTest contentTest;
        protected CurrentValue currentValue;
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();

        public byte[] refer() {
            return refer;
        }
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public ContentTest contentTest() {
            return contentTest;
        }
        public CurrentValue currentValue() {
            return currentValue;
        }

        /**
         * Returns the builder object of refer.
         *
         * @param refer value of refer
         * @return builder object of refer
         */
        public YtbChoiceWithContainerAndLeafListBuilder refer(byte[] refer) {
            getValueLeafFlags().set(LeafIdentifier.REFER.getLeafIndex());
            this.refer = refer;
            return this;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public YtbChoiceWithContainerAndLeafListBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of contentTest.
         *
         * @param contentTest value of contentTest
         * @return builder object of contentTest
         */
        public YtbChoiceWithContainerAndLeafListBuilder contentTest(ContentTest contentTest) {
            this.contentTest = contentTest;
            return this;
        }

        /**
         * Returns the builder object of currentValue.
         *
         * @param currentValue value of currentValue
         * @return builder object of currentValue
         */
        public YtbChoiceWithContainerAndLeafListBuilder currentValue(CurrentValue currentValue) {
            this.currentValue = currentValue;
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



        public YtbChoiceWithContainerAndLeafListBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public YtbChoiceWithContainerAndLeafList build() {
            return new YtbChoiceWithContainerAndLeafListOpParam(this);
        }

        /**
         * Creates an instance of ytbChoiceWithContainerAndLeafListBuilder.
         */
        public YtbChoiceWithContainerAndLeafListBuilder() {
        }
    }
}
