package org.blacksmith.commons.tree.traverser;

import java.util.List;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;

@SuppressWarnings("ForLoopReplaceableByForEach")
public final class PreOrderTreeTraverserRecur implements TreeNode.TreeTraverser {

  @Override
  public <T> void traverse(TreeNode<T> node, NodeVisitor<T> visitor) {
    if (!visitor.accept(node)) {
      return;
    }
    visitor.visit(node);
    final List<TreeNode<T>> children = node.getChildren();
    for (int i = 0; i < children.size(); i++) {
      traverse(children.get(i), visitor);
    }
  }
}
