package org.blacksmith.commons.tree;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.assertj.core.util.Arrays;
import org.blacksmith.commons.tree.traverser.RevOrderTreeTraverserRecur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreeNodeTest {

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
    assertNotNull(node2);
    assertNotNull(node3);
    assertNotNull(node4);
    assertNotNull(node5);
    assertNotNull(node6);
    assertNotNull(node7);
    assertNotNull(node8);
    assertNotNull(node9);
  }

  @Test
  public void shouldClearTree() {
    setUp1();
    assertEquals(9,treeRoot.size());
    assertEquals(2,treeRoot.getChildren().size());
    assertFalse(treeRoot.getChildren().isEmpty());
    treeRoot.clear();
    assertEquals(1,treeRoot.size());
    assertEquals(0,treeRoot.getChildren().size());
    assertTrue(treeRoot.getChildren().isEmpty());
  }
  @Test
  public void shouldDelete() {
    setUp1();
    assertEquals(9,treeRoot.size());
    var removed9 = treeRoot.removeDescendantWith(9);
    assertNotNull(removed9);
    assertEquals(9,removed9.getData());
    assertNull(removed9.getParent());
    assertEquals(8,treeRoot.size());
    treeRoot.removeDescendantWith(3);
    assertEquals(4,treeRoot.size());
    setUp1();
    assertEquals(9,treeRoot.size());
    var n9 = treeRoot.findDescendantWith(9);
    var n9Removed = n9.getParent().removeChild(n9);
    assertNull(n9Removed.getParent());
    assertEquals(8,treeRoot.size());
    var n3 = treeRoot.findDescendantWith(3);
    n3.getParent().removeChild(n3);
    assertEquals(4,treeRoot.size());
  }

  @Test
  public void nodeContainsAnother() {
    setUp1();
    assertTrue(treeRoot.contains(2));
    assertTrue(treeRoot.contains(3));
    assertTrue(treeRoot.contains(4));
    assertTrue(treeRoot.contains(5));
    assertTrue(treeRoot.contains(6));
    assertTrue(treeRoot.contains(7));
    assertTrue(treeRoot.contains(8));
    assertTrue(treeRoot.contains(9));
    assertFalse(treeRoot.contains(10));
    assertFalse(treeRoot.contains(11));
    assertFalse(treeRoot.contains(12));
    assertFalse(node2.contains(1));
    assertTrue(node2.contains(5));
    assertFalse(node5.contains(8));
    assertTrue(node3.contains(6));
    assertTrue(node3.contains(8));
  }

  @Test
  public void nodeSize() {
    setUp1();
    assertEquals(9,treeRoot.size());
    assertEquals(3, node2.size());
    assertEquals(5, node3.size());
    assertEquals(1, node4.size());
    assertEquals(1, node5.size());
    assertEquals(3, node6.size());
    assertEquals(1, node7.size());
    assertEquals(1, node8.size());
    assertEquals(1, node9.size());
  }

  @Test
  public void nodeIsRoot() {
    setUp1();
    assertTrue(treeRoot.isRoot());
    assertFalse(node2.isRoot());
    assertFalse(node3.isRoot());
    assertFalse(node4.isRoot());
    assertFalse(node5.isRoot());
    assertFalse(node6.isRoot());
    assertFalse(node7.isRoot());
    assertFalse(node8.isRoot());
    assertFalse(node9.isRoot());
  }

  @Test
  public void nodeIsParentOf() {
    setUp1();
    assertTrue(treeRoot.isParentOf(node2));
    assertTrue(treeRoot.isParentOf(node3));
    assertFalse(treeRoot.isParentOf(node4));
    assertFalse(treeRoot.isParentOf(node5));
    assertTrue(node2.isParentOf(node4));
    assertTrue(node2.isParentOf(node5));
    assertFalse(node2.isParentOf(node3));
    assertTrue(node2.isParentOf(node4));
    assertTrue(node2.isParentOf(node5));
    assertTrue(node6.isParentOf(node8));
    assertFalse(node8.isParentOf(node6));
  }

  @Test
  public void nodeIsLeaf() {
    setUp1();
    assertTrue(treeRoot.hasChildren());
    assertFalse(node2.isLeaf());
    assertFalse(node3.isLeaf());
    assertTrue(node4.isLeaf());
    assertTrue(node5.isLeaf());
    assertFalse(node6.isLeaf());
    assertTrue(node7.isLeaf());
    assertTrue(node8.isLeaf());
    assertTrue(node9.isLeaf());
  }

  @Test
  public void nodeHasChildren() {
    setUp1();
    assertTrue(node2.hasChildren());
    assertTrue(node3.hasChildren());
    assertFalse(node4.hasChildren());
    assertFalse(node5.hasChildren());
    assertTrue(node6.hasChildren());
    assertFalse(node7.hasChildren());
    assertFalse(node8.hasChildren());
    assertFalse(node9.hasChildren());
  }

  @Test
  public void nodeIsDescendantOf() {
    setUp1();
    assertFalse(treeRoot.isDescendantOf(node2));
    assertFalse(treeRoot.isDescendantOf(node3));
    assertFalse(treeRoot.isDescendantOf(node7));
    assertFalse(treeRoot.isDescendantOf(node8));
    assertTrue(node2.isDescendantOf(treeRoot));
    assertFalse(node2.isDescendantOf(node2));
    assertFalse(node2.isDescendantOf(node3));
    assertFalse(node2.isDescendantOf(node6));
    assertTrue(node3.isDescendantOf(treeRoot));
    assertTrue(node4.isDescendantOf(treeRoot));
    assertTrue(node4.isDescendantOf(node2));
    assertTrue(node5.isDescendantOf(treeRoot));
    assertTrue(node6.isDescendantOf(treeRoot));
    assertTrue(node7.isDescendantOf(treeRoot));
    assertTrue(node8.isDescendantOf(treeRoot));
    assertTrue(node8.isDescendantOf(node3));
    assertTrue(node8.isDescendantOf(node6));
    assertFalse(node8.isDescendantOf(node4));
    assertTrue(node9.isDescendantOf(treeRoot));
  }

  @Test
  public void nodeIsChildOf() {
    setUp1();
    assertFalse(treeRoot.isChildOf(node2));
    assertFalse(treeRoot.isChildOf(node3));
    assertFalse(treeRoot.isChildOf(node7));
    assertFalse(treeRoot.isChildOf(node8));
    assertTrue(node2.isChildOf(treeRoot));
    assertFalse(node2.isChildOf(node2));
    assertFalse(node2.isChildOf(node3));
    assertFalse(node2.isChildOf(node6));
    assertTrue(node3.isChildOf(treeRoot));
    assertFalse(node4.isChildOf(treeRoot));
    assertTrue(node4.isChildOf(node2));
    assertFalse(node5.isChildOf(treeRoot));
    assertFalse(node6.isChildOf(treeRoot));
    assertFalse(node7.isChildOf(treeRoot));
    assertFalse(node8.isChildOf(treeRoot));
    assertFalse(node8.isChildOf(node3));
    assertTrue(node8.isChildOf(node6));
    assertFalse(node8.isChildOf(node4));
    assertFalse(node9.isChildOf(treeRoot));
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
    assertNotNull(root.findDescendantWith(20));
    assertNull(root.findDescendantWith(25));
    assertNotNull(root.findDescendantWith(6));
    assertEquals(4,root.findDescendantsWith(20).size());
    assertEquals(4,root.findDescendantsWith((nd)->nd==20).size());
    assertEquals(4,root.findDescendantsArrayWith((nd)->nd==20).length);
    assertEquals(2,root.findDescendantsWith(6).size());
    assertEquals(2,root.findDescendantsWith((nd)->nd==6).size());
    assertEquals(2,root.findDescendantsArrayWith((nd)->nd==6).length);
    assertEquals(1,root.findDescendantsWith(7).size());
    assertEquals(1,root.findDescendantsWith((nd)->nd==7).size());
    assertEquals(1,root.findDescendantsArrayWith((nd)->nd==7).length);
  }

  @Test
  public void nodeSetData() {
    setUp1();
    assertEquals(0,treeRoot.findDescendantsArrayWith(25).length);
    var node = treeRoot.findDescendantWith(7);
    node.setData(25);
    assertEquals(1,treeRoot.findDescendantsArrayWith(25).length);
  }

  @Test
  public void nodeAddChild() {
    setUp1();
    var n3 = treeRoot.findDescendantWith(3);
    assertEquals(0,n3.findDescendantsWith(33).size());
    assertEquals(0,n3.findDescendantsWith(36).size());
    n3.addChildWith(33);
    n3.addChild(new BTreeNode<>(36));
    assertEquals(1,n3.findDescendantsWith(33).size());
    assertEquals(1,n3.findDescendantsWith(36).size());
  }



  @SuppressWarnings("rawtypes")
  @Test
  public void treeConversionTest() {
      TreeNode<Integer> tree = StdTraverseTreeFactory.createTree();
      final Integer[] expected = Arrays.array(1, 3, 7, 6, 9, 8, 2, 5, 4);
      Assertions.assertArrayEquals(expected,tree.toDataArray(new Integer[0], new RevOrderTreeTraverserRecur()));
      Assertions.assertIterableEquals(List.of(expected),tree.toDataList(new RevOrderTreeTraverserRecur()));
      Assertions.assertArrayEquals(expected,tree.toDataArray(new Integer[0]));
      Assertions.assertIterableEquals(List.of(expected),tree.toDataList());
      assertThat(tree.toList()).extracting(TreeNode::getData).containsExactly(expected);
      var nodeArray = tree.toArray();
      Assertions.assertEquals(expected.length,nodeArray.length);
      for (int i=0; i<expected.length; i++) {
        assertEquals(expected[i],((TreeNode)nodeArray[i]).getData());
      }
  }
}