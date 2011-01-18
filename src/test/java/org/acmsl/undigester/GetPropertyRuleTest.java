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
import org.acmsl.undigester.GetPropertyRule;
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
* Tests GetPropertyRuleTest class.
* @version $Revision$
* @see org.acmsl.undigester.GetPropertyRule
*/
public class GetPropertyRuleTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  org.acmsl.undigester.GetPropertyRule getpropertyrule = null;
  // JUnitDoclet end class
  
  /**
  * Creates a GetPropertyRuleTest with given name.
  * @param name such name.
  */
  public GetPropertyRuleTest(String name)
  {
    // JUnitDoclet begin method GetPropertyRuleTest
    super(name);
    // JUnitDoclet end method GetPropertyRuleTest
  }
  
  /**
  * Creates an instance of the tested class.
  * @return such instance.
  
  */
  public org.acmsl.undigester.GetPropertyRule createInstance()
  throws Exception
  {
    // JUnitDoclet begin method testcase.createInstance
    return new org.acmsl.undigester.GetPropertyRule(
        Son.class.getName(), "name");
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
    getpropertyrule = createInstance();
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
    getpropertyrule = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  /**
  * Tests GetPropertyRuleTestgetPropertyName()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.GetPropertyRule#getPropertyName()
  */
  public void testGetPropertyName()
  throws Exception
  {
    // JUnitDoclet begin method getPropertyName
    // JUnitDoclet end method getPropertyName
  }
  
  /**
  * Tests GetPropertyRuleTestsetAsElement()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.GetPropertyRule#setAsElement(boolean)
  */
  public void testSetAsElement()
  throws Exception
  {
    // JUnitDoclet begin method setAsElement
    // JUnitDoclet end method setAsElement
  }
  
  /**
  * Tests GetPropertyRuleTestasElement()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.GetPropertyRule#asElement()
  */
  public void testAsElement()
  throws Exception
  {
    // JUnitDoclet begin method asElement
    // JUnitDoclet end method asElement
  }
  
  /**
  * Tests GetPropertyRuleTestsetOutputNull()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.GetPropertyRule#setOutputNull(boolean)
  */
  public void testSetOutputNull()
  throws Exception
  {
    // JUnitDoclet begin method setOutputNull
    // JUnitDoclet end method setOutputNull
  }
  
  /**
  * Tests GetPropertyRuleTestoutputNull()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.GetPropertyRule#outputNull()
  */
  public void testOutputNull()
  throws Exception
  {
    // JUnitDoclet begin method outputNull
    // JUnitDoclet end method outputNull
  }
  
  /**
  * Tests GetPropertyRuleTestapply()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.GetPropertyRule#apply(org.acmsl.undigester.TreeNode)
  */
  public void testApply()
  throws Exception
  {
    // JUnitDoclet begin method apply
    // JUnitDoclet end method apply
  }
  
  /**
  * Tests GetPropertyRuleTesttoString()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.GetPropertyRule#toString()
  */
  public void testToString()
  throws Exception
  {
    // JUnitDoclet begin method toString
    // JUnitDoclet end method toString
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
    junit.textui.TestRunner.run(GetPropertyRuleTest.class);
    // JUnitDoclet end method testcase.main
  }
}
