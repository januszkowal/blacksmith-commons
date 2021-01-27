package org.blacksmith.commons.tree.traverser;

import java.util.List;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeAcceptant;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;

public final class RevOrderTreeTraverserRecur implements TreeNode.TreeTraverser {

  public <T, U> boolean traverse(TreeNode<T> root, NodeAcceptant<T, U> visitor, U callerData) {
    if (visitor.reject(root, callerData)) {
      return false;
    }
    final List<TreeNode<T>> children = root.getChildren();
    for (int i = children.size() - 1; i >= 0; --i) {
      if (!traverse(children.get(i), visitor, callerData)) {
        return false;
      }
    }
    return true;
  }

  public <T, U> void traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
    visitor.visit(root, callerData);
    final List<TreeNode<T>> children = root.getChildren();
    for (int i = children.size() - 1; i >= 0; --i) {
      traverse(children.get(i), visitor, callerData);
    }
  }
}
