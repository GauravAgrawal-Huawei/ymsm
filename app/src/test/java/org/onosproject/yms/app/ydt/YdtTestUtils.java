package org.onosproject.yms.app.ydt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.onosproject.yms.app.ysr.TestYangSchemaNodeProvider;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;
import org.onosproject.yms.ydt.YdtListener;
import org.onosproject.yms.ydt.YdtType;

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
    private static TestYangSchemaNodeProvider testYangSchemaNodeProvider = new TestYangSchemaNodeProvider();

    public static YangRequestWorkBench foodArenaYdt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.ydt.food.rev20160624.FoodService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "foodarena";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("food", "ydt.food", YdtContextOperationType.MERGE);
        defaultYdtBuilder.addChild("food", "ydt.food");
//        defaultYdtBuilder.addChild("snack", null, "ydt.food");
//        defaultYdtBuilder.addChild("latenight", null, "ydt.food");
        defaultYdtBuilder.addLeaf("chocolate", "ydt.food", "dark");
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    public static YangRequestWorkBench ietfNetwork1Ydt() {

        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang." +
                "ietf.network.rev20151208.IetfNetworkService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "ietf-network";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);
        defaultYdtBuilder.addChild("ietf-network", "urn:ietf:params:xml:ns:yang:ietf-network");

        // Adding container
        defaultYdtBuilder.addChild("networks", null);
        // Adding list inside container
        defaultYdtBuilder.addChild("network", null);
        // Adding key element network Id
        defaultYdtBuilder.addLeaf("network-id", null, "network1");
        defaultYdtBuilder.traverseToParent();

        // Adding list inside list
        defaultYdtBuilder.addChild("supporting-network", null);
        // Adding key element network-ref
        defaultYdtBuilder.addLeaf("network-ref", null, "network2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        // Adding list inside list
        defaultYdtBuilder.addChild("node", null);
        // Adding key element node-id
        defaultYdtBuilder.addLeaf("node-id", null, "node1");
        defaultYdtBuilder.traverseToParent();

        // Adding list inside list
        defaultYdtBuilder.addChild("supporting-node", null);
        // Adding key element network-ref
        defaultYdtBuilder.addLeaf("network-ref", null, "network3");
        defaultYdtBuilder.traverseToParent();

        // Adding key element node-ref
        defaultYdtBuilder.addLeaf("node-ref", null, "network4");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        // Adding container
        defaultYdtBuilder.addChild("networks-state", null);
        // Adding list inside container
        defaultYdtBuilder.addChild("network", null);
        // Adding key element network-ref
        defaultYdtBuilder.addLeaf("network-ref", null, "network5");
        defaultYdtBuilder.traverseToParent();
        // Adding leaf server-provided
        defaultYdtBuilder.addLeaf("server-provided", null, "true");
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    @SuppressWarnings({"unchecked", "MismatchedQueryAndUpdateOfCollection"})
    public static YangRequestWorkBench listWithContainerYdt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.RootlistService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "list";
        Set<String> valueSet = new HashSet();
        valueSet.add("1");
        valueSet.add("2");


        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider
                        .getDefaultYangSchemaRegistry(),
                true);
        defaultYdtBuilder.addChild("rootlist", "ydt.rootlist");
        keysValueList.clear();
        keysValueList.add("12");
        keysValueList.add("12");
        defaultYdtBuilder.addMultiInstanceChild("listwithcontainer", null, keysValueList);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalidinterval", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalidinterval", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("interface", null);
        defaultYdtBuilder.addLeaf("invalidinterval", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid", null, "121");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }


    @SuppressWarnings({"unchecked", "MismatchedQueryAndUpdateOfCollection"})
    public static YangRequestWorkBench listWithContainer1Ydt() {
        String appName = "org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.RootlistService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "list";
        Set<String> valueSet = new HashSet();
        valueSet.add("1");
        valueSet.add("2");

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider
                        .getDefaultYangSchemaRegistry(),
                true);
        defaultYdtBuilder.addChild("rootlist", "ydt.rootlist");
        defaultYdtBuilder.addChild("listwithcontainer", null);
        defaultYdtBuilder.addLeaf("invalid", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid1", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalidinterval", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalidinterval", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("interface", null);
        defaultYdtBuilder.addLeaf("invalidinterval", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalid", null, "121");
        defaultYdtBuilder.traverseToParent();

        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    @SuppressWarnings({"unchecked", "MismatchedQueryAndUpdateOfCollection"})
    public static YangRequestWorkBench listWithContainer2Ydt() {
        String appName = "org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.RootlistService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "list";
        Set<String> valueSet = new HashSet();
        valueSet.add("1");
        valueSet.add("2");
        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider
                        .getDefaultYangSchemaRegistry(),
                true);

        defaultYdtBuilder.addChild("rootlist", "ydt.rootlist");
        keysValueList.clear();
        keysValueList.add("1222");
        keysValueList.add("1212");
        defaultYdtBuilder.addMultiInstanceChild("listwithcontainer", null, keysValueList);
        defaultYdtBuilder.traverseToParent();

        keysValueList.clear();
        keysValueList.add("12");
        keysValueList.add("1");
        defaultYdtBuilder.addMultiInstanceChild("invalidinterval", null, keysValueList);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalidinterval", null, "122");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("invalidinterval", null, "2222");
        defaultYdtBuilder.traverseToParent();

        keysValueList.clear();
        keysValueList.add("1222");
        keysValueList.add("1212");
        defaultYdtBuilder.addMultiInstanceChild("invalidinterval", null, keysValueList);
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("interface", null);
        defaultYdtBuilder.addLeaf("invalidinterval", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    public static YangRequestWorkBench listWithoutContainerYdt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.ydt.rootlist.rev20160624.RootlistService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "list";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("rootlist", "ydt.rootlist");
        defaultYdtBuilder.addChild("listwithoutcontainer", null);
        defaultYdtBuilder.addLeaf("invalidinterval", null, "12");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    @SuppressWarnings("unchecked")
    public static YangRequestWorkBench logisticsManagerYdt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "logisticsmanager";
        Set<String> valueSet = new HashSet();
        valueSet.add("1");
        valueSet.add("2");
        valueSet.add("3");
        valueSet.add("4");
        valueSet.add("5");

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("customssupervisor", "ydt.customs-supervisor", YdtContextOperationType.MERGE);
        defaultYdtBuilder.addLeaf("supervisor", "ydt.customs-supervisor", "abc");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("merchandisersupervisor", "ydt.Merchandiser-supervisor",
                YdtContextOperationType.MERGE);
        defaultYdtBuilder.addLeaf("supervisor", "ydt.Merchandiser-supervisor", "abc");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("materialsupervisor", "ydt.material-supervisor", YdtContextOperationType.MERGE);
        defaultYdtBuilder.addChild("supervisor", "ydt.material-supervisor");
        defaultYdtBuilder.addLeaf("name", "ydt.material-supervisor", "abc");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("departmentId", "ydt.material-supervisor", "xyz");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("supervisor", "ydt.material-supervisor");
        defaultYdtBuilder.addLeaf("name", "ydt.material-supervisor", "ab");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("departmentId", "ydt.material-supervisor", "xy");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("purchasingsupervisor", "ydt.purchasing-supervisor", YdtContextOperationType.MERGE);
        defaultYdtBuilder.addChild("supervisor", "ydt.purchasing-supervisor");
        defaultYdtBuilder.addLeaf("purchasing-specialist", "ydt.purchasing-supervisor", "abc");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("support", "ydt.purchasing-supervisor", "xyz");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("warehousesupervisor", "ydt.warehouse-supervisor", YdtContextOperationType.MERGE);
        defaultYdtBuilder.addLeaf("supervisor", "ydt.warehouse-supervisor", valueSet);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("tradingsupervisor", "ydt.trading-supervisor", YdtContextOperationType.MERGE);
        defaultYdtBuilder.addLeaf("supervisor", "ydt.trading-supervisor", "abc");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("employeeid", "ydt.employee-id", YdtContextOperationType.MERGE);
        defaultYdtBuilder.addLeaf("employeeid", "ydt.employee-id", valueSet);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        testYangSchemaNodeProvider.unregisterService("org.onosproject.yang.gen.v1.ydt.customs." +
                "supervisor.rev20160524.CustomssupervisorService");
        testYangSchemaNodeProvider.unregisterService("org.onosproject.yang.gen.v1.ydt.merchandiser." +
                "supervisor.rev20160524.MerchandisersupervisorService");
        testYangSchemaNodeProvider.unregisterService("org.onosproject.yang.gen.v1.ydt.material." +
                "supervisor.rev20160524.MaterialsupervisorService");
        testYangSchemaNodeProvider.unregisterService("org.onosproject.yang.gen.v1.ydt.purchasing." +
                "supervisor.rev20160524.PurchasingsupervisorService");
        testYangSchemaNodeProvider.unregisterService("org.onosproject.yang.gen.v1.ydt.warehouse." +
                "supervisor.rev20160524.WarehousesupervisorService");
        testYangSchemaNodeProvider.unregisterService("org.onosproject.yang.gen.v1.ydt.trading." +
                "supervisor.rev20160524.TradingsupervisorService");
        testYangSchemaNodeProvider.unregisterService("org.onosproject.yang.gen.v1.ydt.employee.id." +
                "rev20160524.EmployeeidService");

        return defaultYdtBuilder;
    }

    public static YangRequestWorkBench bitYdt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.ydt.binarytest.rev20160524.BinarytestService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "builtInType";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("bit", "ydt.bit");
        defaultYdtBuilder.addChild("bitList", null);
        defaultYdtBuilder.addLeaf("bit", null, "disable-nagle");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("bitList", null);
        defaultYdtBuilder.addLeaf("bit", null, "auto-sense-speed");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("bitList", null);
        defaultYdtBuilder.addLeaf("bit", null, "ten-Mb-only");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("bitList", null);
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    public static YangRequestWorkBench booleanYdt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());

        String appName = "org.onosproject.yang.gen.v1.ydt.yangautoprefixboolean.rev20160524.BoolService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "builtInType";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("bool", "ydt.boolean");
        defaultYdtBuilder.addChild("booleanList", null);
        defaultYdtBuilder.addLeaf("boolean", null, "true");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("booleanList", null);
        defaultYdtBuilder.addLeaf("boolean", null, "false");
        defaultYdtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }


    public static YangRequestWorkBench emptyTypeYdt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.ydt.emptydata.rev20160524.EmptydataService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "builtInType";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("emptydata", "ydt.emptydata");
        defaultYdtBuilder.addChild("emptyList", null);
        defaultYdtBuilder.addLeaf("empty", null, "");
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    public static YangRequestWorkBench enumYdt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.ydt.enumtest.rev20160524.EnumtestService";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "builtInType";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("enumtest", "ydt.enumtest");
        defaultYdtBuilder.addChild("enumList", null);
        defaultYdtBuilder.addLeaf("enumleaf", null, "ten");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("enumList", null);
        defaultYdtBuilder.addLeaf("enumleaf", null, "hundred");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("enumList", null);
        defaultYdtBuilder.addLeaf("enumleaf", null, "thousand");
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    public static YangRequestWorkBench integer8Ydt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.ydt.integer8.rev20160524.Integer8Service";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "builtInType";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("integer8", "ydt.integer8");
        defaultYdtBuilder.addLeaf("negInt", null, "-128");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("posInt", null, "127");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("minUInt", null, "0");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxUInt", null, "255");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("midIntWithRange", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("minIntWithRange", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxIntWithRange", null, "100");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("midUIntWithRange", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("minUIntWithRange", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxUIntWithRange", null, "100");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "40");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "50");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "55");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("UnInteger", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "40");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "50");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "55");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("revInteger", null, "-128");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "20");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "127");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "0");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "20");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "255");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    public static YangRequestWorkBench integer16Ydt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.ydt.integer16.rev20160524.Integer16Service";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "builtInType";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("integer16", "ydt.integer16");
        defaultYdtBuilder.addLeaf("negInt", null, "-32768");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("posInt", null, "32767");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("minUInt", null, "0");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxUInt", null, "65535");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("midIntWithRange", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("minIntWithRange", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxIntWithRange", null, "100");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("midUIntWithRange", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("minUIntWithRange", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxUIntWithRange", null, "100");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "40");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "50");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "55");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("UnInteger", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "40");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "50");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "55");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("revInteger", null, "-32768");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "20");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "32767");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "0");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "20");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "65535");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    public static YangRequestWorkBench integer32Ydt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.ydt.integer32.rev20160524.Integer32Service";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "builtInType";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("integer32", "ydt.integer32");
        defaultYdtBuilder.addLeaf("negInt", null, "-2147483648");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("posInt", null, "2147483647");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("minUInt", null, "0");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxUInt", null, "4294967295");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("midIntWithRange", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("minIntWithRange", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxIntWithRange", null, "100");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("midUIntWithRange", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("minUIntWithRange", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxUIntWithRange", null, "100");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "40");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "50");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "55");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("UnInteger", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "40");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "50");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "55");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("revInteger", null, "-2147483648");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "20");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "2147483647");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "0");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "20");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "4294967295");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    public static YangRequestWorkBench integer64Ydt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.ydt.integer64.rev20160524.Integer64Service";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "builtInType";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("integer64", "ydt.integer64");
        defaultYdtBuilder.addLeaf("negInt", null, "-9223372036854775808");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("posInt", null, "9223372036854775807");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("minUInt", null, "0");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxUInt", null, "18446744073709551615");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("midIntWithRange", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("minIntWithRange", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxIntWithRange", null, "100");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addLeaf("midUIntWithRange", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("minUIntWithRange", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxUIntWithRange", null, "100");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "40");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "50");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "55");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("integer", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("UnInteger", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "40");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "50");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "55");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("UnInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("revInteger", null, "-9223372036854775808");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "20");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revInteger", null, "9223372036854775807");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "0");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "1");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "2");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "20");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null, YdtType.MULTI_INSTANCE_NODE);
        defaultYdtBuilder.addLeaf("revUnInteger", null, "18446744073709551615");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    public static YangRequestWorkBench decimal64Ydt() {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        String appName = "org.onosproject.yang.gen.v1.ydt.decimal64.rev20160524.Decimal64Service";
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangRequestWorkBench defaultYdtBuilder;
        String rootName = "builtInType";

        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);

        defaultYdtBuilder.addChild("decimal64", "ydt.decimal64");
        defaultYdtBuilder.addLeaf("negInt", null, "-92233720368547758.08");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("posInt", null, "92233720368547758.07");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("negIntWithMinFraction", null, "-922337203685477580.8");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("posIntWithMinFraction", null, "922337203685477580.7");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("negIntWithMaxFraction", null, "-9.223372036854775808");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("posIntWithMaxFraction", null, "9.223372036854775807");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("midIntWithRange", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("minIntWithRange", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addLeaf("maxIntWithRange", null, "100");
        defaultYdtBuilder.traverseToParent();

        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("decimal", null, "11");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("decimal", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("decimal", null, "40");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("decimal", null, "50");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("decimal", null, "55");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("decimal", null, "100");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("revDecimal", null, "-92233720368547758.08");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("revDecimal", null, "2.505");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("revDecimal", null, "3.14");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("revDecimal", null, "10");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("revDecimal", null, "20");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("revDecimal", null, "92233720368547757");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.addChild("multiRangeValidation", null);
        defaultYdtBuilder.addLeaf("revDecimal", null, "92233720368547758.07");
        defaultYdtBuilder.traverseToParent();
        defaultYdtBuilder.traverseToParent();
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    public static YangRequestWorkBench getDefaultYdtBuilder(String rootName,
                                                            String moduleName,
                                                            String nameSpace,
                                                            String appName) {
        setSchemaRegistry(testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());
        YangRequestWorkBench defaultYdtBuilder;
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        defaultYdtBuilder = new YangRequestWorkBench(rootName, null, null,
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry(), true);
        defaultYdtBuilder.addChild(moduleName, nameSpace);
        testYangSchemaNodeProvider.unregisterService(appName);
        return defaultYdtBuilder;
    }

    // Logger list is used for walker testing.
    private static final List<String> LOGGER = new ArrayList<>();

    public static void compareValueSet(Set<String> valueSet, Set<String> userInputValueSet) {
        // Check the value against user input.
        for (Object aValueSet : valueSet) {
            assertThat(true, is((userInputValueSet.contains(aValueSet))));
        }
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
