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

package org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.rootlist.Listwithcontainer;
import org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.rootlist.Listwithoutcontainer;

/**
 * Represents the implementation of rootlist.
 */
public class RootlistOpParam implements Rootlist {

    protected List<Listwithoutcontainer> listwithoutcontainer = new ArrayList<>();
    protected List<Listwithcontainer> listwithcontainer = new ArrayList<>();

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

    public List<Listwithoutcontainer> listwithoutcontainer() {
        return listwithoutcontainer;
    }
    public List<Listwithcontainer> listwithcontainer() {
        return listwithcontainer;
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
        return Objects.hash(listwithoutcontainer, listwithcontainer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RootlistOpParam) {
            RootlistOpParam other = (RootlistOpParam) obj;
            return
                 Objects.equals(listwithoutcontainer, other.listwithoutcontainer) &&
                 Objects.equals(listwithcontainer, other.listwithcontainer);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("listwithoutcontainer", listwithoutcontainer)
            .add("listwithcontainer", listwithcontainer)
            .toString();
    }

    /**
     * Creates an instance of rootlist.
     *
     * @param builderObject builder object of rootlist
     */
    protected RootlistOpParam(RootlistBuilder builderObject) {
        this.listwithoutcontainer = builderObject.listwithoutcontainer();
        this.listwithcontainer = builderObject.listwithcontainer();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public Rootlist processSubtreeFiltering(Rootlist appInstance, boolean isSelectAllSchemaChild) {
        RootlistBuilder subTreeFilteringResultBuilder = new RootlistBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Rootlist appInstance, RootlistBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Listwithoutcontainer listwithoutcontainer : appInstance.listwithoutcontainer()) {
                subTreeFilteringResultBuilder.addToListwithoutcontainer(listwithoutcontainer);
            }
        } else if (listwithoutcontainer() != null) {
            isAnySelectOrContainmentNode = true;
            if (!listwithoutcontainer().isEmpty()) {
                if (appInstance.listwithoutcontainer() != null && !appInstance.listwithoutcontainer().isEmpty()) {
                    for (Listwithoutcontainer listwithoutcontainer : listwithoutcontainer()) {
                        for (Listwithoutcontainer listwithoutcontainer2 : appInstance.listwithoutcontainer()) {
                            Listwithoutcontainer result = listwithoutcontainer
            .processSubtreeFiltering(listwithoutcontainer2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToListwithoutcontainer(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.listwithoutcontainer() != null && !appInstance.listwithoutcontainer().isEmpty()) {
                    for (Listwithoutcontainer listwithoutcontainer : appInstance.listwithoutcontainer()) {
                        subTreeFilteringResultBuilder.addToListwithoutcontainer(listwithoutcontainer);
                    }
                }
            }
        }

        if (isSelectAllSchemaChild) {
            for (Listwithcontainer listwithcontainer : appInstance.listwithcontainer()) {
                subTreeFilteringResultBuilder.addToListwithcontainer(listwithcontainer);
            }
        } else if (listwithcontainer() != null) {
            isAnySelectOrContainmentNode = true;
            if (!listwithcontainer().isEmpty()) {
                if (appInstance.listwithcontainer() != null && !appInstance.listwithcontainer().isEmpty()) {
                    for (Listwithcontainer listwithcontainer : listwithcontainer()) {
                        for (Listwithcontainer listwithcontainer2 : appInstance.listwithcontainer()) {
                            Listwithcontainer result = listwithcontainer
            .processSubtreeFiltering(listwithcontainer2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToListwithcontainer(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.listwithcontainer() != null && !appInstance.listwithcontainer().isEmpty()) {
                    for (Listwithcontainer listwithcontainer : appInstance.listwithcontainer()) {
                        subTreeFilteringResultBuilder.addToListwithcontainer(listwithcontainer);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of rootlistOpParam.
     */
    protected RootlistOpParam() {
    }

    /**
     * Returns the attribute RootlistBuilder.
     *
     * @return value of RootlistBuilder
     */
    public static RootlistBuilder builder() {
        return new RootlistBuilder();
    }


    /**
     * Represents the builder implementation of rootlist.
     */
    public static class RootlistBuilder {

        protected List<Listwithoutcontainer> listwithoutcontainer = new ArrayList<>();
        protected List<Listwithcontainer> listwithcontainer = new ArrayList<>();

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        public List<Listwithoutcontainer> listwithoutcontainer() {
            return listwithoutcontainer;
        }
        public List<Listwithcontainer> listwithcontainer() {
            return listwithcontainer;
        }

        /**
         * Returns the builder object of listwithoutcontainer.
         *
         * @param listwithoutcontainer list of listwithoutcontainer
         * @return builder object of listwithoutcontainer
         */
        public RootlistBuilder listwithoutcontainer(List<Listwithoutcontainer> listwithoutcontainer) {
            this.listwithoutcontainer = listwithoutcontainer;
            return this;
        }

        /**
         * Returns the builder object of listwithcontainer.
         *
         * @param listwithcontainer list of listwithcontainer
         * @return builder object of listwithcontainer
         */
        public RootlistBuilder listwithcontainer(List<Listwithcontainer> listwithcontainer) {
            this.listwithcontainer = listwithcontainer;
            return this;
        }
        public RootlistBuilder addToListwithoutcontainer(Listwithoutcontainer value) {
            listwithoutcontainer().add(value);
            return this;
        }
        public RootlistBuilder addToListwithcontainer(Listwithcontainer value) {
            listwithcontainer().add(value);
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
        public RootlistBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        public Rootlist build() {
            return new RootlistOpParam(this);
        }

        /**
         * Creates an instance of rootlistBuilder.
         */
        public RootlistBuilder() {
        }
    }
}
