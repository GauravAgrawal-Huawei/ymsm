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

package org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.ytbsimplechoicecase
            .ytbfood.ytbsnack;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.ytbsimplechoicecase
            .ytbfood.YtbSnack;

/**
 * Represents the implementation of ytbSportsArena.
 */
public class DefaultYtbSportsArena implements YtbSportsArena {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected String pretzel;
    protected String beer;
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
    public String pretzel() {
        return pretzel;
    }

    @Override
    public String beer() {
        return beer;
    }

    @Override
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pretzel, beer, onosYangNodeOperationType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultYtbSportsArena) {
            DefaultYtbSportsArena other = (DefaultYtbSportsArena) obj;
            return
                 Objects.equals(pretzel, other.pretzel) &&
                 Objects.equals(beer, other.beer) &&
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("pretzel", pretzel)
            .add("beer", beer)
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
     * Creates an instance of ytbSportsArena.
     *
     * @param builderObject builder object of ytbSportsArena
     */
    protected DefaultYtbSportsArena(YtbSportsArenaBuilder builderObject) {
        this.pretzel = builderObject.pretzel();
        this.beer = builderObject.beer();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public YtbSnack processSubtreeFiltering(YtbSnack instance, boolean isSelectAllSchemaChild) {
        YtbSportsArenaBuilder subTreeFilteringResultBuilder = new YtbSportsArenaBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        YtbSportsArena appInstance = (YtbSportsArena) instance;
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
    private boolean processLeafSubtreeFiltering(YtbSnack instance, YtbSportsArenaBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        YtbSportsArena appInstance = (YtbSportsArena) instance;
        if (getValueLeafFlags().get(LeafIdentifier.PRETZEL.getLeafIndex())) {
            if (appInstance.pretzel() == null || !(pretzel().equals(appInstance.pretzel()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.pretzel(appInstance.pretzel());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.PRETZEL.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.pretzel(appInstance.pretzel());
        }

        if (getValueLeafFlags().get(LeafIdentifier.BEER.getLeafIndex())) {
            if (appInstance.beer() == null || !(beer().equals(appInstance.beer()))) {
                return false;
            } else {
                subTreeFilteringResultBuilder.beer(appInstance.beer());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.BEER.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.beer(appInstance.beer());
        }

        return true;
    }


    /**
     * Creates an instance of defaultYtbSportsArena.
     */
    protected DefaultYtbSportsArena() {
    }

    /**
     * Returns the attribute YtbSportsArenaBuilder.
     *
     * @return value of YtbSportsArenaBuilder
     */
    public static YtbSportsArenaBuilder builder() {
        return new YtbSportsArenaBuilder();
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
     * Represents the builder implementation of ytbSportsArena.
     */
    public static class YtbSportsArenaBuilder implements YtbSportsArena.YtbSportsArenaBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected String pretzel;
        protected String beer;
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
        public String pretzel() {
            return pretzel;
        }

        @Override
        public String beer() {
            return beer;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public YtbSportsArenaBuilder pretzel(String pretzel) {
            getValueLeafFlags().set(LeafIdentifier.PRETZEL.getLeafIndex());
            this.pretzel = pretzel;
            return this;
        }

        @Override
        public YtbSportsArenaBuilder beer(String beer) {
            getValueLeafFlags().set(LeafIdentifier.BEER.getLeafIndex());
            this.beer = beer;
            return this;
        }

        @Override
        public YtbSportsArenaBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
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
        public YtbSportsArenaBuilder selectLeaf(LeafIdentifier leaf) {
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
        public YtbSportsArena build() {
            return new DefaultYtbSportsArena(this);
        }

        /**
         * Creates an instance of ytbSportsArenaBuilder.
         */
        public YtbSportsArenaBuilder() {
        }
    }
}
