package org.blacksmith.commons.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TreeSearchSpeedTest {

  private static final int NODES_COUNT = 50;

  private static BTreeNode<Integer> root = BTreeNode.of(0);

  private static void populate(TreeNode<Integer> node, AtomicInteger counter, int maxTotalCount, int maxChildrenCount) {
    final Deque<TreeNode<Integer>> dq = new LinkedList<>();
    dq.add(node);
    while (!dq.isEmpty()) {
      var xnode = dq.pollFirst();
      if (counter.get() < maxTotalCount) {
        for (int i = 0; i < maxChildrenCount && counter.get() < maxTotalCount; i++) {
          var subnode = xnode.addChildWith(counter.incrementAndGet());
          dq.add(subnode);
        }
      }
    }
  }

  @BeforeAll
  public static void setUp() {
    System.out.println("Set up");
    populate(root, new AtomicInteger(0), NODES_COUNT, 3);
  }

  @Test
  public void testSingleFound() {
    Assertions.assertEquals(3, Optional.ofNullable(root.findDescendantWith(3)).map(TreeNode::getData).orElse(null));
  }

  @Test
  public void testSingleNotFound() {
    Assertions.assertEquals(null, Optional.ofNullable(root.findDescendantWith(NODES_COUNT+1)).map(TreeNode::getData).orElse(null));
  }

  @Test
  public void testSingleFound2() {
    Assertions.assertArrayEquals(new Integer[]{3}, root.findDescendantsWith(3));
  }

  @Test
  public void testPredicateFound() {
    Assertions.assertArrayEquals(new Integer[]{7,8,9}, root.findDescendantsListWith((d)->d>6&&d<10).stream().map(TreeNode::getData).sorted().toArray());
  }
}
