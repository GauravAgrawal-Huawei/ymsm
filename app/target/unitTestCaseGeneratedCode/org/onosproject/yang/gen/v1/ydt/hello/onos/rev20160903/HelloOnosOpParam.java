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

package org.onosproject.yang.gen.v1.ydt.hello.onos.rev20160903;

/**
 * Represents the implementation of helloOnos.
 */
public class HelloOnosOpParam implements HelloOnos {


    /**
     * Creates an instance of helloOnos.
     *
     * @param builderObject builder object of helloOnos
     */
    protected HelloOnosOpParam(HelloOnosBuilder builderObject) {
    }

    @Override
    public HelloOnos processSubtreeFiltering(HelloOnos appInstance, boolean isSelectAllSchemaChild) {
        HelloOnosBuilder subTreeFilteringResultBuilder = new HelloOnosBuilder();
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
    private boolean processChildNodesSubTreeFiltering(HelloOnos appInstance, HelloOnosBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        return true;
    }


    /**
     * Creates an instance of helloOnosOpParam.
     */
    protected HelloOnosOpParam() {
    }

    /**
     * Returns the attribute HelloOnosBuilder.
     *
     * @return value of HelloOnosBuilder
     */
    public static HelloOnosBuilder builder() {
        return new HelloOnosBuilder();
    }


    /**
     * Represents the builder implementation of helloOnos.
     */
    public static class HelloOnosBuilder {


        public HelloOnos build() {
            return new HelloOnosOpParam(this);
        }

        /**
         * Creates an instance of helloOnosBuilder.
         */
        public HelloOnosBuilder() {
        }
    }
}
