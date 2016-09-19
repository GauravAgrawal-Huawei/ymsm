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
 * Represents the implementation of derivedbinarya.
 */
public final class Derivedbinarya {

    private Derivedbinaryb derivedbinaryb;

    /**
     * Creates an instance of derivedbinarya.
     */
    private Derivedbinarya() {
    }

    /**
     * Creates an instance of derivedbinaryaForTypeDerivedbinaryb.
     *
     * @param value value of derivedbinaryaForTypeDerivedbinaryb
     */
    public Derivedbinarya(Derivedbinaryb value) {
        this.derivedbinaryb = value;
    }

    /**
     * Returns the object of derivedbinaryaForTypeDerivedbinaryb.
     *
     * @param value value of derivedbinaryaForTypeDerivedbinaryb
     * @return Object of derivedbinaryaForTypeDerivedbinaryb
     */
    public static Derivedbinarya of(Derivedbinaryb value) {
        return new Derivedbinarya(value);
    }

    /**
     * Returns the attribute derivedbinaryb.
     *
     * @return value of derivedbinaryb
     */
    public Derivedbinaryb derivedbinaryb() {
        return derivedbinaryb;
    }

    @Override
    public int hashCode() {
        return Objects.hash(derivedbinaryb);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Derivedbinarya) {
            Derivedbinarya other = (Derivedbinarya) obj;
            return
                 Objects.equals(derivedbinaryb, other.derivedbinaryb);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("derivedbinaryb", derivedbinaryb)
            .toString();
    }

    /**
     * Returns the object of derivedbinarya fromString input String.
     *
     * @param valInString input String
     * @return Object of derivedbinarya
     */
    public static Derivedbinarya fromString(String valInString) {
        try {
            Derivedbinaryb tmpVal = Derivedbinaryb.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
