/*
                        Undigester

    Copyright (C) 2003-2004  Jose San Leandro Armendáriz
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
                    Urb. Valdecabañas
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armendáriz
 *
 * Description: Has the responsibility of serializing the result of applying
 *              the toString() method on the matching object.
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
import org.acmsl.undigester.ChainableRule;
import org.acmsl.undigester.UndigesterException;

/*
 * Importing some ACMSL-Commons classes.
 */
import org.acmsl.commons.utils.StringValidator;

/**
 * Has the responsibility of serializing the result of applying
 * the toString() method on the matching object.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public class ToStringRule
    extends  CallMethodRule
{
    /**
     * Default method name.
     */
    protected static final String METHOD_NAME = "toString";

    /**
     * Creates the ToStringRule with given information.
     * @param pattern the class pattern.
     * @precondition pattern != null
     */
    public ToStringRule(final String pattern)
    {
        super(pattern, METHOD_NAME);
    }

    /**
     * Creates the ToStringRule with given information.
     * @param pattern the class pattern.
     * @param parent the parent rule.
     * @precondition pattern != null
     */
    public ToStringRule(final String pattern, final ChainableRule parent)
    {
        super(pattern, METHOD_NAME, parent);
    }

    /**
     * Creates the ToStringRule with given information.
     * @param pattern the class pattern.
     * @param label the name of the element or attribute.
     * @param asElement whether to include the results as element or attribute.
     * @precondition pattern != null
     */
    public ToStringRule(
        final String pattern, final String label, final boolean asElement)
    {
        super(pattern, METHOD_NAME, NO_ARGS, label, asElement);
    }

    /**
     * Creates the ToStringRule with given information.
     * @param pattern the class pattern.
     * @param label the name of the element or attribute.
     * @param asElement whether to include the results as element or attribute.
     * @param parent the parent rule.
     * @precondition pattern != null
     */
    public ToStringRule(
        final String pattern,
        final String label,
        final boolean asElement,
        final ChainableRule parent)
    {
        super(pattern, METHOD_NAME, NO_ARGS, label, asElement, parent);
    }

    /**
     * Processes the rule over given node.
     * @param treeNode the tree node.
     * @throws UndigesterException never.
     * @precondition treeNode != null
     */
    public void apply(final TreeNode treeNode)
        throws  UndigesterException
    {
        apply(
            treeNode,
            treeNode.getObject(),
            treeNode.getBody(),
            getLabel(),
            getAsElement(),
            StringValidator.getInstance());
    }

    /**
     * Processes the rule over given object.
     * @param treeNode the tree node.
     * @param object the wrapped object.
     * @param body the tree node body.
     * @param label the name of the element or attribute.
     * @param asElement whether to include the results as element or attribute.
     * @param stringValidator the StringValidator instance.
     * @precondition treeNode != null
     * @precondition object != null
     * @precondition body != null
     * @precondition stringValidator != null
     */
    protected void apply(
        final TreeNode treeNode,
        final Object object,
        final StringBuffer body,
        final String label,
        final boolean asElement,
        final StringValidator stringValidator)
    {
        String t_strResult = applyPojo(object);

        if  (asElement)
        {
            body.append("<");
            body.append(label);
            if  (stringValidator.isEmpty(t_strResult))
            {
                body.append("/>");
            }
            else
            {
                body.append(t_strResult);
                body.append("</");
                body.append(label);
                body.append(">");
            }
        }
        else
        {
            treeNode.addAttribute(label, t_strResult);
        }
    }

    /**
     * Processes the rule over given object.
     * @param object the wrapped object.
     * @return the output of <code>object.toString()</code>
     * @precondition object != null
     */
    protected String applyPojo(final Object object)
    {
        return object.toString();
    }
}
