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

package org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826
            .ytbchoicewithcontainerandleaflist.currentvalue;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826
            .ytbchoicewithcontainerandleaflist.CurrentValue;

/**
 * Represents the implementation of ytbAbsent.
 */
public class DefaultYtbAbsent implements YtbAbsent {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected List<String> yangAutoPrefixFinal;
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
    public List<String> yangAutoPrefixFinal() {
        return yangAutoPrefixFinal;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yangAutoPrefixFinal, onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultYtbAbsent) {
            DefaultYtbAbsent other = (DefaultYtbAbsent) obj;
            return
                 Objects.equals(yangAutoPrefixFinal, other.yangAutoPrefixFinal) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("yangAutoPrefixFinal", yangAutoPrefixFinal)
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
     * Creates an instance of ytbAbsent.
     *
     * @param builderObject builder object of ytbAbsent
     */
    protected DefaultYtbAbsent(YtbAbsentBuilder builderObject) {
        this.yangAutoPrefixFinal = builderObject.yangAutoPrefixFinal();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public CurrentValue processSubtreeFiltering(CurrentValue instance, boolean isSelectAllSchemaChild) {
        YtbAbsentBuilder subTreeFilteringResultBuilder = new YtbAbsentBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        YtbAbsent appInstance = (YtbAbsent) instance;
        if (!processLeafListSubTreeFiltering(appInstance, subTreeFilteringResultBuilder,
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
    private boolean processLeafListSubTreeFiltering(CurrentValue instance, YtbAbsentBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        YtbAbsent appInstance = (YtbAbsent) instance;
        if (isSelectAllSchemaChild) {
            for (String yangAutoPrefixFinal : appInstance.yangAutoPrefixFinal()) {
                subTreeFilteringResultBuilder.addToYangAutoPrefixFinal(yangAutoPrefixFinal);
            }
        } else if (yangAutoPrefixFinal() != null) {
            if (!yangAutoPrefixFinal().isEmpty()) {
                if (appInstance.yangAutoPrefixFinal() == null || appInstance.yangAutoPrefixFinal().isEmpty()) {
                    return false;
                }
                for (String yangAutoPrefixFinal : yangAutoPrefixFinal()) {
                    boolean flag = false;
                    for (String yangAutoPrefixFinal2 : appInstance.yangAutoPrefixFinal()) {
                        if (yangAutoPrefixFinal.equals(yangAutoPrefixFinal2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToYangAutoPrefixFinal(yangAutoPrefixFinal2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.yangAutoPrefixFinal() != null && !appInstance.yangAutoPrefixFinal().isEmpty()) {
                    for (String yangAutoPrefixFinal : appInstance.yangAutoPrefixFinal()) {
                        subTreeFilteringResultBuilder.addToYangAutoPrefixFinal(yangAutoPrefixFinal);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultYtbAbsent.
     */
    protected DefaultYtbAbsent() {
    }

    /**
     * Returns the attribute YtbAbsentBuilder.
     *
     * @return value of YtbAbsentBuilder
     */
    public static YtbAbsentBuilder builder() {
        return new YtbAbsentBuilder();
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
     * Represents the builder implementation of ytbAbsent.
     */
    public static class YtbAbsentBuilder implements YtbAbsent.YtbAbsentBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected List<String> yangAutoPrefixFinal;
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
        public List<String> yangAutoPrefixFinal() {
            return yangAutoPrefixFinal;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public YtbAbsentBuilder yangAutoPrefixFinal(List<String> yangAutoPrefixFinal) {
            this.yangAutoPrefixFinal = yangAutoPrefixFinal;
            return this;
        }

        @Override
        public YtbAbsentBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public YtbAbsentBuilder addToYangAutoPrefixFinal(String value) {
            if (yangAutoPrefixFinal() == null) {
                yangAutoPrefixFinal(new ArrayList<>());
            }
            yangAutoPrefixFinal().add(value);
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
        public YtbAbsentBuilder selectLeaf(LeafIdentifier leaf) {
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
        public YtbAbsent build() {
            return new DefaultYtbAbsent(this);
        }

        /**
         * Creates an instance of ytbAbsentBuilder.
         */
        public YtbAbsentBuilder() {
        }
    }
}
