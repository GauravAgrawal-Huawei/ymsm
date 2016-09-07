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

import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of ipv6AddressNoZone.
 */
public final class Ipv6AddressNoZone {

    private Ipv6Address ipv6Address;

    /**
     * Creates an instance of ipv6AddressNoZone.
     */
    private Ipv6AddressNoZone() {
    }

    /**
     * Creates an instance of ipv6AddressNoZoneForTypeIpv6Address.
     *
     * @param value value of ipv6AddressNoZoneForTypeIpv6Address
     */
    public Ipv6AddressNoZone(Ipv6Address value) {
        this.ipv6Address = value;
    }

    /**
     * Returns the object of ipv6AddressNoZoneForTypeIpv6Address.
     *
     * @param value value of ipv6AddressNoZoneForTypeIpv6Address
     * @return Object of ipv6AddressNoZoneForTypeIpv6Address
     */
    public static Ipv6AddressNoZone of(Ipv6Address value) {
        return new Ipv6AddressNoZone(value);
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
        return Objects.hash(ipv6Address);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Ipv6AddressNoZone) {
            Ipv6AddressNoZone other = (Ipv6AddressNoZone) obj;
            return
                 Objects.equals(ipv6Address, other.ipv6Address);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("ipv6Address", ipv6Address)
            .toString();
    }

    /**
     * Returns the object of ipv6AddressNoZone fromString input String.
     *
     * @param valInString input String
     * @return Object of ipv6AddressNoZone
     */
    public static Ipv6AddressNoZone fromString(String valInString) {
        try {
            Ipv6Address tmpVal = Ipv6Address.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
