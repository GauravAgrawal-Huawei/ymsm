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

package org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.rootlist;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.rootlist.listwithcontainer.YangAutoPrefixInterface;

/**
 * Represents the implementation of listwithcontainer.
 */
public class DefaultListwithcontainer implements Listwithcontainer {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected int invalid;
    protected int invalid1;
    protected List<Integer> invalidinterval;
    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected YangAutoPrefixInterface yangAutoPrefixInterface;
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
    public int invalid() {
        return invalid;
    }

    @Override
    public int invalid1() {
        return invalid1;
    }

    @Override
    public List<Integer> invalidinterval() {
        return invalidinterval;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public YangAutoPrefixInterface yangAutoPrefixInterface() {
        return yangAutoPrefixInterface;
    }

    @Override
    public int hashCode() {
        return Objects.hash(invalid, invalid1, invalidinterval, onosYangNodeOperationType, yangAutoPrefixInterface);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultListwithcontainer) {
            DefaultListwithcontainer other = (DefaultListwithcontainer) obj;
            return
                 Objects.equals(invalid, other.invalid) &&
                 Objects.equals(invalid1, other.invalid1) &&
                 Objects.equals(invalidinterval, other.invalidinterval) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(yangAutoPrefixInterface, other.yangAutoPrefixInterface);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("invalid", invalid)
            .add("invalid1", invalid1)
            .add("invalidinterval", invalidinterval)
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("yangAutoPrefixInterface", yangAutoPrefixInterface)
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
     * Creates an instance of listwithcontainer.
     *
     * @param builderObject builder object of listwithcontainer
     */
    protected DefaultListwithcontainer(ListwithcontainerBuilder builderObject) {
        this.invalid = builderObject.invalid();
        this.invalid1 = builderObject.invalid1();
        this.invalidinterval = builderObject.invalidinterval();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.yangAutoPrefixInterface = builderObject.yangAutoPrefixInterface();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Listwithcontainer processSubtreeFiltering(Listwithcontainer appInstance, boolean isSelectAllSchemaChild) {
        ListwithcontainerBuilder subTreeFilteringResultBuilder = new ListwithcontainerBuilder();
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
    private boolean processLeafSubtreeFiltering(Listwithcontainer appInstance, ListwithcontainerBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (getValueLeafFlags().get(LeafIdentifier.INVALID.getLeafIndex())) {
            if (appInstance.invalid() != invalid()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.invalid(appInstance.invalid());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.INVALID.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.invalid(appInstance.invalid());
        }

        if (getValueLeafFlags().get(LeafIdentifier.INVALID1.getLeafIndex())) {
            if (appInstance.invalid1() != invalid1()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.invalid1(appInstance.invalid1());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.INVALID1.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.invalid1(appInstance.invalid1());
        }

        return true;
    }

    private boolean processLeafListSubTreeFiltering(Listwithcontainer appInstance, ListwithcontainerBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Integer invalidinterval : appInstance.invalidinterval()) {
                subTreeFilteringResultBuilder.addToInvalidinterval(invalidinterval);
            }
        } else if (invalidinterval() != null) {
            if (!invalidinterval().isEmpty()) {
                if (appInstance.invalidinterval() == null || appInstance.invalidinterval().isEmpty()) {
                    return false;
                }
                for (Integer invalidinterval : invalidinterval()) {
                    boolean flag = false;
                    for (Integer invalidinterval2 : appInstance.invalidinterval()) {
                        if (invalidinterval.equals(invalidinterval2)) {
                            flag = true;
                            subTreeFilteringResultBuilder.addToInvalidinterval(invalidinterval2);
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
            } else {
                isAnySelectOrContainmentNode = true;
                if (appInstance.invalidinterval() != null && !appInstance.invalidinterval().isEmpty()) {
                    for (Integer invalidinterval : appInstance.invalidinterval()) {
                        subTreeFilteringResultBuilder.addToInvalidinterval(invalidinterval);
                    }
                }
            }
        }

        return true;
    }

    private boolean processChildNodesSubTreeFiltering(Listwithcontainer appInstance, ListwithcontainerBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (yangAutoPrefixInterface()  != null) {
            isAnySelectOrContainmentNode = true;
            if (appInstance.yangAutoPrefixInterface() != null) {
                YangAutoPrefixInterface result = yangAutoPrefixInterface.processSubtreeFiltering(appInstance
            .yangAutoPrefixInterface(), false);
                if (result != null) {
                    subTreeFilteringResultBuilder.yangAutoPrefixInterface(result);
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of defaultListwithcontainer.
     */
    protected DefaultListwithcontainer() {
    }

    /**
     * Returns the attribute ListwithcontainerBuilder.
     *
     * @return value of ListwithcontainerBuilder
     */
    public static ListwithcontainerBuilder builder() {
        return new ListwithcontainerBuilder();
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
     * Represents the builder implementation of listwithcontainer.
     */
    public static class ListwithcontainerBuilder implements Listwithcontainer.ListwithcontainerBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected int invalid;
        protected int invalid1;
        protected List<Integer> invalidinterval;
        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected YangAutoPrefixInterface yangAutoPrefixInterface;
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
        public int invalid() {
            return invalid;
        }

        @Override
        public int invalid1() {
            return invalid1;
        }

        @Override
        public List<Integer> invalidinterval() {
            return invalidinterval;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public YangAutoPrefixInterface yangAutoPrefixInterface() {
            return yangAutoPrefixInterface;
        }

        @Override
        public ListwithcontainerBuilder invalid(int invalid) {
            getValueLeafFlags().set(LeafIdentifier.INVALID.getLeafIndex());
            this.invalid = invalid;
            return this;
        }

        @Override
        public ListwithcontainerBuilder invalid1(int invalid1) {
            getValueLeafFlags().set(LeafIdentifier.INVALID1.getLeafIndex());
            this.invalid1 = invalid1;
            return this;
        }

        @Override
        public ListwithcontainerBuilder invalidinterval(List<Integer> invalidinterval) {
            this.invalidinterval = invalidinterval;
            return this;
        }

        @Override
        public ListwithcontainerBuilder onosYangNodeOperationType(OnosYangNodeOperationType
            onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        @Override
        public ListwithcontainerBuilder yangAutoPrefixInterface(YangAutoPrefixInterface yangAutoPrefixInterface) {
            this.yangAutoPrefixInterface = yangAutoPrefixInterface;
            return this;
        }

        @Override
        public ListwithcontainerBuilder addToInvalidinterval(Integer value) {
            if (invalidinterval() == null) {
                invalidinterval(new ArrayList<>());
            }
            invalidinterval().add(value);
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
        public ListwithcontainerBuilder selectLeaf(LeafIdentifier leaf) {
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
        public Listwithcontainer build() {
            return new DefaultListwithcontainer(this);
        }

        /**
         * Creates an instance of listwithcontainerBuilder.
         */
        public ListwithcontainerBuilder() {
        }
    }
}
