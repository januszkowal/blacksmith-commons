package org.blacksmith.commons.arg;


public class ArgUtils {

  private ArgUtils() {
  }

  public static <T> T firstNotNull(T arg1, T arg2) {
    return arg1 != null ? arg1 : arg2;
  }

  public static <T> T firstNotNull(T arg1, T arg2, T arg3) {
    return arg1 != null ? arg1 : firstNotNull(arg2, arg3);
  }

  public static <T> T firstNotNull(T arg1, T arg2, T arg3, T arg4) {
    return arg1 != null ? arg1 : firstNotNull(arg2, arg3, arg4);
  }

  public static <T> T firstNotNull(T arg1, T arg2, T arg3, T arg4, T arg5) {
    return arg1 != null ? arg1 : firstNotNull(arg2, arg3, arg4, arg5);
  }
}
