package org.blacksmith.commons.tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.blacksmith.commons.arrays.ArrayUtils;

public class BTreeNode<T> implements TreeNode<T> {
  private T data;
  private TreeNode<T> parent;
  private List<TreeNode<T>> children = Collections.emptyList();

  public BTreeNode(T data) {
    this.data = data;
  }

  @Override
  public void setData(T data) {
    this.data = data;
  }

  @Override
  public T getData() {
    return data;
  }

  @Override
  public TreeNode<T> getParent() {
    return this.parent;
  }

  @Override
  public void setParent(TreeNode<T> parent) {
    this.parent = parent;
  }

  @Override public List<TreeNode<T>> getChildren() {
    return this.children;
  }

  @Override public TreeNode<T> addChild(TreeNode<T> child) {
    if (children.isEmpty()) {
      children = new ArrayList<>();
    }
    children.add(child);
    child.setParent(this);
    return child;
  }

  @Override public TreeNode<T> removeChild(TreeNode<T> child) {
    if (child != null && children.remove(child)) {
      child.setParent(null);
      return child;
    }
    return null;
  }

  @Override public TreeNode<T> addChildWith(T o) {
    return addChild(new BTreeNode<>(o));
  }

  @Override public boolean isParentOf(TreeNode<T> n) {
    if (n == null) {
      return false;
    }
    return this.equals(n.getParent());
  }

  @Override public boolean isDescendantOf(TreeNode<T> n) {
    if (n == null) {
      return false;
    }
    TreeNode<T> tmp = findDescendantWith(data);
    return this.equals(tmp);
  }

  @Override public TreeNode<T> removeDescendantWith(T o) {
    return null;
  }

  @Override public boolean contains(T o) {
    return false;
  }

  @Override public void clear() {
    children.clear();
    parent = null;
    data = null;
  }

  @Override public boolean isLeaf() {
    return this.children.isEmpty();
  }

  @Override public boolean isRoot() {
    return this.parent == null;
  }

  @Override
  public int size() {
    final int[] counter = { 0 };
    StdTreeTraverser.PRE2_ORDER.traverse(this, (node, c) -> {
      c[0] = c[0] + 1;
      return true;
    }, counter);
    return counter[0];
  }

  @SuppressWarnings("unchecked")
  @Override public T[] toDataArray(T[] a, TreeTraverser traverser) {
    final int size = size();
    if (a.length < size) {
      a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
    }

    traverser.traverse(this, new NodeVisitor<>() {
      int index = 0;

      @Override
      public boolean onNode(TreeNode<T> node, T[] a) {
        a[index++] = node.getData();
        return true;
      }
    }, a);
    return a;
  }

  @Override public Object[] toArray(TreeTraverser traverser) {
    final int size = size();
    Object[] a = new Object[size];
    traverser.traverse(this, new NodeVisitor<>() {
      int index = 0;

      @Override
      public boolean onNode(TreeNode<T> node, Object[] a) {
        a[index++] = node;
        return true;
      }
    }, a);
    return a;
  }

  @SuppressWarnings("unchecked")
  @Override
  public TreeNode<T> findDescendantWith(final T o) {
    final Object[] found = { null };
    StdTreeTraverser.PRE2_ORDER.traverse(this, (node, found1) -> {
      if (node.getData().equals(o)) {
        found1[0] = node;
        return false;
      }
      return true;
    }, found);
    return (TreeNode<T>) found[0];
  }

  @SuppressWarnings("unchecked")
  @Override public TreeNode<T>[] findDescendantsWith(T o) {
    final List<TreeNode<T>> found = new ArrayList<>();
    StdTreeTraverser.PRE2_ORDER.traverse(this, (node, found1) -> {
      if (node.getData().equals(o)) {
        found1.add(node);
        return false;
      }
      return true;
    }, found);
    return ArrayUtils.listToArray(TreeNode.class,found);
  }
}
