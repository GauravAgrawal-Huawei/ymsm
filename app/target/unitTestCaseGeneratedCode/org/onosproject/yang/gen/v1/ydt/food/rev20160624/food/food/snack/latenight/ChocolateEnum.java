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

package org.onosproject.yang.gen.v1.ydt.food.rev20160624.food.food.snack.latenight;

/**
 * Represents ENUM data of chocolateEnum.
 */
public enum ChocolateEnum {

        /**
         * Represents dark.
         */
        DARK(0),

        /**
         * Represents milk.
         */
        MILK(1),

        /**
         * Represents firstAvailable.
         */
        FIRST_AVAILABLE(2);

    private int chocolateEnum;

    /**
     * Creates an instance of chocolateEnum.
     *
     * @param value value of chocolateEnum
     */
    ChocolateEnum(int value) {
        chocolateEnum = value;
    }

    /**
     * Returns the object of chocolateEnumForTypeInt.
     *
     * @param value value of chocolateEnumForTypeInt
     * @return Object of chocolateEnumForTypeInt
     */
    public static ChocolateEnum of(int value) {
        switch (value) {
            case 0:
                return ChocolateEnum.DARK;
            case 1:
                return ChocolateEnum.MILK;
            case 2:
                return ChocolateEnum.FIRST_AVAILABLE;
            default :
                return null;
        }
    }

    /**
     * Returns the attribute chocolateEnum.
     *
     * @return value of chocolateEnum
     */
    public int chocolateEnum() {
        return chocolateEnum;
    }

    /**
     * Returns the object of chocolateEnum fromString input String.
     *
     * @param valInString input String
     * @return Object of chocolateEnum
     */
    public static ChocolateEnum fromString(String valInString) {
        try {
            int tmpVal = Integer.parseInt(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
