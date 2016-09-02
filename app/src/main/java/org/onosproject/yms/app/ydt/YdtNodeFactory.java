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
import org.onosproject.yms.app.ydt.exceptions.YdtExceptions;

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
     * Returns a YANG data tree node for a given name, set of values and instance type.
     *
     * @param nodeIdentifier       dataNodeIdentifier of data tree node
     * @param yangSchemaNode       data node as per YANG schema metadata
     * @param requestedCardinality requested cardinality of node
     * @param isLeaf               call type false for addChild and true for addLeaf
     * @return YANG data tree node
     */
    public static YdtNode getNode(YangSchemaNodeIdentifier nodeIdentifier, YangSchemaNode yangSchemaNode,
                                  RequestedCardinality requestedCardinality, boolean isLeaf) {

        YdtNode newNode;
        YangSchemaNodeType yangSchemaNodeType = yangSchemaNode.getYangSchemaNodeType();

        switch (requestedCardinality) {

            case UNKNOWN:
                /**
                 * if requested node type is UNKNOWN, check corresponding yang data node type
                 * and create respective type node.
                 */
                newNode = getYangSchemaNodeTypeSpecificContext(nodeIdentifier, yangSchemaNodeType, isLeaf);
                break;

            /**
             *if requested node type is specified and it exist as node of some other type in data model
             * then throw exception
             */
            case SINGLE_INSTANCE:
                if (yangSchemaNodeType == YangSchemaNodeType.YANG_SINGLE_INSTANCE_NODE) {
                    newNode = new YdtSingleInstanceNode(nodeIdentifier);
                    break;
                } else {
                    String errorInfo = "Schema node with name " + nodeIdentifier.getName() + " doesn't exist.";
                    throw new YdtExceptions(errorInfo);
                }

            case MULTI_INSTANCE:
                if (yangSchemaNodeType == YangSchemaNodeType.YANG_MULTI_INSTANCE_NODE) {
                    newNode = new YdtMultiInstanceNode(nodeIdentifier);
                    break;
                } else {
                    String errorInfo = "Schema node with name " + nodeIdentifier.getName() + " doesn't exist.";
                    throw new YdtExceptions(errorInfo);
                }

            case SINGLE_INSTANCE_LEAF:
                if (yangSchemaNodeType == YangSchemaNodeType.YANG_SINGLE_INSTANCE_LEAF_NODE) {
                    newNode = new YdtSingleInstanceLeafNode(nodeIdentifier);
                    break;
                } else {
                    String errorInfo = "Schema node with name " + nodeIdentifier.getName() + " doesn't exist.";
                    throw new YdtExceptions(errorInfo);
                }

            case MULTI_INSTANCE_LEAF:
                if (yangSchemaNodeType == YangSchemaNodeType.YANG_MULTI_INSTANCE_LEAF_NODE) {
                    newNode = new YdtMultiInstanceLeafNode(nodeIdentifier);
                    break;
                } else {
                    String errorInfo = "Schema node with name " + nodeIdentifier.getName() + " doesn't exist.";
                    throw new YdtExceptions(errorInfo);
                }

            default:
                String errorInfo = "Schema node with name " + nodeIdentifier.getName() + " doesn't exist.";
                throw new YdtExceptions(errorInfo);
        }

        // set reference of yang data node in the requested node.
        newNode.setYangSchemaNode(yangSchemaNode);

        return newNode;
    }

    /**
     * Create Yang data tree node of YangSchemaNode type specific for
     * requestedCardinality of type UNKNOWN and returns the same.
     *
     * @param nodeIdentifier     node identifier of data tree node
     * @param yangSchemaNodeType schema node type as per YANG schema metadata
     * @param isLeaf             call type "false" for addChild & "true" for addLeaf
     * @return YANG data tree node
     */
    private static YdtNode getYangSchemaNodeTypeSpecificContext(YangSchemaNodeIdentifier nodeIdentifier,
                                                                YangSchemaNodeType yangSchemaNodeType, boolean isLeaf) {


        if (isLeaf) {
            switch (yangSchemaNodeType) {

                case YANG_SINGLE_INSTANCE_LEAF_NODE:
                    return new YdtSingleInstanceLeafNode(nodeIdentifier);

                case YANG_MULTI_INSTANCE_LEAF_NODE:
                    return new YdtMultiInstanceLeafNode(nodeIdentifier);

                default:
                    String errorInfo = "Schema node with name " + nodeIdentifier.getName() + " doesn't exist.";
                    throw new YdtExceptions(errorInfo);
            }
        } else {
            switch (yangSchemaNodeType) {

                case YANG_SINGLE_INSTANCE_NODE:
                    return new YdtSingleInstanceNode(nodeIdentifier);

                case YANG_MULTI_INSTANCE_NODE:
                    return new YdtMultiInstanceNode(nodeIdentifier);

                default:
                    String errorInfo = "Schema node with name " + nodeIdentifier.getName() + " doesn't exist.";
                    throw new YdtExceptions(errorInfo);
            }
        }

    }

    /**
     * Create Yang data tree node of YangSchemaNode type specific and returns the same.
     *
     * @param nodeIdentifier     node identifier of data tree node
     * @param yangSchemaNodeType schema node type as per YANG schema metadata
     * @return YANG data tree node
     */
    public static YdtNode getYangSchemaNodeTypeSpecificContext(YangSchemaNodeIdentifier nodeIdentifier,
                                                               YangSchemaNodeType yangSchemaNodeType) {

        switch (yangSchemaNodeType) {

            case YANG_SINGLE_INSTANCE_LEAF_NODE:
                return new YdtSingleInstanceLeafNode(nodeIdentifier);

            case YANG_MULTI_INSTANCE_LEAF_NODE:
                return new YdtMultiInstanceLeafNode(nodeIdentifier);

            case YANG_SINGLE_INSTANCE_NODE:
                return new YdtSingleInstanceNode(nodeIdentifier);

            case YANG_MULTI_INSTANCE_NODE:
                return new YdtMultiInstanceNode(nodeIdentifier);

            default:
                String errorInfo = "Schema node with name " + nodeIdentifier.getName() + " doesn't exist.";
                throw new YdtExceptions(errorInfo);
        }

    }
}
