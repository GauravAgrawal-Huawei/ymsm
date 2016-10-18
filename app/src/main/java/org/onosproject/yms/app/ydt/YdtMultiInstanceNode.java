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

import com.google.common.collect.ImmutableList;
import org.onosproject.yangutils.datamodel.YangList;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yms.ydt.YdtContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.onosproject.yms.app.ydt.YdtConstants.errorMsg;
import static org.onosproject.yms.ydt.YdtType.MULTI_INSTANCE_NODE;


/**
 * Represents a multi instance node in YANG data tree.
 */
public class YdtMultiInstanceNode extends YdtNode {

    // ydt formatted error string
    private static final String FMT_MISSING_KEY =
            "%s is missing some of the keys of %s.";
    private static final String FMT_UNI_KEY =
            "Some of the key elements are not unique in %s.";
    private static final String FMT_MANY_INS =
            "Too many instances of %s. Expected maximum instances %d.";
    private static final String FMT_FEW_INS =
            "Too few instances of %s. Expected minimum instances %d.";

    /*
     * Reference for list of key element's ydtContext.
     */
    private List<YdtContext> keyNodeList = new ArrayList<>();

    /*
     * Reference for composite key string for multi Instance Node..
     */
    private String compositeKey;

    /**
     * Creates a YANG multi instance node object.
     *
     * @param node schema of YDT multi instance node .
     */
    protected YdtMultiInstanceNode(YangSchemaNode node) {
        super(MULTI_INSTANCE_NODE, node);
    }

    /**
     * Returns the composite key string for current multi instance node.
     *
     * @return composite key string
     */
    private String getCompositeKey() {
        return compositeKey;
    }

    /**
     * Returns the list of key element's ydtContext.
     *
     * @return list of key element's ydtContext
     */
    public List<YdtContext> getKeyNodeList() {
        return ImmutableList.copyOf(keyNodeList);
    }

    @Override
    public void createKeyNodeList() {
        YangList yangListHolder = (YangList) getYangSchemaNode();
        List<String> schemaKeyList = yangListHolder.getKeyList();

        /*
         * If key element not defined in schema or config is false then
         * return no need to do create key list.
         */
        if (schemaKeyList == null || !yangListHolder.isConfig()) {
            return;
        }

        StringBuilder ksb = new StringBuilder();

        // Iterator for schema key name list.
        Iterator<String> sklItr = schemaKeyList.iterator();

        List<YdtContext> nodeList = new ArrayList<>();

        YangSchemaNodeIdentifier id = new YangSchemaNodeIdentifier();
        id.setNameSpace(new NameSpace(getNamespace()));
        // This loop should run while schema key list is not finished
        while (sklItr.hasNext()) {
            String name = sklItr.next();
            id.setName(name);
            YdtNode<YdtSingleInstanceLeafNode> collidingChild =
                    (YdtNode<YdtSingleInstanceLeafNode>) ydtNodeMap.get(id);

            if (collidingChild == null) {
                errorHandler(errorMsg(FMT_MISSING_KEY,
                                      yangListHolder.getParent().getName(),
                                      yangListHolder.getName()), this);
            }

            YdtNode<YdtSingleInstanceLeafNode> ydtNode = collidingChild;
            /*
             * Preparing composite key string by concatenating values of
             * all the key leaf.
             */
            ksb.append(ydtNode.getValue());
            nodeList.add(ydtNode);
        }
        //Setting te key object in List.
        keyNodeList = nodeList;
        compositeKey = ksb.toString();
    }

    /**
     * Validates the given list of instances by verifying the allowed
     * instance count and key element uniqueness.
     *
     * @param keyStringSet set to validate the key element uniqueness
     * @param nodelist     list of instance's of same list
     */
    public void validateInstances(Set keyStringSet, List nodelist) {

        List<YdtNode<YdtMultiInstanceNode>> ydtNodeList = nodelist;
        // Clearing the set.
        keyStringSet.clear();

        /*
         * Storing the number of multiInstance node for number
         * if instance validation.
         */
        int instanceCount = ydtNodeList.size();

        YangList list = (YangList) ydtNodeList.get(0).getYangSchemaNode();
        validateInstanceCount(instanceCount, list);
        if (list.isConfig() && instanceCount > 1) {

            /*
             * Iterating over values in ydtNodeList of
             * multiInstanceNode and compare the key string.
             */
            for (YdtNode ydtNode : ydtNodeList) {
                if (!keyStringSet.add(((YdtMultiInstanceNode) ydtNode)
                                              .getCompositeKey())) {
                    errorHandler(errorMsg(
                            FMT_UNI_KEY, ydtNode.getName()), this);
                }
            }
        }
    }

    /**
     * Validates the instance count for given list entry.
     *
     * @param instanceCount actual count
     * @param list          list entry for which instance count need
     *                      to be validated
     */
    private void validateInstanceCount(int instanceCount, YangList list) {

        if (list.getMinElements() != null) {
            int minElement = list.getMinElements().getMinElement();
            if (instanceCount < minElement) {
                errorHandler(errorMsg(FMT_FEW_INS, list.getName(),
                                      minElement), this);
            }
        }

        if (list.getMaxElements() != null) {
            int maxElement = list.getMaxElements().getMaxElement();
            if (instanceCount > maxElement) {
                errorHandler(errorMsg(FMT_MANY_INS, list.getName(),
                                      maxElement), this);
            }
        }
    }
}
