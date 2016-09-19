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

import java.util.BitSet;
import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of derivedbitsb.
 */
public final class Derivedbitsb {

    private BitSet bits;

    /**
     * Creates an instance of derivedbitsb.
     */
    private Derivedbitsb() {
    }

    /**
     * Creates an instance of derivedbitsbForTypeBits.
     *
     * @param value value of derivedbitsbForTypeBits
     */
    public Derivedbitsb(BitSet value) {
        this.bits = value;
    }

    /**
     * Returns the object of derivedbitsbForTypeBits.
     *
     * @param value value of derivedbitsbForTypeBits
     * @return Object of derivedbitsbForTypeBits
     */
    public static Derivedbitsb of(BitSet value) {
        return new Derivedbitsb(value);
    }

    /**
     * Returns the attribute bits.
     *
     * @return value of bits
     */
    public BitSet bits() {
        return bits;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bits);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Derivedbitsb) {
            Derivedbitsb other = (Derivedbitsb) obj;
            return
                 Objects.equals(bits, other.bits);
        }
        return false;
    }

    @Override
    public String toString() {
        return bits.toString();
    }

    /**
     * Returns the object of derivedbitsb fromString input String.
     *
     * @param valInString input String
     * @return Object of derivedbitsb
     */
    public static Derivedbitsb fromString(String valInString) {
        try {
            BitSet tmpVal = new BitSet();
            valInString = valInString.replace('{', ' ');
            valInString = valInString.replace('}', ' ');
            valInString = valInString.trim();
            String[] bitsTemp = valInString.split(",", 0);
            for (String bitTemp : bitsTemp) {
                bitTemp = bitTemp.trim();
                tmpVal.set(Integer.parseInt(bitTemp));
            }
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
