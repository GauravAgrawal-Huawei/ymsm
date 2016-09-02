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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.ipprefix;

import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.Ipv4Prefix;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.Ipv6Prefix;
import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of ipPrefixUnion.
 */
public final class IpPrefixUnion {

    private Ipv4Prefix ipv4Prefix;
    private Ipv6Prefix ipv6Prefix;

    /**
     * Creates an instance of ipPrefixUnion.
     */
    private IpPrefixUnion() {
    }

    /**
     * Creates an instance of ipPrefixUnionForTypeIpv4Prefix.
     *
     * @param value value of ipPrefixUnionForTypeIpv4Prefix
     */
    public IpPrefixUnion(Ipv4Prefix value) {
        this.ipv4Prefix = value;
    }

    /**
     * Creates an instance of ipPrefixUnionForTypeIpv6Prefix.
     *
     * @param value value of ipPrefixUnionForTypeIpv6Prefix
     */
    public IpPrefixUnion(Ipv6Prefix value) {
        this.ipv6Prefix = value;
    }

    /**
     * Returns the object of ipPrefixUnionForTypeIpv4Prefix.
     *
     * @param value value of ipPrefixUnionForTypeIpv4Prefix
     * @return Object of ipPrefixUnionForTypeIpv4Prefix
     */
    public static IpPrefixUnion of(Ipv4Prefix value) {
        return new IpPrefixUnion(value);
    }

    /**
     * Returns the object of ipPrefixUnionForTypeIpv6Prefix.
     *
     * @param value value of ipPrefixUnionForTypeIpv6Prefix
     * @return Object of ipPrefixUnionForTypeIpv6Prefix
     */
    public static IpPrefixUnion of(Ipv6Prefix value) {
        return new IpPrefixUnion(value);
    }

    /**
     * Returns the attribute ipv4Prefix.
     *
     * @return value of ipv4Prefix
     */
    public Ipv4Prefix ipv4Prefix() {
        return ipv4Prefix;
    }

    /**
     * Returns the attribute ipv6Prefix.
     *
     * @return value of ipv6Prefix
     */
    public Ipv6Prefix ipv6Prefix() {
        return ipv6Prefix;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipv4Prefix, ipv6Prefix);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IpPrefixUnion) {
            IpPrefixUnion other = (IpPrefixUnion) obj;
            return
                 Objects.equals(ipv4Prefix, other.ipv4Prefix) &&
                 Objects.equals(ipv6Prefix, other.ipv6Prefix);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .omitNullValues()
            .add("ipv4Prefix", ipv4Prefix)
            .add("ipv6Prefix", ipv6Prefix)
            .toString();
    }

    /**
     * Returns the object of ipPrefixUnion fromString input String.
     *
     * @param valInString input String
     * @return Object of ipPrefixUnion
     */
    public static IpPrefixUnion fromString(String valInString) {
        try {
            Ipv4Prefix tmpVal = Ipv4Prefix.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        try {
            Ipv6Prefix tmpVal = Ipv6Prefix.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
