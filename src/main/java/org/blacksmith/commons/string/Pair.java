package org.blacksmith.commons.string;

public class Pair<L,R> {
  private final L left;
  private final R right;

  public Pair(L left, R right) {
    this.left = left;
    this.right = right;
  }

  public static <T> Pair<T,T> of(T left, T right) {
    return new Pair<>(left, right);
  }

  public L getLeft() { return left; }

  public R getRight() {
    return right;
  }
}
