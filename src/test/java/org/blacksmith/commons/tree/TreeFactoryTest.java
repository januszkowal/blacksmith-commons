package org.blacksmith.commons.tree;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreeFactoryTest {
  @Test
  public void standardTree() {
    var tree = StdTraverseTreeFactory.createTree();
    assertThat(tree.size()).isEqualTo(9);
  }

  @Test
  public void treeFactoryTotal() {
    BTreeNode<Integer> root1 = new BTreeNode<>(0);
    new TreeFactory<>(Integer::intValue).populateTotal(root1, 1, 3);
    assertThat(root1.size()).isEqualTo(1);
    BTreeNode<Integer> root2 = new BTreeNode<>(0);
    new TreeFactory<>(Integer::intValue).populateTotal(root2, 2, 3);
    assertThat(root2.size()).isEqualTo(2);
    BTreeNode<Integer> root3 = new BTreeNode<>(0);
    new TreeFactory<>(Integer::intValue).populateTotal(root3, 3, 3);
    assertThat(root3.size()).isEqualTo(3);
    BTreeNode<Integer> root4 = new BTreeNode<>(0);
    new TreeFactory<>(Integer::intValue).populateTotal(root4, 100, 3);
    assertThat(root4.size()).isEqualTo(100);
  }

  @Test
  public void treeFactoryRegular() {
    BTreeNode<Integer> root1 = new BTreeNode<>(0);
    new TreeFactory<>(Integer::intValue).populateRegular(root1, 1, 3);
    assertThat(root1.size()).isEqualTo(1);
    BTreeNode<Integer> root2 = new BTreeNode<>(0);
    new TreeFactory<>(Integer::intValue).populateRegular(root2, 2, 3);
    assertThat(root2.size()).isEqualTo(4);
    BTreeNode<Integer> root3 = new BTreeNode<>(0);
    new TreeFactory<>(Integer::intValue).populateRegular(root3, 3, 3);
    assertThat(root3.size()).isEqualTo(4);
    BTreeNode<Integer> root4 = new BTreeNode<>(0);
    new TreeFactory<>(Integer::intValue).populateRegular(root4, 4, 3);
    assertThat(root3.size()).isEqualTo(4);
    BTreeNode<Integer> root5 = new BTreeNode<>(0);
    new TreeFactory<>(Integer::intValue).populateRegular(root5, 100, 3);
    assertThat(root5.size()).isEqualTo(100);
    BTreeNode<Integer> root6 = new BTreeNode<>(0);
    new TreeFactory<>(Integer::intValue).populateRegular(root6, 101, 3);
    assertThat(root6.size()).isEqualTo(103);
  }
}
