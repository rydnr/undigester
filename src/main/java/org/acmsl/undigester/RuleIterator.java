/*
                        Undigester

    Copyright (C) 2002-2004  Jose San Leandro Armend�riz
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
                    Urb. Valdecaba�as
                    Boadilla del monte
                    28660 Madrid
                    Spain

    This library uses an external API to retrieve version information at
    runtime.
    So far I haven't released such API as a project itself, but you should be
    able to download it from the web page where you got this source code.

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armend�riz
 *
 * Description: Allows browsing over rules.
 *
 * Last modified by: $Author$ at $Date$
 *
 * File version: $Revision$
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id$
 *
 */
package org.acmsl.undigester;

/*
 * Importing some ACMSL classes.
 */
import org.acmsl.undigester.Rule;

/**
 * Allows browsing over rules.
 * @see org.acmsl.undigester.Rule
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public interface RuleIterator
{
    /**
     * Checks if there're more rules to browse.
     * @return <code>true</code> if there're pending rules.
     */
    public boolean hasNext();

    /**
     * Retrieves the next rule.
     * @return such information.
     */
    public Rule next();
}