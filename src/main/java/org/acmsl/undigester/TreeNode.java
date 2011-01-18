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
 * Description: Refers to concrete object in an Undigester-specific object
 *              graph.
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
import org.acmsl.undigester.utils.UndigesterUtils;

/*
 * Importing some ACMSL-Commons classes.
 */
import org.acmsl.commons.utils.StringValidator;

/*
 * Importing some JDK classes.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Refers to concrete object in an Undigester-specific object graph.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$ ($Date$ by $Author$)
 */
public class TreeNode
{
    /**
     * The attribute values of all nodes
     * (to reuse the same map for all nodes, by properly managing its keys).
     */
    private static Map m__mAttributeValues;

    /**
     * The node id (which identifies uniquely the node in the graph).
     */
    private String m__strNodeId;

    /**
     * The wrapped object.
     */
    private Object m__Object;

    /**
     * The parent node.
     */
    private TreeNode m__Parent;

    /**
     * The children nodes.
     */
    private Collection m__cChildren;

    /**
     * The name of the node.
     */
    private String m__strName;

    /**
     * The specialized pattern.
     */
    private String m__strSpecializedPattern;

    /**
     * The node index.
     */
    private int m__iIndex;

    /**
     * The body of this node.
     */
    private StringBuffer m__sbBody;

    /**
     * The attribute names of this node.
     */
    private Collection m__cAttributeNames;

    /**
     * The specialized children map.
     */
    private Map m__mSpecializedChildren;

    /**
     * The specialized children keys.
     */
    private Collection m__cSpecializedChildrenKeys;

    /**
     * The child count.
     */
    private int m__iChildCount;

    /**
     * Creates a tree node to wrap given object.
     * @param nodeId the node's id.
     * @param object the object to wrap.
     * @param parent the parent node.
     * @param specializedPattern the optional specialized pattern.
     * @param index the node index.
     * @precondition nodeId != null
     * @precondition object != null
     */
    public TreeNode(
        final String nodeId,
        final Object object,
        final TreeNode parent,
        final String specializedPattern)
    {
        immutableSetNodeId(nodeId);
        immutableSetObject(object);
        immutableSetParent(parent);
        immutableSetSpecializedPattern(specializedPattern);
        immutableSetBody(new StringBuffer());
        immutableSetChildCount(0);
    }

    /**
     * Creates a tree node to wrap given object.
     * @param nodeId the node's id.
     * @param object the object to wrap.
     * @precondition nodeId != null
     * @precondition object != null
     */
    public TreeNode(
        final String nodeId, final Object object)
    {
        this(nodeId, object, null, null);
    }

    /**
     * Specifies the identifier of the node.
     * @param nodeId such id.
     */
    private void immutableSetNodeId(final String nodeId)
    {
        m__strNodeId = nodeId;
    }

    /**
     * Specifies the identifier of the node.
     * @param nodeId such id.
     */
    protected void setNodeId(final String nodeId)
    {
        immutableSetNodeId(nodeId);
    }

    /**
     * Retrieves the node identifier.
     * @return such id.
     */
    public String getNodeId()
    {
        return m__strNodeId;
    }

    /**
     * Specifies the object to wrap.
     * @param object the wrapped object.
     */
    private void immutableSetObject(final Object object)
    {
        m__Object = object;
    }

    /**
     * Specifies the object to wrap.
     * @param object the wrapped object.
     */
    protected void setObject(final Object object)
    {
        immutableSetObject(object);
    }

    /**
     * Retrieves the object to wrap.
     * @return such object.
     */
    public Object getObject()
    {
        return m__Object;
    }

    /**
     * Specifies the parent node.
     * @param parent the parent.
     */
    private void immutableSetParent(final TreeNode parent)
    {
        m__Parent = parent;
    }

    /**
     * Specifies the parent node.
     * @param parent the parent.
     */
    protected void setParent(final TreeNode parent)
    {
        immutableSetParent(parent);
    }

    /**
     * Retrieves the parent node.
     * @return such parent.
     */
    public TreeNode getParent()
    {
        return m__Parent;
    }

    /**
     * Specifies the name of the node.
     * @param nodeName such name.
     */
    public void setNodeName(final String nodeName)
    {
        m__strName = nodeName;
    }

    /**
     * Retrieves the node name.
     * @return such name.
     */
    public String getNodeName()
    {
        return m__strName;
    }

    /**
     * Specifies the specialized pattern of this node.
     * @param pattern such pattern.
     */
    private void immutableSetSpecializedPattern(final String pattern)
    {
        m__strSpecializedPattern = pattern;
    }

