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

/**
 * A dummy class used only for testing.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$, by $Author$ at $Date$
 */
public class Person
{
    /**
     * The parent.
     */
    private Person m__Parent;

    /**
     * The name.
     */
    private String m__strName;

    /**
     * The phone.
     */
    private String m__strPhone;

    /**
     * The address.
     */
    private String m__strAddress;

    /**
     * Creates a person.
     * @param parent the parent.
     * @param name the name.
     * @param phone the phone.
     * @param address the address.
     */
    protected Person(
        final Person parent,
        final String name,
        final String phone,
        final String address)
    {
        inmutableSetParent(parent);
        inmutableSetName(name);
        inmutableSetPhone(phone);
        inmutableSetAddress(address);
    }

    /**
     * Creates a person.
     * @param parent the parent.
     * @param name the name.
     * @param phone the phone.
     */
    public Person(
        final String name,
        final String phone,
        final String address)
    {
        this(null, name, phone, address);
    }

    /**
     * Specifies the parent.
     * @param parent the parent.
     */
    private void inmutableSetParent(final Person parent)
    {
        m__Parent = parent;
    }

    /**
     * Specifies the parent.
     * @param parent the parent.
     */
    protected void setParent(final Person parent)
    {
        inmutableSetParent(parent);
    }

    /**
     * Retrieves the parent.
     * @return such node.
     */
    public Person getParent()
    {
        return m__Parent;
    }

    /**
     * Specifies the name.
     * @param name the name.
     */
    private void inmutableSetName(final String name)
    {
        m__strName = name;
    }

    /**
     * Specifies the name.
     * @param name the name.
     */
    protected void setName(final String name)
    {
        inmutableSetName(name);
    }

    /**
     * Retrieves the name.
     * @return such information.
     */
    public String getName()
    {
        return m__strName;
    }

    /**
     * Specifies the phone.
     * @param phone the phone.
     */
    private void inmutableSetPhone(final String phone)
    {
        m__strPhone = phone;
    }

    /**
     * Specifies the phone.
     * @param phone the phone.
     */
    protected void setPhone(final String phone)
    {
        inmutableSetPhone(phone);
    }

    /**
     * Retrieves the phone.
     * @return such information.
     */
    public String getPhone()
    {
        return m__strPhone;
    }

    /**
     * Specifies the address.
     * @param address the address.
     */
    private void inmutableSetAddress(final String address)
    {
        m__strAddress = address;
    }

    /**
     * Specifies the address.
     * @param address the address.
     */
    protected void setAddress(final String address)
    {
        inmutableSetAddress(address);
    }

    /**
     * Retrieves the address property.
     * @return such information.
     */
    public String getAddress()
    {
        return m__strAddress;
    }
}
