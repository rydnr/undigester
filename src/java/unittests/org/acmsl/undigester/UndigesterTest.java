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
import org.acmsl.undigester.ChainableRule;
import org.acmsl.undigester.GetNameRule;
import org.acmsl.undigester.GetNextRule;
import org.acmsl.undigester.GetPropertyRule;
import org.acmsl.undigester.Undigester;
import org.acmsl.undigester.UndigesterException;
import org.acmsl.undigester.ToStringRule;
import java.util.ArrayList;
import java.util.Collection;
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
1version 2.1  of the  License, or  (at your option)  any  later
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
* Tests UndigesterTest class.
* @version $Revision$
* @see org.acmsl.undigester.Undigester
*/
public class UndigesterTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
    /**
     * Tests the Undigester#unparse() method.
     * @see org.acmsl.undigester.Undigester#unparse()
     */
    public void testFlatElementUnparse()
    {
        Son t_Son = new Son("name-s_feu", "phone-s_feu", "address-s_feu");

        Undigester t_Undigester =
            new Undigester(t_Son);

        t_Undigester.addRule(
            new GetNameRule(
                t_Son.getClass().getName()));

        t_Undigester.addRule(
            new GetPropertyRule(
                t_Son.getClass().getName(),
                "name",
                true));

        t_Undigester.addRule(
            new GetPropertyRule(
                t_Son.getClass().getName(),
                "phone",
                true));

        try 
        {
            String t_strResult = t_Undigester.unparse();

            assertTrue(t_strResult != null);

            assertEquals(
                  "<son><name>name-s_feu</name><phone>phone-s_feu</phone>"
                + "</son>",
                t_strResult);
        }
        catch  (final UndigesterException undigesterException)
        {
            fail("" + undigesterException.getMessage());
        }
    }

    /**
     * Tests the Undigester#unparse() method.
     * @see org.acmsl.undigester.Undigester#unparse()
     */
    public void testFlatMixUnparse()
    {
        Son t_Son = new Son("name-s_fmu", "phone-s_fmu", "address-s_fmu");

        Undigester t_Undigester =
            new Undigester(t_Son);

        t_Undigester.addRule(
            new GetNameRule(
                t_Son.getClass().getName()));

        t_Undigester.addRule(
            new GetPropertyRule(
                t_Son.getClass().getName(),
                "name"));

        t_Undigester.addRule(
            new GetPropertyRule(
                t_Son.getClass().getName(),
                "phone",
                true));

        try 
        {
            String t_strResult = t_Undigester.unparse();

            assertTrue(t_strResult != null);

            assertEquals(
                  "<son name=\"name-s_fmu\"><phone>phone-s_fmu</phone>"
                + "</son>",
                t_strResult);
        }
        catch  (final UndigesterException undigesterException)
        {
            fail("" + undigesterException.getMessage());
        }
    }

    /**
     * Tests the Undigester#unparse() method.
     * @see org.acmsl.undigester.Undigester#unparse()
     */
    public void testFlatMix2Unparse()
    {
        Son t_Son = new Son("name-s_fm2u", "phone-s_fm2u", "address-s_fm2u");

        Undigester t_Undigester =
            new Undigester(t_Son);

        t_Undigester.addRule(
            new GetNameRule(
                t_Son.getClass().getName()));

        t_Undigester.addRule(
            new GetPropertyRule(
                t_Son.getClass().getName(),
                "name",
                true));

        t_Undigester.addRule(
            new GetPropertyRule(
                t_Son.getClass().getName(),
                "phone"));

        try 
        {
            String t_strResult = t_Undigester.unparse();

            assertTrue(t_strResult != null);

            assertEquals(
                  "<son phone=\"phone-s_fm2u\"><name>name-s_fm2u</name>"
                + "</son>",
                t_strResult);
        }
        catch  (final UndigesterException undigesterException)
        {
            fail("" + undigesterException.getMessage());
        }
    }

    /**
     * Tests the Undigester#unparse() method.
     * @see org.acmsl.undigester.Undigester#unparse()
     */
    public void test2LevelUnparse()
    {
        Son t_Son = new Son("name-s_2lu", "phone-s_2lu", "address-s_2lu");

        Undigester t_Undigester =
            new Undigester(t_Son);

        t_Undigester.addRule(
            new GetNameRule(
                t_Son.getClass().getName()));

        t_Undigester.addRule(
            new GetPropertyRule(
                t_Son.getClass().getName(),
                "name"));

        ChainableRule t_GetNextRule =
            new GetNextRule(
                t_Son.getClass().getName(),
                "getPhone");

        t_Undigester.addRule(t_GetNextRule);

        t_Undigester.addRule(
            new GetNameRule(
                  t_Son.getClass().getName()
                + "/"
                + String.class.getName(),
                "phone-element",
                t_GetNextRule));

        t_Undigester.addRule(
            new ToStringRule(
                  t_Son.getClass().getName()
                + "/"
                + String.class.getName(),
                "phone",
                false,
                t_GetNextRule));

        try 
        {
            String t_strResult = t_Undigester.unparse();

            assertTrue(t_strResult != null);

            assertEquals(
                  "<son name=\"name-s_2lu\">"
                + "<phone-element phone=\"phone-s_2lu\"/>"
                + "</son>",
                t_strResult);
        }
        catch  (final UndigesterException undigesterException)
        {
            fail("" + undigesterException.getMessage());
        }
    }
    /**
     * Tests the Undigester#unparse() method.
     * @see org.acmsl.undigester.Undigester#unparse()
     */
    public void testArray2LevelUnparse()
    {
        Father t_Father =
            new Father(
                null,
                "name-f_a2lu",
                new String[]
                {
                    "phone-f_a2lu-1",
                    "phone-f_a2lu-2",
                    "phone-f_a2lu-3"
                },
                "address-f_a2lu");

        Undigester t_Undigester =
            new Undigester(t_Father);

        t_Undigester.addRule(
            new GetNameRule(
                t_Father.getClass().getName()));

        t_Undigester.addRule(
            new GetPropertyRule(
                t_Father.getClass().getName(),
                "name"));

        GetNextRule t_GetNextRule =
            new GetNextRule(
                t_Father.getClass().getName(),
                "getPhones");

        t_Undigester.addRule(t_GetNextRule);

        t_Undigester.addRule(
            new GetNameRule(
                  t_Father.getClass().getName()
                + "/"
                + String.class.getName(),
                "phone-element",
                t_GetNextRule));

        t_Undigester.addRule(
            new ToStringRule(
                  t_Father.getClass().getName()
                + "/"
                + String.class.getName(),
                "phone",
                false,
                t_GetNextRule));

        try 
        {
            String t_strResult = t_Undigester.unparse();

            assertTrue(t_strResult != null);

            assertEquals(
                  "<father name=\"name-f_a2lu\">"
                + "<phone-element phone=\"phone-f_a2lu-1\"/>"
                + "<phone-element phone=\"phone-f_a2lu-2\"/>"
                + "<phone-element phone=\"phone-f_a2lu-3\"/>"
                + "</father>",
                t_strResult);
        }
        catch  (final UndigesterException undigesterException)
        {
            fail("" + undigesterException.getMessage());
        }
    }

    /**
     * Tests the Undigester#unparse() method.
     * @see org.acmsl.undigester.Undigester#unparse()
     */
    public void testArray3LevelUnparse()
    {
        GrandFather t_GrandFather =
            new GrandFather(
                null,
                "name-gf_a3lu",
                new String[]
                {
                    "phone-gf_a3lu-1",
                    "phone-gf_a3lu-2",
                    "phone-gf_a3lu-3",
                    "phone-gf_a3lu-4"
                },
                "address-gf_a3lu");

        Father t_Father =
            new Father(
                t_GrandFather,
                "name-f_a3lu",
                new String[]
                {
                    "phone-f_a3lu-1",
                    "phone-f_a3lu-2",
                    "phone-f_a3lu-3"
                },
                "address-f_a3lu");

        t_GrandFather.addChild(t_Father);

        Undigester t_Undigester =
            new Undigester(t_GrandFather);

        t_Undigester.addRule(
            new GetNameRule(
                t_GrandFather.getClass().getName()));

        t_Undigester.addRule(
            new GetPropertyRule(
                t_GrandFather.getClass().getName(),
                "name"));

        GetNextRule t_GetNextRule =
            new GetNextRule(
                t_GrandFather.getClass().getName(),
                "getPhones");

        t_Undigester.addRule(t_GetNextRule);

        t_Undigester.addRule(
            new GetNameRule(
                  t_GrandFather.getClass().getName()
                + "/"
                + String.class.getName(),
                "phone-element",
                t_GetNextRule));

        t_Undigester.addRule(
            new ToStringRule(
                  t_GrandFather.getClass().getName()
                + "/"
                + String.class.getName(),
                "phone",
                false,
                t_GetNextRule));

        t_GetNextRule =
            new GetNextRule(
                t_GrandFather.getClass().getName(),
                "getChildren");

        t_Undigester.addRule(t_GetNextRule);

        t_Undigester.addRule(
            new GetNameRule(
                  t_GrandFather.getClass().getName()
                + "/"
                + t_Father.getClass().getName(),
                "house-element",
                t_GetNextRule));

        t_Undigester.addRule(
            new GetPropertyRule(
                  t_GrandFather.getClass().getName()
                + "/"
                + t_Father.getClass().getName(),
                "address",
                t_GetNextRule));

        GetNextRule t_SecondGetNextRule =
            new GetNextRule(
                  t_GrandFather.getClass().getName()
                + "/"
                + t_Father.getClass().getName(),
                "getPhones",
                t_GetNextRule);

        t_Undigester.addRule(t_SecondGetNextRule);

        t_Undigester.addRule(
            new GetNameRule(
                  t_GrandFather.getClass().getName()
                + "/"
                + t_Father.getClass().getName()
                + "/"
                + String.class.getName(),
                "phone-element",
                t_SecondGetNextRule));

        t_Undigester.addRule(
            new ToStringRule(
                  t_GrandFather.getClass().getName()
                + "/"
                + t_Father.getClass().getName()
                + "/"
                + String.class.getName(),
                "phone",
                false,
                t_SecondGetNextRule));

        try 
        {
            String t_strResult = t_Undigester.unparse();

            assertTrue(t_strResult != null);

            assertEquals(
                  "<grandfather name=\"name-gf_a3lu\">"
                + "<phone-element phone=\"phone-gf_a3lu-1\"/>"
                + "<phone-element phone=\"phone-gf_a3lu-2\"/>"
                + "<phone-element phone=\"phone-gf_a3lu-3\"/>"
                + "<phone-element phone=\"phone-gf_a3lu-4\"/>"
                + "<house-element address=\"address-f_a3lu\">"
                + "<phone-element phone=\"phone-f_a3lu-1\"/>"
                + "<phone-element phone=\"phone-f_a3lu-2\"/>"
                + "<phone-element phone=\"phone-f_a3lu-3\"/>"
                + "</house-element>"
                + "</grandfather>",
                t_strResult);
        }
        catch  (final UndigesterException undigesterException)
        {
            fail("" + undigesterException.getMessage());
        }
    }
  // JUnitDoclet end class
  
  /**
  * Creates a UndigesterTest with given name.
  * @param name such name.
  */
  public UndigesterTest(String name)
  {
    // JUnitDoclet begin method UndigesterTest
    super(name);
    // JUnitDoclet end method UndigesterTest
  }
  
  /**
  * Creates an instance of the tested class.
  * @return such instance.
  
  */
  public org.acmsl.undigester.Undigester createInstance()
  throws Exception
  {
    // JUnitDoclet begin method testcase.createInstance
    return null;
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
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  /**
  * Tests UndigesterTestgetRoot()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.Undigester#getRoot()
  */
  public void testGetRoot()
  throws Exception
  {
    // JUnitDoclet begin method getRoot
      Son t_Son = new Son("name-s_gr", "phone-s_gr", "address-s_gr");

      Undigester t_Undigester = new Undigester(t_Son);

      assertTrue(t_Son.equals(t_Undigester.getRoot()));
    // JUnitDoclet end method getRoot
  }
  
  /**
  * Tests UndigesterTestaddRules()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.Undigester#addRules(org.acmsl.undigester.RuleSet)
  */
  public void testAddRules()
  throws Exception
  {
    // JUnitDoclet begin method addRules
    // JUnitDoclet end method addRules
  }
  
  /**
  * Tests UndigesterTestaddRule()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.Undigester#addRule(org.acmsl.undigester.Rule)
  */
  public void testAddRule()
  throws Exception
  {
    // JUnitDoclet begin method addRule
      Son t_Son = new Son("name-s_ar", "phone-s_ar", "address-s_ar");

      Undigester t_Undigester = new Undigester(t_Son);
      
      t_Undigester.addRule(
          new GetNameRule(t_Son.getClass().getName()));
    // JUnitDoclet end method addRule
  }
  
  /**
  * Tests UndigesterTestgetRules()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.Undigester#getRules(org.acmsl.undigester.TreeNode)
  */
  public void testGetRules()
  throws Exception
  {
    // JUnitDoclet begin method getRules
      Son t_Son = new Son("name-s_grs", "phone-s_grs", "address-s_grs");

      Undigester t_Undigester = new Undigester(t_Son);

      t_Undigester.addRule(
          new GetNameRule(t_Son.getClass().getName()));
    // JUnitDoclet end method getRules
  }
  
  /**
  * Tests UndigesterTestunparse()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.undigester.Undigester#unparse()
  */
  public void testUnparse()
  throws Exception
  {
    // JUnitDoclet begin method unparse
      Son t_Son = new Son("name-s_u", "phone-s_u", "address-s_u");

      Undigester t_Undigester = new Undigester(t_Son);

      t_Undigester.addRule(
          new GetNameRule(t_Son.getClass().getName()));

      t_Undigester.addRule(
          new GetPropertyRule(
              t_Son.getClass().getName(), "name"));

      t_Undigester.addRule(
          new GetPropertyRule(
              t_Son.getClass().getName(), "phone"));

      try 
      {
          String t_strResult = t_Undigester.unparse();

          assertTrue(t_strResult != null);

          assertEquals(
              "<son name=\"name-s_u\" phone=\"phone-s_u\"/>",
              t_strResult);
      }
      catch  (final UndigesterException undigesterException)
      {
          fail("" + undigesterException.getMessage());
      }
    // JUnitDoclet end method unparse
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
    junit.textui.TestRunner.run(UndigesterTest.class);
    // JUnitDoclet end method testcase.main
  }
}
