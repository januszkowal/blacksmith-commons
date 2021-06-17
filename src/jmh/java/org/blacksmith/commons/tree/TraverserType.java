package org.blacksmith.commons.tree;

import java.util.function.Supplier;
import org.blacksmith.commons.tree.TreeNode.TreeTraverser;
import org.blacksmith.commons.tree.traverser.BreadthOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PostOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverser;
import org.blacksmith.commons.tree.traverser.PreOrderTreeTraverser2;
import org.blacksmith.commons.tree.traverser.RevOrderTreeTraverser;

public enum TraverserType {
  BreadthOrderTreeTraverser(() -> new BreadthOrderTreeTraverser()),
  PostOrderTreeTraverser(() -> new PostOrderTreeTraverser()),
  PreOrderTreeTraverser(() -> new PreOrderTreeTraverser()),
  PreOrderTreeTraverser2(() -> new PreOrderTreeTraverser2()),
  RevOrderTreeTraverser(() -> new RevOrderTreeTraverser());

  private final Supplier<TreeTraverser> traverserSupplier;

  TraverserType(Supplier<TreeTraverser> traverserSupplier) {
    this.traverserSupplier = traverserSupplier;
  }

  public TreeTraverser createTraverser() {
    return traverserSupplier.get();
  }
}
