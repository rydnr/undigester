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
 * Description: Serializes Java objects to XML.
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
import org.acmsl.undigester.Rule;
import org.acmsl.undigester.RuleSet;
import org.acmsl.undigester.TreeNode;
import org.acmsl.undigester.TreeVisitor;
import org.acmsl.undigester.UndigesterException;
import org.acmsl.undigester.utils.UndigesterUtils;

/*
 * Importing ACM-SL Commons classes.
 */
import org.acmsl.commons.utils.EqualityComparator;
import org.acmsl.commons.utils.StringValidator;

/*
 * Importing some JDK classes.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Serializes Java objects to XML.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$ ($Date$ by $Author$)
 */
public class Undigester
{
    /**
     * The root of the object graph to serialize.
     */
    private Object m__Root;

    /**
     * The rule map.
     */
    private Map m__mRules;

    /**
     * Creates an Undigester instance to serialize given
     * object.
     * @param root the root of the object graph.
     * @precondition root != null
     */
    public Undigester(final Object root)
    {
        inmutableSetRoot(root);
    }

    /**
     * Specifies the root of the object graph to serialize.
     * @param root such root.
     */
    private void inmutableSetRoot(final Object root)
    {
        m__Root = root;
    }

    /**
     * Specifies the root of the object graph to serialize.
     * @param root such root.
     */
    protected void setRoot(final Object root)
    {
        inmutableSetRoot(root);
    }

    /**
     * Retrieves the root of the object graph to serialize.
     * @return such root.
     */
    public Object getRoot()
    {
        return m__Root;
    }

    /**
     * Specifies the rule map.
     * @param map such map.
     */
    protected void setRules(final Map map)
    {
        m__mRules = map;
    }

    /**
     * Retrieves the rule map.
     * @return such map.
     */
    protected Map getRules()
    {
        return m__mRules;
    }

    /**
     * Retrieves the rules.
     * @return the rule map (pattern -> rule(s)).
     */
    protected Map retrieveRules()
    {
        Map result = getRules();

        if  (result == null)
        {
            result = new HashMap();
            setRules(result);
        }

        return result;
    }

    /**
     * Adds a set of rules to configure the serialization process.
     * @param ruleSet such rule set.
     * @precondition ruleSet != null
     */
    public void addRules(final RuleSet ruleSet)
    {
        addRules(
            ruleSet.getRules(),
            retrieveRules(),
            UndigesterUtils.getInstance());
    }

    /**
     * Adds a set of rules to configure the serialization process.
     * @param ruleSet such rule set.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @precondition rules != null
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     */
    protected void addRules(
        final Collection rules,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils)
    {
        Iterator t_itRuleIterator = rules.iterator();

        if  (t_itRuleIterator != null)
        {
            Rule t_CurrentRule = null;

            while  (t_itRuleIterator.hasNext())
            {
                t_CurrentRule =
                    (Rule) t_itRuleIterator.next();

                if (t_CurrentRule != null)
                {
                    addRule(
                        t_CurrentRule,
                        t_CurrentRule.getPattern(),
                        ruleMap,
                        undigesterUtils);
                }
            }
        }
    }

    /**
     * Adds a concrete rule to configure the serialization process.
     * @param rule such rule.
     * @precondition rule != null
     */
    public void addRule(final Rule rule)
    {
        addRule(
            rule,
            rule.getPattern(),
            retrieveRules(),
            UndigesterUtils.getInstance());
    }

    /**
     * Adds a concrete rule to configure the serialization process.
     * @param rule such rule.
     * @param pattern the rule's pattern.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @precondition rule != null
     * @precondition pattern != null
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     */
    protected void addRule(
        final Rule rule,
        final String pattern,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils)
    {
        addRule(
            undigesterUtils.buildPatternKey(pattern),
            rule,
            ruleMap);
    }

    /**
     * Adds a concrete rule to configure the serialization process.
     * @param key the key of the pattern in the rule map.
     * @param rule such rule.
     * @param ruleMap the rule map.
     * @precondition key != null
     * @precondition rule != null
     * @precondition ruleMap != null
     */
    protected void addRule(
        final Object key, final Rule rule, final Map ruleMap)
    {
        Collection t_cRules = getRules(key, ruleMap);

        t_cRules.add(rule);

        ruleMap.put(key, t_cRules);
    }

