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
 * Description: Contains a complete set of Undigester rules.
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
import org.acmsl.undigester.Rule;

/*
 * Importing some JDK1.3 classes.
 */
import java.util.ArrayList;
import java.util.Collection;

/**
 * Contains a complete set of Undigester rules.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public class RuleSet
{
    /**
     * The rule collection.
     */
    private Collection m__cRules;

    /**
     * Creates an empty rule set.
     */
    public RuleSet() {};

    /**
     * Specifies the rule collection.
     * @param rules the rule collection.
     */
    protected void setRules(final Collection rules)
    {
        m__cRules = rules;
    }

    /**
     * Specifies the rule collection.
     * @param rules the rule collection.
     */
    private Collection immutableGetRules()
    {
        return m__cRules;
    }

    /**
     * Specifies the rule collection.
     * @param rules the rule collection.
     */
    public Collection getRules()
    {
        Collection result = m__cRules;

        if  (result == null)
        {
            result = new ArrayList();

            setRules(result);
        }

        return result;
    }

    /**
     * Adds a new rule.
     * @param rule the rule to add.
     * @precondition rule != null
     */
    public void addRule(final Rule rule)
    {
        Collection t_cRules = getRules();

        if  (t_cRules != null)
        {
            t_cRules.add(rule);
        }
    }
}
