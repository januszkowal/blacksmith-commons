package org.blacksmith.commons.tree.traverser;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeAcceptant;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;

public final class PreOrderTreeTraverser implements TreeNode.TreeTraverser {

  public <T, U> boolean traverse(TreeNode<T> root, NodeAcceptant<T, U> visitor, U callerData) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    dq.add(root);
    while (!dq.isEmpty()) {
      TreeNode<T> n = dq.pollLast();

      if (visitor.reject(n, callerData)) {
        return false;
      }
      final List<TreeNode<T>> children = n.getChildren();
      for (int i = children.size() - 1; i >= 0; --i) {
        dq.add(children.get(i));
      }
    }
    return true;
  }

  public <T, U> void traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    dq.add(root);
    while (!dq.isEmpty()) {
      TreeNode<T> n = dq.pollLast();

      visitor.visit(n, callerData);
      final List<TreeNode<T>> children = n.getChildren();
      for (int i = children.size() - 1; i >= 0; --i) {
        dq.add(children.get(i));
      }
    }
  }
}
