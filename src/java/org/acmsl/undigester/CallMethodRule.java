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
 * Description: Has the responsibility of invoking a certain method and
 *              serializing its output.
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
import org.acmsl.undigester.CommonChainableRule;
import org.acmsl.undigester.UndigesterException;
import org.acmsl.undigester.utils.ReflectionUtils;
import org.acmsl.undigester.utils.UndigesterUtils;

/*
 * Importing ACMSL Commons classes.
 */
import org.acmsl.commons.utils.StringValidator;

/*
 * Importing some JDK1.3 classes.
 */
import java.lang.IllegalAccessException;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Has the responsibility of invoking a certain method and serializing its
 * output.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public class CallMethodRule
    extends  CommonChainableRule
{
    //~ Static fields/initializers ............................................

    /**
     * Default method arguments.
     */
    public static final Object[] NO_ARGS =
        ReflectionUtils.EMPTY_OBJECT_ARRAY;

    //~ Instance fields .......................................................

    /**
     * The method name.
     */
    private String m__strMethodName;

    /**
     * The method arguments.
     */
    private Object[] m__aArgs;

    /**
     * The label.
     */
    private String m__strLabel;

    /**
     * Whether the result will appear as an inner element or as attribute.
     */
    private boolean m__bAsElement;

    //~ Constructors ..........................................................

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param args the method arguments.
     * @param label the name of the element or attribute.
     * @param asElement whether to include the results as element or attribute.
     * @precondition pattern != null
     * @precondition methodName != null
     * @precondition args != null
     */
    public CallMethodRule(
        final String   pattern,
        final String   methodName,
        final Object[] args,
        final String   label,
        final boolean  asElement)
    {
        super(pattern);
        immutableSetMethodName(methodName);
        immutableSetArgs(args);
        immutableSetLabel(label);
        immutableSetAsElement(asElement);
    }

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param args the method arguments.
     * @param label the name of the element or attribute.
     * @precondition pattern != null
     * @precondition methodName != null
     * @precondition args != null
     */
    public CallMethodRule(
        final String   pattern,
        final String   methodName,
        final Object[] args,
        final String   label)
    {
        this(pattern, methodName, args, label, false);
    }

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param args the method arguments.
     * @param label the name of the element or attribute.
     * @param asElement whether to include the results as element or attribute.
     * @param parent the parent rule.
     * @precondition pattern != null
     * @precondition methodName != null
     * @precondition args != null
     */
    public CallMethodRule(
        final String        pattern,
        final String        methodName,
        final Object[]      args,
        final String        label,
        final boolean       asElement,
        final ChainableRule parent)
    {
        super(pattern, parent);
        immutableSetMethodName(methodName);
        immutableSetArgs(args);
        immutableSetLabel(label);
        immutableSetAsElement(asElement);
    }

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param args the method arguments.
     * @param label the name of the element or attribute.
     * @param parent the parent rule.
     * @precondition pattern != null
     * @precondition methodName != null
     * @precondition args != null
     */
    public CallMethodRule(
        final String        pattern,
        final String        methodName,
        final Object[]      args,
        final String        label,
        final ChainableRule parent)
    {
        this(pattern, methodName, args, label, false, parent);
    }

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @precondition pattern != null
     * @precondition methodName != null
     */
    public CallMethodRule(final String pattern, final String methodName)
    {
        this(pattern, methodName, NO_ARGS, null, false, null);
    }

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param parent the parent rule.
     * @precondition pattern != null
     * @precondition methodName != null
     */
    public CallMethodRule(
        final String pattern,
        final String methodName,
        final ChainableRule parent)
    {
        this(pattern, methodName, NO_ARGS, null, false, parent);
    }

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param label the name of the element or attribute.
     * @param asElement whether to include the results as element or attribute.
     * @precondition pattern != null
     * @precondition methodName != null
     */
    public CallMethodRule(
        final String  pattern,
        final String  methodName,
        final String  label,
        final boolean asElement)
    {
        this(pattern, methodName, NO_ARGS, label, asElement, null);
    }

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param label the name of the element or attribute.
     * @precondition pattern != null
     * @precondition methodName != null
     */
    public CallMethodRule(
        final String  pattern,
        final String  methodName,
        final String  label)
    {
        this(pattern, methodName, NO_ARGS, label, false, null);
    }

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param label the name of the element or attribute.
     * @param asElement whether to include the results as element or attribute.
     * @param parent the parent rule.
     * @precondition pattern != null
     * @precondition methodName != null
     */
    public CallMethodRule(
        final String        pattern,
        final String        methodName,
        final String        label,
        final boolean       asElement,
        final ChainableRule parent)
    {
        this(pattern, methodName, NO_ARGS, label, asElement, parent);
    }

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param label the name of the element or attribute.
     * @param parent the parent rule.
     * @precondition pattern != null
     * @precondition methodName != null
     */
    public CallMethodRule(
        final String        pattern,
        final String        methodName,
        final String        label,
        final ChainableRule parent)
    {
        this(pattern, methodName, NO_ARGS, label, false, parent);
    }

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param args the method arguments.
     * @precondition pattern != null
     * @precondition methodName != null
     * @precondition args != null
     */
    public CallMethodRule(
        final String   pattern,
        final String   methodName,
        final Object[] args)
    {
        this(pattern, methodName, args, null, false, null);
    }

    /**
     * Creates a CallMethodRule with given information.
     * @param pattern the class pattern.
     * @param methodName the method name.
     * @param args the method arguments.
     * @param parent the parent rule.
     * @precondition pattern != null
     * @precondition methodName != null
     * @precondition args != null
     */
    public CallMethodRule(
        final String        pattern,
        final String        methodName,
        final Object[]      args,
        final ChainableRule parent)
    {
        this(pattern, methodName, args, null, false, parent);
    }

    //~ Methods ...............................................................

    /**
     * Specifies the method name.
     * @param methodName the new method name.
     */
    private void immutableSetMethodName(final String methodName)
    {
        m__strMethodName = methodName;
    }

    /**
     * Specifies the method name.
     * @param methodName the new method name.
     */
    protected void setMethodName(final String methodName)
    {
        immutableSetMethodName(methodName);
    }

    /**
     * Retrieves the method name.
     * @return such information.
     */
    public String getMethodName()
    {
        return m__strMethodName;
    }

    /**
     * Specifies the method arguments.
     * @param args the new arguments.
     */
    private void immutableSetArgs(final Object[] args)
    {
        m__aArgs = args;
    }

    /**
     * Specifies the method arguments.
     * @param args the new arguments.
     */
    public void setArgs(final Object[] args)
    {
        immutableSetArgs(args);
    }

    /**
     * Retrieves the method arguments.
     * @return such information.
     */
    public Object[] getArgs()
    {
        return m__aArgs;
    }

    /**
     * Specifies the name of the element or attribute.
     * @param innerName the new element name.
     */
    private void immutableSetLabel(final String innerName)
    {
        m__strLabel = innerName;
    }

    /**
     * Specifies the name of the element or attribute.
     * @param innerName the new element name.
     */
    protected void setLabel(final String innerName)
    {
        immutableSetLabel(innerName);
    }

    /**
     * Retrieves the name of the element or attribute.
     * @return such information.
     */
    public String getLabel()
    {
        return m__strLabel;
    }

    /**
     * Specifies whether the result will be formatted as element
     * or attribute.
     * @param asElement such decision.
     */
    private void immutableSetAsElement(final boolean asElement)
    {
        m__bAsElement = asElement;
    }

    /**
     * Specifies whether the result will be formatted as element
     * or attribute.
     * @param asElement such decision.
     */
    protected void setAsElement(final boolean asElement)
    {
        immutableSetAsElement(asElement);
    }

    /**
     * Retrieves whether the result will be formatted as element
     * or attribute.
     * @return such decision.
     */
    public boolean getAsElement()
    {
        return m__bAsElement;
    }

    /**
     * Retrieves the pattern used to distinguish branches.
     * @return such information.
     */
    public String getBranchPattern()
    {
        return
            getBranchPattern(
                getMethodName(),
                getArgs());
    }

    /**
     * Retrieves the pattern used to distinguish branches.
     * @param methodName the method name.
     * @param args the method arguments.
     * @return such information.
     * @precondition methodName != null
     * @precondition args != null
     */
    protected String getBranchPattern(
        final String methodName, final Object[] args)
    {
        StringBuffer t_sbResult = new StringBuffer();

        t_sbResult.append(methodName);

        t_sbResult.append("(");

        for  (int t_iArgIndex = 0; t_iArgIndex < args.length; t_iArgIndex++)
        {
            if  (t_iArgIndex > 0);
            {
                t_sbResult.append(",");
            }
            t_sbResult.append(args[t_iArgIndex].getClass().getName());
        }

        t_sbResult.append(")");

        return t_sbResult.toString();
    }

    /**
     * Processes the rule over given object.
     * @param treeNode the tree node.
     * @throws UndigesterException if the rule could not be applied.
     * @precondition treeNode != null
     */
    public void apply(final TreeNode treeNode)
      throws UndigesterException
    {
        apply(
            treeNode.getBody(),
            treeNode.getObject(),
            getMethodName(),
            getArgs(),
            getLabel(),
            ReflectionUtils.getInstance(),
            StringValidator.getInstance(),
            UndigesterUtils.getInstance());
    }

    /**
     * Processes the rule over given object.
     * @param body the tree node's body.
     * @param object the wrapped object.
     * @param methodName the method name.
     * @param args the method arguments.
     * @param innerName the inner name.
     * @param reflectionUtils the ReflectionUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param undigesterUtils the UndigesterUtils instance.
     * @throws UndigesterException if the rule could not be applied.
     * @precondition body != null
     * @precondition object != null
     * @precondition methodName != null
     * @precondition args != null
     * @precondition reflectionUtils != null
     * @precondition stringValidator != null
     * @precondition undigesterUtils != null
     */
    protected void apply(
        final StringBuffer    body,
        final Object          object,
        final String          methodName,
        final Object[]        args,
        final String          innerName,
        final ReflectionUtils reflectionUtils,
        final StringValidator stringValidator,
        final UndigesterUtils undigesterUtils)
      throws UndigesterException
    {
        String t_strResult = null;

        try
        {
            if  (stringValidator.isEmpty(methodName))
            {
                t_strResult = "" + object;
            }
            else
            {
                t_strResult =
                      ""
                    + reflectionUtils.invokeMethod(
                          object, methodName, args);
            }

            if  (stringValidator.isEmpty(innerName))
            {
                body.append(t_strResult);
            }
            else
            {
                body.append("<" + innerName + ">");
                body.append(t_strResult);
                body.append("</" + innerName + ">");
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
     * Retrieves the rule description.
     * @return such information.
     */
    public String toString()
    {
        return
            toString(
                super.toString(),
                getMethodName(),
                getArgs(),
                getLabel(),
                UndigesterUtils.getInstance(),
                StringValidator.getInstance());
    }

    /**
     * Retrieves the rule description.
     * @param parentValue the result of super.toString().
     * @param methodName the method name.
     * @param args the method arguments.
     * @param innerName the inner name.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @return such information.
     * @precondition parentValue != null
     * @precondition methodName != null
     * @precondition args != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     */
    protected String toString(
        final String parentValue,
        final String methodName,
        final Object[] args,
        final String innerName,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator)
    {
        StringBuffer result = new StringBuffer(parentValue);

        result.append("#");
        result.append(methodName);
        result.append("(");
        result.append(undigesterUtils.classArrayToString(args));
        result.append(")");

        if  (!stringValidator.isEmpty(innerName))
        {
            result.append("[");
            result.append(innerName);
            result.append("]");
        }

        return result.toString();
    }
}
