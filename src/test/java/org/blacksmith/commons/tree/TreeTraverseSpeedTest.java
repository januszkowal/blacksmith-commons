package org.blacksmith.commons.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.blacksmith.commons.tree.traverser.BreadthOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PostOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverserRecur;
import org.blacksmith.commons.tree.traverser.RevOrderTreeTraverserRecur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

public class TreeTraverseSpeedTest {

  private static final int TEST_REPEAT = 5;
  private static final int NODES_COUNT = 5_0000_000;

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

  @RepeatedTest(TEST_REPEAT)
  public void testToList() {
    root.toList();
  }

  @RepeatedTest(TEST_REPEAT)
  public void testToListBreadthOrderTreeTraverser() {
    root.toList(new BreadthOrderTreeTraverser());
  }

  @RepeatedTest(TEST_REPEAT)
  public void testToListPostOrderTreeTraverser() {
    root.toList(new PostOrderTreeTraverser());
  }

  @RepeatedTest(TEST_REPEAT)
  public void testToListPreOrderTreeTraverser() {
    root.toList(new PreOrderTreeTraverser());
  }

  @RepeatedTest(TEST_REPEAT)
  public void testToListPreOrderTreeTraverserRecur() {
    root.toList(new PreOrderTreeTraverserRecur());
  }

  @RepeatedTest(TEST_REPEAT)
  public void testToListRevOrderTreeTraverserRecur() {
    root.toList(new RevOrderTreeTraverserRecur());
  }

  @RepeatedTest(TEST_REPEAT)
  public void testFindFirst1() {
    Assertions.assertEquals(5, Optional.ofNullable(root.findDescendantWith(5)).map(TreeNode::getData).orElse(null));
  }

  @RepeatedTest(TEST_REPEAT)
  public void testFindFirst2() {
    Assertions.assertEquals(null, Optional.ofNullable(root.findDescendantWith(NODES_COUNT+1)).map(TreeNode::getData).orElse(null));
  }
}
