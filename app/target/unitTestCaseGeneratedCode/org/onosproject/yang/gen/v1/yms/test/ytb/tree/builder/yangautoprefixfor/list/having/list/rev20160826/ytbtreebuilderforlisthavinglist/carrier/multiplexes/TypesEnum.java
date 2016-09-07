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

package org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826
            .ytbtreebuilderforlisthavinglist.carrier.multiplexes;

/**
 * Represents ENUM data of typesEnum.
 */
public enum TypesEnum {

    /**
     * Represents spaceDivision.
     */
    SPACE_DIVISION(0, "space-division"),

    /**
     * Represents frequencyDivision.
     */
    FREQUENCY_DIVISION(1, "frequency-division"),

    /**
     * Represents timeDivision.
     */
    TIME_DIVISION(3, "time-division");

    private int typesEnum;
    private String schemaName;

    /**
     * Creates an instance of typesEnum.
     *
     * @param value value of typesEnum
     */
    TypesEnum(int value, String stringValue) {
        typesEnum = value;
        schemaName = stringValue;
    }

    /**
     * Returns the object of typesEnumForValue.
     *
     * @param value value of typesEnumForValue
     * @return Object of typesEnumForValue
     */
    public static TypesEnum of(int value) {
        switch (value) {
            case 0:
                return TypesEnum.SPACE_DIVISION;
            case 1:
                return TypesEnum.FREQUENCY_DIVISION;
            case 3:
                return TypesEnum.TIME_DIVISION;
            default :
                return null;
        }
    }

    /**
     * Returns the object of typesEnumForSchemaName.
     *
     * @param value value of typesEnumForSchemaName
     * @return Object of typesEnumForSchemaName
     */
    public static TypesEnum of(String value) {
        switch (value) {
            case "space-division":
                return TypesEnum.SPACE_DIVISION;
            case "frequency-division":
                return TypesEnum.FREQUENCY_DIVISION;
            case "time-division":
                return TypesEnum.TIME_DIVISION;
            default :
                return null;
        }
    }
    /**
     * Returns the attribute typesEnum.
     *
     * @return value of typesEnum
     */
    public int typesEnum() {
        return typesEnum;
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
     * Returns the object of typesEnum fromString input String.
     *
     * @param valInString input String
     * @return Object of typesEnum
     */
    public static TypesEnum fromString(String valInString) {
        try {
            int tmpVal = Integer.parseInt(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
