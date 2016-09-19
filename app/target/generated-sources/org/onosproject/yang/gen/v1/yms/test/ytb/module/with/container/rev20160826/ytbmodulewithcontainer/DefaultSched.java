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

package org.onosproject.yang.gen.v1.yms.test.ytb.module.with.container.rev20160826.ytbmodulewithcontainer;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the implementation of sched.
 */
public class DefaultSched implements Sched {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected BigDecimal predict;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    /**
     * Identify the leafs whose value are explicitly set
     * Applicable in protocol edit and query operation
     */
    private BitSet valueLeafFlags = new BitSet();

    /**
     * Identify the leafs to be selected, in a query operation
     */
    private BitSet selectLeafFlags = new BitSet();

    @Override
    public BigDecimal predict() {
        return predict;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(predict, onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultSched) {
            DefaultSched other = (DefaultSched) obj;
            return
                 Objects.equals(predict, other.predict) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("predict", predict)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .toString();
    }

    @Override
    public Object yangAugmentedInfo(Class classObject) {
        return yangAugmentedInfoMap.get(classObject);
    }

    @Override
    public Map<Class<?>, Object> yangAugmentedInfoMap() {
        return yangAugmentedInfoMap;
    }

    /**
     * Creates an instance of sched.
     *
     * @param builderObject builder object of sched
     */
    protected DefaultSched(SchedBuilder builderObject) {
        this.predict = builderObject.predict();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Sched processSubtreeFiltering(Sched appInstance, boolean isSelectAllSchemaChild) {
        SchedBuilder subTreeFilteringResultBuilder = new SchedBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        if (!processLeafSubtreeFiltering(appInstance, subTreeFilteringResultBuilder,
            isAnySelectOrContainmentNode, isSelectAllSchemaChild)) {
            return null;
        }
        for (Object yangAugmentedInfo : this.yangAugmentedInfoMap().values()) {
            Object yangAugmentedOpParamInfo = appInstance.yangAugmentedInfo(yangAugmentedInfo.getClass());
            Object processSubtreeFiltering;
            try {
                Class<?>[] interfaces = yangAugmentedInfo.getClass().getInterfaces();
                processSubtreeFiltering = yangAugmentedInfo.getClass()
                    .getMethod("processSubtreeFiltering", interfaces[0]).invoke(yangAugmentedInfo,
                        yangAugmentedOpParamInfo);
                if (processSubtreeFiltering != null) {
                    subTreeFilteringResultBuilder
            .addYangAugmentedInfo(processSubtreeFiltering, processSubtreeFiltering.getClass());
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                continue;
            }
        }
        if (!isSelectAllSchemaChild && !isAnySelectOrContainmentNode) {
            return processSubtreeFiltering(appInstance, true);
        }
        return subTreeFilteringResultBuilder.build();
    }
    private boolean processLeafSubtreeFiltering(Sched appInstance, SchedBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.PREDICT.getLeafIndex())) {
            if (appInstance.predict() != predict()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.predict(appInstance.predict());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.PREDICT.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.predict(appInstance.predict());
        }

        return true;
    }


    /**
     * Creates an instance of defaultSched.
     */
    protected DefaultSched() {
    }

    /**
     * Returns the attribute SchedBuilder.
     *
     * @return value of SchedBuilder
     */
    public static SchedBuilder builder() {
        return new SchedBuilder();
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
     * Represents the builder implementation of sched.
     */
    public static class SchedBuilder implements Sched.SchedBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected BigDecimal predict;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        /**
         * Identify the leafs whose value are explicitly set
         * Applicable in protocol edit and query operation
         */
        private BitSet valueLeafFlags = new BitSet();

        /**
         * Identify the leafs to be selected, in a query operation
         */
        private BitSet selectLeafFlags = new BitSet();


        @Override
        public BigDecimal predict() {
            return predict;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public SchedBuilder predict(BigDecimal predict) {
            getValueLeafFlags().set(LeafIdentifier.PREDICT.getLeafIndex());
            this.predict = predict;
            return this;
        }

        @Override
        public SchedBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
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



        @Override
        public SchedBuilder selectLeaf(LeafIdentifier leaf) {
            getSelectLeafFlags().set(leaf.getLeafIndex());
            return this;
        }

        @Override
        public void addYangAugmentedInfo(Object value, Class classObject) {
            yangAugmentedInfoMap.put(classObject, value);
        }

        @Override
        public Object yangAugmentedInfo(Class classObject) {
            return yangAugmentedInfoMap.get(classObject);
        }

        @Override
        public Map<Class<?>, Object> yangAugmentedInfoMap() {
            return yangAugmentedInfoMap;
        }
        @Override
        public Sched build() {
            return new DefaultSched(this);
        }

        /**
         * Creates an instance of schedBuilder.
         */
        public SchedBuilder() {
        }
    }
}
