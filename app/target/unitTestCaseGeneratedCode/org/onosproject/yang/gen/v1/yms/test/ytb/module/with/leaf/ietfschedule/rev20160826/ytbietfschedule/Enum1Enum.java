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
 * Represents ENUM data of enum1Enum.
 */
public enum Enum1Enum {

    /**
     * Represents ten.
     */
    TEN(10, "ten"),

    /**
     * Represents hundred.
     */
    HUNDRED(100, "hundred"),

    /**
     * Represents thousand.
     */
    THOUSAND(1000, "thousand");

    private int enum1Enum;
    private String schemaName;

    /**
     * Creates an instance of enum1Enum.
     *
     * @param value value of enum1Enum
     */
    Enum1Enum(int value, String stringValue) {
        enum1Enum = value;
        schemaName = stringValue;
    }

    /**
     * Returns the object of enum1EnumForValue.
     *
     * @param value value of enum1EnumForValue
     * @return Object of enum1EnumForValue
     */
    public static Enum1Enum of(int value) {
        switch (value) {
            case 10:
                return Enum1Enum.TEN;
            case 100:
                return Enum1Enum.HUNDRED;
            case 1000:
                return Enum1Enum.THOUSAND;
            default :
                return null;
        }
    }

    /**
     * Returns the object of enum1EnumForSchemaName.
     *
     * @param value value of enum1EnumForSchemaName
     * @return Object of enum1EnumForSchemaName
     */
    public static Enum1Enum of(String value) {
        switch (value) {
            case "ten":
                return Enum1Enum.TEN;
            case "hundred":
                return Enum1Enum.HUNDRED;
            case "thousand":
                return Enum1Enum.THOUSAND;
            default :
                return null;
        }
    }
    /**
     * Returns the attribute enum1Enum.
     *
     * @return value of enum1Enum
     */
    public int enum1Enum() {
        return enum1Enum;
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
     * Returns the object of enum1Enum fromString input String.
     *
     * @param valInString input String
     * @return Object of enum1Enum
     */
    public static Enum1Enum fromString(String valInString) {
        try {
            int tmpVal = Integer.parseInt(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
