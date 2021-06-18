package org.blacksmith.commons.tree;

import java.util.List;
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
public class TreeBenchmark {

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

  private static TreeNode<Integer> bigTree() {
    BTreeNode<Integer> root = new BTreeNode<>(0);
    new TreeFactory(TreeFactory.createIntegerSupplier()).populateTotal(root, 10000, 3);
    return root;
  }

  @Benchmark
  public List<Integer> getData(BenchmarkData data) {
    return data.tree.toDataList(data.traverser);
  }

  @State(Scope.Benchmark)
  public static class BenchmarkData {

    @Param({"SMALL", "BIG"})
    String size;

    @Param({"BreadthOrderTreeTraverser",
        "PostOrderTreeTraverser",
        "PreOrderTreeTraverser",
        "PreOrderTreeTraverser2",
        "RevOrderTreeTraverser"})
    TraverserType traverserType;

    TreeNode<Integer> tree;
    TreeTraverser traverser;

    @Setup(Level.Trial)
    public void setUp() {
      if (size.equals("SMALL")) {
        this.tree = smallTree();
      } else {
        this.tree = bigTree();
      }
      this.traverser = traverserType.createTraverser();
    }
  }
}
