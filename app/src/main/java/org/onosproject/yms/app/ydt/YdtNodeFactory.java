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

package org.onosproject.yms.app.ydt;

import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yangutils.datamodel.YangSchemaNodeType;
import org.onosproject.yms.app.ydt.exceptions.YdtException;

import static org.onosproject.yangutils.datamodel.YangSchemaNodeType.YANG_MULTI_INSTANCE_LEAF_NODE;
import static org.onosproject.yangutils.datamodel.YangSchemaNodeType.YANG_MULTI_INSTANCE_NODE;
import static org.onosproject.yangutils.datamodel.YangSchemaNodeType.YANG_SINGLE_INSTANCE_LEAF_NODE;
import static org.onosproject.yangutils.datamodel.YangSchemaNodeType.YANG_SINGLE_INSTANCE_NODE;

/**
 * Represents an YANG node factory to create different types of YANG data tree
 * node.
 */
final class YdtNodeFactory {

    /**
     * Utility factory class, hence the object creation is forbidden.
     */
    private YdtNodeFactory() {
    }

    /**
     * Returns a YANG data tree node for a given name, set of values and
     * instance type.
     *
     * @param id          dataNodeIdentifier of data tree node
     * @param schemaNode  data node as per YANG schema metadata
     * @param cardinality requested cardinality of node
     * @param callType    identify the call type
     * @return YANG data tree node
     */
    protected static YdtNode getNode(YangSchemaNodeIdentifier id,
                                     YangSchemaNode schemaNode,
                                     RequestedCardinality cardinality,
                                     RequestedCallType callType) {

        YdtNode newNode;
        YangSchemaNodeType nodeType = schemaNode
                .getYangSchemaNodeType();

        switch (cardinality) {

            case UNKNOWN:
                /*
                 * if requested node type is UNKNOWN, check corresponding
                 * yang data node type and create respective type node.
                 */
                newNode = getYangSchemaNodeTypeSpecificContext(
                        id, nodeType, callType);
                break;

            /*
             *if requested node type is specified and it exist as node of some
             * other type in data model then throw exception
             */
            case SINGLE_INSTANCE:
                if (nodeType == YANG_SINGLE_INSTANCE_NODE) {
                    newNode = new YdtSingleInstanceNode(id);
                    break;
                } else {
                    throw new YdtException(
                            "Schema node with name " + id.getName() +
                                    " doesn't exist.");
                }

            case MULTI_INSTANCE:
                if (nodeType == YANG_MULTI_INSTANCE_NODE) {
                    newNode = new YdtMultiInstanceNode(id);
                    break;
                } else {
                    throw new YdtException(
                            "Schema node with name " + id.getName() +
                                    " doesn't exist.");
                }

            case SINGLE_INSTANCE_LEAF:
                if (nodeType == YANG_SINGLE_INSTANCE_LEAF_NODE) {
                    newNode = new YdtSingleInstanceLeafNode(id);
                    break;
                } else {
                    throw new YdtException(
                            "Schema node with name " + id.getName() +
                                    " doesn't exist.");
                }

            case MULTI_INSTANCE_LEAF:
                if (nodeType == YANG_MULTI_INSTANCE_LEAF_NODE) {
                    newNode = new YdtMultiInstanceLeafNode(id);
                    break;
                } else {
                    throw new YdtException(
                            "Schema node with name " + id.getName() +
                                    " doesn't exist.");
                }

            default:
                throw new YdtException(
                        "Schema node with name " + id.getName() +
                                " doesn't exist.");
        }

        // set reference of yang data node in the requested node.
        newNode.setYangSchemaNode(schemaNode);

        return newNode;
    }

    /**
     * Creates Yang data tree node of YangSchemaNode type specific for
     * requestedCardinality of type UNKNOWN and returns the same.
     *
     * @param id       node identifier of data tree node
     * @param nodeType schema node type as per YANG schema metadata
     * @param callType identify the call type
     * @return YANG data tree node
     */
    private static YdtNode getYangSchemaNodeTypeSpecificContext(
            YangSchemaNodeIdentifier id,
            YangSchemaNodeType nodeType,
            RequestedCallType callType) {
        switch (callType) {
            case LEAF:
                switch (nodeType) {

                    case YANG_SINGLE_INSTANCE_LEAF_NODE:
                        return new YdtSingleInstanceLeafNode(id);

                    case YANG_MULTI_INSTANCE_LEAF_NODE:
                        return new YdtMultiInstanceLeafNode(id);

                    default:
                        throw new YdtException(
                                "Schema node with name " + id.getName() +
                                        " doesn't exist.");
                }

            case OTHER:
                switch (nodeType) {

                    case YANG_SINGLE_INSTANCE_NODE:
                        return new YdtSingleInstanceNode(id);

                    case YANG_MULTI_INSTANCE_NODE:
                        return new YdtMultiInstanceNode(id);

                    default:
                        throw new YdtException(
                                "Schema node with name " + id.getName() +
                                        " doesn't exist.");
                }
            case UNKNOWN:
                switch (nodeType) {

                    case YANG_MULTI_INSTANCE_LEAF_NODE:
                        return new YdtMultiInstanceLeafNode(id);

                    case YANG_MULTI_INSTANCE_NODE:
                        return new YdtMultiInstanceNode(id);

                    default:
                        throw new YdtException(
                                "Schema node with name " + id.getName() +
                                        " doesn't exist.");
                }
            default:
                throw new YdtException(
                        "Schema node with name " + id.getName() +
                                " doesn't exist.");
        }
    }

    /**
     * Create Yang data tree node of YangSchemaNode type specific and
     * returns the same.
     *
     * @param id       node identifier of data tree node
     * @param nodeType schema node type as per YANG schema metadata
     * @return YANG data tree node
     */
    protected static YdtNode getYangSchemaNodeTypeSpecificContext(
            YangSchemaNodeIdentifier id,
            YangSchemaNodeType nodeType) {

        switch (nodeType) {

            case YANG_SINGLE_INSTANCE_LEAF_NODE:
                return new YdtSingleInstanceLeafNode(id);

            case YANG_MULTI_INSTANCE_LEAF_NODE:
                return new YdtMultiInstanceLeafNode(id);

            case YANG_SINGLE_INSTANCE_NODE:
                return new YdtSingleInstanceNode(id);

            case YANG_MULTI_INSTANCE_NODE:
                return new YdtMultiInstanceNode(id);

            default:
                throw new YdtException(
                        "Schema node with name " + id.getName() +
                                " doesn't exist.");
        }
    }
}
