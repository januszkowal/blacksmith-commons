package org.blacksmith.commons.tree.traverser;

import java.util.List;
import org.blacksmith.commons.tree.TreeNode;
import org.blacksmith.commons.tree.TreeNode.NodeVisitor;
import org.blacksmith.commons.tree.TreeNode.NodeVisitorNa;

@SuppressWarnings("ForLoopReplaceableByForEach")
public final class PreOrderTreeTraverserRecur implements TreeNode.TreeTraverser {
  public static PreOrderTreeTraverserRecur of() {
    return new PreOrderTreeTraverserRecur();
  }
  public <T, U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
    if (!visitor.accept(root, callerData)) {
      return false;
    }
    final List<TreeNode<T>> children = root.getChildren();
    for (int i=0; i<children.size(); i++) {
      if (!traverse(children.get(i), visitor, callerData)) {
        return false;
      }
    }
    return true;
  }

  public <T, U> void traverse(TreeNode<T> root, NodeVisitorNa<T, U> visitor, U callerData) {
    visitor.accept(root, callerData);
    final List<TreeNode<T>> children = root.getChildren();
    for (int i=0; i<children.size(); i++) {
      traverse(children.get(i), visitor, callerData);
    }
  }
}
