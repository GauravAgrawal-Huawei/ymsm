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

package org.onosproject.yms.app.ysr;

import org.onosproject.yms.ysr.YangModuleIdentifier;
import org.onosproject.yms.ysr.YangModuleInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Represents YANG module information.
 */
public class DefaultYangModuleInformation implements YangModuleInformation {

    private YangModuleIdentifier moduleIdentifier;
    private List<YangModuleIdentifier> subModuleIdentifiers;
    private String nameSpace;
    private List<String> features;

    /**
     * Creates an instance of YANG module information.
     */
    public DefaultYangModuleInformation() {
        setFeatures(new ArrayList<>());
        setSubModuleIdentifiers(new ArrayList<>());
    }

    @Override
    public YangModuleIdentifier getModuleIdentifier() {
        return moduleIdentifier;
    }

    @Override
    public String getNamespace() {
        return nameSpace;
    }

    @Override
    public List<String> getFeatureList() {
        return features;
    }

    @Override
    public List<YangModuleIdentifier> getSubModuleIdentifier() {
        return subModuleIdentifiers;
    }

    /**
     * Sets feature list.
     *
     * @param features YANG features
     */
    private void setFeatures(List<String> features) {
        this.features = features;
    }

    /**
     * Sets YANG module identifier.
     *
     * @param moduleIdentifier YANG module identifier
     */
    public void setModuleIdentifier(YangModuleIdentifier moduleIdentifier) {
        this.moduleIdentifier = moduleIdentifier;
    }

    /**
     * Sets YANG module identifier list.
     *
     * @param subModuleIdentifiers YANG module identifier list
     */
    private void setSubModuleIdentifiers(List<YangModuleIdentifier> subModuleIdentifiers) {
        this.subModuleIdentifiers = subModuleIdentifiers;
    }

    /**
     * Adds to YANG sub module identifier list.
     *
     * @param subModuleIdentifier YANG sub module identifier
     */
    public void addSubModuleIdentifiers(YangModuleIdentifier subModuleIdentifier) {
        getSubModuleIdentifier().add(subModuleIdentifier);
    }

    /**
     * Sets name space.
     *
     * @param nameSpace YANG name space
     */
    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleIdentifier, subModuleIdentifiers, nameSpace, features);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultYangModuleInformation) {
            DefaultYangModuleInformation that = (DefaultYangModuleInformation) obj;
            return Objects.equals(moduleIdentifier, that.moduleIdentifier) &&
                    Objects.equals(nameSpace, that.nameSpace) &&
                    Objects.equals(features, that.features) &&
                    Objects.equals(subModuleIdentifiers, that.subModuleIdentifiers);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("yangModuleIdentifier", moduleIdentifier)
                .add("nameSpace", nameSpace)
                .add("features", features)
                .add("yangModuleIdentifiers", subModuleIdentifiers)
                .toString();
    }
}