    /**
     * Specifies the specialized pattern of this node.
     * @param pattern such pattern.
     */
    protected void setSpecializedPattern(final String pattern)
    {
        immutableSetSpecializedPattern(pattern);
    }

    /**
     * Retrieves the specialized pattern.
     * @return such pattern.
     */
    public String getSpecializedPattern()
    {
        return m__strSpecializedPattern;
    }

    /**
     * Specifies the node index.
     * @param index the index.
     */
    public void setIndex(final int index)
    {
        m__iIndex = index;
    }

    /**
     * Retrieves the node index.
     * @return the index of this node.
     */
    public int getIndex()
    {
        return m__iIndex;
    }

    /**
     * Specifies the node's body.
     * @param body such body.
     */
    private void immutableSetBody(StringBuffer body)
    {
        m__sbBody = body;
    }

    /**
     * Specifies the node's body.
     * @param body such body.
     */
    protected void setBody(StringBuffer body)
    {
        immutableSetBody(body);
    }

    /**
     * Retrieves the body.
     * @return such information.
     */
    public StringBuffer getBody()
    {
        return m__sbBody;
    }

    /**
     * Specifies the child count value.
     * @param count such count.
     * @precondition count >= 0
     */
    private void immutableSetChildCount(final int count)
    {
        m__iChildCount = count;
    }

    /**
     * Specifies the child count value.
     * @param count such count.
     * @precondition count >= 0
     */
    protected void setChildCount(final int count)
    {
        immutableSetChildCount(count);
    }

    /**
     * Retrieves the child count.
     * @return such count.
     */
    protected int getChildCount()
    {
        return m__iChildCount;
    }

    /**
     * Increments the child count, and retrieves the previous value.
     * @return 0 the first time the method is executed.
     */
    protected int incrementChildCount()
    {
        int result = getChildCount();

        setChildCount(result + 1);

        return result;
    }

    /**
     * Specifies the attribute value map.
     * @param map such map.
     */
    protected synchronized static void setAttributeValues(final Map map)
    {
        m__mAttributeValues = map;
    }

    /**
     * Retrieves the attribute value map.
     * @return such map.
     */
    protected static Map getAttributeValues()
    {
        return m__mAttributeValues;
    }

    /**
     * Retrieves the attribute value map.
     * @return the attribute map.
     */
    protected synchronized static Map retrieveAttributeValues()
    {
        Map result = getAttributeValues();

        if  (result == null)
        {
            result = new HashMap();
            setAttributeValues(result);
        }

        return result;
    }

    /**
     * Specifies the attribute names.
     * @param names such collection.
     */
    protected void setAttributeNames(final Collection names)
    {
        m__cAttributeNames = names;
    }

    /**
     * Retrieves the attribute names.
     * @return such collection.
     */
    protected Collection getAttributeNames()
    {
        return m__cAttributeNames;
    }

    /**
     * Retrieves the attribute names.
     * @return the attribute name collection.
     */
    protected Collection retrieveAttributeNames()
    {
        Collection result = getAttributeNames();

        if  (result == null)
        {
            result = new ArrayList();
            setAttributeNames(result);
        }

        return result;
    }

    /**
     * Adds an attribute.
     * @param name the attribute name.
     * @param value the attribute value.
     * @precondition name != null
     * @precondition value != null
     */
    public void addAttribute(final String name, final String value)
    {
        Collection t_cAttributeNames = retrieveAttributeNames();

        if  (!t_cAttributeNames.contains(name))
        {
            t_cAttributeNames.add(name);
        }

        Map t_mAttributeValues = retrieveAttributeValues();

        addAttribute(
            getNodeId(),
            getIndex(),
            name,
            value,
            t_mAttributeValues,
            UndigesterUtils.getInstance());
    }

    /**
     * Adds an attribute.
     * @param nodeId the node id.
     * @param name the attribute name.
     * @param value the attribute value.
     * @param index the node index.
     * @param attributeValues the attribute values.
     * @param undigesterUtils the UndigesterUtils instance.
     * @precondition nodeId != null
     * @precondition name != null
     * @precondition value != null
     * @precondition attributeValues != null
     * @precondition undigesterUtils != null
     */
    protected void addAttribute(
        final String nodeId,
        final int index,
        final String name,
        final String value,
        final Map attributeValues,
        final UndigesterUtils undigesterUtils)
    {
        attributeValues.put(
            undigesterUtils.buildAttributeKey(
                nodeId,
                undigesterUtils.buildBranchIndex(this),
                name),
            value);
    }

    /**
     * Specifies the children.
     * @param names such collection.
     */
    protected void setChildren(final Collection children)
    {
        m__cChildren = children;
    }

