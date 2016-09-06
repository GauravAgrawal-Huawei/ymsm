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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes;

import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.ipversion
            .IpVersionEnum;
import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of ipVersion.
 */
public final class IpVersion {

    private IpVersionEnum enumeration;

    /**
     * Creates an instance of ipVersion.
     */
    private IpVersion() {
    }

    /**
     * Creates an instance of ipVersionForTypeEnumeration.
     *
     * @param value value of ipVersionForTypeEnumeration
     */
    public IpVersion(IpVersionEnum value) {
        this.enumeration = value;
    }

    /**
     * Returns the object of ipVersionForTypeEnumeration.
     *
     * @param value value of ipVersionForTypeEnumeration
     * @return Object of ipVersionForTypeEnumeration
     */
    public static IpVersion of(IpVersionEnum value) {
        return new IpVersion(value);
    }

    /**
     * Returns the attribute enumeration.
     *
     * @return value of enumeration
     */
    public IpVersionEnum enumeration() {
        return enumeration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(enumeration);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IpVersion) {
            IpVersion other = (IpVersion) obj;
            return
                 Objects.equals(enumeration, other.enumeration);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("enumeration", enumeration)
            .toString();
    }

    /**
     * Returns the object of ipVersion fromString input String.
     *
     * @param valInString input String
     * @return Object of ipVersion
     */
    public static IpVersion fromString(String valInString) {
        try {
            IpVersionEnum tmpVal = IpVersionEnum.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
