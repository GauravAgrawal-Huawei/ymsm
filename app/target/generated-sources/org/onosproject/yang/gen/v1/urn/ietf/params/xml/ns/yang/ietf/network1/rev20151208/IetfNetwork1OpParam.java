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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network1.rev20151208;

import com.google.common.base.MoreObjects;
import java.util.Objects;

/**
 * Represents the implementation of ietfNetwork1.
 */
public class IetfNetwork1OpParam implements IetfNetwork1 {

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
        if (obj instanceof IetfNetwork1OpParam) {
            IetfNetwork1OpParam other = (IetfNetwork1OpParam) obj;
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
     * Creates an instance of ietfNetwork1.
     *
     * @param builderObject builder object of ietfNetwork1
     */
    protected IetfNetwork1OpParam(IetfNetwork1Builder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public IetfNetwork1 processSubtreeFiltering(IetfNetwork1 appInstance, boolean isSelectAllSchemaChild) {
        IetfNetwork1Builder subTreeFilteringResultBuilder = new IetfNetwork1Builder();
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
    private boolean processChildNodesSubTreeFiltering(IetfNetwork1 appInstance, IetfNetwork1Builder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        return true;
    }


    /**
     * Creates an instance of ietfNetwork1OpParam.
     */
    protected IetfNetwork1OpParam() {
    }

    /**
     * Returns the attribute IetfNetwork1Builder.
     *
     * @return value of IetfNetwork1Builder
     */
    public static IetfNetwork1Builder builder() {
        return new IetfNetwork1Builder();
    }


    /**
     * Represents the builder implementation of ietfNetwork1.
     */
    public static class IetfNetwork1Builder {

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
        public IetfNetwork1Builder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        public IetfNetwork1 build() {
            return new IetfNetwork1OpParam(this);
        }

        /**
         * Creates an instance of ietfNetwork1Builder.
         */
        public IetfNetwork1Builder() {
        }
    }
}
