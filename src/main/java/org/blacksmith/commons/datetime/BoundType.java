package org.blacksmith.commons.datetime;

public enum BoundType {
  OPEN(false),
  CLOSED(true);

  final boolean inclusive;

  BoundType(boolean inclusive) {
    this.inclusive = inclusive;
  }

  /** Returns the bound type corresponding to a boolean value for inclusively. */
  static BoundType ofBoolean(boolean inclusive) {
    return inclusive ? CLOSED : OPEN;
  }

  BoundType flip() {
    return ofBoolean(!inclusive);
  }
}
