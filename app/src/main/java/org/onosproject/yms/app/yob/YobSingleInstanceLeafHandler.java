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

package org.onosproject.yms.app.yob;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.onosproject.yangutils.datamodel.YangLeaf;
import org.onosproject.yangutils.datamodel.YangType;
import org.onosproject.yangutils.datamodel.javadatamodel
        .JavaQualifiedTypeInfoContainer;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.onosproject.yms.app.ydt.AppType.YOB;

/**
 * Represents a single instance leaf node handler in YANG object builder.
 */
class YobSingleInstanceLeafHandler
        extends YobHandler {

    private static final Logger log =
            LoggerFactory.getLogger(YobSingleInstanceLeafHandler.class);

    @Override
    public void createYangBuilderObject(YdtExtendedContext curYdtNode,
                                        YdtExtendedContext rootYdtNode,
                                        YangSchemaRegistry registry) {
        // For single instance leaf no need to create an object.
    }

    @Override
    public void buildObjectFromBuilder(YdtExtendedContext ydtNode,
                                       YdtExtendedContext ydtRootNode,
                                       YangSchemaRegistry schemaRegistry) {
        // For single instance leaf no need to build object
    }

    @Override
    public void setObjectInParent(YdtExtendedContext leafNode,
                                  YangSchemaRegistry schemaRegistry) {
        String setterMethodNameInParent;
        Method parentSetterMethod = null;
        YdtExtendedContext parentYdtNode = (YdtExtendedContext)
                leafNode.getParent();
        YobBuilderContainer parentYobBuilderContainer = (YobBuilderContainer)
                parentYdtNode.getAppInfo(YOB);
        String leafValue = leafNode.getValue();

        try {
            setterMethodNameInParent = leafNode.getYangSchemaNode()
                    .getJavaAttributeName();
            Object parentBuilderObject = parentYobBuilderContainer
                    .getParentBuilderObjectFromAncestor(leafNode,
                                                        schemaRegistry);
            Class<?> parentBuilderClass = parentBuilderObject.getClass();
            Field leafName = parentBuilderClass
                    .getDeclaredField(setterMethodNameInParent);
            parentSetterMethod = parentBuilderClass
                    .getDeclaredMethod(setterMethodNameInParent,
                                       leafName.getType());
            JavaQualifiedTypeInfoContainer javaQualifiedTypeInfoContainer
                    = (JavaQualifiedTypeInfoContainer) leafNode
                    .getYangSchemaNode();
            YangLeaf yangLeaf = (YangLeaf) javaQualifiedTypeInfoContainer;
            YangType<?> yangType = yangLeaf.getDataType();
            setDataFromStringValue(yangType, leafValue, parentSetterMethod,
                                   parentBuilderObject, leafNode);
        } catch (NoSuchMethodException | InvocationTargetException |
                IllegalAccessException | NoSuchFieldException e) {
            log.error("YOB: failed to invoke method for application " +
                              parentSetterMethod);
        }
    }
}
