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

package org.onosproject.yang.gen.v1.ydt.root.rev20160524;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.root.rev20160524.logisticsmanager.MaterialSupervisor;
import org.onosproject.yang.gen.v1.ydt.root.rev20160524.logisticsmanager.PurchasingSupervisor;

/**
 * Represents the implementation of logisticsManager.
 */
public class LogisticsManagerOpParam implements LogisticsManager {

    protected String customsSupervisor;
    protected String merchandiserSupervisor;
    protected String tradingSupervisor;
    protected List<String> warehouseSupervisor;
    protected List<String> employeeId;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<MaterialSupervisor> materialSupervisor;
    protected PurchasingSupervisor purchasingSupervisor;
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();
    public String customsSupervisor() {
        return customsSupervisor;
    }
    public String merchandiserSupervisor() {
        return merchandiserSupervisor;
    }
    public String tradingSupervisor() {
        return tradingSupervisor;
    }
    public List<String> warehouseSupervisor() {
        return warehouseSupervisor;
    }
    public List<String> employeeId() {
        return employeeId;
    }
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public List<MaterialSupervisor> materialSupervisor() {
        return materialSupervisor;
    }
    public PurchasingSupervisor purchasingSupervisor() {
        return purchasingSupervisor;
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(customsSupervisor, merchandiserSupervisor, tradingSupervisor, warehouseSupervisor, employeeId,
            onosYangNodeOperationType, materialSupervisor, purchasingSupervisor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LogisticsManagerOpParam) {
            LogisticsManagerOpParam other = (LogisticsManagerOpParam) obj;
            return
                 Objects.equals(customsSupervisor, other.customsSupervisor) &&
                 Objects.equals(merchandiserSupervisor, other.merchandiserSupervisor) &&
                 Objects.equals(tradingSupervisor, other.tradingSupervisor) &&
                 Objects.equals(warehouseSupervisor, other.warehouseSupervisor) &&
                 Objects.equals(employeeId, other.employeeId) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(materialSupervisor, other.materialSupervisor) &&
                 Objects.equals(purchasingSupervisor, other.purchasingSupervisor);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("customsSupervisor", customsSupervisor)
            .add("merchandiserSupervisor", merchandiserSupervisor)
            .add("tradingSupervisor", tradingSupervisor)
            .add("warehouseSupervisor", warehouseSupervisor)
            .add("employeeId", employeeId)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("materialSupervisor", materialSupervisor)
            .add("purchasingSupervisor", purchasingSupervisor)
            .toString();
    }

    /**
     * Creates an instance of logisticsManager.
     *
     * @param builderObject builder object of logisticsManager
     */
    protected LogisticsManagerOpParam(LogisticsManagerBuilder builderObject) {
        this.customsSupervisor = builderObject.customsSupervisor();
        this.merchandiserSupervisor = builderObject.merchandiserSupervisor();
        this.tradingSupervisor = builderObject.tradingSupervisor();
        this.warehouseSupervisor = builderObject.warehouseSupervisor();
        this.employeeId = builderObject.employeeId();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.materialSupervisor = builderObject.materialSupervisor();
        this.purchasingSupervisor = builderObject.purchasingSupervisor();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
    }

    @Override
    public LogisticsManager processSubtreeFiltering(LogisticsManager appInstance, boolean isSelectAllSchemaChild) {
        LogisticsManagerBuilder subTreeFilteringResultBuilder = new LogisticsManagerBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!processLeafListSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!processChildNodesSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processLeafSubtreeFiltering(LogisticsManager appInstance, LogisticsManagerBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.CUSTOMSSUPERVISOR.getLeafIndex())) {
            if (appInstance.customsSupervisor() == null || !(customsSupervisor()
            .equals(appInstance.customsSupervisor()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.customsSupervisor(appInstance.customsSupervisor());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .CUSTOMSSUPERVISOR.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.customsSupervisor(appInstance.customsSupervisor());
        }

        if (getValueLeafFlags().get(LeafIdentifier.MERCHANDISERSUPERVISOR.getLeafIndex())) {
            if (appInstance.merchandiserSupervisor() == null || !(merchandiserSupervisor()
            .equals(appInstance.merchandiserSupervisor()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.merchandiserSupervisor(appInstance.merchandiserSupervisor());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .MERCHANDISERSUPERVISOR.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.merchandiserSupervisor(appInstance.merchandiserSupervisor());
        }

        if (getValueLeafFlags().get(LeafIdentifier.TRADINGSUPERVISOR.getLeafIndex())) {
            if (appInstance.tradingSupervisor() == null || !(tradingSupervisor()
            .equals(appInstance.tradingSupervisor()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.tradingSupervisor(appInstance.tradingSupervisor());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier
            .TRADINGSUPERVISOR.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.tradingSupervisor(appInstance.tradingSupervisor());
        }

        return true;
    }

    private boolean processLeafListSubTreeFiltering(LogisticsManager appInstance, LogisticsManagerBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (String warehouseSupervisor : appInstance.warehouseSupervisor()) {
                subTreeFilteringResultBuilder.addToWarehouseSupervisor(warehouseSupervisor);
            }
        } else if (warehouseSupervisor() != null) {
            if (!warehouseSupervisor().isEmpty()) {
                if (appInstance.warehouseSupervisor() == null || appInstance.warehouseSupervisor().isEmpty()) {
                    return false;
                }
                for (String warehouseSupervisor : warehouseSupervisor()) {
                    boolean flag = false;
                    for (String warehouseSupervisor2 : appInstance.warehouseSupervisor()) {
                        if (warehouseSupervisor.equals(warehouseSupervisor2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToWarehouseSupervisor(warehouseSupervisor2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.warehouseSupervisor() != null && !appInstance.warehouseSupervisor().isEmpty()) {
                    for (String warehouseSupervisor : appInstance.warehouseSupervisor()) {
                        subTreeFilteringResultBuilder.addToWarehouseSupervisor(warehouseSupervisor);
                    }
                }
            }
        }

        if (isSelectAllSchemaChild) {
            for (String employeeId : appInstance.employeeId()) {
                subTreeFilteringResultBuilder.addToEmployeeId(employeeId);
            }
        } else if (employeeId() != null) {
            if (!employeeId().isEmpty()) {
                if (appInstance.employeeId() == null || appInstance.employeeId().isEmpty()) {
                    return false;
                }
                for (String employeeId : employeeId()) {
                    boolean flag = false;
                    for (String employeeId2 : appInstance.employeeId()) {
                        if (employeeId.equals(employeeId2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToEmployeeId(employeeId2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.employeeId() != null && !appInstance.employeeId().isEmpty()) {
                    for (String employeeId : appInstance.employeeId()) {
                        subTreeFilteringResultBuilder.addToEmployeeId(employeeId);
                    }
                }
            }
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(LogisticsManager appInstance, LogisticsManagerBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (MaterialSupervisor materialSupervisor : appInstance.materialSupervisor()) {
                subTreeFilteringResultBuilder.addToMaterialSupervisor(materialSupervisor);
            }
        } else if (materialSupervisor() != null) {
            isAnySelectOrContainmentNode = true;
            if (!materialSupervisor().isEmpty()) {
                if (appInstance.materialSupervisor() != null && !appInstance.materialSupervisor().isEmpty()) {
                    for (MaterialSupervisor materialSupervisor : materialSupervisor()) {
                        for (MaterialSupervisor materialSupervisor2 : appInstance.materialSupervisor()) {
                            MaterialSupervisor result = materialSupervisor
            .processSubtreeFiltering(materialSupervisor2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToMaterialSupervisor(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.materialSupervisor() != null && !appInstance.materialSupervisor().isEmpty()) {
                    for (MaterialSupervisor materialSupervisor : appInstance.materialSupervisor()) {
                        subTreeFilteringResultBuilder.addToMaterialSupervisor(materialSupervisor);
                    }
                }
            }
        }

        if (purchasingSupervisor()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.purchasingSupervisor() != null) {
                PurchasingSupervisor result = purchasingSupervisor.processSubtreeFiltering(appInstance
            .purchasingSupervisor(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.purchasingSupervisor(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of logisticsManagerOpParam.
     */
    protected LogisticsManagerOpParam() {
    }

    /**
     * Returns the attribute LogisticsManagerBuilder.
     *
     * @return value of LogisticsManagerBuilder
     */
    public static LogisticsManagerBuilder builder() {
        return new LogisticsManagerBuilder();
    }

    /**
     * Returns the valueLeafFlags.
     *
     * @return value of valueLeafFlags
     */
    public BitSet getValueLeafFlags() {
        return valueLeafFlags;
    }

    /**
     * Returns the selectLeafFlags.
     *
     * @return value of selectLeafFlags
     */
    public BitSet getSelectLeafFlags() {
        return selectLeafFlags;
    }


    @Override
    public boolean isLeafValueSet(LeafIdentifier leaf) {
        return getValueLeafFlags().get(leaf.getLeafIndex());
    }

    @Override
    public boolean isSelectLeaf(LeafIdentifier leaf) {
        return getSelectLeafFlags().get(leaf.getLeafIndex());
    }


    /**
     * Represents the builder implementation of logisticsManager.
     */
    public static class LogisticsManagerBuilder {

        protected String customsSupervisor;
        protected String merchandiserSupervisor;
        protected String tradingSupervisor;
        protected List<String> warehouseSupervisor;
        protected List<String> employeeId;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<MaterialSupervisor> materialSupervisor;
        protected PurchasingSupervisor purchasingSupervisor;
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();

        public String customsSupervisor() {
            return customsSupervisor;
        }
        public String merchandiserSupervisor() {
            return merchandiserSupervisor;
        }
        public String tradingSupervisor() {
            return tradingSupervisor;
        }
        public List<String> warehouseSupervisor() {
            return warehouseSupervisor;
        }
        public List<String> employeeId() {
            return employeeId;
        }
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public List<MaterialSupervisor> materialSupervisor() {
            return materialSupervisor;
        }
        public PurchasingSupervisor purchasingSupervisor() {
            return purchasingSupervisor;
        }

        /**
         * Returns the builder object of customsSupervisor.
         *
         * @param customsSupervisor value of customsSupervisor
         * @return builder object of customsSupervisor
         */
        public LogisticsManagerBuilder customsSupervisor(String customsSupervisor) {
            getValueLeafFlags().set(LeafIdentifier.CUSTOMSSUPERVISOR.getLeafIndex());
            this.customsSupervisor = customsSupervisor;
            return this;
        }

        /**
         * Returns the builder object of merchandiserSupervisor.
         *
         * @param merchandiserSupervisor value of merchandiserSupervisor
         * @return builder object of merchandiserSupervisor
         */
        public LogisticsManagerBuilder merchandiserSupervisor(String merchandiserSupervisor) {
            getValueLeafFlags().set(LeafIdentifier.MERCHANDISERSUPERVISOR.getLeafIndex());
            this.merchandiserSupervisor = merchandiserSupervisor;
            return this;
        }

        /**
         * Returns the builder object of tradingSupervisor.
         *
         * @param tradingSupervisor value of tradingSupervisor
         * @return builder object of tradingSupervisor
         */
        public LogisticsManagerBuilder tradingSupervisor(String tradingSupervisor) {
            getValueLeafFlags().set(LeafIdentifier.TRADINGSUPERVISOR.getLeafIndex());
            this.tradingSupervisor = tradingSupervisor;
            return this;
        }

        /**
         * Returns the builder object of warehouseSupervisor.
         *
         * @param warehouseSupervisor list of warehouseSupervisor
         * @return builder object of warehouseSupervisor
         */
        public LogisticsManagerBuilder warehouseSupervisor(List<String> warehouseSupervisor) {
            this.warehouseSupervisor = warehouseSupervisor;
            return this;
        }

        /**
         * Returns the builder object of employeeId.
         *
         * @param employeeId list of employeeId
         * @return builder object of employeeId
         */
        public LogisticsManagerBuilder employeeId(List<String> employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public LogisticsManagerBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of materialSupervisor.
         *
         * @param materialSupervisor list of materialSupervisor
         * @return builder object of materialSupervisor
         */
        public LogisticsManagerBuilder materialSupervisor(List<MaterialSupervisor> materialSupervisor) {
            this.materialSupervisor = materialSupervisor;
            return this;
        }

        /**
         * Returns the builder object of purchasingSupervisor.
         *
         * @param purchasingSupervisor value of purchasingSupervisor
         * @return builder object of purchasingSupervisor
         */
        public LogisticsManagerBuilder purchasingSupervisor(PurchasingSupervisor purchasingSupervisor) {
            this.purchasingSupervisor = purchasingSupervisor;
            return this;
        }
        public LogisticsManagerBuilder addToWarehouseSupervisor(String value) {
            if (warehouseSupervisor() == null) {
                warehouseSupervisor(new ArrayList<>());
            }
            warehouseSupervisor().add(value);
            return this;
        }
        public LogisticsManagerBuilder addToEmployeeId(String value) {
            if (employeeId() == null) {
                employeeId(new ArrayList<>());
            }
            employeeId().add(value);
            return this;
        }
        public LogisticsManagerBuilder addToMaterialSupervisor(MaterialSupervisor value) {
            if (materialSupervisor() == null) {
                materialSupervisor(new ArrayList<>());
            }
            materialSupervisor().add(value);
            return this;
        }

        /**
         * Returns the valueLeafFlags.
         *
         * @return value of valueLeafFlags
         */
        public BitSet getValueLeafFlags() {
            return valueLeafFlags;
        }

        /**
         * Returns the selectLeafFlags.
         *
         * @return value of selectLeafFlags
         */
        public BitSet getSelectLeafFlags() {
            return selectLeafFlags;
        }



        public LogisticsManagerBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        public LogisticsManager build() {
            return new LogisticsManagerOpParam(this);
        }

        /**
         * Creates an instance of logisticsManagerBuilder.
         */
        public LogisticsManagerBuilder() {
        }
    }
}
