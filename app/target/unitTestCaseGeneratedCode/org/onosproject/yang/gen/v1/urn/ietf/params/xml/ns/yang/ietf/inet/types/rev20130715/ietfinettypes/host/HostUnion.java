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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.host;

import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.DomainName;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.IpAddress;
import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of hostUnion.
 */
public final class HostUnion {

    private IpAddress ipAddress;
    private DomainName domainName;

    /**
     * Creates an instance of hostUnion.
     */
    private HostUnion() {
    }

    /**
     * Creates an instance of hostUnionForTypeIpAddress.
     *
     * @param value value of hostUnionForTypeIpAddress
     */
    public HostUnion(IpAddress value) {
        this.ipAddress = value;
    }

    /**
     * Creates an instance of hostUnionForTypeDomainName.
     *
     * @param value value of hostUnionForTypeDomainName
     */
    public HostUnion(DomainName value) {
        this.domainName = value;
    }

    /**
     * Returns the object of hostUnionForTypeIpAddress.
     *
     * @param value value of hostUnionForTypeIpAddress
     * @return Object of hostUnionForTypeIpAddress
     */
    public static HostUnion of(IpAddress value) {
        return new HostUnion(value);
    }

    /**
     * Returns the object of hostUnionForTypeDomainName.
     *
     * @param value value of hostUnionForTypeDomainName
     * @return Object of hostUnionForTypeDomainName
     */
    public static HostUnion of(DomainName value) {
        return new HostUnion(value);
    }

    /**
     * Returns the attribute ipAddress.
     *
     * @return value of ipAddress
     */
    public IpAddress ipAddress() {
        return ipAddress;
    }

    /**
     * Returns the attribute domainName.
     *
     * @return value of domainName
     */
    public DomainName domainName() {
        return domainName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, domainName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HostUnion) {
            HostUnion other = (HostUnion) obj;
            return
                 Objects.equals(ipAddress, other.ipAddress) &&
                 Objects.equals(domainName, other.domainName);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .omitNullValues()
            .add("ipAddress", ipAddress)
            .add("domainName", domainName)
            .toString();
    }

    /**
     * Returns the object of hostUnion fromString input String.
     *
     * @param valInString input String
     * @return Object of hostUnion
     */
    public static HostUnion fromString(String valInString) {
        try {
            IpAddress tmpVal = IpAddress.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        try {
            DomainName tmpVal = DomainName.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
