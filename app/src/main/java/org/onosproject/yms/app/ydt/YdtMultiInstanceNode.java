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
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yms.app.ydt.exceptions.YdtException;
import org.onosproject.yms.ydt.YdtContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.onosproject.yms.ydt.YdtType.MULTI_INSTANCE_NODE;

/**
 * Represents a multi instance node in YANG data tree.
 */
public class YdtMultiInstanceNode extends YdtNode {

    /*
     * Reference for list of key element's ydtContext.
     */
    private List<YdtContext> keyNodeList;

    /*
     * Reference for composite key string for multi Instance Node..
     */
    private String compositeKey;

    /*
     * Returns the composite key string for current multi instance node.
     *
     * @return composite key string
     */
    public String getCompositeKey() {
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

    /**
     * Creates a YANG multi instance node object.
     *
     * @param id node identifier of YDT multi instance node .
     */
    protected YdtMultiInstanceNode(YangSchemaNodeIdentifier id) {
        super(MULTI_INSTANCE_NODE, id);
        keyNodeList = new ArrayList<>();
    }

    @Override
    public void createKeyNodeList() {
        YangList yangListHolder = (YangList) getYangSchemaNode();
        List<String> schemaKeyList = yangListHolder.getKeyList();

        //Reference for composite key string.
        String keyString = "";
        StringBuilder keyStringBuilder = new StringBuilder();

        /*
         * If key element not defined in schema or config is false then
         * return no need to do create key list.
         */
        if (schemaKeyList == null || !yangListHolder.isConfig()) {
            return;
        }

        // Iterator for schema key name list.
        Iterator<String> it1 = schemaKeyList.iterator();

        String namespace = getYdtNodeIdentifier().getNameSpace();

        List<YdtContext> nodeList = new ArrayList<>();

        YangSchemaNodeIdentifier id = new YangSchemaNodeIdentifier();
        id.setNameSpace(namespace);
        // This loop should run till schema key list are not finished.
        while (it1.hasNext()) {
            String name = it1.next();
            id.setName(name);
            List<YdtNode<YdtMultiInstanceNode>> collidingChild =
                    (List<YdtNode<YdtMultiInstanceNode>>) ydtNodeMap.get(id);

            if (collidingChild == null) {
                // Free resources
                freeRestResources();
                throw new YdtException(yangListHolder.getParent()
                                               .getName() + " is missing " +
                                               "some of the keys of " +
                                               yangListHolder.getName() + ".");
            }

            YdtNode<YdtMultiInstanceNode> ydtNode = collidingChild.get(0);
            /*
             * Preparing composite key string by concatenating values of
             * all the key leaf.
             */
            keyStringBuilder.append(keyString);
            keyStringBuilder.append(ydtNode.getValue());
            nodeList.add(ydtNode);
        }
        //Setting te key object in List.
        keyNodeList = nodeList;
        compositeKey = keyStringBuilder.toString();
    }
}
