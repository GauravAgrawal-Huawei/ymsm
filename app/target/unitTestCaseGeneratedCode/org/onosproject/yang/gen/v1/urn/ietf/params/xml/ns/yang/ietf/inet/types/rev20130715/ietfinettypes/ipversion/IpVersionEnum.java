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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.ipversion;

/**
 * Represents ENUM data of ipVersionEnum.
 */
public enum IpVersionEnum {

        /**
         * Represents unknown.
         */
        UNKNOWN(0),

        /**
         * Represents ipv4.
         */
        IPV4(1),

        /**
         * Represents ipv6.
         */
        IPV6(2);

    private int ipVersionEnum;

    /**
     * Creates an instance of ipVersionEnum.
     *
     * @param value value of ipVersionEnum
     */
    IpVersionEnum(int value) {
        ipVersionEnum = value;
    }

    /**
     * Returns the object of ipVersionEnumForTypeInt.
     *
     * @param value value of ipVersionEnumForTypeInt
     * @return Object of ipVersionEnumForTypeInt
     */
    public static IpVersionEnum of(int value) {
        switch (value) {
            case 0:
                return IpVersionEnum.UNKNOWN;
            case 1:
                return IpVersionEnum.IPV4;
            case 2:
                return IpVersionEnum.IPV6;
            default :
                return null;
        }
    }

    /**
     * Returns the attribute ipVersionEnum.
     *
     * @return value of ipVersionEnum
     */
    public int ipVersionEnum() {
        return ipVersionEnum;
    }

    /**
     * Returns the object of ipVersionEnum fromString input String.
     *
     * @param valInString input String
     * @return Object of ipVersionEnum
     */
    public static IpVersionEnum fromString(String valInString) {
        try {
            int tmpVal = Integer.parseInt(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
