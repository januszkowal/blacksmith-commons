package org.blacksmith.commons.tree;

import java.util.concurrent.TimeUnit;
import org.blacksmith.commons.tree.TreeNode.TreeTraverser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

//@Slf4j
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(2)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class TreeSizeBenchmark {

  private static TreeNode<Integer> smallTree() {
    BTreeNode<Integer> root = new BTreeNode<>(1);
    final TreeNode<Integer> node2 = root.addChildWith(2);
    node2.addChildWith(4);
    node2.addChildWith(5);
    final TreeNode<Integer> node3 = root.addChildWith(3);
    final TreeNode<Integer> node6 = node3.addChildWith(6);
    node3.addChildWith(7);
    node6.addChildWith(8);
    node6.addChildWith(9);
    return root;
  }

  private static TreeNode<Integer> bigTree(int totalCount, int childCount) {
    BTreeNode<Integer> root = new BTreeNode<>(0);
    new TreeFactory(TreeFactory.createIntegerSupplier()).populateTotal(root, totalCount, childCount);
    return root;
  }

  @Benchmark
  public int getSize(BenchmarkData data) {
    int size = data.tree.size();
    return size;
  }

  @State(Scope.Benchmark)
  public static class BenchmarkData {

    @Param({"SMALL", "BIG3", "BIG10"})
    String size;

    TreeNode<Integer> tree;

    @Setup(Level.Trial)
    public void setUp() {
      if (size.equals("SMALL")) {
        this.tree = smallTree();
      }
      else if (size.equals("BIG3")) {
        this.tree = bigTree(50000, 3);
      }
      else if (size.equals("BIG10")) {
        this.tree = bigTree(50000, 10);
      }
      else {
        this.tree = null;
      }
    }
  }
}
