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

package org.onosproject.yang.gen.v1.ydt.enumtest.rev20160524.enumtest.enumlist;

/**
 * Represents ENUM data of enumleafEnum.
 */
public enum EnumleafEnum {

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

    private int enumleafEnum;
    private String schemaName;

    /**
     * Creates an instance of enumleafEnum.
     *
     * @param value value of enumleafEnum
     */
    EnumleafEnum(int value, String stringValue) {
        enumleafEnum = value;
        schemaName = stringValue;
    }

    /**
     * Returns the object of enumleafEnumForValue.
     *
     * @param value value of enumleafEnumForValue
     * @return Object of enumleafEnumForValue
     */
    public static EnumleafEnum of(int value) {
        switch (value) {
            case 10:
                return EnumleafEnum.TEN;
            case 100:
                return EnumleafEnum.HUNDRED;
            case 1000:
                return EnumleafEnum.THOUSAND;
            default :
                return null;
        }
    }

    /**
     * Returns the object of enumleafEnumForSchemaName.
     *
     * @param value value of enumleafEnumForSchemaName
     * @return Object of enumleafEnumForSchemaName
     */
    public static EnumleafEnum of(String value) {
        switch (value) {
            case "ten":
                return EnumleafEnum.TEN;
            case "hundred":
                return EnumleafEnum.HUNDRED;
            case "thousand":
                return EnumleafEnum.THOUSAND;
            default :
                return null;
        }
    }
    /**
     * Returns the attribute enumleafEnum.
     *
     * @return value of enumleafEnum
     */
    public int enumleafEnum() {
        return enumleafEnum;
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
     * Returns the object of enumleafEnum fromString input String.
     *
     * @param valInString input String
     * @return Object of enumleafEnum
     */
    public static EnumleafEnum fromString(String valInString) {
        try {
            int tmpVal = Integer.parseInt(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}