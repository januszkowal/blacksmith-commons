package org.blacksmith.commons.tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @f:off
 *                        (1)
 *                        / \
 *                      /     \
 *                    /         \
 *                  /             \
 *                /                 \
 *              /                     \
 *           (2)                      (3)
 *           / \                      / \
 *         /     \                  /     \
 *       /         \              /         \
 *     /             \          /             \
 *   (4)             (5)      (6)             (7)
 *                           / \
 *                         /     \
 *                       /         \
 *                     /             \
 *                   (8)             (9)
 *
 * @f:on
 *
 * PRE:             1,2,4,5,3,6,8,9,7
 * POST:            4,5,2,8,9,6,7,3,1
 * BREADTH_FIRST:   1,2,3,4,5,6,7,8,9
 * REV:             1,3,7,6,9,8,2,5,4
 */
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
  TreeNode<T>[] findDescendantsArrayWith(final T o);
  TreeNode<T>[] findDescendantsArrayWith(Predicate<T> p);
  List<TreeNode<T>> findDescendantsWith(final T o);
  List<TreeNode<T>> findDescendantsWith(Predicate<T> p);
  boolean contains(T o);
  void clear();
  boolean isLeaf();
  boolean isRoot();
  int size();
  int size(TreeTraverser traverser);

  default boolean hasChildren() {
    return !getChildren().isEmpty();
  }

  default boolean isDescendantOf(TreeNode<T> node) {
    if (node == null) {
      return false;
    }
    TreeNode<T> n = this;
    TreeNode<T> parent;
    while ((parent=n.getParent())!=null) {
      if (parent==node) return
        true;
      n = parent;
    }
    return false;
  }

  default boolean isChildOf(TreeNode<T> node) {
    return (this.getParent()==null) ? false : this.getParent()==node;
  }

  T[] toDataArray(T[] a);
  Object[] toArray();
  List<TreeNode<T>> toList();
  List<T> toDataList();

  @SuppressWarnings("unchecked")
  default T[] toDataArray(T[] a, TreeTraverser traverser) {
    final int size = size();
    if (a.length < size) {
      a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
    }

    traverser.traverse(this, new NodeVisitor<>() {
      int index = 0;
      @Override
      public void visit(TreeNode<T> node, T[] a) {
        a[index++] = node.getData();
      }
    }, a);
    return a;
  }

  default Object[] toArray(TreeTraverser traverser) {
    final int size = size();
    Object[] a = new Object[size];
    traverser.traverse(this, new NodeVisitor<>() {
      int index = 0;
      @Override
      public void visit(TreeNode<T> node, Object[] a) {
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

  interface NodeAcceptant<T,U> {
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean accept(TreeNode<T> node, U caller);
    default boolean reject(TreeNode<T> node, U caller) {return !accept(node,caller);}
  }

  interface NodeVisitor<T,U> {
    void visit(TreeNode<T> node, U caller);
  }

  interface TreeTraverser {
	  <T,U> boolean traverse(TreeNode<T> root, NodeAcceptant<T, U> visitor, U callerData);
    <T,U> void traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData);
  }

}
