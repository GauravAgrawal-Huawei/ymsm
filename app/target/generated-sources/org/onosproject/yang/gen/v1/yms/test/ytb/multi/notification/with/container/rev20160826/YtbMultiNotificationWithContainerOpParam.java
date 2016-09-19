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

package org.onosproject.yang.gen.v1.yms.test.ytb.multi.notification.with.container.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.Objects;

/**
 * Represents the implementation of ytbMultiNotificationWithContainer.
 */
public class YtbMultiNotificationWithContainerOpParam implements YtbMultiNotificationWithContainer {

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
        if (obj instanceof YtbMultiNotificationWithContainerOpParam) {
            YtbMultiNotificationWithContainerOpParam other = (YtbMultiNotificationWithContainerOpParam) obj;
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
     * Creates an instance of ytbMultiNotificationWithContainer.
     *
     * @param builderObject builder object of ytbMultiNotificationWithContainer
     */
    protected YtbMultiNotificationWithContainerOpParam(YtbMultiNotificationWithContainerBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public YtbMultiNotificationWithContainer processSubtreeFiltering(YtbMultiNotificationWithContainer appInstance,
            boolean
            isSelectAllSchemaChild) {
        YtbMultiNotificationWithContainerBuilder subTreeFilteringResultBuilder = new
            YtbMultiNotificationWithContainerBuilder();
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
    private boolean processChildNodesSubTreeFiltering(YtbMultiNotificationWithContainer appInstance,
            YtbMultiNotificationWithContainerBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        return true;
    }


    /**
     * Creates an instance of ytbMultiNotificationWithContainerOpParam.
     */
    protected YtbMultiNotificationWithContainerOpParam() {
    }

    /**
     * Returns the attribute YtbMultiNotificationWithContainerBuilder.
     *
     * @return value of YtbMultiNotificationWithContainerBuilder
     */
    public static YtbMultiNotificationWithContainerBuilder builder() {
        return new YtbMultiNotificationWithContainerBuilder();
    }


    /**
     * Represents the builder implementation of ytbMultiNotificationWithContainer.
     */
    public static class YtbMultiNotificationWithContainerBuilder {

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
        public YtbMultiNotificationWithContainerBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        public YtbMultiNotificationWithContainer build() {
            return new YtbMultiNotificationWithContainerOpParam(this);
        }

        /**
         * Creates an instance of ytbMultiNotificationWithContainerBuilder.
         */
        public YtbMultiNotificationWithContainerBuilder() {
        }
    }
}
