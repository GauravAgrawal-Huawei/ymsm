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

package org.onosproject.yms.app.ytb;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826.YtbDerivedTypeWithBitsAndBinary;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826.YtbDerivedTypeWithBitsAndBinaryOpParam;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826.ytbderivedtypewithbitsandbinary.Derivedbinarya;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826.ytbderivedtypewithbitsandbinary.Derivedbinaryb;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826.ytbderivedtypewithbitsandbinary.Derivedbitsa;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826.ytbderivedtypewithbitsandbinary.Derivedbitsb;
import org.onosproject.yang.gen.v1.yms.test.ytb.derived.type.with.bits.and.binary.rev20160826.ytbderivedtypewithbitsandbinary.ForunionUnion;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.container.rev20160826.YtbModuleWithContainer;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.container.rev20160826.YtbModuleWithContainerOpParam;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.container.rev20160826.ytbmodulewithcontainer.DefaultSched;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.container.rev20160826.ytbmodulewithcontainer.Sched;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826.YtbIetfSchedule;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826.YtbIetfScheduleOpParam;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826.ytbietfschedule.Enum1Enum;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826.ytbietfschedule.Enum2Enum;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaflist.rev20160826.YtbModuleWithLeafList;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaflist.rev20160826.YtbModuleWithLeafListOpParam;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.list.rev20160826.YtbModuleWithList;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.list.rev20160826.YtbModuleWithListOpParam;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.list.rev20160826.ytbmodulewithlist.DefaultYtblistlist;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.list.rev20160826.ytbmodulewithlist.Find;
import org.onosproject.yang.gen.v1.yms.test.ytb.module.with.list.rev20160826.ytbmodulewithlist.Ytblistlist;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.a.rev20160826.YtbMultiModulea;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.a.rev20160826.YtbMultiModuleaOpParam;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.a.rev20160826.ytbmultimodulea.DefaultYtbmultilist;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.a.rev20160826.ytbmultimodulea.Ytbmultilist;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.b.rev20160826.YtbMultiModuleb;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.b.rev20160826.YtbMultiModulebOpParam;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.b.rev20160826.ytbmultimoduleb.DefaultYtbmultilistb;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.module.b.rev20160826.ytbmultimoduleb.Ytbmultilistb;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.notification.with.container.rev20160826.ytbmultinotificationwithcontainer.DefaultFortesta;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.notification.with.container.rev20160826.ytbmultinotificationwithcontainer.Fortesta;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.notification.with.container.rev20160826.ytbmultinotificationwithcontainer.YtbMultiNotificationWithContainerEvent;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.notification.with.container.rev20160826.ytbmultinotificationwithcontainer.YtbMultiNotificationWithContainerEventSubject;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.notification.with.container.rev20160826.ytbmultinotificationwithcontainer.fortesta.DefaultYtbnot;
import org.onosproject.yang.gen.v1.yms.test.ytb.multi.notification.with.container.rev20160826.ytbmultinotificationwithcontainer.fortesta.Ytbnot;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826.YtbTreeBuilderForListHavingList;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826.YtbTreeBuilderForListHavingListOpParam;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826.ytbtreebuilderforlisthavinglist.Carrier;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826.ytbtreebuilderforlisthavinglist.DefaultCarrier;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826.ytbtreebuilderforlisthavinglist.carrier.DefaultMultiplexes;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826.ytbtreebuilderforlisthavinglist.carrier.Multiplexes;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826.ytbtreebuilderforlisthavinglist.carrier.multiplexes.ApplicationAreas;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826.ytbtreebuilderforlisthavinglist.carrier.multiplexes.DefaultApplicationAreas;
import org.onosproject.yang.gen.v1.yms.test.ytb.tree.builder.yangautoprefixfor.list.having.list.rev20160826.ytbtreebuilderforlisthavinglist.carrier.multiplexes.TypesEnum;
import org.onosproject.yms.app.ydt.YdtExtendedBuilder;
import org.onosproject.yms.app.ydt.YdtNode;
import org.onosproject.yms.app.ysr.DefaultYangSchemaRegistry;
import org.onosproject.yms.app.ysr.TestYangSchemaNodeProvider;
import org.onosproject.yms.ydt.YdtBuilder;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;
import org.onosproject.yms.ydt.YmsOperationType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.onosproject.yang.gen.v1.yms.test.ytb.module.with.leaf.ietfschedule.rev20160826.YtbIetfScheduleOpParam.OnosYangNodeOperationType;
import static org.onosproject.yms.ydt.YmsOperationType.EDIT_CONFIG_REQUEST;

/**
 * Unit test cases for YANG tree builder with different YANG object
 * configuration.
 *
 * // TODO: Unregistering all the registered service from YSR has to be done.
 */
public class DefaultYangTreeBuilderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private TestYangSchemaNodeProvider testYangSchemaNodeProvider =
            new TestYangSchemaNodeProvider();

    /**
     * Processes an empty object list to the YTB and checks that the
     * exception is thrown.
     */
    @Test
    public void processInvalidListInput() {
        thrown.expect(YtbException.class);
        thrown.expectMessage(
                "The input module/sub-module object list, cannot be null.");
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        yangTreeBuilder.getYdtBuilderForYo(null, "rootName", "rootNamespace",
                                           EDIT_CONFIG_REQUEST, null);
    }

    /**
     * Processes an empty notification object to the YTB and checks that the
     * exception is thrown.
     */
    @Test
    public void processInvalidListInputForNotification() {
        thrown.expect(YtbException.class);
        thrown.expectMessage(
                "The input module/sub-module object list, cannot be null.");
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        yangTreeBuilder.getYdtForNotification(null, "rootName", null);
    }

    /**
     * Processes a YAB/YSB request to YTB with a leaf value being filled in
     * the app object. Checks the constructed YDT tree for module and leaf
     * and its value.
     */
    @Test
    public void processModuleAndLeaf() {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry defaultTreeBuilder =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        YtbIetfSchedule ietfSchedule =
                new YtbIetfScheduleOpParam.YtbIetfScheduleBuilder()
                        .time((byte) 9)
                        .onosYangNodeOperationType(OnosYangNodeOperationType
                                                           .MERGE)
                        .build();


        // YSB or YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(ietfSchedule);

        // Build YANG tree.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtExtendedBuilder ydtBuilder = yangTreeBuilder
                .getYdtBuilderForYo(objectList, "rootName", "rootNamespace",
                                    EDIT_CONFIG_REQUEST, defaultTreeBuilder);

        // Receive YDT context and check the tree that is built.
        YdtContext ydtContext = ydtBuilder.getRootNode();

        // Get the first module from logical root node.
        YdtContext moduleNode = ydtContext.getFirstChild();
        String moduleNodeName = moduleNode.getName();
        YdtContextOperationType operationType = ((YdtNode) moduleNode)
                .getYdtContextOperationType();
        assertThat(moduleNodeName, is("YtbIetfSchedule"));
        assertThat(operationType, is(YdtContextOperationType.MERGE));

        // Get the first leaf from module IetfSchedule.
        YdtContext ydtContextForLeaf = moduleNode.getFirstChild();
        String leafName = ydtContextForLeaf.getName();
        String value = ydtContextForLeaf.getValue();
        assertThat(value, is("9"));
        assertThat(leafName, is("time"));
    }

    /**
     * Test for checking if for type enum ydt should have schema name instead
     * of enum's contants.
     */
    @Test
    public void processLeafAndLeafListWithTypeEnum() {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry defaultTreeBuilder =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        List<Enum2Enum> enum2Ena = new ArrayList<>();
        enum2Ena.add(Enum2Enum.HUNDRED_100);
        enum2Ena.add(Enum2Enum.TEN_10);
        enum2Ena.add(Enum2Enum.THOUSAND_1000);
        YtbIetfSchedule ietfSchedule =
                new YtbIetfScheduleOpParam.YtbIetfScheduleBuilder()
                        .time((byte) 9)
                        .onosYangNodeOperationType(
                                OnosYangNodeOperationType
                                        .MERGE).enum1(Enum1Enum.HUNDRED)
                        .enum2(enum2Ena)
                        .build();


        // YSB or YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(ietfSchedule);

        // Build YANG tree.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtExtendedBuilder ydtBuilder = yangTreeBuilder
                .getYdtBuilderForYo(objectList, "rootName", "rootNamespace",
                                    EDIT_CONFIG_REQUEST, defaultTreeBuilder);

        // Receive YDT context and check the tree that is built.
        YdtContext ydtContext = ydtBuilder.getRootNode();

        // Get the first module from logical root node.
        YdtContext moduleNode = ydtContext.getFirstChild();
        String moduleNodeName = moduleNode.getName();
        YdtContextOperationType operationType =
                ((YdtNode) moduleNode).getYdtContextOperationType();
        assertThat(moduleNodeName, is("YtbIetfSchedule"));
        assertThat(operationType, is(YdtContextOperationType.MERGE));

        // Get the first leaf from module IetfSchedule.
        YdtContext ydtContextForLeaf = moduleNode.getFirstChild();
        String leafName = ydtContextForLeaf.getName();
        String value = ydtContextForLeaf.getValue();
        assertThat(value, is("9"));
        assertThat(leafName, is("time"));

        ydtContextForLeaf = ydtContextForLeaf.getNextSibling();
        leafName = ydtContextForLeaf.getName();
        value = ydtContextForLeaf.getValue();
        assertThat(value, is("hundred"));
        assertThat(leafName, is("enum1"));

        ydtContextForLeaf = ydtContextForLeaf.getNextSibling();
        leafName = ydtContextForLeaf.getName();
        Set<String> valueSet = ydtContextForLeaf.getValueSet();
        assertThat(true, is(valueSet.contains("hundred-100")));
        assertThat(leafName, is("enum2"));
    }

    /**
     * Processes a YAB/YSB request to YTB with a leaf-list value being filled
     * in the app object. Checks the constructed YDT tree for module and
     * leaf-list and its value.
     */
    @Test
    public void processModuleAndLeafList() {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry yangSchemaRegistryTest =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();
        // Application which builds java of YANG.
        List<Long> longList = new ArrayList<>();
        longList.add((long) 1);
        longList.add((long) 2);
        longList.add((long) 3);
        YtbModuleWithLeafList ytbModuleWithLeafList =
                new YtbModuleWithLeafListOpParam.YtbModuleWithLeafListBuilder()
                        .time(longList).build();

        // YSB or YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(ytbModuleWithLeafList);

        // Build YANG tree.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtBuilder ydtBuilder =
                yangTreeBuilder.getYdtBuilderForYo(objectList, "modulewithlist",
                                                   "modulewithlistspace",
                                                   YmsOperationType
                                                           .QUERY_REQUEST,
                                                   yangSchemaRegistryTest);

        // Receive YDT context and check the tree that is built.
        YdtContext ydtContext = ydtBuilder.getRootNode();

        // Get the first module from logical root node.
        YdtContext moduleNode = ydtContext.getFirstChild();
        String moduleNodeName = moduleNode.getName();
        YdtContextOperationType operationType =
                ((YdtNode) moduleNode).getYdtContextOperationType();
        assertThat(moduleNodeName, is("YtbModuleWithLeafList"));
        assertThat(operationType, is(YdtContextOperationType.NONE));

        // Get the first leaf-list from module IetfSchedule.
        YdtContext ydtContextForLeafList = moduleNode.getFirstChild();
        String leafListName = ydtContextForLeafList.getName();
        Set<String> value = ydtContextForLeafList.getValueSet();
        assertThat(value.contains("1"), is(true));
        assertThat(value.contains("2"), is(true));
        assertThat(value.contains("3"), is(true));
        assertThat(leafListName, is("time"));
    }

    /**
     * Processes a YAB/YSB request to YTB with a container having leaf value
     * being filled in the app object. Checks the constructed YDT tree for
     * module and container and leaf.
     */
    @Test
    public void processModuleWithContainer() {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry yangSchemaRegistryTest =
                testYangSchemaNodeProvider
                        .getDefaultYangSchemaRegistry();


        // Application which builds java of YANG.
        Sched scheduleContainer = new DefaultSched.SchedBuilder()
                .predict(BigDecimal.valueOf(98989))
                .onosYangNodeOperationType(
                        DefaultSched.OnosYangNodeOperationType.DELETE).build();
        YtbModuleWithContainer moduleWithContainer = new
                YtbModuleWithContainerOpParam.YtbModuleWithContainerBuilder()
                .sched(scheduleContainer)
                .onosYangNodeOperationType(
                        YtbModuleWithContainerOpParam
                                .OnosYangNodeOperationType.CREATE)
                .build();

        // YSB or YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(moduleWithContainer);

        // Build YANG tree.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtBuilder ydtBuilder = yangTreeBuilder
                .getYdtBuilderForYo(objectList, "modulewithcontainer",
                                    "modulewithcontainerspace",
                                    YmsOperationType.QUERY_CONFIG_REQUEST,
                                    yangSchemaRegistryTest);

        // Receive YDT context and check the tree that is built.
        YdtContext ydtContext = ydtBuilder.getRootNode();

        // Get the first module from logical root node.
        YdtContext moduleNode = ydtContext.getFirstChild();
        String moduleNodeName = moduleNode.getName();
        YdtContextOperationType operationType =
                ((YdtNode) moduleNode).getYdtContextOperationType();

        assertThat(operationType, is(YdtContextOperationType.CREATE));
        assertThat(moduleNodeName, is("YtbModuleWithContainer"));

        // Get the first container from module IetfSchedule.
        YdtContext ydtContextForContainer = moduleNode.getFirstChild();
        String containerName = ydtContextForContainer.getName();
        YdtContextOperationType operationTypeForContainer =
                ((YdtNode) ydtContextForContainer)
                        .getYdtContextOperationType();

        assertThat(operationTypeForContainer,
                   is(YdtContextOperationType.DELETE));
        assertThat(containerName, is("sched"));

        YdtContext ydtContextForLeaf = ydtContextForContainer.getFirstChild();
        String leafPredict = ydtContextForLeaf.getName();
        String leafPredictValue = ydtContextForLeaf.getValue();
        assertThat(leafPredict, is("predict"));
        assertThat(leafPredictValue, is("98989"));
    }

    /**
     * Processes a YAB/YSB request to YTB with a list having leaf-list value
     * being filled in the app object. Checks the constructed YDT tree for
     * module and list and leaf-list.
     */
    @Test
    public void processModuleWithList() {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry yangSchemaRegistryTest =
                testYangSchemaNodeProvider
                        .getDefaultYangSchemaRegistry();

        // As an application build the java for YANG.
        Find valueOfFind1 = new Find(true);
        Find valueOfFind2 = new Find(false);
        Find valueOfFind3 = new Find(true);
        Find valueOfFind4 = new Find(false);
        List<Find> findList = new ArrayList<>();
        List<Find> findList1 = new ArrayList<>();
        List<Ytblistlist> ytbList = new ArrayList<>();
        findList.add(valueOfFind1);
        findList.add(valueOfFind2);
        findList1.add(valueOfFind3);
        findList1.add(valueOfFind4);
        Ytblistlist listInModule1 =
                new DefaultYtblistlist.YtblistlistBuilder().prediction(findList)
                        .build();
        Ytblistlist listInModule2 = new DefaultYtblistlist.YtblistlistBuilder()
                .prediction(findList1).build();
        ytbList.add(listInModule1);
        ytbList.add(listInModule2);
        YtbModuleWithList moduleWithList =
                new YtbModuleWithListOpParam.YtbModuleWithListBuilder()
                        .ytblistlist(ytbList).build();

        // YSB or YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(moduleWithList);

        // Build YANG tree.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtBuilder ydtBuilder =
                yangTreeBuilder.getYdtBuilderForYo(objectList, "modulewithlist",
                                                   "modulewithlistspace",
                                                   EDIT_CONFIG_REQUEST,
                                                   yangSchemaRegistryTest);

        // Receives YDT context and check the tree that is built.
        YdtContext ydtContext = ydtBuilder.getRootNode();

        // Gets the first module from logical root node.
        YdtContext moduleNode = ydtContext.getFirstChild();
        String moduleNodeName = moduleNode.getName();
        YdtContextOperationType operationType =
                ((YdtNode) moduleNode).getYdtContextOperationType();

        assertThat(operationType, is(YdtContextOperationType.NONE));
        assertThat(moduleNodeName, is("YtbModuleWithList"));

        // Gets the first list from module YtbModuleWithList.
        YdtContext ydtContextForList = moduleNode.getFirstChild();
        String listName = ydtContextForList.getName();
        YdtContextOperationType operationTypeForContainer =
                ((YdtNode) ydtContextForList)
                        .getYdtContextOperationType();

        // Checks the contents in the first list.
        assertThat(operationTypeForContainer, is(YdtContextOperationType.NONE));
        assertThat(listName, is("ytblistlist"));

        // Gets the contents of the leaf-list in the first list content.
        YdtContext ydtContextForLeafList1 = ydtContextForList.getFirstChild();
        String leafListName1 = ydtContextForLeafList1.getName();

        // Evaluates the leaf-list values.
        Set leafListValue1 = ydtContextForLeafList1.getValueSet();
        assertThat(leafListName1, is("prediction"));
        assertThat(leafListValue1.contains("true"), is(true));
        assertThat(leafListValue1.contains("false"), is(true));

        // Gets the second list from module YtbModuleWithList.
        YdtContext ydtContextForList2 = ydtContextForList.getNextSibling();

        // Gets the contents of the leaf-list in the second list content.
        YdtContext ydtContextForLeafList2 = ydtContextForList2.getFirstChild();
        String leafListName2 = ydtContextForLeafList2.getName();

        // Evaluates the leaf-list values.
        Set leafListValue2 = ydtContextForLeafList2.getValueSet();
        assertThat(leafListName2, is("prediction"));
        assertThat(leafListValue2.contains("true"), is(true));
        assertThat(leafListValue2.contains("false"), is(true));
    }

    /**
     * Processes multi notification under module and request comes for one
     * notification event in module.
     */
    @Test
    public void processMultiNotificationWithContainer() {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry yangSchemaRegistryTest =
                testYangSchemaNodeProvider
                        .getDefaultYangSchemaRegistry();

        // As an application create values.
        BitSet bitleaf = new BitSet();
        bitleaf.set(5);
        bitleaf.set(7);
        Ytbnot ytbnot =
                new DefaultYtbnot.YtbnotBuilder().notileaf(bitleaf).build();
        Fortesta testa =
                new DefaultFortesta.FortestaBuilder().ytbnot(ytbnot).build();
        YtbMultiNotificationWithContainerEventSubject eventSubject = new
                YtbMultiNotificationWithContainerEventSubject();
        eventSubject.fortesta(testa);
        YtbMultiNotificationWithContainerEvent event =
                new YtbMultiNotificationWithContainerEvent(
                        YtbMultiNotificationWithContainerEvent
                                .Type.FORTESTA, eventSubject);

        // Build YANG tree.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtContext ydtContext = yangTreeBuilder
                .getYdtForNotification(event, "rootNotif",
                                       yangSchemaRegistryTest);

        // Gets the first module from logical root node.
        YdtContext moduleNode = ydtContext.getFirstChild();
        String moduleNodeName = moduleNode.getName();
        YdtContextOperationType operationType =
                ((YdtNode) moduleNode).getYdtContextOperationType();

        assertThat(operationType, is(YdtContextOperationType.NONE));
        assertThat(moduleNodeName, is("YtbMultiNotificationWithContainer"));

        // Gets the first container from module
        // YtbMultiNotificationWithContainer.
        YdtContext ydtContextForNotification = moduleNode.getFirstChild();
        String notificationName = ydtContextForNotification.getName();
        YdtContextOperationType operationTypeForNotification =
                ((YdtNode) ydtContextForNotification)
                        .getYdtContextOperationType();

        // Checks the contents in the first notification.
        assertThat(
                operationTypeForNotification,
                is(YdtContextOperationType.NONE));
        assertThat(notificationName, is("fortesta"));

        // Gets the contents of the first container in notification
        YdtContext ydtContextForContainer =
                ydtContextForNotification.getFirstChild();
        String containerName = ydtContextForContainer.getName();

        assertThat(containerName, is("ytbnot"));

        // Evaluates the leaf values.
        YdtContext ydtLeafInContainer = ydtContextForContainer.getFirstChild();
        String leafName = ydtLeafInContainer.getName();

        assertThat(leafName, is("notileaf"));

        String value = ydtLeafInContainer.getValue();
        // TODO: check the bits to string is okay.
        assertThat(value, is("{5, 7}"));
    }

    /**
     * Processes multi module with list in both the modules and checks the
     * YANG data tree building.
     */
    @Test
    public void processMultiModule() {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry yangSchemaRegistryTest =
                testYangSchemaNodeProvider
                        .getDefaultYangSchemaRegistry();

        // As an application fill two modules with list and leaf-list
        // respectively.
        List<BigInteger> bigIntegerList = new ArrayList<>();
        bigIntegerList.add(BigInteger.valueOf(1));
        bigIntegerList.add(BigInteger.valueOf(2));
        bigIntegerList.add(BigInteger.valueOf(3));
        List<BigInteger> bigIntegerList1 = new ArrayList<>();
        bigIntegerList1.add(BigInteger.valueOf(4));
        bigIntegerList1.add(BigInteger.valueOf(5));
        bigIntegerList1.add(BigInteger.valueOf(6));
        Ytbmultilist multiLista = new DefaultYtbmultilist.YtbmultilistBuilder()
                .check(bigIntegerList).build();
        Ytbmultilist multiLista1 = new DefaultYtbmultilist.YtbmultilistBuilder()
                .check(bigIntegerList1).build();
        List<Ytbmultilist> ytbmultilists = new ArrayList<>();
        ytbmultilists.add(multiLista);
        ytbmultilists.add(multiLista1);
        YtbMultiModulea modulea =
                new YtbMultiModuleaOpParam.YtbMultiModuleaBuilder()
                        .ytbmultilist(ytbmultilists)
                        .build();

        List<String> stringList = new ArrayList<>();
        stringList.add("ant");
        stringList.add("animal");
        stringList.add("bird");
        List<String> stringList1 = new ArrayList<>();
        stringList1.add("catch");
        stringList1.add("ball");
        stringList1.add("bat");
        Ytbmultilistb multiListb =
                new DefaultYtbmultilistb.YtbmultilistbBuilder()
                        .checkin(stringList).build();
        Ytbmultilistb multiListb1 =
                new DefaultYtbmultilistb.YtbmultilistbBuilder()
                        .checkin(stringList1).build();
        List<Ytbmultilistb> ytbMultiListb = new ArrayList<>();
        ytbMultiListb.add(multiListb);
        ytbMultiListb.add(multiListb1);
        ytbMultiListb.add(multiListb1);
        YtbMultiModuleb moduleb =
                new YtbMultiModulebOpParam.YtbMultiModulebBuilder()
                        .ytbmultilistb(ytbMultiListb)
                        .build();

        List<Object> listOfModules = new ArrayList<>();
        listOfModules.add(modulea);
        listOfModules.add(moduleb);

        // Build YANG tree.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtBuilder ydtBuilder =
                yangTreeBuilder.getYdtBuilderForYo(listOfModules, "multimodule",
                                                   "multimodulespace",
                                                   EDIT_CONFIG_REQUEST,
                                                   yangSchemaRegistryTest);

        // Receives YDT context and check the tree that is built.
        YdtContext ydtContext = ydtBuilder.getRootNode();

        YdtContext moduleaNode = ydtContext.getFirstChild();
        assertThat(moduleaNode.getName(), is("YtbMultiModulea"));

        YdtContext moduleAListA = moduleaNode.getFirstChild();
        assertThat(moduleAListA.getName(), is("ytbmultilist"));

        YdtContext moduleAListALeafList = moduleAListA.getFirstChild();
        assertThat(moduleAListALeafList.getName(), is("check"));

        Set<String> value1 = moduleAListALeafList.getValueSet();
        assertThat(value1.contains("1"), is(true));
        assertThat(value1.contains("2"), is(true));
        assertThat(value1.contains("3"), is(true));

        YdtContext moduleAListB = moduleAListA.getNextSibling();
        assertThat(moduleAListB.getName(), is("ytbmultilist"));

        YdtContext moduleAListBLeafList = moduleAListB.getFirstChild();
        assertThat(moduleAListBLeafList.getName(), is("check"));

        Set<String> value2 = moduleAListBLeafList.getValueSet();
        assertThat(value2.contains("4"), is(true));
        assertThat(value2.contains("5"), is(true));
        assertThat(value2.contains("6"), is(true));

        YdtContext modulebNode = moduleaNode.getNextSibling();
        assertThat(modulebNode.getName(), is("YtbMultiModuleb"));

        YdtContext moduleBListA = modulebNode.getFirstChild();
        assertThat(moduleBListA.getName(), is("ytbmultilistb"));

        YdtContext moduleBListALeafList = moduleBListA.getFirstChild();
        assertThat(moduleBListALeafList.getName(), is("checkin"));

        Set<String> value3 = moduleBListALeafList.getValueSet();
        assertThat(value3.contains("ant"), is(true));
        assertThat(value3.contains("animal"), is(true));
        assertThat(value3.contains("bird"), is(true));

        YdtContext moduleBListB = moduleBListA.getNextSibling();
        assertThat(moduleBListB.getName(), is("ytbmultilistb"));

        YdtContext moduleBListBLeafList = moduleBListB.getFirstChild();
        assertThat(moduleBListBLeafList.getName(), is("checkin"));

        Set<String> value4 = moduleBListBLeafList.getValueSet();
        assertThat(value4.contains("catch"), is(true));
        assertThat(value4.contains("ball"), is(true));
        assertThat(value4.contains("bat"), is(true));
    }

    /**
     * Processes tree building when a list node is having list inside it.
     */
    @Test
    public void processTreeBuilderForListHavingList() {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry yangSchemaRegistryTest =
                testYangSchemaNodeProvider
                        .getDefaultYangSchemaRegistry();

        //Children leaf-list-a and leaf-list-b having leaf-list contents.
        List<byte[]> byteArray = new ArrayList<>();
        byte[] arr = new byte[]{1, 6, 3};
        byte[] arr1 = new byte[]{2, 7, 4};
        byteArray.add(arr);
        byteArray.add(arr1);

        List<byte[]> byteArray1 = new ArrayList<>();
        byte[] arr2 = new byte[]{3, 8, 4};
        byte[] arr3 = new byte[]{5, 6, 1};
        byteArray1.add(arr2);
        byteArray1.add(arr3);

        // Child list having respective leaf-lists.
        ApplicationAreas applicationAreas1 =
                new DefaultApplicationAreas.ApplicationAreasBuilder()
                        .destinationAreas(byteArray).build();
        ApplicationAreas applicationAreas2 =
                new DefaultApplicationAreas.ApplicationAreasBuilder()
                        .destinationAreas(byteArray1).build();

        List<ApplicationAreas> applicationAreasList = new ArrayList<>();
        applicationAreasList.add(applicationAreas1);
        applicationAreasList.add(applicationAreas2);
        Multiplexes multiplex1 = new DefaultMultiplexes.MultiplexesBuilder()
                .types(TypesEnum.TIME_DIVISION)
                .applicationAreas(applicationAreasList).build();

        // Second set for parent list.
        List<byte[]> byteArrayB = new ArrayList<>();
        byte[] arrB = new byte[]{0, 0, 1};
        byte[] arr1B = new byte[]{1, 0, 0};
        byteArrayB.add(arrB);
        byteArrayB.add(arr1B);

        List<byte[]> byteArray1B = new ArrayList<>();
        byte[] arr2B = new byte[]{7, 7, 7};
        byte[] arr3B = new byte[]{0, 1};
        byteArray1B.add(arr2B);
        byteArray1B.add(arr3B);

        ApplicationAreas applicationAreas1B =
                new DefaultApplicationAreas.ApplicationAreasBuilder()
                        .destinationAreas(byteArrayB).build();
        ApplicationAreas applicationAreas2B =
                new DefaultApplicationAreas.ApplicationAreasBuilder()
                        .destinationAreas(byteArray1B).build();

        List<ApplicationAreas> applicationAreasListB = new ArrayList<>();
        applicationAreasListB.add(applicationAreas1B);
        applicationAreasListB.add(applicationAreas2B);
        Multiplexes multiplex1B = new DefaultMultiplexes.MultiplexesBuilder()
                .types(TypesEnum.FREQUENCY_DIVISION)
                .applicationAreas(applicationAreasListB).build();

        // Adds the child lists to the parent list.
        List<Multiplexes> multiplexList = new ArrayList<>();
        multiplexList.add(multiplex1);
        multiplexList.add(multiplex1B);

        // Sets it in the container.
        Carrier carrier =
                new DefaultCarrier.CarrierBuilder().multiplexes(multiplexList)
                        .build();

        YtbTreeBuilderForListHavingList treeBuilderForListHavingList = new
                YtbTreeBuilderForListHavingListOpParam
                        .YtbTreeBuilderForListHavingListBuilder()
                .carrier(carrier)
                .build();

        List<Object> objectList = new ArrayList<>();
        objectList.add(treeBuilderForListHavingList);
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtBuilder ydtBuilder = yangTreeBuilder
                .getYdtBuilderForYo(objectList, "RootNameOfListHavingList",
                                    "RootNameSpaceOfListHavingList",
                                    YmsOperationType.QUERY_CONFIG_REQUEST,
                                    yangSchemaRegistryTest);

        // Checks the module node and name under logical node.
        YdtContext ydtContext = ydtBuilder.getRootNode();
        YdtContext moduleNode = ydtContext.getFirstChild();
        assertThat(moduleNode.getName(), is("YtbTreeBuilderForListHavingList"));

        // Checks the container node and name under module node.
        YdtContext ydtContextForContainer = moduleNode.getFirstChild();
        assertThat(ydtContextForContainer.getName(), is("carrier"));

        // Checks the list node a and name under module node.
        YdtContext ydtContextForParentListA =
                ydtContextForContainer.getFirstChild();
        assertThat(ydtContextForParentListA.getName(), is("multiplexes"));

        YdtContext leafOfParentA = ydtContextForParentListA.getFirstChild();
        assertThat(leafOfParentA.getName(), is("types"));
        assertThat(leafOfParentA.getValue(), is("time-division"));

        YdtContext ydtContextForChildListAOfParentA = leafOfParentA
                .getNextSibling();
        assertThat(ydtContextForChildListAOfParentA.getName(),
                   is("application-areas"));

        YdtContext leaflistOfChildListAOfParentA =
                ydtContextForChildListAOfParentA.getFirstChild();
        assertThat(leaflistOfChildListAOfParentA.getName(),
                   is("destination-areas"));
        Set leaflistAValue = leaflistOfChildListAOfParentA.getValueSet();
        // TODO: check the leaf-list value.

        YdtContext ydtContextForChildListBOfParentA =
                ydtContextForChildListAOfParentA.getNextSibling();
        assertThat(ydtContextForChildListBOfParentA.getName(),
                   is("application-areas"));

        YdtContext leaflistOfChildListBOfParentA =
                ydtContextForChildListBOfParentA.getFirstChild();
        assertThat(leaflistOfChildListBOfParentA.getName(),
                   is("destination-areas"));
        Set leaflistBValue = leaflistOfChildListAOfParentA.getValueSet();
        // TODO: check the leaf-list value.

        YdtContext ydtContextForParentListB =
                ydtContextForParentListA.getNextSibling();
        assertThat(ydtContextForParentListB.getName(), is("multiplexes"));

        YdtContext leafOfParentB = ydtContextForParentListB.getFirstChild();
        assertThat(leafOfParentB.getName(), is("types"));
        assertThat(leafOfParentB.getValue(), is("frequency-division"));

        YdtContext ydtContextForChildListAOfParentB =
                leafOfParentB.getNextSibling();
        assertThat(ydtContextForChildListAOfParentB.getName(),
                   is("application-areas"));

        YdtContext leaflistOfChildListAOfParentB =
                ydtContextForChildListAOfParentB.getFirstChild();
        assertThat(leaflistOfChildListAOfParentB.getName(),
                   is("destination-areas"));
        Set leaflistCValue = leaflistOfChildListAOfParentB.getValueSet();
        // TODO: check the leaf-list value.

        YdtContext ydtContextForChildListBOfParentB =
                ydtContextForChildListAOfParentB.getNextSibling();
        assertThat(ydtContextForChildListBOfParentB.getName(),
                   is("application-areas"));

        YdtContext leaflistOfChildListBOfParentB =
                ydtContextForChildListBOfParentB.getFirstChild();
        assertThat(leaflistOfChildListBOfParentB.getName(),
                   is("destination-areas"));
        Set leaflistDValue = leaflistOfChildListBOfParentB.getValueSet();
        // TODO: check the leaf-list value.
    }

    /**
     * Processes tree building from the derived type of leaf and leaf-list
     * having binary and bits .
     */
    @Test
    public void processTreeBuilderForBinaryAndBits() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry yangSchemaRegistryTest =
                testYangSchemaNodeProvider
                        .getDefaultYangSchemaRegistry();

        byte[] arrValue = new byte[]{5, 5, 5};
        Derivedbinaryb derivedbinaryb = new Derivedbinaryb(arrValue);
        Derivedbinarya derivedbinarya = new Derivedbinarya(derivedbinaryb);

        // As an application create values.
        BitSet bitSetleaf = new BitSet();
        bitSetleaf.set(1);
        bitSetleaf.set(100);
        Derivedbitsb derivedbitsb = new Derivedbitsb(bitSetleaf);
        Derivedbitsa derivedbitsa = new Derivedbitsa(derivedbitsb);

        byte[] addValueToList1 = new byte[]{9, 9, 0};
        byte[] addValueToList2 = new byte[]{12, 6, 0};
        byte[] addValueToList3 = new byte[]{16, 29, 25};
        Derivedbinaryb derivedbinaryb1 = new Derivedbinaryb(addValueToList1);
        Derivedbinaryb derivedbinaryb2 = new Derivedbinaryb(addValueToList2);
        Derivedbinaryb derivedbinaryb3 = new Derivedbinaryb(addValueToList3);
        Derivedbinarya derivedbinarya1 = new Derivedbinarya(derivedbinaryb1);
        Derivedbinarya derivedbinarya2 = new Derivedbinarya(derivedbinaryb2);
        Derivedbinarya derivedbinarya3 = new Derivedbinarya(derivedbinaryb3);
        List<Derivedbinarya> derivedbinaryaList = new ArrayList<>();
        derivedbinaryaList.add(derivedbinarya1);
        derivedbinaryaList.add(derivedbinarya2);
        derivedbinaryaList.add(derivedbinarya3);

        BitSet bitSetLeafList1 = new BitSet();
        bitSetLeafList1.set(1);
        BitSet bitSetLeafList2 = new BitSet();
        bitSetLeafList2.set(1);
        bitSetLeafList2.set(10);
        BitSet bitSetLeafList3 = new BitSet();
        bitSetLeafList3.set(1);
        bitSetLeafList3.set(10);
        bitSetLeafList3.set(100);
        Derivedbitsb derivedbitsb1 = new Derivedbitsb(bitSetLeafList1);
        Derivedbitsb derivedbitsb2 = new Derivedbitsb(bitSetLeafList2);
        Derivedbitsb derivedbitsb3 = new Derivedbitsb(bitSetLeafList3);
        Derivedbitsa derivedbitsa1 = new Derivedbitsa(derivedbitsb1);
        Derivedbitsa derivedbitsa2 = new Derivedbitsa(derivedbitsb2);
        Derivedbitsa derivedbitsa3 = new Derivedbitsa(derivedbitsb3);
        List<Derivedbitsa> derivedbitsaList = new ArrayList<>();
        derivedbitsaList.add(derivedbitsa1);
        derivedbitsaList.add(derivedbitsa2);
        derivedbitsaList.add(derivedbitsa3);

        YtbDerivedTypeWithBitsAndBinary bitsAndBinary = new
                YtbDerivedTypeWithBitsAndBinaryOpParam
                        .YtbDerivedTypeWithBitsAndBinaryBuilder()
                .forbinary(derivedbinarya).forbits(derivedbitsa)
                .forbinarylist(derivedbinaryaList)
                .forbitslist(derivedbitsaList).build();

        // YSB or YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(bitsAndBinary);

        // Build YANG tree.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtExtendedBuilder ydtBuilder = yangTreeBuilder
                .getYdtBuilderForYo(objectList, "rootNameForBinary",
                                    "rootNamespaceForBinary",
                                    EDIT_CONFIG_REQUEST,
                                    yangSchemaRegistryTest);
        YdtContext rootContext = ydtBuilder.getRootNode();
        YdtContext moduleNode = rootContext.getFirstChild();
        assertThat(moduleNode.getName(), is("YtbDerivedTypeWithBitsAndBinary"));
        YdtContext leafInModule = moduleNode.getFirstChild();
        assertThat(leafInModule.getName(), is("forbinary"));

        String leafValueInModule = leafInModule.getValue();
        assertThat(leafValueInModule, is("BQUF"));

        YdtContext bitsLeafInModule = leafInModule.getNextSibling();
        assertThat(bitsLeafInModule.getName(), is("forbits"));

        String bitsLeafValueInModule = bitsLeafInModule.getValue();
        // TODO: The value of bits should be mapped text string.
        //assertThat(bitsLeafValueInModule, is("{1, 100}"));

        YdtContext binaryLeafListInModule = bitsLeafInModule.getNextSibling();
        assertThat(binaryLeafListInModule.getName(), is("forbinarylist"));
        Set valueOfBinaryLeafList = binaryLeafListInModule.getValueSet();
        assertThat(valueOfBinaryLeafList.contains("CQkA"), is(true));
        assertThat(valueOfBinaryLeafList.contains("DAYA"), is(true));
        assertThat(valueOfBinaryLeafList.contains("EB0Z"), is(true));

        // TODO: The value of bits should be mapped text string.
        YdtContext bitsLeafList = binaryLeafListInModule.getNextSibling();
        assertThat(bitsLeafList.getName(), is("forbitslist"));
        Set valueOfBitsLeafList = bitsLeafList.getValueSet();
        //assertThat(valueOfBitsLeafList.contains("{1, 10}"), is(true));
        //assertThat(valueOfBitsLeafList.contains("{1}"), is(true));
        //assertThat(valueOfBitsLeafList.contains("{1, 10, 100}"), is(true));
    }

    /**
     * Processes tree building for the union type.
     */
    @Test
    public void processYtbUnionType() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry yangSchemaRegistryTest =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();
        byte[] unionBinary = new byte[]{0, 0, 0};
        ForunionUnion union = new ForunionUnion(unionBinary);
        YtbDerivedTypeWithBitsAndBinary unionType = new
                YtbDerivedTypeWithBitsAndBinaryOpParam
                        .YtbDerivedTypeWithBitsAndBinaryBuilder()
                .forunion(union)
                .build();
        // YSB or YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(unionType);

        // Build YANG tree.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtExtendedBuilder ydtBuilder = yangTreeBuilder
                .getYdtBuilderForYo(objectList, "rootNameForBinary",
                                    "rootNamespaceForBinary",
                                    EDIT_CONFIG_REQUEST,
                                    yangSchemaRegistryTest);
        YdtContext rootNode = ydtBuilder.getRootNode();
        YdtContext moduleNode = rootNode.getFirstChild();
        YdtContext unionChild = moduleNode.getFirstChild();
        assertThat(unionChild.getName(), is("forunion"));
        //TODO: Correct it once union generated code is fixed.
        assertThat(unionChild.getValue(), is("0"));
    }
}
