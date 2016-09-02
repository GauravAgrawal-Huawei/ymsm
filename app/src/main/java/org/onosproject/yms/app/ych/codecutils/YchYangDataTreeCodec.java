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

import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.onosproject.yms.app.ydt.DefaultYdtWalker;
import org.onosproject.yms.app.ydt.YangRequestWorkBench;
import org.onosproject.yms.app.ydt.YdtExtendedBuilder;
import org.onosproject.yms.app.ydt.YdtExtendedWalker;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ych.YangCompositeEncoding;
import org.onosproject.yms.ych.YangDataTreeCodec;
import org.onosproject.yms.ych.YangProtocolEncodingFormat;
import org.onosproject.yms.ydt.YdtBuilder;
import org.onosproject.yms.ydt.YmsOperationType;

import static org.onosproject.yms.app.ych.codecutils.CodecUtilConstants.CONFIG;
import static org.onosproject.yms.app.ych.codecutils.CodecUtilConstants.DATA;
import static org.onosproject.yms.app.ych.codecutils.CodecUtilConstants
        .EDITCONFIG;
import static org.onosproject.yms.app.ych.codecutils.CodecUtilConstants.FILTER;
import static org.onosproject.yms.app.ych.codecutils.CodecUtilConstants.GET;
import static org.onosproject.yms.app.ych.codecutils.CodecUtilConstants
        .GETCONFIG;
import static org.onosproject.yms.ydt.YmsOperationType.EDIT_CONFIG_REQUEST;
import static org.onosproject.yms.ydt.YmsOperationType.QUERY_CONFIG_REQUEST;
import static org.onosproject.yms.ydt.YmsOperationType.QUERY_REQUEST;

/**
 * Represents an YCH data tree codec.
 */
