package org.blacksmith.commons.tree;

import java.lang.reflect.Array;
import java.util.Deque;
import java.util.LinkedList;
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
  boolean contains(T o);
  void clear();
  boolean isLeaf();
  boolean isRoot();

  interface Traverser<NODE, U> {
    boolean onNode(NODE node, U caller);
  }

  enum TraverseOrder {
    PRE,
    POST,
    BREATH_FIRST
  }

  default <U> boolean traverse(Traverser<TreeNode<T>, U> traverser, U callerData, TreeNode.TraverseOrder traverseOrder) {
    switch (traverseOrder) {
      case PRE:
        return traverseByPreOrder(traverser, callerData);
      case POST:
        return traverseByPostOrder(traverser, callerData);
      case BREATH_FIRST:
        return traverseByBreathFirstOrder(traverser, callerData);
    }
    return true;
  }

  default <U> boolean traverseByPreOrder(Traverser<TreeNode<T>, U> traverser, U callerData) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    dq.add(this);

    while (! dq.isEmpty()) {
      TreeNode<T> n = dq.pollLast();

      if (! traverser.onNode(n, callerData)) {
        // No more iteration if the caller callback returns false;
        return false;
      }

      final List<TreeNode<T>> children = (List<TreeNode<T>>) n.getChildren();
      final int childrenCount = children.size();
      for (int i=childrenCount-1; i>=0; --i) {
        final TreeNode<T> child = (TreeNode<T>) children.get(i);
        dq.add(child);
      }
    }

    return true;
  }

  default  <U> boolean traverseByPostOrder(TreeNode.Traverser<TreeNode<T>, U> traverser, U callerData) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    final Deque<TreeNode<T>> dq2 = new LinkedList<>();
    dq.add(this);

    while (! dq.isEmpty()) {
      TreeNode<T> n = dq.pollLast();
      dq2.add(n);

      final List<TreeNode<T>> children = (List<TreeNode<T>>) n.getChildren();
      final int childrenCount = children.size();
      for (int i=0; i<childrenCount; ++i) {
        final TreeNode<T> child = (TreeNode<T>) children.get(i);
        dq.add(child);
      }
    }

    while (! dq2.isEmpty()) {
      TreeNode<T> n = dq2.pollLast();

      if (! traverser.onNode(n, callerData)) {
        // No more iteration if the caller callback returns false;
        return false;
      }
    }

    return true;
  }

  default <U> boolean traverseByBreathFirstOrder(TreeNode.Traverser<TreeNode<T>, U> traverser, U callerData) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    dq.add(this);

    while (! dq.isEmpty()) {
      TreeNode<T> n = dq.pollFirst();

      if (! traverser.onNode(n, callerData)) {
        // No more iteration if the caller callback returns false;
        return false;
      }

      final List<TreeNode<T>> children = (List<TreeNode<T>>) n.getChildren();
      final int childrenCount = children.size();
      for (int i=0; i<childrenCount; ++i) {
        final TreeNode<T> child = (TreeNode<T>) children.get(i);
        dq.add(child);
      }
    }

    return true;
  }

  default int size() {
    final int[] counter = { 0 };
    traverseByBreathFirstOrder(new Traverser<TreeNode<T>, int[]>() {
      @Override
      public boolean onNode(TreeNode<T> node, int[] c) {
        c[0] = c[0] + 1;
        return true;
      }
    }, counter);
    return counter[0];
  }

  default Object[] toArray(TraverseOrder traverseOrder) {
    final int size = size();
    Object[] a = new Object[size];
    traverse(new Traverser<TreeNode<T>, Object[]>() {
      int index = 0;

      @Override
      public boolean onNode(TreeNode<T> node, Object[] a) {
        a[index++] = node;
        return true;
      }
    }, a, traverseOrder);
    return a;
  }

  default T[] toDataArray(T[] a, TreeNode.TraverseOrder traverseOrder) {
    final int size = size();
    if (a.length < size) {
      a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
    }

    traverse(new TreeNode.Traverser<TreeNode<T>, T[]>() {
      int index = 0;

      @Override
      public boolean onNode(TreeNode<T> node, T[] a) {
        a[index++] = node.getData();
        return true;
      }
    }, a, traverseOrder);

    return a;
  }

  default TreeNode<T> findDescendantWith(final T o) {
    final Object[] found = { null };
    traverseByBreathFirstOrder(new TreeNode.Traverser<TreeNode<T>, Object[]>() {
      @Override
      public boolean onNode(TreeNode<T> node, Object[] found) {
        if (node.getData().equals(o)) {
          found[0] = node;
          return false;
        }
        return true;
      }
    }, found);
    return (TreeNode<T>) found[0];
  }

}
