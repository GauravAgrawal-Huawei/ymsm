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

import org.junit.Test;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev20130715.ietfinettypes.Uri;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.YmsIetfNetwork;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.YmsIetfNetworkOpParam;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.DefaultNetworks;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.Networks;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.NodeId;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.networks.DefaultNetwork;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.networks.Network;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.networks.network.DefaultNode;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.networks.network.Node;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.networks.network.node.DefaultSupportingNode;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.networks.network.node.SupportingNode;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile.networks.network.node.AugmentedNdNode;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile.networks.network.node.DefaultAugmentedNdNode;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile.networks.network.node.augmentedndnode.DefaultTerminationPoint;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile.networks.network.node.augmentedndnode.TerminationPoint;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile.networks.network.node.augmentedndnode.terminationpoint.DefaultSupportingTerminationPoint;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.from.another.file.rev20160826.ytbaugmentfromanotherfile.networks.network.node.augmentedndnode.terminationpoint.SupportingTerminationPoint;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826.ytbaugmentforrpcinput.activatesoftwareimage.output.AugmentedRpcOutput;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826.ytbaugmentforrpcinput.activatesoftwareimage.output.DefaultAugmentedRpcOutput;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826.ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.Selection;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826.ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.DefaultValueIn;
import org.onosproject.yang.gen.v1.yms.test.ytb.augment.yangautoprefixfor.rpc.input.rev20160826.ytbaugmentforrpcinput.activatesoftwareimage.output.augmentedrpcoutput.selection.valuein.ValueIn;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826.YtbChoiceWithContainerAndLeafList;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826.YtbChoiceWithContainerAndLeafListOpParam;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826.ytbchoicewithcontainerandleaflist.ContentTest;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826.ytbchoicewithcontainerandleaflist.CurrentValue;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826.ytbchoicewithcontainerandleaflist.contenttest.DefaultChoiceContainer;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826.ytbchoicewithcontainerandleaflist.contenttest.choicecontainer.ChoiceContainer;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826.ytbchoicewithcontainerandleaflist.contenttest.choicecontainer.choicecontainer.DefaultPredict;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826.ytbchoicewithcontainerandleaflist.contenttest.choicecontainer.choicecontainer.Predict;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826.ytbchoicewithcontainerandleaflist.contenttest.choicecontainer.choicecontainer.predict.DefaultReproduce;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826.ytbchoicewithcontainerandleaflist.contenttest.choicecontainer.choicecontainer.predict.Reproduce;
import org.onosproject.yang.gen.v1.yms.test.ytb.choice.with.container.and.leaf.list.rev20160826.ytbchoicewithcontainerandleaflist.currentvalue.DefaultYtbAbsent;
import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826.ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.ActivateSoftwareImageOutput;
import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826.ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.DefaultActivateSoftwareImageOutput;
import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826.ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.activatesoftwareimageoutput.DefaultOutputList;
import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826.ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.activatesoftwareimageoutput.OutputList;
import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826.ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.activatesoftwareimageoutput.outputlist.ContentInside;
import org.onosproject.yang.gen.v1.yms.test.ytb.rpc.response.with.advanced.input.and.output.rev20160826.ytbrpcresponsewithadvancedinputandoutput.activatesoftwareimage.activatesoftwareimageoutput.outputlist.DefaultContentInside;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.YtbSimpleAugment;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.YtbSimpleAugmentOpParam;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.Cont1;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.DefaultCont1;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.DefaultCont2;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2.AugmentedCont2;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2.DefaultAugmentedCont2;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2.augmentedcont2.Cont1s;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2.augmentedcont2.DefaultCont1s;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.YtbSimpleChoiceCase;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.YtbSimpleChoiceCaseOpParam;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.ytbsimplechoicecase.DefaultYtbFood;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.ytbsimplechoicecase.YtbFood;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.ytbsimplechoicecase.ytbfood.YtbSnack;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.choice.yangautoprefixcase.rev20160826.ytbsimplechoicecase.ytbfood.ytbsnack.DefaultYtbLateNight;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.rpc.response.rev20160826.ytbsimplerpcresponse.rpc.DefaultRpcOutput;
import org.onosproject.yang.gen.v1.yms.test.ytb.simple.rpc.response.rev20160826.ytbsimplerpcresponse.rpc.RpcOutput;
import org.onosproject.yms.app.ydt.YangRequestWorkBench;
import org.onosproject.yms.app.ydt.YdtExtendedBuilder;
import org.onosproject.yms.app.ysr.DefaultYangSchemaRegistry;
import org.onosproject.yms.app.ysr.TestYangSchemaNodeProvider;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ydt.YdtContextOperationType;
import org.onosproject.yms.ydt.YmsOperationType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.onosproject.yms.ydt.YmsOperationType.EDIT_CONFIG_REPLY;
import static org.onosproject.yms.ydt.YmsOperationType.QUERY_CONFIG_REPLY;
import static org.onosproject.yms.ydt.YmsOperationType.RPC_REQUEST;

/**
 * Unit test cases for YANG tree builder for context switch for augment, RPC
 * and case.
 */
public class YtbContextSwitchTest {


