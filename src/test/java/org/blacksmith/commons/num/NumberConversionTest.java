package org.blacksmith.commons.num;

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
}