    /**
     * Retrieves the children.
     * @return such collection.
     */
    public Collection getChildren()
    {
        return m__cChildren;
    }

    /**
     * Retrieves the children.
     * @return such collection, empty (not null) if the node is a leaf.
     */
    protected Collection retrieveChildren()
    {
        Collection result = getChildren();

        if  (result == null)
        {
            result = new ArrayList();
            setChildren(result);
        }

        return result;
    }

    /**
     * Adds a child.
     * @param node the child node.
     * @precondition node != null
     */
    public void addChild(final TreeNode node)
    {
        Collection t_cChildren = retrieveChildren();

        node.setIndex(incrementChildCount());

        t_cChildren.add(node);
    }

    /**
     * Specifies the specialized children.
     * @param names such collection.
     */
    protected void setSpecializedChildren(final Map specializedChildren)
    {
        m__mSpecializedChildren = specializedChildren;
    }

    /**
     * Retrieves the children.
     * @return such collection.
     */
    protected Map getSpecializedChildren()
    {
        return m__mSpecializedChildren;
    }

    /**
     * Retrieves the children.
     * @return such collection, empty (not null) if the node is a leaf.
     */
    protected Map retrieveSpecializedChildren()
    {
        Map result = getSpecializedChildren();

        if  (result == null)
        {
            result = new HashMap();
            setSpecializedChildren(result);
        }

        return result;
    }

    /**
     * Retrieves the specialized children grouped by given key.
     * @param elementName the global name shared by the new child and other
     * siblings, used to group them.
     * @return such collection, empty (not null) if no children for such
     * name already exist.
     * @precondition elementName != null
     */
    public Collection retrieveChildren(final String elementName)
    {
        Collection result = null;

        Map t_mSpecializedChildren = retrieveSpecializedChildren();

        result = (Collection) t_mSpecializedChildren.get(elementName);

        if  (result == null)
        {
            result = new ArrayList();
            t_mSpecializedChildren.put(elementName, result);
            addSpecializedChildrenKey(elementName);
        }

        return result;
    }

    /**
     * Adds a specialized child.
     * @param elementName the global name shared by the new child and other
     * siblings, used to group them.
     * @param node the child node.
     * @precondition elementName != null
     * @precondition node != null
     */
    public void addChild(final String elementName, final TreeNode node)
    {
        Collection t_cChildren = retrieveChildren(elementName);

        node.setIndex(incrementChildCount());

        t_cChildren.add(node);
    }

    /**
     * Specifies the specialized children keys.
     * @param childrenKeys such collection.
     */
    protected void setSpecializedChildrenKeys(final Collection childrenKeys)
    {
        m__cSpecializedChildrenKeys = childrenKeys;
    }

    /**
     * Retrieves the specialized children keys.
     * @return such collection.
     */
    public Collection getSpecializedChildrenKeys()
    {
        return m__cSpecializedChildrenKeys;
    }

    /**
     * Retrieves the children.
     * @return such collection, empty (not null) if the node is a leaf.
     */
    protected Collection retrieveSpecializedChildrenKeys()
    {
        Collection result = getSpecializedChildrenKeys();

        if  (result == null)
        {
            result = new ArrayList();
            setSpecializedChildrenKeys(result);
        }

        return result;
    }

    /**
     * Adds a key which groups children subsets, so that the order
     * is preserved.
     * @param key the grouping key.
     * @precondition key != null
     */
    protected void addSpecializedChildrenKey(final String key)
    {
        Collection t_cSpecializedChildrenKeys =
            retrieveSpecializedChildrenKeys();

        t_cSpecializedChildrenKeys.add(key);
    }

    /**
     * Provides a XML-formatted serialized version of the information
     * stored in this tree node.
     * @return the complete XML-formatted information of the branch whose
     * root is this node.
     */
    public String toXml()
    {
        return
            toXml(
                getNodeId(),
                getIndex(),
                getNodeName(),
                getAttributeNames(),
                getAttributeValues(),
                getBody(),
                getChildren(),
                getSpecializedChildren(),
                getSpecializedChildrenKeys());
    }

