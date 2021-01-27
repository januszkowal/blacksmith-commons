package org.blacksmith.commons.tree;

/**
 * @f:off (1) / \ /     \ /         \ /             \ /                 \ /                     \ (2)
 *   (3) / \                      / \ /     \                  /     \ /         \              /         \ /
 *  \          /             \ (4)             (5)      (6)             (7) / \ /     \ /         \ /             \ (8)
 * (9)
 * @f:on PRE:             1,2,4,5,3,6,8,9,7 POST:            4,5,2,8,9,6,7,3,1 BREADTH_FIRST:   1,2,3,4,5,6,7,8,9 REV:
 * 1,3,7,6,9,8,2,5,4
 */
public class StdTraverseTreeFactory {

  public static TreeNode<Integer> createTree() {
    BTreeNode<Integer> root = new BTreeNode<>(1);
    final TreeNode<Integer> node2 = root.addChildWith(2);
    node2.addChildWith(4);
    node2.addChildWith(5);

    final TreeNode<Integer> node3 = root.addChildWith(3);
    final TreeNode<Integer> node6 = node3.addChildWith(6);
    node3.addChildWith(7);
    node6.addChildWith(8);
    node6.addChildWith(9);
    return root;
  }
}
