package org.blacksmith.commons.tree.traverser;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;

public final class PreOrderTreeTraverser implements TreeNode.TreeTraverser {

  @Override
  public <T> void traverse(TreeNode<T> node, NodeVisitor<T> visitor) {
    final Deque<TreeNode<T>> dq = new ArrayDeque<>();
    if (!visitor.accept(node)) {
      return;
    }
    dq.add(node);
    TreeNode<T> n;
    while ((n = dq.pollLast()) != null) {
      visitor.visit(n);
      final List<TreeNode<T>> children = n.getChildren();
      if (!children.isEmpty() && visitor.acceptChildren(n)) {
        for (int i = children.size() - 1; i >= 0; --i) {
          TreeNode<T> child = children.get(i);
          if (visitor.accept(child)) {
            dq.add(child);
          }
        }
      }
    }
  }

  @Override
  public <T> void fullTraverse(TreeNode<T> node, NodeVisitor<T> visitor) {
    final Deque<TreeNode<T>> dq = new ArrayDeque<>();
    if (!visitor.accept(node)) {
      return;
    }
    dq.add(node);
    TreeNode<T> n;
    while ((node = dq.pollLast()) != null) {
      visitor.visit(node);
      final List<TreeNode<T>> children = node.getChildren();
      for (int i = children.size() - 1; i >= 0; --i) {
        dq.add(children.get(i));
      }
    }
  }
}
