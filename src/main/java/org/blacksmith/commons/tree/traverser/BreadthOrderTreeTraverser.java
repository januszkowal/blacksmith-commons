package org.blacksmith.commons.tree.traverser;

import java.util.Deque;
import java.util.LinkedList;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;
import org.blacksmith.commons.tree.TreeNode.NodeVisitorNa;

public final class BreadthOrderTreeTraverser implements TreeNode.TreeTraverser {
  public static BreadthOrderTreeTraverser of () {
    return new BreadthOrderTreeTraverser();
  }
  public <T,U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    dq.add(root);
    while (!dq.isEmpty()) {
      TreeNode<T> n = dq.pollFirst();
      if (!visitor.onNode(n, callerData)) {
        return false;
      }
      dq.addAll(n.getChildren());
    }

    return true;
  }

  public <T,U> void traverse(TreeNode<T> root, NodeVisitorNa<T, U> visitor, U callerData) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    dq.add(root);
    while (!dq.isEmpty()) {
      TreeNode<T> n = dq.pollFirst();
      visitor.onNode(n, callerData);
      dq.addAll(n.getChildren());
    }
  }
}