    /**
     * Retrieves the rules for given nod.
     * @param node the tree node.
     * @return such rules.
     * @precondition node != null
     */
    public Collection getRules(final TreeNode node)
    {
        return
            getRules(
                node,
                retrieveRules(),
                UndigesterUtils.getInstance(),
                StringValidator.getInstance(),
                EqualityComparator.getInstance());
    }

    /**
     * Retrieves the rules for given pattern.
     * @param node the tree node.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @return such rules.
     * @precondition node != null
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected Collection getRules(
        final TreeNode node,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
    {
        Collection result =
            getRules(
                undigesterUtils.buildPatternKey(node.getNodeId()),
                ruleMap);

        if  (result != null)
        {
            result =
                retrieveBranchRules(
                    node,
                    result,
                    undigesterUtils,
                    stringValidator,
                    equalityComparator);
        }

        return result;
            
    }

    /**
     * Retrieves the rules for given pattern.
     * @param key the rule pattern key.
     * @param ruleMap the rule map.
     * @return such rules.
     * @precondition key != null
     * @precondition ruleMap != null
     */
    protected Collection getRules(final Object key, final Map ruleMap)
    {
        Collection result = (Collection) ruleMap.get(key);

        if  (result == null)
        {
            result = new ArrayList();
        }

        return result;
    }

    /**
     * Retrieves the rules affecting to the branch given node belongs to.
     * @param node the tree node.
     * @param rules the rules to process.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @return the rule subset for the node's branch.
     * @precondition node != null
     * @precondition rules != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected Collection retrieveBranchRules(
        final TreeNode node,
        final Collection rules,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
    {
        Collection result = null;

        String t_strSpecializedPattern =
            undigesterUtils.buildSpecializedPattern(
                node, stringValidator);

        boolean t_bEmptySpecializedPattern = 
            stringValidator.isEmpty(t_strSpecializedPattern);

        if  (t_bEmptySpecializedPattern)
        {
            result = rules;
        }
        else
        {
            Collection t_cInput =
                undigesterUtils.removeChainableRules(
                    rules, t_bEmptySpecializedPattern);

            Iterator t_itRuleIterator = t_cInput.iterator();

            result = new ArrayList();

            Rule t_CurrentRule = null;

            String t_strCurrentSpecializedPattern = null;

            while  (   (t_itRuleIterator != null)
                    && (t_itRuleIterator.hasNext()))
            {
                t_CurrentRule = (Rule) t_itRuleIterator.next();

                t_strCurrentSpecializedPattern =
                    undigesterUtils.buildSpecializedPattern(
                        (ChainableRule) t_CurrentRule,
                        stringValidator);

                if  (!stringValidator.isEmpty(
                         t_strCurrentSpecializedPattern))
                {
                    String t_strCommonBranch =
                        undigesterUtils.extractCommonBranch(
                            t_strCurrentSpecializedPattern,
                            t_strSpecializedPattern);

                    if  (equalityComparator.areEqual(
                             t_strSpecializedPattern,
                             t_strCommonBranch))
                    {
                        result.add(t_CurrentRule);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Unparses the object graph starting by the root object,
     * according to the configured rules.
     * @return the serialized object graph.
     * @throws UndigesterException if the serialization process fails.
     */
    public String unparse()
        throws  UndigesterException
    {
        return
            unparse(
                getRoot(),
                retrieveRules(),
                UndigesterUtils.getInstance(),
                StringValidator.getInstance(),
                EqualityComparator.getInstance());
    }

