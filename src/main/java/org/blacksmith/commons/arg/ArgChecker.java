package org.blacksmith.commons.arg;

import java.util.Collection;
import java.util.function.Supplier;

public class ArgChecker {
  private ArgChecker(){}
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

  public static <T extends Comparable<T>> void inOrderOrEqual(T val1, T val2, String message) {
    notNull(val1);
    notNull(val2);
    if (val1.compareTo(val2)>0) {
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

  public static <T extends Comparable<T>> void inOrderOrEqual(T val1, T val2, String2ArgSupplier<T> messageSupplier) {
    notNull(val1);
    notNull(val2);
    if (val1.compareTo(val2)>0) {
      throw new IllegalArgumentException(messageSupplier.get(val1,val2));
    }
  }

  public static <T extends Comparable<T>> void inOrderNotEqual(T val1, T val2, String message) {
    notNull(val1);
    notNull(val2);
    if (val1.compareTo(val2)>=0) {
      throw new IllegalArgumentException(message);
    }
  }

  public static <T extends Comparable<T>> void inOrderNotEqual(T val1, T val2, Supplier<String> messageSupplier) {
    notNull(val1);
    notNull(val2);
    if (val1.compareTo(val2)>=0) {
      throw new IllegalArgumentException(messageSupplier.get());
    }
  }

  public static <T extends Comparable<T>> void inOrderNotEqual(T val1, T val2, String2ArgSupplier<T> messageSupplier) {
    notNull(val1);
    notNull(val2);
    if (val1.compareTo(val2)>=0) {
      throw new IllegalArgumentException(messageSupplier.get(val1,val2));
    }
  }

  public static void checkStringLength(String value, int length) {
    notNull(value);
    if (value.length()!=length)
      throw new IllegalArgumentException("Length must be equal " + length + " actual: " + value.length());

  }
  public static void checkStringLength(String value, int length, String message) {
    notNull(value);
    if (value.length()!=length)
      throw new IllegalArgumentException(message);
  }

  public static void checkStringLength(String value, int length, Supplier<String> messageSupplier) {
    notNull(value);
    if (value.length()!=length)
      throw new IllegalArgumentException(messageSupplier.get());
  }

  public static void checkStringLength(String value, int length, String1Arg1ParSupplier<Integer> messageSupplier) {
    notNull(value);
    if (value.length()!=length)
      throw new IllegalArgumentException(messageSupplier.get(value,length));
  }

  public static void checkStringLength(String value, int minLength, int maxLength) {
    notNull(value);
    if (value.length()<minLength || value.length()>maxLength)
      throw new IllegalArgumentException("Length must be between " + minLength + " and " + maxLength + " actual: " + value.length());

  }
  public static void checkStringLength(String value, int minLength, int maxLength, String message) {
    notNull(value);
    if (value.length()<minLength || value.length()>maxLength)
      throw new IllegalArgumentException(message);
  }
  public static void checkStringLength(String value, int minLength, int maxLength, Supplier<String> messageSupplier) {
    notNull(value);
    if (value.length()<minLength || value.length()>maxLength)
      throw new IllegalArgumentException(messageSupplier.get());
  }

  public static void checkStringLength(String value, int minLength, int maxLength, String1Arg2ParSupplier<Integer> messageSupplier) {
    notNull(value);
    if (value.length()<minLength || value.length()>maxLength)
      throw new IllegalArgumentException(messageSupplier.get(value,minLength,maxLength));
  }

  @FunctionalInterface
  public interface String1Arg1ParSupplier<P> {
    String get(String arg,P par);
  }

  @FunctionalInterface
  public interface String1Arg2ParSupplier<P> {
    String get(String arg,P par1, P par2);
  }

  @FunctionalInterface
  public interface String2ArgSupplier<P> {
    String get(P arg1, P arg2);
  }

}
