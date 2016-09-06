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

import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.ipaddress
            .IpAddressUnion;
import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of ipAddress.
 */
public final class IpAddress {

    private IpAddressUnion union;

    /**
     * Creates an instance of ipAddress.
     */
    private IpAddress() {
    }

    /**
     * Creates an instance of ipAddressForTypeUnion.
     *
     * @param value value of ipAddressForTypeUnion
     */
    public IpAddress(IpAddressUnion value) {
        this.union = value;
    }

    /**
     * Returns the object of ipAddressForTypeUnion.
     *
     * @param value value of ipAddressForTypeUnion
     * @return Object of ipAddressForTypeUnion
     */
    public static IpAddress of(IpAddressUnion value) {
        return new IpAddress(value);
    }

    /**
     * Returns the attribute union.
     *
     * @return value of union
     */
    public IpAddressUnion union() {
        return union;
    }

    @Override
    public int hashCode() {
        return Objects.hash(union);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IpAddress) {
            IpAddress other = (IpAddress) obj;
            return
                 Objects.equals(union, other.union);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("union", union)
            .toString();
    }

    /**
     * Returns the object of ipAddress fromString input String.
     *
     * @param valInString input String
     * @return Object of ipAddress
     */
    public static IpAddress fromString(String valInString) {
        try {
            IpAddressUnion tmpVal = IpAddressUnion.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
