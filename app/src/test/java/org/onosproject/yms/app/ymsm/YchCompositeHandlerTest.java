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

package org.onosproject.yms.app.ymsm;

import org.junit.Test;
import org.onosproject.yang.gen.v1.ydt.customs.supervisor.rev20160524.CustomssupervisorOpParam;
import org.onosproject.yms.app.ych.codecutils.YchYangDataTreeCodec;
import org.onosproject.yms.app.ysr.TestYangSchemaNodeProvider;
import org.onosproject.yms.ych.YangCodecHandler;
import org.onosproject.yms.ych.YangCompositeEncoding;
import org.onosproject.yms.ych.YangDataTreeCodec;
import org.onosproject.yms.ych.YangProtocolEncodingFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.onosproject.yms.ych.YangProtocolEncodingFormat.XML_ENCODING;

/**
 * Unit test case for YCH composite codec handler.
 */
public class YchCompositeHandlerTest {

    private static final String TARGET = "target/TestYangSchemaNodeProvider";
    private TestYangSchemaNodeProvider testYangSchemaNodeProvider = new TestYangSchemaNodeProvider();

    private Map<YangProtocolEncodingFormat, YangDataTreeCodec> defaultCodecs = new HashMap<>();

    /**
     * Unit test case in which verifying codec handler is null or not.
     */
    @Test
    public void checkForCodecHandler() {
        YmsManager ymsManager = new YmsManager();
        YangCodecHandler yangCodecHandler = ymsManager.getYangCodecHandler();
        assertTrue(yangCodecHandler != null);
    }

    /**
     * Unit test case in which verifying codec handler is null or not.
     */
    @Test
    public void checkForRegisterDefaultCodec() {

        YangDataTreeCodec yangDataTreeCodec = new RegisterDataTreeCodec();
        YmsManager ymsManager = new YmsManager();
        ymsManager.registerDefaultCodec(yangDataTreeCodec, XML_ENCODING);
        YangCodecHandler yangCodecHandler = ymsManager.getYangCodecHandler();
        assertTrue(yangCodecHandler != null);

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        List<Object> yangModuleList = new ArrayList<>();

        // Creating the object
        Object object = CustomssupervisorOpParam.builder().supervisor("Customssupervisor").build();
        yangModuleList.add(object);

        // Get the xml string and compare
        Map<String, String> tagAttr = new HashMap<String, String>();
        tagAttr.put("type", "subtree");

        try {
            yangCodecHandler.encodeOperation("filter", "ydt.filter-type", tagAttr, yangModuleList,
                                             YangProtocolEncodingFormat.XML_ENCODING, null);
        } catch (Exception e) {
            if (!e.getMessage().equals("RegisterDataTreeCodec encodeYdtToProtocolFormat Called.")) {
                assertTrue(false);
            }
        }

        // Verify the received object list
        try {
            yangCodecHandler.decode("XML String", YangProtocolEncodingFormat.XML_ENCODING, null);
        } catch (Exception e) {
            if (!e.getMessage().equals("RegisterDataTreeCodec decodeProtocolDataToYdt Called.")) {
                assertTrue(false);
            }
        }

        // Verify the received object list
        try {
            yangCodecHandler.encodeCompositeOperation("filter", "ydt.filter-type", object,
                                                      YangProtocolEncodingFormat.XML_ENCODING, null);
        } catch (Exception e) {
            if (!e.getMessage().equals("RegisterDataTreeCodec encodeYdtToCompositeProtocolFormat Called.")) {
                assertTrue(false);
            }
        }

        // Verify the received object list
        try {
            YangCompositeEncoding yangCompositeEncoding = new YangCompositeTest();
            yangCodecHandler.decode(yangCompositeEncoding, XML_ENCODING, null);
        } catch (Exception e) {
            if (!e.getMessage().equals("RegisterDataTreeCodec decodeCompositeProtocolDataToYdt Called.")) {
                assertTrue(false);
            }
        }

    }

