package org.blacksmith.commons.tree;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.blacksmith.commons.tree.traverser.BreadthOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PostOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverserRecur;
import org.blacksmith.commons.tree.traverser.RevOrderTreeTraverserRecur;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("TreeTraverseTest")
public class TreeTraverseTest {

  @Test
  public void testPreOrderTreeTraverser() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    Integer[] expected = {1, 2, 4, 5, 3, 6, 8, 9, 7};
    assertThat(tree.toDataList(new PreOrderTreeTraverser())).containsExactly(expected);
  }

  @Test
  public void testPostOrderTreeTraverser() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    Integer[] expected = {4, 5, 2, 8, 9, 6, 7, 3, 1};
    assertThat(tree.toDataList(new PostOrderTreeTraverser())).containsExactly(expected);
  }

  @Test
  public void testBreadthOrderTreeTraverser() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    assertThat(tree.toDataList(new BreadthOrderTreeTraverser())).containsExactly(expected);
  }

  @Test
  public void testPreOrderTreeTraverserStack() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    Integer[] expected = {1, 2, 4, 5, 3, 6, 8, 9, 7};
    assertThat(tree.toDataList(new PreOrderTreeTraverserRecur())).containsExactly(expected);

  }

  @Test
  public void testRevOrderTreeTraverserStack2() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    Integer[] expected = {1, 3, 7, 6, 9, 8, 2, 5, 4};
    assertThat(tree.toDataList(new RevOrderTreeTraverserRecur())).containsExactly(expected);
  }
}
