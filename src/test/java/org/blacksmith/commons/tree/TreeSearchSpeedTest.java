package org.blacksmith.commons.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TreeSearchSpeedTest {

  private static final int NODES_COUNT = 50;

  private static final BTreeNode<Integer> root = BTreeNode.of(0);

  @BeforeAll
  public static void setUp() {
    System.out.println("Set up");
    new TreeFactory<>(Long::intValue).populate(root,NODES_COUNT, 3);
  }

  @Test
  public void testSingleFound() {
    Assertions.assertEquals(3, Optional.ofNullable(root.findDescendantWith(3)).map(TreeNode::getData).orElse(null));
  }

  @Test
  public void testSingleNotFound() {
    Assertions.assertNull(Optional.ofNullable(root.findDescendantWith(NODES_COUNT + 1)).map(TreeNode::getData).orElse(null));
  }

  @Test
  public void testSingleFound2() {
    Assertions.assertArrayEquals(new Integer[]{3}, Arrays.stream(root.findDescendantsWith(3)).map(TreeNode::getData).collect(
        Collectors.toList()).toArray());
  }

  @Test
  public void testPredicateFound() {
    Assertions.assertArrayEquals(new Integer[]{7,8,9}, root.findDescendantsListWith((d)->d>6&&d<10).stream().map(TreeNode::getData).sorted().toArray());
  }
}
