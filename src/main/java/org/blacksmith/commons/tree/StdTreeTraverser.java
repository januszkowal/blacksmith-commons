package org.blacksmith.commons.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.blacksmith.commons.tree.TreeNode.NodeVisitor;

/**
 * @f:off
 *                     (1)
 *                    /  \
 *                  /     \
 *                /        \
 *              /           \
 *            /              \
 *         (2)               (3)
 *         / \               / \
 *       /    \            /    \
 *     /       \         /       \
 *   /          \      /          \
 * (4)          (5)  6)           (7)
 *                  / \
 *                 /   \
 *                /     \
 *               /       \
 *             (8)      (9)
 *
 * @f:on
 *
 * PRE:             1,2,4,5,3,6,8,9,7
 * PRE2:            1,2,4,5,3,6,8,9,7
 * POST:            4,5,2,8,9,6,7,3,1
 * BREADTH_FIRST:   1,2,3,4,5,6,7,8,9
 */
public class StdTreeTraverser {
  private StdTreeTraverser(){}
  public static final TreeNode.TreeTraverser PRE_ORDER = new PreOrderTreeTraverser();
  public static final TreeNode.TreeTraverser POST_ORDER = new PostOrderTreeTraverser();
  public static final TreeNode.TreeTraverser BREADTH_ORDER = new BreadthOrderTreeTraverser();
  public static final TreeNode.TreeTraverser PRE2_ORDER = new Pre2TreeTraverser();

  private static final class PostOrderTreeTraverser implements TreeNode.TreeTraverser {
    public <T, U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
      final Deque<TreeNode<T>> dq = new LinkedList<>();
      final Deque<TreeNode<T>> dq2 = new LinkedList<>();
      dq.add(root);

      while (!dq.isEmpty()) {
        TreeNode<T> n = dq.pollLast();
        dq2.add(n);

        final List<? extends TreeNode<T>> children = n.getChildren();
        final int childrenCount = children.size();
        for (TreeNode<T> child : children) {
          dq.add(child);
        }
      }

      while (!dq2.isEmpty()) {
        TreeNode<T> n = dq2.pollLast();

        if (!visitor.onNode(n, callerData)) {
          return false;
        }
      }

      return true;
    }
  }
  
  private static final class PreOrderTreeTraverser implements TreeNode.TreeTraverser {
    public <T, U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
      final Deque<TreeNode<T>> dq = new LinkedList<>();
      dq.add(root);
      while (!dq.isEmpty()) {
        TreeNode<T> n = dq.pollLast();

        if (!visitor.onNode(n, callerData)) {
          return false;
        }
        final List<TreeNode<T>> children = n.getChildren();
        for (int i = children.size() - 1; i >= 0; --i) {
          dq.add(children.get(i));
        }
      }
      return true;
    }
  }

  private static final class BreadthOrderTreeTraverser implements TreeNode.TreeTraverser {
    public <T, U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
      final Deque<TreeNode<T>> dq = new LinkedList<>();
      dq.add(root);

      while (!dq.isEmpty()) {
        TreeNode<T> n = dq.pollFirst();

        if (!visitor.onNode(n, callerData)) {
          return false;
        }

        final List<TreeNode<T>> children = n.getChildren();
        final int childrenCount = children.size();
        for (TreeNode<T> child : children) {
          dq.add(child);
        }
      }

      return true;
    }
  }
  
  private static final class Pre2TreeTraverser implements TreeNode.TreeTraverser {
    private <T, U> boolean internalTraverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
      if (!visitor.onNode(root, callerData)) {
        return false;
      }
      final List<TreeNode<T>> children = root.getChildren();
      final int childrenCount = children.size();
      for (TreeNode<T> child : children) {
        if (!internalTraverse(child, visitor, callerData))
          return false;
      }
      return true;      
    }
    public <T, U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
      return internalTraverse(root,visitor,callerData);
    }
  }
}
