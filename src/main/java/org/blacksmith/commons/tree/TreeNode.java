package org.blacksmith.commons.tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
  TreeNode<T> removeDescendantWith(T o);
  TreeNode<T> findDescendantWith(final T o);
  TreeNode<T>[] findDescendantsWith(final T o);
  TreeNode<T>[] findDescendantsWith(Predicate<T> p);
  List<TreeNode<T>> findDescendantsListWith(final T o);
  List<TreeNode<T>> findDescendantsListWith(Predicate<T> p);
  boolean contains(T o);
  void clear();
  boolean isLeaf();
  boolean isRoot();
  int size();
  int size(TreeTraverser traverser);

  default boolean isDescendantOf(TreeNode<T> n) {
    if (n == null) {
      return false;
    }
    TreeNode<T> tmp = findDescendantWith(n.getData());
    return this.equals(tmp);
  }

  T[] toDataArray(T[] a);
  Object[] toArray();
  List<TreeNode<T>> toList();
  List<T> toDataList();

  default T[] toDataArray(T[] a, TreeTraverser traverser) {
    final int size = size();
    if (a.length < size) {
      a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
    }

    traverser.traverse(this, new NodeVisitorNa<>() {
      int index = 0;
      @Override
      public void accept(TreeNode<T> node, T[] a) {
        a[index++] = node.getData();
      }
    }, a);
    return a;
  }

  default Object[] toArray(TreeTraverser traverser) {
    final int size = size();
    Object[] a = new Object[size];
    traverser.traverse(this, new NodeVisitorNa<>() {
      int index = 0;
      @Override
      public void accept(TreeNode<T> node, Object[] a) {
        a[index++] = node;
      }
    }, a);
    return a;
  }

  default List<TreeNode<T>> toList(TreeTraverser traverser) {
    List<TreeNode<T>> result = new ArrayList<>();
    traverser.traverse(this, (node, x) -> {
      x.add(node);
    }, result);
    return result;
  }

  default List<T> toDataList(TreeTraverser traverser) {
    List<T> result = new ArrayList<>();
    traverser.traverse(this, (node, x) -> {
      x.add(node.getData());
    }, result);
    return result;
  }

  interface NodeVisitor<T,U> {
    boolean accept(TreeNode<T> node, U caller);
  }

  interface NodeVisitorNa<T,U> {
    void accept(TreeNode<T> node, U caller);
  }

  interface TreeTraverser {
	  <T,U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData);
    <T,U> void traverse(TreeNode<T> root, NodeVisitorNa<T, U> visitor, U callerData);
  }

}
