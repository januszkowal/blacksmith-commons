package org.blacksmith.commons.tree;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class TreeTraverseTest {
  public TreeNode<Long> createTree() {
    TreeNode<Long> root = new BTreeNode<Long>(1L);
    final TreeNode<Long> node2 = root.addChildWith(2L);
    node2.addChildWith(4L);
    node2.addChildWith(5L);

    final TreeNode<Long> node3 = root.addChildWith(3L);
    final TreeNode<Long> node6 = node3.addChildWith(6L);
    node3.addChildWith(7L);
    node6.addChildWith(8l);
    node6.addChildWith(9L);
    return root;
  }

  public String arrToString(Long[] array) {
    return String.join(",",Arrays.stream(array).map(l -> l.toString()).collect(Collectors.toList()));
  }

  @Test
  public void testTraversePRE() {
    TreeNode<Long> tree = createTree();
    final Long[] xtree = tree.toDataArray(new Long[0], StdBTreeTraverser.PRE_ORDER);
    System.out.println("sort PRE:"+arrToString(xtree));
  }
  @Test
  public void testTraversePOST() {
    TreeNode<Long> tree = createTree();
    final Long[] xtree = tree.toDataArray(new Long[0], StdBTreeTraverser.POST_ORDER);
    System.out.println("sort POST:"+arrToString(xtree));
  }
  @Test
  public void testTraverseBREATH_FIRST() {
    TreeNode<Long> tree = createTree();
    final Long[] xtree = tree.toDataArray(new Long[0], StdBTreeTraverser.BREADTH_ORDER);
    System.out.println("sort BREATH_FIRST:"+arrToString(xtree));
  }
}
