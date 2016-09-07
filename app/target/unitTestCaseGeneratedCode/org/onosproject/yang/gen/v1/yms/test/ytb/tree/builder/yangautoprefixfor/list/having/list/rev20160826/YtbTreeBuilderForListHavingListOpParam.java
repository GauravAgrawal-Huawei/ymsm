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

package org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826
            .ytbtreebuilderforlisthavinglist.Carrier;

/**
 * Represents the implementation of ytbTreeBuilderForListHavingList.
 */
public class YtbTreeBuilderForListHavingListOpParam implements YtbTreeBuilderForListHavingList {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected Carrier carrier;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public Carrier carrier() {
        return carrier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, carrier);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbTreeBuilderForListHavingListOpParam) {
            YtbTreeBuilderForListHavingListOpParam other = (YtbTreeBuilderForListHavingListOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(carrier, other.carrier);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("carrier", carrier)
            .toString();
    }

    /**
     * Creates an instance of ytbTreeBuilderForListHavingList.
     *
     * @param builderObject builder object of ytbTreeBuilderForListHavingList
     */
    protected YtbTreeBuilderForListHavingListOpParam(YtbTreeBuilderForListHavingListBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.carrier = builderObject.carrier();
    }

    @Override
    public YtbTreeBuilderForListHavingList processSubtreeFiltering(YtbTreeBuilderForListHavingList appInstance,
            boolean
            isSelectAllSchemaChild) {
        YtbTreeBuilderForListHavingListBuilder subTreeFilteringResultBuilder = new
            YtbTreeBuilderForListHavingListBuilder();
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
    private boolean processChildNodesSubTreeFiltering(YtbTreeBuilderForListHavingList appInstance,
            YtbTreeBuilderForListHavingListBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (carrier()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.carrier() != null) {
                Carrier result = carrier.processSubtreeFiltering(appInstance.carrier(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.carrier(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ytbTreeBuilderForListHavingListOpParam.
     */
    protected YtbTreeBuilderForListHavingListOpParam() {
    }

    /**
     * Returns the attribute YtbTreeBuilderForListHavingListBuilder.
     *
     * @return value of YtbTreeBuilderForListHavingListBuilder
     */
    public static YtbTreeBuilderForListHavingListBuilder builder() {
        return new YtbTreeBuilderForListHavingListBuilder();
    }


    /**
     * Represents the builder implementation of ytbTreeBuilderForListHavingList.
     */
    public static class YtbTreeBuilderForListHavingListBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected Carrier carrier;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public Carrier carrier() {
            return carrier;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public YtbTreeBuilderForListHavingListBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of carrier.
         *
         * @param carrier value of carrier
         * @return builder object of carrier
         */
        public YtbTreeBuilderForListHavingListBuilder carrier(Carrier carrier) {
            this.carrier = carrier;
            return this;
        }

        public YtbTreeBuilderForListHavingList build() {
            return new YtbTreeBuilderForListHavingListOpParam(this);
        }

        /**
         * Creates an instance of ytbTreeBuilderForListHavingListBuilder.
         */
        public YtbTreeBuilderForListHavingListBuilder() {
        }
    }
}