    private TestYangSchemaNodeProvider testYangSchemaNodeProvider =
            new TestYangSchemaNodeProvider();

    /**
     * Processes a simple choice case and builds the YANG tree to YDT.
     */
    @Test
    public void processSimpleChoiceCase() {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry defaultTreeBuilder =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        YtbSnack caseSnack = new DefaultYtbLateNight.YtbLateNightBuilder()
                .chocolate("choc").build();
        YtbFood food = new DefaultYtbFood.YtbFoodBuilder()
                .ytbSnack(caseSnack).build();
        YtbSimpleChoiceCase simpleChoiceCase = new YtbSimpleChoiceCaseOpParam
                .YtbSimpleChoiceCaseBuilder().ytbFood(food)
                .build();

        // YSB or YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(simpleChoiceCase);

        // Build YANG tree.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtExtendedBuilder ydtBuilder = yangTreeBuilder
                .getYdtBuilderForYo(objectList, "choiceRootName",
                                    "choiceRootNamespace",
                                    EDIT_CONFIG_REPLY, defaultTreeBuilder);
        YdtContext rootNode = ydtBuilder.getRootNode();
        YdtContext moduleNode = rootNode.getFirstChild();
        assertThat(moduleNode.getName(), is("YtbSimpleChoiceCase"));

        YdtContext containerNode = moduleNode.getFirstChild();
        assertThat(containerNode.getName(), is("YtbFood"));

        YdtContext caseNode = containerNode.getFirstChild();
        assertThat(caseNode.getName(), is("chocolate"));
    }

    /**
     * Processes module with two choices and a choice having node and a
     * leaf-list.
     */
    @Test
    public void processChoiceWithNodeAndLeafList() {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry defaultTreeBuilder =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        Reproduce reproduce1 = new DefaultReproduce.ReproduceBuilder()
                .yangAutoPrefixCatch((short) 90).build();
        Predict predict1 = new DefaultPredict.PredictBuilder()
                .reproduce(reproduce1).build();
        Reproduce reproduce2 = new DefaultReproduce.ReproduceBuilder()
                .yangAutoPrefixCatch((short) 100).build();
        Predict predict2 = new DefaultPredict.PredictBuilder()
                .reproduce(reproduce2).build();
        List<Predict> predictList = new ArrayList<>();
        predictList.add(predict1);
        predictList.add(predict2);

        ChoiceContainer choiceContainer =
                new org.onosproject.yang.gen.v1.yms.test.ytb.choice.with
                        .container
                        .and.leaf.list.rev20160826
                        .ytbchoicewithcontainerandleaflist.contenttest
                        .choicecontainer.DefaultChoiceContainer
                        .ChoiceContainerBuilder().predict(predictList).build();
        ContentTest contentTest =
                new DefaultChoiceContainer.ChoiceContainerBuilder()
                        .choiceContainer(choiceContainer).build();


        List<String> stringList = new ArrayList<>();
        stringList.add("val");
        stringList.add("ind");
        CurrentValue currentValue = new DefaultYtbAbsent.YtbAbsentBuilder()
                .yangAutoPrefixFinal(stringList).build();

        YtbChoiceWithContainerAndLeafList choiceWithContainerAndLeafList = new
                YtbChoiceWithContainerAndLeafListOpParam
                        .YtbChoiceWithContainerAndLeafListBuilder()
                .contentTest(contentTest).currentValue(currentValue)
                .build();

        // YSB or YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(choiceWithContainerAndLeafList);

        // Build YANG tree.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtExtendedBuilder ydtBuilder = yangTreeBuilder
                .getYdtBuilderForYo(objectList, "choiceContainerRootName",
                                    "choiceContainerRootNamespace",
                                    QUERY_CONFIG_REPLY, defaultTreeBuilder);

        YdtContext rootNode = ydtBuilder.getRootNode();
        assertThat(rootNode.getName(), is("choiceContainerRootName"));
        assertThat(rootNode.getNamespace(),
                   is("choiceContainerRootNamespace"));
        YdtContext moduleNode = rootNode.getFirstChild();
        assertThat(moduleNode.getName(),
                   is("YtbChoiceWithContainerAndLeafList"));
        YdtContext firstChoiceCaseContainer = moduleNode.getFirstChild();
        assertThat(firstChoiceCaseContainer.getName(), is("choice-container"));
        YdtContext listUnderContainer1 =
                firstChoiceCaseContainer.getFirstChild();
        assertThat(listUnderContainer1.getName(), is("predict"));
        YdtContext containerUnderList1 = listUnderContainer1.getFirstChild();
        assertThat(containerUnderList1.getName(), is("reproduce"));
        YdtContext leafUnderContainer1 = containerUnderList1.getFirstChild();
        assertThat(leafUnderContainer1.getName(), is("catch"));
        assertThat(leafUnderContainer1.getValue(), is("90"));

        YdtContext listUnderContainer2 = listUnderContainer1.getNextSibling();
        assertThat(listUnderContainer2.getName(), is("predict"));
        YdtContext containerUnderList2 = listUnderContainer2.getFirstChild();
        assertThat(containerUnderList2.getName(), is("reproduce"));
        YdtContext leafUnderContainer2 = containerUnderList2.getFirstChild();
        assertThat(leafUnderContainer2.getName(), is("catch"));
        assertThat(leafUnderContainer2.getValue(), is("100"));

        YdtContext secondChoiceCase = firstChoiceCaseContainer.getNextSibling();
        assertThat(secondChoiceCase.getName(), is("final"));
        Set valueInLeafList = secondChoiceCase.getValueSet();
        assertThat(valueInLeafList.contains("val"), is(true));
        assertThat(valueInLeafList.contains("ind"), is(true));
    }

