package org.blacksmith.commons.arg;

import java.util.function.Supplier;

public class Validate {
  /**
   * Checks that an object is not null.
   *
   * @param o Object to be checked.
   * @throws IllegalArgumentException if {@code o} is {@code null}.
   */
  public static void checkNotNull(Object o)
      throws IllegalArgumentException {
    if (o == null) {
      throw new IllegalArgumentException();
    }
  }
  public static void checkNotNull(Object o, String message)
      throws IllegalArgumentException {
    if (o == null) {
      throw new IllegalArgumentException(message);
    }
  }
  public static void checkNotNull(Object o, Supplier<String> messageSupplier)
      throws IllegalArgumentException {
    if (o == null) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }

  public static <T extends Comparable> void inOrder(T val1, T val2, String message) {
    if (val1.compareTo(val2)>0) {
      throw new IllegalArgumentException(message);
    }
  }

  public static <T extends Comparable> void inOrder(T val1, T val2, Supplier<String> messageSupplier) {
    if (val1.compareTo(val2)>=0) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }

  public static <T extends Comparable> void inOrderOrEqual(T val1, T val2, String message) {
    if (val1.compareTo(val2)>=0) {
      throw new IllegalArgumentException(message);
    }
  }

  public static <T extends Comparable> void inOrderOrEqual(T val1, T val2, Supplier<String> messageSupplier) {
    if (val1.compareTo(val2)>0) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }
}
