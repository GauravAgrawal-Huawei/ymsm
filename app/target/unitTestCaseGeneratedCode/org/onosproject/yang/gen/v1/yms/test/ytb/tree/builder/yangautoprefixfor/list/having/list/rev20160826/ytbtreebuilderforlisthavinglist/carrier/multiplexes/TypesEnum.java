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
        SPACE_DIVISION(0),

        /**
         * Represents frequencyDivision.
         */
        FREQUENCY_DIVISION(1),

        /**
         * Represents timeDivision.
         */
        TIME_DIVISION(3);

    private int typesEnum;

    /**
     * Creates an instance of typesEnum.
     *
     * @param value value of typesEnum
     */
    TypesEnum(int value) {
        typesEnum = value;
    }

    /**
     * Returns the object of typesEnumForTypeInt.
     *
     * @param value value of typesEnumForTypeInt
     * @return Object of typesEnumForTypeInt
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
     * Returns the attribute typesEnum.
     *
     * @return value of typesEnum
     */
    public int typesEnum() {
        return typesEnum;
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
