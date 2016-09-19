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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.ipaddress;

import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.Ipv4Address;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.Ipv6Address;
import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of ipAddressUnion.
 */
public final class IpAddressUnion {

    private Ipv4Address ipv4Address;
    private Ipv6Address ipv6Address;

    /**
     * Creates an instance of ipAddressUnion.
     */
    private IpAddressUnion() {
    }

    /**
     * Creates an instance of ipAddressUnionForTypeIpv4Address.
     *
     * @param value value of ipAddressUnionForTypeIpv4Address
     */
    public IpAddressUnion(Ipv4Address value) {
        this.ipv4Address = value;
    }

    /**
     * Creates an instance of ipAddressUnionForTypeIpv6Address.
     *
     * @param value value of ipAddressUnionForTypeIpv6Address
     */
    public IpAddressUnion(Ipv6Address value) {
        this.ipv6Address = value;
    }

    /**
     * Returns the object of ipAddressUnionForTypeIpv4Address.
     *
     * @param value value of ipAddressUnionForTypeIpv4Address
     * @return Object of ipAddressUnionForTypeIpv4Address
     */
    public static IpAddressUnion of(Ipv4Address value) {
        return new IpAddressUnion(value);
    }

    /**
     * Returns the object of ipAddressUnionForTypeIpv6Address.
     *
     * @param value value of ipAddressUnionForTypeIpv6Address
     * @return Object of ipAddressUnionForTypeIpv6Address
     */
    public static IpAddressUnion of(Ipv6Address value) {
        return new IpAddressUnion(value);
    }

    /**
     * Returns the attribute ipv4Address.
     *
     * @return value of ipv4Address
     */
    public Ipv4Address ipv4Address() {
        return ipv4Address;
    }

    /**
     * Returns the attribute ipv6Address.
     *
     * @return value of ipv6Address
     */
    public Ipv6Address ipv6Address() {
        return ipv6Address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipv4Address, ipv6Address);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IpAddressUnion) {
            IpAddressUnion other = (IpAddressUnion) obj;
            return
                 Objects.equals(ipv4Address, other.ipv4Address) &&
                 Objects.equals(ipv6Address, other.ipv6Address);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .omitNullValues()
            .add("ipv4Address", ipv4Address)
            .add("ipv6Address", ipv6Address)
            .toString();
    }

    /**
     * Returns the object of ipAddressUnion fromString input String.
     *
     * @param valInString input String
     * @return Object of ipAddressUnion
     */
    public static IpAddressUnion fromString(String valInString) {
        try {
            Ipv4Address tmpVal = Ipv4Address.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        try {
            Ipv6Address tmpVal = Ipv6Address.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
