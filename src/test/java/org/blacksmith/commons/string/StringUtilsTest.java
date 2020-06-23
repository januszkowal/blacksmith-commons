package org.blacksmith.commons.string;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilsTest {
  @Test
  public void firstLetterToLowerCase() {
    Assertions.assertNull(StringUtils.firstLetterToLowerCase(null));
    Assertions.assertEquals("",StringUtils.firstLetterToLowerCase(""));
    Assertions.assertEquals("abc",StringUtils.firstLetterToLowerCase("abc"));
    Assertions.assertEquals("abc",StringUtils.firstLetterToLowerCase("Abc"));
    Assertions.assertEquals("aBC",StringUtils.firstLetterToLowerCase("ABC"));
  }

  @Test
  public void firstLetterToUpperCase() {
    Assertions.assertNull(StringUtils.firstLetterToUpperCase(null));
    Assertions.assertEquals("",StringUtils.firstLetterToUpperCase(""));
    Assertions.assertEquals("Abc",StringUtils.firstLetterToUpperCase("abc"));
    Assertions.assertEquals("Abc",StringUtils.firstLetterToUpperCase("Abc"));
    Assertions.assertEquals("ABC",StringUtils.firstLetterToUpperCase("ABC"));
  }
}