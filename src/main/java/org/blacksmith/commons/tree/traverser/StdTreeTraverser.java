package org.blacksmith.commons.tree.traverser;

import org.blacksmith.commons.tree.TreeNode.TreeTraverser;

/**
 * @f:off
 *                        (1)
 *                        / \
 *                      /     \
 *                    /         \
 *                  /             \
 *                /                 \
 *              /                     \
 *           (2)                      (3)
 *           / \                      / \
 *         /     \                  /     \
 *       /         \              /         \
 *     /             \          /             \
 *   (4)             (5)      (6)             (7)
 *                           / \
 *                         /     \
 *                       /         \
 *                     /             \
 *                   (8)             (9)
 *
 * @f:on
 *
 * PRE:             1,2,4,5,3,6,8,9,7
 * POST:            4,5,2,8,9,6,7,3,1
 * BREADTH_FIRST:   1,2,3,4,5,6,7,8,9
 * REV:             1,3,7,6,9,8,2,5,4
 */
public class StdTreeTraverser {
  private StdTreeTraverser(){}
  public static final TreeTraverser PRE_ORDER = new PreOrderTreeTraverser();
  public static final TreeTraverser POST_ORDER = new PostOrderTreeTraverser();
  public static final TreeTraverser BREADTH_ORDER = new BreadthOrderTreeTraverser();
  public static final TreeTraverser PRE_ORDER_RECUR = new PreOrderTreeTraverserRecur();
}
