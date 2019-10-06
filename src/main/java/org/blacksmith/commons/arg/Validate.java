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
  public static void checkNotNull(Object o) {
    if (o == null) {
      throw new IllegalArgumentException();
    }
  }
  public static void checkNotNull(Object o, String message) {
    if (o == null) {
      throw new IllegalArgumentException(message);
    }
  }
  public static void checkNotNull(Object o, Supplier<String> messageSupplier) {
    if (o == null) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }

  public static void checkNotEmpty(Object[] o) {
    if (o == null) {
      throw new IllegalArgumentException();
    }
    if (o.length == 0) {
      throw new IllegalArgumentException();
    }
  }
  public static void checkNotEmpty(Collection<?> c) {
    if (c == null) {
      throw new IllegalArgumentException();
    }
    if (c.isEmpty()) {
      throw new IllegalArgumentException();
    }
  }

  public static void checkNotEmpty(Object[] o, String message) {
    if (o == null) {
      throw new IllegalArgumentException(message);
    }
    if (o.length == 0) {
      throw new IllegalArgumentException(message);
    }
  }
  public static void checkNotEmpty(Collection<?> c, String message) {
    if (c == null) {
      throw new IllegalArgumentException(message);
    }
    if (c.isEmpty()) {
      throw new IllegalArgumentException(message);
    }
  }

  public static void checkNotEmpty(Object[] o, Supplier<String> messageSupplier) {
    if (o == null) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
    if (o.length == 0) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }
  public static void checkNotEmpty(Collection<?> c, Supplier<String> messageSupplier) {
    if (c == null) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
    if (c.isEmpty()) {
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
