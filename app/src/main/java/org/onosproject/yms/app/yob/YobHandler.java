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


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.onosproject.yangutils.datamodel.RpcNotificationContainer;
import org.onosproject.yangutils.datamodel.YangBinary;
import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangType;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.onosproject.yangutils.utils.io.impl.YangIoUtils
        .getCapitalCase;
import static org.onosproject.yms.app.ydt.AppType.YOB;
import static org.onosproject.yms.app.yob.YobBuilderContainer
        .getQualifiedDefaultClassName;
import static org.onosproject.yms.ydt.YdtType.MULTI_INSTANCE_NODE;

/**
 * Represents a YANG object builder handler to process the ydt content and
 * build yang object.
 */
public abstract class YobHandler {

    private static final String FROMSTRING = "fromString";
    private static final String BUILD = "build";
    private static final String OPPARAM = "OpParam";
    private static final String DEFAULT = "Default";
    private static final String ADDTO = "addTo";
    private static final String VALUEOF = "valueOf";

    private static final Logger log = LoggerFactory.getLogger(YobHandler.class);

    /**
     * Creates a YANG builder object.
     *
     * @param curYdtNode  ydtExtendedContext is used to get
     *                    application related information maintained
     *                    in YDT
     * @param rootYdtNode ydtRootNode is refers to module node
     * @param registry
     */
    public void createYangBuilderObject(YdtExtendedContext curYdtNode,
                                        YdtExtendedContext rootYdtNode,
                                        YangSchemaRegistry registry) {
        String packageName;
        String className;
        Class<?> yangDefaultClass = null;
        Class<?>[] yangDefaultClassBuilder = null;
        Object builderObject = null;


        String qualifiedClassName = getQualifiedDefaultClassName(curYdtNode
                                                                         .getYangSchemaNode());

        ClassLoader classLoader = getClassLoader(registry,
                                                 qualifiedClassName,
                                                 curYdtNode);

        String setterMethodName = null;
        if (curYdtNode != rootYdtNode) {
            setterMethodName = curYdtNode.getYangSchemaNode()
                    .getJavaAttributeName();
        }

        builderObject = new YobBuilderContainer(
                curYdtNode.getYangSchemaNode(), classLoader,
                qualifiedClassName, setterMethodName);

        curYdtNode.setAppInfo(YOB, builderObject);
    }

