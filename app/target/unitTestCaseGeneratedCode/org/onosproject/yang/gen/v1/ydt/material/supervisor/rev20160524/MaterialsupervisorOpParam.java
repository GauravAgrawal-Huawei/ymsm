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

package org.onosproject.yang.gen.v1.ydt.material.supervisor.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.material.supervisor.rev20160524.materialsupervisor.Supervisor;

/**
 * Represents the implementation of materialsupervisor.
 */
public class MaterialsupervisorOpParam implements Materialsupervisor {

    protected List<Supervisor> supervisor = new ArrayList<>();

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

    public List<Supervisor> supervisor() {
        return supervisor;
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
        return Objects.hash(supervisor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MaterialsupervisorOpParam) {
            MaterialsupervisorOpParam other = (MaterialsupervisorOpParam) obj;
            return
                 Objects.equals(supervisor, other.supervisor);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("supervisor", supervisor)
            .toString();
    }

    /**
     * Creates an instance of materialsupervisor.
     *
     * @param builderObject builder object of materialsupervisor
     */
    protected MaterialsupervisorOpParam(MaterialsupervisorBuilder builderObject) {
        this.supervisor = builderObject.supervisor();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
    }

    @Override
    public Materialsupervisor processSubtreeFiltering(Materialsupervisor appInstance, boolean
            isSelectAllSchemaChild) {
        MaterialsupervisorBuilder subTreeFilteringResultBuilder = new MaterialsupervisorBuilder();
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
    private boolean processChildNodesSubTreeFiltering(Materialsupervisor appInstance, MaterialsupervisorBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Supervisor supervisor : appInstance.supervisor()) {
                subTreeFilteringResultBuilder.addToSupervisor(supervisor);
            }
        } else if (supervisor() != null) {
            isAnySelectOrContainmentNode = true;
            if (!supervisor().isEmpty()) {
                if (appInstance.supervisor() != null && !appInstance.supervisor().isEmpty()) {
                    for (Supervisor supervisor : supervisor()) {
                        for (Supervisor supervisor2 : appInstance.supervisor()) {
                            Supervisor result = supervisor.processSubtreeFiltering(supervisor2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToSupervisor(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.supervisor() != null && !appInstance.supervisor().isEmpty()) {
                    for (Supervisor supervisor : appInstance.supervisor()) {
                        subTreeFilteringResultBuilder.addToSupervisor(supervisor);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of materialsupervisorOpParam.
     */
    protected MaterialsupervisorOpParam() {
    }

    /**
     * Returns the attribute MaterialsupervisorBuilder.
     *
     * @return value of MaterialsupervisorBuilder
     */
    public static MaterialsupervisorBuilder builder() {
        return new MaterialsupervisorBuilder();
    }


    /**
     * Represents the builder implementation of materialsupervisor.
     */
    public static class MaterialsupervisorBuilder {

        protected List<Supervisor> supervisor = new ArrayList<>();

        /**
         * Specify the node specific operation in protocols like NETCONF.
         * Applicable in protocol edit operation, will be ignored in query operation
         */
        private OnosYangNodeOperationType onosYangNodeOperationType;


        public List<Supervisor> supervisor() {
            return supervisor;
        }

        /**
         * Returns the builder object of supervisor.
         *
         * @param supervisor list of supervisor
         * @return builder object of supervisor
         */
        public MaterialsupervisorBuilder supervisor(List<Supervisor> supervisor) {
            this.supervisor = supervisor;
            return this;
        }
        public MaterialsupervisorBuilder addToSupervisor(Supervisor value) {
            supervisor().add(value);
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
        public MaterialsupervisorBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
           this.onosYangNodeOperationType = onosYangNodeOperationType;
           return this;
        }


        public Materialsupervisor build() {
            return new MaterialsupervisorOpParam(this);
        }

        /**
         * Creates an instance of materialsupervisorBuilder.
         */
        public MaterialsupervisorBuilder() {
        }
    }
}
