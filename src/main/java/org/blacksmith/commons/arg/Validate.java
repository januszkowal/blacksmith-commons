package org.blacksmith.commons.arg;

import java.util.Collection;
import java.util.function.Supplier;

public class Validate {
  private Validate(){}
  /**
   * Checks that an object is not null.
   *
   * @param o Object to be checked.
   * @throws IllegalArgumentException if {@code o} is {@code null}.
   */
  public static void notNull(Object o) {
    if (o == null) {
      throw new IllegalArgumentException("Argument can't be null");
    }
  }
  public static void notNull(Object o, String message) {
    if (o == null) {
      throw new IllegalArgumentException(message);
    }
  }
  public static void notNull(Object o, Supplier<String> messageSupplier) {
    if (o == null) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }

  public static void notEmpty(Object[] a) {
    if (a==null || a.length == 0) {
      throw new IllegalArgumentException("Array can't be empty or null");
    }
  }
  public static void notEmpty(Collection<?> c) {
    if (c==null || c.isEmpty()) {
      throw new IllegalArgumentException("Collection can't be empty or null");
    }
  }

  public static void notEmpty(Object[] a, String message) {
    if (a==null || a.length == 0) {
      throw new IllegalArgumentException(message);
    }
  }
  public static void notEmpty(Collection<?> c, String message) {
    if (c == null || c.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void notEmpty(Object[] a, Supplier<String> messageSupplier) {
    if (a==null || a.length == 0) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }
  public static void notEmpty(Collection<?> c, Supplier<String> messageSupplier) {
    if (c==null || c.isEmpty()) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }

  public static <T extends Comparable<T>> void inOrder(T val1, T val2, String message) {
    notNull(val1);
    notNull(val2);
    if (val1.compareTo(val2)>0) {
      throw new IllegalArgumentException(message);
    }
  }

  public static <T extends Comparable<T>> void inOrder(T val1, T val2, Supplier<String> messageSupplier) {
    notNull(val1);
    notNull(val2);
    if (val1.compareTo(val2)>=0) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }

  public static <T extends Comparable<T>> void inOrderOrEqual(T val1, T val2, String message) {
    notNull(val1);
    notNull(val2);
    if (val1.compareTo(val2)>=0) {
      throw new IllegalArgumentException(message);
    }
  }

  public static <T extends Comparable<T>> void inOrderOrEqual(T val1, T val2, Supplier<String> messageSupplier) {
    notNull(val1);
    notNull(val2);
    if (val1.compareTo(val2)>0) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }
}
