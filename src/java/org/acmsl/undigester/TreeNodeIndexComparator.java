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
 * Description: Is able to compare tree nodes according to their index.
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
import org.acmsl.undigester.TreeNode;

/*
 * Importing some JDK classes.
 */
import java.lang.ref.WeakReference;
import java.util.Comparator;

/**
 * Is able to compare tree nodes according to their index.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public abstract class TreeNodeIndexComparator
    implements  Comparator
{
    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference m__Singleton;

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected TreeNodeIndexComparator() {};

    /**
     * Specifies a new weak reference.
     * @param comparator the comparator instance to use.
     * @precondition comparator != null
     */
    protected static void setReference(TreeNodeIndexComparator comparator)
    {
        m__Singleton = new WeakReference(comparator);
    }

    /**
     * Retrieves the weak reference.
     * @return such reference.
     */
    protected static WeakReference getReference()
    {
        return m__Singleton;
    }

    /**
     * Retrieves a TreeNodeIndexComparator instance.
     * @return such instance.
     */
    public static TreeNodeIndexComparator getInstance()
    {
        TreeNodeIndexComparator result = null;

        WeakReference t_Reference = getReference();

        if  (t_Reference != null) 
        {
            result = (TreeNodeIndexComparator) t_Reference.get();
        }

        if  (result == null)
        {
            result = new TreeNodeIndexComparator() {};

            setReference(result);
        }
        
        return result;
    }

    /**
     * Compares given objects.
     * @param first the first object.
     * @param second the second object.
     * @return a negative integer, zero, or a positive integer as the first
     * node's index is less than, equal to, or greater than the second's.
     * @precondition first != null
     * @precondition first instanceof org.acmsl.undigester.TreeNode
     * @precondition second != null
     * @precondition second instanceof org.acmsl.undigester.TreeNode
     */
    public int compare(final Object first, final Object second)
    {
        return compare((TreeNode) first, (TreeNode) second);
    }

    /**
     * Compares given objects.
     * @param first the first node.
     * @param second the second node.
     * @return a negative integer, zero, or a positive integer as the first
     * node's index is less than, equal to, or greater than the second's.
     * @precondition first != null
     * @precondition second != null
     */
    protected int compare(final TreeNode first, final TreeNode second)
    {
        return compare(first.getIndex(), second.getIndex());
    }

    /**
     * Compares given objects.
     * @param first the index of the first node.
     * @param second the index of the second node.
     * @return a negative integer, zero, or a positive integer as the first
     * node's index is less than, equal to, or greater than the second's.
     */
    protected int compare(final int first, final int second)
    {
        int result = 0;

        if  (first > second)
        {
            result = 1;
        }
        else if  (first < second)
        {
            result = -1;
        }

        return result;
    }

    /**
     * Checks if given object is semantically equivalent to this one.
     * @param object the object to compare.
     * @return <code>true</code> if given object is equivalent to this
     * comparator.
     */
    public boolean equals(final Object object)
    {
        // Since this class is a stateless singleton, if the class matches,
        // the object is equivalent. No derived classes expected.
        return
            (   (object != null)
             && (object instanceof TreeNodeIndexComparator));
    }
}
