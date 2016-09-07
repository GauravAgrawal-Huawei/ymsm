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

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<Listwithoutcontainer> listwithoutcontainer;
    protected List<Listwithcontainer> listwithcontainer;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public List<Listwithoutcontainer> listwithoutcontainer() {
        return listwithoutcontainer;
    }
    public List<Listwithcontainer> listwithcontainer() {
        return listwithcontainer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, listwithoutcontainer, listwithcontainer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RootlistOpParam) {
            RootlistOpParam other = (RootlistOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(listwithoutcontainer, other.listwithoutcontainer) &&
                 Objects.equals(listwithcontainer, other.listwithcontainer);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
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
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.listwithoutcontainer = builderObject.listwithoutcontainer();
        this.listwithcontainer = builderObject.listwithcontainer();
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

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<Listwithoutcontainer> listwithoutcontainer;
        protected List<Listwithcontainer> listwithcontainer;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public List<Listwithoutcontainer> listwithoutcontainer() {
            return listwithoutcontainer;
        }
        public List<Listwithcontainer> listwithcontainer() {
            return listwithcontainer;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public RootlistBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
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
            if (listwithoutcontainer() == null) {
                listwithoutcontainer(new ArrayList<>());
            }
            listwithoutcontainer().add(value);
            return this;
        }
        public RootlistBuilder addToListwithcontainer(Listwithcontainer value) {
            if (listwithcontainer() == null) {
                listwithcontainer(new ArrayList<>());
            }
            listwithcontainer().add(value);
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
