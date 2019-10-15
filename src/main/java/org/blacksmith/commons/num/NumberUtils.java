package org.blacksmith.commons.num;

import java.math.BigDecimal;

public class NumberUtils {

  private NumberUtils() {}
  public static BigDecimal bigDecimalFromDouble(double value) {
    return BigDecimal.valueOf(value) ;
  }

}