    /**
     * Provides a XML-formatted serialized version of the information
     * stored in this tree node.
     * @param nodeId the node identifier.
     * @param index the node index.
     * @param name the node name.
     * @param attributeNames the attribute names.
     * @param attributeValues the attribute values.
     * @param body the inner body.
     * @param children the children collection.
     * @param specializedChildren the specialized children.
     * @param specializedChildrenKeys the keys which group specialized children
     * subsets.
     * @return the complete XML-formatted information of the branch whose
     * root is this node.
     * @precondition nodeId != null
     * @precondition index >= 0
     * @precondition name != null
     * @precondition body != null
     */
    protected String toXml(
        final String nodeId,
        final int index,
        final String name,
        final Collection attributeNames,
        final Map attributeValues,
        final StringBuffer body,
        final Collection children,
        final Map specializedChildren,
        final Collection specializedChildrenKeys)
    {
        return
            toXml(
                nodeId,
                index,
                name,
                attributeNames,
                attributeValues,
                body.toString(),
                children,
                specializedChildren,
                specializedChildrenKeys,
                UndigesterUtils.getInstance(),
                StringValidator.getInstance());
    }

    /**
     * Provides a XML-formatted serialized version of the information
     * stored in this tree node.
     * @param nodeId the node identifier.
     * @param index the node index.
     * @param name the node name.
     * @param attributeNames the attribute names.
     * @param attributeValues the attribute values.
     * @param body the inner body.
     * @param children the children collection.
     * @param specializedChildren the specialized children.
     * @param specializedChildrenKeys the keys which group specialized children
     * subsets.
     * @param undigesterUtils the UndigesterUtils instance.
     * @param stringValidator the StringValidator instance.
     * @return the complete XML-formatted information of the branch whose
     * root is this node.
     * @precondition nodeId != null
     * @precondition index >= 0
     * @precondition name != null
     * @precondition body != null
     * @precondition undigesterUtils != null
     * @precondition stringValidator != null
     */
    protected String toXml(
        final String nodeId,
        final int index,
        final String name,
        final Collection attributeNames,
        final Map attributeValues,
        final String body,
        final Collection children,
        final Map specializedChildren,
        final Collection specializedChildrenKeys,
        final UndigesterUtils undigesterUtils,
        final StringValidator stringValidator)
    {
        StringBuffer t_sbResult = new StringBuffer("<");

        t_sbResult.append(name);

        if  (   (attributeNames != null)
             && (!attributeNames.isEmpty()))
        {
            Iterator t_itAttributeIterator = attributeNames.iterator();

            while  (t_itAttributeIterator.hasNext())
            {
                t_sbResult.append(" ");

                t_sbResult.append(
                    attributeToXml(
                        nodeId,
                        index,
                        (String) t_itAttributeIterator.next(),
                        attributeValues,
                        undigesterUtils));
            }
        }

        // Bug 122: child order gets messed up.
        String t_strChildren =
            toXml(
                undigesterUtils.mergeChildren(this),
                specializedChildren,
                stringValidator);

        if  (   (stringValidator.isEmpty(body))
             && (stringValidator.isEmpty(t_strChildren)))
        {
            t_sbResult.append("/");
        }
        else
        {
            t_sbResult.append(">");
            t_sbResult.append(body);
            t_sbResult.append(t_strChildren);
            t_sbResult.append("</");
            t_sbResult.append(name);
        }

        t_sbResult.append(">");

        return t_sbResult.toString();
    }

    /**
     * Serializes given attribute.
     * @param nodeId the id of the node.
     * @param index the node index.
     * @param name the attribute name.
     * @param values the attribute values.
     * @param undigesterUtils the UndigesterUtils instance.
     * @return given name-value information serialized as a XML attribute.
     * @precondition nodeId != null
     * @precondition index >= 0
     * @precondition name != null
     * @precondition values != null
     * @precondition undigesterUtils != null
     */
    protected String attributeToXml(
        final String nodeId,
        final int index,
        final String name,
        final Map values,
        final UndigesterUtils undigesterUtils)
    {
        return
            attributeToXml(
                name,
                undigesterUtils.escapeAttribute(
                    (String)
                        values.get(
                            undigesterUtils.buildAttributeKey(
                                nodeId, 
                                undigesterUtils.buildBranchIndex(this),
                                name))));
    }

    /**
     * Serializes given attribute.
     * @param name the attribute name.
     * @param value the attribute value.
     * @return given name-value information serialized as a XML attribute.
     * @precondition name != null
     * @precondition value != null
     */
    protected String attributeToXml(final String name, final String value)
    {
        return name + "=\"" + value + "\"";
    }

