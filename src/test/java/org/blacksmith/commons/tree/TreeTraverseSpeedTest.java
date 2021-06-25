package org.blacksmith.commons.tree;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.blacksmith.commons.tree.traverser.BreadthOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PostOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverser2;
import org.blacksmith.commons.tree.traverser.RevOrderTreeTraverser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

public class TreeTraverseSpeedTest {

  private static final int TEST_REPEAT = 5;
  private static final int NODES_COUNT = 5_0000;

  private static final BTreeNode<Integer> root = BTreeNode.of(0);

  @BeforeAll
  public static void setUp() {
    new TreeFactory<>(Integer::intValue).populateTotal(root, NODES_COUNT, 3);
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
    root.toList(new PreOrderTreeTraverser2());
  }

  @RepeatedTest(TEST_REPEAT)
  public void testToListRevOrderTreeTraverserRecur() {
    root.toList(new RevOrderTreeTraverser());
  }

  @RepeatedTest(TEST_REPEAT)
  public void testFindFirst1() {
    assertThat(Optional.ofNullable(root.findDescendantWith(5)).map(TreeNode::getData).orElse(null)).isEqualTo(5);
  }

  @RepeatedTest(TEST_REPEAT)
  public void testFindFirst2() {
    assertThat(Optional.ofNullable(root.findDescendantWith(NODES_COUNT + 1)).map(TreeNode::getData).orElse(null)).isNull();
  }
}
