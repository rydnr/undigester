/*
                        Undigester

    Copyright (C) 2002-2004  Jose San Leandro Armend&aacute;riz
                             jsanleandro@yahoo.es
                             chousz@yahoo.com

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the License, or any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    General Public License for more details.

    You should have received a copy of the GNU General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

    Thanks to ACM S.L. for distributing this library under the GPL license.
    Contact info: jsr000@terra.es
    Postal Address: c/Playa de Lagoa, 1
                    Urb. Valdecaba&ntilde;as
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armend&aacute;riz
 *
 * Description: Provides some useful methods when using Reflection.
 *
 * Last modified by: $Author$ at $Date$
 *
 * File version: $Revision$
 *
 * Project version: $Name$
 *
 * $Id$
 *
 */
package org.acmsl.undigester.utils;

/*
 * Importing ACMSL Commons classes.
 */
import org.acmsl.commons.patterns.Utils;

/*
 * Importing Jakarta Commons BeanUtils.
 */
import org.apache.commons.beanutils.MethodUtils;

/*
 * Importing some JDK classes.
 */
import java.lang.IllegalAccessException;
import java.lang.NoSuchMethodException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;

/**
 * Provides some useful methods when using Reflection.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public abstract class ReflectionUtils
    implements  Utils
{
    /**
     * Empty object array.
     */
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference m__Singleton;

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected ReflectionUtils() {};

    /**
     * Specifies a new weak reference.
     * @param utils the utils instance to use.
     * @precondition utils != null
     */
    protected static void setReference(ReflectionUtils utils)
    {
        m__Singleton = new WeakReference(utils);
    }

    /**
     * Retrieves the weak reference.
     * @return such reference.
     */
    protected static WeakReference getReference()
    {
        return m__Singleton;
    }

    /**
     * Retrieves a ReflectionUtils instance.
     * @return such instance.
     */
    public static ReflectionUtils getInstance()
    {
        ReflectionUtils result = null;

        WeakReference t_Reference = getReference();

        if  (t_Reference != null)
        {
            result = (ReflectionUtils) t_Reference.get();
        }

        if  (result == null)
        {
            result = new ReflectionUtils() {};

            setReference(result);
        }

        return result;
    }

    /**
     * Retrieves a property value of given object, by its name.
     * @param object the object with the property.
     * @param name the property name.
     * @return the property value.
     * @throws NoSuchMethodException if there is no such accessible method.
     * @throws InvocationTargetException if the attempt to execute the getter
     * method fails.
     * @throws IllegalAccessException if the method exists but is not suitable
     * of being executed via reflection.
     * @precondition object != null
     * @precondition name != null
     * @precondition name.length() > 0
     */
    public Object getProperty(final Object object, final String name)
      throws NoSuchMethodException,
             InvocationTargetException,
             IllegalAccessException
    {
        return
            MethodUtils.invokeMethod(
                object,
                  "get"
                + name.substring(0,1).toUpperCase()
                + name.substring(1),
                new Object[0]);
    }

    /**
     * Invokes a method on given object only by its name.
     * @param object the object.
     * @param methodName the method name.
     * @param args the method arguments.
     * @return the result of the method call.
     * @throws NoSuchMethodException if there is no such accessible method.
     * @throws InvocationTargetException if the attempt to execute the getter
     * method fails.
     * @throws IllegalAccessException if the method exists but is not suitable
     * @precondition object != null
     * @precondition methodName != null
     * @precondition args != null
     */
    public Object invokeMethod(
        final Object object, final String methodName, final Object[] args)
      throws NoSuchMethodException,
             InvocationTargetException,
             IllegalAccessException
    {
        return
            MethodUtils.invokeMethod(
                object,
                methodName,
                args);
    }
}
