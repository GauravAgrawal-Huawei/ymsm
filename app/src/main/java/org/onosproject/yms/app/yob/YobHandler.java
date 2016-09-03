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

import static org.onosproject.yangutils.utils.io.impl.YangIoUtils.getCapitalCase;
import static org.onosproject.yms.app.ydt.AppType.YOB;
import static org.onosproject.yms.ydt.YdtType.MULTI_INSTANCE_NODE;

/**
 * Represents a YANG object builder handler to process the ydt content and build yang object.
 */
public abstract class YobHandler {

    private static final String FROMSTRING = "fromString";
    private static final String BUILD = "build";
    private static final String OPPARAM = "OpParam";
    private static final String DEFAULT = "Default";
    private static final String ADDTO = "addTo";
    private static final String VALUEOF = "valueOf";

    private ClassLoader registeredAppClassLoader;

    private static final Logger log = LoggerFactory.getLogger(YobHandler.class);

    /**
     * Creates a YANG builder object.
     *
     * @param ydtExtendedContext ydtExtendedContext is used to get application related information maintained in YDT
     * @param ydtRootNode        ydtRootNode is refers to module node
     * @param registry
     */
    public void createYangBuilderObject(YdtExtendedContext ydtExtendedContext, YdtExtendedContext ydtRootNode,
                                        YangSchemaRegistry registry) {
        String packageName;
        String className;
        Class<?> yangDefaultClass = null;
        Class<?>[] yangDefaultClassBuilder = null;
        Object builderObject = null;
        String qualifiedClassName;

        YangSchemaNode yangSchemaNode = ydtExtendedContext.getYangSchemaNode();
        if (yangSchemaNode == null) {
            //TODO
            return;
        }

        packageName = ydtExtendedContext.getYangSchemaNode().getJavaPackage();
        className = ydtExtendedContext.getYangSchemaNode().getJavaClassNameOrBuiltInType();
        className = getCapitalCase(className);

        if (ydtExtendedContext.equals(ydtRootNode)) {
            qualifiedClassName = packageName + "." + className + OPPARAM;
        } else {
            qualifiedClassName = packageName + "." + DEFAULT + className;
        }

        updateClassLoader(registry, qualifiedClassName, ydtExtendedContext);
        try {
            yangDefaultClass = getRegisteredAppClassLoader().loadClass(qualifiedClassName);
        } catch (ClassNotFoundException e) {
            log.error("YOB: failed to load class for class " + className);
        }
        try {
            if (yangDefaultClass != null) {
                yangDefaultClassBuilder = yangDefaultClass.getDeclaredClasses();
            }
            if (yangDefaultClassBuilder != null) {
                builderObject = yangDefaultClassBuilder[0].newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("YOB: failed to create an object for class " + className);
        }
        ydtExtendedContext.setAppInfo(YOB, builderObject);
    }

    /**
     * Sets the YANG built object in corresponding parent class method.
     *
     * @param ydtExtendedContext ydtExtendedContext is used to get application
     *                           related information maintained in YDT
     */
    public void setObjectInParent(YdtExtendedContext ydtExtendedContext) {
        Method method = null;
        Object builtDefaultObject = ydtExtendedContext.getAppInfo(YOB);
        YdtExtendedContext defaultYdtNodeParent = (YdtExtendedContext) ydtExtendedContext.getParent();

        if (defaultYdtNodeParent != null) {
            Object parentObject = defaultYdtNodeParent.getAppInfo(YOB);
            Class<?> yangParentClass = parentObject.getClass();
            try {
                Field leafName = yangParentClass.getDeclaredField(
                        ydtExtendedContext.getYangSchemaNode().getJavaAttributeName());
                if (ydtExtendedContext.getYdtType() == MULTI_INSTANCE_NODE) {
                    ParameterizedType genericListType = (ParameterizedType) leafName.getGenericType();
                    Class<?> genericListClass = (Class<?>) genericListType.getActualTypeArguments()[0];
                    method = yangParentClass
                            .getDeclaredMethod(ADDTO +
                                                       getCapitalCase(ydtExtendedContext
                                                                              .getYangSchemaNode()
                                                                              .getJavaAttributeName()),
                                               genericListClass);
                } else {
                    method = yangParentClass.getDeclaredMethod(
                            ydtExtendedContext.getYangSchemaNode().getJavaAttributeName(), leafName.getType());
                }
            } catch (NoSuchMethodException e) {
                log.error("YOB: failed to get method for class " + yangParentClass);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            try {
                if (method != null) {
                    method.invoke(parentObject, builtDefaultObject);
                }
            } catch (InvocationTargetException | IllegalAccessException e) {
                log.error("YOB: failed to invoke method for class " + yangParentClass);
            }
        }
        ydtExtendedContext.setAppInfo(YOB, builtDefaultObject);
    }

    /**
     * To build the object from builder method.
     *
     * @param ydtExtendedContext ydtExtendedContext is used to get application related
     *                           information maintained in YDT
     */
    public void buildObjectFromBuilder(YdtExtendedContext ydtExtendedContext) {
        Method method;
        Object defaultBuilderObject = ydtExtendedContext.getAppInfo(YOB);
        Object builtDefaultObject = null;
        Class<?> defaultBuilderClass = defaultBuilderObject.getClass();
        Class<?> defaultClass = defaultBuilderObject.getClass().getDeclaringClass();
        Object ydtContextOperationType = null;

        // Setting the value into OnosYangNodeOperationType from ydtcontext operation type.
        try {
            Class<?>[] innerClasses = defaultClass.getClasses();
            for (Class<?> innerEnumClass : innerClasses) {
                if (innerEnumClass.getSimpleName().equals("OnosYangNodeOperationType")) {
                    Method valueOfMethod = innerEnumClass.getDeclaredMethod(VALUEOF, String.class);
                    if (ydtExtendedContext.getYdtContextOperationType() != null) {
                        ydtContextOperationType =
                                valueOfMethod.invoke(null, ydtExtendedContext.getYdtContextOperationType().toString());
                    }
                }
            }
            Field onosYangNodeOperationType = defaultBuilderClass.getDeclaredField("onosYangNodeOperationType");
            onosYangNodeOperationType.setAccessible(true);
            onosYangNodeOperationType.set(defaultBuilderObject, ydtContextOperationType);
        } catch (NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.error("YOB: failed to set onosYangNodeOperationType");
        }
        // Invoking the build method to get built object from build method.
        try {
            method = defaultBuilderClass.getDeclaredMethod(BUILD);
            if (method != null) {
                builtDefaultObject = method.invoke(defaultBuilderObject);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.error("YOB: failed to get method for class " + defaultBuilderClass);
        }
        // The built object will be maintained in ydt context and same will be used while setting into parent method.
        ydtExtendedContext.setAppInfo(YOB, builtDefaultObject);
    }

    /**
     * This method is used to set data from string value in parent method.
     *
     * @param type                refers to YANG type
     * @param leafValue           leafValue argument is used to set the value in method
     * @param parentSetterMethod  Invokes the underlying method represented
     *                            by this parentSetterMethod
     * @param parentBuilderObject the parentBuilderObject is to invoke the underlying method
     * @param ydtExtendedContext  ydtExtendedContext is used to get application related
     *                            information maintained in YDT
     * @throws InvocationTargetException throws InvocationTargetException
     * @throws IllegalAccessException    throws IllegalAccessException
     * @throws NoSuchMethodException     throws NoSuchMethodException
     */
    public void setDataFromStringValue(YangType<?> type, String leafValue,
                                       Method parentSetterMethod, Object parentBuilderObject,
                                       YdtExtendedContext ydtExtendedContext)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        switch (type.getDataType()) {
            case INT8: {
                parentSetterMethod.invoke(parentBuilderObject, Byte.parseByte(leafValue));
                break;
            }
            case UINT8:
            case INT16: {
                parentSetterMethod.invoke(parentBuilderObject, Short.parseShort(leafValue));
                break;
            }
            case UINT16:
            case INT32: {
                parentSetterMethod.invoke(parentBuilderObject, Integer.parseInt(leafValue));
                break;
            }
            case UINT32:
            case INT64: {
                parentSetterMethod.invoke(parentBuilderObject, Long.parseLong(leafValue));
                break;
            }
            case UINT64: {
                parentSetterMethod.invoke(parentBuilderObject, new BigInteger(leafValue));
                break;
            }
            case EMPTY:
            case BOOLEAN: {
                parentSetterMethod.invoke(parentBuilderObject, Boolean.parseBoolean(leafValue));
                break;
            }
            case STRING: {
                parentSetterMethod.invoke(parentBuilderObject, leafValue);
                break;
            }
            case BINARY: {
                parentSetterMethod.invoke(parentBuilderObject, new YangBinary(leafValue));
                break;
            }
            case BITS: {
                //TODO
                break;
            }
            case DECIMAL64: {
                parentSetterMethod.invoke(parentBuilderObject, new BigDecimal(leafValue));
                break;
            }
            case DERIVED: {
                parseDerivedTypeInfo(ydtExtendedContext, parentSetterMethod, parentBuilderObject, leafValue, false);
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
                parseDerivedTypeInfo(ydtExtendedContext, parentSetterMethod, parentBuilderObject, leafValue, true);
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
     * @param leafValue           leafValue argument is used to set the value in method
     * @param parentSetterMethod  Invokes the underlying method represented
     *                            by this parentSetterMethod
     * @param parentBuilderObject the parentBuilderObject is to invoke the underlying method
     * @param ydtExtendedContext  ydtExtendedContext is used to get application related
     *                            information maintained in YDT
     * @param isEnum              isEnum parameter is used to check whether type is enum or derived
     *                            information maintained in YDT
     * @throws InvocationTargetException throws InvocationTargetException
     * @throws IllegalAccessException    throws IllegalAccessException
     * @throws NoSuchMethodException     throws NoSuchMethodException
     */
    public void parseDerivedTypeInfo(YdtExtendedContext ydtExtendedContext,
                                     Method parentSetterMethod,
                                     Object parentBuilderObject,
                                     String leafValue, boolean isEnum)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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
                log.error("YOB: failed to load constructor for class " + className);
            }
            if (childSetterClass != null) {
                childFromStringMethod = childSetterClass.getDeclaredMethod(FROMSTRING, String.class);
            }
        } else {
            if (childSetterClass != null) {
                childFromStringMethod = childSetterClass.getDeclaredMethod("of", String.class);
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
     * Returns registered application's class loader.
     *
     * @return registered application's class loader
     */
    private ClassLoader getRegisteredAppClassLoader() {
        return registeredAppClassLoader;
    }

    /**
     * Sets registered application's class loader.
     *
     * @param registeredAppClassLoader registered application's class loader
     */
    private void setRegisteredAppClassLoader(ClassLoader registeredAppClassLoader) {
        this.registeredAppClassLoader = registeredAppClassLoader;
    }

    /**
     * Updates class loader for all the classes.
     *
     * @param registry           YANG schema registry
     * @param context            YDT context
     * @param qualifiedClassName qualified class name
     */
    private void updateClassLoader(YangSchemaRegistry registry,
                                   String qualifiedClassName, YdtExtendedContext context) {

        YangSchemaNode yangSchemaNode = context.getYangSchemaNode();
        if (yangSchemaNode instanceof RpcNotificationContainer) {
            Class<?> regClass = registry.getRegisteredClass(yangSchemaNode, qualifiedClassName);
            setRegisteredAppClassLoader(regClass.getClassLoader());
        } else {
            YdtExtendedContext parent = (YdtExtendedContext) context.getParent();
            Object parentObj = parent.getAppInfo(YOB);
            setRegisteredAppClassLoader(parentObj.getClass().getClassLoader());
        }
    }
}