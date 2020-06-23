package org.blacksmith.commons.tree;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.blacksmith.commons.tree.traverser.BreadthOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PostOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverserRecur;
import org.blacksmith.commons.tree.traverser.RevOrderTreeTraverserRecur;
import org.blacksmith.test.TimingExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisplayName("TreeTraverseTest")
@ExtendWith(TimingExtension.class)
public class TreeTraverseTest {
  private final static Logger LOGGER = LoggerFactory.getLogger(TreeTraverseTest.class);
  public BTreeNode<Integer> createTree() {
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

  public String arrToString(Integer[] array) {
    return Stream.of(array).map(Object::toString).collect(Collectors.joining(","));
  }

  @Test
  public void testPreOrderTreeTraverser() {
    TreeNode<Integer> tree = createTree();
    final Integer[] arr = tree.toDataArray(new Integer[0], new PreOrderTreeTraverser());
    Assertions.assertArrayEquals(new Integer[]{1,2,4,5,3,6,8,9,7},arr);
  }
  @Test
  public void testPostOrderTreeTraverser() {
    TreeNode<Integer> tree = createTree();
    final Integer[] arr = tree.toDataArray(new Integer[0], new PostOrderTreeTraverser());
    Assertions.assertArrayEquals(new Integer[]{4, 5, 2, 8, 9, 6, 7, 3, 1},arr);
  }
  @Test
  public void testBreadthOrderTreeTraverser() {
    TreeNode<Integer> tree = createTree();
    final Integer[] arr = tree.toDataArray(new Integer[0], new BreadthOrderTreeTraverser());
    Assertions.assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9},arr);
  }
  @Test
  public void testPreOrderTreeTraverserStack() {
    TreeNode<Integer> tree = createTree();
    final Integer[] arr = tree.toDataArray(new Integer[0], new PreOrderTreeTraverserRecur());
    Assertions.assertArrayEquals(new Integer[]{1,2,4,5,3,6,8,9,7},arr);

  }
  @Test
  public void testRevOrderTreeTraverserStack2() {
    TreeNode<Integer> tree = createTree();
    final Integer[] arr = tree.toDataArray(new Integer[0], new RevOrderTreeTraverserRecur());
    Assertions.assertArrayEquals(new Integer[]{1,3,7,6,9,8,2,5,4},arr);
  }
  @Test
  public void testSize() {
    TreeNode<Integer> tree = createTree();
    Assertions.assertEquals(9,tree.size());
  }
}
