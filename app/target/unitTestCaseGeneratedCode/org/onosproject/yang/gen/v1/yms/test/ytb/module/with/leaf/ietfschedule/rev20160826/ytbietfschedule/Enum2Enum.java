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

package org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826.ytbietfschedule;

/**
 * Represents ENUM data of enum2Enum.
 */
public enum Enum2Enum {

    /**
     * Represents ten10.
     */
    TEN_10(10, "ten-10"),

    /**
     * Represents hundred100.
     */
    HUNDRED_100(100, "hundred-100"),

    /**
     * Represents thousand1000.
     */
    THOUSAND_1000(1000, "thousand-1000");

    private int enum2Enum;
    private String schemaName;

    /**
     * Creates an instance of enum2Enum.
     *
     * @param value value of enum2Enum
     */
    Enum2Enum(int value, String stringValue) {
        enum2Enum = value;
        schemaName = stringValue;
    }

    /**
     * Returns the object of enum2EnumForValue.
     *
     * @param value value of enum2EnumForValue
     * @return Object of enum2EnumForValue
     */
    public static Enum2Enum of(int value) {
        switch (value) {
            case 10:
                return Enum2Enum.TEN_10;
            case 100:
                return Enum2Enum.HUNDRED_100;
            case 1000:
                return Enum2Enum.THOUSAND_1000;
            default :
                return null;
        }
    }

    /**
     * Returns the object of enum2EnumForSchemaName.
     *
     * @param value value of enum2EnumForSchemaName
     * @return Object of enum2EnumForSchemaName
     */
    public static Enum2Enum of(String value) {
        switch (value) {
            case "ten-10":
                return Enum2Enum.TEN_10;
            case "hundred-100":
                return Enum2Enum.HUNDRED_100;
            case "thousand-1000":
                return Enum2Enum.THOUSAND_1000;
            default :
                return null;
        }
    }
    /**
     * Returns the attribute enum2Enum.
     *
     * @return value of enum2Enum
     */
    public int enum2Enum() {
        return enum2Enum;
    }

    /**
     * Returns the attribute schemaName.
     *
     * @return value of schemaName
     */
    public String schemaName() {
        return schemaName;
    }

    /**
     * Returns the object of enum2Enum fromString input String.
     *
     * @param valInString input String
     * @return Object of enum2Enum
     */
    public static Enum2Enum fromString(String valInString) {
        try {
            int tmpVal = Integer.parseInt(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
