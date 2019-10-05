package org.blacksmith.commons.num;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class NumberConversion
{
  private NumberConversion() {}
  private static boolean long2bool(long value)
  {
    return value==1L;
  }

  public static Long createLong(Number value)
  {
    return (value==null) ? null : Long.valueOf(value.longValue());
  }

  public static Long createLong(String value)
  {
    return (StringUtils.isEmpty(value)) ? null : Long.valueOf(value);
  }

  public static Integer createInteger(Number value)
  {
    return (value==null) ? null : Integer.valueOf(value.intValue());
  }

  public static Integer createInteger(String value)
  {
    return (StringUtils.isEmpty(value)) ? null : Integer.valueOf(value);
  }

  public static BigInteger createBigInteger(long value)
  {
    return BigInteger.valueOf(value);
  }

  public static BigInteger createBigInteger(Number value)
  {
    return (value==null) ? null : BigInteger.valueOf(value.longValue());
  }

  public static long toLong(Number value)
  {
    return (value==null) ? 0 : Long.valueOf(value.longValue());
  }

  public static long toLong(String value)
  {
    return (StringUtils.isEmpty(value)) ? null : Long.valueOf(value);
  }

  public static int toInt(Number value)
  {
    return (value==null) ? 0 : Integer.valueOf(value.intValue());
  }

  public static int toInt(String value)
  {
    return (StringUtils.isEmpty(value)) ? null : Integer.valueOf(value);
  }

  public static Boolean createBoolean(Number value)
  {
    return (value==null) ? null : long2bool(value.longValue());
  }

  public static boolean toBoolean(Number value, boolean defaultValue)
  {
    return (value==null) ? defaultValue : long2bool(value.longValue());
  }

  public static Long booleanToLong(Boolean value)
  {
    return (value==null) ? null : booleanToLong(value.booleanValue());
  }

  public static Integer booleanToInteger(Boolean value)
  {
    return (value==null) ? null : booleanToInt(value.booleanValue());
  }

  public static List<Long> toListLong(List<BigInteger> list)
  {
    List<Long> result = new ArrayList<>();
    for (int i=0; i<list.size();i++)
    {
      result.add(Long.valueOf(list.get(i).longValue()));
    }
    return result;
  }
  
  private static int booleanToInt(boolean value) {
    return value ? 1 : 0;
  }

  private static long booleanToLong(boolean value) {
    return value ? 1L : 0L;
  }
}
