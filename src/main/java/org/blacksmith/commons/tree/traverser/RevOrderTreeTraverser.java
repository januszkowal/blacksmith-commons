package org.blacksmith.commons.tree.traverser;

import java.util.List;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;

public class RevOrderTreeTraverser implements TreeNode.TreeTraverser {

  @Override
  public <T> void traverse(TreeNode<T> node, NodeVisitor<T> visitor) {
    if (!visitor.accept(node)) {
      return;
    }
    visitor.visit(node);
    if (visitor.acceptChildren(node)) {
      final List<TreeNode<T>> children = node.getChildren();
      for (int i = children.size() - 1; i >= 0; --i) {
        traverse(children.get(i), visitor);
      }
    }
  }
}
