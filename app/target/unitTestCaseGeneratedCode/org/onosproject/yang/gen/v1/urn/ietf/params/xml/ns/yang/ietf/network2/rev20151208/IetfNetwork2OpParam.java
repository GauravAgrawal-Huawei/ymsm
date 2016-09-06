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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network2.rev20151208;

/**
 * Represents the implementation of ietfNetwork2.
 */
public class IetfNetwork2OpParam implements IetfNetwork2 {


    /**
     * Creates an instance of ietfNetwork2.
     *
     * @param builderObject builder object of ietfNetwork2
     */
    protected IetfNetwork2OpParam(IetfNetwork2Builder builderObject) {
    }

    @Override
    public IetfNetwork2 processSubtreeFiltering(IetfNetwork2 appInstance, boolean isSelectAllSchemaChild) {
        IetfNetwork2Builder subTreeFilteringResultBuilder = new IetfNetwork2Builder();
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
    private boolean processChildNodesSubTreeFiltering(IetfNetwork2 appInstance, IetfNetwork2Builder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        return true;
    }


    /**
     * Creates an instance of ietfNetwork2OpParam.
     */
    protected IetfNetwork2OpParam() {
    }

    /**
     * Returns the attribute IetfNetwork2Builder.
     *
     * @return value of IetfNetwork2Builder
     */
    public static IetfNetwork2Builder builder() {
        return new IetfNetwork2Builder();
    }


    /**
     * Represents the builder implementation of ietfNetwork2.
     */
    public static class IetfNetwork2Builder {


        public IetfNetwork2 build() {
            return new IetfNetwork2OpParam(this);
        }

        /**
         * Creates an instance of ietfNetwork2Builder.
         */
        public IetfNetwork2Builder() {
        }
    }
}
