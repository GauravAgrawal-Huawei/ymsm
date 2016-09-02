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

package org.onosproject.yms.app.ych.codecutils;

import org.dom4j.Element;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.ydt.YdtExtendedListener;
import org.onosproject.yms.ych.YangProtocolEncodingFormat;
import org.onosproject.yms.ydt.YdtContext;

import java.util.Stack;

/**
 * Represents implementation of codec YANG data object listener.
 */
public class CodecYdtListener implements YdtExtendedListener {

    /**
     * Data format type requested from driver.
     */
    private YangProtocolEncodingFormat recvedDataFormat;

    /**
     * Stack for element is maintained for hierarchical references, this is
     * used during YDT walker and preparation of xml/json.
     */
    private Stack<Element> domElementStack = new Stack<Element>();

    /**
     * Root name received from driver.
     */
    private YdtExtendedContext rootYdtNode;

    /**
     * Returns the root YDT context node.
     *
     * @return the root YDT context node
     */
    public YdtExtendedContext getRootYdtNode() {
        return rootYdtNode;
    }
    /**
     * Sets the root YDT context node.
     *
     * @param rootYdtNode the root YDT context node
     */
    public void setRootYdtNode(YdtExtendedContext rootYdtNode) {
        this.rootYdtNode = rootYdtNode;
    }

    /**
     * Returns data format type requested from driver.
     *
     * @return data format type requested from driver
     */
    private YangProtocolEncodingFormat getRecvedDataFormat() {
        return recvedDataFormat;
    }

    /**
     * Sets data format type requested from driver.
     *
     * @param recvedDataFormat  data format type requested from driver
     */
    public void setRecvedDataFormat(YangProtocolEncodingFormat recvedDataFormat) {
        this.recvedDataFormat = recvedDataFormat;
    }

    /**
     * Returns the stack for the element.
     *
     * @return the stack for the element
     */
    public Stack<Element> getDomElementStack() {
        return domElementStack;
    }

    /**
     * Sets the stack for the element.
     *
     * @param domElementStack stack for the element
     */
    private void setDomElementStack(Stack<Element> domElementStack) {
        this.domElementStack = domElementStack;
    }

    @Override
    public void enterYdtNode(YdtExtendedContext ydtExtendedContext) {
        if (getRootYdtNode() != ydtExtendedContext) {

            XmlCodecHandler nodeHandler = CodecHandlerFactory.getCodecHandlerForContext(ydtExtendedContext,
                                                                                        getRecvedDataFormat());

            try {
                if (getRecvedDataFormat() == YangProtocolEncodingFormat.XML_ENCODING) {
                    nodeHandler.processXmlContext(ydtExtendedContext, getDomElementStack());
                }
            } catch (Exception e) {
                // TODO
            }

            if (getRecvedDataFormat() == YangProtocolEncodingFormat.XML_ENCODING) {
                nodeHandler.setXmlValue(ydtExtendedContext, getDomElementStack());
            }
        }
    }

    @Override
    public void exitYdtNode(YdtExtendedContext ydtExtendedContext) {
        if (getRootYdtNode() != ydtExtendedContext) {
            getDomElementStack().pop();
        }
    }

    @Override
    public void enterYdtNode(YdtContext ydtContext) {
    }

    @Override
    public void exitYdtNode(YdtContext ydtContext) {
    }

}
