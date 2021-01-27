package org.blacksmith.commons.struct;

public class Wrapper<E> {

  private final boolean isChanged;
  private final E value;

  public Wrapper(E value, boolean isChanged) {
    this.value = value;
    this.isChanged = isChanged;
  }

  public E getValue() {
    return this.value;
  }

  public boolean isChanged() {
    return this.isChanged;
  }
}
