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


import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtListener;

/**
 * Represents implementation of YDT walker, which walks the YDT.
 */
public class DefaultYdtWalker implements YdtExtendedWalker {

    @Override
    public void walk(YdtListener ydtListener, YdtContext rootNode) {
        walkTree(ydtListener, rootNode, false);
    }

    /**
     * Walks the YANG data tree. Protocols implements YDT listener and YDT Extended Listener
     * and walks YDT tree with input as implemented object. YDT walker provides
     * call backs to implemented methods.
     *
     * @param ydtListener YDT listener implemented by the protocol
     * @param rootNode    root node of YDT
     * @param isExtended  check for understanding the type of call whether extended or base listener call.
     */
    @SuppressWarnings("RedundantCast")
    private void walkTree(YdtListener ydtListener, YdtContext rootNode, boolean isExtended) {
        YdtContext currentNode = rootNode;

        while (currentNode != null) {

            // Visit (currentNode) for entry callback
            if (isExtended) {
                ((YdtExtendedListener) ydtListener).enterYdtNode((YdtExtendedContext) currentNode);
            } else {
                ydtListener.enterYdtNode(currentNode);
            }

            // Move down to first child
            YdtContext nextNode = currentNode.getFirstChild();
            if (nextNode != null) {
                currentNode = nextNode;
                continue;
            }

            // No child nodes, so walk tree
            while (currentNode != null) {

                // Revisit (currentNode) for exit callback
                if (isExtended) {
                    ((YdtExtendedListener) ydtListener).exitYdtNode((YdtExtendedContext) currentNode);
                } else {
                    ydtListener.exitYdtNode(currentNode);
                }

                // Stop walking the tree once exit entry called for given rootNode.
                if (currentNode.equals(rootNode)) {
                    return;
                }

                // Move to sibling if possible.
                nextNode = currentNode.getNextSibling();
                if (nextNode != null) {
                    currentNode = nextNode;
                    break;
                }

                // Move up
                if (currentNode == rootNode) {
                    currentNode = null;
                } else {
                    currentNode = currentNode.getParent();
                }
            }
        }

    }

    @Override
    public void walk(YdtExtendedListener ydtExtendedListener, YdtExtendedContext rootNode) {
        walkTree(ydtExtendedListener, rootNode, true);
    }
}
