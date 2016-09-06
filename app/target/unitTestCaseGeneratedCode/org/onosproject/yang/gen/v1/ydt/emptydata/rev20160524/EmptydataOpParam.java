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

package org.onosproject.yang.gen.v1.ydt.emptydata.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.emptydata.rev20160524.emptydata.EmptyList;

/**
 * Represents the implementation of emptydata.
 */
public class EmptydataOpParam implements Emptydata {

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


    protected List<EmptyList> emptyList = new ArrayList<>();
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    public List<EmptyList> emptyList() {
        return emptyList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(emptyList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EmptydataOpParam) {
            EmptydataOpParam other = (EmptydataOpParam) obj;
            return
                 Objects.equals(emptyList, other.emptyList);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("emptyList", emptyList)
            .toString();
    }

    /**
     * Creates an instance of emptydata.
     *
     * @param builderObject builder object of emptydata
     */
    protected EmptydataOpParam(EmptydataBuilder builderObject) {
        this.emptyList = builderObject.emptyList();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public Emptydata processSubtreeFiltering(Emptydata appInstance, boolean isSelectAllSchemaChild) {
        EmptydataBuilder subTreeFilteringResultBuilder = new EmptydataBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Emptydata appInstance, EmptydataBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (EmptyList emptyList : appInstance.emptyList()) {
                subTreeFilteringResultBuilder.addToEmptyList(emptyList);
            }
        } else if (emptyList() != null) {
            isAnySelectOrContainmentNode = true;
            if (!emptyList().isEmpty()) {
                if (appInstance.emptyList() != null && !appInstance.emptyList().isEmpty()) {
                    for (EmptyList emptyList : emptyList()) {
                        for (EmptyList emptyList2 : appInstance.emptyList()) {
                            EmptyList result = emptyList.processSubtreeFiltering(emptyList2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToEmptyList(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.emptyList() != null && !appInstance.emptyList().isEmpty()) {
                    for (EmptyList emptyList : appInstance.emptyList()) {
                        subTreeFilteringResultBuilder.addToEmptyList(emptyList);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of emptydataOpParam.
     */
    protected EmptydataOpParam() {
    }

    /**
     * Returns the attribute EmptydataBuilder.
     *
     * @return value of EmptydataBuilder
     */
    public static EmptydataBuilder builder() {
        return new EmptydataBuilder();
    }


    /**
     * Represents the builder implementation of emptydata.
     */
    public static class EmptydataBuilder {

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected List<EmptyList> emptyList = new ArrayList<>();

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
        public EmptydataBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }

        public List<EmptyList> emptyList() {
            return emptyList;
        }

        /**
         * Returns the builder object of emptyList.
         *
         * @param emptyList list of emptyList
         * @return builder object of emptyList
         */
        public EmptydataBuilder emptyList(List<EmptyList> emptyList) {
            this.emptyList = emptyList;
            return this;
        }
        public EmptydataBuilder addToEmptyList(EmptyList value) {
            emptyList().add(value);
            return this;
        }

        public Emptydata build() {
            return new EmptydataOpParam(this);
        }

        /**
         * Creates an instance of emptydataBuilder.
         */
        public EmptydataBuilder() {
        }
    }
}
