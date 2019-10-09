package org.blacksmith.commons.tree;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.blacksmith.commons.test.TimingExtension;
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
  public BTreeNode<Long> createTree() {
    BTreeNode<Long> root = new BTreeNode<Long>(1L);
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
    final Long[] xtree = tree.toDataArray(new Long[0], StdTreeTraverser.PRE_ORDER);
    LOGGER.info("sort PRE:{}",arrToString(xtree));
  }
  @Test
  public void testTraversePOST() {
    TreeNode<Long> tree = createTree();
    final Long[] xtree = tree.toDataArray(new Long[0], StdTreeTraverser.POST_ORDER);
    LOGGER.info("sort POST:{}",arrToString(xtree));
  }
  @Test
  public void testTraverseBREATH_FIRST() {
    TreeNode<Long> tree = createTree();
    final Long[] xtree = tree.toDataArray(new Long[0], StdTreeTraverser.BREADTH_ORDER);
    LOGGER.info("sort BREATH_FIRST:{}",arrToString(xtree));
  }
  @Test
  public void testTraverseFAST() {
    TreeNode<Long> tree = createTree();
    final Long[] xtree = tree.toDataArray(new Long[0], StdTreeTraverser.PRE2_ORDER);
    LOGGER.info("sort FAST:{}",arrToString(xtree));   
  }
  @Test
  public void testSize() {
    TreeNode<Long> tree = createTree();
    Assertions.assertEquals(9,tree.size());
  }
}
