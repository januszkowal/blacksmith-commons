package org.blacksmith.commons.tree.traverser;

import java.util.Deque;
import java.util.LinkedList;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;

public final class BreadthOrderTreeTraverser implements TreeNode.TreeTraverser {

  @Override
  public <T> void traverse(TreeNode<T> node, NodeVisitor<T> visitor) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    dq.add(node);
    while (!dq.isEmpty()) {
      TreeNode<T> n = dq.pollFirst();
      if (visitor.accept(n)) {
        visitor.visit(n);
        if (visitor.acceptChildren(n)) {
          dq.addAll(n.getChildren());
        }
      }
    }
  }
}
