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

import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes
            .ipaddressnozone.IpAddressNoZoneUnion;
import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of ipAddressNoZone.
 */
public final class IpAddressNoZone {

    private IpAddressNoZoneUnion union;

    /**
     * Creates an instance of ipAddressNoZone.
     */
    private IpAddressNoZone() {
    }

    /**
     * Creates an instance of ipAddressNoZoneForTypeUnion.
     *
     * @param value value of ipAddressNoZoneForTypeUnion
     */
    public IpAddressNoZone(IpAddressNoZoneUnion value) {
        this.union = value;
    }

    /**
     * Returns the object of ipAddressNoZoneForTypeUnion.
     *
     * @param value value of ipAddressNoZoneForTypeUnion
     * @return Object of ipAddressNoZoneForTypeUnion
     */
    public static IpAddressNoZone of(IpAddressNoZoneUnion value) {
        return new IpAddressNoZone(value);
    }

    /**
     * Returns the attribute union.
     *
     * @return value of union
     */
    public IpAddressNoZoneUnion union() {
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
        if (obj instanceof IpAddressNoZone) {
            IpAddressNoZone other = (IpAddressNoZone) obj;
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
     * Returns the object of ipAddressNoZone fromString input String.
     *
     * @param valInString input String
     * @return Object of ipAddressNoZone
     */
    public static IpAddressNoZone fromString(String valInString) {
        try {
            IpAddressNoZoneUnion tmpVal = IpAddressNoZoneUnion.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
