package org.blacksmith.commons.tree;

import java.util.List;

public interface TreeNode<T> {
  void setData(T data);
  T getData();
  TreeNode<T> getParent();
  void setParent(TreeNode<T> parent);
  List<TreeNode<T>> getChildren();
  TreeNode<T> addChild(TreeNode<T> child);
  TreeNode<T> removeChild(TreeNode<T> child);
  TreeNode<T> addChildWith(T o);
  boolean isParentOf(TreeNode<T> n);
  boolean isDescendantOf(TreeNode<T> n);
  TreeNode<T> removeDescendantWith(T o);
  TreeNode<T> findDescendantWith(final T o);
  boolean contains(T o);
  void clear();
  boolean isLeaf();
  boolean isRoot();
  int size();

  interface NodeVisitor<T,U> {
    boolean onNode(TreeNode<T> node, U caller);
  }

  interface TreeTraverser {
	  <T,U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData);
  }

  T[] toDataArray(T[] a, TreeTraverser traverser);
  Object[] toArray(TreeTraverser traverser);
}
