/*
 * Copyright 2015-present Open Networking Laboratory
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

package org.onosproject.yms.app.ynh;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.onosproject.event.Event;
import org.onosproject.event.EventListener;
import org.onosproject.event.ListenerRegistry;
import org.onosproject.event.ListenerService;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.onosproject.yms.app.ytb.DefaultYangTreeBuilder;
import org.onosproject.yms.app.ytb.YangTreeBuilder;
import org.onosproject.yms.ydt.YdtContext;
import org.onosproject.yms.ynh.YangNotification;
import org.onosproject.yms.ynh.YangNotificationEvent;
import org.onosproject.yms.ynh.YangNotificationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.onlab.util.Tools.groupedThreads;

/**
 * Provides implementation of YANG notification manager.
 */
public class YangNotificationManager extends ListenerRegistry<YangNotificationEvent, YangNotificationListener>
        implements YangNotificationExtendedService {

    private ExecutorService eventHandlingExecutor;

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * YANG notification abstract listener. This listener will listens
     * abstractly to all the notification from the application to which it
     * has subscribed.
     */
    YnhAbstractListener ynhAbstractListener;

    /**
     * Maintains schema registry.
     */
    YangSchemaRegistry schemaRegistry;

    /**
     * Creates an instance of YANG notification manager.
     *
     * @param schemaRegistry YANG schema registry
     */
    public YangNotificationManager(YangSchemaRegistry schemaRegistry) {
        ynhAbstractListener = new YnhAbstractListener();
        this.schemaRegistry = schemaRegistry;
        eventHandlingExecutor = Executors.newSingleThreadExecutor(
                groupedThreads("onos/apps/yang-management-system/yang-notification-handler", "event-handler", log));
    }

    /**
     * Returns YANG schema registry.
     *
     * @return YANG schema registry
     */
    public YangSchemaRegistry getSchemaRegistry() {
        return schemaRegistry;
    }

    /**
     * Returns YANG notification handler abstract listener.
     *
     * @return YANG notification handler abstract listener
     */
    public YnhAbstractListener getYnhAbstractListener() {
        return ynhAbstractListener;
    }

    @Override
    public void registerAsListener(ListenerService appObject) {
        appObject.addListener(getYnhAbstractListener());
    }

    @Override
    public YangNotification getFilteredSubject(YangNotification notificationSubject,
                                               YangNotification notificationFilter) {
        return null;
        // TODO
    }

    /**
     * YANG notification handler's abstract listener, listening for events
     * from application(s).
     */
    private class YnhAbstractListener implements EventListener {

        @Override
        public void event(Event event) {
            eventHandlingExecutor.execute(() -> {
                try {
                    // Create an instance of YANG tree builder.
                    YangTreeBuilder yangTreeBuilder = new DefaultYangTreeBuilder();
                    /*
                     * Obtain YANG data tree corresponding to notification with
                     * logical root node as yangnotification, followed by module/
                     * sub-module, followed by notification.
                     */
                    YdtContext notificationSchemaNode = yangTreeBuilder.getYdtForNotification(event,
                            "yangnotification", getSchemaRegistry());
                    // Create YANG notification
                    YangNotification yangNotification = new YangNotification(notificationSchemaNode);
                    // Send notification to registered protocols.
                    process(new YangNotificationEvent(yangNotification));
                } catch (Exception e) {
                    log.warn("Failed to process {}", event, e);
                }
            });
        }
    }
}
