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
 * Description:  Is able to walk tree nodes.
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
import org.acmsl.undigester.TreeNode;

/*
 * Importing some Jakarta Collections classes.
 */
import org.apache.commons.collections.ArrayStack;

/*
 * Importing JDK classes.
 */
import java.util.Collection;
import java.util.Iterator;

/**
 * Is able to walk tree nodes.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$ ($Date$ by $Author$)
 */
public class TreeVisitor
{
    /**
     * The starting node.
     */
    private TreeNode m__Start;

    /**
     * The current node.
     */
    private TreeNode m__Current;

    /**
     * The node iterator.
     */
    private Iterator m__NodeIterator;

    /**
     * The subiterators stack.
     */
    private ArrayStack m__asSubiterators;

    /**
     * Creates a tree node visitor with given information.
     * @param start the root of the tree to walk.
     * @precondition start != null
     */
    public TreeVisitor(final TreeNode start)
    {
        inmutableSetStart(start);
    }

    /**
     * Specifies the start node.
     * @param start such node.
     */
    private void inmutableSetStart(final TreeNode start)
    {
        m__Start = start;
    }

    /**
     * Specifies the start node.
     * @param start such node.
     */
    protected void setStart(final TreeNode start)
    {
        inmutableSetStart(start);
    }

    /**
     * Retrieves the start node.
     * @return such node.
     */
    public TreeNode getStartNode()
    {
        return m__Start;
    }

    /**
     * Specifies the current node.
     * @param current such node.
     */
    protected void setCurrentNode(final TreeNode current)
    {
        m__Current = current;
    }

    /**
     * Retrieves the current node.
     * @return such node.
     */
    public TreeNode getCurrentNode()
    {
        return m__Current;
    }

    /**
     * Specifies the node iterator.
     * @param iterator such iterator.
     */
    protected void setNodeIterator(final Iterator iterator)
    {
        m__NodeIterator = iterator;
    }

    /**
     * Retrieves the node iterator.
     * @return such iterator.
     */
    public Iterator getNodeIterator()
    {
        return m__NodeIterator;
    }

    /**
     * Specifies the subiterators' stack.
     * @param stack such stack.
     */
    protected void setSubiterators(final ArrayStack stack)
    {
        m__asSubiterators = stack;
    }

    /**
     * Retrieves the subiterators' stack.
     * @return such stack.
     */
    protected ArrayStack getSubiterators()
    {
        return m__asSubiterators;
    }

    /**
     * Retrieves whether there're more nodes to visit.
     * @return such information.
     */
    public boolean hasMoreNodes()
    {
        return
            hasMoreNodes(
                getStartNode(),
                getCurrentNode(),
                getNodeIterator(),
                getSubiterators());

    }

    /**
     * Retrieves whether there're more nodes to visit.
     * @param start the start node.
     * @param current the current node.
     * @param iterator the node iterator.
     * @param subiterators the subiterators.
     * @return such information.
     * @precondition start != null
     */
    protected boolean hasMoreNodes(
        final TreeNode start,
        final TreeNode current,
        final Iterator iterator,
        final ArrayStack subiterators)
    {
        boolean result = false;

        if  (   (subiterators == null)
             || (subiterators.size() == 0))
        {
            // Browsing a level no deeper than 1.

            if  (current == null)
            {
                setCurrentNode(start);

                result = true;
            }
            else if  (iterator == null)
            {
                // Starting from the left-most first-level child.
                Collection t_cChildren = start.getChildren();

                result = (   (t_cChildren != null)
                          && (t_cChildren.size() > 0));
            }
            else
            {
                // Do we have more first-level children?
                result = iterator.hasNext();
            }
        }
        else
        {
            // Retrieving the current iterator from the stack.
            Iterator t_Iterator = (Iterator) subiterators.pop();

            result = t_Iterator.hasNext();
        }

        return result;
    }

    /**
     * Retrieves the next node.
     * @return such node.
     */
    protected TreeNode nextNode()
    {
        return
            nextNode(
                getStartNode(),
                getCurrentNode(),
                getSubiterators(),
                getNodeIterator());
    }

    /**
     * Retrieves the next node.
     * @param start the start node.
     * @param current the current node.
     * @param subiterators the subiterators stack.
     * @param iterator the first-level iterator.
     * @return such node.
     * @precondition start != null
     */
    protected TreeNode nextNode(
        final TreeNode start,
        final TreeNode current,
        final ArrayStack subiterators,
        final Iterator iterator)
    {
        TreeNode result = null;

        if  (   (subiterators == null)
             || (subiterators.size() == 0))
        {
            // Browsing a level no deeper than 1.

            if  (current == start)
            {
                result = start;
            }
            else
            {
                Iterator t_Iterator = iterator;

                if  (t_Iterator == null)
                {
                    // Starting from the left-most first-level child.
                    Collection t_cChildren = start.getChildren();

                    if  (t_cChildren != null)
                    {
                        t_Iterator = t_cChildren.iterator();

                        setNodeIterator(t_Iterator);
                    }
                }

                if  (   (t_Iterator != null)
                     && (t_Iterator.hasNext()))
                {
                    result = (TreeNode) iterator.next();

                    ArrayStack t_asSubiterators = new ArrayStack();
                    setSubiterators(t_asSubiterators);

                    Collection t_cCollection = result.getChildren();

                    if  (t_cCollection != null)
                    {
                        t_asSubiterators.push(
                            t_cCollection.iterator());
                    }
                }
            }
        }
        else
        {
            Iterator t_Iterator = (Iterator) subiterators.peek();

            if  (t_Iterator.hasNext())
            {
                result = (TreeNode) t_Iterator.next();
            }
            else
            {
                // Removing exhausted iterator.
                subiterators.pop();

                // Recursion
                result = nextNode();
            }
        }

        setCurrentNode(result);

        return result;
    }
}
