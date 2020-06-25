package org.blacksmith.commons.tree;

import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TreeSearchSpeedTest {

  private static final int NODES_COUNT = 111;

  private static final BTreeNode<Long> root = BTreeNode.of(0L);

  @BeforeAll
  public static void setUp() {
    System.out.println("Set up");
    new TreeFactory<>(TreeFactory.createLongSupplier()).populate(root,NODES_COUNT, 3);
    System.out.println("Initialized with size: "+root.size());
  }

  @Test
  public void testSingleFound() {
    Assertions.assertEquals(3, Optional.ofNullable(root.findDescendantWith(3L)).map(TreeNode::getData).orElse(null));
  }

  @Test
  public void testSingleNotFound() {
    Assertions.assertNull(Optional.ofNullable(root.findDescendantWith(NODES_COUNT + 1L)).map(TreeNode::getData).orElse(null));
  }

  @Test
  public void testSingleFound2() {
    Assertions.assertArrayEquals(new Long[]{3L},
        Arrays.stream(root.findDescendantsWith(3L)).map(TreeNode::getData).toArray());
  }

  @Test
  public void testPredicateFound() {
    Assertions.assertArrayEquals(new Long[]{7L,8L,9L}, root.findDescendantsListWith((d)->d>6&&d<10).stream().map(TreeNode::getData).sorted().toArray());
  }
}
