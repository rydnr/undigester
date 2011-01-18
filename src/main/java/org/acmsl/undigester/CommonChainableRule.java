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
                    Urb. Valdecaba&ntlide;as
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armend&aacute;riz
 *
 * Description: Defines the common behaviour shared by most of rule
 *              implementations.
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
import org.acmsl.undigester.CommonRule;
import org.acmsl.undigester.Rule;

/*
 * Importing JDK classes.
 */
import java.util.ArrayList;
import java.util.Collection;

/**
 * Defines the common behaviour shared by most of rule implementations.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public abstract class CommonChainableRule
    extends     CommonRule
    implements  ChainableRule
{
    /**
     * The parent rule.
     */
    private ChainableRule m__Parent;

    /**
     * The children.
     */
    private Collection m__cChildren;

    /**
     * Creates a chainable rule with given pattern.
     * @param pattern the class pattern.
     * @precondition pattern != null
     */
    protected CommonChainableRule(final String pattern)
    {
        super(pattern);
    }

    /**
     * Creates a chainable rule with given pattern.
     * @param pattern the class pattern.
     * @param parent the parent rule.
     * @precondition pattern != null
     */
    protected CommonChainableRule(
        final String pattern, final ChainableRule parent)
    {
        this(pattern);
        immutableSetParent(parent);
    }

    /**
     * Specifies the parent rule.
     * @param parent such parent.
     */
    private void immutableSetParent(final ChainableRule parent)
    {
        m__Parent = parent;

        if  (parent != null)
        {
            parent.addChild(this);
        }
    }

    /**
     * Specifies the parent rule.
     * @param parent such parent.
     */
    protected void setParent(final ChainableRule parent)
    {
        immutableSetParent(parent);
    }

    /**
     * Retrieves the parent rule.
     * @return such parent.
     */
    public ChainableRule getParent()
    {
        return m__Parent;
    }

    /**
     * Specifies the children collection.
     * @param children such collection.
     */
    protected void setChildren(final Collection children)
    {
        m__cChildren = children;
    }

    /**
     * Retrieves the children of this rule.
     * @return such collection.
     */
    public Collection getChildren()
    {
        return m__cChildren;
    }

    /**
     * Declares given rule as child of this one.
     * @param child the new child.
     */
    public void addChild(final Rule rule)
    {
        Collection t_cChildren = getChildren();

        if  (t_cChildren == null)
        {
            t_cChildren = new ArrayList();

            setChildren(t_cChildren);
        }

        t_cChildren.add(rule);
    }
}
