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
 * Author: Jose San Leandro Armendáriz
 *
 * Description: Has the responsibility of finding out the name of the
 *              object being undigested.
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
import org.acmsl.undigester.ChainableRule;
import org.acmsl.undigester.CommonChainableRule;
import org.acmsl.undigester.UndigesterException;

/*
 * Importing some ACM-SL Commons classes.
 */
import org.acmsl.commons.utils.StringValidator;

/**
 * Has the responsibility of finding out the name of the object being
 * undigested.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public class GetNameRule
    extends  CommonChainableRule
{
    /**
     * The cached instance of the unknown name string.
     */
    public static final String UNKNOWN_NAME = "unknown";

    /**
     * The alternate object name.
     */
    private String m__strObjectName;

    /**
     * Creates an ObjectNameRule with given information.
     * @param pattern the class pattern.
     * @precondition pattern != null
     */
    public GetNameRule(final String pattern)
    {
        super(pattern);
    }

    /**
     * Creates an ObjectNameRule with given information.
     * @param pattern the class pattern.
     * @param parent the parent rule.
     * @precondition pattern != null
     */
    public GetNameRule(final String pattern, final ChainableRule parent)
    {
        super(pattern, parent);
    }

    /**
     * Creates an ObjectNameRule with given information.
     * @param pattern the class pattern.
     * @param objectName an alternate name.
     * @precondition pattern != null
     */
    public GetNameRule(final String pattern, final String objectName)
    {
        this(pattern);
        immutableSetObjectName(objectName);
    }

    /**
     * Creates an ObjectNameRule with given information.
     * @param pattern the class pattern.
     * @param objectName an alternate name.
     * @param parent the parent rule.
     * @precondition pattern != null
     */
    public GetNameRule(
        final String pattern,
        final String objectName,
        final ChainableRule parent)
    {
        this(pattern, parent);
        immutableSetObjectName(objectName);
    }

    /**
     * Specifies the object name.
     * @param objectName the new object name.
     */
    private void immutableSetObjectName(final String objectName)
    {
        m__strObjectName = objectName;
    }

    /**
     * Specifies the object name.
     * @param objectName the new object name.
     */
    protected void setObjectName(final String objectName)
    {
        m__strObjectName = objectName;
    }

    /**
     * Retrieves the object name.
     * @return such information.
     */
    public String getObjectName() 
    {
        return m__strObjectName;
    }

    /**
     * Processes the rule over given object.
     * @param treeNode the tree node to analyze.
     * @throws UndigesterException if the name of the object cannot be
     * retrieved.
     */
    public void apply(final TreeNode treeNode)
        throws  UndigesterException
    {
        treeNode.setNodeName(applyPojo(treeNode.getObject()));
    }

    /**
     * Processes the rule over given object.
     * @param object the object to analyze.
     * @throws UndigesterException if the name of the object cannot be
     * retrieved.
     */
    protected String applyPojo(final Object object)
        throws  UndigesterException
    {
        String result = UNKNOWN_NAME;

        String t_strObjectName = getObjectName();

        if  (t_strObjectName != null) 
        {
            result = t_strObjectName;
        }
        else 
        {
            result = retrieveName(object).toLowerCase();
        }

        return result;
    }

    /**
     * Retrieves the XML element name for given object.
     * @param object the object to analyze.
     * @return the object name.
     * @precondition object != null
     */
    public String retrieveName(final Object object)
    {
        return retrieveName(object, StringValidator.getInstance());
    }

    /**
     * Retrieves the XML element name for given object.
     * @param object the object to analyze.
     * @param stringValidator the StringValidator instance.
     * @return the object name.
     * @precondition stringValidator != null
     */
    protected String retrieveName(
        final Object object, final StringValidator stringValidator)
    {
        Object t_Object = object;

        if  (object instanceof TreeNode)
        {
            t_Object = ((TreeNode) object).getObject();
        }

        return retrievePojoName(t_Object, stringValidator);
    }

    /**
     * Retrieves the XML element name for given object.
     * @param object the object to analyze.
     * @param stringValidator the StringValidator instance.
     * @return the object name.
     * @precondition object != null
     * @precondition stringValidator != null
     */
    protected String retrievePojoName(
        final Object object, final StringValidator stringValidator)
    {
        String result = UNKNOWN_NAME;

        Class t_Class = object.getClass();

        if  (t_Class != null) 
        {
            Package t_Package = t_Class.getPackage();

            if  (t_Package != null) 
            {
                String t_strClassName = t_Class.getName();
                String t_strPackageName = t_Package.getName();

                result =
                    t_strClassName.substring(
                          t_strClassName.indexOf(t_strPackageName)
                        + t_strPackageName.length()
                        + 1);
            }
        }

        /*
        if  (   (stringValidator.isEmpty(result))
             || (result.indexOf("$") > -1)
             || (result.equals(UNKNOWN_NAME)))
        */
        if  (   (!stringValidator.isEmpty(result))
             && (result.indexOf("$") > -1))
        {
            result = result.substring(0, result.indexOf("$"));
        }

        if  (   (stringValidator.isEmpty(result))
             || (result.indexOf("$") > -1))
        {
            result = UNKNOWN_NAME;
        }
        
        return result;
    }

    /**
     * Retrieves the object description.
     * @return such information.
     */
    public String toString()
    {
        StringBuffer result = new StringBuffer(super.toString());

        result.append(", " + getObjectName());

        return result.toString();
    }
}
