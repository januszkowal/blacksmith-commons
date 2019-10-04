package org.blacksmith.commons;

public class BsStringUtils {
  private BsStringUtils() {}
  public static String firstLetterToUpperCase(String value)
  {
    char[] c = value.toCharArray();
    if (c.length>0) {
      c[0] = Character.toUpperCase(c[0]);
    }
    return new String(c);
  }
  public static String firstLetterToLowerCase(String value)
  {
    char[] c = value.toCharArray();
    if (c.length>0) {
      c[0] = Character.toLowerCase(c[0]);
    }
    return new String(c);
  }
}
