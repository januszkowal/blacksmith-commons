package org.blacksmith.commons.tree.traverser;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;
import org.blacksmith.commons.tree.TreeNode.TreeTraverser;

public final class PostOrderTreeTraverser implements TreeTraverser {

  @Override
  public <T> void traverse(TreeNode<T> node, NodeVisitor<T> visitor) {
    final Deque<TreeNode<T>> dq = new ArrayDeque<>();
    final Deque<TreeNode<T>> dq2 = new ArrayDeque<>();
    if (!visitor.accept(node)) {
      return;
    }
    dq.add(node);
    TreeNode<T> n;
    while ((n = dq.pollLast()) != null) {
      dq2.add(n);
      final List<TreeNode<T>> children = n.getChildren();
      if (!children.isEmpty() && visitor.acceptChildren(n)) {
        for (int i = 0; i < children.size(); ++i) {
          TreeNode<T> child = children.get(i);
          if (visitor.accept(child)) {
            dq.add(child);
          }
        }
      }
    }

    while ((n = dq2.pollLast()) != null) {
      visitor.visit(n);
    }
  }

  @Override
  public <T> void fullTraverse(TreeNode<T> node, NodeVisitor<T> visitor) {
    final Deque<TreeNode<T>> dq = new ArrayDeque<>();
    final Deque<TreeNode<T>> dq2 = new ArrayDeque<>();
    dq.add(node);
    TreeNode<T> n;
    while ((node = dq.pollLast()) != null) {
      dq2.add(node);
      dq.addAll(node.getChildren());
    }

    while ((n = dq2.pollLast()) != null) {
      visitor.visit(n);
    }
  }
}
