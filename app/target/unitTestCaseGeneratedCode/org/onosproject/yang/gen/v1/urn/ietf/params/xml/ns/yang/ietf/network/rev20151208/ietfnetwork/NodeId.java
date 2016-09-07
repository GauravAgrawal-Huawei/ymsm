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

package org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ietfnetwork;

import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.Uri;
import java.util.Objects;
import com.google.common.base.MoreObjects;

/**
 * Represents the implementation of nodeId.
 */
public final class NodeId {

    private Uri uri;

    /**
     * Creates an instance of nodeId.
     */
    private NodeId() {
    }

    /**
     * Creates an instance of nodeIdForTypeUri.
     *
     * @param value value of nodeIdForTypeUri
     */
    public NodeId(Uri value) {
        this.uri = value;
    }

    /**
     * Returns the object of nodeIdForTypeUri.
     *
     * @param value value of nodeIdForTypeUri
     * @return Object of nodeIdForTypeUri
     */
    public static NodeId of(Uri value) {
        return new NodeId(value);
    }

    /**
     * Returns the attribute uri.
     *
     * @return value of uri
     */
    public Uri uri() {
        return uri;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NodeId) {
            NodeId other = (NodeId) obj;
            return
                 Objects.equals(uri, other.uri);
        }
        return false;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
            .add("uri", uri)
            .toString();
    }

    /**
     * Returns the object of nodeId fromString input String.
     *
     * @param valInString input String
     * @return Object of nodeId
     */
    public static NodeId fromString(String valInString) {
        try {
            Uri tmpVal = Uri.fromString(valInString);
            return of(tmpVal);
        } catch (Exception e) {
        }
        return null;
    }
}
