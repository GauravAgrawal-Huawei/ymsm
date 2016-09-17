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

import org.onosproject.yms.app.ysr.TestYangSchemaNodeProvider;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;
import org.onosproject.yms.ydt.YdtListener;
import org.onosproject.yms.ydt.YdtType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class YdtTestUtils
        implements YdtListener {

    private static List<String> keysValueList = new ArrayList<>();

    public static YangSchemaRegistry getSchemaRegistry() {
        return schemaRegistry;
    }

    public static void setSchemaRegistry(YangSchemaRegistry registry) {
        schemaRegistry = registry;
    }

    private static YangSchemaRegistry schemaRegistry;
    private static TestYangSchemaNodeProvider testYangSchemaNodeProvider =
            new TestYangSchemaNodeProvider();

    public static YangRequestWorkBench foodArenaYdt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.food.rev20160624.FoodService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "foodarena";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder
                .addChild("food", "ydt.food", YdtContextOperationType.MERGE);
        ydtBuilder.addChild("food", "ydt.food");
//        ydtBuilder.addChild("snack", null, "ydt.food");
//        ydtBuilder.addChild("latenight", null, "ydt.food");
        ydtBuilder.addLeaf("chocolate", "ydt.food", "dark");
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }

    public static YangRequestWorkBench ietfNetwork1Ydt() {

        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang." +
                        "ietf.network.rev20151208.YmsIetfNetworkService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "yms-ietf-network";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(),
                true);
        ydtBuilder.addChild("yms-ietf-network",
                            "urn:ietf:params:xml:ns:yang:ietf-network");

        // Adding container
        ydtBuilder.addChild("networks", null);
        // Adding list inside container
        ydtBuilder.addChild("network", null);
        // Adding key element network Id
        ydtBuilder.addLeaf("network-id", null, "network1");
        ydtBuilder.traverseToParent();

        // Adding list inside list
        ydtBuilder.addChild("supporting-network", null);
        // Adding key element network-ref
        ydtBuilder.addLeaf("network-ref", null, "network2");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        // Adding list inside list
        ydtBuilder.addChild("node", null);
        // Adding key element node-id
        ydtBuilder.addLeaf("node-id", null, "node1");
        ydtBuilder.traverseToParent();

        // Adding list inside list
        ydtBuilder.addChild("supporting-node", null);
        // Adding key element network-ref
        ydtBuilder.addLeaf("network-ref", null, "network3");
        ydtBuilder.traverseToParent();

        // Adding key element node-ref
        ydtBuilder.addLeaf("node-ref", null, "network4");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        // Adding container
        ydtBuilder.addChild("networks-state", null);
        // Adding list inside container
        ydtBuilder.addChild("network", null);
        // Adding key element network-ref
        ydtBuilder.addLeaf("network-ref", null, "network5");
        ydtBuilder.traverseToParent();
        // Adding leaf server-provided
        ydtBuilder.addLeaf("server-provided", null, "true");
        testYangSchemaNodeProvider.unregisterService(appName);
        return ydtBuilder;
    }

    @SuppressWarnings({"unchecked", "MismatchedQueryAndUpdateOfCollection"})
    public static YangRequestWorkBench listWithContainerYdt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.rootlist" +
                        ".rev20160624.RootlistService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "list";
        Set<String> valueSet = new HashSet();
        valueSet.add("1");
        valueSet.add("2");

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);
        ydtBuilder.addChild("rootlist", "ydt.rootlist");
        keysValueList.clear();
        keysValueList.add("12");
        keysValueList.add("12");
        ydtBuilder.addMultiInstanceChild("listwithcontainer", null,
                                         keysValueList);
        ydtBuilder.addLeaf("invalidinterval", null, "1");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("invalidinterval", null, "2");
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("interface", null);
        ydtBuilder.addLeaf("invalidinterval", null, "12");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("invalid", null, "121");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        testYangSchemaNodeProvider.unregisterService(appName);
        return ydtBuilder;
    }


    @SuppressWarnings({"unchecked", "MismatchedQueryAndUpdateOfCollection"})
    public static YangRequestWorkBench listWithContainer1Ydt() {
        String appName =
                "org.onosproject.yang.gen.v1.ydt.rootlist" +
                        ".rev20160624.RootlistService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "list";
        Set<String> valueSet = new HashSet();
        valueSet.add("1");
        valueSet.add("2");

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);
        ydtBuilder.addChild("rootlist", "ydt.rootlist");
        ydtBuilder.addChild("listwithcontainer", null);
        ydtBuilder.addLeaf("invalid", null, "12");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("invalid1", null, "12");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("invalidinterval", null, "1");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("invalidinterval", null, "2");
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("interface", null);
        ydtBuilder.addLeaf("invalidinterval", null, "12");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("invalid", null, "121");
        ydtBuilder.traverseToParent();

        testYangSchemaNodeProvider.unregisterService(appName);
        return ydtBuilder;
    }

    @SuppressWarnings({"unchecked", "MismatchedQueryAndUpdateOfCollection"})
    public static YangRequestWorkBench listWithContainer2Ydt() {
        String appName =
                "org.onosproject.yang.gen.v1.ydt.rootlist" +
                        ".rev20160624.RootlistService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "list";
        Set<String> valueSet = new HashSet();
        valueSet.add("1");
        valueSet.add("2");
        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("rootlist", "ydt.rootlist");
        keysValueList.clear();
        keysValueList.add("1222");
        keysValueList.add("1212");
        ydtBuilder.addMultiInstanceChild("listwithcontainer", null,
                                         keysValueList);

        keysValueList.clear();
        keysValueList.add("12");
        keysValueList.add("1");
        ydtBuilder
                .addMultiInstanceChild("invalidinterval", null, keysValueList);
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("invalidinterval", null, "122");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("invalidinterval", null, "2222");
        ydtBuilder.traverseToParent();

        keysValueList.clear();
        keysValueList.add("1222");
        keysValueList.add("1212");
        ydtBuilder
                .addMultiInstanceChild("invalidinterval", null, keysValueList);
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("interface", null);
        ydtBuilder.addLeaf("invalidinterval", null, "12");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        testYangSchemaNodeProvider.unregisterService(appName);
        return ydtBuilder;
    }

    public static YangRequestWorkBench listWithoutContainerYdt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.rootlist" +
                        ".rev20160624.RootlistService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "list";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("rootlist", "ydt.rootlist");
        ydtBuilder.addChild("listwithoutcontainer", null);
        ydtBuilder.addLeaf("invalidinterval", null, "12");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);
        return ydtBuilder;
    }

    @SuppressWarnings("unchecked")
    public static YangRequestWorkBench logisticsManagerYdt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "logisticsmanager";
        Set<String> valueSet = new HashSet();
        valueSet.add("1");
        valueSet.add("2");
        valueSet.add("3");
        valueSet.add("4");
        valueSet.add("5");

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder
                .addChild("customssupervisor", "ydt.customs-supervisor",
                          YdtContextOperationType.MERGE);
        ydtBuilder
                .addLeaf("supervisor", "ydt.customs-supervisor", "abc");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("merchandisersupervisor",
                            "ydt.Merchandiser-supervisor",
                            YdtContextOperationType.MERGE);
        ydtBuilder
                .addLeaf("supervisor", "ydt.Merchandiser-supervisor", "abc");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder
                .addChild("materialsupervisor", "ydt.material-supervisor",
                          YdtContextOperationType.MERGE);
        ydtBuilder.addChild("supervisor", "ydt.material-supervisor");
        ydtBuilder.addLeaf("name", "ydt.material-supervisor", "abc");
        ydtBuilder.traverseToParent();
        ydtBuilder
                .addLeaf("departmentId", "ydt.material-supervisor", "xyz");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("supervisor", "ydt.material-supervisor");
        ydtBuilder.addLeaf("name", "ydt.material-supervisor", "ab");
        ydtBuilder.traverseToParent();
        ydtBuilder
                .addLeaf("departmentId", "ydt.material-supervisor", "xy");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.traverseToParent();

        ydtBuilder
                .addChild("purchasingsupervisor", "ydt.purchasing-supervisor",
                          YdtContextOperationType.MERGE);
        ydtBuilder.addChild("supervisor", "ydt.purchasing-supervisor");
        ydtBuilder
                .addLeaf("purchasing-specialist", "ydt.purchasing-supervisor",
                         "abc");
        ydtBuilder.traverseToParent();
        ydtBuilder
                .addLeaf("support", "ydt.purchasing-supervisor", "xyz");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.traverseToParent();

        ydtBuilder
                .addChild("warehousesupervisor", "ydt.warehouse-supervisor",
                          YdtContextOperationType.MERGE);
        ydtBuilder
                .addLeaf("supervisor", "ydt.warehouse-supervisor", valueSet);
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder
                .addChild("tradingsupervisor", "ydt.trading-supervisor",
                          YdtContextOperationType.MERGE);
        ydtBuilder
                .addLeaf("supervisor", "ydt.trading-supervisor", "abc");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("employeeid", "ydt.employee-id",
                            YdtContextOperationType.MERGE);
        ydtBuilder.addLeaf("employeeid", "ydt.employee-id", valueSet);
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        testYangSchemaNodeProvider
                .unregisterService("org.onosproject.yang.gen.v1.ydt.customs." +
                                           "supervisor.rev20160524" +
                                           ".CustomssupervisorService");
        testYangSchemaNodeProvider.unregisterService(
                "org.onosproject.yang.gen.v1.ydt.merchandiser." +
                        "supervisor.rev20160524.MerchandisersupervisorService");
        testYangSchemaNodeProvider
                .unregisterService("org.onosproject.yang.gen.v1.ydt.material." +
                                           "supervisor.rev20160524" +
                                           ".MaterialsupervisorService");
        testYangSchemaNodeProvider.unregisterService(
                "org.onosproject.yang.gen.v1.ydt.purchasing." +
                        "supervisor.rev20160524.PurchasingsupervisorService");
        testYangSchemaNodeProvider.unregisterService(
                "org.onosproject.yang.gen.v1.ydt.warehouse." +
                        "supervisor.rev20160524.WarehousesupervisorService");
        testYangSchemaNodeProvider
                .unregisterService("org.onosproject.yang.gen.v1.ydt.trading." +
                                           "supervisor.rev20160524" +
                                           ".TradingsupervisorService");
        testYangSchemaNodeProvider.unregisterService(
                "org.onosproject.yang.gen.v1.ydt.employee.id." +
                        "rev20160524.EmployeeidService");

        return ydtBuilder;
    }

    public static YangRequestWorkBench bitYdt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.binarytest.rev20160524" +
                        ".BinarytestService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "builtInType";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("bit", "ydt.bit");
        ydtBuilder.addChild("bitList", null);
        ydtBuilder.addLeaf("bit", null, "disable-nagle");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("bitList", null);
        ydtBuilder.addLeaf("bit", null, "auto-sense-speed");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("bitList", null);
        ydtBuilder.addLeaf("bit", null, "ten-Mb-only");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("bitList", null);
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }

    public static YangRequestWorkBench booleanYdt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());

        String appName =
                "org.onosproject.yang.gen.v1.ydt.yangautoprefixboolean" +
                        ".rev20160524.BoolService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "builtInType";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("bool", "ydt.boolean");
        ydtBuilder.addChild("booleanList", null);
        ydtBuilder.addLeaf("boolean", null, "true");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("booleanList", null);
        ydtBuilder.addLeaf("boolean", null, "false");
        ydtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }


    public static YangRequestWorkBench emptyTypeYdt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.emptydata" +
                        ".rev20160524.EmptydataService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "builtInType";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("emptydata", "ydt.emptydata");
        ydtBuilder.addChild("emptyList", null);
        ydtBuilder.addLeaf("empty", null, "");
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }

    public static YangRequestWorkBench enumYdt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.enumtest" +
                        ".rev20160524.EnumtestService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "builtInType";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("enumtest", "ydt.enumtest");
        ydtBuilder.addChild("enumList", null);
        ydtBuilder.addLeaf("enumleaf", null, "ten");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("enumList", null);
        ydtBuilder.addLeaf("enumleaf", null, "hundred");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("enumList", null);
        ydtBuilder.addLeaf("enumleaf", null, "thousand");
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }

    public static YangRequestWorkBench integer8Ydt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.integer8" +
                        ".rev20160524.Integer8Service";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "builtInType";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("integer8", "ydt.integer8");
        ydtBuilder.addLeaf("negInt", null, "-128");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("posInt", null, "127");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("minUInt", null, "0");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxUInt", null, "255");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("midIntWithRange", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("minIntWithRange", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxIntWithRange", null, "100");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("midUIntWithRange", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("minUIntWithRange", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxUIntWithRange", null, "100");
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "40");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "50");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "55");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("UnInteger", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "40");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "50");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "55");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("revInteger", null, "-128");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "1");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "2");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "20");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "127");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "0");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "1");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "2");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "20");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "255");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }

    public static YangRequestWorkBench integer16Ydt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.integer16" +
                        ".rev20160524.Integer16Service";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "builtInType";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("integer16", "ydt.integer16");
        ydtBuilder.addLeaf("negInt", null, "-32768");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("posInt", null, "32767");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("minUInt", null, "0");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxUInt", null, "65535");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("midIntWithRange", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("minIntWithRange", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxIntWithRange", null, "100");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("midUIntWithRange", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("minUIntWithRange", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxUIntWithRange", null, "100");
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "40");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "50");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "55");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("UnInteger", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "40");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "50");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "55");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("revInteger", null, "-32768");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "1");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "2");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "20");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "32767");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "0");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "1");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "2");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "20");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "65535");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }

    public static YangRequestWorkBench integer32Ydt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.integer32" +
                        ".rev20160524.Integer32Service";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "builtInType";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("integer32", "ydt.integer32");
        ydtBuilder.addLeaf("negInt", null, "-2147483648");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("posInt", null, "2147483647");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("minUInt", null, "0");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxUInt", null, "4294967295");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("midIntWithRange", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("minIntWithRange", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxIntWithRange", null, "100");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("midUIntWithRange", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("minUIntWithRange", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxUIntWithRange", null, "100");
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "40");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "50");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "55");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("UnInteger", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "40");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "50");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "55");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("revInteger", null, "-2147483648");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "1");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "2");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "20");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "2147483647");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "0");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "1");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "2");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "20");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "4294967295");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }

    public static YangRequestWorkBench integer64Ydt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.integer64" +
                        ".rev20160524.Integer64Service";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "builtInType";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("integer64", "ydt.integer64");
        ydtBuilder.addLeaf("negInt", null, "-9223372036854775808");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("posInt", null, "9223372036854775807");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("minUInt", null, "0");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxUInt", null, "18446744073709551615");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("midIntWithRange", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("minIntWithRange", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxIntWithRange", null, "100");
        ydtBuilder.traverseToParent();

        ydtBuilder.addLeaf("midUIntWithRange", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("minUIntWithRange", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxUIntWithRange", null, "100");
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "40");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "50");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "55");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("integer", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("UnInteger", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "40");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "50");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "55");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("UnInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("revInteger", null, "-9223372036854775808");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "1");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "2");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "20");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revInteger", null, "9223372036854775807");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "0");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "1");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "2");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "20");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null,
                            YdtType.MULTI_INSTANCE_NODE);
        ydtBuilder.addLeaf("revUnInteger", null, "18446744073709551615");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }

    public static YangRequestWorkBench decimal64Ydt() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.decimal64" +
                        ".rev20160524.Decimal64Service";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "builtInType";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("decimal64", "ydt.decimal64");
        ydtBuilder.addLeaf("negInt", null, "-92233720368547758.08");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("posInt", null, "92233720368547758.07");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("negIntWithMinFraction", null,
                           "-922337203685477580.8");
        ydtBuilder.traverseToParent();
        ydtBuilder
                .addLeaf("posIntWithMinFraction", null, "922337203685477580.7");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("negIntWithMaxFraction", null,
                           "-9.223372036854775808");
        ydtBuilder.traverseToParent();
        ydtBuilder
                .addLeaf("posIntWithMaxFraction", null, "9.223372036854775807");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("midIntWithRange", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("minIntWithRange", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("maxIntWithRange", null, "100");
        ydtBuilder.traverseToParent();

        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("decimal", null, "11");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("decimal", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("decimal", null, "40");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("decimal", null, "50");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("decimal", null, "55");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("decimal", null, "100");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("revDecimal", null, "-92233720368547758.08");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("revDecimal", null, "2.505");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("revDecimal", null, "3.14");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("revDecimal", null, "10");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("revDecimal", null, "20");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("revDecimal", null, "92233720368547757");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.addChild("multiRangeValidation", null);
        ydtBuilder.addLeaf("revDecimal", null, "92233720368547758.07");
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }

    public static YangRequestWorkBench getydtBuilder(String rootName,
                                                     String moduleName,
                                                     String nameSpace,
                                                     String appName) {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        YangRequestWorkBench ydtBuilder;
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);
        ydtBuilder.addChild(moduleName, nameSpace);
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }

    // Logger list is used for walker testing.
    private static final List<String> LOGGER = new ArrayList<>();

    public static void compareValueSet(Set<String> valueSet,
                                       Set<String> userInputValueSet) {
        // Check the value against user input.
        for (Object aValueSet : valueSet) {
            assertThat(true, is((userInputValueSet.contains(aValueSet))));
        }
    }


    public static YangRequestWorkBench helloOnos() {
        setSchemaRegistry(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName =
                "org.onosproject.yang.gen.v1.ydt.hello.onos" +
                        ".rev20160903.HelloOnosService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench ydtBuilder;
        String rootName = "Hello-ONOS";

        ydtBuilder = new YangRequestWorkBench(
                rootName, null, null, testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry(), true);

        ydtBuilder.addChild("Hello_ONOS", "ydt:hello_onos");
        ydtBuilder.addChild("hello-world", null);
        ydtBuilder.addChild("input", null);
        ydtBuilder.addLeaf("name", null, "onos");
        ydtBuilder.traverseToParent();
        ydtBuilder.addLeaf("surName", null, "yang");
        ydtBuilder.traverseToParent();

        keysValueList.clear();
        keysValueList.add("ON");
        keysValueList.add("LAB");
        ydtBuilder
                .addMultiInstanceChild("stringList", null, keysValueList);
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        ydtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);

        return ydtBuilder;
    }

    @Override
    public void enterYdtNode(YdtContext ydtContext) {
        LOGGER.add("Entry Node is " + ydtContext.getName() + ".");
    }

    @Override
    public void exitYdtNode(YdtContext ydtContext) {
        LOGGER.add("Exit Node is " + ydtContext.getName() + ".");
    }

    /**
     * Returns the LOGGER with log for testing the YDT walker.
     *
     * @return list of logs
     */
    public static List<String> getLogger() {
        return LOGGER;
    }

    /**
     * Clear the LOGGER array.
     */
    public static void resetLogger() {
        LOGGER.clear();
    }
}
