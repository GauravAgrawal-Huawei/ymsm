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

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.onosproject.yms.app.ydt.YdtExtendedBuilder;
import org.onosproject.yms.ydt.YdtContextOperationType;

import java.util.Iterator;

/**
 * Represents implementation of codec xml listener.
 */
public class XmlCodecListener implements XmlListener {


    /**
     * YANG data tree builder object.
     */
    YdtExtendedBuilder ydtExtendedBuilder;

    /**
     * Returns the YANG data tree builder object.
     *
     * @return   YANG data tree builder object
     */
    public YdtExtendedBuilder getYdtExtendedBuilder() {
        return ydtExtendedBuilder;
    }

    /**
     * Sets the YANG data tree builder object.
     *
     * @param ydtExtendedBuilder   YANG data tree builder object
     */
    public void setYdtExtendedBuilder(YdtExtendedBuilder ydtExtendedBuilder) {
        this.ydtExtendedBuilder = ydtExtendedBuilder;
    }

    @Override
    public void enterXmlElement(Element element, XmlNodeType nodeType, Element rootElement) {
        String nameSpace = null;
        YdtContextOperationType editOperationType = null;

        if (element.equals(rootElement)) {
            return;
        }

        for (Iterator iter = element.attributeIterator(); iter.hasNext();) {
            Attribute attrib = (Attribute) iter.next();
            if (attrib.getName().equals(CodecUtilConstants.OPERATIONTYPE)) {
                editOperationType = YdtContextOperationType.valueOf(attrib.getValue().toUpperCase());
            }
        }

        if (element.getNamespace() != null) {
            nameSpace = element.getNamespace().getURI();
        }

        if (nodeType == XmlNodeType.OBJECT_NODE) {
            if (getYdtExtendedBuilder() != null) {
                getYdtExtendedBuilder().addChild(element.getName(), nameSpace, editOperationType);
            }
        } else if (nodeType == XmlNodeType.TEXT_NODE) {

            if (getYdtExtendedBuilder() != null) {
                getYdtExtendedBuilder().addLeaf(element.getName(), nameSpace, element.getText());
            }
        }
    }

    @Override
    public void exitXmlElement(Element element, XmlNodeType nodeType, Element rootElement) {
        if (element.equals(rootElement)) {
            return;
        }

        if (getYdtExtendedBuilder() != null) {
            getYdtExtendedBuilder().traverseToParent();
        }
    }
}
