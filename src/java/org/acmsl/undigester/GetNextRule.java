/*
                        Undigester

    Copyright (C) 2003-2004  Jose San Leandro Armend&aacute;riz
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
 * Description: Has the responsibility of retrieving the next object to
 *              serialize.
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
package org.acmsl.undigester;

/*
 * Importing project classes.
 */
import org.acmsl.undigester.CallMethodRule;
import org.acmsl.undigester.StructureShaperRule;
import org.acmsl.undigester.TreeNode;
import org.acmsl.undigester.UndigesterException;
import org.acmsl.undigester.utils.ReflectionUtils;
import org.acmsl.undigester.utils.UndigesterUtils;

/*
 * Importing some ACMSL-Commons classes.
 */
import org.acmsl.commons.utils.StringValidator;

/*
 * Importing JDK classes.
 */
import java.lang.IllegalAccessException;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;

/**
 * Has the responsibility of retrieving the next object to serialize.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public class GetNextRule
    extends    CallMethodRule
    implements StructureShaperRule
{
    /**
     * Creates a GetNextRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @precondition pattern != null
     * @precondition methodName != null
     */
    public GetNextRule(final String pattern, final String methodName)
    {
        super(pattern, methodName);
    }

    /**
     * Creates a GetNextRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param parent the parent rule.
     * @precondition pattern != null
     * @precondition methodName != null
     */
    public GetNextRule(
        final String pattern,
        final String methodName,
        final ChainableRule parent)
    {
        super(pattern, methodName, parent);
    }

    /**
     * Creates a GetNextRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param args the method arguments.
     * @precondition pattern != null
     * @precondition methodName != null
     * @precondition args != null
     */
    public GetNextRule(
        final String   pattern,
        final String   methodName,
        final Object[] args)
    {
        super(pattern, methodName, args);
    }

    /**
     * Creates a GetNextRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param args the method arguments.
     * @param parent the parent rule.
     * @precondition pattern != null
     * @precondition methodName != null
     * @precondition args != null
     */
    public GetNextRule(
        final String        pattern,
        final String        methodName,
        final Object[]      args,
        final ChainableRule parent)
    {
        super(pattern, methodName, args, parent);
    }

    /**
     * Creates a GetNextRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param label the name of the element or attribute.
     * @precondition pattern != null
     * @precondition methodName != null
     */
    public GetNextRule(
        final String pattern,
        final String methodName,
        final String label)
    {
        super(pattern, methodName, label, true);
    }

    /**
     * Creates a GetNextRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param label the element name of the inner contents (optional).
     * @param parent the parent rule.
     * @precondition pattern != null
     * @precondition methodName != null
     */
    public GetNextRule(
        final String        pattern,
        final String        methodName,
        final String        label,
        final ChainableRule parent)
    {
        super(pattern, methodName, label, true, parent);
    }

    /**
     * Creates a GetNextRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param args the method arguments.
     * @param label the element name of the inner contents (optional).
     * @precondition pattern != null
     * @precondition methodName != null
     * @precondition args != null
     */
    public GetNextRule(
        final String   pattern,
        final String   methodName,
        final Object[] args,
        final String   label)
    {
        super(pattern, methodName, args, label, true);
    }

    /**
     * Creates a GetNextRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param args the method arguments.
     * @param innerName the element name for inner contents (optional).
     * @param parent the parent rule.
     * @precondition pattern != null
     * @precondition methodName != null
     * @precondition args != null
     */
    public GetNextRule(
        final String        pattern,
        final String        methodName,
        final Object[]      args,
        final String        label,
        final ChainableRule parent)
    {
        super(pattern, methodName, args, label, parent);
    }

    /**
     * Processes the rule over given object.
     * @param object the tree node.
     * @throws UndigesterException if the next object could not be retrieved.
     * @precondition object != null
     * @precondition object instanceof org.acmsl.undigester.TreeNode
     */
    public void apply(final Object object)
        throws  UndigesterException
    {
        apply((TreeNode) object);
    }

    /**
     * Processes the rule over given object.
     * @param treeNode the tree node.
     * @throws UndigesterException if the next object could not be retrieved.
     * @precondition treeNode != null
     */
    public void apply(final TreeNode treeNode)
        throws  UndigesterException
    {
        apply(
            treeNode,
            treeNode.getObject(),
            getMethodName(),
            getArgs(),
            getPattern(),
            getLabel(),
            ReflectionUtils.getInstance(),
            UndigesterUtils.getInstance(),
            StringValidator.getInstance());
    }

    /**
     * Processes the rule over given object.
     * @param treeNode the tree node.
     * @param object the wrapped object.
     * @param methodName the name of the method to call.
     * @param args the args to pass to the method.
     * @param pattern the rule pattern.
     * @param label the element name of the inner contents (optional).
     * @param reflectionUtils the ReflectionUtils instance.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @throws UndigesterException if the next object could not be retrieved.
     * @precondition treeNode != null
     * @precondition object != null
     * @precondition methodName != null
     * @precondition args != null
     * @precondition pattern != null
     * @precondition reflectionUtils != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition !stringValidator.isEmpty(methodName)
     */
    public void apply(
        final TreeNode treeNode,
        final Object object,
        final String methodName,
        final Object[] args,
        final String pattern,
        final String label,
        final ReflectionUtils reflectionUtils,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator)
      throws  UndigesterException
    {
        try 
        {
            Object t_Object = 
                reflectionUtils.invokeMethod(
                    object,
                    methodName,
                    args);

            if  (t_Object != null) 
            {
                if  (t_Object.getClass().isArray())
                {
                    applyPojoArray(
                        treeNode,
                        (Object[]) t_Object,
                        methodName,
                        args,
                        pattern,
                        label,
                        undigesterUtils,
                        stringValidator);
                }
                else
                {
                    applyPojo(
                        treeNode,
                        t_Object,
                        methodName,
                        args,
                        pattern,
                        label,
                        undigesterUtils,
                        stringValidator);
                }
            }
        }
        catch (final NoSuchMethodException noSuchMethodException)
        {
            /*
             * Expected something like:
             * method.not.found=The method {0}#{1}({2}) does not exist
             */
            throw
                new UndigesterException(
                    "method.not.found",
                    new Object[]
                    {
                        object.getClass().getName(),
                        methodName,
                        undigesterUtils.toString(args)
                    },
                    noSuchMethodException);
        }
        catch (final InvocationTargetException invocationTargetException)
        {
            /*
             * Expected something like:
             * method.threw.exception=The method {0}#{1}({2}) threw the
             * following exception: {3}
             */
            throw
                new UndigesterException(
                    "method.threw.exception",
                    new Object[]
                    {
                        object.getClass().getName(),
                        methodName,
                        undigesterUtils.toString(args)
                    },
                    invocationTargetException);
        }
        catch  (final IllegalAccessException illegalAccessException)
        {
            /*
             * Expected something like:
             * method.not.accessible=The method {0}#{1}({2}) is not available.
             * Details: {3}
             */
            throw
                new UndigesterException(
                    "method.not.accessible",
                    new Object[]
                    {
                        object.getClass().getName(),
                        methodName,
                        undigesterUtils.toString(args)
                    },
                    illegalAccessException);
        }
    }
    
    /**
     * Associates given object as a child node of the tree.
     * @param treeNode the current node.
     * @param object the object to be referred as a child node.
     * @param methodName the name of the method.
     * @param args the method arguments.
     * @param pattern the rule pattern.
     * @param label the node group label.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @precondition treeNode != null
     * @precondition object != null
     * @precondition methodName != null
     * @precondition args != null
     * @precondition pattern != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     */
    protected void applyPojo(
        final TreeNode treeNode,
        final Object object,
        final String methodName,
        final Object[] args,
        final String pattern,
        final String label,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator)
    {
        TreeNode t_TreeNode =
            new TreeNode(
                  pattern
                + "/"
                + object.getClass().getName(),
                object,
                treeNode,
                undigesterUtils.buildSpecializedPattern(
                    methodName,
                    args,
                    pattern));

        if  (stringValidator.isEmpty(label))
        {
            treeNode.addChild(t_TreeNode);
        }
        else
        {
            treeNode.addChild(label, t_TreeNode);
        }
    }

    /**
     * Associates given object as a child node of the tree.
     * @param treeNode the current node.
     * @param objects the objects to be referred as child nodes.
     * @param methodName the method name.
     * @param methodName the name of the method.
     * @param args the method arguments.
     * @param pattern the rule pattern.
     * @param label the node group label.
     * @param stringValidator the StringValidator instance.
     * @param undigesterUtils the UndigesterUtils instance.
     * @precondition treeNode != null
     * @precondition objects != null
     * @precondition pattern != null
     * @precondition methodName != null
     * @precondition args != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     */
    protected void applyPojoArray(
        final TreeNode treeNode,
        final Object[] objects,
        final String methodName,
        final Object[] args,
        final String pattern,
        final String label,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator)
    {
        for  (int t_iIndex = 0; t_iIndex < objects.length; t_iIndex++)
        {
            applyPojo(
                treeNode,
                objects[t_iIndex],
                methodName,
                args,
                pattern,
                label,
                undigesterUtils,
                stringValidator);
        }
    }
}
