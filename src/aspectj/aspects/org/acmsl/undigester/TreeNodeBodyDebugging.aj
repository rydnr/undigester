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
    Contact info: jsanleandro@yahoo.es
    Postal Address: c/Playa de Lagoa, 1
                    Urb. Valdecaba&ntilde;as
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armendáriz
 *
 * Description: Introduces a new tree node property, with its body,
 *              and synchronizes it with the current StringBuffer property,
 *              since such existing member variable is much difficult to
 *              inspect while debugging.
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
package aspects.org.acmsl.undigester;

/**
 * Importing project classes.
 */
import org.acmsl.undigester.CallMethodRule;
import org.acmsl.undigester.Rule;
import org.acmsl.undigester.TreeNode;

/*
 * Importing JDK classes.
 */
import java.lang.StringBuffer;

/**
 * Introduces a new tree node property, with its body,
 * and synchronizes it with the current StringBuffer property, since
 * such existing member variable is much difficult to inspect while
 * debugging.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public aspect TreeNodeBodyDebugging
{
    /**
     * Introduces a new TreeNode field.
     */
    public String TreeNode.body;

    /**
     * Matches the execution of Rule.apply()
     */
    pointcut applyingRule(TreeNode treeNode) :
           execution(void CallMethodRule.apply(TreeNode))
        && args(treeNode);

    /**
     * Matches the call to TreeNode.getBody().
     *
    pointcut retrievingBody(TreeNode treeNode) :
           (call(StringBuffer TreeNode.getBody()))
        && (target(treeNode))
        && (cflowBelow(applyingRule(treeNode)));
    */

    /**
     * Matches the call to TreeNode.body.append().
     *
    pointcut appending(String text) :
           (call(StringBuffer StringBuffer.append(String)))
        && (args(text))
        && (!within(java.lang.StringBuffer))
        && (cflow(applyingRule()));
    */

    /**
     * Ties all pointcuts.
     *
    pointcut appendingBody(TreeNode treeNode) :
           (retrievingBody(treeNode))
        && (applyingRule());
    */

    /**
     * Updates the new variable member after the tree node body
     * is updated.
     *
    StringBuffer around(TreeNode treeNode): retrievingBody(treeNode)
    {
        StringBuffer result = proceed(treeNode);

        if  (result != null)
        {
            treeNode.body = result.toString();
        }

        return result;
    }
    */

    /**
     * Updates the new variable member after the tree node body
     * is updated.
     */
    after(TreeNode treeNode) returning : applyingRule(treeNode)
    {
        treeNode.body = treeNode.getBody().toString();
    }
}
