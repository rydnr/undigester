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
import org.acmsl.undigester.Rule;
import org.acmsl.undigester.TreeNode;
import org.acmsl.undigester.utils.UndigesterUtils;

/*
 * Importing JDK classes.
 */
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * Defines the common behaviour shared by most of rule implementations.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public abstract class CommonRule
    implements Rule
{
    /**
     * The class pattern.
     */
    private String m__strPattern;

    /**
     * Creates a rule with given pattern.
     * @param pattern the class pattern.
     * @precondition pattern != null
     */
    protected CommonRule(final String pattern)
    {
        inmutableSetPattern(pattern);
    }

    /**
     * Specifies the pattern.
     * @param pattern the new pattern.
     */
    private void inmutableSetPattern(final String pattern)
    {
        m__strPattern = pattern;
    }

    /**
     * Specifies the pattern.
     * @param pattern the new pattern.
     */
    protected void setPattern(final String pattern)
    {
        inmutableSetPattern(pattern);
    }

    /**
     * Retrieves the pattern.
     * @return such information.
     */
    public String getPattern()
    {
        return m__strPattern;
    }

    /**
     * Retrieves the pattern used to distinguish branches.
     * @return such information.
     */
    public String getBranchPattern()
    {
        return "";
    }

    /**
     * Retrieves the object description.
     * @return such information.
     */
    public String toString()
    {
        return
            toString(
                getClass().getName(),
                getPattern(),
                UndigesterUtils.getInstance());
    }

    /**
     * Retrieves the object description.
     * @param initialValue the initial value.
     * @param pattern the pattern.
     * @param undigesterUtils the UndigesterUtils instance.
     * @return such information.
     * @precondition initialValue != null
     * @precondition pattern != null
     * @precondition undigesterUtils != null
     */
    public String toString(
        final String          initialValue,
        final String          pattern,
        final UndigesterUtils undigesterUtils)
    {
        return
            toString(
                initialValue,
                undigesterUtils.expandPattern(pattern));
    }

    /**
     * Retrieves the object description.
     * @param initialValue the initial value.
     * @param pattern the pattern.
     * @return such information.
     * @precondition initialValue != null
     * @precondition pattern != null
     */
    protected String toString(
        final String   initialValue,
        final String[] pattern)
    {
        StringBuffer result = new StringBuffer(initialValue);

        result.append(" { " + pattern[0]);

        for (int t_iIndex = 1; t_iIndex < pattern.length; t_iIndex++)
        {
            result.append(" , " + pattern[t_iIndex]);
        }

        result.append(" } ");

        return result.toString();
    }
}
