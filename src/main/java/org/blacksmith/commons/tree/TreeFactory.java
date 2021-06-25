package org.blacksmith.commons.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class TreeFactory<T> {

  @FunctionalInterface
  public interface PopulatorFunction<T> {
    void populate(TreeNode<T> node, int maxTotalCount, int maxChildrenCount);

  }

  private final Function<Integer, T> dataSupplier;

  public TreeFactory(Function<Integer, T> dataSupplier) {
    this.dataSupplier = dataSupplier;
  }

  public static Function<Integer, Long> createLongSupplier() {
    return t -> t.longValue();
  }

  public static Function<Integer, Integer> createIntegerSupplier() {
    return t -> t;
  }

  public void populateTotal(TreeNode<T> node, int maxTotalCount, int maxChildrenCount) {
    final Deque<TreeNode<T>> queue = new LinkedList<>();
    queue.add(node);
    int counter = 1;
    TreeNode<T> n;
    while ((n = queue.pollFirst()) != null) {
      int toPopulate = Math.min(maxChildrenCount, maxTotalCount - counter);
      for (int i = 0; i < toPopulate; i++) {
        ++counter;
        queue.add(node.addChildWith(dataSupplier.apply(counter)));
      }
    }
  }

  public void populateRegular(TreeNode<T> node, int maxTotalCount, int maxChildrenCount) {
    final Deque<TreeNode<T>> queue = new LinkedList<>();
    queue.add(node);
    int counter = 1;
    TreeNode<T> n;
    while ((n = queue.pollFirst()) != null) {
      if (counter < maxTotalCount) {
        for (int i = 0; i < maxChildrenCount; i++) {
          ++counter;
          queue.add(n.addChildWith(dataSupplier.apply(counter)));
        }
      }
    }
  }
}
