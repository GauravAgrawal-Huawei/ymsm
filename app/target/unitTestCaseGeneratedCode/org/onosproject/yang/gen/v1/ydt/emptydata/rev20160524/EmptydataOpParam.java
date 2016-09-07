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

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<EmptyList> emptyList;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public List<EmptyList> emptyList() {
        return emptyList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, emptyList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EmptydataOpParam) {
            EmptydataOpParam other = (EmptydataOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(emptyList, other.emptyList);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("emptyList", emptyList)
            .toString();
    }

    /**
     * Creates an instance of emptydata.
     *
     * @param builderObject builder object of emptydata
     */
    protected EmptydataOpParam(EmptydataBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.emptyList = builderObject.emptyList();
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

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<EmptyList> emptyList;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public List<EmptyList> emptyList() {
            return emptyList;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public EmptydataBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
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
            if (emptyList() == null) {
                emptyList(new ArrayList<>());
            }
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
