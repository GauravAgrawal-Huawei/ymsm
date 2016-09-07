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
 * Represents the implementation of portNumber.
 */
public final class PortNumber {

    private int uint16;

    /**
     * Creates an instance of portNumber.
     */
    private PortNumber() {
    }

    /**
     * Creates an instance of portNumberForTypeUint16.
     *
     * @param value value of portNumberForTypeUint16
     */
    public PortNumber(int value) {
        this.uint16 = value;
    }

    /**
     * Returns the object of portNumberForTypeUint16.
     *
     * @param value value of portNumberForTypeUint16
     * @return Object of portNumberForTypeUint16
     */
    public static PortNumber of(int value) {
        return new PortNumber(value);
    }

    /**
     * Returns the attribute uint16.
     *
     * @return value of uint16
     */
    public int uint16() {
        return uint16;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uint16);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PortNumber) {
            PortNumber other = (PortNumber) obj;
            return
                 Objects.equals(uint16, other.uint16);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("uint16", uint16)
            .toString();
    }

    /**
     * Returns the object of portNumber fromString input String.
     *
     * @param valInString input String
     * @return Object of portNumber
     */
    public static PortNumber fromString(String valInString) {
        try {
            int tmpVal = Integer.parseInt(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
