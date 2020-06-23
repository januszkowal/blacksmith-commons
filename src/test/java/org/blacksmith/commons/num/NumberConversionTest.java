package org.blacksmith.commons.num;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NumberConversionTest {

  @Test
  void createIntegerFromNumber() {
    assertEquals(12,NumberConversion.createInteger(12.1d));
    assertEquals(12,NumberConversion.createInteger(12d));
  }

  @Test
  void createIntegerFromString() {
    assertEquals(78,NumberConversion.createInteger("78"));
  }

  @Test
  void bigDecimalFromDoubleTest() {
    assertEquals(BigDecimal.valueOf(89.999993d),NumberConversion.doubleToBigDecimal(89.999993d));
  }
  @SuppressWarnings("UnnecessaryBoxing")
  @Test
  void isNumberTest() {
    //objects
    Assertions.assertTrue(NumberConversion.isNumber(Double.valueOf(2.8)));
    Assertions.assertTrue(NumberConversion.isNumber(Integer.valueOf(2)));
    Assertions.assertTrue(NumberConversion.isNumber(BigDecimal.valueOf(2.8)));
    //primitives
    Assertions.assertTrue(NumberConversion.isNumber(2.8));
    Assertions.assertTrue(NumberConversion.isNumber(2));
    Assertions.assertTrue(NumberConversion.isNumber(2f));
    Assertions.assertTrue(NumberConversion.isNumber(2L));
  }
}