    /**
     * Unit test case in which verifying codec handler is null or not.
     */
    @Test
    public void checkForDefaultCodec() {
        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(XML_ENCODING, yangDataTreeCodec);
        YmsManager ymsManager = new YmsManager();
        ymsManager.coreService = new CoreServiceTest();
        ymsManager.activate();
        YangCodecHandler yangCodecHandler = ymsManager.getYangCodecHandler();
        assertTrue(yangCodecHandler != null);

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        List<Object> yangModuleList = new ArrayList<>();

        // Creating the object
        Object object = CustomssupervisorOpParam.builder().supervisor("Customssupervisor").build();
        yangModuleList.add(object);

        // Get the xml string and compare
        Map<String, String> tagAttr = new HashMap<String, String>();
        tagAttr.put("type", "subtree");

        // Verify the received object list
        try {
            yangCodecHandler.encodeCompositeOperation("filter", "ydt.filter-type", object,
                                                      YangProtocolEncodingFormat.XML_ENCODING, null);
        } catch (Exception e) {
            if (!e.getMessage().equals("Encode for composite protocol request not supported.")) {
                assertTrue(false);
            }
        }

        // Verify the received object list
        try {
            YangCompositeEncoding yangCompositeEncoding = new YangCompositeTest();
            yangCodecHandler.decode(yangCompositeEncoding, XML_ENCODING, null);
        } catch (Exception e) {
            if (!e.getMessage().equals("Decode for composite protocol request not supported.")) {
                assertTrue(false);
            }
        }
    }

    /**
     * Unit test case in which verifying overriden codec handler is null or not.
     */
    @Test
    public void checkForOverridenDataTreeCodec() {

        YangDataTreeCodec yangDataTreeCodec = new RegisterDataTreeCodec();
        YmsManager ymsManager = new YmsManager();
        ymsManager.registerDefaultCodec(yangDataTreeCodec, XML_ENCODING);
        YangCodecHandler yangCodecHandler = ymsManager.getYangCodecHandler();
        assertTrue(yangCodecHandler != null);

        YangDataTreeCodec overriddenCodec = new OverridenDataTreeCodec();
        yangCodecHandler.registerOverriddenCodec(overriddenCodec, XML_ENCODING);

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        List<Object> yangModuleList = new ArrayList<>();

        // Creating the object
        Object object = CustomssupervisorOpParam.builder().supervisor("Customssupervisor").build();
        yangModuleList.add(object);

        // Get the xml string and compare
        Map<String, String> tagAttr = new HashMap<String, String>();
        tagAttr.put("type", "subtree");

        try {
            yangCodecHandler.encodeOperation("filter", "ydt.filter-type", tagAttr, yangModuleList,
                                             YangProtocolEncodingFormat.XML_ENCODING, null);
        } catch (Exception e) {
            if (!e.getMessage().equals("OverridenDataTreeCodec encodeYdtToProtocolFormat Called.")) {
                assertTrue(false);
            }
        }

        // Verify the received object list
        try {
            yangCodecHandler.decode("XML String", YangProtocolEncodingFormat.XML_ENCODING, null);
        } catch (Exception e) {
            if (!e.getMessage().equals("OverridenDataTreeCodec decodeProtocolDataToYdt Called.")) {
                assertTrue(false);
            }
        }

        // Verify the received object list
        try {
            yangCodecHandler.encodeCompositeOperation("filter", "ydt.filter-type", object,
                                                      YangProtocolEncodingFormat.XML_ENCODING, null);
        } catch (Exception e) {
            if (!e.getMessage().equals("OverridenDataTreeCodec encodeYdtToCompositeProtocolFormat Called.")) {
                assertTrue(false);
            }
        }

        // Verify the received object list
        try {
            YangCompositeEncoding yangCompositeEncoding = new YangCompositeTest();
            yangCodecHandler.decode(yangCompositeEncoding, XML_ENCODING, null);
        } catch (Exception e) {
            if (!e.getMessage().equals("OverridenDataTreeCodec decodeCompositeProtocolDataToYdt Called.")) {
                assertTrue(false);
            }
        }

    }
}