    /**
     * Retrieves the result of formatting all children information
     * in XML format.
     * @param children the children collection.
     * @param specializedChildren the specialized children, to find out
     * @param stringValidator the StringValidator instance.
     * if a concrete child is specialized or not.
     * @return such XML-formatted information.
     * @precondition stringValidator != null
     */
    protected String toXml(
        final Collection children,
        final Map specializedChildren,
        final StringValidator stringValidator)
    {
        StringBuffer t_sbResult = new StringBuffer();

        if  (children != null)
        {
            Collection t_cSpecializedKeys = null;

            Iterator t_itChildIterator = children.iterator();

            TreeNode t_CurrentTreeNode = null;

            String t_strSpecializedKey = null;

            boolean t_bSpecialized = false;

            while  (   (t_itChildIterator != null)
                    && (t_itChildIterator.hasNext()))
            {
                t_bSpecialized = false;

                t_CurrentTreeNode = (TreeNode) t_itChildIterator.next();

                if  (specializedChildren != null)
                {
                    t_strSpecializedKey =
                        retrieveSpecializedKey(
                            t_CurrentTreeNode,
                            specializedChildren);

                    if  (t_strSpecializedKey != null)
                    {
                        t_bSpecialized = true;
                    }
                }

                if  (t_bSpecialized)
                {
                    if  (t_cSpecializedKeys == null)
                    {
                        t_cSpecializedKeys = new ArrayList();
                    }

                    if  (!t_cSpecializedKeys.contains(t_strSpecializedKey))
                    {
                        t_sbResult.append(
                            specializedToXml(
                                (Collection)
                                    specializedChildren.get(
                                        t_strSpecializedKey),
                                t_strSpecializedKey,
                                stringValidator));

                        t_cSpecializedKeys.add(t_strSpecializedKey);
                    }
                }
                else
                {
                    t_sbResult.append(t_CurrentTreeNode.toXml());
                }
            }
        }

        return t_sbResult.toString();
    }

    /**
     * Retrieves the specialized key associated to given child node.
     * @param child the child node.
     * @param specializedChildren the specialized children.
     * @return such key.
     * @precondition child != null
     * @precondition specializedChildren != null
     */
    protected String retrieveSpecializedKey(
        final TreeNode child, final Map specializedChildren)
    {
        return
            retrieveSpecializedKey(
                child, specializedChildren, specializedChildren.keySet());
    }

    /**
     * Retrieves the specialized key associated to given child node.
     * @param child the child node.
     * @param specializedChildren the specialized children.
     * @param specializedKeys the specialized keys.
     * @return such key.
     * @precondition child != null
     * @precondition specializedChildren != null
     * @precondition specializedKeys != null
     */
    protected String retrieveSpecializedKey(
        final TreeNode child,
        final Map specializedChildren,
        final Set specializedKeys)
    {
        String result = null;

        Iterator t_itSpecializedKeys = specializedKeys.iterator();

        Object t_Key = null;

        Object t_Value = null;

        Collection t_cChildren = null;

        while  (   (t_itSpecializedKeys != null)
                && (t_itSpecializedKeys.hasNext()))
        {
            t_Key = t_itSpecializedKeys.next();

            if  (t_Key != null)
            {
                t_Value = specializedChildren.get(t_Key);

                if  (   (t_Value != null)
                     && (t_Value instanceof Collection))
                {
                    t_cChildren = (Collection) t_Value;

                    if  (t_cChildren.contains(child))
                    {
                        result = t_Key.toString();

                        break;
                    }
                }
            }
        }

        return result;
    }

    /**
     * Retrieves the result of formatting all specialized children belonging
     * to given key, in XML format.
     * @param children the children collection.
     * @param childrenElementName the children's element name.
     * @param stringValidator the StringValidator instance.
     * @return such XML-formatted information.
     * @precondition stringValidator != null
     */
    protected String specializedToXml(
        final Collection children,
        final String childrenElementName,
        final StringValidator stringValidator)
    {
        StringBuffer t_sbResult = new StringBuffer();

        StringBuffer t_sbChildren = null;

        boolean t_bEmpty = true;

        if  (children != null)
        {
            t_sbChildren = new StringBuffer();

            Iterator t_itChildIterator = children.iterator();

            while  (   (t_itChildIterator != null)
                    && (t_itChildIterator.hasNext()))
            {
                t_sbChildren.append(
                    ((TreeNode) t_itChildIterator.next()).toXml());

                t_bEmpty = false;
            }
        }

        if  (!t_bEmpty)
        {
            String t_strChildren = t_sbChildren.toString();

            if  (stringValidator.isEmpty(childrenElementName))
            {
                t_sbResult.append(t_strChildren);
            }
            else
            {
                t_sbResult.append("<");
                t_sbResult.append(childrenElementName);

                if  (stringValidator.isEmpty(t_strChildren))
                {
                    t_sbResult.append("/>");
                }
                else
                {
                    t_sbResult.append(">");
                    t_sbResult.append(t_strChildren);
                    t_sbResult.append("</");
                    t_sbResult.append(childrenElementName);
                    t_sbResult.append(">");
                }
            }
        }

        return t_sbResult.toString();
    }
}
