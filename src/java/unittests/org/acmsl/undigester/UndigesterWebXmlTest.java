/*
                        Undigester

    Copyright (C) 2003-2004  Jose San Leandro Armend&aacute;riz
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
 * Description: Performs some unit tests associated to web.xml serialization
 *              on Undigester class.
 *
 * Last modified by: $Author$ at $Date$
 *
 * File version: $Revision$
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id$
 *
 */
package unittests.org.acmsl.undigester;

/*
 * Importing some ACMSL classes.
 */
import org.acmsl.undigester.CallMethodRule;
import org.acmsl.undigester.GetNameRule;
import org.acmsl.undigester.GetNextRule;
import org.acmsl.undigester.GetPropertyRule;
import org.acmsl.undigester.Rule;
import org.acmsl.undigester.Undigester;
import org.acmsl.undigester.UndigesterException;

/**
 * Importing JUnit classes.
 */
import junit.framework.TestCase;

/*
 * Importing JDK classes.
 */
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Performs some unit tests associated to web.xml serialization
 * on Undigester class.
 * @testfamily JUnit
 * @testkind testcase
 * @testsetup Default TestCase
 * @testedclass org.acmsl.undigester.Undigester
 * @see org.acmsl.undigester.Undigester
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class UndigesterWebXmlTest
    extends  TestCase
{
    /**
     * Performs other tests on undigester class.
     */
    public void testSimpleServletAndWelcomeFile()
    {
        try 
        {
            String t_strSuccessful =
                  "<web-app>"
                +   "<display-name>DisplayName</display-name>"
                +   "<servlet>"
                +     "<servlet-name>action</servlet-name>"
                +     "<servlet-class>org.apache.struts.action.ActionServlet"
                +     "</servlet-class>"
                +   "</servlet>"
                +   "<servlet>"
                +     "<servlet-name>bshservlet</servlet-name>"
                +     "<servlet-class>bsh.servlet.BshServlet</servlet-class>"
                +   "</servlet>"
                +   "<welcome-file-list>"
                +     "<welcome-file>index.jsp</welcome-file>"
                +   "</welcome-file-list>"
                + "</web-app>";

            WebApp t_WebApp = new WebApp("DisplayName");

            Undigester t_Undigester = new Undigester(t_WebApp);

            Servlet t_Servlet =
                new Servlet(
                    "action", "org.apache.struts.action.ActionServlet");

            t_WebApp.addServlet(t_Servlet);

            t_Servlet = new Servlet("bshservlet", "bsh.servlet.BshServlet");

            t_WebApp.addServlet(t_Servlet);

            t_WebApp.addWelcomeFile("index.jsp");

            Rule t_Rule =
                new GetNameRule(WebApp.class.getName(), "web-app");

            t_Undigester.addRule(t_Rule);

            GetNextRule t_GetNextRule =
                new GetNextRule(WebApp.class.getName(), "getDisplayName");

            t_Undigester.addRule(t_GetNextRule);

            t_Rule =
                new GetNameRule(
                    WebApp.class.getName() + "/" + IdText.class.getName(),
                    "display-name",
                    t_GetNextRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                    WebApp.class.getName() + "/" + IdText.class.getName(),
                    "getText",
                    new Object[0], // args
                    t_GetNextRule);

            t_Undigester.addRule(t_Rule);

            t_GetNextRule =
                new GetNextRule(
                    WebApp.class.getName(),
                    "getServletArray");

            t_Undigester.addRule(t_GetNextRule);

            t_Rule =
                new GetNameRule(
                    WebApp.class.getName() + "/" + Servlet.class.getName(),
                    "servlet",
                    t_GetNextRule);

            t_Undigester.addRule(t_Rule);

            GetNextRule t_ServletNameRule =
                new GetNextRule(
                    WebApp.class.getName() + "/" + Servlet.class.getName(),
                    "getServletName",
                    t_GetNextRule);

            t_Undigester.addRule(t_ServletNameRule);

            t_Rule =
                new GetNameRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "servlet-name",
                    t_ServletNameRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "getText",
                    t_ServletNameRule);

            t_Undigester.addRule(t_Rule);

            GetNextRule t_ServletClassRule =
                new GetNextRule(
                    WebApp.class.getName() + "/" + Servlet.class.getName(),
                    "getServletClass",
                    t_GetNextRule);

            t_Undigester.addRule(t_ServletClassRule);

            t_Rule =
                new GetNameRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "servlet-class",
                    t_ServletClassRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "getText",
                    t_ServletClassRule);

            t_Undigester.addRule(t_Rule);

            GetNextRule t_WelcomeFileArrayRule =
              new GetNextRule(
                  WebApp.class.getName(),
                  "getWelcomeFileArray",
                  "welcome-file-list");

            t_Undigester.addRule(t_WelcomeFileArrayRule);

            t_Rule =
              new GetNameRule(
                  WebApp.class.getName() + "/" + IdText.class.getName(),
                  "welcome-file",
                  t_WelcomeFileArrayRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
              new CallMethodRule(
                  WebApp.class.getName() + "/" + IdText.class.getName(),
                  "getText",
                  t_WelcomeFileArrayRule);

            t_Undigester.addRule(t_Rule);

            String t_strResult = t_Undigester.unparse();

            assertTrue(t_strResult != null);

            assertEquals(t_strSuccessful, t_strResult);
        }
        catch  (final UndigesterException undigesterException)
        {
            fail("" + undigesterException);
        }
    }

    /**
     * Performs other tests on undigester class.
     */
    public void testWelcomeFiles()
    {
        try 
        {
            String t_strSuccessful =
                  "<web-app>"
                +   "<display-name>DisplayName</display-name>"
                +   "<servlet>"
                +     "<servlet-name>action</servlet-name>"
                +     "<servlet-class>org.apache.struts.action.ActionServlet"
                +     "</servlet-class>"
                +   "</servlet>"
                +   "<servlet>"
                +     "<servlet-name>bshservlet</servlet-name>"
                +     "<servlet-class>bsh.servlet.BshServlet</servlet-class>"
                +   "</servlet>"
                +   "<welcome-file-list>"
                +     "<welcome-file>index.jsp</welcome-file>"
                +     "<welcome-file>welcome.jsp</welcome-file>"
                +     "<welcome-file>home.jsp</welcome-file>"
                +   "</welcome-file-list>"
                + "</web-app>";

            WebApp t_WebApp = new WebApp("DisplayName");

            Servlet t_Servlet =
                new Servlet(
                    "action", "org.apache.struts.action.ActionServlet");

            t_WebApp.addServlet(t_Servlet);

            t_Servlet = new Servlet("bshservlet", "bsh.servlet.BshServlet");

            t_WebApp.addServlet(t_Servlet);

            t_WebApp.addWelcomeFile("index.jsp");
            t_WebApp.addWelcomeFile("welcome.jsp");
            t_WebApp.addWelcomeFile("home.jsp");

            Undigester t_Undigester = new Undigester(t_WebApp);

            Rule t_Rule =
                new GetNameRule(WebApp.class.getName(), "web-app");

            t_Undigester.addRule(t_Rule);

            GetNextRule t_GetNextRule =
                new GetNextRule(WebApp.class.getName(), "getDisplayName");

            t_Undigester.addRule(t_GetNextRule);

            t_Rule =
                new GetNameRule(
                    WebApp.class.getName() + "/" + IdText.class.getName(),
                    "display-name",
                    t_GetNextRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                    WebApp.class.getName() + "/" + IdText.class.getName(),
                    "getText",
                    new Object[0], // args
                    t_GetNextRule);

            t_Undigester.addRule(t_Rule);

            t_GetNextRule =
                new GetNextRule(
                    WebApp.class.getName(),
                    "getServletArray");

            t_Undigester.addRule(t_GetNextRule);

            t_Rule =
                new GetNameRule(
                    WebApp.class.getName() + "/" + Servlet.class.getName(),
                    "servlet",
                    t_GetNextRule);

            t_Undigester.addRule(t_Rule);

            GetNextRule t_ServletNameRule =
                new GetNextRule(
                    WebApp.class.getName() + "/" + Servlet.class.getName(),
                    "getServletName",
                    t_GetNextRule);

            t_Undigester.addRule(t_ServletNameRule);

            t_Rule =
                new GetNameRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "servlet-name",
                    t_ServletNameRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "getText",
                    t_ServletNameRule);

            t_Undigester.addRule(t_Rule);

            GetNextRule t_ServletClassRule =
                new GetNextRule(
                    WebApp.class.getName() + "/" + Servlet.class.getName(),
                    "getServletClass",
                    t_GetNextRule);

            t_Undigester.addRule(t_ServletClassRule);

            t_Rule =
                new GetNameRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "servlet-class",
                    t_ServletClassRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "getText",
                    t_ServletClassRule);

            t_Undigester.addRule(t_Rule);

            GetNextRule t_WelcomeFileArrayRule =
              new GetNextRule(
                  WebApp.class.getName(),
                  "getWelcomeFileArray",
                  "welcome-file-list");

            t_Undigester.addRule(t_WelcomeFileArrayRule);

            t_Rule =
              new GetNameRule(
                  WebApp.class.getName() + "/" + IdText.class.getName(),
                  "welcome-file",
                  t_WelcomeFileArrayRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
              new CallMethodRule(
                  WebApp.class.getName() + "/" + IdText.class.getName(),
                  "getText",
                  t_WelcomeFileArrayRule);

            t_Undigester.addRule(t_Rule);

            String t_strResult = t_Undigester.unparse();

            assertTrue(t_strResult != null);

            assertEquals(t_strSuccessful, t_strResult);
        }
        catch  (final UndigesterException undigesterException)
        {
            fail("" + undigesterException);
        }
    }

    /**
     * Performs other tests on undigester class.
     */
    public void testServlets()
    {
        try 
        {
            String t_strSuccessful =
                  "<web-app>"
                +   "<display-name>DisplayName</display-name>"
                +   "<servlet>"
                +     "<servlet-name>action</servlet-name>"
                +     "<servlet-class>org.apache.struts.action.ActionServlet"
                +     "</servlet-class>"
                +     "<init-param>"
                +       "<param-name>config</param-name>"
                +       "<param-value>/WEB-INF/struts-config.xml</param-value>"
                +     "</init-param>"
                +     "<init-param>"
                +       "<param-name>debug</param-name>"
                +       "<param-value>3</param-value>"
                +     "</init-param>"
                +     "<init-param>"
                +       "<param-name>detail</param-name>"
                +       "<param-value>3</param-value>"
                +     "</init-param>"
                +     "<init-param>"
                +       "<param-name>application</param-name>"
                +       "<param-value>ApplicationResources</param-value>"
                +     "</init-param>"
                +     "<init-param>"
                +       "<param-name>convertNull</param-name>"
                +       "<param-value>true</param-value>"
                +     "</init-param>"
                +     "<load-on-startup>2</load-on-startup>"
                +   "</servlet>"
                +   "<servlet>"
                +     "<servlet-name>bshservlet</servlet-name>"
                +     "<servlet-class>bsh.servlet.BshServlet</servlet-class>"
                +   "</servlet>"
                +   "<welcome-file-list>"
                +     "<welcome-file>index.jsp</welcome-file>"
                +     "<welcome-file>welcome.jsp</welcome-file>"
                +     "<welcome-file>home.jsp</welcome-file>"
                +   "</welcome-file-list>"
                + "</web-app>";

            WebApp t_WebApp = new WebApp("DisplayName");

            Servlet t_Servlet =
                new Servlet(
                    "action",
                    "org.apache.struts.action.ActionServlet",
                    "2");

            t_Servlet.addInitParam("config", "/WEB-INF/struts-config.xml");
            t_Servlet.addInitParam("debug", "3");
            t_Servlet.addInitParam("detail", "3");
            t_Servlet.addInitParam("application", "ApplicationResources");
            t_Servlet.addInitParam("convertNull", "true");

            t_WebApp.addServlet(t_Servlet);

            t_Servlet = new Servlet("bshservlet", "bsh.servlet.BshServlet");

            t_WebApp.addServlet(t_Servlet);

            t_WebApp.addWelcomeFile("index.jsp");
            t_WebApp.addWelcomeFile("welcome.jsp");
            t_WebApp.addWelcomeFile("home.jsp");

            Undigester t_Undigester = new Undigester(t_WebApp);

            Rule t_Rule =
                new GetNameRule(WebApp.class.getName(), "web-app");

            t_Undigester.addRule(t_Rule);

            GetNextRule t_GetNextRule =
                new GetNextRule(WebApp.class.getName(), "getDisplayName");

            t_Undigester.addRule(t_GetNextRule);

            t_Rule =
                new GetNameRule(
                    WebApp.class.getName() + "/" + IdText.class.getName(),
                    "display-name",
                    t_GetNextRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                    WebApp.class.getName() + "/" + IdText.class.getName(),
                    "getText",
                    new Object[0], // args
                    t_GetNextRule);

            t_Undigester.addRule(t_Rule);

            t_GetNextRule =
                new GetNextRule(
                    WebApp.class.getName(),
                    "getServletArray");

            t_Undigester.addRule(t_GetNextRule);

            t_Rule =
                new GetNameRule(
                    WebApp.class.getName() + "/" + Servlet.class.getName(),
                    "servlet",
                    t_GetNextRule);

            t_Undigester.addRule(t_Rule);

            GetNextRule t_ServletNameRule =
                new GetNextRule(
                    WebApp.class.getName() + "/" + Servlet.class.getName(),
                    "getServletName",
                    t_GetNextRule);

            t_Undigester.addRule(t_ServletNameRule);

            t_Rule =
                new GetNameRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "servlet-name",
                    t_ServletNameRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "getText",
                    t_ServletNameRule);

            t_Undigester.addRule(t_Rule);

            GetNextRule t_ServletClassRule =
                new GetNextRule(
                    WebApp.class.getName() + "/" + Servlet.class.getName(),
                    "getServletClass",
                    t_GetNextRule);

            t_Undigester.addRule(t_ServletClassRule);

            t_Rule =
                new GetNameRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "servlet-class",
                    t_ServletClassRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "getText",
                    t_ServletClassRule);

            t_Undigester.addRule(t_Rule);

