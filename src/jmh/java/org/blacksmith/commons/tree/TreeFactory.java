package org.blacksmith.commons.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Function;

public class TreeFactory<T> {

  private final Function<Long, T> dataSupplier;
  private long counter = 0;

  public TreeFactory(Function<Long, T> dataSupplier) {
    this.dataSupplier = dataSupplier;
  }

  public static Function<Long, Long> createLongSupplier() {
    return t -> t;
  }

  public static Function<Long, Integer> createIntegerSupplier() {
    return t -> t.intValue();
  }

  public void populate(TreeNode<T> node, long maxTotalCount, int maxChildrenCount) {
    this.counter = 0;
    populateInternal(node, maxTotalCount, maxChildrenCount);
  }

  private void populateInternal(TreeNode<T> node, long maxTotalCount, int maxChildrenCount) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    dq.add(node);
    while (!dq.isEmpty()) {
      var pNode = dq.pollFirst();
      if (counter < maxTotalCount) {
        for (long i = 0; i < maxChildrenCount && counter < maxTotalCount; i++) {
          ++counter;
          var subNode = pNode.addChildWith(dataSupplier.apply(counter));
          dq.add(subNode);
        }
      }
    }
  }
}
