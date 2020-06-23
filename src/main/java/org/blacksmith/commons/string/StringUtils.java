package org.blacksmith.commons.string;

public class StringUtils {
  private StringUtils() {}
  public static String firstLetterToUpperCase(String value)
  {
    if (value==null)
      return null;
    char[] c = value.toCharArray();
    if (c.length>0) {
      c[0] = Character.toUpperCase(c[0]);
    }
    return new String(c);
  }
  public static String firstLetterToLowerCase(String value)
  {
    if (value==null)
      return null;
    char[] c = value.toCharArray();
    if (c.length>0) {
      c[0] = Character.toLowerCase(c[0]);
    }
    return new String(c);
  }
}
