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
import static org.onosproject.yms.app.ydt.YdtConstants.errorMsg;

/**
 * Represents an YANG node factory to create different types of YANG data tree
 * node.
 */
final class YdtNodeFactory {

    // ydt formatted error string
    private static final String FMT_NOT_EXIST =
            "Schema node with name %s doesn't exist.";
    private static final String E_MULTI_INS =
            "Requested interface adds an instance of type list or " +
                    "leaf-list node only.";

    // No instantiation
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

        YdtNode newNode = null;
        YangSchemaNodeType nodeType = schemaNode.getYangSchemaNodeType();

        switch (cardinality) {

            case UNKNOWN:
                /*
                 * if requested node type is UNKNOWN, check corresponding
                 * yang data node type and create respective type node.
                 */
                newNode = getYangSchemaNodeTypeSpecificContext(schemaNode, nodeType,
                                                               callType);
                break;

            /*
             * if requested node type is specified and it exist as node of some
             * other type in data model then throw exception
             */
            case SINGLE_INSTANCE:
                validateNodeType(schemaNode, nodeType, YANG_SINGLE_INSTANCE_NODE);
                newNode = new YdtSingleInstanceNode(schemaNode);
                break;

            case MULTI_INSTANCE:

                validateNodeType(schemaNode, nodeType, YANG_MULTI_INSTANCE_NODE);
                newNode = new YdtMultiInstanceNode(schemaNode);
                break;

            case SINGLE_INSTANCE_LEAF:

                validateNodeType(schemaNode, nodeType, YANG_SINGLE_INSTANCE_LEAF_NODE);
                newNode = new YdtSingleInstanceLeafNode(schemaNode);
                break;

            case MULTI_INSTANCE_LEAF:

                validateNodeType(schemaNode, nodeType, YANG_MULTI_INSTANCE_LEAF_NODE);
                newNode = new YdtMultiInstanceLeafNode(schemaNode);
                break;

            default:
                throwNotExistError(schemaNode);
        }

        // set reference of yang data node in the requested node.
        newNode.setYangSchemaNode(schemaNode);

        return newNode;
    }

    /**
     * Validates the requested ydt node type against the schema node type,
     * if it is not equal then it will throw warning.
     *
     * @param node          schema node
     * @param nodeType      actual node type
     * @param requestedType user requested node type
     */
    private static void validateNodeType(YangSchemaNode node,
                                         YangSchemaNodeType nodeType,
                                         YangSchemaNodeType requestedType) {
        if (nodeType != requestedType) {
            throwNotExistError(node);
        }
    }

    /**
     * Creates Yang data tree node of YangSchemaNode type specific for
     * requestedCardinality of type UNKNOWN and returns the same.
     *
     * @param node     schema node
     * @param nodeType schema node type as per YANG schema metadata
     * @param callType identify the call type
     * @return YANG data tree node
     */
    private static YdtNode getYangSchemaNodeTypeSpecificContext(
            YangSchemaNode node,
            YangSchemaNodeType nodeType,
            RequestedCallType callType) {
        switch (callType) {
            case LEAF:
                switch (nodeType) {

                    case YANG_SINGLE_INSTANCE_LEAF_NODE:
                        return new YdtSingleInstanceLeafNode(node);

                    case YANG_MULTI_INSTANCE_LEAF_NODE:
                        return new YdtMultiInstanceLeafNode(node);

                    default:
                        throwNotExistError(node);
                }

            case NON_LEAF:
                switch (nodeType) {

                    case YANG_SINGLE_INSTANCE_NODE:
                        return new YdtSingleInstanceNode(node);

                    case YANG_MULTI_INSTANCE_NODE:
                        return new YdtMultiInstanceNode(node);

                    default:
                        throwNotExistError(node);
                }

            case MULTI_INSTANCE:
                switch (nodeType) {

                    case YANG_MULTI_INSTANCE_LEAF_NODE:
                        return new YdtMultiInstanceLeafNode(node);

                    case YANG_MULTI_INSTANCE_NODE:
                        return new YdtMultiInstanceNode(node);

                    default:
                        throw new YdtException(E_MULTI_INS);
                }

            default:
                throwNotExistError(node);
        }

        return null;
    }

    /**
     * Create Yang data tree node of YangSchemaNode type specific and
     * returns the same.
     *
     * @param node schema node
     * @return YANG data tree node
     */
    protected static YdtNode getYangSchemaNodeTypeSpecificContext(
            YangSchemaNode node) {

        switch (node.getYangSchemaNodeType()) {

            case YANG_SINGLE_INSTANCE_LEAF_NODE:
                return new YdtSingleInstanceLeafNode(node);

            case YANG_MULTI_INSTANCE_LEAF_NODE:
                return new YdtMultiInstanceLeafNode(node);

            case YANG_SINGLE_INSTANCE_NODE:
                return new YdtSingleInstanceNode(node);

            case YANG_MULTI_INSTANCE_NODE:
                return new YdtMultiInstanceNode(node);

            default:
                throwNotExistError(node);
        }

        return null;
    }

    /**
     * Throws exception for requested ydt node by preparing error message with
     * given node identifier.
     *
     * @param node schema node
     */
    private static void throwNotExistError(YangSchemaNode node) {
        throw new YdtException(errorMsg(FMT_NOT_EXIST, node.getName()));
    }
}
