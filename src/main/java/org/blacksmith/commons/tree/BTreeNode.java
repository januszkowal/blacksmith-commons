package org.blacksmith.commons.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import org.blacksmith.commons.tree.traverser.RevOrderTreeTraverserRecur;

public class BTreeNode<T> implements TreeNode<T> {

  public static final TreeNode.TreeTraverser TRAVERSER = new RevOrderTreeTraverserRecur();
  public static final TreeNode.TreeTraverser SIZE_TRAVERSER = new RevOrderTreeTraverserRecur();
  private T data;
  private BTreeNode<T> parent;
  private List<TreeNode<T>> children = Collections.emptyList();

  public BTreeNode(T data) {
    this.data = data;
  }

  public static <T> BTreeNode<T> of(T data) {
    return new BTreeNode<>(data);
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
  public BTreeNode<T> getParent() {
    return this.parent;
  }

  @Override
  public void setParent(TreeNode<T> parent) {
    this.parent = (BTreeNode<T>) parent;
  }

  @Override
  public List<TreeNode<T>> getChildren() {
    return this.children;
  }

  @Override
  public BTreeNode<T> addChild(TreeNode<T> child) {
    if (children.isEmpty()) {
      children = new ArrayList<>();
    }
    child.setParent(this);
    children.add(child);
    return (BTreeNode<T>) child;
  }

  @Override
  public BTreeNode<T> removeChild(TreeNode<T> child) {
    if (child != null && children.remove(child)) {
      child.setParent(null);
      return (BTreeNode<T>)child;
    }
    return null;
  }

  @Override
  public BTreeNode<T> addChildWith(T o) {
    return addChild(new BTreeNode<>(o));
  }

  @Override
  public boolean isParentOf(TreeNode<T> n) {
    if (n == null) {
      return false;
    }
    return this.equals(n.getParent());
  }

  @Override
  public BTreeNode<T> removeDescendantWith(T o) {
    var node = (BTreeNode<T>)findDescendantWith(o);
    if (node!=null && node.parent!=null) {
      node.getParent().removeChild(node);
      node.setParent(null);
    }
    return node;
  }

  @Override
  public boolean contains(T o) {
    return findDescendantWith(o)!=null;
  }

  @Override
  public void clear() {
    children.clear();
    parent = null;
    data = null;
  }

  @Override
  public boolean isLeaf() {
    return (this.parent!=null) && this.children.isEmpty();
  }

  @Override
  public boolean isRoot() {
    return this.parent == null;
  }

  @Override
  public int size() {
    return size(SIZE_TRAVERSER);
  }

  @Override
  public int size(TreeTraverser traverser) {
    final int[] counter = {0};
    traverser.traverse(this, (node, c) -> {
      c[0] = c[0] + 1;
    }, counter);
    return counter[0];
  }

  @Override
  public Object[] toArray() {
    return toArray(TRAVERSER);
  }

  @Override
  public T[] toDataArray(T[] a) {
    return toDataArray(a,TRAVERSER);
  }

  @Override
  public List<TreeNode<T>> toList() {
    return toList(TRAVERSER);
  }

  @Override
  public List<T> toDataList() {
    return toDataList(TRAVERSER);
  }

  @SuppressWarnings("unchecked")
  @Override
  public TreeNode<T> findDescendantWith(final T o) {
    final Object[] found = {null};
    TRAVERSER.traverse(this,(node,found1)->{
      if (node.getData().equals(o)) {
        found1[0] = node;
        return false;
      }
      return true;
    },found);
    return (TreeNode<T>) found[0];
  }

  @SuppressWarnings("unchecked")
  @Override
  public TreeNode<T>[] findDescendantsArrayWith(T o) {
    return findDescendantsWith(o).toArray(new TreeNode[0]);
  }

  @SuppressWarnings("unchecked")
  @Override
  public TreeNode<T>[] findDescendantsArrayWith(Predicate<T> p) {
    return findDescendantsWith(p).toArray(new TreeNode[0]);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<TreeNode<T>> findDescendantsWith(T o) {
    final List<TreeNode<T>> found = new ArrayList<>();
    TRAVERSER.traverse(this, (node, f) -> {
      if (node.getData().equals(o)) {
        f.add(node);
      }
    }, found);
    return found;
  }

  @Override
  public List<TreeNode<T>> findDescendantsWith(Predicate<T> p) {
    final List<TreeNode<T>> found = new ArrayList<>();
    TRAVERSER.traverse(this, (node, f) -> {
      if (p.test(node.getData())) {
        f.add(node);
      }
    }, found);
    return found;
  }
}
