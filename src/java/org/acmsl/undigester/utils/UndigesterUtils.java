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
 * Description: Provides stateless helper Undigester-related methods.
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
package org.acmsl.undigester.utils;

/*
 * Importing some project classes.
 */
import org.acmsl.undigester.ChainableRule;
import org.acmsl.undigester.Rule;
import org.acmsl.undigester.TreeNode;
import org.acmsl.undigester.TreeNodeIndexComparator;
import org.acmsl.undigester.Undigester;

/*
 * Importing ACM-SL Commons classes.
 */
import org.acmsl.commons.regexpplugin.Compiler;
import org.acmsl.commons.regexpplugin.MalformedPatternException;
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.MatchResult;
import org.acmsl.commons.regexpplugin.Pattern;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;
import org.acmsl.commons.regexpplugin.RegexpManager;

/*
 * Importing ACM-SL Commons.
 */
import org.acmsl.commons.utils.StringValidator;

/*
 * Importing some JDK classes.
 */
import java.lang.ref.WeakReference;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Provides stateless helper Undigester-related methods.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public abstract class UndigesterUtils
{
    /**
     * Regular expression used to parse rule "patterns".
     */
    public static final String RULE_PATTERN_REGEXP =
        "(.*)?/(.*)";

    /**
     * Regular expression used to parse rule "subpatterns".
     */
    public static final String RULE_SUBPATTERN_REGEXP =
        "(.*)?:(.*)?#(.*)";

    /**
     * Regular expresion used to escape double quote symbols.
     */
    public static final String ESCAPE_DOUBLE_QUOTE_REGEXP =
        "(.*)?\"(.*)";

    /**
     * Regular expresion used to escape reserved symbols.
     */
    public static final String ESCAPE_SYMBOL_REGEXP =
        "(.*)?\\{0}(.*)";

    /**
     * Cached empty String array.
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * Compiler used.
     */
    private static Compiler m__Compiler;

    /**
     * Compiled regexp pattern to parse rule "patterns".
     */
    private static Pattern m__RulePattern;

    /**
     * Compiled regexp pattern to parse rule "subpatterns".
     */
    private static Pattern m__RuleSubpattern;

    /**
     * Compiled regexp pattern to escape double quote symbols.
     */
    private static Pattern m__DoubleQuotePattern;

    /**
     * Compiled regexp pattern to escape open-parenthesis symbols.
     */
    private static Pattern m__OpenParenthesisPattern;

    /**
     * Compiled regexp pattern to escape close-parenthesis symbols.
     */
    private static Pattern m__CloseParenthesisPattern;

    /**
     * Compiled regexp pattern to escape Kleene-star symbols.
     */
    private static Pattern m__KleeneStarPattern;

    /**
     * Compiled regexp pattern to escape dot symbols.
     */
    private static Pattern m__DotPattern;

    /**
     * Compiled regexp pattern to escape question mark symbols.
     */
    private static Pattern m__QuestionMarkPattern;

    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference m__Singleton;

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected UndigesterUtils() {};

    /**
     * Specifies a new weak reference.
     * @param utils the utils instance to use.
     * @precondition utils != null
     */
    protected static void setReference(final UndigesterUtils utils)
    {
        m__Singleton = new WeakReference(utils);
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
     * Retrieves an UndigesterUtils instance.
     * @return such instance.
     */
    public static UndigesterUtils getInstance()
    {
        UndigesterUtils result = null;

        WeakReference t_Reference = getReference();

        if  (t_Reference != null) 
        {
            result = (UndigesterUtils) t_Reference.get();
        }

        if  (result == null) 
        {
            result = new UndigesterUtils() {};
        }

        Compiler t_Compiler = result.getRegexpCompiler();

        if  (t_Compiler == null)
        {
            synchronized (result)
            {
                t_Compiler = RegexpManager.createCompiler();

                if  (t_Compiler != null)
                {
                    t_Compiler.setMultiline(false);
                    t_Compiler.setCaseSensitive(false);

                    result.immutableSetRegexpCompiler(t_Compiler);

                    result.immutableSetRuleRegexpPattern(
                        compile(t_Compiler, RULE_PATTERN_REGEXP));

                    result.immutableSetRuleRegexpSubPattern(
                        compile(t_Compiler, RULE_SUBPATTERN_REGEXP));

                    result.immutableSetDoubleQuotePattern(
                        compile(t_Compiler, ESCAPE_DOUBLE_QUOTE_REGEXP));

                    MessageFormat t_MessageFormat =
                        new MessageFormat(ESCAPE_SYMBOL_REGEXP);

                    result.immutableSetOpenParenthesisPattern(
                        compile(
                            t_Compiler,
                            t_MessageFormat.format(
                                new Object[] { "(" })));

                    result.immutableSetCloseParenthesisPattern(
                        compile(
                            t_Compiler,
                            t_MessageFormat.format(
                                new Object[] { ")" })));

                    result.immutableSetKleeneStarPattern(
                        compile(
                            t_Compiler,
                            t_MessageFormat.format(
                                new Object[] { "*" })));

                    result.immutableSetDotPattern(
                        compile(
                            t_Compiler,
                            t_MessageFormat.format(
                                new Object[] { "." })));

                    result.immutableSetQuestionMarkPattern(
                        compile(
                            t_Compiler,
                            t_MessageFormat.format(
                                new Object[] { "?" })));

                    setReference(result);
                }
            }
        }
        
        return result;
    }

    /**
     * Specifies the regexp compiler.
     * @param compiler the compiler.
     * @precondition compiler != null
     */
    private void immutableSetRegexpCompiler(final Compiler compiler)
    {
        m__Compiler = compiler;
    }

    /**
     * Specifies the regexp compiler.
     * @param compiler the compiler.
     * @precondition compiler != null
     */
    protected void setRegexpCompiler(final Compiler compiler)
    {
        immutableSetRegexpCompiler(compiler);
    }

    /**
     * Retrieves the regexp compiler.
     * @return such compiler.
     */
    protected Compiler getRegexpCompiler()
    {
        return m__Compiler;
    }

    /**
     * Specifies the regexp pattern to parse rule "patterns".
     * @param pattern the pattern.
     * @precondition pattern != null
     */
    private void immutableSetRuleRegexpPattern(final Pattern pattern)
    {
        m__RulePattern = pattern;
    }

    /**
     * Specifies the regexp pattern to parse rule "patterns".
     * @param pattern the pattern.
     */
    protected void setRuleRegexpPattern(final Pattern pattern)
    {
        immutableSetRuleRegexpPattern(pattern);
    }

    /**
     * Retrieves the regexp pattern to parse rule "patterns".
     * @return such pattern.
     */
    public Pattern getRuleRegexpPattern()
    {
        return m__RulePattern;
    }

    /**
     * Specifies the regexp pattern to parse rule "subpatterns".
     * @param pattern the pattern.
     * @precondition pattern != null
     */
    private void immutableSetRuleRegexpSubPattern(final Pattern pattern)
    {
        m__RuleSubpattern = pattern;
    }

    /**
     * Specifies the regexp pattern to parse rule "subpatterns".
     * @param pattern the pattern.
     * @precondition pattern != null
     */
    protected void setRuleRegexpSubPattern(final Pattern pattern)
    {
        immutableSetRuleRegexpPattern(pattern);
    }

    /**
     * Retrieves the regexp pattern to parse rule "subpatterns".
     * @return such pattern.
     */
    public Pattern getRuleRegexpSubPattern()
    {
        return m__RuleSubpattern;
    }

    /**
     * Specifies the regexp pattern to escape double quote symbols.
     * @param pattern such regexp pattern.
     */
    private void immutableSetDoubleQuotePattern(final Pattern pattern)
    {
        m__DoubleQuotePattern = pattern;
    }

    /**
     * Specifies the regexp pattern to escape double quote symbols.
     * @param pattern such regexp pattern.
     */
    protected void setDoubleQuotePattern(final Pattern pattern)
    {
        immutableSetDoubleQuotePattern(pattern);
    }

    /**
     * Retrieves the regexp pattern to escape double quote symbols.
     * @return such regexp pattern.
     */
    protected Pattern getDoubleQuotePattern()
    {
        return m__DoubleQuotePattern;
    }

    /**
     * Specifies the regexp pattern to escape open parenthesis symbols.
     * @param pattern such regexp pattern.
     */
    private void immutableSetOpenParenthesisPattern(final Pattern pattern)
    {
        m__OpenParenthesisPattern = pattern;
    }

    /**
     * Specifies the regexp pattern to escape open parenthesis symbols.
     * @param pattern such regexp pattern.
     */
    protected void setOpenParenthesisPattern(final Pattern pattern)
    {
        immutableSetOpenParenthesisPattern(pattern);
    }

    /**
     * Retrieves the regexp pattern to escape open parenthesis symbols.
     * @return such regexp pattern.
     */
    protected Pattern getOpenParenthesisPattern()
    {
        return m__OpenParenthesisPattern;
    }

    /**
     * Specifies the regexp pattern to escape close parenthesis symbols.
     * @param pattern such regexp pattern.
     */
    private void immutableSetCloseParenthesisPattern(final Pattern pattern)
    {
        m__CloseParenthesisPattern = pattern;
    }

    /**
     * Specifies the regexp pattern to escape close parenthesis symbols.
     * @param pattern such regexp pattern.
     */
    protected void setCloseParenthesisPattern(final Pattern pattern)
    {
        immutableSetCloseParenthesisPattern(pattern);
    }

    /**
     * Retrieves the regexp pattern to escape close parenthesis symbols.
     * @return such regexp pattern.
     */
    protected Pattern getCloseParenthesisPattern()
    {
        return m__CloseParenthesisPattern;
    }

    /**
     * Specifies the regexp pattern to escape Kleene star symbols.
     * @param pattern such regexp pattern.
     */
    private void immutableSetKleeneStarPattern(final Pattern pattern)
    {
        m__KleeneStarPattern = pattern;
    }

    /**
     * Specifies the regexp pattern to escape Kleene star symbols.
     * @param pattern such regexp pattern.
     */
    protected void setKleeneStarPattern(final Pattern pattern)
    {
        immutableSetKleeneStarPattern(pattern);
    }

    /**
     * Retrieves the regexp pattern to escape Kleene star symbols.
     * @return such regexp pattern.
     */
    protected Pattern getKleeneStarPattern()
    {
        return m__KleeneStarPattern;
    }

    /**
     * Specifies the regexp pattern to escape dot symbols.
     * @param pattern such regexp pattern.
     */
    private void immutableSetDotPattern(final Pattern pattern)
    {
        m__DotPattern = pattern;
    }

    /**
     * Specifies the regexp pattern to escape dot symbols.
     * @param pattern such regexp pattern.
     */
    protected void setDotPattern(final Pattern pattern)
    {
        immutableSetDotPattern(pattern);
    }

    /**
     * Retrieves the regexp pattern to escape dot symbols.
     * @return such regexp pattern.
     */
    protected Pattern getDotPattern()
    {
        return m__DotPattern;
    }

    /**
     * Specifies the regexp pattern to escape question mark symbols.
     * @param pattern such regexp pattern.
     */
    private void immutableSetQuestionMarkPattern(final Pattern pattern)
    {
        m__QuestionMarkPattern = pattern;
    }

    /**
     * Specifies the regexp pattern to escape question mark symbols.
     * @param pattern such regexp pattern.
     */
    protected void setQuestionMarkPattern(final Pattern pattern)
    {
        immutableSetQuestionMarkPattern(pattern);
    }

    /**
     * Retrieves the regexp pattern to escape question mark symbols.
     * @return such regexp pattern.
     */
    protected Pattern getQuestionMarkPattern()
    {
        return m__QuestionMarkPattern;
    }

    /**
     * Compiles given pattern.
     * @param compiler the regexp compiler.
     * @param pattern the regexp pattern to compile.
     * @return the compiled pattern.
     * @throws RuntimeException if the pattern is invalid.
     * @precondition compiler != null
     * @precondition pattern != null
     */
    private static Pattern compile(
        final Compiler compiler, final String pattern)
      throws  RuntimeException
    {
        Pattern result = null;

        try
        {
            result = compiler.compile(pattern);
        }
        catch  (final MalformedPatternException malformedPatternException)
        {
            /*
             * This should never happen for known patterns.
             * It's a compile-time error not detected by the compiler.
             */
            throw new RuntimeException(
                "Invalid pattern (" + pattern + ")",
                malformedPatternException);
        }

        return result;
    }

    /**
     * Expands given pattern.
     * @param pattern the pattern to expand.
     * @return the expanded pattern.
     * @precondition pattern != null
     */
    public String[] expandPattern(final String pattern)
    {
        return expandPattern(pattern, getRuleRegexpPattern());
    }

    /**
     * Expands given pattern.
     * @param input the input to expand.
     * @param regexpPattern the regexp pattern.
     * @return the expanded pattern.
     * @precondition input != null
     * @precondition regexpPattern != null
     */
    protected String[] expandPattern(
        final String input, Pattern regexpPattern)
    {
        Collection t_cResults = new ArrayList();

        Matcher t_Matcher = RegexpManager.createMatcher();

        MatchResult t_MatchResult = null;

        String t_strContents = input;

        while  (   (t_strContents != null)
                && (t_Matcher.contains(
                        t_strContents, regexpPattern)))
        {
            t_MatchResult = t_Matcher.getMatch();

            t_cResults.add(t_MatchResult.group(1));

            t_strContents = t_MatchResult.group(2);
        }

        t_cResults.add(t_strContents);

        return (String[]) t_cResults.toArray(EMPTY_STRING_ARRAY);
    }

    /**
     * Applies given pattern to the input text.
     * @param input the input to expand.
     * @param regexpPattern the regexp pattern.
     * @return the expanded pattern.
     * @precondition input != null
     * @precondition regexpPattern != null
     */
    protected String apply(final String input, Pattern regexpPattern)
    {
        StringBuffer t_sbResults = new StringBuffer();

        Matcher t_Matcher = RegexpManager.createMatcher();

        MatchResult t_MatchResult = null;

        String t_strContents = input;

        while  (   (t_strContents != null)
                && (t_Matcher.contains(
                        t_strContents, regexpPattern)))
        {
            t_MatchResult = t_Matcher.getMatch();

            t_sbResults.append(t_MatchResult.group(1));

            t_strContents = t_MatchResult.group(2);
        }

        t_sbResults.append(t_strContents);

        return t_sbResults.toString();
    }

    /**
     * Flattens given array to a comma-separated string.
     * @param array the array to flatten.
     * @return such string.
     * @precondition array != null
     */
    public String toString(final Object[] array)
    {
        StringBuffer t_sbResult = new StringBuffer();

        for  (int t_iIndex = 0; t_iIndex < array.length; t_iIndex++)
        {
            t_sbResult.append(array[t_iIndex]);

            if  (t_iIndex < array.length - 1)
            {
                t_sbResult.append(", ");
            }
        }

        return t_sbResult.toString();
    }

    /**
     * Flattens given array to a comma-separated string.
     * @param array the array to flatten.
     * @return such string.
     * @precondition array != null
     */
    public String classArrayToString(final Object[] array)
    {
        StringBuffer t_sbResult = new StringBuffer();

        for  (int t_iIndex = 0; t_iIndex < array.length; t_iIndex++)
        {
            Object t_CurrentItem = null;

            if  (array[t_iIndex] != null)
            {
                t_CurrentItem = array[t_iIndex].getClass();
            }

            if  (t_CurrentItem instanceof Class)
            {
                t_CurrentItem = ((Class) t_CurrentItem).getName();
            }

            t_sbResult.append(t_CurrentItem);

            if  (t_iIndex < array.length - 1)
            {
                t_sbResult.append(",");
            }
        }

        return t_sbResult.toString();
    }

    /**
     * Builds the attribute key for an attribute of given node.
     * @param nodeId the node id.
     * @param index the node index.
     * @param name the attribute name.
     * @return the key specific for such attribute.
     * @precondition nodeId != null
     * @precondition index != null
     * @precondition name != null
     */
    public String buildAttributeKey(
        final String nodeId, final String index, final String name)
    {
        return "attr[" + nodeId + "#" + index + "@" + name + "]";
    }

    /**
     * Builds the attribute key for a pattern.
     * @param pattern the pattern.
     * @return the key specific for such pattern
     * @precondition pattern != null
     */
    public String buildPatternKey(final String pattern)
    {
        return "pattern[" + pattern + "]";
    }

    /**
     * Builds the specialized pattern with given information.
     * @param methodName the name of the method.
     * @param args the method arguments.
     * @param pattern the rule pattern.
     * @return the specialized pattern.
     * @precondition methodName != null
     * @precondition args != null
     * @precondition pattern != null
     */
    public String buildSpecializedPattern(
        final String methodName,
        final Object[] args,
        final String pattern)
    {
        return
              pattern
            + "#"
            + methodName
            + "("
            + classArrayToString(args)
            + ")";
    }

    /**
     * Builds the specialized pattern associated to given node.
     * @param node the node to analyze.
     * @param stringValidator the StringValidator instance.
     * @return the specialized pattern.
     * @precondition node != null
     * @precondition stringValidator != null
     */
    public String buildSpecializedPattern(
        final TreeNode node, final StringValidator stringValidator)
    {
        StringBuffer result = new StringBuffer();

        TreeNode t_CurrentParent = node.getParent();

        boolean t_bParentPattern = false;

        if  (t_CurrentParent != null)
        {
            String t_strParentSpecializedPattern =
                buildSpecializedPattern(t_CurrentParent, stringValidator);

            if  (!stringValidator.isEmpty(t_strParentSpecializedPattern))
            {
                result.append(t_strParentSpecializedPattern);
                t_bParentPattern = true;
            }
        }

        if  (t_bParentPattern)
        {
            result.append("->");
        }

        String t_strSpecializedPattern = node.getSpecializedPattern();

        if  (!stringValidator.isEmpty(t_strSpecializedPattern))
        {
            result.append(t_strSpecializedPattern);
        }

        return result.toString();
    }

    /**
     * Builds the specialized pattern associated to given rule.
     * @param rule the rule to analyze.
     * @param stringValidator the StringValidator instance.
     * @return the specialized pattern.
     * @precondition rule != null
     * @precondition stringValidator != null
     */
    public String buildSpecializedPattern(
        final ChainableRule rule, final StringValidator stringValidator)
    {
        StringBuffer t_sbResult = new StringBuffer();

        ChainableRule t_CurrentParent = rule.getParent();

        boolean t_bParentPattern = false;

        if  (t_CurrentParent != null)
        {
            String t_strParentSpecializedPattern =
                buildSpecializedPattern(t_CurrentParent, stringValidator);

            if  (!stringValidator.isEmpty(t_strParentSpecializedPattern))
            {
                t_sbResult.append(t_strParentSpecializedPattern);
                t_bParentPattern = true;
            }
        }

        if  (t_bParentPattern)
        {
            t_sbResult.append("->");
        }

        t_sbResult.append(normalizePattern(rule, stringValidator));

        return t_sbResult.toString();
    }

    /**
     * Provides a normalized version of the pattern associated to given rule.
     * @param rule the rule to process.
     * @param stringValidator the StringValidator instance.
     * @return its normalized pattern.
     * @precondition rule != null
     * @precondition stringValidator != null
     */
    protected String normalizePattern(
        final Rule rule, final StringValidator stringValidator)
    {
        StringBuffer t_sbResult = new StringBuffer(rule.getPattern());

        String t_strBranchPattern = rule.getBranchPattern();

        if  (!stringValidator.isEmpty(t_strBranchPattern))
        {
            t_sbResult.append("#");
            t_sbResult.append(t_strBranchPattern);
        }

        return t_sbResult.toString();
    }

    /**
     * Takes the common branch of given patterns.
     * @param first the first pattern.
     * @param second the second pattern.
     * @return the pattern which identifies the common branch.
     * @precondition first != null
     * @precondition second != null
     */
    public String extractCommonBranch(final String first, final String second)
    {
        return extractCommonBranch(first, second, true);
    }

    /**
     * Takes the common branch of given patterns.
     * @param first the first pattern.
     * @param second the second pattern.
     * @param retry whether to retry the process comparing the patterns in
     * the opposite order.
     * @return the pattern which identifies the common branch.
     * @precondition first != null
     * @precondition second != null
     */
    protected String extractCommonBranch(
        final String first, final String second, final boolean retry)
    {
        String result = "";

        int t_iPosition = first.indexOf(second);

        if  (t_iPosition == 0)
        {
            result = first.substring(0, second.length());
        }
        else if  (retry)
        {
            result = extractCommonBranch(second, first, false);
        }

        return result;
    }

    /**
     * Escapes given atribute value to avoid conflict with the quote
     * character.
     * @param input the input to escape.
     * @return the escaped input.
     * @precondition input != null
     */
    public String escapeAttribute(final String input)
    {
        return replace(input, getDoubleQuotePattern(), "\\\"");
    }

    /**
     * Replaces all occurrences of a certain text with given
     * replacement in the provided input.
     * @param input the input to process.
     * @param toBeReplaced the text to replace.
     * @param replacement the text to replace with.
     * @return the updated input.
     * @precondition input != null
     * @precondition toBeReplaced != null
     * @precondition replacement != null
     */
     public String replace(
         final String input,
         final String toBeReplaced,
         final String replacement)
     {
         return
             replace(
                 input, toBeReplaced, replacement, getRegexpCompiler());
     }

    /**
     * Replaces all occurrences of a certain text with given
     * replacement in the provided input.
     * @param input the input to process.
     * @param toBeReplaced the text to replace.
     * @param replacement the text to replace with.
     * @param compiler the regexp compiler.
     * @return the updated input.
     * @precondition input != null
     * @precondition toBeReplaced != null
     * @precondition replacement != null
     * @precondition compiler != null
     */
     protected String replace(
         final String input,
         final String toBeReplaced,
         final String replacement,
         final Compiler compiler)
    {
        String result = input;

        try
        {
            Pattern t_Pattern =
                compiler.compile(
                      "(.*?)"
                    + multipleReplace(
                          toBeReplaced,
                          new Pattern[]
                          {
                              getOpenParenthesisPattern(),
                              getCloseParenthesisPattern(),
                              getKleeneStarPattern(),
                              getDotPattern(),
                              getQuestionMarkPattern()
                          },
                          new String[]
                          {
                              "\\(",
                              "\\)",
                              "\\*",
                              "\\.",
                              "\\?"
                          })
                    + "(.*)");
        }
        catch  (final MalformedPatternException malformedPatternException)
        {
            throw new RuntimeException(
                "Cannot handle operation: "
                + "replace("
                + "\"" + input        + "\", "
                + "\"" + toBeReplaced + "\", "
                + "\"" + replacement  + "\")",
                malformedPatternException);
        }

        return result;
    }

    /**
     * Escapes all reserved symbols of given input.
     * @param input the input to process.
     * @param patterns the regexp patterns to use.
     * @param sequences the input sequences.
     * @return the result of escaping characters according to given
     * patterns.
     * @precondition input != null
     * @precondition patterns != null
     * @precondition sequences != null
     */
    protected String multipleReplace(
        final String input,
        final Pattern[] patterns,
        final String[] sequences)
    {
        String result = input;

        for  (int t_iIndex = 0; t_iIndex < patterns.length; t_iIndex++)
        {
            result =
                replace(
                    result,
                    patterns[t_iIndex],
                    "\\" + sequences[t_iIndex]);
        }

        return result;
    }

    /**
     * Replaces all occurrences of a certain text with given
     * replacement in the provided input, using a concrete regexp.
     * @param input the input to process.
     * @param regexpPattern the regexp pattern.
     * @param replacement the text to replace with.
     * @return the updated input.
     * @precondition input != null
     * @precondition regexpPattern != null
     * @precondition replacement != null
     */
    protected String replace(
        final String input,
        final Pattern regexpPattern,
        final String replacement)
    {
        StringBuffer t_sbResult = new StringBuffer();

        Matcher t_Matcher = RegexpManager.createMatcher();

        MatchResult t_MatchResult = null;

        String t_strContents = input;

        while  (   (t_strContents != null)
                && (t_Matcher.contains(
                        t_strContents, regexpPattern)))
        {
            t_MatchResult = t_Matcher.getMatch();

            t_sbResult.append(t_MatchResult.group(1));

            t_sbResult.append(replacement);

            t_strContents = t_MatchResult.group(2);
        }

        if  (t_strContents != null)
        {
            t_sbResult.append(t_strContents);
        }

        return t_sbResult.toString();
    }

    /**
     * Retrieves the pattern associated to given object.
     * @param object the object to analyze.
     * @return the associated pattern.
     * @precondition object != null
     */
    public String retrievePattern(final Object object)
    {
        return retrievePattern(object, StringValidator.getInstance());
    }

    /**
     * Retrieves the pattern associated to given object.
     * @param object the object to analyze.
     * @param stringValidator the StringValidator instance.
     * @return the associated pattern.
     * @precondition object != null
     * @precondition stringValidator != null
     */
    protected String retrievePattern(
        final Object object, final StringValidator stringValidator)
    {
        String result = null;

        if  (object instanceof TreeNode)
        {
            result =
                retrievePattern(
                    (TreeNode) object,
                    stringValidator);
        }
        else
        {
            result = object.getClass().getName();
        }

        return result;
    }

    /**
     * Retrieves the pattern associated to given tree node.
     * @param treeNode the node to analyze.
     * @param stringValidator the StringValidator instance.
     * @return the associated pattern.
     * @precondition treeNode != null
     * @precondition stringValidator != null
     */
    protected String retrievePattern(
        final TreeNode treeNode, final StringValidator stringValidator)
    {
        StringBuffer t_sbResult = new StringBuffer();

        t_sbResult.append(retrievePattern(treeNode.getObject()));

        String t_strSpecializedPattern =
            treeNode.getSpecializedPattern();

        if  (!stringValidator.isEmpty(t_strSpecializedPattern))
        {
            t_sbResult.append(":");
            t_sbResult.append(t_strSpecializedPattern);
        }

        return t_sbResult.toString();
    }

    /**
     * Removes any chainable rule from given collection.
     * @param rules the rule collection.
     * @return a subset of the input collection.
     * @precondition rules != null
     */
    public Collection removeChainableRules(final Collection rules)
    {
        return removeChainableRules(rules, true);
    }

    /**
     * Removes any not-chainable rule from given collection.
     * @param rules the rule collection.
     * @return a subset of the input collection.
     * @precondition rules != null
     */
    public Collection removeNotChainableRules(final Collection rules)
    {
        return removeChainableRules(rules, false);
    }

    /**
     * Removes any chainable rule from given collection.
     * @param rules the rule collection.
     * @param chainable whether we want to remove the chainable or the not
     * chainable rules.
     * @return a subset of the input collection.
     * @precondition rules != null
     */
    public Collection removeChainableRules(
        final Collection rules, final boolean chainable)
    {
        Collection result = new ArrayList();

        Iterator t_itRuleIterator = rules.iterator();

        Rule t_CurrentRule = null;

        while  (   (t_itRuleIterator != null)
                && (t_itRuleIterator.hasNext()))
        {
            t_CurrentRule = (Rule) t_itRuleIterator.next();

            if  (chainable)
            {
                if  (!(t_CurrentRule instanceof ChainableRule))
                {
                    result.add(t_CurrentRule);
                }
            }
            else
            {
                if  (t_CurrentRule instanceof ChainableRule)
                {
                    result.add(t_CurrentRule);
                }
            }
        }

        return result;
    }

    /**
     * Merges the regular and the specialized children of given node,
     * according to their index.
     * @param treeNode the tree node.
     * @return the ordered collection.
     * @precondition treeNode != null
     */
    public Collection mergeChildren(final TreeNode treeNode)
    {
        return
            mergeChildren(
                treeNode,
                treeNode.getChildren(),
                treeNode.getSpecializedChildrenKeys(),
                TreeNodeIndexComparator.getInstance());
    }

    /**
     * Merges the regular and the specialized children of given node,
     * according to their index.
     * @param treeNode the tree node.
     * @param regularChildren the "regular" children.
     * @param childGroupKeys the keys to retrieve the specialized children.
     * @param treeNodeIndexComparator the comparator to sort the resulting
     * collection.
     * @return the ordered collection.
     * @precondition treeNode != null
     * @precondition treeNodeIndexComparator != null
     */
    protected Collection mergeChildren(
        final TreeNode treeNode,
        final Collection regularChildren,
        final Collection childGroupKeys,
        final TreeNodeIndexComparator treeNodeIndexComparator)
    {
        List result = new ArrayList();

        if  (regularChildren != null)
        {
            result.addAll(regularChildren);
        }

        if  (childGroupKeys != null)
        {
            Iterator t_itChildGroupKeys = childGroupKeys.iterator();

            while  (   (t_itChildGroupKeys != null)
                    && (t_itChildGroupKeys.hasNext()))
            {
                result.addAll(
                    treeNode.retrieveChildren(
                        (String) t_itChildGroupKeys.next()));
            }
        }

        Collections.sort(result, treeNodeIndexComparator);

        return result;
    }

    /**
     * Builds the index of the whole tree branch, ending on given node.
     * @param node the node.
     * @return the whole index.
     * @precondition node != null
     */
    public String buildBranchIndex(final TreeNode node)
    {
        StringBuffer result = new StringBuffer();

        TreeNode t_CurrentParent = node.getParent();

        while  (t_CurrentParent != null)
        {
            result.append("" + t_CurrentParent.getIndex());

            t_CurrentParent = t_CurrentParent.getParent();
        }

        result.append("" + node.getIndex());

        return result.toString();
    }
}
