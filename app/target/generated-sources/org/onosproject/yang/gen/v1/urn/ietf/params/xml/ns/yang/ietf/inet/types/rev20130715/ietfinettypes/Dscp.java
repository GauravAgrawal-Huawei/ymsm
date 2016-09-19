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
 * Represents the implementation of dscp.
 */
public final class Dscp {

    private short uint8;

    /**
     * Creates an instance of dscp.
     */
    private Dscp() {
    }

    /**
     * Creates an instance of dscpForTypeUint8.
     *
     * @param value value of dscpForTypeUint8
     */
    public Dscp(short value) {
        this.uint8 = value;
    }

    /**
     * Returns the object of dscpForTypeUint8.
     *
     * @param value value of dscpForTypeUint8
     * @return Object of dscpForTypeUint8
     */
    public static Dscp of(short value) {
        return new Dscp(value);
    }

    /**
     * Returns the attribute uint8.
     *
     * @return value of uint8
     */
    public short uint8() {
        return uint8;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uint8);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Dscp) {
            Dscp other = (Dscp) obj;
            return
                 Objects.equals(uint8, other.uint8);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("uint8", uint8)
            .toString();
    }

    /**
     * Returns the object of dscp fromString input String.
     *
     * @param valInString input String
     * @return Object of dscp
     */
    public static Dscp fromString(String valInString) {
        try {
            short tmpVal = Short.parseShort(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
