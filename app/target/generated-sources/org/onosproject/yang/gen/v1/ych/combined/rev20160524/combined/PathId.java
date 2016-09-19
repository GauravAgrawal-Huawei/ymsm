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

package org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined;

import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of pathId.
 */
public final class PathId {

    private long uint32;

    /**
     * Creates an instance of pathId.
     */
    private PathId() {
    }

    /**
     * Creates an instance of pathIdForTypeUint32.
     *
     * @param value value of pathIdForTypeUint32
     */
    public PathId(long value) {
        this.uint32 = value;
    }

    /**
     * Returns the object of pathIdForTypeUint32.
     *
     * @param value value of pathIdForTypeUint32
     * @return Object of pathIdForTypeUint32
     */
    public static PathId of(long value) {
        return new PathId(value);
    }

    /**
     * Returns the attribute uint32.
     *
     * @return value of uint32
     */
    public long uint32() {
        return uint32;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uint32);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PathId) {
            PathId other = (PathId) obj;
            return
                 Objects.equals(uint32, other.uint32);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("uint32", uint32)
            .toString();
    }

    /**
     * Returns the object of pathId fromString input String.
     *
     * @param valInString input String
     * @return Object of pathId
     */
    public static PathId fromString(String valInString) {
        try {
            long tmpVal = Long.parseLong(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
