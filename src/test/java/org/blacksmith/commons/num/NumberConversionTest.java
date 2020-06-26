package org.blacksmith.commons.num;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.Test;

class NumberConversionTest {

  @Test
  @SuppressWarnings("UnnecessaryBoxing")
  void isNumberTest() {
    //objects
    assertTrue(NumberConversion.isNumber(Double.valueOf(2.8)));
    assertTrue(NumberConversion.isNumber(Integer.valueOf(2)));
    assertTrue(NumberConversion.isNumber(BigDecimal.valueOf(2.8)));
    //primitives
    assertTrue(NumberConversion.isNumber(2.8));
    assertTrue(NumberConversion.isNumber(2));
    assertTrue(NumberConversion.isNumber(2f));
    assertTrue(NumberConversion.isNumber(2L));
    //classes
    assertTrue(NumberConversion.isNumber(Integer.class));
    assertTrue(NumberConversion.isNumber(BigDecimal.class));
    assertFalse(NumberConversion.isNumber(String.class));
  }

  @Test
  void createIntegerFromNumberTest() {
    assertEquals(12, NumberConversion.createInteger(12.1d));
    assertEquals(12, NumberConversion.createInteger(12d));
  }

  @Test
  void createIntegerFromStringTest() {
    assertEquals(78, NumberConversion.createInteger("78"));
  }

  @Test
  void createBigIntegerTest() {
    assertEquals(BigInteger.valueOf(789L), NumberConversion.createBigInteger(789L));
    assertEquals(BigInteger.valueOf(789L), NumberConversion.createBigInteger(Long.valueOf(789L)));
  }

  @Test
  void doubleToBitDecimalTest() {
    assertEquals(BigDecimal.valueOf(89.999993d), NumberConversion.doubleToBigDecimal(89.999993d));
  }

  @Test
  void booleanToIntegerTest() {
    assertEquals(0, NumberConversion.booleanToInteger(Boolean.FALSE));
    assertEquals(0, NumberConversion.booleanToInteger(false));
    assertEquals(1, NumberConversion.booleanToInteger(Boolean.TRUE));
    assertEquals(1, NumberConversion.booleanToInteger(true));
    assertNull(NumberConversion.booleanToInteger(null));
  }

  @Test
  void createBoolean() {
    assertEquals(Boolean.TRUE,NumberConversion.createBoolean(1));
    assertEquals(Boolean.FALSE,NumberConversion.createBoolean(2));
    assertEquals(Boolean.FALSE,NumberConversion.createBoolean(0));
    Number num = null;
    assertNull(NumberConversion.createBoolean(num));
  }

  @Test
  void createLong() {
    assertEquals(56L,NumberConversion.createLong(56L));
    assertEquals(56L,NumberConversion.createLong(BigDecimal.valueOf(56L)));
    assertEquals(56L,NumberConversion.createLong("56"));
    assertEquals(56L,NumberConversion.createLong("56"));
    Number num = null;
    String str = null;
    assertNull(NumberConversion.createLong(num));
    assertNull(NumberConversion.createLong(str));
  }

  @Test
  void booleanToLongTest() {
    assertEquals(0, NumberConversion.booleanToLong(Boolean.FALSE));
    assertEquals(0, NumberConversion.booleanToLong(false));
    assertEquals(1, NumberConversion.booleanToLong(Boolean.TRUE));
    assertEquals(1, NumberConversion.booleanToLong(true));
    assertNull(NumberConversion.booleanToLong(null));
  }

  @Test
  void toBoolean() {
    assertTrue(NumberConversion.toBoolean(1L,Boolean.FALSE));
    assertTrue(NumberConversion.toBoolean(null,Boolean.TRUE));
    assertFalse(NumberConversion.toBoolean(null,Boolean.FALSE));
  }

  @Test
  void toInt() {
    assertEquals(1,NumberConversion.toInt(1L));
    assertEquals(18,NumberConversion.toInt(18L));
    assertEquals(18,NumberConversion.toInt("18"));
    assertEquals(0,NumberConversion.toInt(""));
  }

  @Test
  void toLong() {
    assertEquals(1,NumberConversion.toLong(1d));
    assertEquals(18,NumberConversion.toLong(18d));
    assertEquals(18,NumberConversion.toLong("18"));
    assertEquals(0,NumberConversion.toLong(""));
  }

  @Test
  void toListLongTest() {
    assertThat(NumberConversion.toListLong(List.of(BigInteger.valueOf(1L),BigInteger.valueOf(2),BigInteger.valueOf(3L))))
        .containsAll(List.of(1L,2L,3L));
  }

  @Test
  void convertNumberObjectToTargetClass() {
    assertEquals(12, NumberConversion.convertNumberObjectToTargetClass(BigDecimal.valueOf(12.45), Integer.class));
    assertEquals(12.45, NumberConversion.convertNumberObjectToTargetClass(BigDecimal.valueOf(12.45), Double.class));
  }

  @Test
  void convertNumberToTargetClassTest() {
    assertEquals(12, NumberConversion.convertNumberToTargetClass(BigDecimal.valueOf(12.45), Integer.class));
    assertEquals(12.45, NumberConversion.convertNumberToTargetClass(BigDecimal.valueOf(12.45), Double.class));
  }
}