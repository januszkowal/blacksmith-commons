package org.blacksmith.commons.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
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
 *
 * PRE:  1,2,4,5,3,6,8,9,7
 * POST: 4,5,2,8,9,6,7,3,1
 * BREADTH_FIRST: 1,2,3,4,5,6,7,8,9
 */
public enum StdBTreeTraverser implements TreeNode.TreeTraverser {

  PRE_ORDER {
    public boolean traverse(TreeNode root, TreeNode.NodeVisitor visitor, Object callerData) {
      final Deque<TreeNode<?>> dq = new LinkedList<>();
      dq.add(root);
      while (! dq.isEmpty()) {
        TreeNode<?> n = dq.pollLast();

        if (!visitor.onNode(n, callerData)) {
          // No more iteration if the caller callback returns false;
          return false;
        }
        final List<? extends TreeNode<?>> children = n.getChildren();
        for (int i=children.size()-1; i>=0; --i) {
          final TreeNode<?> child = children.get(i);
          dq.add(child);
        }
      }
      return true;
    }
  },
  POST_ORDER{
    public boolean traverse(TreeNode root, TreeNode.NodeVisitor visitor, Object callerData) {
      final Deque<TreeNode<?>> dq = new LinkedList<>();
      final Deque<TreeNode<?>> dq2 = new LinkedList<>();
      dq.add(root);

      while (! dq.isEmpty()) {
        TreeNode<?> n = dq.pollLast();
        dq2.add(n);

        final List<? extends TreeNode<?>> children = n.getChildren();
        final int childrenCount = children.size();
        for (int i=0; i<childrenCount; ++i) {
          final TreeNode<?> child = children.get(i);
          dq.add(child);
        }
      }

      while (! dq2.isEmpty()) {
        TreeNode<?> n = dq2.pollLast();

        if (!visitor.onNode(n, callerData)) {
          // No more iteration if the caller callback returns false;
          return false;
        }
      }

      return true;
    }
  },
  BREADTH_ORDER{
    public boolean traverse(TreeNode root, TreeNode.NodeVisitor visitor, Object callerData) {
      final Deque<TreeNode<?>> dq = new LinkedList<>();
      dq.add(root);

      while (! dq.isEmpty()) {
        TreeNode<?> n = dq.pollFirst();

        if (!visitor.onNode(n, callerData)) {
          // No more iteration if the caller callback returns false;
          return false;
        }

        final List<? extends TreeNode<?>> children = n.getChildren();
        final int childrenCount = children.size();
        for (int i=0; i<childrenCount; ++i) {
          final TreeNode<?> child = children.get(i);
          dq.add(child);
        }
      }

      return false;
    }
  };
}
