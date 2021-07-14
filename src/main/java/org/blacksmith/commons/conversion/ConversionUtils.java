package org.blacksmith.commons.conversion;

public class ConversionUtils {
  private ConversionUtils() {}
  public static boolean isAssignableClass(Class<?> targetClass, Class<?> srcClass) {
    return targetClass.isAssignableFrom(srcClass);
  }

  public static boolean isAssignableValue(Class<?> targetClass, Object value) {
    return targetClass.isAssignableFrom(value.getClass());
  }
}
