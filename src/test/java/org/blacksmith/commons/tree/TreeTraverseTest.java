package org.blacksmith.commons.tree;

import static org.assertj.core.api.Assertions.assertThat;

import org.blacksmith.commons.tree.traverser.BreadthOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PostOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverser2;
import org.blacksmith.commons.tree.traverser.RevOrderTreeTraverser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("TreeTraverseTest")
public class TreeTraverseTest {
  Integer[] PRE_ORDER_EXPECTED = {1, 2, 4, 5, 3, 6, 8, 9, 7};
  Integer[] POST_ORDER_EXPECTED = {4, 5, 2, 8, 9, 6, 7, 3, 1};
  Integer[] BREATH_ORDER_EXPECTED = {1, 2, 3, 4, 5, 6, 7, 8, 9};
  Integer[] REV_ORDER_EXPECTED = {1, 3, 7, 6, 9, 8, 2, 5, 4};

  @Test
  public void testPreOrderTreeTraverser() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    assertThat(tree.toDataList(new PreOrderTreeTraverser())).containsExactly(PRE_ORDER_EXPECTED);
    assertThat(tree.toDataList(new PreOrderTreeTraverser2())).containsExactly(PRE_ORDER_EXPECTED);
  }

  @Test
  public void testPostOrderTreeTraverser() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    assertThat(tree.toDataList(new PostOrderTreeTraverser())).containsExactly(POST_ORDER_EXPECTED);
  }

  @Test
  public void testBreadthOrderTreeTraverser() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    assertThat(tree.toDataList(new BreadthOrderTreeTraverser())).containsExactly(BREATH_ORDER_EXPECTED);
  }

  @Test
  public void testRevOrderTreeTraverser() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    assertThat(tree.toDataList(new RevOrderTreeTraverser())).containsExactly(REV_ORDER_EXPECTED);
  }
}
