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
 * Description: A dummy class used only for testing.
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
package unittests.org.acmsl.undigester;

/*
 * Importing project classes.
 */
import unittests.org.acmsl.undigester.Person;

/**
 * A dummy class used only for testing.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$, by $Author$ at $Date$
 */
public class Son
    extends  Person
{
    /**
     * Creates a son.
     * @param parent the parent.
     * @param name the name.
     */
    public Son(
        final Person parent, final String name)
    {
        super(parent, name, parent.getPhone(), parent.getAddress());
    }

    /**
     * Creates a son.
     * @param parent the parent.
     * @param name the name.
     * @param phone the phone.
     */
    public Son(
        final String name,
        final String phone,
        final String address)
    {
        super(null, name, phone, address);
    }
}