    /**
     * Unparses the object graph starting by given object,
     * according to the configured rules.
     * @param object the root of the (sub)graph to serialize.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @return the serialized object graph.
     * @throws UndigesterException if the serialization process fails.
     * @precondition object != null
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected String unparse(
        final Object object,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        TreeNode t_TreeNode = 
            new TreeNode(
                object.getClass().getName(),
                object);

        buildTreeGraph(
            object,
            t_TreeNode,
            t_TreeNode.getNodeId(),
            ruleMap,
            undigesterUtils,
            stringValidator,
            equalityComparator);

        unparse(
            object,
            t_TreeNode,
            t_TreeNode.getNodeId(),
            ruleMap,
            undigesterUtils,
            stringValidator,
            equalityComparator);

        return t_TreeNode.toXml();
    }

    /**
     * Unparses the object graph starting by given object,
     * according to the configured rules.
     * @param treeNode the root of the (sub)graph to serialize.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @throws UndigesterException if the serialization process fails.
     * @precondition treeNode != null
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected void unparse(
        final TreeNode treeNode,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        unparse(
            treeNode.getObject(),
            treeNode,
            treeNode.getNodeId(),
            ruleMap,
            undigesterUtils,
            stringValidator,
            equalityComparator);
    }

    /**
     * Builds the object graph starting by given object,
     * according to the configured rules.
     * @param treeNode the root of the (sub)graph to serialize.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @throws UndigesterException if the serialization process fails.
     * @precondition treeNode != null
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected void buildTreeGraph(
        final TreeNode treeNode,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        buildTreeGraph(
            treeNode.getObject(),
            treeNode,
            treeNode.getNodeId(),
            ruleMap,
            undigesterUtils,
            stringValidator,
            equalityComparator);
    }

    /**
     * Builds the object graph starting by given object,
     * according to the configured rules.
     * @param object the object to unparse.
     * @param treeNode the root of the (sub)graph to serialize.
     * @param nodeId the node identifier.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @throws UndigesterException if the serialization process fails.
     * @precondition object != null
     * @precondition treeNode != null
     * @precondition nodeId != null
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected void buildTreeGraph(
        final Object object,
        final TreeNode treeNode,
        final String nodeId,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        Object t_Key = undigesterUtils.buildPatternKey(nodeId);

        Collection t_cRules = (Collection) ruleMap.get(t_Key);

        if  (t_cRules != null)
        {
            Iterator t_itRuleIterator = t_cRules.iterator();

            while  (t_itRuleIterator.hasNext())
            {
                Rule t_CurrentRule =
                    (Rule) t_itRuleIterator.next();

                if  (t_CurrentRule instanceof StructureShaperRule)
                {
                    applyRule(
                        t_CurrentRule,
                        treeNode,
                        ruleMap,
                        undigesterUtils,
                        stringValidator,
                        equalityComparator);
                }
            }

            applyNewBornChildRules(
                treeNode,
                ruleMap,
                undigesterUtils,
                stringValidator,
                equalityComparator);
        }
    }

    /**
     * Unparses the object graph starting by given object,
     * according to the configured rules.
     * @param object the object to unparse.
     * @param treeNode the root of the (sub)graph to serialize.
     * @param nodeId the node identifier.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @return the serialized object graph.
     * @throws UndigesterException if the serialization process fails.
     * @precondition object != null
     * @precondition treeNode != null
     * @precondition nodeId != null
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected void unparse(
        final Object object,
        final TreeNode treeNode,
        final String nodeId,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        Object t_Key = undigesterUtils.buildPatternKey(nodeId);

        Collection t_cRules =
            getRules(
                treeNode, 
                ruleMap,
                undigesterUtils,
                stringValidator,
                equalityComparator);

        if  (t_cRules != null)
        {
            Iterator t_itRuleIterator = t_cRules.iterator();

            while  (t_itRuleIterator.hasNext())
            {
                Rule t_CurrentRule =
                    (Rule) t_itRuleIterator.next();

                if  (!(t_CurrentRule instanceof StructureShaperRule))
                {
                    applyRule(
                        t_CurrentRule,
                        treeNode,
                        ruleMap,
                        undigesterUtils,
                        stringValidator,
                        equalityComparator);
                }
            }

            applyChildRules(
                treeNode,
                ruleMap,
                undigesterUtils,
                stringValidator,
                equalityComparator);
        }
    }

    /**
     * Applies given rule to the specified tree node.
     * @param rule the rule to apply.
     * @param treeNode the tree node to serialize.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @throws UndigesterException if the serialization process fails.
     * @precondition rule != null
     * @precondition rule instanceof org.acmsl.undigester.Rule
     * @precondition treeNode != null
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected void applyRule(
        final Object rule,
        final TreeNode treeNode,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        applyRule(
            (Rule) rule,
            treeNode,
            ruleMap,
            undigesterUtils,
            stringValidator,
            equalityComparator);
    }

    /**
     * Applies given rule to the specified tree node.
     * @param rule the rule to apply.
     * @param treeNode the tree node to serialize.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @throws UndigesterException if the serialization process fails.
     * @precondition rule != null
     * @precondition treeNode != null
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected void applyRule(
        final Rule rule,
        final TreeNode treeNode,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        applyRule(
            rule,
            treeNode,
            treeNode.getBody(),
            ruleMap,
            undigesterUtils,
            stringValidator,
            equalityComparator);
    }

    /**
     * Applies given rule to the specified tree node.
     * @param rule the rule to apply.
     * @param treeNode the tree node to serialize.
     * @param body the body of the node.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @throws UndigesterException if the serialization process fails.
     * @precondition rule != null
     * @precondition treeNode != null
     * @precondition body != null
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected void applyRule(
        final Rule rule,
        final TreeNode treeNode,
        final StringBuffer body,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        TreeVisitor t_TreeVisitor = new TreeVisitor(treeNode);

        while  (t_TreeVisitor.hasMoreNodes())
        {
            TreeNode t_CurrentNode = t_TreeVisitor.nextNode();

            boolean t_bBreakLoop = false;

            if  (t_CurrentNode == treeNode)
            {
                rule.apply(t_CurrentNode);

                break;
            }
            else
            {
                unparse(
                    t_CurrentNode,
                    ruleMap,
                    undigesterUtils,
                    stringValidator,
                    equalityComparator);
            }
        }
    }

    /**
     * Applies the rules to the new-born children of given node.
     * @param treeNode the tree node.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @throws UndigesterException if some problem occurs.
     * @see {@bug 122}
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected void applyNewBornChildRules(
        final TreeNode treeNode,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        // Bug 122: The original rule order gets messed up.
        applyNewBornChildRules(
            undigesterUtils.mergeChildren(treeNode),
            ruleMap,
            undigesterUtils,
            stringValidator,
            equalityComparator);
    }

    /**
     * Applies the rules to given new-born children.
     * @param children the child nodes.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @throws UndigesterException if some problem occurs.
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected void applyNewBornChildRules(
        final Collection children,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        if  (children != null)
        {
            Iterator t_itChildIterator = children.iterator();

            int t_iChildIndex = 1;

            while  (   (t_itChildIterator != null)
                    && (t_itChildIterator.hasNext()))
            {
                TreeNode t_CurrentChild =
                    (TreeNode) t_itChildIterator.next();

                buildTreeGraph(
                    t_CurrentChild.getObject(),
                    t_CurrentChild,
                    t_CurrentChild.getNodeId(),
                    ruleMap,
                    undigesterUtils,
                    stringValidator,
                    equalityComparator);
            }
        }
    }

    /**
     * Applies the rules to given tree node.
     * @param treeNode the tree node.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @throws UndigesterException if some problem occurs.
     * @see {@bug 122}
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected void applyChildRules(
        final TreeNode treeNode,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        // Bug 122: The original rule order gets messed up.
        applyChildRules(
            undigesterUtils.mergeChildren(treeNode),
            ruleMap,
            undigesterUtils,
            stringValidator,
            equalityComparator);
    }

    /**
     * Applies the rules to given children.
     * @param children the child nodes.
     * @param ruleMap the rule map.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @param equalityComparator the EqualityComparator instance.
     * @throws UndigesterException if some problem occurs.
     * @precondition ruleMap != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     * @precondition equalityComparator != null
     */
    protected void applyChildRules(
        final Collection children,
        final Map ruleMap,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator,
        final EqualityComparator equalityComparator)
      throws  UndigesterException
    {
        if  (children != null)
        {
            Iterator t_itChildIterator = children.iterator();

            int t_iChildIndex = 1;

            while  (   (t_itChildIterator != null)
                    && (t_itChildIterator.hasNext()))
            {
                TreeNode t_CurrentChild =
                    (TreeNode) t_itChildIterator.next();

                unparse(
                    t_CurrentChild.getObject(),
                    t_CurrentChild,
                    t_CurrentChild.getNodeId(),
                    ruleMap,
                    undigesterUtils,
                    stringValidator,
                    equalityComparator);
            }
        }
    }
}
