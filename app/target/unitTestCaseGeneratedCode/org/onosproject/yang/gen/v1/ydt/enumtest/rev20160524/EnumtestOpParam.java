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

package org.onosproject.yang.gen.v1.ydt.enumtest.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.enumtest.rev20160524.enumtest.EnumList;

/**
 * Represents the implementation of enumtest.
 */
public class EnumtestOpParam implements Enumtest {

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


    protected List<EnumList> enumList = new ArrayList<>();
    /**
     * Returns the onosYangNodeOperationType.
     *
     * @return value of onosYangNodeOperationType
     */
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    public List<EnumList> enumList() {
        return enumList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(enumList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EnumtestOpParam) {
            EnumtestOpParam other = (EnumtestOpParam) obj;
            return
                 Objects.equals(enumList, other.enumList);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("enumList", enumList)
            .toString();
    }

    /**
     * Creates an instance of enumtest.
     *
     * @param builderObject builder object of enumtest
     */
    protected EnumtestOpParam(EnumtestBuilder builderObject) {
        this.enumList = builderObject.enumList();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public Enumtest processSubtreeFiltering(Enumtest appInstance, boolean isSelectAllSchemaChild) {
        EnumtestBuilder subTreeFilteringResultBuilder = new EnumtestBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Enumtest appInstance, EnumtestBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (EnumList enumList : appInstance.enumList()) {
                subTreeFilteringResultBuilder.addToEnumList(enumList);
            }
        } else if (enumList() != null) {
            isAnySelectOrContainmentNode = true;
            if (!enumList().isEmpty()) {
                if (appInstance.enumList() != null && !appInstance.enumList().isEmpty()) {
                    for (EnumList enumList : enumList()) {
                        for (EnumList enumList2 : appInstance.enumList()) {
                            EnumList result = enumList.processSubtreeFiltering(enumList2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToEnumList(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.enumList() != null && !appInstance.enumList().isEmpty()) {
                    for (EnumList enumList : appInstance.enumList()) {
                        subTreeFilteringResultBuilder.addToEnumList(enumList);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of enumtestOpParam.
     */
    protected EnumtestOpParam() {
    }

    /**
     * Returns the attribute EnumtestBuilder.
     *
     * @return value of EnumtestBuilder
     */
    public static EnumtestBuilder builder() {
        return new EnumtestBuilder();
    }


    /**
     * Represents the builder implementation of enumtest.
     */
    public static class EnumtestBuilder {

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        protected List<EnumList> enumList = new ArrayList<>();

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
        public EnumtestBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }

        public List<EnumList> enumList() {
            return enumList;
        }

        /**
         * Returns the builder object of enumList.
         *
         * @param enumList list of enumList
         * @return builder object of enumList
         */
        public EnumtestBuilder enumList(List<EnumList> enumList) {
            this.enumList = enumList;
            return this;
        }
        public EnumtestBuilder addToEnumList(EnumList value) {
            enumList().add(value);
            return this;
        }

        public Enumtest build() {
            return new EnumtestOpParam(this);
        }

        /**
         * Creates an instance of enumtestBuilder.
         */
        public EnumtestBuilder() {
        }
    }
}
