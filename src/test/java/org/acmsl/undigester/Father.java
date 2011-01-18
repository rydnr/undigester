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
public class Father
    extends  Person
{
    /**
     * The phone list.
     */
    private String[] m__astrPhones;

    /**
     * Creates a father.
     * @param parent the parent.
     * @param name the name.
     * @param phones the phones.
     * @param address the address.
     */
    public Father(
        final Person parent,
        final String name,
        final String[] phones,
        final String address)
    {
        super(parent, name, phones[0], address);
        inmutableSetPhones(phones);
    }

    /**
     * Specifies the phones.
     * @param phones the phone list.
     */
    private void inmutableSetPhones(final String[] phones)
    {
        m__astrPhones = phones;
    }

    /**
     * Specifies the phones.
     * @param phones the phone list.
     */
    protected void setPhones(final String[] phones)
    {
        inmutableSetPhones(phones);
    }

    /**
     * Retrieves the phone iterator.
     * @return the iterator to browse the phone information.
     */
    public String[] getPhones()
    {
        return m__astrPhones;
    }
}
