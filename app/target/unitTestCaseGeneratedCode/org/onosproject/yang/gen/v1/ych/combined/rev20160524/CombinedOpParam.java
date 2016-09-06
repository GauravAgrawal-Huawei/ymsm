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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.Attributes;

/**
 * Represents the implementation of combined.
 */
public class CombinedOpParam implements Combined {

    protected Attributes attributes;

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

    public Attributes attributes() {
        return attributes;
    }
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }


    @Override
    public int hashCode() {
        return Objects.hash(attributes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CombinedOpParam) {
            CombinedOpParam other = (CombinedOpParam) obj;
            return
                 Objects.equals(attributes, other.attributes);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("attributes", attributes)
            .toString();
    }

    /**
     * Creates an instance of combined.
     *
     * @param builderObject builder object of combined
     */
    protected CombinedOpParam(CombinedBuilder builderObject) {
        this.attributes = builderObject.attributes();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public Combined processSubtreeFiltering(Combined appInstance, boolean isSelectAllSchemaChild) {
        CombinedBuilder subTreeFilteringResultBuilder = new CombinedBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Combined appInstance, CombinedBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (attributes()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.attributes() != null) {
                Attributes result = attributes.processSubtreeFiltering(appInstance.attributes(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.attributes(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of combinedOpParam.
     */
    protected CombinedOpParam() {
    }

    /**
     * Returns the attribute CombinedBuilder.
     *
     * @return value of CombinedBuilder
     */
    public static CombinedBuilder builder() {
        return new CombinedBuilder();
    }


    /**
     * Represents the builder implementation of combined.
     */
    public static class CombinedBuilder {

        protected Attributes attributes;

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        public Attributes attributes() {
            return attributes;
        }

        /**
         * Returns the builder object of attributes.
         *
         * @param attributes value of attributes
         * @return builder object of attributes
         */
        public CombinedBuilder attributes(Attributes attributes) {
            this.attributes = attributes;
            return this;
        }
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
        public CombinedBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        public Combined build() {
            return new CombinedOpParam(this);
        }

        /**
         * Creates an instance of combinedBuilder.
         */
        public CombinedBuilder() {
        }
    }
}
