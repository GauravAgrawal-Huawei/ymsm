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

package org.onosproject.yang.gen.v1.yms.test.ytb.module.with.list.rev20160826.ytbmodulewithlist;

import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of find.
 */
public final class Find {

    private boolean yangAutoPrefixBoolean;

    /**
     * Creates an instance of find.
     */
    private Find() {
    }

    /**
     * Creates an instance of findForTypeYangAutoPrefixBoolean.
     *
     * @param value value of findForTypeYangAutoPrefixBoolean
     */
    public Find(boolean value) {
        this.yangAutoPrefixBoolean = value;
    }

    /**
     * Returns the object of findForTypeYangAutoPrefixBoolean.
     *
     * @param value value of findForTypeYangAutoPrefixBoolean
     * @return Object of findForTypeYangAutoPrefixBoolean
     */
    public static Find of(boolean value) {
        return new Find(value);
    }

    /**
     * Returns the attribute yangAutoPrefixBoolean.
     *
     * @return value of yangAutoPrefixBoolean
     */
    public boolean yangAutoPrefixBoolean() {
        return yangAutoPrefixBoolean;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yangAutoPrefixBoolean);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Find) {
            Find other = (Find) obj;
            return
                 Objects.equals(yangAutoPrefixBoolean, other.yangAutoPrefixBoolean);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("yangAutoPrefixBoolean", yangAutoPrefixBoolean)
            .toString();
    }

    /**
     * Returns the object of find fromString input String.
     *
     * @param valInString input String
     * @return Object of find
     */
    public static Find fromString(String valInString) {
        try {
            boolean tmpVal = Boolean.parseBoolean(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
