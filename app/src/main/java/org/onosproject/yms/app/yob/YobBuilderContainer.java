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

import org.onosproject.yangutils.datamodel.YangSchemaNode;
import org.onosproject.yangutils.datamodel.YangSchemaNodeContextInfo;
import org.onosproject.yangutils.datamodel.YangSchemaNodeIdentifier;
import org.onosproject.yangutils.datamodel.YangNode;
import org.onosproject.yangutils.datamodel.RpcNotificationContainer;
import org.onosproject.yangutils.datamodel.exceptions.DataModelException;
import org.onosproject.yms.app.ydt.YdtExtendedContext;
import org.onosproject.yms.app.yob.exception.YobExceptions;
import org.onosproject.yms.app.ysr.YangSchemaRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import static org.onosproject.yangutils.datamodel.YangSchemaNodeType.YANG_AUGMENT_NODE;
import static org.onosproject.yangutils.datamodel.YangSchemaNodeType.YANG_CHOICE_NODE;
import static org.onosproject.yangutils.utils.io.impl.YangIoUtils.getCapitalCase;
import static org.onosproject.yms.app.ydt.AppType.YOB;
import static org.onosproject.yms.app.yob.YobConstants.DEFAULT;
import static org.onosproject.yms.app.yob.YobConstants.OPPARAM;
import static org.onosproject.yms.app.yob.YobConstants.ADDTO;
import static org.onosproject.yms.app.yob.YobConstants.VALUEOF;
import static org.onosproject.yms.app.yob.YobConstants.BUILD;
import static org.onosproject.yms.app.yob.YobConstants.ONOSOPERATIONTYPE;
import static org.onosproject.yms.app.yob.YobConstants.OPERATIONTYPE;

import static org.onosproject.yms.ydt.YdtType.MULTI_INSTANCE_NODE;

/**
 * Represents the YANG object builder's work bench corresponding to a YANG data
 * tree node.
 */
public class YobBuilderContainer {

    private static final Logger log
            = LoggerFactory.getLogger(YobBuilderContainer.class);

    /**
     * Class loader to be used to load the class.
     */
    private ClassLoader registeredAppClassLoader;

    /**
     * Map of the non schema descendant builders.
     */
    private Map<YangSchemaNodeIdentifier, YobBuilderContainer>
            builderContainerMap = new HashMap<>();

    /**
     * Reference for data-model schema node.
     */
    private YangSchemaNode yangSchemaNode;

    /**
     * builder object or the built object corresponding to the current schema
     * node.
     */
    private YobBuilderOrBuiltObject builderOrBuiltObjectOfScheam;

    private String setterMethodNameInParent;

    /**
     * Returns the builder container with the mapping schema being initialized.
     *
     * @param yangSchemaNode mapping schema node
     */
    public YobBuilderContainer(YangSchemaNode yangSchemaNode,
                               ClassLoader registeredAppClassLoader,
                               String qualifiedClassName,
                               String setterMethodNameInParent) {
        this.yangSchemaNode = yangSchemaNode;
        setRegisteredAppClassLoader(registeredAppClassLoader);
        this.setterMethodNameInParent = setterMethodNameInParent;

        setBuilderOrBuiltObjectOfScheam(
                new YobBuilderOrBuiltObject(qualifiedClassName,
                                            getRegisteredAppClassLoader()));
    }

    /**
     * Returns the mapping schema node.
     *
     * @return mapping schema node
     */
    public YangSchemaNode getYangSchemaNode() {
        return yangSchemaNode;
    }


    /**
     * Returns the builder object or the built object corresponding to the
     * current schema node.
     *
     * @return builder or built object
     */
    public YobBuilderOrBuiltObject getBuilderOrBuiltObjectOfScheam() {
        return builderOrBuiltObjectOfScheam;
    }

