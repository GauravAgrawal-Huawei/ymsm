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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.rpc.response.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.Objects;

/**
 * Represents the implementation of ytbSimpleRpcResponse.
 */
public class YtbSimpleRpcResponseOpParam implements YtbSimpleRpcResponse {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbSimpleRpcResponseOpParam) {
            YtbSimpleRpcResponseOpParam other = (YtbSimpleRpcResponseOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .toString();
    }

    /**
     * Creates an instance of ytbSimpleRpcResponse.
     *
     * @param builderObject builder object of ytbSimpleRpcResponse
     */
    protected YtbSimpleRpcResponseOpParam(YtbSimpleRpcResponseBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public YtbSimpleRpcResponse processSubtreeFiltering(YtbSimpleRpcResponse appInstance, boolean
            isSelectAllSchemaChild) {
        YtbSimpleRpcResponseBuilder subTreeFilteringResultBuilder = new YtbSimpleRpcResponseBuilder();
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
    private boolean processChildNodesSubTreeFiltering(YtbSimpleRpcResponse appInstance, YtbSimpleRpcResponseBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        return true;
    }


    /**
     * Creates an instance of ytbSimpleRpcResponseOpParam.
     */
    protected YtbSimpleRpcResponseOpParam() {
    }

    /**
     * Returns the attribute YtbSimpleRpcResponseBuilder.
     *
     * @return value of YtbSimpleRpcResponseBuilder
     */
    public static YtbSimpleRpcResponseBuilder builder() {
        return new YtbSimpleRpcResponseBuilder();
    }


    /**
     * Represents the builder implementation of ytbSimpleRpcResponse.
     */
    public static class YtbSimpleRpcResponseBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public YtbSimpleRpcResponseBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        public YtbSimpleRpcResponse build() {
            return new YtbSimpleRpcResponseOpParam(this);
        }

        /**
         * Creates an instance of ytbSimpleRpcResponseBuilder.
         */
        public YtbSimpleRpcResponseBuilder() {
        }
    }
}
