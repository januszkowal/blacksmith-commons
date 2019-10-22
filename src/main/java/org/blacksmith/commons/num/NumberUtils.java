package org.blacksmith.commons.num;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.blacksmith.commons.arg.Validate;

public class NumberUtils {

  private static final BigInteger LONG_MIN = BigInteger.valueOf(Long.MIN_VALUE);

  private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);

  private NumberUtils() {}
  public static BigDecimal bigDecimalFromDouble(double value) {
    return BigDecimal.valueOf(value) ;
  }

  public static boolean isNumber(Object value) {
    try {
      Number n = (Number)value;
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }

  public static boolean isNumber(Class<?> clazz) {
    try {
      return Number.class.isAssignableFrom(clazz);
    }
    catch (Exception e) {
      return false;
    }
  }

  public static Number convertNumberObjectToTargetClass(Object number, Class<?> targetClass)
      throws IllegalArgumentException {

    Validate.notNull(number, "Number must not be null");
    Validate.notNull(targetClass, "Target class must not be null");
    return convertNumberToTargetClassInternal((Number)number, (Class<Number>)targetClass);
  }

  public static <T extends Number> T convertNumberToTargetClass(Number number, Class<T> targetClass)
      throws IllegalArgumentException {

    Validate.notNull(number, "Number must not be null");
    Validate.notNull(targetClass, "Target class must not be null");
    return (T)convertNumberToTargetClassInternal(number, targetClass);
  }

  private static Number convertNumberToTargetClassInternal(Number number, Class<? extends Number> targetClass)
      throws IllegalArgumentException {

    Validate.notNull(number, "Number must not be null");
    Validate.notNull(targetClass, "Target class must not be null");

    if (targetClass.isInstance(number)) {
      return number;
    }
    else if (Byte.class == targetClass) {
      long value = checkedLongValue(number, targetClass);
      if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {
        raiseOverflowException(number, targetClass);
      }
      return Byte.valueOf(number.byteValue());
    }
    else if (Short.class == targetClass) {
      long value = checkedLongValue(number, targetClass);
      if (value < Short.MIN_VALUE || value > Short.MAX_VALUE) {
        raiseOverflowException(number, targetClass);
      }
      return Short.valueOf(number.shortValue());
    }
    else if (Integer.class == targetClass) {
      long value = checkedLongValue(number, targetClass);
      if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
        raiseOverflowException(number, targetClass);
      }
      return Integer.valueOf(number.intValue());
    }
    else if (Long.class == targetClass) {
      long value = checkedLongValue(number, targetClass);
      return Long.valueOf(value);
    }
    else if (BigInteger.class == targetClass) {
      if (number instanceof BigDecimal) {
        // do not lose precision - use BigDecimal's own conversion
        return ((BigDecimal) number).toBigInteger();
      }
      else {
        // original value is not a Big* number - use standard long conversion
        return BigInteger.valueOf(number.longValue());
      }
    }
    else if (Float.class == targetClass) {
      return Float.valueOf(number.floatValue());
    }
    else if (Double.class == targetClass) {
      return Double.valueOf(number.doubleValue());
    }
    else if (BigDecimal.class == targetClass) {
      // always use BigDecimal(String) here to avoid unpredictability of BigDecimal(double)
      // (see BigDecimal javadoc for details)
      return new BigDecimal(number.toString());
    }
    else {
      throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" +
          number.getClass().getName() + "] to unsupported target class [" + targetClass.getName() + "]");
    }
  }

  private static long checkedLongValue(Number number, Class<? extends Number> targetClass) {
    BigInteger bigInt = null;
    if (number instanceof BigInteger) {
      bigInt = (BigInteger) number;
    }
    else if (number instanceof BigDecimal) {
      bigInt = ((BigDecimal) number).toBigInteger();
    }
    // Effectively analogous to JDK 8's BigInteger.longValueExact()
    if (bigInt != null && (bigInt.compareTo(LONG_MIN) < 0 || bigInt.compareTo(LONG_MAX) > 0)) {
      raiseOverflowException(number, targetClass);
    }
    return number.longValue();
  }

  private static void raiseOverflowException(Number number, Class<?> targetClass) {
    throw new IllegalArgumentException("Could not convert number [" + number + "] of type [" +
        number.getClass().getName() + "] to target class [" + targetClass.getName() + "]: overflow");
  }
}
