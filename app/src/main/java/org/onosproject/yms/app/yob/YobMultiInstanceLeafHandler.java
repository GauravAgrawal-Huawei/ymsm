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
import java.lang.reflect.ParameterizedType;
import java.util.Set;

import org.onosproject.yangutils.datamodel.YangLeafList;
import org.onosproject.yangutils.datamodel.YangType;
import org.onosproject.yangutils.datamodel.javadatamodel
        .JavaQualifiedTypeInfoContainer;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.onosproject.yangutils.utils.io.impl.YangIoUtils
        .getCapitalCase;
import static org.onosproject.yms.app.ydt.AppType.YOB;
import static org.onosproject.yms.app.yob.YobConstants.ADD_TO;
import static org.onosproject.yms.app.yob.YobConstants.FAIL_TO_INVOKE_METHOD;

/**
 * Represents a multi instance leaf node handler in YANG object builder.
 */
class YobMultiInstanceLeafHandler
        extends YobHandler {

    private static final Logger log =
            LoggerFactory.getLogger(YobMultiInstanceLeafHandler.class);

    @Override
    public void createYangBuilderObject(YdtExtendedContext curYdtNode,
                                        YdtExtendedContext rootYdtNode,
                                        YangSchemaRegistry registry) {
        // For multi instance leaf no need to create an object.
    }

    @Override
    public void buildObjectFromBuilder(YdtExtendedContext ydtNode,
                                       YdtExtendedContext ydtRootNode,
                                       YangSchemaRegistry schemaRegistry) {
        // For multi instance leaf no need to build object.
    }

    @Override
    public void setObjectInParent(YdtExtendedContext leafListYdtNode,
                                  YangSchemaRegistry schemaRegistry) {
        String setterInParent;
        Method parentSetterMethod;
        Object parentBuilderObject;
        Field leafName;
        ParameterizedType genericListType;
        JavaQualifiedTypeInfoContainer javaQualifiedType;
        Class<?> parentBuilderClass = null;
        YdtExtendedContext parentYdtNode = (YdtExtendedContext)
                leafListYdtNode.getParent();
        YobWorkBench parentYobWorkBench = (YobWorkBench)
                parentYdtNode.getAppInfo(YOB);
        Set<String> valueSet = leafListYdtNode.getValueSet();

        for (String value : valueSet) {
            try {
                setterInParent = leafListYdtNode.getYangSchemaNode()
                        .getJavaAttributeName();
                parentBuilderObject = parentYobWorkBench
                        .getParentBuilder(leafListYdtNode, schemaRegistry);
                parentBuilderClass = parentBuilderObject.getClass();
                leafName = parentBuilderClass.getDeclaredField(setterInParent);
                setterInParent = getCapitalCase(setterInParent);
                genericListType = (ParameterizedType) leafName.getGenericType();
                Class<?> genericListClass =
                        (Class<?>) genericListType.getActualTypeArguments()[0];
                parentSetterMethod = parentBuilderClass
                        .getDeclaredMethod(ADD_TO + setterInParent,
                                           genericListClass);
                javaQualifiedType =
                        (JavaQualifiedTypeInfoContainer) leafListYdtNode
                                .getYangSchemaNode();
                YangLeafList yangLeafList = (YangLeafList) javaQualifiedType;
                YangType<?> yangType = yangLeafList.getDataType();
                setDataFromStringValue(yangType, value, parentSetterMethod,
                                       parentBuilderObject, leafListYdtNode);
            } catch (NoSuchMethodException | InvocationTargetException
                    | IllegalAccessException | NoSuchFieldException e) {
                log.error(FAIL_TO_INVOKE_METHOD + parentBuilderClass.getName());
            }
        }
    }
}
