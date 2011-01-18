/*
                        Undigester

    Copyright (C) 2003-2004  Jose San Leandro Armend&aacute;riz
                             jsanleandro@yahoo.es
                             chousz@yahoo.com

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the License, or (at your option) any later version.

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
 * Description: Has the responsibility of serializing a concrete object
 *              property.
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
 * Importing some project classes.
 */
import org.acmsl.undigester.CommonChainableRule;
import org.acmsl.undigester.TreeNode;
import org.acmsl.undigester.utils.ReflectionUtils;
import org.acmsl.undigester.utils.UndigesterUtils;

/*
 * Importing some JDK1.3 classes.
 */
import java.lang.IllegalAccessException;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;

/**
 * Has the responsibility of serializing a concrete object property.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public class GetPropertyRule
    extends  CommonChainableRule
{
    /**
     * The property name.
     */
    private String m__strPropertyName;

    /**
     * The "as-element" flag.
     */
    private boolean m__bAsElement;

    /**
     * Flag to output null values or not.
     */
    private boolean m__bOutputNull;

    /**
     * Creates the GetPropertyRule with given information.
     * @param pattern the class pattern.
     * @param propertyName the property of the object to serialize.
     * @param asElement if the property is serialized as element instead
     * of attribute.
     * @param outputNull whether to output null properties.
     * @param parent the parent.
     * @precondition pattern != null
     * @precondition propertyName != null
     */
    public GetPropertyRule(
        final String        pattern,
        final String        propertyName,
        final boolean       asElement,
        final boolean       outputNull,
        final ChainableRule parent)
    {
        super(pattern, parent);
        immutableSetPropertyName(propertyName);
        immutableSetAsElement(asElement);
        immutableSetOutputNull(outputNull);
    }

    /**
     * Creates the GetPropertyRule with given information.
     * @param pattern the class pattern.
     * @param propertyName the property of the object to serialize.
     * @param asElement if the property is serialized as element instead
     * of attribute.
     * @param parent the parent.
     * @precondition pattern != null
     * @precondition propertyName != null
     */
    public GetPropertyRule(
        final String        pattern,
        final String        propertyName,
        final boolean       asElement,
        final ChainableRule parent)
    {
        this(pattern, propertyName, asElement, false, parent);
    }

    /**
     * Creates the GetPropertyRule with given information.
     * @param pattern the class pattern.
     * @param propertyName the property of the object to serialize.
     * @param asElement if the property is serialized as element instead
     * of attribute.
     * @precondition pattern != null
     * @precondition propertyName != null
     */
    public GetPropertyRule(
        final String pattern,
        final String propertyName,
        final boolean asElement)
    {
        this(pattern, propertyName, asElement, null);
    }

    /**
     * Creates the GetPropertyRule with given information.
     * @param pattern the class pattern.
     * @param propertyName the property of the object to serialize.
     * @precondition pattern != null
     * @precondition propertyName != null
     */
    public GetPropertyRule(final String pattern, final String propertyName)
    {
        this(pattern, propertyName, false);
    }

    /**
     * Creates the GetPropertyRule with given information.
     * @param pattern the class pattern.
     * @param propertyName the property of the object to serialize.
     * @param parent the parent.
     * @precondition pattern != null
     * @precondition propertyName != null
     */
    public GetPropertyRule(
        final String pattern,
        final String propertyName,
        final ChainableRule parent)
    {
        this(pattern, propertyName, false, parent);
    }

    /**
     * Specifies the property name.
     * @param propertyName the new property name.
     */
    private void immutableSetPropertyName(final String propertyName)
    {
        m__strPropertyName = propertyName;
    }

    /**
     * Specifies the property name.
     * @param propertyName the new property name.
     */
    protected void setPropertyName(final String propertyName)
    {
        immutableSetPropertyName(propertyName);
    }

    /**
     * Retrieves the property name.
     * @return such information.
     */
    public String getPropertyName() 
    {
        return m__strPropertyName;
    }

    /**
     * Specifies how the property is serialized.
     * @param asElement <code>true</code> to represent the property
     * as an element.
     */
    private void immutableSetAsElement(final boolean asElement)
    {
        m__bAsElement = asElement;
    }

    /**
     * Specifies how the property is serialized.
     * @param asElement <code>true</code> to represent the property
     * as an element.
     */
    public void setAsElement(final boolean asElement)
    {
        immutableSetAsElement(asElement);
    }

    /**
     * Retrieves if the property is serialized as an element or attribute.
     * @return <code>true</code> if the property becames an element.
     */
    public boolean asElement()
    {
        return m__bAsElement;
    }

    /**
     * Specifies if null properties are included in the output.
     * @param outputNull <code>true</code> to represent the property
     * as an null.
     */
    private void immutableSetOutputNull(final boolean outputNull)
    {
        m__bOutputNull = outputNull;
    }

    /**
     * Specifies if null properties are included in the output.
     * @param outputNull <code>true</code> to represent the property
     * as an null.
     */
    public void setOutputNull(final boolean outputNull)
    {
        immutableSetOutputNull(outputNull);
    }

    /**
     * Retrieves if null properties are included in the output.
     * @return <code>true</code> if null properties are included.
     */
    public boolean outputNull()
    {
        return m__bOutputNull;
    }

    /**
     * Processes the rule over given node.
     * @param treeNode the tree node to process.
     * @throws UndigesterException if the property cannot be extracted.
     * @precondition treeNode != null
     */
    public void apply(final TreeNode treeNode)
        throws  UndigesterException
    {
        apply(
            treeNode,
            treeNode.getObject(),
            getPropertyName(),
            asElement(),
            getPattern(),
            outputNull(),
            ReflectionUtils.getInstance(),
            UndigesterUtils.getInstance());
    }

    /**
     * Processes the rule over given object.
     * @param treeNode the tree node.
     * @param object the object to analyze.
     * @param propertyName the property name.
     * @param asElement the <code>asElement</code> flag.
     * @param pattern the rule pattern.
     * @param outputNull the <code>outputNull</code> flag.
     * @param reflectionUtils the ReflectionUtils instance.
     * @param undigesterUtils the UndigesterUtils instance.
     * @throws UndigesterException if the property cannot be extracted.
     * @precondition treeNode != null
     * @precondition object != null
     * @precondition propertyName != null
     * @precondition pattern != null
     * @precondition reflectionUtils != null
     * @precondition undigesterUtils != null
     */
    protected void apply(
        final TreeNode treeNode,
        final Object object,
        final String propertyName,
        final boolean asElement,
        final String pattern,
        final boolean outputNull,
        final ReflectionUtils reflectionUtils,
        final UndigesterUtils undigesterUtils)
      throws  UndigesterException
    {
        String t_strValue =
            applyPojo(
                object,
                propertyName,
                asElement,
                pattern,
                outputNull,
                reflectionUtils,
                undigesterUtils);

        if  (t_strValue != null)
        {
            if  (asElement)
            {
                appendSubelement(
                    treeNode.getBody(),
                    propertyName,
                    t_strValue);
            }
            else
            {
                treeNode.addAttribute(propertyName, t_strValue);
            }
        }
    }

    /**
     * Processes the rule over given object.
     * @param object the object to analyze.
     * @param propertyName the property name.
     * @param asElement the <code>asElement</code> flag.
     * @param pattern the rule pattern.
     * @param outputNull the <code>outputNull</code> flag.
     * @param reflectionUtils the ReflectionUtils instance.
     * @param undigesterUtils the UndigesterUtils instance.
     * @throws UndigesterException if the property cannot be extracted.
     * @precondition propertyName != null
     * @precondition pattern != null
     * @precondition reflectionUtils != null
     * @precondition undigesterUtils != null
     */
    protected String applyPojo(
        final Object object,
        final String propertyName,
        final boolean asElement,
        final String pattern,
        final boolean outputNull,
        final ReflectionUtils reflectionUtils,
        final UndigesterUtils undigesterUtils)
      throws  UndigesterException
    {
        String result = null;

        try 
        {
            Object t_Property =
                reflectionUtils.getProperty(object, propertyName);

            if  (   (outputNull)
                 || (t_Property != null))
            {
                result = "" + t_Property;
            }
        }
        catch (final NoSuchMethodException noSuchMethodException)
        {
            /*
             * Expected something like:
             * property.not.found=The property {0}.{1} does not exist
             */
            throw
                new UndigesterException(
                    "property.not.found",
                    new Object[]
                    {
                        object.getClass().getName(),
                        propertyName
                    },
                    noSuchMethodException);
        }
        catch (final InvocationTargetException invocationTargetException)
        {
            /*
             * Expected something like:
             * property.getter.threw.exception=The getter method for property
             * {0}.{1} threw the following exception: {2}
             */
            throw
                new UndigesterException(
                    "property.getter.threw.exception",
                    new Object[]
                    {
                        object.getClass().getName(),
                        propertyName
                    },
                    invocationTargetException);
        }
        catch  (final IllegalAccessException illegalAccessException)
        {
            /*
             * Expected something like:
             * property.getter.not.accessible=The getter method for property
             * {0}.{1} is not available.
             * Details: {3}
             */
            throw
                new UndigesterException(
                    "property.getter.not.accessible",
                    new Object[]
                    {
                        object.getClass().getName(),
                        propertyName
                    },
                    illegalAccessException);
        }

        return result;
    }

    /**
     * Appends given property as a subelement.
     * @param buffer the stringBuffer to which append to.
     * @param name the property name.
     * @param value the property value.
     * @precondition buffer != null
     * @precondition name != null
     * @precondition value != null
     */
    protected void appendSubelement(
        final StringBuffer buffer,
        final String name,
        final String value)
    {
        buffer.append("<");
        buffer.append(name);
        buffer.append(">");
        buffer.append(value);
        buffer.append("</");
        buffer.append(name);
        buffer.append(">");
    }

    /**
     * Retrieves the object description.
     * @return such information.
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer(super.toString());

        result.append(", " + getPropertyName());

        return result.toString();
    }
}
