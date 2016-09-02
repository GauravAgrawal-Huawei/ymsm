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

package org.onosproject.yms.app.ych;

import org.junit.Test;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.CombinedOpParam;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.AsNum;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.Attributes;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.DefaultAttributes;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.Metric;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.PathId;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.Aigp;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.BgpParameters;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.DefaultAigp;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.DefaultBgpParameters;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.DefaultLocalPref;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.DefaultMultiExitDisc;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.DefaultOrigin;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.DefaultUnrecognizedAttributes;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.LocalPref;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.MultiExitDisc;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.Origin;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.UnrecognizedAttributes;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.aigp.AigpTlv;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.aigp.DefaultAigpTlv;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.DefaultOptionalCapabilities;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.OptionalCapabilities;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.optionalcapabilities.Cparameters;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.optionalcapabilities.DefaultCparameters;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.optionalcapabilities.cparameters.As4BytesCapability;
import org.onosproject.yang.gen.v1.ych.combined.rev20160524.combined.attributes.bgpparameters.optionalcapabilities.cparameters.DefaultAs4BytesCapability;
import org.onosproject.yang.gen.v1.ych.empty.container.rev20160524.EmptyContainerOpParam;
import org.onosproject.yang.gen.v1.ych.empty.container.rev20160524.emptycontainer.EmptyContainer;
import org.onosproject.yang.gen.v1.ych.purchasing.supervisor.rev20160524.YchPurchasingsupervisorOpParam;
import org.onosproject.yang.gen.v1.ych.purchasing.supervisor.rev20160524.ychpurchasingsupervisor.DefaultYchPurchasingSupervisor;
import org.onosproject.yang.gen.v1.ych.purchasing.supervisor.rev20160524.ychpurchasingsupervisor.YchPurchasingSupervisor;
import org.onosproject.yang.gen.v1.ydt.customs.supervisor.rev20160524.CustomssupervisorOpParam;
import org.onosproject.yang.gen.v1.ydt.employee.id.rev20160524.EmployeeidOpParam;
import org.onosproject.yang.gen.v1.ydt.material.supervisor.rev20160524.MaterialsupervisorOpParam;
import org.onosproject.yang.gen.v1.ydt.material.supervisor.rev20160524.materialsupervisor.DefaultSupervisor;
import org.onosproject.yang.gen.v1.ydt.material.supervisor.rev20160524.materialsupervisor.Supervisor;
import org.onosproject.yang.gen.v1.ydt.merchandiser.supervisor.rev20160524.MerchandisersupervisorOpParam;
import org.onosproject.yang.gen.v1.ydt.root.rev20160524.LogisticsManagerOpParam;
import org.onosproject.yang.gen.v1.ydt.root.rev20160524.logisticsmanager.DefaultPurchasingSupervisor;
import org.onosproject.yang.gen.v1.ydt.trading.supervisor.rev20160524.TradingsupervisorOpParam;
import org.onosproject.yang.gen.v1.ydt.warehouse.supervisor.rev20160524.WarehousesupervisorOpParam;
import org.onosproject.yms.app.ych.codecutils.YchYangDataTreeCodec;
import org.onosproject.yms.app.ysr.DefaultYangSchemaRegistry;
import org.onosproject.yms.app.ysr.TestYangSchemaNodeProvider;
import org.onosproject.yms.ych.YangDataTreeCodec;
import org.onosproject.yms.ych.YangProtocolEncodingFormat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.onosproject.yang.gen.v1.ydt.root.rev20160524.logisticsmanager.DefaultPurchasingSupervisor.OnosYangNodeOperationType.DELETE;

/**
 * Unit test case for default codec handler.
 */
public class DefaultYangCodecHandlerTest {

    private static final String TARGET = "target/TestYangSchemaNodeProvider";
    private TestYangSchemaNodeProvider testYangSchemaNodeProvider = new TestYangSchemaNodeProvider();

    private Map<YangProtocolEncodingFormat, YangDataTreeCodec> defaultCodecs = new HashMap<>();

    /**
     * Unit test case in which verifying xml string for module object with leaf.
     */
    @Test
    public void proceessCodecHandlerForLeaf() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();
        List<Object> yangModuleList = new ArrayList<>();
        boolean result;


        // Creating the object
        Object object = CustomssupervisorOpParam.builder().supervisor("Customssupervisor").build();
        yangModuleList.add(object);

