package org.blacksmith.commons.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class TreeFactory<T> {

  private final Function<Long,T> dataSupplier;

  public TreeFactory(Function<Long,T> dataSupplier) {
    this.dataSupplier = dataSupplier;
  }

  public void populate(TreeNode<T> node, long maxTotalCount, int maxChildrenCount) {
    AtomicLong counter = new AtomicLong();
    populateInternal(node, new AtomicLong(0L), maxTotalCount, maxChildrenCount);
  }

  private void populateInternal(TreeNode<T> node, AtomicLong counter, long maxTotalCount, int maxChildrenCount) {
    final Deque<TreeNode<T>> dq = new LinkedList<>();
    dq.add(node);
    while (!dq.isEmpty()) {
      var pNode = dq.pollFirst();
      if (counter.get() < maxTotalCount) {
        for (int i = 0; i < maxChildrenCount && counter.get() < maxTotalCount; i++) {
          var subNode = pNode.addChildWith(dataSupplier.apply(counter.incrementAndGet()));
          dq.add(subNode);
        }
      }
    }
  }
}
