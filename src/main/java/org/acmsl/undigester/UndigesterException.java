/*
                        Undigester

    Copyright (C) 2002-2004  Jose San Leandro Armend&aacute;riz
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
 * Description: Indicates an error in the serialization process has occured.
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
 * Importing some JDK classes.
 */
import java.lang.Exception;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Indicates an error in the serialization process has occured.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public class UndigesterException
    extends  Exception
{
    /**
     * The message bundle.
     */
    public static final String BUNDLE = "exceptions";

    /**
     * The context.
     */
    private Object[] m__aContext;

    /**
     * Creates an UndigesterException with given message.
     * @param key the message key.
     * @param context the relevant context.
     * @precondition key != null
     * @precondition context != null
     */
    public UndigesterException(final String key, final Object[] context)
    {
        super(key);
        immutableSetContext(context);
    }

    /**
     * Creates an UndigesterException with given message,
     * summarizing the details provided by <i>reason</i> exception.
     * @param key the message key.
     * @param context the relevant context.
     * @param reason the actual reason.
     * @precondition key != null
     * @precondition context != null
     * @precondition reason != null
     */
    public UndigesterException(
        final String key, final Object[] context, final Exception reason)
    {
        super(key, reason);
        immutableSetContext(context);
    }

    /**
     * Specifies the context.
     * @param context the relevant information in which the
     * exception appears.
     */
    private void immutableSetContext(final Object[] context)
    {
        m__aContext = context;
    }

    /**
     * Specifies the context.
     * @param context the relevant information in which the
     * exception appears.
     */
    protected void setContext(final Object[] context)
    {
        immutableSetContext(context);
    }

    /**
     * Retrieves the context.
     * @return such relevant information.
     */
    public Object[] getContext()
    {
        return m__aContext;
    }

    /**
     * Retrieves the message for the default locale.
     * @return the message for the default locale.
     */
    public String getMessage()
    {
        return getMessage(null);
    }

    /**
     * Retrieves the message for given locale.
     * @param locale the locale.
     * @return the localized message.
     */
    public String getMessage(final Locale locale)
    {
        String result = super.getMessage();

        ResourceBundle t_Bundle = null;

        if  (locale != null)
        {
            t_Bundle = 
                ResourceBundle.getBundle(BUNDLE, locale);
        }
        else
        {
            t_Bundle = 
                ResourceBundle.getBundle(BUNDLE);
        }

        result = getMessage(result, getContext(), t_Bundle);

        return result;
    }

    /**
     * Retrieves the message for given key in the
     * internationalized bundle.
     * @param key the message key.
     * @param context the context.
     * @param bundle the bundle.
     * @return the localized message.
     * @precondition key != null
     * @precondition context != null
     */
    protected String getMessage(
        final String key, final Object[] context, final ResourceBundle bundle)
    {
        String result = key;

        if  (bundle != null)
        {
            result = bundle.getString(key);
        }

        if  (result != null)
        {
            MessageFormat t_MessageFormatter =
                new MessageFormat(result);

            result = t_MessageFormatter.format(context);
        }

        return result;
    }
}
