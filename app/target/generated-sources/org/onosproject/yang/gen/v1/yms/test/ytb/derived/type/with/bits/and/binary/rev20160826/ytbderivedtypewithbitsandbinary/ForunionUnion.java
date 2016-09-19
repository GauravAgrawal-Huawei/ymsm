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
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of forunionUnion.
 */
public final class ForunionUnion {

    private byte[] binary;
    private byte int8;

    /**
     * Creates an instance of forunionUnion.
     */
    private ForunionUnion() {
    }

    /**
     * Creates an instance of forunionUnionForTypeBinary.
     *
     * @param value value of forunionUnionForTypeBinary
     */
    public ForunionUnion(byte[] value) {
        this.binary = value;
    }

    /**
     * Creates an instance of forunionUnionForTypeInt8.
     *
     * @param value value of forunionUnionForTypeInt8
     */
    public ForunionUnion(byte value) {
        this.int8 = value;
    }

    /**
     * Returns the object of forunionUnionForTypeBinary.
     *
     * @param value value of forunionUnionForTypeBinary
     * @return Object of forunionUnionForTypeBinary
     */
    public static ForunionUnion of(byte[] value) {
        return new ForunionUnion(value);
    }

    /**
     * Returns the object of forunionUnionForTypeInt8.
     *
     * @param value value of forunionUnionForTypeInt8
     * @return Object of forunionUnionForTypeInt8
     */
    public static ForunionUnion of(byte value) {
        return new ForunionUnion(value);
    }

    /**
     * Returns the attribute binary.
     *
     * @return value of binary
     */
    public byte[] binary() {
        return binary;
    }

    /**
     * Returns the attribute int8.
     *
     * @return value of int8
     */
    public byte int8() {
        return int8;
    }

    @Override
    public int hashCode() {
        return Objects.hash(binary, int8);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ForunionUnion) {
            ForunionUnion other = (ForunionUnion) obj;
            return
                 Objects.equals(binary, other.binary) &&
                 Objects.equals(int8, other.int8);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .omitNullValues()
            .add("binary", binary)
            .add("int8", int8)
            .toString();
    }

    /**
     * Returns the object of forunionUnion fromString input String.
     *
     * @param valInString input String
     * @return Object of forunionUnion
     */
    public static ForunionUnion fromString(String valInString) {
        try {
            byte[] tmpVal = Base64.getDecoder().decode(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        try {
            byte tmpVal = Byte.parseByte(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
