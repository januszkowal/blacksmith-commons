package org.blacksmith.commons.tree.traverser;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;

public final class PreOrderTreeTraverser implements TreeNode.TreeTraverser {

  @Override
  public <T> void traverse(TreeNode<T> node, NodeVisitor<T> visitor) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    dq.add(node);
    while (!dq.isEmpty()) {
      TreeNode<T> n = dq.pollLast();

      if (visitor.accept(n)) {
        visitor.visit(n);
        if (visitor.acceptChildren(n)) {
          final List<TreeNode<T>> children = n.getChildren();
          for (int i = children.size() - 1; i >= 0; --i) {
            dq.add(children.get(i));
          }
        }
      }
    }
  }
}
