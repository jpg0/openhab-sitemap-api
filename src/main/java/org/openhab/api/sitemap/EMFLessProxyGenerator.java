/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.openhab.api.sitemap;

import org.apache.commons.lang.ClassUtils;
import org.eclipse.emf.common.util.DelegatingEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.smarthome.model.sitemap.sitemap.*;
import org.openhab.api.sitemap.model.ELessObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Class which reimplements interfaces adding stub EMF functionality to interfaces which don't normally support it.
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public class EMFLessProxyGenerator {
    //todo: get from osgi
    private final Logger logger = LoggerFactory.getLogger(EMFLessProxyGenerator.class);

    private ClassLoader classLoader = getClass().getClassLoader();
    private static final Class<?> EOBJECT_CLASS = EObject.class;
    private static final List<Class<?>> EOBJECT_INTERFACES = ClassUtils.getAllInterfaces(EOBJECT_CLASS);

    private static final Package OH_SITEMAP_OBJECTS_PACKAGE = org.eclipse.smarthome.model.sitemap.sitemap.Sitemap.class.getPackage();

    public <E extends EObject> E proxyFor(ELessObject<E> eLess, Class<E> proxyTargetClass) {
        return proxyFor(eLess, proxyTargetClass, null);
    }

    public <E extends EObject> E proxyFor(ELessObject<E> eLess, Class<E> proxyTargetClass, E parentProxy) {
        List<Class<?>> interfaces = ClassUtils.getAllInterfaces(eLess.getClass());
        //we need to add all the interfaces this class implements
        interfaces.add(proxyTargetClass);

        ProxyDelegate<E> delegate = new ProxyDelegate<>(eLess, proxyTargetClass, parentProxy);

        return (E) Proxy.newProxyInstance(classLoader, interfaces.toArray(new Class<?>[0]), delegate::invoke);
    }

    private class ProxyDelegate<E extends EObject> {
        private ELessObject<E> eLess;
        private Class<E> proxyTargetClass;
        private E parentProxy;

        ProxyDelegate(ELessObject<E> eLess, Class<E> proxyTargetClass, E parentProxy) {
            this.eLess = eLess;
            this.proxyTargetClass = proxyTargetClass;
            this.parentProxy = parentProxy;
        }

        private Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //reject calls to EObject & subinterfaces
            if (EOBJECT_INTERFACES.contains(method.getDeclaringClass())) {
                throw new UnsupportedOperationException("No EMF calls supported; " + method.getName() + " of " + method
                        .getDeclaringClass() + " was called");
            }

            //use Object.equals for the moment
            if(method.getName().equals("equals")) {
                return proxy.hashCode() == args[0].hashCode();
            }

            //stub out container
            if(method.getName().equals("eContainer")) {
                return parentProxy;
            }

            //stub out eClass
            if(method.getName().equals("eClass")) {
                return generateEClassForClass(proxyTargetClass);
            }

            //remap to eLess class
            if(method.getName().startsWith("get") || method.getName().equals("hashCode")) {
                try {
                    return coerceReturn((E)proxy, remapMethod(method).invoke(eLess, args), method.getReturnType());
                } catch (InvocationTargetException ite) {
                    throw ite.getTargetException();
                }
            }

            //reject calls not explicitly handled
            throw new UnsupportedOperationException("Only specific calls are supported; " + method.getName() + " of " + method
                    .getDeclaringClass() + " was called");
        }

        private <T> T coerceReturn(E proxy, Object toReturn, Class<T> expected) {
            if(expected == null)
                expected = (Class<T>)guessExpected(toReturn);

            if(expected.equals(EList.class) && toReturn instanceof List) {
                //first coerce the contents
                List<?> returnList = ((List<?>)toReturn).stream()
                        .map(x -> coerceReturn(proxy, x, null))
                        .collect(Collectors.toList());
                toReturn = new DelegatingEList.UnmodifiableEList(returnList);
            } else if (toReturn instanceof ELessObject<?>) {
                return (T)proxyFor((ELessObject<EObject>)toReturn, (Class<EObject>)expected, proxy);
            }
            return (T)toReturn;
        }

        private Class<?> guessExpected(Object o) {
            if(o instanceof List) {
                return EList.class;
            } else if (o instanceof ELessObject<?>) {
                try {
                    Class<?> localInterface = o.getClass().getInterfaces()[0];
                    return Class.forName(OH_SITEMAP_OBJECTS_PACKAGE.getName() + "." + localInterface.getSimpleName());
                } catch (ClassNotFoundException e) {
                    logger.warn("Failed to locate expected class to map to: {}: {}", e.getClass().getSimpleName(), e.getMessage());
                }
            }

            return o == null ? Object.class : o.getClass();
        }

        private Method remapMethod(Method method) {
            try {
                return eLess.getClass().getMethod(method.getName(), method.getParameterTypes());
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("Cannot find a method " + method + " for class " + eLess.getClass());
            }
        }

        private EClass generateEClassForClass(Class<E> clazz) {

//            try {
//                Method factoryMethod = SitemapFactory.class.getMethod("create" + clazz.getSimpleName());
//                return ((EObject)factoryMethod.invoke(SitemapFactory.eINSTANCE)).eClass();
//            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
//                throw new UnsupportedOperationException("Failed to get eclass for " + clazz, e);
//            }

            return (EClass)Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[]{EClass.class}, (Object proxy, Method method, Object[] args) -> {
                if(method.getName().equals("getInstanceTypeName")) {
                    return clazz.getName();
                }

                //reject calls not explicitly handled
                throw new UnsupportedOperationException("Only specific calls are supported; " + method.getName() + " of " + method
                        .getDeclaringClass() + " was called");
            });
        }
    }
}