        // Get the xml string and compare
        Map<String, String> tagAttr = new HashMap<String, String>();
        tagAttr.put("type", "subtree");

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);
        String xmlOutPut = defaultYangCodecHandler.encodeOperation("filter", "ydt.filter-type", tagAttr,
                                                           yangModuleList, YangProtocolEncodingFormat.XML_ENCODING,
                                                                   null);

        result = xmlOutPut.equals("<filter xmlns=\"ydt.filter-type\" type=\"subtree\">" +
                                          "<customssupervisor xmlns=\"ydt.customs-supervisor\">" +
                                          "<supervisor>Customssupervisor</supervisor>" +
                                          "</customssupervisor>" +
                                          "</filter>");
        assertTrue(result);

        // Creating the object
        object = MerchandisersupervisorOpParam.builder().supervisor("Merchandisersupervisor").build();
        yangModuleList.clear();
        yangModuleList.add(object);

        // Get the xml string and compare
        xmlOutPut = defaultYangCodecHandler.encodeOperation("config", "ydt.root", null, yangModuleList,
                                                            YangProtocolEncodingFormat.XML_ENCODING, null);
        result = xmlOutPut.equals("<config xmlns=\"ydt.root\">" +
                                          "<merchandisersupervisor xmlns=\"ydt.Merchandiser-supervisor\">" +
                                          "<supervisor>Merchandisersupervisor</supervisor>" +
                                          "</merchandisersupervisor>" +
                                          "</config>");

        assertTrue(result);

        // Creating the object
        object = TradingsupervisorOpParam.builder().supervisor("Tradingsupervisor").build();
        yangModuleList.clear();
        yangModuleList.add(object);

        // Get the xml string and compare
        xmlOutPut = defaultYangCodecHandler.encodeOperation("config", "ydt.root", null, yangModuleList,
                                                            YangProtocolEncodingFormat.XML_ENCODING, null);
        result = xmlOutPut.equals("<config xmlns=\"ydt.root\">" +
                                          "<tradingsupervisor xmlns=\"ydt.trading-supervisor\">" +
                                          "<supervisor>Tradingsupervisor</supervisor>" +
                                          "</tradingsupervisor>" +
                                          "</config>");
        assertTrue(result);
    }

    /**
     * Unit test case in which verifying xml string for module object with list.
     */
    @Test
    public void proceessCodecHandlerForList() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();
        List<Object> yangModuleList = new ArrayList<>();
        boolean result;

        // Creating the object
        Supervisor supervisor1 = new DefaultSupervisor.SupervisorBuilder()
                .name("abc1").departmentId("xyz1").build();
        Supervisor supervisor2 = new DefaultSupervisor.SupervisorBuilder()
                .name("abc2").departmentId("xyz2").build();
        Supervisor supervisor3 = new DefaultSupervisor.SupervisorBuilder()
                .name("abc3").departmentId("xyz3").build();
        Supervisor supervisor4 = new DefaultSupervisor.SupervisorBuilder()
                .name("abc4").departmentId("xyz4").build();
        Supervisor supervisor5 = new DefaultSupervisor.SupervisorBuilder()
                .name("abc5").departmentId("xyz5").build();

        Object object = MaterialsupervisorOpParam.builder().addToSupervisor(supervisor1)
                .addToSupervisor(supervisor2).addToSupervisor(supervisor3).addToSupervisor(supervisor4)
                .addToSupervisor(supervisor5).build();

        yangModuleList.add(object);

        // Get the xml string and compare
        Map<String, String> tagAttr = new HashMap<String, String>();
        tagAttr.put("type", "subtree");

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);
        String xmlOutput = defaultYangCodecHandler.encodeOperation("filter", "ydt.filter-type", tagAttr,
                                                           yangModuleList, YangProtocolEncodingFormat.XML_ENCODING,
                                                                   null);
        result = xmlOutput.equals("<filter xmlns=\"ydt.filter-type\" type=\"subtree\">" +
                                          "<materialsupervisor xmlns=\"ydt.material-supervisor\">" +
                                          "<supervisor>" +
                                          "<name>abc1</name><departmentId>xyz1</departmentId>" +
                                          "</supervisor>" +
                                          "<supervisor><name>abc2</name><departmentId>xyz2</departmentId>" +
                                          "</supervisor>" +
                                          "<supervisor><name>abc3</name><departmentId>xyz3</departmentId>" +
                                          "</supervisor>" +
                                          "<supervisor><name>abc4</name><departmentId>xyz4</departmentId>" +
                                          "</supervisor>" +
                                          "<supervisor><name>abc5</name><departmentId>xyz5</departmentId>" +
                                          "</supervisor>" +
                                          "</materialsupervisor>" +
                                          "</filter>");
        assertTrue(result);
    }

    /**
     * Unit test case in which verifying xml string for module object with empty container.
     */
    @Test
    public void proceessCodecHandlerForEmptyContainer() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();
        List<Object> yangModuleList = new ArrayList<>();
        boolean result;

        // Creating the object
        EmptyContainer emptyContainer =  EmptyContainerOpParam.builder().emptyContainer();
        Object object = EmptyContainerOpParam.builder().emptyContainer(emptyContainer).build();

        yangModuleList.add(object);

        // Get the xml string and compare
        Map<String, String> tagAttr = new HashMap<String, String>();
        tagAttr.put("type", "subtree");

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);
        String xmlOutput = defaultYangCodecHandler.encodeOperation("filter", "ydt.filter-type", tagAttr,
                                                               yangModuleList, YangProtocolEncodingFormat.XML_ENCODING,
                                                                   null);
        result = xmlOutput.equals("<filter xmlns=\"ydt.filter-type\" type=\"subtree\">" +
                                          "<EmptyContainer xmlns=\"ych.Empty.Container\"/>" +
                                          "</filter>");
        assertTrue(result);
    }
    /**
     * Unit test case in which verifying xml string for module object with list inside list.
     */
    @Test
    public void proceessCodecHandlerForListInsideList() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();
        List<Object> yangModuleList = new ArrayList<>();
        boolean result;

        // Creating the object
        PathId pathId = new PathId(123);
        Origin origin = new DefaultOrigin.OriginBuilder().value(pathId).build();
        MultiExitDisc multiExitDisc = new DefaultMultiExitDisc.MultiExitDiscBuilder().med(456).build();
        LocalPref localPref = new DefaultLocalPref.LocalPrefBuilder().pref(23).build();
        Metric metric = new Metric(456);
        AigpTlv aigpTlv = new DefaultAigpTlv.AigpTlvBuilder().metric(metric).build();
        Aigp aigp = new DefaultAigp.AigpBuilder().aigpTlv(aigpTlv).build();

        byte[] bytes = new byte[]{1, 2, 3};

        UnrecognizedAttributes unrecognizedAttributes1 = new DefaultUnrecognizedAttributes
                .UnrecognizedAttributesBuilder()
                .partial(false).transitive(false).type((short) 1).value("ABC".getBytes()).build();

        UnrecognizedAttributes unrecognizedAttributes2 = new DefaultUnrecognizedAttributes
                .UnrecognizedAttributesBuilder()
                .partial(true).transitive(true).type((short) 2).value(bytes).build();

        UnrecognizedAttributes unrecognizedAttributes3 = new DefaultUnrecognizedAttributes
                .UnrecognizedAttributesBuilder()
                .partial(true).transitive(false).type((short) 3).value(bytes).build();

        UnrecognizedAttributes unrecognizedAttributes4 = new DefaultUnrecognizedAttributes
                .UnrecognizedAttributesBuilder()
                .partial(false).transitive(true).type((short) 4).value(bytes).build();

        AsNum asNum1 = new AsNum(11);
        As4BytesCapability as4BytesCapability1 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                .asNumber(asNum1).build();
        Cparameters cparameters1 = new DefaultCparameters.CparametersBuilder().as4BytesCapability(as4BytesCapability1)
                .build();
        OptionalCapabilities optionalCapabilities1 = new DefaultOptionalCapabilities.OptionalCapabilitiesBuilder()
                .cParameters(cparameters1).build();

        AsNum asNum2 = new AsNum(22);
        As4BytesCapability as4BytesCapability2 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                .asNumber(asNum2).build();
        Cparameters cparameters2 = new DefaultCparameters.CparametersBuilder().as4BytesCapability(as4BytesCapability2)
                .build();
        OptionalCapabilities optionalCapabilities2 = new DefaultOptionalCapabilities.OptionalCapabilitiesBuilder()
                .cParameters(cparameters2).build();

        AsNum asNum3 = new AsNum(33);
        As4BytesCapability as4BytesCapability3 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                .asNumber(asNum3).build();
        Cparameters cparameters3 = new DefaultCparameters.CparametersBuilder().as4BytesCapability(as4BytesCapability3)
                .build();
        OptionalCapabilities optionalCapabilities3 = new DefaultOptionalCapabilities.OptionalCapabilitiesBuilder()
                .cParameters(cparameters3).build();

        BgpParameters bgpParameters1 = new DefaultBgpParameters.BgpParametersBuilder()
                .addToOptionalCapabilities(optionalCapabilities1)
                .addToOptionalCapabilities(optionalCapabilities2).addToOptionalCapabilities(optionalCapabilities3)
                .build();

        AsNum asNum4 = new AsNum(11);
        As4BytesCapability as4BytesCapability4 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                .asNumber(asNum4).build();
        Cparameters cparameters4 = new DefaultCparameters.CparametersBuilder().as4BytesCapability(as4BytesCapability4)
                .build();
        OptionalCapabilities optionalCapabilities4 = new DefaultOptionalCapabilities.OptionalCapabilitiesBuilder()
                .cParameters(cparameters4).build();

        AsNum asNum5 = new AsNum(22);
        As4BytesCapability as4BytesCapability5 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                .asNumber(asNum5).build();
        Cparameters cparameters5 = new DefaultCparameters.CparametersBuilder().as4BytesCapability(as4BytesCapability5)
                .build();
        OptionalCapabilities optionalCapabilities5 = new DefaultOptionalCapabilities.OptionalCapabilitiesBuilder()
                .cParameters(cparameters5).build();

        AsNum asNum6 = new AsNum(33);
        As4BytesCapability as4BytesCapability6 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                .asNumber(asNum6).build();
        Cparameters cparameters6 = new DefaultCparameters.CparametersBuilder().as4BytesCapability(as4BytesCapability6)
                .build();
        OptionalCapabilities optionalCapabilities6 = new DefaultOptionalCapabilities.OptionalCapabilitiesBuilder()
                .cParameters(cparameters6).build();

        BgpParameters bgpParameters2 = new DefaultBgpParameters.BgpParametersBuilder()
                .addToOptionalCapabilities(optionalCapabilities4)
                .addToOptionalCapabilities(optionalCapabilities5).addToOptionalCapabilities(optionalCapabilities6)
                .build();

        Attributes attributes = new DefaultAttributes.AttributesBuilder()
                .origin(origin)
                .multiExitDisc(multiExitDisc)
                .localPref(localPref)
                .aigp(aigp)
                .addToUnrecognizedAttributes(unrecognizedAttributes1)
                .addToUnrecognizedAttributes(unrecognizedAttributes2)
                .addToUnrecognizedAttributes(unrecognizedAttributes3)
                .addToUnrecognizedAttributes(unrecognizedAttributes4)
                .addToBgpParameters(bgpParameters1)
                .addToBgpParameters(bgpParameters2).build();
        Object object = CombinedOpParam.builder().attributes(attributes).build();

        yangModuleList.add(object);

        // Get the xml string and compare
        Map<String, String> tagAttr = new HashMap<String, String>();
        tagAttr.put("type", "subtree");

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);
        String xmlOutput = defaultYangCodecHandler.encodeOperation("filter", "ydt.filter-type", tagAttr,
                                                           yangModuleList, YangProtocolEncodingFormat.XML_ENCODING,
                                                                   null);
        result = xmlOutput.equals("<filter xmlns=\"ydt.filter-type\" type=\"subtree\">" +
                                          "<Combined xmlns=\"urn:opendaylight:params:xml:ns:ych:combined\">" +
                                          "<attributes>" +
                                          "<origin><value>123</value></origin>" +
                                          "<multi-exit-disc><med>456</med></multi-exit-disc>" +
                                          "<local-pref><pref>23</pref></local-pref>" +
                                          "<aigp>" +
                                          "<aigp-tlv><metric>456</metric></aigp-tlv>" +
                                          "</aigp>" +
                                          "<unrecognized-attributes>" +
                                          "<partial>false</partial>" +
                                          "<transitive>false</transitive>" +
                                          "<type>1</type>" +
                                          "<value>[B@2a17b7b6</value>" +
                                          "</unrecognized-attributes>" +
                                          "<unrecognized-attributes>" +
                                          "<partial>true</partial>" +
                                          "<transitive>true</transitive>" +
                                          "<type>2</type>" +
                                          "<value>[B@4f063c0a</value>" +
                                          "</unrecognized-attributes>" +
                                          "<unrecognized-attributes>" +
                                          "<partial>true</partial>" +
                                          "<transitive>false</transitive>" +
                                          "<type>3</type>" +
                                          "<value>[B@4f063c0a</value>" +
                                          "</unrecognized-attributes>" +
                                          "<unrecognized-attributes>" +
                                          "<partial>false</partial>" +
                                          "<transitive>true</transitive>" +
                                          "<type>4</type>" +
                                          "<value>[B@4f063c0a</value>" +
                                          "</unrecognized-attributes>" +
                                          "<bgp-parameters>" +
                                          "<optional-capabilities>" +
                                          "<c-parameters>" +
                                          "<as4-bytes-capability>" +
                                          "<as-number>11</as-number>" +
                                          "</as4-bytes-capability>" +
                                          "</c-parameters>" +
                                          "</optional-capabilities>" +
                                          "<optional-capabilities>" +
                                          "<c-parameters>" +
                                          "<as4-bytes-capability>" +
                                          "<as-number>22</as-number>" +
                                          "</as4-bytes-capability>" +
                                          "</c-parameters>" +
                                          "</optional-capabilities>" +
                                          "<optional-capabilities>" +
                                          "<c-parameters>" +
                                          "<as4-bytes-capability>" +
                                          "<as-number>33</as-number>" +
                                          "</as4-bytes-capability>" +
                                          "</c-parameters>" +
                                          "</optional-capabilities>" +
                                          "</bgp-parameters>" +
                                          "<bgp-parameters>" +
                                          "<optional-capabilities>" +
                                          "<c-parameters>" +
                                          "<as4-bytes-capability>" +
                                          "<as-number>11</as-number>" +
                                          "</as4-bytes-capability>" +
                                          "</c-parameters>" +
                                          "</optional-capabilities>" +
                                          "<optional-capabilities>" +
                                          "<c-parameters>" +
                                          "<as4-bytes-capability>" +
                                          "<as-number>22</as-number>" +
                                          "</as4-bytes-capability>" +
                                          "</c-parameters>" +
                                          "</optional-capabilities>" +
                                          "<optional-capabilities>" +
                                          "<c-parameters>" +
                                          "<as4-bytes-capability>" +
                                          "<as-number>33</as-number>" +
                                          "</as4-bytes-capability>" +
                                          "</c-parameters>" +
                                          "</optional-capabilities>" +
                                          "</bgp-parameters>" +
                                          "</attributes>" +
                                          "</Combined>" +
                                          "</filter>");
        //assertTrue(result);
    }

    /**
     * Unit test case in which verifying xml string for module object with container.
     */
    @Test
    public void proceessCodecHandlerForContainer() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();
        List<Object> yangModuleList = new ArrayList<>();
        boolean result;

        // Creating the object
        YchPurchasingSupervisor supervisor = new DefaultYchPurchasingSupervisor.YchPurchasingSupervisorBuilder()
                .ychPurchasingSpecialist("purchasingSpecialist").ychPurchasingSupport("support")
        .onosYangNodeOperationType(DefaultYchPurchasingSupervisor.OnosYangNodeOperationType.CREATE).build();
        Object object = YchPurchasingsupervisorOpParam.builder().ychPurchasingSupervisor(supervisor).build();
        yangModuleList.add(object);

        // Get the xml string and compare
        Map<String, String> tagAttr = new HashMap<String, String>();
        tagAttr.put("type", "subtree");

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);
        String xmlOutPut = defaultYangCodecHandler.encodeOperation("filter", "ydt.filter-type", tagAttr,
                                                           yangModuleList, YangProtocolEncodingFormat.XML_ENCODING,
                                                                   null);
        result = xmlOutPut.equals("<filter xmlns=\"ydt.filter-type\" type=\"subtree\">" +
                                          "<ych-purchasingsupervisor xmlns=\"ych.purchasing-supervisor\">" +
                                          "<ych-purchasing-supervisor operation=\"create\">" +
                                          "<ych-purchasing-specialist>purchasingSpecialist" +
                                          "</ych-purchasing-specialist>" +
                                          "<ych-purchasing-support>support</ych-purchasing-support>" +
                                          "</ych-purchasing-supervisor>" +
                                          "</ych-purchasingsupervisor>" +
                                          "</filter>");
        assertTrue(result);
    }

    /**
     * Unit test case in which verifying xml string for module object with leaf list.
     */
    @Test
    public void proceessCodecHandlerForLeafList() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();
        List<Object> yangModuleList = new ArrayList<>();
        boolean result;

        // Creating the object
        List<String> employeeid = EmployeeidOpParam.builder().employeeid();
        employeeid.add("Employ1");
        employeeid.add("Employ2");
        employeeid.add("Employ3");
        employeeid.add("Employ4");
        employeeid.add("Employ5");

        Object object = EmployeeidOpParam.builder().employeeid(employeeid).build();
        yangModuleList.add(object);

        // Get the xml string and compare
        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);
        String xmlOutPut = defaultYangCodecHandler.encodeOperation("config", "ydt.root", null, yangModuleList,
                                                                   YangProtocolEncodingFormat.XML_ENCODING, null);

        result = xmlOutPut.equals("<config xmlns=\"ydt.root\">" +
                                          "<employeeid xmlns=\"ydt.employee-id\">" +
                                          "<employeeid>Employ1</employeeid>" +
                                          "<employeeid>Employ5</employeeid>" +
                                          "<employeeid>Employ4</employeeid>" +
                                          "<employeeid>Employ3</employeeid>" +
                                          "<employeeid>Employ2</employeeid>" +
                                          "</employeeid>" +
                                          "</config>");
        assertTrue(result);

        List<String> supervisor = WarehousesupervisorOpParam.builder().supervisor();
        supervisor.add("supervisor1");
        supervisor.add("supervisor2");
        supervisor.add("supervisor3");
        supervisor.add("supervisor4");
        supervisor.add("supervisor5");

        object = WarehousesupervisorOpParam.builder().supervisor(supervisor).build();
        yangModuleList.clear();
        yangModuleList.add(object);

        // Get the xml string and compare
        xmlOutPut = defaultYangCodecHandler.encodeOperation("config", "ydt.root", null, yangModuleList,
                                                            YangProtocolEncodingFormat.XML_ENCODING, null);

        result = xmlOutPut.equals("<config xmlns=\"ydt.root\">" +
                                          "<warehousesupervisor xmlns=\"ydt.warehouse-supervisor\">" +
                                          "<supervisor>supervisor4</supervisor>" +
                                          "<supervisor>supervisor5</supervisor>" +
                                          "<supervisor>supervisor1</supervisor>" +
                                          "<supervisor>supervisor2</supervisor>" +
                                          "<supervisor>supervisor3</supervisor>" +
                                          "</warehousesupervisor>" +
                                          "</config>");
        assertTrue(result);
    }

    /**
     * Unit test case in which verifying xml string for multiple module object.
     */
    @Test
    public void proceessCodecHandlerForMultipleModule() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        List<Object> yangModuleList = new ArrayList<>();
        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);

        // Creating the object for customssupervisor module
        Object object = CustomssupervisorOpParam.builder().supervisor("Customssupervisor").build();
        yangModuleList.add(object);

        // Creating the object for merchandisersupervisor module
        object = MerchandisersupervisorOpParam.builder().supervisor("Merchandisersupervisor").build();
        yangModuleList.add(object);

        // Creating the object for materialsupervisor module
        Supervisor supervisor1 = new DefaultSupervisor.SupervisorBuilder()
                .name("abc1").departmentId("xyz1").build();
        Supervisor supervisor2 = new DefaultSupervisor.SupervisorBuilder()
                .name("abc2").departmentId("xyz2").build();
        Supervisor supervisor3 = new DefaultSupervisor.SupervisorBuilder()
                .name("abc3").departmentId("xyz3").build();
        Supervisor supervisor4 = new DefaultSupervisor.SupervisorBuilder()
                .name("abc4").departmentId("xyz4").build();
        Supervisor supervisor5 = new DefaultSupervisor.SupervisorBuilder()
                .name("abc5").departmentId("xyz5").build();

        object = MaterialsupervisorOpParam.builder().addToSupervisor(supervisor1)
                .addToSupervisor(supervisor2).addToSupervisor(supervisor3).addToSupervisor(supervisor4)
                .addToSupervisor(supervisor5).build();

        yangModuleList.add(object);

        // Creating the object for YchPurchasingsupervisor module
        YchPurchasingSupervisor purSupervisor = new DefaultYchPurchasingSupervisor.YchPurchasingSupervisorBuilder()
                .ychPurchasingSpecialist("purchasingSpecialist").ychPurchasingSupport("support").build();
        object = YchPurchasingsupervisorOpParam.builder().ychPurchasingSupervisor(purSupervisor).build();
        yangModuleList.add(object);

        // Creating the object for warehousesupervisor module
        List<String> supervisor = WarehousesupervisorOpParam.builder().supervisor();
        supervisor.add("supervisor1");
        supervisor.add("supervisor2");
        supervisor.add("supervisor3");
        supervisor.add("supervisor4");
        supervisor.add("supervisor5");

        object = WarehousesupervisorOpParam.builder().supervisor(supervisor).build();
        yangModuleList.add(object);

        // Creating the object for tradingsupervisor module
        object = TradingsupervisorOpParam.builder().supervisor("Tradingsupervisor").build();
        yangModuleList.add(object);

        List<String> employeeid = EmployeeidOpParam.builder().employeeid();
        employeeid.add("Employ1");
        employeeid.add("Employ2");
        employeeid.add("Employ3");
        employeeid.add("Employ4");
        employeeid.add("Employ5");

        // Creating the object for employeeid module
        object = EmployeeidOpParam.builder().employeeid(employeeid).build();
        yangModuleList.add(object);

        // Get the xml string and compare
        String xmlOutPut = defaultYangCodecHandler.encodeOperation("config", "ydt.root", null, yangModuleList,
                                                                   YangProtocolEncodingFormat.XML_ENCODING, null);
        boolean result;
        result = xmlOutPut.equals("<config xmlns=\"ydt.root\">" +
                                          "<customssupervisor xmlns=\"ydt.customs-supervisor\">" +
                                              "<supervisor>Customssupervisor</supervisor>" +
                                          "</customssupervisor>" +
                                          "<merchandisersupervisor xmlns=\"ydt.Merchandiser-supervisor\">" +
                                              "<supervisor>Merchandisersupervisor</supervisor>" +
                                          "</merchandisersupervisor>" +
                                          "<materialsupervisor xmlns=\"ydt.material-supervisor\">" +
                                              "<supervisor>" +
                                                  "<name>abc1</name>" +
                                                  "<departmentId>xyz1</departmentId>" +
                                              "</supervisor>" +
                                              "<supervisor>" +
                                                  "<name>abc2</name>" +
                                                  "<departmentId>xyz2</departmentId>" +
                                              "</supervisor>" +
                                              "<supervisor>" +
                                                  "<name>abc3</name>" +
                                                  "<departmentId>xyz3</departmentId>" +
                                              "</supervisor>" +
                                              "<supervisor>" +
                                                  "<name>abc4</name>" +
                                                  "<departmentId>xyz4</departmentId>" +
                                              "</supervisor>" +
                                              "<supervisor>" +
                                                  "<name>abc5</name>" +
                                                  "<departmentId>xyz5</departmentId>" +
                                              "</supervisor>" +
                                          "</materialsupervisor>" +
                                          "<ych-purchasingsupervisor xmlns=\"ych.purchasing-supervisor\">" +
                                              "<ych-purchasing-supervisor>" +
                                                  "<ych-purchasing-specialist>purchasingSpecialist" +
                                                  "</ych-purchasing-specialist>" +
                                                  "<ych-purchasing-support>support</ych-purchasing-support>" +
                                                  "</ych-purchasing-supervisor>" +
                                          "</ych-purchasingsupervisor>" +
                                          "<warehousesupervisor xmlns=\"ydt.warehouse-supervisor\">" +
                                              "<supervisor>supervisor4</supervisor>" +
                                              "<supervisor>supervisor5</supervisor>" +
                                              "<supervisor>supervisor1</supervisor>" +
                                              "<supervisor>supervisor2</supervisor>" +
                                              "<supervisor>supervisor3</supervisor>" +
                                          "</warehousesupervisor>" +
                                          "<tradingsupervisor xmlns=\"ydt.trading-supervisor\">" +
                                              "<supervisor>Tradingsupervisor</supervisor>" +
                                          "</tradingsupervisor>" +
                                          "<employeeid xmlns=\"ydt.employee-id\">" +
                                              "<employeeid>Employ1</employeeid>" +
                                              "<employeeid>Employ5</employeeid>" +
                                              "<employeeid>Employ4</employeeid>" +
                                              "<employeeid>Employ3</employeeid>" +
                                              "<employeeid>Employ2</employeeid>" +
                                          "</employeeid>" +
                                          "</config>");
        assertTrue(result);
    }

    /**
     * Unit test case in which verifying object for xml string with config as root name and multiple module.
     */
    @Test
    public void proceessCodecDecodeFunction() {

        boolean result;
        List<Object> objectList = null;
        String path = "src/test/resources/ychTestResourceFiles/configrootname.xml";
        StringBuilder sb = new StringBuilder();
        String sCurrentLine;
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verify the received object list
        objectList = defaultYangCodecHandler.decode(sb.toString(), YangProtocolEncodingFormat.XML_ENCODING, null);
        Iterator<Object> iterator = objectList.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object.getClass().getSimpleName().equals("LogisticsManagerOpParam")) {
                LogisticsManagerOpParam logisticsManagerOpParam = (LogisticsManagerOpParam) object;
                assertTrue(logisticsManagerOpParam.customsSupervisor().equals("abc"));
                assertTrue(logisticsManagerOpParam.purchasingSupervisor().purchasingSpecialist().equals("bcd"));
                assertTrue(logisticsManagerOpParam.purchasingSupervisor().support().equals("cde"));
            } else if (object.getClass().getSimpleName().equals("MerchandisersupervisorOpParam")) {
                MerchandisersupervisorOpParam merchandisersupervisorOpParam = (MerchandisersupervisorOpParam) object;
                assertTrue(merchandisersupervisorOpParam.supervisor().equals("abc"));
            } else {
                assertTrue(false);
            }
        }
    }

    /**
     * Unit test case in which verifying object for xml string with config as root name and empty container.
     */
    @Test
    public void proceessCodecDecodeFunctionForEmptyContainer() {

        boolean result;
        List<Object> objectList = null;
        String path = "src/test/resources/ychTestResourceFiles/getconfigemptycontainer.xml";
        StringBuilder sb = new StringBuilder();
        String sCurrentLine;
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verify the received object list
        objectList = defaultYangCodecHandler.decode(sb.toString(), YangProtocolEncodingFormat.XML_ENCODING, null);
        Iterator<Object> iterator = objectList.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object.getClass().getSimpleName().equals("EmptyContainerOpParam")) {
                EmptyContainerOpParam emptyContainerOpParam  = (EmptyContainerOpParam) object;
                assertTrue(emptyContainerOpParam.emptyContainer() == null);
            } else {
                assertTrue(false);
            }
        }
    }
    /**
     * Unit test case in which verifying object for xml string with config as root name and multiple module.
     */
    @Test
    public void proceessCodecDecodeFunctionForListInsideList() {

        boolean result;
        List<Object> objectList = null;
        String path = "src/test/resources/ychTestResourceFiles/combinedrootname.xml";
        StringBuilder sb = new StringBuilder();
        String sCurrentLine;
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO Need to verify test result once YTB support binary.
        // Verify the received object list
        /*objectList = defaultYangCodecHandler.decode(sb.toString(), YangProtocolEncodingFormat.XML_ENCODING);
        Iterator<Object> iterator = objectList.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object.getClass().getSimpleName().equals("CombinedOpParam")) {
                CombinedOpParam combinedOpParam = (CombinedOpParam)object;

                PathId pathId = new PathId(123);
                Origin origin = new DefaultOrigin.OriginBuilder().value(pathId).build();
                assertTrue(combinedOpParam.attributes().origin().equals(origin));

                MultiExitDisc multiExitDisc = new DefaultMultiExitDisc.MultiExitDiscBuilder().med(456).build();
                assertTrue(combinedOpParam.attributes().multiExitDisc().equals(multiExitDisc));

                LocalPref localPref = new DefaultLocalPref.LocalPrefBuilder().pref(23).build();
                assertTrue(combinedOpParam.attributes().localPref().equals(localPref));

                Metric metric = new Metric(456);
                AigpTlv aigpTlv = new DefaultAigpTlv.AigpTlvBuilder().metric(metric).build();
                Aigp aigp = new DefaultAigp.AigpBuilder().aigpTlv(aigpTlv).build();
                assertTrue(combinedOpParam.attributes().aigp().equals(aigp));

                UnrecognizedAttributes unrecognizedAttributes1 = new DefaultUnrecognizedAttributes
                        .UnrecognizedAttributesBuilder()
                        .partial(false).transitive(false).type((short) 1).value("ABC".getBytes()).build();

                UnrecognizedAttributes unrecognizedAttributes2 = new DefaultUnrecognizedAttributes
                        .UnrecognizedAttributesBuilder()
                        .partial(true).transitive(true).type((short) 2).value("BCA".getBytes()).build();

                UnrecognizedAttributes unrecognizedAttributes3 = new DefaultUnrecognizedAttributes
                        .UnrecognizedAttributesBuilder()
                        .partial(true).transitive(false).type((short) 3).value("CAB".getBytes()).build();

                UnrecognizedAttributes unrecognizedAttributes4 = new DefaultUnrecognizedAttributes
                        .UnrecognizedAttributesBuilder()
                        .partial(false).transitive(true).type((short) 4).value("111".getBytes()).build();

                AsNum asNum1 = new AsNum(11);
                As4BytesCapability as4BytesCapability1 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                        .asNumber(asNum1).build();
                Cparameters cparameters1 = new DefaultCparameters.CparametersBuilder()
                .as4BytesCapability(as4BytesCapability1)
                        .build();
                OptionalCapabilities optionalCapabilities1 = new DefaultOptionalCapabilities
                .OptionalCapabilitiesBuilder()
                        .cParameters(cparameters1).build();

                AsNum asNum2 = new AsNum(22);
                As4BytesCapability as4BytesCapability2 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                        .asNumber(asNum2).build();
                Cparameters cparameters2 = new DefaultCparameters.CparametersBuilder()
                .as4BytesCapability(as4BytesCapability2)
                        .build();
                OptionalCapabilities optionalCapabilities2 = new DefaultOptionalCapabilities
                .OptionalCapabilitiesBuilder()
                        .cParameters(cparameters2).build();

                AsNum asNum3 = new AsNum(33);
                As4BytesCapability as4BytesCapability3 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                        .asNumber(asNum3).build();
                Cparameters cparameters3 = new DefaultCparameters.CparametersBuilder()
                .as4BytesCapability(as4BytesCapability3)
                        .build();
                OptionalCapabilities optionalCapabilities3 = new DefaultOptionalCapabilities
                .OptionalCapabilitiesBuilder()
                        .cParameters(cparameters3).build();

                BgpParameters bgpParameters1 = new DefaultBgpParameters.BgpParametersBuilder()
                        .addToOptionalCapabilities(optionalCapabilities1)
                        .addToOptionalCapabilities(optionalCapabilities2)
                        .addToOptionalCapabilities(optionalCapabilities3)
                        .build();

                AsNum asNum4 = new AsNum(11);
                As4BytesCapability as4BytesCapability4 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                        .asNumber(asNum4).build();
                Cparameters cparameters4 = new DefaultCparameters.CparametersBuilder()
                .as4BytesCapability(as4BytesCapability4)
                        .build();
                OptionalCapabilities optionalCapabilities4 = new DefaultOptionalCapabilities
                .OptionalCapabilitiesBuilder()
                        .cParameters(cparameters4).build();

                AsNum asNum5 = new AsNum(22);
                As4BytesCapability as4BytesCapability5 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                        .asNumber(asNum5).build();
                Cparameters cparameters5 = new DefaultCparameters.CparametersBuilder()
                .as4BytesCapability(as4BytesCapability5)
                        .build();
                OptionalCapabilities optionalCapabilities5 = new DefaultOptionalCapabilities
                .OptionalCapabilitiesBuilder()
                        .cParameters(cparameters5).build();

                AsNum asNum6 = new AsNum(33);
                As4BytesCapability as4BytesCapability6 = new DefaultAs4BytesCapability.As4BytesCapabilityBuilder()
                        .asNumber(asNum6).build();
                Cparameters cparameters6 = new DefaultCparameters.CparametersBuilder()
                .as4BytesCapability(as4BytesCapability6)
                        .build();
                OptionalCapabilities optionalCapabilities6 = new DefaultOptionalCapabilities
                .OptionalCapabilitiesBuilder()
                        .cParameters(cparameters6).build();

                BgpParameters bgpParameters2 = new DefaultBgpParameters.BgpParametersBuilder()
                        .addToOptionalCapabilities(optionalCapabilities4)
                        .addToOptionalCapabilities(optionalCapabilities5)
                        .addToOptionalCapabilities(optionalCapabilities6)
                        .build();

                Attributes attributes = new DefaultAttributes.AttributesBuilder()
                        .origin(origin)
                        .multiExitDisc(multiExitDisc)
                        .localPref(localPref)
                        .aigp(aigp)
                        .addToUnrecognizedAttributes(unrecognizedAttributes1)
                        .addToUnrecognizedAttributes(unrecognizedAttributes2)
                        .addToUnrecognizedAttributes(unrecognizedAttributes3)
                        .addToUnrecognizedAttributes(unrecognizedAttributes4)
                        .addToBgpParameters(bgpParameters1)
                        .addToBgpParameters(bgpParameters2).build();
            } else {
                assertTrue(false);
            }
        }*/
    }

    /**
     * Unit test case in which verifying object for xml string with config as root name and
     * operation type.
     */
    @Test
    public void proceessCodecDecodeFunctionForOperTypeTest() {

        boolean result;
        List<Object> objectList = null;
        String path = "src/test/resources/ychTestResourceFiles/configrootnameOperationType.xml";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);

        StringBuilder sb = new StringBuilder();
        String sCurrentLine;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verify the received object list
        objectList = defaultYangCodecHandler.decode(sb.toString(), YangProtocolEncodingFormat.XML_ENCODING, null);
        Iterator<Object> iterator = objectList.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object.getClass().getSimpleName().equals("LogisticsManagerOpParam")) {
                LogisticsManagerOpParam logistics = (LogisticsManagerOpParam) object;
                DefaultPurchasingSupervisor purchasingSupervisor = (DefaultPurchasingSupervisor) logistics
                                                                    .purchasingSupervisor();
                assertTrue(purchasingSupervisor.onosYangNodeOperationType().equals(DELETE));
                assertTrue(logistics.customsSupervisor().equals("abc"));
                assertTrue(logistics.purchasingSupervisor().purchasingSpecialist().equals("bcd"));
                assertTrue(logistics.purchasingSupervisor().support().equals("cde"));
            } else if (object.getClass().getSimpleName().equals("MerchandisersupervisorOpParam")) {
                MerchandisersupervisorOpParam merchandisersupervisorOpParam = (MerchandisersupervisorOpParam) object;
                assertTrue(merchandisersupervisorOpParam.supervisor().equals("abc"));
            } else {
                assertTrue(false);
            }
        }
    }
    /**
     * Unit test case in which verifying object for xml string with get and filter as root name.
     */
    @Test
    public void proceessCodecDecodeFunctionForGet() {

        boolean result;
        List<Object> objectList = null;
        String path = "src/test/resources/ychTestResourceFiles/getrootname.xml";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);

        StringBuilder sb = new StringBuilder();
        String sCurrentLine;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verify the received object list
        objectList = defaultYangCodecHandler.decode(sb.toString(), YangProtocolEncodingFormat.XML_ENCODING, null);
        Iterator<Object> iterator = objectList.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object.getClass().getSimpleName().equals("LogisticsManagerOpParam")) {
                LogisticsManagerOpParam logisticsManagerOpParam = (LogisticsManagerOpParam) object;
                assertTrue(logisticsManagerOpParam.purchasingSupervisor().purchasingSpecialist().equals("bcd"));
            } else {
                assertTrue(false);
            }
        }
    }
    /**
     * Unit test case in which verifying object for xml string with get-config and filter as root name.
     */
    @Test
    public void proceessCodecDecodeFunctionForGetConfig() {

        boolean result;
        List<Object> objectList = null;
        String path = "src/test/resources/ychTestResourceFiles/getconfigrootname.xml";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);

        StringBuilder sb = new StringBuilder();
        String sCurrentLine;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verify the received object list
        objectList = defaultYangCodecHandler.decode(sb.toString(), YangProtocolEncodingFormat.XML_ENCODING, null);
        Iterator<Object> iterator = objectList.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object.getClass().getSimpleName().equals("MerchandisersupervisorOpParam")) {
                MerchandisersupervisorOpParam merchandisersupervisorOpParam = (MerchandisersupervisorOpParam) object;
                assertTrue(merchandisersupervisorOpParam.supervisor().equals("abc"));
            } else {
                assertTrue(false);
            }
        }
    }
    /**
     * Unit test case in which verifying object for xml string with data as root name.
     */
    @Test
    public void proceessCodecDecodeFunctionForGetData() {

        boolean result;
        List<Object> objectList = null;
        String path = "src/test/resources/ychTestResourceFiles/getReply.xml";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);

        StringBuilder sb = new StringBuilder();
        String sCurrentLine;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verify the received object list
        objectList = defaultYangCodecHandler.decode(sb.toString(), YangProtocolEncodingFormat.XML_ENCODING, null);
        Iterator<Object> iterator = objectList.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object.getClass().getSimpleName().equals("LogisticsManagerOpParam")) {
                LogisticsManagerOpParam logisticsManagerOpParam = (LogisticsManagerOpParam) object;
                assertTrue(logisticsManagerOpParam.purchasingSupervisor().purchasingSpecialist().equals("bcd"));
            } else {
                assertTrue(false);
            }
        }
    }
    /**
     * Unit test case in which verifying object for xml string with rpc-reply and data as root name .
     */
    @Test
    public void proceessCodecDecodeFunctionForGetConfigData() {

        boolean result;
        List<Object> objectList = null;
        String path = "src/test/resources/ychTestResourceFiles/getconfigReply.xml";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry schemaRegistry = testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        YangDataTreeCodec yangDataTreeCodec = new YchYangDataTreeCodec();
        defaultCodecs.put(YangProtocolEncodingFormat.XML_ENCODING, yangDataTreeCodec);
        DefaultYangCodecHandler defaultYangCodecHandler = new DefaultYangCodecHandler(schemaRegistry, defaultCodecs);

        StringBuilder sb = new StringBuilder();
        String sCurrentLine;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verify the received object list
        objectList = defaultYangCodecHandler.decode(sb.toString(), YangProtocolEncodingFormat.XML_ENCODING, null);
        Iterator<Object> iterator = objectList.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object.getClass().getSimpleName().equals("MerchandisersupervisorOpParam")) {
                MerchandisersupervisorOpParam merchandisersupervisorOpParam = (MerchandisersupervisorOpParam) object;
                assertTrue(merchandisersupervisorOpParam.supervisor().equals("abc"));
            } else {
                assertTrue(false);
            }
        }
    }
}