    /**
     * Sets the builder object or the built object corresponding to the
     * current schema node.
     *
     * @param builderOrBuiltObjectOfScheam builder or built object
     */
    public void setBuilderOrBuiltObjectOfScheam(
            YobBuilderOrBuiltObject builderOrBuiltObjectOfScheam) {
        this.builderOrBuiltObjectOfScheam = builderOrBuiltObjectOfScheam;
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
    private void setRegisteredAppClassLoader(
            ClassLoader registeredAppClassLoader) {
        this.registeredAppClassLoader = registeredAppClassLoader;
    }

    /**
     * Returns the parent builder object in which the child object can be set.
     *
     * @param childYdtNode child YDT node
     * @param registry     schema registry
     * @return parent builder object
     */
    public Object getParentBuilderObjectFromAncestor(
            YdtExtendedContext childYdtNode,
            YangSchemaRegistry registry) {

        /*
         * Descendent schema node for whom the builder is required.
         */
        YangSchemaNodeIdentifier descendentSchemaIdentifier = childYdtNode
                .getYangSchemaNode().getYangSchemaNodeIdentifier();

        //Current builder container
        YobBuilderContainer curBuilderContainer = this;

        //Current Schema node context
        YangSchemaNodeContextInfo descendentSchemaContext = null;
        do {

            try {
                //Find the new schema context node.
                descendentSchemaContext =
                        curBuilderContainer.getYangSchemaNode()
                                .getChildSchema(descendentSchemaIdentifier);
            } catch (DataModelException e) {
                throw new YobExceptions(getYangSchemaNode().getName()
                                                + " does not have child " +
                                                descendentSchemaIdentifier
                                                        .getName());
            }

            //If the descendent schema node is in switched context
            if (descendentSchemaContext.getContextSwitchedNode() != null) {

                //check if the descendent builder container is already available
                YobBuilderContainer descendentBuilderContainer =
                        curBuilderContainer.builderContainerMap.get(
                                descendentSchemaIdentifier);
                if (descendentBuilderContainer == null) {
                    YobBuilderContainer newBuilderContainer =
                            getNewDescendentBuilderContainer(
                                    descendentSchemaContext,
                                    descendentSchemaIdentifier,
                                    curBuilderContainer, registry);
                    //TODO: verify that the descendentSchemaContext is in case, it shoudl not be in choice
                    curBuilderContainer.builderContainerMap.put(descendentSchemaIdentifier,
                            newBuilderContainer);
                    curBuilderContainer = newBuilderContainer;
                } else {
                    curBuilderContainer = descendentBuilderContainer;
                }
            }

        } while (descendentSchemaContext.getContextSwitchedNode() != null);

        return curBuilderContainer.getBuilderOrBuiltObjectOfScheam()
                .getBuilderObject();
    }

    /**
     * Creates a new builder container object corresponding to a context
     * switch schema node.
     *
     * @param descendentSchemaContext    schema context of immediate descedent
     * @param descendentSchemaIdentifier final node whose parent builder is
     *                                   requried
     * @param curBuilderContainer        current context bulter container
     * @param registry                   schema registry
     * @return new builder container object corresponding to a context
     * switch schema node.
     */
    private YobBuilderContainer getNewDescendentBuilderContainer(
            YangSchemaNodeContextInfo descendentSchemaContext,
            YangSchemaNodeIdentifier descendentSchemaIdentifier,
            YobBuilderContainer curBuilderContainer,
            YangSchemaRegistry registry) {

         /*This is the first descendent trying to set its object in the
         current context. */
        String setterMethodNameInParent = descendentSchemaContext
                .getContextSwitchedNode().getJavaAttributeName();

        /* If current switched context is choice, then case class needs to be
         used. */
        if (descendentSchemaContext.getContextSwitchedNode()
                .getYangSchemaNodeType() == YANG_CHOICE_NODE) {
            try {
                descendentSchemaContext =
                        descendentSchemaContext
                                .getContextSwitchedNode()
                                .getChildSchema(
                                        descendentSchemaIdentifier);
            } catch (DataModelException e) {
                throw new YobExceptions(
                        getYangSchemaNode().getName()
                                + " does not have child " +
                                descendentSchemaIdentifier
                                        .getName());
            }
        }

        ClassLoader newClassesLoader
                = getContextSwitchClassLoader(curBuilderContainer
                                                      .getRegisteredAppClassLoader(),
                                              descendentSchemaContext,
                                              registry);

        return new YobBuilderContainer(descendentSchemaContext
                                               .getContextSwitchedNode(),
                                       newClassesLoader,
                                       getQualifiedDefaultClassName(
                                               descendentSchemaContext
                                                       .getSchemaNode()),
                                       setterMethodNameInParent);
    }

    /**
     * Returns the qualified default / op param class.
     *
     * @param schemaNode schema node of the required class
     * @return qualified default / op param class name
     */
    static String getQualifiedDefaultClassName(YangSchemaNode schemaNode) {
        String packageName = schemaNode.getJavaPackage();
        String className =
                schemaNode.getJavaClassNameOrBuiltInType();
        className = getCapitalCase(className);
        String qualifiedClassName;
        boolean isRootNode = schemaNode instanceof RpcNotificationContainer;
        if (isRootNode) {
            qualifiedClassName =
                    packageName + "." + className + OPPARAM;
        } else {
            qualifiedClassName =
                    packageName + "." + DEFAULT + className;
        }

        return qualifiedClassName;
    }

    /**
     * Returns the class loader to be used for the switched context schema node.
     *
     * @param currentClassLoader current context class loader
     * @param switchedContext    switched context
     * @param registry           schema registry
     * @return class loader to be used for the switched context schema node
     */
    private ClassLoader getContextSwitchClassLoader(
            ClassLoader currentClassLoader,
            YangSchemaNodeContextInfo switchedContext,
            YangSchemaRegistry registry) {

        if (switchedContext.getSchemaNode().getYangSchemaNodeType() ==
                YANG_AUGMENT_NODE) {
            YangSchemaNode augmentSchemaNode = switchedContext.getSchemaNode();
            YangSchemaNode parentSchemaNode =
                    ((YangNode) augmentSchemaNode).getParent();

            Class<?> regClass = registry.getRegisteredClass(parentSchemaNode,
                                                            getCapitalCase(
                                                                    parentSchemaNode
                                                                            .getJavaClassNameOrBuiltInType()));
            return regClass.getClassLoader();
        }

        return currentClassLoader;
    }

    public String getSetterMethodNameInParent() {
        return setterMethodNameInParent;
    }

    public void invokebuildObjectFromBuilder(YdtExtendedContext ydtNode,
                                             YdtExtendedContext ydtRootNode,
                                             YobBuilderContainer yobBuilderContainer) {
        Method method;


        Object builderObject = getBuilderOrBuiltObjectOfScheam().getBuilderObject();
        Object builtObject = null;
        Class<?> defaultBuilderClass = getBuilderOrBuiltObjectOfScheam().yangDefaultClassBuilder[0];
        Class<?> defaultClass = getBuilderOrBuiltObjectOfScheam().yangDefaultClass;
        Object ydtContextOperationType = null;

        // Setting the value into OnosYangNodeOperationType from ydtcontext
        // operation type.
        try {
            Class<?>[] innerClasses = defaultClass.getClasses();
            for (Class<?> innerEnumClass : innerClasses) {
                if (innerEnumClass.getSimpleName()
                        .equals(ONOSOPERATIONTYPE)) {
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
                    .getDeclaredField(OPERATIONTYPE);
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
        getBuilderOrBuiltObjectOfScheam().setBuiltObject(builtObject);

        // The current ydt context node and root node are same then return.
        if (!ydtNode.equals(ydtRootNode)) {
            invokeSetObjectInParent(ydtNode,
                    yobBuilderContainer);
        }
    }

    /**
     * Sets the YANG built object in corresponding parent class method.
     *
     * @param ydtNode        ydtExtendedContext is used to get application
     *                       related information maintained in YDT
     * @param yobBuilderContainer yobBuilderContainer
     */

    public void invokeSetObjectInParent(YdtExtendedContext ydtNode,
                                        YobBuilderContainer yobBuilderContainer) {
        Class<?>  classType = null;
        Method    method;
        Field fieldName;
        ParameterizedType genericListType = null;

        Object objectToSetinParent = yobBuilderContainer.getBuilderOrBuiltObjectOfScheam().getBuiltObject();

        YdtExtendedContext parentYdtNode =
                (YdtExtendedContext) ydtNode.getParent();
        if (parentYdtNode != null) {
            YobBuilderContainer parentYobBuilderContainer =
                    (YobBuilderContainer) parentYdtNode.getAppInfo(YOB);
            Object parentBuilderObject = parentYobBuilderContainer.
                    getBuilderOrBuiltObjectOfScheam().getBuilderObject();

            Class<?> parentBuilderClass = parentBuilderObject.getClass();

            try {
                fieldName = parentBuilderClass.getDeclaredField(yobBuilderContainer.getSetterMethodNameInParent());
                if (fieldName != null) {
                    classType = fieldName.getType();
                }

                if (ydtNode.getYdtType() == MULTI_INSTANCE_NODE) {
                    if (fieldName != null) {
                        genericListType =
                                (ParameterizedType) fieldName.getGenericType();
                    }
                    if (genericListType != null) {
                        classType = (Class<?>) genericListType.getActualTypeArguments()[0];
                    }
                    method  = parentBuilderClass
                            .getDeclaredMethod(ADDTO + getCapitalCase(yobBuilderContainer.getSetterMethodNameInParent()),
                                    classType);
                } else {
                    method  = parentBuilderClass
                            .getDeclaredMethod(yobBuilderContainer.getSetterMethodNameInParent(),
                                    classType);
                }

                if (method != null) {
                    method.invoke(parentBuilderObject, objectToSetinParent);
                }
            } catch (NoSuchFieldException e) {
                log.error("YOB: failed to get field for class " +
                        parentBuilderClass);
            } catch (NoSuchMethodException e) {
                log.error("YOB: failed to get method for class " +
                        parentBuilderClass);
            } catch (InvocationTargetException | IllegalAccessException e) {
                log.error("YOB: failed to invoke method for class " +
                        parentBuilderObject.getClass());
            }
        }
        ydtNode.setAppInfo(YOB, yobBuilderContainer);
    }
}