    /**
     * Sets the YANG built object in corresponding parent class method.
     *
     * @param ydtNode        ydtExtendedContext is used to get application
     *                       related information maintained in YDT
     * @param schemaRegistry
     */
    public void setObjectInParent(YdtExtendedContext ydtNode,
                                  YangSchemaRegistry schemaRegistry) {
        Method method = null;
        YobBuilderContainer yobBuilderContainer
                = (YobBuilderContainer) ydtNode.getAppInfo(YOB);
        Object objectToSetinParent = yobBuilderContainer
                .getBuilderOrBuiltObjectOfScheam().getBuiltObject();

        YdtExtendedContext parentYdtNode =
                (YdtExtendedContext) ydtNode.getParent();

        if (parentYdtNode != null) {
            YobBuilderContainer parentYobBuilderContainer =
                    (YobBuilderContainer) parentYdtNode.getAppInfo(YOB);
            Object parentBuilderObject = parentYobBuilderContainer
                    .getBuilderOrBuiltObjectOfScheam().getBuilderObject();

            Class<?> parentBuilderClass = parentBuilderObject.getClass();
            try {
                Field fieldName = parentBuilderClass.getDeclaredField(
                        ydtNode.getYangSchemaNode()
                                .getJavaAttributeName());
                if (ydtNode.getYdtType() == MULTI_INSTANCE_NODE) {
                    ParameterizedType genericListType =
                            (ParameterizedType) fieldName.getGenericType();
                    Class<?> genericListClass = (Class<?>) genericListType
                            .getActualTypeArguments()[0];
                    method = parentBuilderClass
                            .getDeclaredMethod(ADDTO +
                                                       getCapitalCase(
                                                               ydtNode
                                                                       .getYangSchemaNode()
                                                                       .getJavaAttributeName()),
                                               genericListClass);
                } else {
                    method = parentBuilderClass.getDeclaredMethod(
                            ydtNode.getYangSchemaNode()
                                    .getJavaAttributeName(),
                            fieldName.getType());
                }
            } catch (NoSuchMethodException e) {
                log.error("YOB: failed to get method for class " +
                                  parentBuilderClass);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            try {
                if (method != null) {
                    method.invoke(parentBuilderObject, objectToSetinParent);
                }
            } catch (InvocationTargetException | IllegalAccessException e) {
                log.error("YOB: failed to invoke method for class " +
                                  parentBuilderClass);
            }
        }
        ydtNode.setAppInfo(YOB, yobBuilderContainer);
    }

    /**
     * To build the object from builder method.
     *
     * @param ydtNode ydtExtendedContext is used to get
     *                application related
     *                information maintained in YDT
     */
    public void buildObjectFromBuilder(YdtExtendedContext ydtNode) {
        Method method;
        YobBuilderContainer yobBuilderContainer = (YobBuilderContainer) ydtNode
                .getAppInfo(YOB);

            Object builderObject = yobBuilderContainer
                .getBuilderOrBuiltObjectOfScheam().getBuilderObject();
        Object builtObject = null;
        Class<?> defaultBuilderClass = yobBuilderContainer
                .getBuilderOrBuiltObjectOfScheam().yangDefaultClassBuilder[0];
        Class<?> defaultClass = yobBuilderContainer
                .getBuilderOrBuiltObjectOfScheam().yangDefaultClass;
        Object ydtContextOperationType = null;

        // Setting the value into OnosYangNodeOperationType from ydtcontext
        // operation type.
        try {
            Class<?>[] innerClasses = defaultClass.getClasses();
            for (Class<?> innerEnumClass : innerClasses) {
                if (innerEnumClass.getSimpleName()
                        .equals("OnosYangNodeOperationType")) {
                    Method valueOfMethod = innerEnumClass
                            .getDeclaredMethod(VALUEOF, String.class);
                    if (ydtNode.getYdtContextOperationType() !=
                            null) {
                        ydtContextOperationType =
                                valueOfMethod.invoke(null, ydtNode
                                        .getYdtContextOperationType()
                                        .toString());
                    }
                }
            }
            Field onosYangNodeOperationType = defaultBuilderClass
                    .getDeclaredField("onosYangNodeOperationType");
            onosYangNodeOperationType.setAccessible(true);
            onosYangNodeOperationType
                    .set(builderObject, ydtContextOperationType);
        } catch (NoSuchFieldException | NoSuchMethodException |
                InvocationTargetException | IllegalAccessException e) {
            log.error("YOB: failed to set onosYangNodeOperationType");
        }
        // Invoking the build method to get built object from build method.
        try {
            method = defaultBuilderClass.getDeclaredMethod(BUILD);
            if (method != null) {
                builtObject = method.invoke(builderObject);
            }
        } catch (NoSuchMethodException | InvocationTargetException |
                IllegalAccessException e) {
            log.error("YOB: failed to get method for class " +
                              defaultBuilderClass);
        }
        // The built object will be maintained in ydt context and same will
        // be used while setting into parent method.
        yobBuilderContainer.getBuilderOrBuiltObjectOfScheam()
                .setBuiltObject(builtObject);
    }

    /**
     * This method is used to set data from string value in parent method.
     *
     * @param type                refers to YANG type
     * @param leafValue           leafValue argument is used to set the value
     *                            in method
     * @param parentSetterMethod  Invokes the underlying method represented
     *                            by this parentSetterMethod
     * @param parentBuilderObject the parentBuilderObject is to invoke the
     *                            underlying method
     * @param ydtExtendedContext  ydtExtendedContext is used to get
     *                            application related
     *                            information maintained in YDT
     * @throws InvocationTargetException throws InvocationTargetException
     * @throws IllegalAccessException    throws IllegalAccessException
     * @throws NoSuchMethodException     throws NoSuchMethodException
     */
    public void setDataFromStringValue(YangType<?> type, String leafValue,
                                       Method parentSetterMethod,
                                       Object parentBuilderObject,
                                       YdtExtendedContext ydtExtendedContext)
            throws InvocationTargetException, IllegalAccessException,
                   NoSuchMethodException {
        switch (type.getDataType()) {
            case INT8: {
                parentSetterMethod
                        .invoke(parentBuilderObject, Byte.parseByte(leafValue));
                break;
            }
            case UINT8:
            case INT16: {
                parentSetterMethod.invoke(parentBuilderObject,
                                          Short.parseShort(leafValue));
                break;
            }
            case UINT16:
            case INT32: {
                parentSetterMethod.invoke(parentBuilderObject,
                                          Integer.parseInt(leafValue));
                break;
            }
            case UINT32:
            case INT64: {
                parentSetterMethod
                        .invoke(parentBuilderObject, Long.parseLong(leafValue));
                break;
            }
            case UINT64: {
                parentSetterMethod
                        .invoke(parentBuilderObject, new BigInteger(leafValue));
                break;
            }
            case EMPTY:
            case BOOLEAN: {
                parentSetterMethod.invoke(parentBuilderObject,
                                          Boolean.parseBoolean(leafValue));
                break;
            }
            case STRING: {
                parentSetterMethod.invoke(parentBuilderObject, leafValue);
                break;
            }
            case BINARY: {
                parentSetterMethod
                        .invoke(parentBuilderObject, new YangBinary(leafValue));
                break;
            }
            case BITS: {
                //TODO
                break;
            }
            case DECIMAL64: {
                parentSetterMethod
                        .invoke(parentBuilderObject, new BigDecimal(leafValue));
                break;
            }
            case DERIVED: {
                parseDerivedTypeInfo(ydtExtendedContext, parentSetterMethod,
                                     parentBuilderObject, leafValue, false);
                break;
            }
            case UNION: {
                // TODO
                break;
            }
            case LEAFREF: {
                // TODO
                break;
            }
            case ENUMERATION: {
                parseDerivedTypeInfo(ydtExtendedContext, parentSetterMethod,
                                     parentBuilderObject, leafValue, true);
                break;
            }
            default: {
                log.error("YOB: Given data type is not supported.");
            }
        }
    }

    /**
     * To set data into parent setter method from string value for derived type.
     *
     * @param leafValue           leafValue argument is used to set the value
     *                            in method
     * @param parentSetterMethod  Invokes the underlying method represented
     *                            by this parentSetterMethod
     * @param parentBuilderObject the parentBuilderObject is to invoke the
     *                            underlying method
     * @param ydtExtendedContext  ydtExtendedContext is used to get
     *                            application related
     *                            information maintained in YDT
     * @param isEnum              isEnum parameter is used to check whether
     *                            type is enum or derived
     *                            information maintained in YDT
     * @throws InvocationTargetException throws InvocationTargetException
     * @throws IllegalAccessException    throws IllegalAccessException
     * @throws NoSuchMethodException     throws NoSuchMethodException
     */
    public void parseDerivedTypeInfo(YdtExtendedContext ydtExtendedContext,
                                     Method parentSetterMethod,
                                     Object parentBuilderObject,
                                     String leafValue, boolean isEnum)
            throws InvocationTargetException, IllegalAccessException,
                   NoSuchMethodException {
        String packageName;
        String className;
        Class<?> childSetterClass = null;
        Constructor<?> childConstructor = null;
        Object childValue = null;
        Object childObject = null;
        Method childFromStringMethod = null;

        YangSchemaNode yangJavaModule = ydtExtendedContext.getYangSchemaNode();
        packageName = yangJavaModule.getJavaPackage();
        className = yangJavaModule.getJavaClassNameOrBuiltInType();
        className = getCapitalCase(className);
        try {
            childSetterClass = Class.forName(packageName + "." + className);
        } catch (ClassNotFoundException e) {
            log.error("YOB: failed to load class for class " + className);
        }
        if (!isEnum) {

            if (childSetterClass != null) {
                childConstructor = childSetterClass.getDeclaredConstructor();
            }

            if (childConstructor != null) {
                childConstructor.setAccessible(true);
            }
            try {
                if (childConstructor != null) {
                    childObject = childConstructor.newInstance();
                }
            } catch (InstantiationException e) {
                log.error("YOB: failed to load constructor for class " +
                                  className);
            }
            if (childSetterClass != null) {
                childFromStringMethod = childSetterClass
                        .getDeclaredMethod(FROMSTRING, String.class);
            }
        } else {
            if (childSetterClass != null) {
                childFromStringMethod =
                        childSetterClass.getDeclaredMethod("of", String.class);
            }
            //leafValue = JavaIdentifierSyntax.getEnumJavaAttribute(leafValue);
            //leafValue = leafValue.toUpperCase();
        }
        if (childFromStringMethod != null) {
            childValue = childFromStringMethod.invoke(childObject, leafValue);
        }

        parentSetterMethod.invoke(parentBuilderObject, childValue);
    }


    /**
     * Updates class loader for all the classes.
     *
     * @param registry           YANG schema registry
     * @param context            YDT context
     * @param qualifiedClassName qualified class name
     * @return current class loader
     */
    private ClassLoader getClassLoader(YangSchemaRegistry registry,
                                       String qualifiedClassName,
                                       YdtExtendedContext context) {

        YangSchemaNode yangSchemaNode = context.getYangSchemaNode();
        if (yangSchemaNode instanceof RpcNotificationContainer) {
            Class<?> regClass = registry.getRegisteredClass(yangSchemaNode,
                                                            qualifiedClassName);
            return regClass.getClassLoader();
        } else {

            YdtExtendedContext parent =
                    (YdtExtendedContext) context.getParent();
            YobBuilderContainer
                    parentBuilderContainer = (YobBuilderContainer) parent
                    .getAppInfo(YOB);
            Object parentObj = parentBuilderContainer
                    .getParentBuilderObjectFromAncestor(context, registry);
            return parentObj.getClass().getClassLoader();
        }
    }
}
