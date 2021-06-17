package org.blacksmith.commons.tree;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.RevOrderTreeTraverser;
import org.junit.jupiter.api.Test;

class TreeOperationsTest {

  private TreeNode<Integer> treeRoot;
  private TreeNode<Integer> node2;
  private TreeNode<Integer> node3;
  private TreeNode<Integer> node4;
  private TreeNode<Integer> node6;
  private TreeNode<Integer> node5;
  private TreeNode<Integer> node7;
  private TreeNode<Integer> node8;
  private TreeNode<Integer> node9;

  public void setUp1() {
    treeRoot = StdTraverseTreeFactory.createTree();
    node2 = treeRoot.findDescendantWith(2);
    node3 = treeRoot.findDescendantWith(3);
    node4 = treeRoot.findDescendantWith(4);
    node5 = treeRoot.findDescendantWith(5);
    node6 = treeRoot.findDescendantWith(6);
    node7 = treeRoot.findDescendantWith(7);
    node8 = treeRoot.findDescendantWith(8);
    node9 = treeRoot.findDescendantWith(9);
    assertThat(node2).isNotNull();
    assertThat(node3).isNotNull();
    assertThat(node4).isNotNull();
    assertThat(node5).isNotNull();
    assertThat(node6).isNotNull();
    assertThat(node7).isNotNull();
    assertThat(node8).isNotNull();
    assertThat(node9).isNotNull();
  }

  @Test
  public void shouldClearTree() {
    setUp1();
    assertThat(treeRoot.size()).isEqualTo(9);
    assertThat(treeRoot.getChildren().size()).isEqualTo(2);
    assertThat(treeRoot.getChildren()).isNotEmpty();
    treeRoot.clear();
    assertThat(treeRoot.size()).isEqualTo(1);
    assertThat(treeRoot.getChildren().size()).isEqualTo(0);
    assertThat(treeRoot.getChildren()).isEmpty();
  }

  @Test
  public void shouldDelete() {
    setUp1();
    assertThat(treeRoot.size()).isEqualTo(9);
    var removed9 = treeRoot.removeDescendantWith(9);
    assertThat(removed9).isNotNull();
    assertThat(removed9.getData()).isEqualTo(9);
    assertThat(removed9.getParent()).isNull();
    assertThat(treeRoot.size()).isEqualTo(8);
    treeRoot.removeDescendantWith(3);
    assertThat(treeRoot.size()).isEqualTo(4);
    setUp1();
    assertThat(treeRoot.size()).isEqualTo(9);
    var n9 = treeRoot.findDescendantWith(9);
    var n9Removed = n9.getParent().removeChild(n9);
    assertThat(n9Removed.getParent()).isNull();
    assertThat(treeRoot.size()).isEqualTo(8);
    var n3 = treeRoot.findDescendantWith(3);
    n3.getParent().removeChild(n3);
    assertThat(treeRoot.size()).isEqualTo(4);
  }

  @Test
  public void nodeContainsAnother() {
    setUp1();
    assertThat(treeRoot.contains(2)).isTrue();
    assertThat(treeRoot.contains(3)).isTrue();
    assertThat(treeRoot.contains(4)).isTrue();
    assertThat(treeRoot.contains(5)).isTrue();
    assertThat(treeRoot.contains(6)).isTrue();
    assertThat(treeRoot.contains(7)).isTrue();
    assertThat(treeRoot.contains(8)).isTrue();
    assertThat(treeRoot.contains(9)).isTrue();
    assertThat(treeRoot.contains(10)).isFalse();
    assertThat(treeRoot.contains(11)).isFalse();
    assertThat(treeRoot.contains(12)).isFalse();
    assertThat(node2.contains(1)).isFalse();
    assertThat(node2.contains(5)).isTrue();
    assertThat(node5.contains(8)).isFalse();
    assertThat(node3.contains(6)).isTrue();
    assertThat(node3.contains(8)).isTrue();
  }

  @Test
  public void nodeSize() {
    setUp1();
    assertThat(treeRoot.size()).isEqualTo(9);
    assertThat(node2.size()).isEqualTo(3);
    assertThat(node3.size()).isEqualTo(5);
    assertThat(node4.size()).isEqualTo(1);
    assertThat(node5.size()).isEqualTo(1);
    assertThat(node6.size()).isEqualTo(3);
    assertThat(node7.size()).isEqualTo(1);
    assertThat(node8.size()).isEqualTo(1);
    assertThat(node9.size()).isEqualTo(1);
  }

