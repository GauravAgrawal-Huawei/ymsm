/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.onosproject.yms.app.yob;

import org.junit.Test;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.YmsIetfNetworkOpParam;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.ymsietfnetwork.networksstate.Network;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.te.topology.rev20160317.YmsIetfTeTopologyOpParam;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.te.topology.rev20160317.ymsietftetopology.TeAdminStatus;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.te.topology.rev20160317.ymsietftetopology.teadminstatus.TeAdminStatusEnum;
import org.onosproject.yms.app.ydt.YangRequestWorkBench;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.ydt.YdtContext;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.onosproject.yms.app.yob.YobTestUtils.ADMIN_STATUS;
import static org.onosproject.yms.app.yob.YobTestUtils.IETF_TE_TOPOLOGY;
import static org.onosproject.yms.app.yob.YobTestUtils.NETWORK;
import static org.onosproject.yms.app.yob.YobTestUtils.NETWORKS_STATE;
import static org.onosproject.yms.app.yob.YobTestUtils.NETWORK_REF;
import static org.onosproject.yms.app.yob.YobTestUtils.ROOT_DATA_RESOURCE;
import static org.onosproject.yms.app.yob.YobTestUtils.STR_LEAF_VALUE;
import static org.onosproject.yms.app.yob.YobTestUtils.TE_NODE_ATTRIBUTES;
import static org.onosproject.yms.app.yob.YobTestUtils.TE_NODE_EVENT;
import static org.onosproject.yms.app.yob.YobTestUtils.UP;
import static org.onosproject.yms.app.yob.YobTestUtils.YMS_IETF_NETWORK;
import static org.onosproject.yms.ydt.YdtContextOperationType.CREATE;

/**
 * Test the YANG object building for the YANG data tree based on the grouping
 * and uses nodes.
 */
public class YobGroupingUsesTest {

    private YobTestUtils utils = YobTestUtils.instance();

    @Test
    public void testGroupingUsesLeaf() throws IOException {
        YangRequestWorkBench ydtBuilder = new YangRequestWorkBench(
                ROOT_DATA_RESOURCE, null, null, utils.schemaRegistry(), true);
        ydtBuilder.addChild(YMS_IETF_NETWORK, null, CREATE);
        ydtBuilder.addChild(NETWORKS_STATE, null);
        ydtBuilder.addChild(NETWORK, null);
        ydtBuilder.addLeaf(NETWORK_REF, null, STR_LEAF_VALUE);
        YdtContext logicalRoot = ydtBuilder.getRootNode();
        YdtExtendedContext appRoot =
                (YdtExtendedContext) logicalRoot.getFirstChild();

        DefaultYobBuilder yobBuilder = new DefaultYobBuilder();
        Object yangObject = yobBuilder.getYangObject(appRoot,
                                                     utils.schemaRegistry());
        assertThat(yangObject, is(notNullValue()));
        YmsIetfNetworkOpParam ietfNetwork = (YmsIetfNetworkOpParam) yangObject;
        Network network = ietfNetwork.networksState().network().iterator().next();
        assertThat(network.networkRef(), is(STR_LEAF_VALUE));
    }

    @Test
    public void testGroupingUsesContainer() throws IOException {
        YangRequestWorkBench ydtBuilder = new YangRequestWorkBench(
                ROOT_DATA_RESOURCE, null, null, utils.schemaRegistry(), true);
        ydtBuilder.addChild(IETF_TE_TOPOLOGY, null, CREATE);
        ydtBuilder.addChild(TE_NODE_EVENT, null);
        ydtBuilder.addChild(TE_NODE_ATTRIBUTES, null);
        ydtBuilder.addLeaf(ADMIN_STATUS, null, UP);
        YdtContext logicalRoot = ydtBuilder.getRootNode();
        YdtExtendedContext appRoot =
                (YdtExtendedContext) logicalRoot.getFirstChild();

        DefaultYobBuilder yobBuilder = new DefaultYobBuilder();
        Object yangObject = yobBuilder.getYangObject(appRoot,
                                                     utils.schemaRegistry());
        assertThat(yangObject, is(notNullValue()));
        YmsIetfTeTopologyOpParam ietfTeTopology = (YmsIetfTeTopologyOpParam) yangObject;
        TeAdminStatus adminStatus = ietfTeTopology.teNodeEvent()
                .teNodeAttributes()
                .adminStatus();
        assertThat(adminStatus.enumeration(), is(TeAdminStatusEnum.UP));
    }

    @Test
    public void testGroupingUsesInterfile() throws IOException {
        YangRequestWorkBench ydtBuilder = new YangRequestWorkBench(
                ROOT_DATA_RESOURCE, null, null, utils.schemaRegistry(), true);
        ydtBuilder.addChild(YMS_IETF_NETWORK, null, CREATE);
        ydtBuilder.addChild("networks", null);
        ydtBuilder.addChild("network", null);
        ydtBuilder.addChild("link", "yms-network-topology");
        ydtBuilder.addChild("te", IETF_TE_TOPOLOGY);
        ydtBuilder.addChild("config", IETF_TE_TOPOLOGY);
        ydtBuilder.addChild("bundled-links", IETF_TE_TOPOLOGY);
        ydtBuilder.addChild("bundled-link", IETF_TE_TOPOLOGY);
        ydtBuilder.addLeaf("sequence", null, "1");
        YdtContext logicalRoot = ydtBuilder.getRootNode();
        YdtExtendedContext appRoot =
                (YdtExtendedContext) logicalRoot.getFirstChild();

        DefaultYobBuilder yobBuilder = new DefaultYobBuilder();
        Object yangObject = yobBuilder.getYangObject(appRoot,
                                                     utils.schemaRegistry());
        assertThat(yangObject, is(notNullValue()));
        YmsIetfNetworkOpParam ietfNetwork = (YmsIetfNetworkOpParam) yangObject;
        ietfNetwork.networks().yangAugmentedInfoMap();
    }

    @Test
    public void testGroupingUsesAugment() throws IOException {
        YangRequestWorkBench ydtBuilder = new YangRequestWorkBench(
                ROOT_DATA_RESOURCE, null, null, utils.schemaRegistry(), true);
        ydtBuilder.addChild(YMS_IETF_NETWORK, null, CREATE);
        ydtBuilder.addChild("networks", null);
        ydtBuilder.addChild("network", null);
        ydtBuilder.addChild("link", "yms-network-topology");
        ydtBuilder.addChild("te", IETF_TE_TOPOLOGY);
        ydtBuilder.addChild("config", IETF_TE_TOPOLOGY);
        ydtBuilder.addLeaf("te-link-template", null, "1");
        YdtContext logicalRoot = ydtBuilder.getRootNode();
        YdtExtendedContext appRoot =
                (YdtExtendedContext) logicalRoot.getFirstChild();

        DefaultYobBuilder yobBuilder = new DefaultYobBuilder();
        Object yangObject = yobBuilder.getYangObject(appRoot,
                                                     utils.schemaRegistry());
        assertThat(yangObject, is(notNullValue()));
//        YmsIetfNetworkOpParam ietfNetwork = (YmsIetfNetworkOpParam) yangObject;
//        ietfNetwork.networks().yangAugmentedInfoMap();
    }
}

