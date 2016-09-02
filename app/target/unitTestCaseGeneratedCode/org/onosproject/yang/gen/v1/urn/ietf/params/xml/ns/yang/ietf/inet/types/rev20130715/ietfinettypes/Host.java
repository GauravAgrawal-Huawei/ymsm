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

import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.host
            .HostUnion;
import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of host.
 */
public final class Host {

    private HostUnion union;

    /**
     * Creates an instance of host.
     */
    private Host() {
    }

    /**
     * Creates an instance of hostForTypeUnion.
     *
     * @param value value of hostForTypeUnion
     */
    public Host(HostUnion value) {
        this.union = value;
    }

    /**
     * Returns the object of hostForTypeUnion.
     *
     * @param value value of hostForTypeUnion
     * @return Object of hostForTypeUnion
     */
    public static Host of(HostUnion value) {
        return new Host(value);
    }

    /**
     * Returns the attribute union.
     *
     * @return value of union
     */
    public HostUnion union() {
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
        if (obj instanceof Host) {
            Host other = (Host) obj;
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
     * Returns the object of host fromString input String.
     *
     * @param valInString input String
     * @return Object of host
     */
    public static Host fromString(String valInString) {
        try {
            HostUnion tmpVal = HostUnion.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