    /**
     * Processes RPC response of a simple output with only a leaf content
     * inside.
     */
    @Test
    public void processSimpleRpcResponse() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry defaultTreeBuilder =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();
        RpcOutput output = new DefaultRpcOutput.RpcOutputBuilder()
                .outputLeaf(500).build();

        // YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(output);

        // Build RPC request tree in YDT.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YangRequestWorkBench requestWorkBench = new YangRequestWorkBench(
                "rpcRootName", "rpcRootNameSpace", RPC_REQUEST,
                defaultTreeBuilder, true);
        requestWorkBench.addChild("YtbSimpleRpcResponse", null,
                                  YdtContextOperationType.NONE);
        requestWorkBench.addChild("rpc", null, YdtContextOperationType.NONE);
        requestWorkBench.addChild("input", null, YdtContextOperationType.NONE);
        YdtExtendedBuilder ydtBuilder = yangTreeBuilder.getYdtForRpcResponse(
                output, requestWorkBench);

        YdtContext rootNode = ydtBuilder.getRootNode();
        assertThat(rootNode.getName(), is("rpcRootName"));
        assertThat(rootNode.getNamespace(), is("rpcRootNameSpace"));

        YdtContext moduleNode = rootNode.getFirstChild();
        assertThat(moduleNode.getName(), is("YtbSimpleRpcResponse"));

        YdtContext rpcNode = moduleNode.getFirstChild();
        assertThat(rpcNode.getName(), is("rpc"));
        // TODO: Change assert after YANG utils is merged.
        YdtContext rpcOutputNode = rpcNode.getFirstChild();
        //assertThat(rpcOutputNode.getName(), is("output"));

        YdtContext outputLeaf = rpcOutputNode.getFirstChild();
        assertThat(outputLeaf.getName(), is("output-leaf"));
        assertThat(outputLeaf.getValue(), is("500"));
    }

    /**
     * Processes RPC response of an advanced input and output defined.
     */
    @Test
    public void processRpcResponseForAdvInputOutput() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry defaultTreeBuilder =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        // Build RPC request tree in YDT.
        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YangRequestWorkBench requestWorkBench = buildYangRequestWorkBenchForRpc(
                defaultTreeBuilder);
        List<OutputList> outputLists = createApplicationBuiltObjectForRpc();
        ActivateSoftwareImageOutput output =
                new DefaultActivateSoftwareImageOutput
                        .ActivateSoftwareImageOutputBuilder()
                        .outputList(outputLists).build();

        // YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(output);

        YdtExtendedBuilder ydtBuilder = yangTreeBuilder.getYdtForRpcResponse(
                output, requestWorkBench);

        YdtContext rootNode = ydtBuilder.getRootNode();
        assertThat(rootNode.getName(), is("RPCAdvanced"));
        assertThat(rootNode.getNamespace(), is("RPCAdvancedSpace"));

        YdtContext moduleNode = rootNode.getFirstChild();
        assertThat(moduleNode.getName(),
                   is("YtbRpcResponseWithAdvancedInputAndOutput"));

        YdtContext rpcNode = moduleNode.getFirstChild();
        assertThat(rpcNode.getName(), is("activate-software-image"));

        // TODO: Change assert after YANG utils is merged.
        YdtContext rpcOutputNode = rpcNode.getFirstChild();
        //assertThat(rpcOutputNode.getName(), is("output"));

        YdtContext outputListNode1 = rpcOutputNode.getFirstChild();
        assertThat(outputListNode1.getName(), is("output-list"));

        YdtContext leafOfListNode1 = outputListNode1.getFirstChild();
        assertThat(leafOfListNode1.getName(), is("list-key"));
        assertThat(leafOfListNode1.getValue(), is("AAE="));

        YdtContext containerOfListNode1 = leafOfListNode1.getNextSibling();
        assertThat(containerOfListNode1.getName(), is("content_inside"));

        YdtContext outputListNode2 = outputListNode1.getNextSibling();
        assertThat(outputListNode2.getName(), is("output-list"));

        YdtContext leafOfListNode2 = outputListNode2.getFirstChild();
        assertThat(leafOfListNode2.getName(), is("list-key"));
        assertThat(leafOfListNode2.getValue(), is("CAk="));

        YdtContext containerOfListNode2 = leafOfListNode2.getNextSibling();
        assertThat(containerOfListNode2.getName(), is("content_inside"));

        YdtContext leafListOfcontainerOfListNode2 = containerOfListNode2
                .getFirstChild();
        assertThat(leafListOfcontainerOfListNode2.getName(), is("available"));
        Set valuesInavailable = leafListOfcontainerOfListNode2.getValueSet();
        assertThat(valuesInavailable.contains("89"), is(true));
        assertThat(valuesInavailable.contains("98"), is(true));

        YdtContext outputListNode3 = outputListNode2.getNextSibling();
        assertThat(outputListNode3.getName(), is("output-list"));

        YdtContext leafOfListNode3 = outputListNode3.getFirstChild();
        assertThat(leafOfListNode3.getName(), is("list-key"));
        assertThat(leafOfListNode3.getValue(), is("AAA="));
    }

    /**
     * Creates object as like an application for RPC.
     *
     * @return object of RPC
     */
    private List<OutputList> createApplicationBuiltObjectForRpc() {
        ContentInside inside1 = new DefaultContentInside.ContentInsideBuilder()
                .build();
        byte[] arr1 = new byte[]{0, 1};
        OutputList outputList1 = new DefaultOutputList.OutputListBuilder()
                .listKey(arr1).contentInside(inside1).build();

        List<Short> avail = new ArrayList<>();
        avail.add((short) 89);
        avail.add((short) 98);
        ContentInside inside2 = new DefaultContentInside.ContentInsideBuilder()
                .available(avail).build();
        byte[] arr2 = new byte[]{8, 9};
        OutputList outputList2 = new DefaultOutputList.OutputListBuilder()
                .listKey(arr2).contentInside(inside2).build();

        byte[] arr3 = new byte[]{0, 0};
        OutputList outputList3 = new DefaultOutputList.OutputListBuilder()
                .listKey(arr3).build();

        List<OutputList> outputLists = new ArrayList<>();
        outputLists.add(outputList1);
        outputLists.add(outputList2);
        outputLists.add(outputList3);
        return outputLists;
    }

    /**
     * Builds YANG request work bench for RPC.
     *
     * @param defaultTreeBuilder schema registry
     * @return YANG request work bench
     */
    private YangRequestWorkBench buildYangRequestWorkBenchForRpc(
            DefaultYangSchemaRegistry defaultTreeBuilder) {
        YangRequestWorkBench requestWorkBench = new YangRequestWorkBench(
                "RPCAdvanced", "RPCAdvancedSpace", RPC_REQUEST,
                defaultTreeBuilder, true);
        Set valueList = new HashSet<>();
        valueList.add(800);
        valueList.add(900);
        requestWorkBench.addChild("YtbRpcResponseWithAdvancedInputAndOutput",
                                  null, YdtContextOperationType.NONE);
        requestWorkBench.addChild("activate-software-image", null,
                                  YdtContextOperationType.NONE);
        requestWorkBench.addChild("input", null, YdtContextOperationType.NONE);
        requestWorkBench.addChild("final", null, YdtContextOperationType.NONE);
        requestWorkBench.addLeaf("value", null, valueList);
        return requestWorkBench;
    }

    /**
     * Processes simple self augment file.
     */
    @Test
    public void processSimpleAugment() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry defaultTreeBuilder =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        org.onosproject.yang.gen.v1.yms.test.ytb.simple.augment.rev20160826
                .ytbsimpleaugment.cont1.cont2.augmentedcont2.cont1s
                .Cont1s cont1si = new org.onosproject.yang.gen.v1.yms.test
                .ytb.simple.augment.rev20160826.ytbsimpleaugment.cont1.cont2
                .augmentedcont2.cont1s.DefaultCont1s.Cont1sBuilder().build();
        Cont1s cont1s = new DefaultCont1s.Cont1sBuilder().cont1s(cont1si)
                .build();
        AugmentedCont2 augmentedCont2 = new DefaultAugmentedCont2
                .AugmentedCont2Builder().cont1s(cont1s).leaf4(500).build();

        DefaultCont2.Cont2Builder cont2Builder = new DefaultCont2
                .Cont2Builder();
        cont2Builder.addYangAugmentedInfo(augmentedCont2, AugmentedCont2.class);

        Cont1 cont1 = new DefaultCont1.Cont1Builder()
                .cont2(cont2Builder.build()).build();
        YtbSimpleAugment simpleAugment = new YtbSimpleAugmentOpParam
                .YtbSimpleAugmentBuilder().cont1(cont1).build();

        // YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(simpleAugment);

        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtExtendedBuilder ydtBuilder = yangTreeBuilder
                .getYdtBuilderForYo(objectList, "simpleAugment",
                                    "simpleAugmentSpace",
                                    YmsOperationType.EDIT_CONFIG_REQUEST,
                                    defaultTreeBuilder);

        YdtContext rootNode = ydtBuilder.getRootNode();
        assertThat(rootNode.getName(), is("simpleAugment"));
        assertThat(rootNode.getNamespace(), is("simpleAugmentSpace"));

        YdtContext moduleNode = rootNode.getFirstChild();
        assertThat(moduleNode.getName(), is("YtbSimpleAugment"));

        YdtContext container1Node = moduleNode.getFirstChild();
        assertThat(container1Node.getName(), is("cont1"));

        YdtContext container2Node = container1Node.getFirstChild();
        assertThat(container2Node.getName(), is("cont2"));

        YdtContext leafNode = container2Node.getFirstChild();
        assertThat(leafNode.getName(), is("leaf4"));
        assertThat(leafNode.getValue(), is("500"));

        YdtContext cont1sNode = leafNode.getNextSibling();
        assertThat(cont1sNode.getName(), is("cont1s"));

        YdtContext cont2sNode = cont1sNode.getFirstChild();
        assertThat(cont2sNode.getName(), is("cont1s"));
    }

    /**
     * Processes inter file augment with augmented node as list and the
     * augment having list.
     */
    @Test
    public void processInterFileAugment() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry defaultTreeBuilder =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        Object opParam = createObjectForInterFileAugment();
        // YAB protocol to set the values for YTB.
        List<Object> objectList = new ArrayList<>();
        objectList.add(opParam);

        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtExtendedBuilder ydtBuilder = yangTreeBuilder
                .getYdtBuilderForYo(objectList, "inter-file-augment",
                                    "inter-file-augment-space",
                                    YmsOperationType.EDIT_CONFIG_REQUEST,
                                    defaultTreeBuilder);

        YdtContext rootNode = ydtBuilder.getRootNode();
        assertThat(rootNode.getName(), is("inter-file-augment"));
        assertThat(rootNode.getNamespace(), is("inter-file-augment-space"));

        YdtContext moduleNode = rootNode.getFirstChild();
        assertThat(moduleNode.getName(), is("yms-ietf-network"));

        YdtContext networksContNode = moduleNode.getFirstChild();
        assertThat(networksContNode.getName(), is("networks"));

        YdtContext networkListNode = networksContNode.getFirstChild();
        assertThat(networkListNode.getName(), is("network"));

        YdtContext nodeListNode1 = networkListNode.getFirstChild();
        assertThat(nodeListNode1.getName(), is("node"));

        YdtContext nodeIdLeafNode1 = nodeListNode1.getFirstChild();
        assertThat(nodeIdLeafNode1.getName(), is("node-id"));
        assertThat(nodeIdLeafNode1.getValue(), is("node-ref3"));

        YdtContext terminationList1 = nodeIdLeafNode1.getNextSibling();
        assertThat(terminationList1.getName(), is("termination-point"));

        YdtContext tpIdLeaftermList1 = terminationList1.getFirstChild();
        assertThat(tpIdLeaftermList1.getName(), is("tp-id"));
        assertThat(tpIdLeaftermList1.getValue(), is("tp-id-aug-1"));

        YdtContext supportingtermList1 = tpIdLeaftermList1.getNextSibling();
        assertThat(supportingtermList1.getName(),
                   is("supporting-termination-point"));

        YdtContext networkRefSupportingtermList1 =
                supportingtermList1.getFirstChild();
        assertThat(networkRefSupportingtermList1.getName(),
                   is("network-ref"));
        assertThat(networkRefSupportingtermList1.getValue(),
                   is("network-ref-aug1"));

        YdtContext nodeRefSupportingtermList1 =
                networkRefSupportingtermList1.getNextSibling();
        assertThat(nodeRefSupportingtermList1.getName(), is("node-ref"));
        assertThat(nodeRefSupportingtermList1.getValue(), is("node-ref-aug1"));

        YdtContext tpRefSupportingtermList1 =
                nodeRefSupportingtermList1.getNextSibling();
        assertThat(tpRefSupportingtermList1.getName(), is("tp-ref"));
        assertThat(tpRefSupportingtermList1.getValue(), is("tp-ref-aug-1"));

        YdtContext terminationList2 = terminationList1.getNextSibling();
        assertThat(terminationList2.getName(), is("termination-point"));

        YdtContext tpIdLeaftermList2 = terminationList2.getFirstChild();
        assertThat(tpIdLeaftermList2.getName(), is("tp-id"));
        assertThat(tpIdLeaftermList2.getValue(), is("tp-id-aug-1b"));

        YdtContext supportingtermList2 = tpIdLeaftermList2.getNextSibling();
        assertThat(supportingtermList2.getName(),
                   is("supporting-termination-point"));

        YdtContext networkRefSupportingtermList2 =
                supportingtermList2.getFirstChild();
        assertThat(networkRefSupportingtermList2.getName(), is("network-ref"));
        assertThat(networkRefSupportingtermList2.getValue(),
                   is("network-ref-augb1"));

        YdtContext nodeRefSupportingtermList2 =
                networkRefSupportingtermList2.getNextSibling();
        assertThat(nodeRefSupportingtermList2.getName(), is("node-ref"));
        assertThat(nodeRefSupportingtermList2.getValue(), is("node-ref-augb1"));

        YdtContext tpRefSupportingtermList2 =
                nodeRefSupportingtermList2.getNextSibling();
        assertThat(tpRefSupportingtermList2.getName(), is("tp-ref"));
        assertThat(tpRefSupportingtermList2.getValue(), is("tp-ref-aug-b1"));

        YdtContext supportingNodeList1 = terminationList2.getNextSibling();
        assertThat(supportingNodeList1.getName(), is("supporting-node"));

        YdtContext networkRefSupportingNodeList1 =
                supportingNodeList1.getFirstChild();
        assertThat(networkRefSupportingNodeList1.getName(), is("network-ref"));
        assertThat(networkRefSupportingNodeList1.getValue(), is("network-ref"));

        YdtContext nodeRefSupportingNodeList1 =
                networkRefSupportingNodeList1.getNextSibling();
        assertThat(nodeRefSupportingNodeList1.getName(), is("node-ref"));
        assertThat(networkRefSupportingNodeList1.getValue(), is("network-ref"));

        YdtContext supportingNodeList2 = supportingNodeList1.getNextSibling();
        assertThat(supportingNodeList2.getName(), is("supporting-node"));

        YdtContext networkRefSupportingNodeList2 =
                supportingNodeList2.getFirstChild();
        assertThat(networkRefSupportingNodeList2.getName(), is("network-ref"));
        assertThat(networkRefSupportingNodeList2.getValue(),
                   is("network-ref2"));

        YdtContext nodeRefSupportingNodeList2 =
                networkRefSupportingNodeList2.getNextSibling();
        assertThat(nodeRefSupportingNodeList2.getName(), is("node-ref"));
        assertThat(networkRefSupportingNodeList2.getValue(),
                   is("network-ref2"));

        YdtContext nodeListNode2 = nodeListNode1.getNextSibling();
        assertThat(nodeListNode2.getName(), is("node"));

        YdtContext nodeIdLeafNode2 = nodeListNode2.getFirstChild();
        assertThat(nodeIdLeafNode2.getName(), is("node-id"));
        assertThat(nodeIdLeafNode2.getValue(), is("node-ref3-b"));

        YdtContext supportingNodeList3 = nodeIdLeafNode2.getNextSibling();
        assertThat(supportingNodeList3.getName(), is("supporting-node"));

        YdtContext networkRefSupportingNodeList3 =
                supportingNodeList3.getFirstChild();
        assertThat(networkRefSupportingNodeList3.getName(), is("network-ref"));
        assertThat(networkRefSupportingNodeList3.getValue(),
                   is("network-ref-b"));

        YdtContext nodeRefSupportingNodeList3 =
                networkRefSupportingNodeList3.getNextSibling();
        assertThat(nodeRefSupportingNodeList3.getName(), is("node-ref"));
        assertThat(nodeRefSupportingNodeList3.getValue(), is("node-ref-b"));

        YdtContext supportingNodeList4 = supportingNodeList3.getNextSibling();
        assertThat(supportingNodeList4.getName(), is("supporting-node"));

        YdtContext networkRefSupportingNodeList4 =
                supportingNodeList4.getFirstChild();
        assertThat(networkRefSupportingNodeList4.getName(), is("network-ref"));
        assertThat(networkRefSupportingNodeList4.getValue(),
                   is("network-ref2-b"));

        YdtContext nodeRefSupportingNodeList4 =
                networkRefSupportingNodeList4.getNextSibling();
        assertThat(nodeRefSupportingNodeList4.getName(), is("node-ref"));
        assertThat(nodeRefSupportingNodeList4.getValue(), is("node-ref2-b"));
    }

    /**
     * Processes inter file augment with rpc output as its target node.
     */
    @Test
    public void processInterFileAugmentWithRpcInputAsTarget() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry defaultTreeBuilder =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();
        // Build RPC request tree in YDT.
        YangRequestWorkBench requestWorkBench =
                buildYangRequestWorkBenchForRpc(defaultTreeBuilder);

        ValueIn valuein1 = new org.onosproject.yang.gen.v1.yms.test.ytb.augment
                .yangautoprefixfor.rpc.input.rev20160826.ytbaugmentforrpcinput
                .activatesoftwareimage.output.augmentedrpcoutput.selection
                .valuein.DefaultValueIn.ValueInBuilder().kinetic("kin1")
                .build();
        ValueIn valuein2 = new org.onosproject.yang.gen.v1.yms.test.ytb.augment
                .yangautoprefixfor.rpc.input.rev20160826.ytbaugmentforrpcinput
                .activatesoftwareimage.output.augmentedrpcoutput.selection
                .valuein.DefaultValueIn.ValueInBuilder().kinetic("kin2")
                .build();
        List<ValueIn> valueInList = new ArrayList<>();
        valueInList.add(valuein1);
        valueInList.add(valuein2);
        Selection selection = new DefaultValueIn.ValueInBuilder()
                .valueIn(valueInList).build();
        AugmentedRpcOutput augmentRpcOutput = new DefaultAugmentedRpcOutput
                .AugmentedRpcOutputBuilder().selection(selection).build();

        List<OutputList> outputLists = createApplicationBuiltObjectForRpc();
        DefaultActivateSoftwareImageOutput
                .ActivateSoftwareImageOutputBuilder output =
                new DefaultActivateSoftwareImageOutput
                        .ActivateSoftwareImageOutputBuilder();
        output.addYangAugmentedInfo(augmentRpcOutput, AugmentedRpcOutput.class);
        output.outputList(outputLists);

        DefaultYangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
        YdtExtendedBuilder ydtBuilder = yangTreeBuilder
                .getYdtForRpcResponse(output, requestWorkBench);

        YdtContext rootNode = ydtBuilder.getRootNode();
        assertThat(rootNode.getName(), is("RPCAdvanced"));
        assertThat(rootNode.getNamespace(), is("RPCAdvancedSpace"));

        YdtContext moduleNode = rootNode.getFirstChild();
        assertThat(moduleNode.getName(),
                   is("YtbRpcResponseWithAdvancedInputAndOutput"));

        YdtContext rpcNode = moduleNode.getFirstChild();
        assertThat(rpcNode.getName(), is("activate-software-image"));

        // TODO: Change assert after YANG utils is merged.
        YdtContext rpcOutputNode = rpcNode.getFirstChild();
        //assertThat(rpcOutputNode.getName(), is("output"));

        YdtContext augmentListValue1 = rpcOutputNode.getFirstChild();
        assertThat(augmentListValue1.getName(), is("value-in"));

        YdtContext augmentListLeaf1 = augmentListValue1.getFirstChild();
        assertThat(augmentListLeaf1.getName(), is("kinetic"));
        assertThat(augmentListLeaf1.getValue(), is("kin1"));

        YdtContext augmentListValue2 = augmentListValue1.getNextSibling();
        assertThat(augmentListValue2.getName(), is("value-in"));

        YdtContext augmentListLeaf2 = augmentListValue2.getFirstChild();
        assertThat(augmentListLeaf2.getName(), is("kinetic"));
        assertThat(augmentListLeaf2.getValue(), is("kin2"));

        YdtContext outputListNode1 = augmentListValue2.getNextSibling();
        assertThat(outputListNode1.getName(), is("output-list"));

        YdtContext leafOfListNode1 = outputListNode1.getFirstChild();
        assertThat(leafOfListNode1.getName(), is("list-key"));
        assertThat(leafOfListNode1.getValue(), is("AAE="));

        YdtContext containerOfListNode1 = leafOfListNode1.getNextSibling();
        assertThat(containerOfListNode1.getName(), is("content_inside"));

        YdtContext outputListNode2 = outputListNode1.getNextSibling();
        assertThat(outputListNode2.getName(), is("output-list"));

        YdtContext leafOfListNode2 = outputListNode2.getFirstChild();
        assertThat(leafOfListNode2.getName(), is("list-key"));
        assertThat(leafOfListNode2.getValue(), is("CAk="));

        YdtContext containerOfListNode2 = leafOfListNode2.getNextSibling();
        assertThat(containerOfListNode2.getName(), is("content_inside"));

        YdtContext leafListOfcontainerOfListNode2 =
                containerOfListNode2.getFirstChild();
        assertThat(leafListOfcontainerOfListNode2.getName(), is("available"));
        Set valuesInavailable = leafListOfcontainerOfListNode2.getValueSet();
        assertThat(valuesInavailable.contains("89"), is(true));
        assertThat(valuesInavailable.contains("98"), is(true));

        YdtContext outputListNode3 = outputListNode2.getNextSibling();
        assertThat(outputListNode3.getName(), is("output-list"));

        YdtContext leafOfListNode3 = outputListNode3.getFirstChild();
        assertThat(leafOfListNode3.getName(), is("list-key"));
        assertThat(leafOfListNode3.getValue(), is("AAA="));
    }

    /**
     * Creates an application object for inter file augment.
     *
     * @return application object
     */
    private Object createObjectForInterFileAugment() {
        Uri uriAug1 = new Uri("network-ref-aug1");
        Uri nodeUriAug1 = new Uri("node-ref-aug1");
        NodeId nodeIdAug1 = new NodeId(nodeUriAug1);
        Uri uriAug2 = new Uri("network-ref-aug2");
        Uri nodeUriAug2 = new Uri("node-ref-aug2");
        NodeId nodeIdAug2 = new NodeId(nodeUriAug2);
        SupportingTerminationPoint supportingTerminationPoint1 =
                new DefaultSupportingTerminationPoint
                        .SupportingTerminationPointBuilder()
                        .networkRef(uriAug1).nodeRef(nodeIdAug1)
                        .tpRef("tp-ref-aug-1").build();
        SupportingTerminationPoint supportingTerminationPoint2 =
                new DefaultSupportingTerminationPoint
                        .SupportingTerminationPointBuilder()
                        .networkRef(uriAug2).nodeRef(nodeIdAug2)
                        .tpRef("tp-ref-aug-2").build();
        List<SupportingTerminationPoint> supportingTerminationPointList =
                new ArrayList<>();
        supportingTerminationPointList.add(supportingTerminationPoint1);
        supportingTerminationPointList.add(supportingTerminationPoint2);
        TerminationPoint terminationPoint1 = new DefaultTerminationPoint
                .TerminationPointBuilder()
                .supportingTerminationPoint(supportingTerminationPointList)
                .tpId("tp-id-aug-1").build();

        Uri uriAugb1 = new Uri("network-ref-augb1");
        Uri nodeUriAugb1 = new Uri("node-ref-augb1");
        NodeId nodeIdAugb1 = new NodeId(nodeUriAugb1);
        Uri uriAugb2 = new Uri("network-ref-augb2");
        Uri nodeUriAugb2 = new Uri("node-ref-augb2");
        NodeId nodeIdAugb2 = new NodeId(nodeUriAugb2);
        SupportingTerminationPoint supportingTerminationPointb1 =
                new DefaultSupportingTerminationPoint
                        .SupportingTerminationPointBuilder()
                        .networkRef(uriAugb1).nodeRef(nodeIdAugb1)
                        .tpRef("tp-ref-aug-b1").build();
        SupportingTerminationPoint supportingTerminationPointb2 =
                new DefaultSupportingTerminationPoint
                        .SupportingTerminationPointBuilder()
                        .networkRef(uriAugb2).nodeRef(nodeIdAugb2)
                        .tpRef("tp-ref-aug-b2").build();
        List<SupportingTerminationPoint> supportingTerminationPointListb =
                new ArrayList<>();
        supportingTerminationPointListb.add(supportingTerminationPointb1);
        supportingTerminationPointListb.add(supportingTerminationPointb2);
        TerminationPoint terminationPoint2 = new DefaultTerminationPoint
                .TerminationPointBuilder()
                .supportingTerminationPoint(supportingTerminationPointListb)
                .tpId("tp-id-aug-1b").build();

        List<TerminationPoint> terminationPointList = new ArrayList<>();
        terminationPointList.add(terminationPoint1);
        terminationPointList.add(terminationPoint2);

        AugmentedNdNode augmentedNdNode = new DefaultAugmentedNdNode
                .AugmentedNdNodeBuilder()
                .terminationPoint(terminationPointList)
                .build();

        Uri uri1 = new Uri("network-ref");
        Uri nodeUri1 = new Uri("node-ref");
        NodeId nodeId1 = new NodeId(nodeUri1);
        SupportingNode supportingNode1 = new DefaultSupportingNode
                .SupportingNodeBuilder().nodeRef(nodeId1)
                .networkRef(uri1).build();
        Uri uri2 = new Uri("network-ref2");
        Uri nodeUri2 = new Uri("node-ref2");
        NodeId nodeId2 = new NodeId(nodeUri2);
        SupportingNode supportingNode2 = new DefaultSupportingNode
                .SupportingNodeBuilder()
                .nodeRef(nodeId2)
                .networkRef(uri2).build();
        List<SupportingNode> supportingNodeList = new ArrayList<>();
        supportingNodeList.add(supportingNode1);
        supportingNodeList.add(supportingNode2);
        Uri nodeUri3 = new Uri("node-ref3");
        NodeId nodeId3 = new NodeId(nodeUri3);

        DefaultNode.NodeBuilder nodeBuilder = new DefaultNode.NodeBuilder();
        nodeBuilder.addYangAugmentedInfo(
                augmentedNdNode, AugmentedNdNode.class);
        nodeBuilder.supportingNode(supportingNodeList);
        nodeBuilder.nodeId(nodeId3);

        Node node1 = nodeBuilder.build();

        AugmentedNdNode augmentedNdNode2 = new DefaultAugmentedNdNode
                .AugmentedNdNodeBuilder().build();
        Uri urib1 = new Uri("network-ref-b");
        Uri nodeUrib1 = new Uri("node-ref-b");
        NodeId nodeIdb1 = new NodeId(nodeUrib1);
        SupportingNode supportingNodeb1 = new DefaultSupportingNode
                .SupportingNodeBuilder().nodeRef(nodeIdb1)
                .networkRef(urib1).build();
        Uri urib2 = new Uri("network-ref2-b");
        Uri nodeUrib2 = new Uri("node-ref2-b");
        NodeId nodeIdb2 = new NodeId(nodeUrib2);
        SupportingNode supportingNodeb2 = new DefaultSupportingNode
                .SupportingNodeBuilder()
                .nodeRef(nodeIdb2)
                .networkRef(urib2).build();
        List<SupportingNode> supportingNodeListb = new ArrayList<>();
        supportingNodeListb.add(supportingNodeb1);
        supportingNodeListb.add(supportingNodeb2);
        Uri nodeUrib3 = new Uri("node-ref3-b");
        NodeId nodeIdb3 = new NodeId(nodeUrib3);

        DefaultNode.NodeBuilder nodeBuilder2 = new DefaultNode.NodeBuilder();
        nodeBuilder2.addYangAugmentedInfo(
                augmentedNdNode2, AugmentedNdNode.class);
        nodeBuilder2.supportingNode(supportingNodeListb);
        nodeBuilder2.nodeId(nodeIdb3);


        Node node2 = nodeBuilder2.build();

        List<Node> nodeList = new LinkedList<>();
        nodeList.add(node1);
        nodeList.add(node2);

        Network listNetwork1 = new DefaultNetwork.NetworkBuilder()
                .node(nodeList).build();

        List<Network> networkList = new ArrayList<>();
        networkList.add(listNetwork1);

        Networks contNetworks = new DefaultNetworks.NetworksBuilder()
                .network(networkList).build();
        YmsIetfNetwork opParam = new YmsIetfNetworkOpParam
                .YmsIetfNetworkBuilder()
                .networks(contNetworks).build();
        return opParam;
    }
}
