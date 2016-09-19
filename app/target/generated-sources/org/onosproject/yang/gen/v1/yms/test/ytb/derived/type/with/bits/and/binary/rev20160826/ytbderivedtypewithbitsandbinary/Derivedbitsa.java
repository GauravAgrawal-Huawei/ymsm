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

import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of derivedbitsa.
 */
public final class Derivedbitsa {

    private Derivedbitsb derivedbitsb;

    /**
     * Creates an instance of derivedbitsa.
     */
    private Derivedbitsa() {
    }

    /**
     * Creates an instance of derivedbitsaForTypeDerivedbitsb.
     *
     * @param value value of derivedbitsaForTypeDerivedbitsb
     */
    public Derivedbitsa(Derivedbitsb value) {
        this.derivedbitsb = value;
    }

    /**
     * Returns the object of derivedbitsaForTypeDerivedbitsb.
     *
     * @param value value of derivedbitsaForTypeDerivedbitsb
     * @return Object of derivedbitsaForTypeDerivedbitsb
     */
    public static Derivedbitsa of(Derivedbitsb value) {
        return new Derivedbitsa(value);
    }

    /**
     * Returns the attribute derivedbitsb.
     *
     * @return value of derivedbitsb
     */
    public Derivedbitsb derivedbitsb() {
        return derivedbitsb;
    }

    @Override
    public int hashCode() {
        return Objects.hash(derivedbitsb);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Derivedbitsa) {
            Derivedbitsa other = (Derivedbitsa) obj;
            return
                 Objects.equals(derivedbitsb, other.derivedbitsb);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("derivedbitsb", derivedbitsb)
            .toString();
    }

    /**
     * Returns the object of derivedbitsa fromString input String.
     *
     * @param valInString input String
     * @return Object of derivedbitsa
     */
    public static Derivedbitsa fromString(String valInString) {
        try {
            Derivedbitsb tmpVal = Derivedbitsb.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
