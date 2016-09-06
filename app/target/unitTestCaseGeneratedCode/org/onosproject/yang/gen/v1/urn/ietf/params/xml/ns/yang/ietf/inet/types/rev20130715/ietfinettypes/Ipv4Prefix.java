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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes;

import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of ipv4Prefix.
 */
public final class Ipv4Prefix {

    private String string;

    /**
     * Creates an instance of ipv4Prefix.
     */
    private Ipv4Prefix() {
    }

    /**
     * Creates an instance of ipv4PrefixForTypeString.
     *
     * @param value value of ipv4PrefixForTypeString
     */
    public Ipv4Prefix(String value) {
        this.string = value;
    }

    /**
     * Returns the object of ipv4PrefixForTypeString.
     *
     * @param value value of ipv4PrefixForTypeString
     * @return Object of ipv4PrefixForTypeString
     */
    public static Ipv4Prefix of(String value) {
        return new Ipv4Prefix(value);
    }

    /**
     * Returns the attribute string.
     *
     * @return value of string
     */
    public String string() {
        return string;
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Ipv4Prefix) {
            Ipv4Prefix other = (Ipv4Prefix) obj;
            return
                 Objects.equals(string, other.string);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("string", string)
            .toString();
    }

    /**
     * Returns the object of ipv4Prefix fromString input String.
     *
     * @param valInString input String
     * @return Object of ipv4Prefix
     */
    public static Ipv4Prefix fromString(String valInString) {
        try {
            String tmpVal = (valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