//--
            GetNextRule t_InitParamArrayRule =
                new GetNextRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName(),
                    "getInitParamArray",
                    t_GetNextRule);

            t_Undigester.addRule(t_InitParamArrayRule);

            t_Rule =
                new GetNameRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + InitParam.class.getName(),
                    "init-param",
                      t_InitParamArrayRule);

            t_Undigester.addRule(t_Rule);

            GetNextRule t_InitParamNameRule =
                new GetNextRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + InitParam.class.getName(),
                    "getName",
                    t_InitParamArrayRule);

            t_Undigester.addRule(t_InitParamNameRule);

            t_Rule =
                new GetNameRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + InitParam.class.getName()
                    + "/" + IdText.class.getName(),
                    "param-name",
                    t_InitParamNameRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + InitParam.class.getName()
                    + "/" + IdText.class.getName(),
                    "getText",
                    t_InitParamNameRule);

            t_Undigester.addRule(t_Rule);

            GetNextRule t_InitParamValueRule =
                new GetNextRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + InitParam.class.getName(),
                    "getValue",
                    t_InitParamArrayRule);

            t_Undigester.addRule(t_InitParamValueRule);

            t_Rule =
                new GetNameRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + InitParam.class.getName()
                    + "/" + IdText.class.getName(),
                    "param-value",
                    t_InitParamValueRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + InitParam.class.getName()
                    + "/" + IdText.class.getName(),
                    "getText",
                    t_InitParamValueRule);

            t_Undigester.addRule(t_Rule);

