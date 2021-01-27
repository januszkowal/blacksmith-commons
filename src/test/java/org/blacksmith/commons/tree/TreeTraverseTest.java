package org.blacksmith.commons.tree;

import java.util.List;
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

@DisplayName("TreeTraverseTest")
@ExtendWith(TimingExtension.class)
public class TreeTraverseTest {

  @Test
  public void testPreOrderTreeTraverser() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    var expected = List.of(1, 2, 4, 5, 3, 6, 8, 9, 7);
    Assertions.assertIterableEquals(expected, tree.toDataList(new PreOrderTreeTraverser()));
    Assertions.assertEquals(expected.size(), tree.size(new PreOrderTreeTraverser()));
  }

  @Test
  public void testPostOrderTreeTraverser() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    var expected = List.of(4, 5, 2, 8, 9, 6, 7, 3, 1);
    Assertions.assertIterableEquals(expected, tree.toDataList(new PostOrderTreeTraverser()));
    Assertions.assertEquals(expected.size(), tree.size(new PostOrderTreeTraverser()));
  }

  @Test
  public void testBreadthOrderTreeTraverser() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    var expected = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    Assertions.assertIterableEquals(expected, tree.toDataList(new BreadthOrderTreeTraverser()));
    Assertions.assertEquals(expected.size(), tree.size(new BreadthOrderTreeTraverser()));
  }

  @Test
  public void testPreOrderTreeTraverserStack() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    var expected = List.of(1, 2, 4, 5, 3, 6, 8, 9, 7);
    Assertions.assertIterableEquals(expected, tree.toDataList(new PreOrderTreeTraverserRecur()));
    Assertions.assertEquals(expected.size(), tree.size(new PreOrderTreeTraverserRecur()));

  }

  @Test
  public void testRevOrderTreeTraverserStack2() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    var expected = List.of(1, 3, 7, 6, 9, 8, 2, 5, 4);
    Assertions.assertIterableEquals(expected, tree.toDataList(new RevOrderTreeTraverserRecur()));
    Assertions.assertEquals(expected.size(), tree.size(new RevOrderTreeTraverserRecur()));
  }

  @Test
  public void testSize() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    Assertions.assertEquals(9, tree.size());
  }
}
