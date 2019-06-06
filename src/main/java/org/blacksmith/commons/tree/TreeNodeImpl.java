package org.blacksmith.commons.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNodeImpl<T> implements TreeNode<T> {
  private T data;
  private TreeNode<T> parent;
  private List<TreeNode<T>> children = Collections.emptyList();

  public TreeNodeImpl(T data) {
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
    return addChild(new TreeNodeImpl<T>(o));
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
    return this.parent==null;
  }
}