//--
            GetNextRule t_LoadOnStartupRule =
                new GetNextRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName(),
                    "getLoadOnStartup",
                    t_GetNextRule);

            t_Undigester.addRule(t_LoadOnStartupRule);

            t_Rule =
                new GetNameRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "load-on-startup",
                    t_LoadOnStartupRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
                new CallMethodRule(
                      WebApp.class.getName()
                    + "/" + Servlet.class.getName()
                    + "/" + IdText.class.getName(),
                    "getText",
                    t_LoadOnStartupRule);

            t_Undigester.addRule(t_Rule);

            GetNextRule t_WelcomeFileArrayRule =
              new GetNextRule(
                  WebApp.class.getName(),
                  "getWelcomeFileArray",
                  "welcome-file-list");

            t_Undigester.addRule(t_WelcomeFileArrayRule);

            t_Rule =
              new GetNameRule(
                  WebApp.class.getName() + "/" + IdText.class.getName(),
                  "welcome-file",
                  t_WelcomeFileArrayRule);

            t_Undigester.addRule(t_Rule);

            t_Rule =
              new CallMethodRule(
                  WebApp.class.getName() + "/" + IdText.class.getName(),
                  "getText",
                  t_WelcomeFileArrayRule);

            t_Undigester.addRule(t_Rule);

            String t_strResult = t_Undigester.unparse();

            assertTrue(t_strResult != null);

            assertEquals(t_strSuccessful, t_strResult);
        }
        catch  (final UndigesterException undigesterException)
        {
            fail("" + undigesterException);
        }
    }

    /**
     * Executes the tests contained in this test case.
     * @param args the command-line arguments, not used.
     */
    public static void main(String[] args)
    {
        junit.textui.TestRunner.run(UndigesterWebXmlTest.class);
    }

    /**
     * WebApp test class.
     * @author <a href="mailto:jsanleandro@yahoo.es"
               >Jose San Leandro Armendáriz</a>
     * @version $Revision$
     */
    private static class WebApp
    {
        /**
         * The display name.
         */
        IdText m__DisplayName;

        /**
         * The servlet collection.
         */
        Collection m__cServlets = new ArrayList();

        /**
         * The welcome files.
         */
        Collection m__cWelcomeFiles = new ArrayList();

        /**
         * Builds a WebApp with given display name.
         * @param displayName the display name.
         */
        public WebApp(String displayName)
        {
            setDisplayName(displayName);
        }

        /**
         * Specifies a display name.
         * @param displayName the new display name.
         */
        public void setDisplayName(String displayName)
        {
            m__DisplayName = new IdText(displayName);
        }

        /**
         * Retrieves the display name.
         * @return such information.
         */
        public IdText getDisplayName()
        {
            return m__DisplayName;
        }

        /**
         * Retrieves the servlet array.
         * @return such information.
         */
        public Servlet[] getServletArray()
        {
            return (Servlet[]) m__cServlets.toArray(new Servlet[0]);
        }
        
        /**
         * Adds a new servlet.
         * @param servlet the new servlet to add.
         */
        public void addServlet(Servlet servlet)
        {
            m__cServlets.add(servlet);
        }

        /**
         * Retrieves the welcome file array.
         * @return such information.
         */
        public IdText[] getWelcomeFileArray()
        {
            return (IdText[]) m__cWelcomeFiles.toArray(new IdText[0]);
        }
        
        /**
         * Adds a new welcome file.
         * @param welcomeFile the new welcome file to add.
         */
        public void addWelcomeFile(String welcomeFile)
        {
            m__cWelcomeFiles.add(new IdText(welcomeFile));
        }

        /**
         * The text self-description.
         * @return such information.
         */
        public String toString()
        {
            return
                  getClass().getName()
                + ":" + getDisplayName()
                + "," + getServletArray().length;
        }
    }

    /**
     * Servlet test class.
     * @author <a href="mailto:jsanleandro@yahoo.es"
               >Jose San Leandro Armendáriz</a>
     * @version $Revision$
     */
    private static class Servlet
    {
        /**
         * The servlet name.
         */
        IdText m__ServletName;

        /**
         * The servlet class.
         */
        IdText m__ServletClass;

        /**
         * The init params.
         */
        Collection m__cInitParams = new ArrayList();

        /**
         * The load-on-startup value.
         */
        IdText m__LoadOnStartup;

        /**
         * Builds a servlet using given servlet name and class.
         * @param servletName the servlet name.
         * @param servletClass the servlet class.
         */
        public Servlet(String servletName, String servletClass)
        {
            setServletName(servletName);
            setServletClass(servletClass);
        }

        /**
         * Builds a servlet using given information.
         * @param servletName the servlet name.
         * @param servletClass the servlet class.
         * @param loadOnStartup the load-on-startup value.
         */
        public Servlet(
            String servletName,
            String servletClass,
            String loadOnStartup)
        {
            this(servletName, servletClass);
            setLoadOnStartup(loadOnStartup);
        }

        /**
         * Specifies the servlet name.
         * @param servletName the servlet name.
         */
        public void setServletName(String servletName)
        {
            m__ServletName = new IdText(servletName);
        }
        
        /**
         * Retrieves the servlet name.
         * @return such information.
         */
        public IdText getServletName()
        {
            return m__ServletName;
        }

        /**
         * Specifies the servlet class.
         * @param servletClass the servlet class.
         */
        public void setServletClass(String servletClass)
        {
            m__ServletClass = new IdText(servletClass);
        }
        
        /**
         * Retrieves the servlet class.
         * @return such information.
         */
        public IdText getServletClass()
        {
            return m__ServletClass;
        }

        /**
         * Retrieves the init param array.IdText
         * @return such information.
         */
        public InitParam[] getInitParamArray()
        {
            return (InitParam[]) m__cInitParams.toArray(new InitParam[0]);
        }
        
        /**
         * Adds a init param file.
         * @param name the name of the new init param to add.
         * @param value the value of the new init param to add.
         */
        public void addInitParam(String name, String value)
        {
            m__cInitParams.add(new InitParam(name, value));
        }

        /**
         * Specifies the load-on-startup value.
         * @param value the new value.
         */
        protected void setLoadOnStartup(String value)
        {
            m__LoadOnStartup = new IdText(value);
        }

        /**
         * Retrieves the load-on-startup value.
         * @return such information.
         */
        public IdText getLoadOnStartup()
        {
            return m__LoadOnStartup;
        }

        /**
         * The text self-description.
         * @return such information.
         */
        public String toString()
        {
            return 
                  getClass().getName()
                + ":(" + getServletName()
                + "," + getServletClass()
                + "):(initParams=" + getInitParamArray().length
                + "):(" + getLoadOnStartup()
                + ")";
        }
    }

    /**
     * InitParam test class.
     * @author <a href="mailto:jsanleandro@yahoo.es"
               >Jose San Leandro Armendáriz</a>
     * @version $Revision$
     */
    private static class InitParam
    {
        /**
         * The init param name.
         */
        IdText m__Name;

        /**
         * The init param value.
         */
        IdText m__Value;

        /**
         * Builds an InitParam using given name and value.
         * @param name the init param name.
         * @param value the init param value.
         */
        public InitParam(String name, String value)
        {
            setName(name);
            setValue(value);
        }

        /**
         * Specifies the init param name.
         * @param name the name.
         */
        public void setName(String name)
        {
            m__Name = new IdText(name);
        }
        
        /**
         * Retrieves the init param name.
         * @return such information.
         */
        public IdText getName()
        {
            return m__Name;
        }

        /**
         * Specifies the init param value.
         * @param value the value.
         */
        public void setValue(String value)
        {
            m__Value = new IdText(value);
        }
        
        /**
         * Retrieves the init param value.
         * @return such information.
         */
        public IdText getValue()
        {
            return m__Value;
        }

        /**
         * The text self-description.
         * @return such information.
         */
        public String toString()
        {
            return 
                  getClass().getName()
                + ":" + getName()
                + "=" + getValue();
        }
    }

    /**
     * IdText test class.
     * @author <a href="mailto:jsanleandro@yahoo.es"
               >Jose San Leandro Armendáriz</a>
     * @version $Revision$
     */
    private static class IdText
    {
        String m__strText;

        /**
         * Builds an IdText object.
         * @param text the text.
         */
        public IdText(String text)
        {
            m__strText = text;
        }
        
        /**
         * Retrieves the text.
         * @return such information.
         */
        public String getText()
        {
            return m__strText;
        }

        /**
         * The text self-description.
         * @return such information.
         */
        public String toString()
        {
            return getClass().getName() + ":" + getText();
        }
    }
}
