package org.blacksmith.commons.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @f:off (1) / \ /     \ /         \ /             \ /                 \ /                     \ (2) (3) / \
 *           / \ /     \                  /     \ /         \              /         \ / \          /             \ (4)
 * (5)      (6)             (7) / \ /     \ /         \ /             \ (8) (9)
 * @f:on PRE:             1,2,4,5,3,6,8,9,7 POST:            4,5,2,8,9,6,7,3,1 BREADTH_FIRST:   1,2,3,4,5,6,7,8,9 REV:
 * 1,3,7,6,9,8,2,5,4
 **/
public interface TreeNode<T> {

  T getData();

  void setData(T data);

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
    while ((parent = n.getParent()) != null) {
      if (parent == node) {
        return
            true;
      }
      n = parent;
    }
    return false;
  }

  default boolean isChildOf(TreeNode<T> node) {
    return (this.getParent() != null) && this.getParent() == node;
  }

  T[] toDataArray(T[] a);

  Object[] toArray();

  List<TreeNode<T>> toList();

  List<T> toDataList();

  @SuppressWarnings("unchecked")
  default T[] toDataArray(T[] a, TreeTraverser traverser) {
    return toDataList(traverser).toArray(a);
  }

  default Object[] toDataArray(TreeTraverser traverser) {
    return toList(traverser).toArray();
  }

  default Object[] toArray(TreeTraverser traverser) {
    return toList(traverser).toArray();
  }

  default List<TreeNode<T>> toList(TreeTraverser traverser) {
    List<TreeNode<T>> result = new ArrayList<>();
    traverser.traverse(this, (node) -> {
      result.add(node);
    });
    return result;
  }

  default List<T> toDataList(TreeTraverser traverser) {
    List<T> result = new ArrayList<>();
    traverser.traverse(this, (node) -> {
      result.add(node.getData());
    });
    return result;
  }

  interface NodeVisitor<T> {
    void visit(TreeNode<T> node);
    default boolean accept(TreeNode<T> node) {
      return true;
    }
  }

  interface TreeTraverser {
    <T> void traverse(TreeNode<T> node, NodeVisitor<T> visitor);
  }
}
