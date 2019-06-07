package org.blacksmith.commons.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.blacksmith.commons.tree.TreeNode.NodeVisitor;

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
public class StdTreeTraverser
{
	public final static TreeNode.TreeTraverser PRE_ORDER = new PreOrderTreeTraverser();
	public final static TreeNode.TreeTraverser POST_ORDER = new PostOrderTreeTraverser();
	public final static TreeNode.TreeTraverser BREADTH_ORDER = new BreadthOrderTreeTraverser();
	
	private static final class PostOrderTreeTraverser implements TreeNode.TreeTraverser
	{
		public <T,U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
			final Deque<TreeNode<T>> dq = new LinkedList<>();
	      final Deque<TreeNode<T>> dq2 = new LinkedList<>();
	      dq.add(root);

	      while (! dq.isEmpty()) {
	        TreeNode<T> n = dq.pollLast();
	        dq2.add(n);

	        final List<? extends TreeNode<T>> children = n.getChildren();
	        final int childrenCount = children.size();
	        for (int i=0; i<childrenCount; ++i) {
	          dq.add(children.get(i));
	        }
	      }

	      while (! dq2.isEmpty()) {
	        TreeNode<T> n = dq2.pollLast();

	        if (!visitor.onNode(n, callerData)) {
	          // No more iteration if the caller callback returns false;
	          return false;
	        }
	      }

	      return true;
		}
	}

	private static final class PreOrderTreeTraverser implements TreeNode.TreeTraverser
	{
		public <T,U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
			final Deque<TreeNode<T>> dq = new LinkedList<>();
	      dq.add(root);
	      while (! dq.isEmpty()) {
	        TreeNode<T> n = dq.pollLast();

	        if (!visitor.onNode(n, callerData)) {
	          // No more iteration if the caller callback returns false;
	          return false;
	        }
	        final List<TreeNode<T>> children = n.getChildren();
	        for (int i=children.size()-1; i>=0; --i) {
	          dq.add(children.get(i));
	        }
	      }
	      return true;
		}
	}
	
	private static final class BreadthOrderTreeTraverser implements TreeNode.TreeTraverser
	{
		public <T,U> boolean traverse(TreeNode<T> root, NodeVisitor<T, U> visitor, U callerData) {
			final Deque<TreeNode<T>> dq = new LinkedList<>();
	      dq.add(root);

	      while (! dq.isEmpty()) {
	        TreeNode<T> n = dq.pollFirst();

	        if (!visitor.onNode(n, callerData)) {
	          // No more iteration if the caller callback returns false;
	          return false;
	        }

	        final List<TreeNode<T>> children = n.getChildren();
	        final int childrenCount = children.size();
	        for (int i=0; i<childrenCount; ++i) {
	          dq.add(children.get(i));
	        }
	      }

	      return false;
		}
	}
}
