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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes
            .ipaddressnozone;

import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes
            .Ipv4AddressNoZone;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes
            .Ipv6AddressNoZone;
import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of ipAddressNoZoneUnion.
 */
public final class IpAddressNoZoneUnion {

    private Ipv4AddressNoZone ipv4AddressNoZone;
    private Ipv6AddressNoZone ipv6AddressNoZone;

    /**
     * Creates an instance of ipAddressNoZoneUnion.
     */
    private IpAddressNoZoneUnion() {
    }

    /**
     * Creates an instance of ipAddressNoZoneUnionForTypeIpv4AddressNoZone.
     *
     * @param value value of ipAddressNoZoneUnionForTypeIpv4AddressNoZone
     */
    public IpAddressNoZoneUnion(Ipv4AddressNoZone value) {
        this.ipv4AddressNoZone = value;
    }

    /**
     * Creates an instance of ipAddressNoZoneUnionForTypeIpv6AddressNoZone.
     *
     * @param value value of ipAddressNoZoneUnionForTypeIpv6AddressNoZone
     */
    public IpAddressNoZoneUnion(Ipv6AddressNoZone value) {
        this.ipv6AddressNoZone = value;
    }

    /**
     * Returns the object of ipAddressNoZoneUnionForTypeIpv4AddressNoZone.
     *
     * @param value value of ipAddressNoZoneUnionForTypeIpv4AddressNoZone
     * @return Object of ipAddressNoZoneUnionForTypeIpv4AddressNoZone
     */
    public static IpAddressNoZoneUnion of(Ipv4AddressNoZone value) {
        return new IpAddressNoZoneUnion(value);
    }

    /**
     * Returns the object of ipAddressNoZoneUnionForTypeIpv6AddressNoZone.
     *
     * @param value value of ipAddressNoZoneUnionForTypeIpv6AddressNoZone
     * @return Object of ipAddressNoZoneUnionForTypeIpv6AddressNoZone
     */
    public static IpAddressNoZoneUnion of(Ipv6AddressNoZone value) {
        return new IpAddressNoZoneUnion(value);
    }

    /**
     * Returns the attribute ipv4AddressNoZone.
     *
     * @return value of ipv4AddressNoZone
     */
    public Ipv4AddressNoZone ipv4AddressNoZone() {
        return ipv4AddressNoZone;
    }

    /**
     * Returns the attribute ipv6AddressNoZone.
     *
     * @return value of ipv6AddressNoZone
     */
    public Ipv6AddressNoZone ipv6AddressNoZone() {
        return ipv6AddressNoZone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipv4AddressNoZone, ipv6AddressNoZone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IpAddressNoZoneUnion) {
            IpAddressNoZoneUnion other = (IpAddressNoZoneUnion) obj;
            return
                 Objects.equals(ipv4AddressNoZone, other.ipv4AddressNoZone) &&
                 Objects.equals(ipv6AddressNoZone, other.ipv6AddressNoZone);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .omitNullValues()
            .add("ipv4AddressNoZone", ipv4AddressNoZone)
            .add("ipv6AddressNoZone", ipv6AddressNoZone)
            .toString();
    }

    /**
     * Returns the object of ipAddressNoZoneUnion fromString input String.
     *
     * @param valInString input String
     * @return Object of ipAddressNoZoneUnion
     */
    public static IpAddressNoZoneUnion fromString(String valInString) {
        try {
            Ipv4AddressNoZone tmpVal = Ipv4AddressNoZone.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        try {
            Ipv6AddressNoZone tmpVal = Ipv6AddressNoZone.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
