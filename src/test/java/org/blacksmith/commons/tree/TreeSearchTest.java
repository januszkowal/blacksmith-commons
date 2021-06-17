package org.blacksmith.commons.tree;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TreeSearchTest {

  private static final int NODES_COUNT = 111;

  private static final BTreeNode<Long> root = BTreeNode.of(0L);

  @BeforeAll
  public static void setUp() {
    new TreeFactory<>(TreeFactory.createLongSupplier()).populate(root, NODES_COUNT, 3);
  }

  @Test
  public void testSingleFound() {
    assertThat(Optional.ofNullable(root.findDescendantWith(3L)).map(TreeNode::getData).orElse(null)).isEqualTo(3);
  }

  @Test
  public void testSingleNotFound() {
    assertThat(Optional.ofNullable(root.findDescendantWith(NODES_COUNT + 1L)).map(TreeNode::getData).orElse(null)).isNull();
  }

  @Test
  public void testSingleFound2() {
    Long[] expected = {3L};
    assertThat(Arrays.stream(root.findDescendantsArrayWith(3L)).map(TreeNode::getData).toArray()).containsExactly(expected);
  }

  @Test
  public void testPredicateFound() {
    Long[] expected = {7L, 8L, 9L};
    assertThat(root.findDescendantsWith((d) -> d > 6 && d < 10).stream().map(TreeNode::getData).sorted().toArray()).containsExactly(expected);
  }
}