  @Test
  public void nodeIsRoot() {
    setUp1();
    assertThat(treeRoot.isRoot()).isTrue();
    assertThat(node2.isRoot()).isFalse();
    assertThat(node3.isRoot()).isFalse();
    assertThat(node4.isRoot()).isFalse();
    assertThat(node5.isRoot()).isFalse();
    assertThat(node6.isRoot()).isFalse();
    assertThat(node7.isRoot()).isFalse();
    assertThat(node8.isRoot()).isFalse();
    assertThat(node9.isRoot()).isFalse();
  }

  @Test
  public void nodeIsParentOf() {
    setUp1();
    assertThat(treeRoot.isParentOf(node2)).isTrue();
    assertThat(treeRoot.isParentOf(node3)).isTrue();
    assertThat(treeRoot.isParentOf(node4)).isFalse();
    assertThat(treeRoot.isParentOf(node5)).isFalse();
    assertThat(node2.isParentOf(node4)).isTrue();
    assertThat(node2.isParentOf(node5)).isTrue();
    assertThat(node2.isParentOf(node3)).isFalse();
    assertThat(node2.isParentOf(node4)).isTrue();
    assertThat(node2.isParentOf(node5)).isTrue();
    assertThat(node6.isParentOf(node8)).isTrue();
    assertThat(node8.isParentOf(node6)).isFalse();
  }

  @Test
  public void nodeIsLeaf() {
    setUp1();
    assertThat(treeRoot.hasChildren()).isTrue();
    assertThat(node2.isLeaf()).isFalse();
    assertThat(node3.isLeaf()).isFalse();
    assertThat(node4.isLeaf()).isTrue();
    assertThat(node5.isLeaf()).isTrue();
    assertThat(node6.isLeaf()).isFalse();
    assertThat(node7.isLeaf()).isTrue();
    assertThat(node8.isLeaf()).isTrue();
    assertThat(node9.isLeaf()).isTrue();
  }

  @Test
  public void nodeHasChildren() {
    setUp1();
    assertThat(node2.hasChildren()).isTrue();
    assertThat(node3.hasChildren()).isTrue();
    assertThat(node4.hasChildren()).isFalse();
    assertThat(node5.hasChildren()).isFalse();
    assertThat(node6.hasChildren()).isTrue();
    assertThat(node7.hasChildren()).isFalse();
    assertThat(node8.hasChildren()).isFalse();
    assertThat(node9.hasChildren()).isFalse();
  }

  @Test
  public void nodeIsDescendantOf() {
    setUp1();
    assertThat(treeRoot.isDescendantOf(node2)).isFalse();
    assertThat(treeRoot.isDescendantOf(node3)).isFalse();
    assertThat(treeRoot.isDescendantOf(node7)).isFalse();
    assertThat(treeRoot.isDescendantOf(node8)).isFalse();
    assertThat(node2.isDescendantOf(treeRoot)).isTrue();
    assertThat(node2.isDescendantOf(node2)).isFalse();
    assertThat(node2.isDescendantOf(node3)).isFalse();
    assertThat(node2.isDescendantOf(node6)).isFalse();
    assertThat(node3.isDescendantOf(treeRoot)).isTrue();
    assertThat(node4.isDescendantOf(treeRoot)).isTrue();
    assertThat(node4.isDescendantOf(node2)).isTrue();
    assertThat(node5.isDescendantOf(treeRoot)).isTrue();
    assertThat(node6.isDescendantOf(treeRoot)).isTrue();
    assertThat(node7.isDescendantOf(treeRoot)).isTrue();
    assertThat(node8.isDescendantOf(treeRoot)).isTrue();
    assertThat(node8.isDescendantOf(node3)).isTrue();
    assertThat(node8.isDescendantOf(node6)).isTrue();
    assertThat(node8.isDescendantOf(node4)).isFalse();
    assertThat(node9.isDescendantOf(treeRoot)).isTrue();
  }

  @Test
  public void nodeIsChildOf() {
    setUp1();
    assertThat(treeRoot.isChildOf(node2)).isFalse();
    assertThat(treeRoot.isChildOf(node3)).isFalse();
    assertThat(treeRoot.isChildOf(node7)).isFalse();
    assertThat(treeRoot.isChildOf(node8)).isFalse();
    assertThat(node2.isChildOf(treeRoot)).isTrue();
    assertThat(node2.isChildOf(node2)).isFalse();
    assertThat(node2.isChildOf(node3)).isFalse();
    assertThat(node2.isChildOf(node6)).isFalse();
    assertThat(node3.isChildOf(treeRoot)).isTrue();
    assertThat(node4.isChildOf(treeRoot)).isFalse();
    assertThat(node4.isChildOf(node2)).isTrue();
    assertThat(node5.isChildOf(treeRoot)).isFalse();
    assertThat(node6.isChildOf(treeRoot)).isFalse();
    assertThat(node7.isChildOf(treeRoot)).isFalse();
    assertThat(node8.isChildOf(treeRoot)).isFalse();
    assertThat(node8.isChildOf(node3)).isFalse();
    assertThat(node8.isChildOf(node6)).isTrue();
    assertThat(node8.isChildOf(node4)).isFalse();
    assertThat(node9.isChildOf(treeRoot)).isFalse();
  }

