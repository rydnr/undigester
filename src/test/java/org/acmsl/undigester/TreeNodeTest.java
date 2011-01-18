/*
                      Project tests

Copyright (C) 2003  Jose San Leandro Armend?riz
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
Urb. Valdecaba?as
Boadilla del monte
28660 Madrid
Spain

******************************************************************************
*
* Filename: $RCSfile$
*
* Author: Jose San Leandro Armend?riz
*
* Description: Executes all tests defined for package
*              unittests.org.acmsl.undigester.
*
* Last modified by: $Author$ at $Date$
*
* File version: $Revision$
*
* Project version: $Name$
*
* $Id$
*/
package unittests.org.acmsl.undigester;


/*
* Importing project classes.
*/

// JUnitDoclet begin import
import org.acmsl.undigester.TreeNode;

// JUnitDoclet end import

/*
* Importing JUnit classes.
*/
import junit.framework.TestCase;

/*
This file is part of  JUnitDoclet, a project to generate basic
test cases  from source code and  helping to keep them in sync
during refactoring.

Copyright (C) 2002  ObjectFab GmbH  (http://www.objectfab.de/)

This library is  free software; you can redistribute it and/or
modify  it under the  terms of  the  GNU Lesser General Public
License as published  by the  Free Software Foundation; either
version 2.1  of the  License, or  (at your option)  any  later
version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or  FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You  should  have  received a  copy of the  GNU Lesser General
Public License along with this  library; if not, write  to the
Free  Software  Foundation, Inc.,  59 Temple Place,  Suite 330,
Boston, MA  02111-1307  USA
*/

/**
* Tests TreeNodeTest class.
* @version $Revision$
* @see org.acmsl.undigester.TreeNode
*/
public class TreeNodeTest
    extends TestCase
// JUnitDoclet end extends_implements
{
    // JUnitDoclet begin class
    org.acmsl.undigester.TreeNode treenode = null;

    // JUnitDoclet end class

    /**
    * Creates a TreeNodeTest with given name.
    * @param name such name.
    */
    public TreeNodeTest(String name)
    {
        // JUnitDoclet begin method TreeNodeTest
        super(name);

        // JUnitDoclet end method TreeNodeTest
    }

    /**
    * Creates an instance of the tested class.
    * @return such instance.

    */
    public org.acmsl.undigester.TreeNode createInstance()
      throws Exception
    {
        // JUnitDoclet begin method testcase.createInstance
        return
            new org.acmsl.undigester.TreeNode("id", "web-app");
        // JUnitDoclet end method testcase.createInstance
    }

    /**
    * Performs any required steps before each test.
    * @throws Exception if an unexpected situation occurs.
    */
    protected void setUp()
      throws Exception
    {
        // JUnitDoclet begin method testcase.setUp
        super.setUp();
        treenode = createInstance();

        // JUnitDoclet end method testcase.setUp
    }

    /**
    * Performs any required steps after each test.
    * @throws Exception if an unexpected situation occurs.
    */
    protected void tearDown()
      throws Exception
    {
        // JUnitDoclet begin method testcase.tearDown
        treenode = null;
        super.tearDown();

        // JUnitDoclet end method testcase.tearDown
    }

    /**
    * Tests TreeNodeTestgetNodeId()
    * @throws Exception if an unexpected situation occurs.
    * @see org.acmsl.undigester.TreeNode#getNodeId()
    */
    public void testGetNodeId()
      throws Exception
    {
        // JUnitDoclet begin method getNodeId
        // JUnitDoclet end method getNodeId
    }

    /**
    * Tests TreeNodeTestgetObject()
    * @throws Exception if an unexpected situation occurs.
    * @see org.acmsl.undigester.TreeNode#getObject()
    */
    public void testGetObject()
      throws Exception
    {
        // JUnitDoclet begin method getObject
        // JUnitDoclet end method getObject
    }

    /**
    * Tests TreeNodeTestgetParent()
    * @throws Exception if an unexpected situation occurs.
    * @see org.acmsl.undigester.TreeNode#getParent()
    */
    public void testGetParent()
      throws Exception
    {
        // JUnitDoclet begin method getParent
        // JUnitDoclet end method getParent
    }

    /**
    * Tests TreeNodeTestgetNodeName()
    * @throws Exception if an unexpected situation occurs.
    * @see org.acmsl.undigester.TreeNode#getNodeName()
    */
    public void testGetNodeName()
      throws Exception
    {
        // JUnitDoclet begin method getNodeName
        // JUnitDoclet end method getNodeName
    }

    /**
    * Tests TreeNodeTestgetSpecializedPattern()
    * @throws Exception if an unexpected situation occurs.
    * @see org.acmsl.undigester.TreeNode#getSpecializedPattern()
    */
    public void testGetSpecializedPattern()
      throws Exception
    {
        // JUnitDoclet begin method getSpecializedPattern
        // JUnitDoclet end method getSpecializedPattern
    }

    /**
    * Tests TreeNodeTestgetBody()
    * @throws Exception if an unexpected situation occurs.
    * @see org.acmsl.undigester.TreeNode#getBody()
    */
    public void testGetBody()
      throws Exception
    {
        // JUnitDoclet begin method getBody
        // JUnitDoclet end method getBody
    }

    /**
    * Tests TreeNodeTestaddAttribute()
    * @throws Exception if an unexpected situation occurs.
    * @see org.acmsl.undigester.TreeNode#addAttribute(java.lang.String, java.lang.String)
    */
    public void testAddAttribute()
      throws Exception
    {
        // JUnitDoclet begin method addAttribute
        // JUnitDoclet end method addAttribute
    }

    /**
    * Tests TreeNodeTesttoXml()
    * @throws Exception if an unexpected situation occurs.
    * @see org.acmsl.undigester.TreeNode#toXml()
    */
    public void testToXml()
      throws Exception
    {
        // JUnitDoclet begin method toXml
        // JUnitDoclet end method toXml
    }

    /**
    * JUnitDoclet moves marker to this method, if there is not match
    * for them in the regenerated code and if the marker is not empty.
    * This way, no test gets lost when regenerating after renaming.
    * Method testVault is supposed to be empty.
    * @throws Exception if an unexpected situation occurs.
    */
    public void testVault()
      throws Exception
    {
        // JUnitDoclet begin method testcase.testVault
        // JUnitDoclet end method testcase.testVault
    }

    public static void main(String[] args)
    {
        // JUnitDoclet begin method testcase.main
        junit.textui.TestRunner.run(TreeNodeTest.class);

        // JUnitDoclet end method testcase.main
    }
}
