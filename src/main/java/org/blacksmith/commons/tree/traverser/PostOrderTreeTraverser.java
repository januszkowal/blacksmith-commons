package org.blacksmith.commons.tree.traverser;

import java.util.Deque;
import java.util.LinkedList;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;
import org.blacksmith.commons.tree.TreeNode.NodeVisitorNa;
import org.blacksmith.commons.tree.TreeNode.TreeTraverser;

public final class PostOrderTreeTraverser implements TreeTraverser {
  public static PostOrderTreeTraverser of() {
    return new PostOrderTreeTraverser();
  }

  public <T,U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    final Deque<TreeNode<T>> dq2 = new LinkedList<>();
    dq.add(root);

    while (!dq.isEmpty()) {
      TreeNode<T> n = dq.pollLast();
      dq2.add(n);
      dq.addAll(n.getChildren());
    }

    while (!dq2.isEmpty()) {
      TreeNode<T> n = dq2.pollLast();
      if (!visitor.onNode(n, callerData)) {
        return false;
      }
    }

    return true;
  }

  public <T,U> void traverse(TreeNode<T> root, NodeVisitorNa<T, U> visitor, U callerData) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    final Deque<TreeNode<T>> dq2 = new LinkedList<>();
    dq.add(root);

    while (!dq.isEmpty()) {
      TreeNode<T> n = dq.pollLast();
      dq2.add(n);
      dq.addAll(n.getChildren());
    }

    while (!dq2.isEmpty()) {
      TreeNode<T> n = dq2.pollLast();
      visitor.onNode(n, callerData);
    }
  }
}