public class YchYangDataTreeCodec
        implements YangDataTreeCodec {

    /**
     * Root element node used for YDT buider.
     */
    private Element rootElement;

    /**
     * Root element name space.
     */
    private String rootNameSpace;

    /**
     * Interaction type received from driver.
     */
    private YmsOperationType yangInteractionType;

    /**
     * Returns the root element node.
     *
     * @return the root element node
     */
    private Element getRootElement() {
        return rootElement;
    }

    /**
     * Sets the root element node.
     *
     * @param rootElement the root element node
     */
    private void setRootElement(Element rootElement) {
        this.rootElement = rootElement;
    }

    /**
     * Returns the root element namespace.
     *
     * @return the root element namespace
     */
    public String getRootNameSpace() {
        return rootNameSpace;
    }

    /**
     * Sets the root element namespace.
     *
     * @param rootNameSpace the root element namespace
     */
    public void setRootNameSpace(String rootNameSpace) {
        this.rootNameSpace = rootNameSpace;
    }

    /**
     * Returns the YANG interaction type.
     *
     * @return the YANG interaction type
     */
    private YmsOperationType getYangInteractionType() {
        return yangInteractionType;
    }

    /**
     * Sets the YANG interaction type.
     *
     * @param yangInteractionType the YANG interaction type
     */
    private void setYangInteractionType(YmsOperationType yangInteractionType) {
        this.yangInteractionType = yangInteractionType;
    }

    /**
     * Creates a new YANG application broker.
     */
    public YchYangDataTreeCodec() {
    }

    @Override
    public String encodeYdtToProtocolFormat(YdtBuilder ydtBuilder,
                                            YmsOperationType
                                                    protocolOperation) {
        YdtExtendedBuilder ydtExtendedBuilder = (YdtExtendedBuilder) ydtBuilder;
        CodecYdtListener codecYdtListener = new CodecYdtListener();
        codecYdtListener
                .setRecvedDataFormat(YangProtocolEncodingFormat.XML_ENCODING);
        codecYdtListener.setRootYdtNode(ydtExtendedBuilder.getRootNode());

        // Creating the root element for xml.
        Element rootElement = DocumentHelper.createDocument()
                .addElement(ydtExtendedBuilder.getRootNode().getName());

        // Adding the name space if exist for root name.
        if (ydtExtendedBuilder.getRootNode().getNamespace() != null) {
            Namespace namespace = Namespace
                    .get(ydtExtendedBuilder.getRootNode().getNamespace());
            rootElement.add(namespace);
        }

        // Adding the attribute if exist
        Map<String, String> tagAttributeLinkedMap =
                ydtExtendedBuilder.getRootTagAttributeMap();
        if ((tagAttributeLinkedMap != null) &&
                !tagAttributeLinkedMap.isEmpty()) {
            for (Map.Entry<String, String> attribute : tagAttributeLinkedMap
                    .entrySet()) {
                rootElement
                        .addAttribute(attribute.getKey(), attribute.getValue());
            }
        }

        codecYdtListener.getDomElementStack().push(rootElement);

        // Walk through YDT and build the xml.
        YdtExtendedWalker ydtExtendedWalker = new DefaultYdtWalker();
        if (ydtExtendedBuilder.getRootNode() != null) {
            ydtExtendedWalker
                    .walk(codecYdtListener, ydtExtendedBuilder.getRootNode());
        }

        return rootElement.asXML();
    }

    @Override
    public YangCompositeEncoding encodeYdtToCompositeProtocolFormat(
            YdtBuilder ydtBuilder,
            YmsOperationType protocolOperation) {
        return null;
    }

    @Override
    public YdtBuilder decodeCompositeProtocolDataToYdt(
            YangCompositeEncoding protocolData,
            Object schemaRegistryForYdt,
            YmsOperationType protocolOperation) {
        return null;
    }

    @Override
    public YdtBuilder decodeProtocolDataToYdt(String protocolData,
                                              Object schemaRegistry,
                                              YmsOperationType
                                                      protocolOperation) {
        YdtExtendedBuilder ydtExtendedBuilder = null;
        Document document = null;

        try {
            document = DocumentHelper.parseText(protocolData);
        } catch (DocumentException e) {
            // TODO
        }
        XmlWalker walker = new DefaultXmlCodecWalker();
        XmlCodecListener listener = new XmlCodecListener();

        // Find the root element in xml string
        findRootElement(document.getRootElement());

        // Get the YDT builder for the logical root name.
        if (getRootElement() != null) {
            ydtExtendedBuilder =
                    new YangRequestWorkBench(getRootElement().getName(),
                                             getRootNameSpace(),
                                             protocolOperation,
                                             (YangSchemaRegistry)
                                                     schemaRegistry,
                                             false);
        }

        listener.setYdtExtendedBuilder(ydtExtendedBuilder);
        // Walk through xml and build the yang data tree.
        walker.walk(listener, getRootElement(), getRootElement());
        return ydtExtendedBuilder;
    }

    /**
     * Find the root node element from the xml string.
     *
     * @param element input element to be traveresed to find the root node
     */
    private void findRootElement(Element element) {

        try {

            switch (element.getName()) {
                // edit-config tag name is found in xml then set the
                // interaction type.
                case EDITCONFIG: {
                    setYangInteractionType(EDIT_CONFIG_REQUEST);
                    break;
                }

                // get-config tag name is found in xml then set the
                // interaction type.
                case GETCONFIG: {
                    setYangInteractionType(QUERY_CONFIG_REQUEST);
                    break;
                }

                // get tag name is found in xml then set the interaction type.
                case GET: {
                    setYangInteractionType(QUERY_REQUEST);
                    break;
                }

                default: {
                    //TODO
                }
            }

            // If config tag name is found then set the root element node.
            if (CONFIG.equals(element.getName()) ||
                    DATA.equals(element.getName())
                    || FILTER.equals(element.getName())) {
                setRootElement(element);
                if (element.getNamespace() != null) {
                    setRootNameSpace(element.getNamespace().getURI());
                }
                return;
            }

            /* If element has child node then traverse through the child node
             by recursivly calling
            findRootElement method. */
            if (element.hasContent() && !element.isTextOnly()) {
                for (Iterator i = element.elementIterator(); i.hasNext();
                     i = i) {
                    Element childElement = (Element) i.next();
                    findRootElement(childElement);
                }
            }

        } catch (Exception e) {
            // TODO
        }

        return;
    }
}