  @Test
  public void nodeFindDescendants() {
    TreeNode<Integer> root = new BTreeNode<>(1);
    var child2 = root.addChildWith(2);
    child2.addChildWith(20);
    child2.addChildWith(20);
    var child3 = root.addChildWith(20);
    var child6 = child3.addChildWith(6);
    child3.addChildWith(7);
    child6.addChildWith(20);
    child6.addChildWith(6);
    assertThat(root.findDescendantWith(20)).isNull();
    assertThat(root.findDescendantsWith(20)).isNotEmpty();
    assertThat(root.findDescendantWith(25)).isNull();
    assertThat(root.findDescendantsWith(25)).isEmpty();
    assertThat(root.findDescendantWith(6)).isNull();
    assertThat(root.findDescendantsWith(6)).isNotEmpty();
    assertThat(root.findDescendantsWith(6).size()).isEqualTo(2);
    assertThat(root.findDescendantsWith(20).size()).isEqualTo(4);
    assertThat(root.findDescendantsWith((nd) -> nd == 20).size()).isEqualTo(4);
    assertThat(root.findDescendantsArrayWith((nd) -> nd == 20).length).isEqualTo(4);
    assertThat(root.findDescendantsWith(6).size()).isEqualTo(2);
    assertThat(root.findDescendantsWith((nd) -> nd == 6).size()).isEqualTo(2);
    assertThat(root.findDescendantsArrayWith((nd) -> nd == 6).length).isEqualTo(2);
    assertThat(root.findDescendantsWith(7).size()).isEqualTo(1);
    assertThat(root.findDescendantsWith((nd) -> nd == 7).size()).isEqualTo(1);
    assertThat(root.findDescendantsArrayWith((nd) -> nd == 7).length).isEqualTo(1);
  }

  @Test
  public void nodeSetData() {
    setUp1();
    assertThat(treeRoot.findDescendantsArrayWith(25).length).isEqualTo(0);
    var node = treeRoot.findDescendantWith(7);
    node.setData(25);
    assertThat(treeRoot.findDescendantsArrayWith(25).length).isEqualTo(1);
  }

  @Test
  public void nodeAddChild() {
    setUp1();
    var n3 = treeRoot.findDescendantWith(3);
    assertThat(n3.findDescendantsWith(33).size()).isEqualTo(0);
    assertThat(n3.findDescendantsWith(36).size()).isEqualTo(0);
    n3.addChildWith(33);
    n3.addChild(new BTreeNode<>(36));
    assertThat(n3.findDescendantsWith(33).size()).isEqualTo(1);
    assertThat(n3.findDescendantsWith(36).size()).isEqualTo(1);
  }


  @SuppressWarnings("rawtypes")
  @Test
  public void treeConversionTest() {
    TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
    final Integer[] expectedPreOrderTreeTraverser = {1, 2, 4, 5, 3, 6, 8, 9, 7};
    final Integer[] expectedRevOrderTreeTraverser = {1, 3, 7, 6, 9, 8, 2, 5, 4};
    //RevOrderTreeTraverserRecur
    assertThat(tree.toDataList(new PreOrderTreeTraverser())).containsExactly(expectedPreOrderTreeTraverser);
    assertThat(tree.toDataArray(new Integer[0], new RevOrderTreeTraverser()))
        .containsExactly(expectedRevOrderTreeTraverser);
    assertThat(tree.toDataArray(new Integer[0])).containsExactly(expectedPreOrderTreeTraverser);
    assertThat(tree.toDataList()).containsExactly(expectedPreOrderTreeTraverser);
    assertThat(tree.toList()).extracting(TreeNode::getData).containsExactly(expectedPreOrderTreeTraverser);
    //PreOrderTreeTraverserRecur
    assertThat(tree.toDataList(new RevOrderTreeTraverser())).containsExactly(expectedRevOrderTreeTraverser);
    assertThat(List.of(tree.toArray()).stream()
        .map(node -> (TreeNode<Integer>) node)
        .map(TreeNode::getData)
        .collect(Collectors.toList()))
        .containsExactly(expectedPreOrderTreeTraverser);
  }
}
