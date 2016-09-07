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

package org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.food.snack;

import com.google.common.base.MoreObjects;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.food.Snack;

/**
 * Represents the implementation of sportsarena.
 */
public class DefaultSportsarena implements Sportsarena {

    protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
    protected boolean pretzel;
    protected boolean beer;
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
    public boolean pretzel() {
        return pretzel;
    }

    @Override
    public boolean beer() {
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
        if (obj instanceof DefaultSportsarena) {
            DefaultSportsarena other = (DefaultSportsarena) obj;
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
     * Creates an instance of sportsarena.
     *
     * @param builderObject builder object of sportsarena
     */
    protected DefaultSportsarena(SportsarenaBuilder builderObject) {
        this.pretzel = builderObject.pretzel();
        this.beer = builderObject.beer();
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.valueLeafFlags = builderObject.getValueLeafFlags();
        this.selectLeafFlags = builderObject.getSelectLeafFlags();
        this.yangAugmentedInfoMap = builderObject.yangAugmentedInfoMap();
    }

    @Override
    public Snack processSubtreeFiltering(Snack instance, boolean isSelectAllSchemaChild) {
        SportsarenaBuilder subTreeFilteringResultBuilder = new SportsarenaBuilder();
        Boolean isAnySelectOrContainmentNode = false;
        Sportsarena appInstance = (Sportsarena) instance;
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
    private boolean processLeafSubtreeFiltering(Snack instance, SportsarenaBuilder subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        Sportsarena appInstance = (Sportsarena) instance;
        if (getValueLeafFlags().get(LeafIdentifier.PRETZEL.getLeafIndex())) {
            if (appInstance.pretzel() != pretzel()) {
                return false;
            } else {
                subTreeFilteringResultBuilder.pretzel(appInstance.pretzel());
            }
        } else if (getSelectLeafFlags().get(LeafIdentifier.PRETZEL.getLeafIndex()) || isSelectAllSchemaChild) {
            isAnySelectOrContainmentNode = true;
            subTreeFilteringResultBuilder.pretzel(appInstance.pretzel());
        }

        if (getValueLeafFlags().get(LeafIdentifier.BEER.getLeafIndex())) {
            if (appInstance.beer() != beer()) {
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
     * Creates an instance of defaultSportsarena.
     */
    protected DefaultSportsarena() {
    }

    /**
     * Returns the attribute SportsarenaBuilder.
     *
     * @return value of SportsarenaBuilder
     */
    public static SportsarenaBuilder builder() {
        return new SportsarenaBuilder();
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
     * Represents the builder implementation of sportsarena.
     */
    public static class SportsarenaBuilder implements Sportsarena.SportsarenaBuilder {

        protected Map<Class<?>, Object> yangAugmentedInfoMap = new HashMap<>();
        protected boolean pretzel;
        protected boolean beer;
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
        public boolean pretzel() {
            return pretzel;
        }

        @Override
        public boolean beer() {
            return beer;
        }

        @Override
        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }

        @Override
        public SportsarenaBuilder pretzel(boolean pretzel) {
            getValueLeafFlags().set(LeafIdentifier.PRETZEL.getLeafIndex());
            this.pretzel = pretzel;
            return this;
        }

        @Override
        public SportsarenaBuilder beer(boolean beer) {
            getValueLeafFlags().set(LeafIdentifier.BEER.getLeafIndex());
            this.beer = beer;
            return this;
        }

        @Override
        public SportsarenaBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
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
        public SportsarenaBuilder selectLeaf(LeafIdentifier leaf) {
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
        public Sportsarena build() {
            return new DefaultSportsarena(this);
        }

        /**
         * Creates an instance of sportsarenaBuilder.
         */
        public SportsarenaBuilder() {
        }
    }
}
