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

package org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.b.rev20160826;

import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.b.rev20160826.ytbmultimoduleb.Ytbmultilistb;

/**
 * Represents the implementation of ytbMultiModuleb.
 */
public class YtbMultiModulebOpParam implements YtbMultiModuleb {

    protected OnosYangNodeOperationType onosYangNodeOperationType;
    protected List<Ytbmultilistb> ytbmultilistb;
    public OnosYangNodeOperationType onosYangNodeOperationType() {
        return onosYangNodeOperationType;
    }
    public List<Ytbmultilistb> ytbmultilistb() {
        return ytbmultilistb;
    }

    @Override
    public int hashCode() {
        return Objects.hash(onosYangNodeOperationType, ytbmultilistb);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof YtbMultiModulebOpParam) {
            YtbMultiModulebOpParam other = (YtbMultiModulebOpParam) obj;
            return
                 Objects.equals(onosYangNodeOperationType, other.onosYangNodeOperationType) &&
                 Objects.equals(ytbmultilistb, other.ytbmultilistb);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("onosYangNodeOperationType", onosYangNodeOperationType)
            .add("ytbmultilistb", ytbmultilistb)
            .toString();
    }

    /**
     * Creates an instance of ytbMultiModuleb.
     *
     * @param builderObject builder object of ytbMultiModuleb
     */
    protected YtbMultiModulebOpParam(YtbMultiModulebBuilder builderObject) {
        this.onosYangNodeOperationType = builderObject.onosYangNodeOperationType();
        this.ytbmultilistb = builderObject.ytbmultilistb();
    }

    @Override
    public YtbMultiModuleb processSubtreeFiltering(YtbMultiModuleb appInstance, boolean isSelectAllSchemaChild) {
        YtbMultiModulebBuilder subTreeFilteringResultBuilder = new YtbMultiModulebBuilder();
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
    private boolean processChildNodesSubTreeFiltering(YtbMultiModuleb appInstance, YtbMultiModulebBuilder
            subTreeFilteringResultBuilder,
            Boolean isAnySelectOrContainmentNode, boolean isSelectAllSchemaChild) {
        if (isSelectAllSchemaChild) {
            for (Ytbmultilistb ytbmultilistb : appInstance.ytbmultilistb()) {
                subTreeFilteringResultBuilder.addToYtbmultilistb(ytbmultilistb);
            }
        } else if (ytbmultilistb() != null) {
            isAnySelectOrContainmentNode = true;
            if (!ytbmultilistb().isEmpty()) {
                if (appInstance.ytbmultilistb() != null && !appInstance.ytbmultilistb().isEmpty()) {
                    for (Ytbmultilistb ytbmultilistb : ytbmultilistb()) {
                        for (Ytbmultilistb ytbmultilistb2 : appInstance.ytbmultilistb()) {
                            Ytbmultilistb result = ytbmultilistb.processSubtreeFiltering(ytbmultilistb2, false);
                            if (result != null) {
                                subTreeFilteringResultBuilder.addToYtbmultilistb(result);
                            }
                        }
                    }
                }
            } else {
                if (appInstance.ytbmultilistb() != null && !appInstance.ytbmultilistb().isEmpty()) {
                    for (Ytbmultilistb ytbmultilistb : appInstance.ytbmultilistb()) {
                        subTreeFilteringResultBuilder.addToYtbmultilistb(ytbmultilistb);
                    }
                }
            }
        }

        return true;
    }


    /**
     * Creates an instance of ytbMultiModulebOpParam.
     */
    protected YtbMultiModulebOpParam() {
    }

    /**
     * Returns the attribute YtbMultiModulebBuilder.
     *
     * @return value of YtbMultiModulebBuilder
     */
    public static YtbMultiModulebBuilder builder() {
        return new YtbMultiModulebBuilder();
    }


    /**
     * Represents the builder implementation of ytbMultiModuleb.
     */
    public static class YtbMultiModulebBuilder {

        protected OnosYangNodeOperationType onosYangNodeOperationType;
        protected List<Ytbmultilistb> ytbmultilistb;

        public OnosYangNodeOperationType onosYangNodeOperationType() {
            return onosYangNodeOperationType;
        }
        public List<Ytbmultilistb> ytbmultilistb() {
            return ytbmultilistb;
        }

        /**
         * Returns the builder object of onosYangNodeOperationType.
         *
         * @param onosYangNodeOperationType value of onosYangNodeOperationType
         * @return builder object of onosYangNodeOperationType
         */
        public YtbMultiModulebBuilder onosYangNodeOperationType(OnosYangNodeOperationType onosYangNodeOperationType) {
            this.onosYangNodeOperationType = onosYangNodeOperationType;
            return this;
        }

        /**
         * Returns the builder object of ytbmultilistb.
         *
         * @param ytbmultilistb list of ytbmultilistb
         * @return builder object of ytbmultilistb
         */
        public YtbMultiModulebBuilder ytbmultilistb(List<Ytbmultilistb> ytbmultilistb) {
            this.ytbmultilistb = ytbmultilistb;
            return this;
        }
        public YtbMultiModulebBuilder addToYtbmultilistb(Ytbmultilistb value) {
            if (ytbmultilistb() == null) {
                ytbmultilistb(new ArrayList<>());
            }
            ytbmultilistb().add(value);
            return this;
        }

        public YtbMultiModuleb build() {
            return new YtbMultiModulebOpParam(this);
        }

        /**
         * Creates an instance of ytbMultiModulebBuilder.
         */
        public YtbMultiModulebBuilder() {
        }
    }
}
