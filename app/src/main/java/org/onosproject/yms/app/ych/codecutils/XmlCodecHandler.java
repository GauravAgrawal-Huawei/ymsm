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
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;

import java.util.Stack;

/**
 * Represents an codec handler to process the xml content and add element to the stack.
 */
public abstract class XmlCodecHandler {

    /**
     * Sets the namespace and tag name in element tree maintained in stack.
     *
     * @param ydtContext           YDT context
     * @param domElementStack      element tree stack
     */
    public void processXmlContext(YdtContext ydtContext, Stack<Element> domElementStack) {

        Element newElement = updateNameAndNamespace(ydtContext, domElementStack.peek());
        domElementStack.push(newElement);
    }

    /**
     * Returns the new element name by updating tag name and namespace.
     *
     * @param ydtContext     YDT context node
     * @param xmlElement     element in the stack used for adding new element
     * @return               new element name by updating tag name and namespace
     */
    public Element updateNameAndNamespace(YdtContext ydtContext, Element xmlElement) {
        String nameSpace = null;
        String parentNameSpace = null;
        Element newElement = null;

        if (ydtContext.getNamespace() != null) {
            nameSpace = ydtContext.getNamespace();
        }

        if (ydtContext.getParent() != null) {
            parentNameSpace = ydtContext.getParent().getNamespace();
        }

        if (nameSpace != null) {
            newElement = xmlElement.addElement(ydtContext.getName(), nameSpace);
        } else {
            if (parentNameSpace != null) {
                newElement = xmlElement.addElement(ydtContext.getName(), parentNameSpace);
            } else {
                newElement = xmlElement.addElement(ydtContext.getName());
            }
        }

        YdtContextOperationType ydtContextOperationType = ((YdtExtendedContext) ydtContext)
                                                                   .getYdtContextOperationType();
        if (ydtContextOperationType != null && ydtContextOperationType != YdtContextOperationType.NONE) {
            newElement.addAttribute(CodecUtilConstants.OPERATIONTYPE, ydtContextOperationType.toString().toLowerCase());
        }

        return newElement;
    }

    /**
     * Sets the leaf value in the current element maintained in stack.
     *
     * @param ydtContext           YDT context node
     * @param domElementStack      current element node in the stack
     */
    public void setXmlValue(YdtContext ydtContext, Stack<Element> domElementStack) {
        return;
    }
}
