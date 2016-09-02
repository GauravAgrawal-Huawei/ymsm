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

import org.onosproject.yangutils.datamodel.YangLeaf;
import org.onosproject.yangutils.datamodel.YangType;
import org.onosproject.yangutils.datamodel.javadatamodel.JavaQualifiedTypeInfoContainer;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.onosproject.yms.app.ydt.AppType.YOB;

/**
 * Represents a single instance leaf node handler in YANG object builder.
 */
class YobSingleInstanceLeafHandler extends YobHandler {

    private static final Logger log = LoggerFactory.getLogger(YobSingleInstanceLeafHandler.class);

    @Override
    public void createYangBuilderObject(YdtExtendedContext ydtExtendedContext, YdtExtendedContext ydtRootNode){
        // For single instance leaf no need to create an object.
    }

    @Override
    public void buildObjectFromBuilder(YdtExtendedContext ydtExtendedContext) {
        // For single instance leaf no need to build object
    }

    @Override
    public void setObjectInParent(YdtExtendedContext ydtExtendedContext) {
        String         setterMethodNameInParent;
        Method         parentSetterMethod = null;
        String         leafValue = ydtExtendedContext.getValue();

        try {
            setterMethodNameInParent =  ydtExtendedContext.getYangSchemaNode().getJavaAttributeName();
            YdtExtendedContext parentNode = (YdtExtendedContext) ydtExtendedContext.getParent();
            Object parentBuilderObject = parentNode.getAppInfo(YOB);
            Class<?> parentBuilderClass = parentBuilderObject.getClass();
            Field leafName = parentBuilderClass.getDeclaredField(setterMethodNameInParent);
            parentSetterMethod = parentBuilderClass.getDeclaredMethod(setterMethodNameInParent, leafName.getType());
            JavaQualifiedTypeInfoContainer javaQualifiedTypeInfoContainer
                    = (JavaQualifiedTypeInfoContainer) ydtExtendedContext.getYangSchemaNode();
            YangLeaf yangLeaf = (YangLeaf) javaQualifiedTypeInfoContainer;
            YangType<?>  yangType = yangLeaf.getDataType();
            setDataFromStringValue(yangType, leafValue, parentSetterMethod,
                    parentBuilderObject, ydtExtendedContext);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e) {
            log.error("YOB: failed to invoke method for application " + parentSetterMethod);
        }
    }
}
