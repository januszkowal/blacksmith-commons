package org.blacksmith.commons.num;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberConversionTest {

  @Test
  void createIntegerFromNumber() {
    NumberConversion.createInteger(12.1d);
  }

  @Test
  void createIntegerFromString() {
    NumberConversion.createInteger("78");
  }

  @Test
  void bigDecimalFromDoubleTest() {
    Assertions.assertEquals(BigDecimal.valueOf(89.999993d),NumberConversion.doubleToBigDecimal(89.999993d));
  }
}