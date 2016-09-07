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

package org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826
            .ytbderivedtypewithbitsandbinary;

import java.util.Base64;
import java.util.Objects;

/**
 * Represents the implementation of derivedbinaryb.
 */
public final class Derivedbinaryb {

    private byte[] binary;

    /**
     * Creates an instance of derivedbinaryb.
     */
    private Derivedbinaryb() {
    }

    /**
     * Creates an instance of derivedbinarybForTypeBinary.
     *
     * @param value value of derivedbinarybForTypeBinary
     */
    public Derivedbinaryb(byte[] value) {
        this.binary = value;
    }

    /**
     * Returns the object of derivedbinarybForTypeBinary.
     *
     * @param value value of derivedbinarybForTypeBinary
     * @return Object of derivedbinarybForTypeBinary
     */
    public static Derivedbinaryb of(byte[] value) {
        return new Derivedbinaryb(value);
    }

    /**
     * Returns the attribute binary.
     *
     * @return value of binary
     */
    public byte[] binary() {
        return binary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(binary);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Derivedbinaryb) {
            Derivedbinaryb other = (Derivedbinaryb) obj;
            return
                 Objects.equals(binary, other.binary);
        }
        return false;
    }

    @Override
    public String toString() {
        return Base64.getEncoder().encodeToString(binary);
    }

    /**
     * Returns the object of derivedbinaryb fromString input String.
     *
     * @param valInString input String
     * @return Object of derivedbinaryb
     */
    public static Derivedbinaryb fromString(String valInString) {
        try {
            byte[] tmpVal = Base64.getDecoder().decode(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
