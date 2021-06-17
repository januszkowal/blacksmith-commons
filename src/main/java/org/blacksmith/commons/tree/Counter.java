package org.blacksmith.commons.tree;

public class Counter {
  private int value = 0;
  public int increment() {
    return ++value;
  }
  public int get() {
    return value;
  }
}
