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

package org.onosproject.yms.app.ysr;

import java.io.IOException;

import org.junit.Test;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.IetfNetwork;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.IetfNetworkOpParam;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network.rev20151208.IetfNetworkService;
import org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.network2.rev20151208.IetfNetwork2Service;
import org.onosproject.yangutils.datamodel.YangNode;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yms.app.ynh.YangNotificationExtendedService;
import org.onosproject.yms.app.ynh.YangNotificationManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Unit test case for default schema registry.
 */
public class DefaultYangSchemaRegistryTest {

    private TestYangSchemaNodeProvider testYangSchemaNodeProvider =
            new TestYangSchemaNodeProvider();

    /**
     * Unit test case in which schema node should be present.
     *
     * @throws IOException when fails to do IO operation
     */
    @Test
    public void testForGetSchemaNode()
            throws IOException {

        String schemaName = "ietf-network1";
        String event =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network1.rev20151208.ietfnetwork1.IetfNetwork1Event";
        String appName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network1.rev20151208.IetfNetwork1Service";
        String moduleInterfaceName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network1.rev20151208.IetfNetwork1";
        String moduleOpParamName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network1.rev20151208.IetfNetwork1OpParam";
        testYangSchemaNodeProvider.processSchemaRegistry(null);

        DefaultYangSchemaRegistry registry =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        YangSchemaNode yangNode =
                registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        //As we have not registered an  application this object should be null.
        Object object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));
        testYangSchemaNodeProvider.unregisterService(appName);

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(yangNode == null));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(yangNode == null));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(yangNode == null));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(yangNode == null));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(yangNode == null));

        //As we have not registered an  application this object should be null.
        object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));


        schemaName = "ietf-network2";
        event = "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                ".network2.rev20151208.ietfnetwork2.IetfNetwork2Event";
        appName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network2.rev20151208.IetfNetwork2Service";
        moduleInterfaceName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network2.rev20151208.IetfNetwork2";
        moduleOpParamName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network2.rev20151208.IetfNetwork2OpParam";

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        //As we have not registered an  application this object should be null.
        object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));

        testYangSchemaNodeProvider.unregisterService(appName);

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(yangNode == null));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(yangNode == null));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(yangNode == null));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(yangNode == null));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(yangNode == null));

        //As we have not registered an  application this object should be null.
        object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));

        schemaName = "ietf-network3";
        event = "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                ".network3.rev20151208.ietfnetwork3.IetfNetwork3Event";
        appName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network3.rev20151208.IetfNetwork3Service";
        moduleInterfaceName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network3.rev20151208.IetfNetwork3";
        moduleOpParamName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network3.rev20151208.IetfNetwork3OpParam";

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(yangNode == null));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        //As we have not registered an  application this object should be null.
        object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));

        testYangSchemaNodeProvider.unregisterService(appName);

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(yangNode == null));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(yangNode == null));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(yangNode == null));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(yangNode == null));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(yangNode == null));

        //As we have not registered an  application this object should be null.
        object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));

        testYangSchemaNodeProvider.unregisterAllService();
    }

    /**
     * Unit test case in which schema node should not be present.
     *
     * @throws IOException when fails to do IO operation
     */
    @Test
    public void testForDoNotGetSchemaNode()
            throws IOException {
        String schemaName = "ietf-routing";
        String moduleInterfaceName = "IetfRouting";
        String moduleOpParamName = "IetfRoutingOpParam";
        String appName = "IetfRoutingService";
        String event = "IetfRoutingEvent";

        DefaultYangSchemaRegistry registry =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        // here all nodes should be null as we have not done any registration for this application.
        YangSchemaNode yangNode =
                registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(yangNode == null));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(yangNode == null));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(yangNode == null));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(yangNode == null));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(yangNode == null));

        //As we have not registered an  application this object should be null.
        Object object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));

    }

    /**
     * Unit test case in which schema node should be present with multi
     * revisions.
     *
     * @throws IOException when fails to do IO operation
     */
    @Test
    public void testForGetSchemaNodeWhenNoRevision()
            throws IOException {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry registry =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        String schemaName = "ietf-network4";
        String event =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20151208.ietfnetwork4.IetfNetwork4Event";
        String appName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20151208.IetfNetwork4Service";
        String moduleInterfaceName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20151208.IetfNetwork4";
        String moduleOpParamName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20151208.IetfNetwork4OpParam";


        YangSchemaNode yangNode =
                registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        //As we have not registered an  application this object should be null.
        Object object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));
        testYangSchemaNodeProvider.unregisterService(appName);

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(yangNode == null));

        //Here the yangNode should be the node which does not have revision.
        // asset should pass with false.
        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(((YangNode) yangNode).getRevision() != null));

        schemaName = "ietf-network4";
        event = "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                ".network4.ietfnetwork4.IetfNetwork4Event";
        appName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.IetfNetwork4Service";
        moduleInterfaceName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.IetfNetwork4";
        moduleOpParamName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.IetfNetwork4OpParam";

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        //As we have not registered an  application this object should be null.
        object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));
        testYangSchemaNodeProvider.unregisterService(appName);

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(yangNode == null));

        //Here the yangNode should be the node which have different revision.
        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(((YangNode) yangNode).getRevision() != null));

        testYangSchemaNodeProvider.unregisterAllService();

    }

    /**
     * Unit test case in which schema node should be present with multi
     * revisions.
     *
     * @throws IOException when fails to do IO operation
     */
    @Test
    public void testForGetSchemaNodeWhenMultiRevision()
            throws IOException {

        testYangSchemaNodeProvider.processSchemaRegistry(null);
        DefaultYangSchemaRegistry registry =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry();

        String schemaName = "ietf-network4";
        String event =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20151208.ietfnetwork4.IetfNetwork4Event";
        String appName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20151208.IetfNetwork4Service";
        String moduleInterfaceName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20151208.IetfNetwork4";
        String moduleOpParamName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20151208.IetfNetwork4OpParam";

        YangSchemaNode yangNode =
                registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        //As we have not registered an  application this object should be null.
        Object object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));
        testYangSchemaNodeProvider.unregisterService(appName);

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(yangNode == null));

        //Here the yangNode should be the node which does not have revision.
        // asset should pass with false.
        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(((YangNode) yangNode).getRevision() != null));

        schemaName = "ietf-network4";
        event = "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                ".network4.rev20161208.ietfnetwork4.IetfNetwork4Event";
        appName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20161208.IetfNetwork4Service";
        moduleInterfaceName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20161208.IetfNetwork4";
        moduleOpParamName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20161208.IetfNetwork4OpParam";

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        //As we have not registered an  application this object should be null.
        object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));
        testYangSchemaNodeProvider.unregisterService(appName);

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(yangNode == null));

        //Here the yangNode should be the node which have different revision.
        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(((YangNode) yangNode).getRevision() != null));

        schemaName = "ietf-network4";
        event = "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                ".network4.rev20171208.ietfnetwork4.IetfNetwork4Event";
        appName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20171208.IetfNetwork4Service";
        moduleInterfaceName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20171208.IetfNetwork4";
        moduleOpParamName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20171208.IetfNetwork4OpParam";

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(yangNode == null));

        //As we have not registered an  application this object should be null.
        object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));
        testYangSchemaNodeProvider.unregisterService(appName);

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(yangNode == null));

        //Here the yangNode should be the node which have different revision.
        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(((YangNode) yangNode).getRevision() == null));

        schemaName = "ietf-network4";
        event = "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                ".network4.rev20141208.ietfnetwork4.IetfNetwork4Event";
        appName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20141208.IetfNetwork4Service";
        moduleInterfaceName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20141208.IetfNetwork4";
        moduleOpParamName =
                "org.onosproject.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf" +
                        ".network4.rev20141208.IetfNetwork4OpParam";

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeInterfaceFileName(
                        moduleInterfaceName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode =
                registry.getYangSchemaNodeUsingGeneratedRootNodeOpPramFileName(
                        moduleOpParamName);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        yangNode = registry.getRootYangSchemaNodeForNotification(event);
        assertThat(true, is(schemaName.equals(yangNode.getName())));

        //As we have not registered an  application this object should be null.
        object = registry.getRegisteredApplication(yangNode);
        assertThat(true, is(object == null));
        testYangSchemaNodeProvider.unregisterService(appName);

        yangNode = registry.getYangSchemaNodeUsingAppName(appName);
        assertThat(true, is(yangNode == null));

        //Here the yangNode should be the node which does not have revision.
        // asset should pass with false.
        yangNode = registry.getYangSchemaNodeUsingSchemaName(schemaName);
        assertThat(true, is((yangNode != null)));

        testYangSchemaNodeProvider.unregisterAllService();
    }

    /**
     * Unit test case should not generate any exceptions.
     */
    @Test
    public void testRegistration() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        testYangSchemaNodeProvider.processRegistrationOfApp();
        testYangSchemaNodeProvider.unregisterAllService();
    }

    /**
     * Unit test case should not generate any exceptions and should
     * return specific revision node.
     */
    @Test
    public void testGetWithSpecificRevision() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        YangSchemaNode schemaNode = testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry()
                .getYangSchemaNodeUsingSchemaName("ietf-network4@2014-00-08");

        assertThat(true, is(schemaNode.getName().equals("ietf-network4")));
        String date = testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry()
                .getDateInStringFormat(schemaNode);
        assertThat(true, is(date.equals("2014-00-08")));
        testYangSchemaNodeProvider.unregisterAllService();
    }

    /**
     * Unit test case should not generate any exceptions
     * verify notification should be checked for registration.
     */
    @Test
    public void testNotification() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        boolean isRegWithNotification =
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry()
                        .verifyNotificationObject(IetfNetworkService.class);
        assertThat(false, is(isRegWithNotification));
        isRegWithNotification = testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry()
                .verifyNotificationObject(IetfNetwork.class);
        assertThat(false, is(isRegWithNotification));
        isRegWithNotification = testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry()
                .verifyNotificationObject(IetfNetworkOpParam.class);
        assertThat(false, is(isRegWithNotification));
        testYangSchemaNodeProvider.unregisterAllService();
    }

    /**
     * Unit test case should not generate any exceptions.
     */
    @Test
    public void testNotificationRegistrationInYnh() {
        testYangSchemaNodeProvider.processSchemaRegistry(null);
        testYangSchemaNodeProvider.getDefaultYangSchemaRegistry()
                .verifyNotificationObject(IetfNetworkService.class);
        testYangSchemaNodeProvider.getDefaultYangSchemaRegistry()
                .verifyNotificationObject(IetfNetwork.class);
        testYangSchemaNodeProvider.getDefaultYangSchemaRegistry()
                .verifyNotificationObject(IetfNetworkOpParam.class);
        Ietf ietf = new Ietf();
        YangNotificationExtendedService extendedService = new
                YangNotificationManager(
                testYangSchemaNodeProvider.getDefaultYangSchemaRegistry());

        boolean isRegWithNotification = testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry()
                .verifyNotificationObject(IetfNetwork2Service.class);
        extendedService.registerAsListener(ietf);

        //Register should work.
        assertThat(true, is(isRegWithNotification));
        isRegWithNotification = testYangSchemaNodeProvider
                .getDefaultYangSchemaRegistry()
                .verifyNotificationObject(IetfNetwork2Service.class);

        //Re register should not happen
        assertThat(false, is(isRegWithNotification));
        extendedService.registerAsListener(ietf);
        testYangSchemaNodeProvider.unregisterAllService();
    }

}


