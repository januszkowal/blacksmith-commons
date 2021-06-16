package org.blacksmith.commons.tree.traverser;

import java.util.Deque;
import java.util.LinkedList;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeAcceptant;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;
import org.blacksmith.commons.tree.TreeNode.TreeTraverser;

public final class PostOrderTreeTraverser implements TreeTraverser {

  @Override
  public <T> void traverse(TreeNode<T> node, NodeAcceptant<T> visitor) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    final Deque<TreeNode<T>> dq2 = new LinkedList<>();
    dq.add(node);

    while (!dq.isEmpty()) {
      TreeNode<T> n = dq.pollLast();
      dq2.add(n);
      dq.addAll(n.getChildren());
    }

    while (!dq2.isEmpty()) {
      TreeNode<T> n = dq2.pollLast();
      if (!visitor.accept(n)) {
        return;
      }
    }
  }

  @Override
  public <T> void traverse(TreeNode<T> node, NodeVisitor<T> visitor) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    final Deque<TreeNode<T>> dq2 = new LinkedList<>();
    dq.add(node);

    while (!dq.isEmpty()) {
      TreeNode<T> n = dq.pollLast();
      dq2.add(n);
      dq.addAll(n.getChildren());
    }

    while (!dq2.isEmpty()) {
      TreeNode<T> n = dq2.pollLast();
      visitor.visit(n);
    }
  }
}
