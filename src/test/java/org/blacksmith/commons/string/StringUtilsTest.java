package org.blacksmith.commons.string;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilsTest {
  @Test
  public void firstLetterToLowerCase(){
    assertNull(StringUtils.firstLetterToLowerCase(null));
    assertEquals("",StringUtils.firstLetterToLowerCase(""));
    assertEquals("abc",StringUtils.firstLetterToLowerCase("abc"));
    assertEquals("abc",StringUtils.firstLetterToLowerCase("Abc"));
    assertEquals("aBC",StringUtils.firstLetterToLowerCase("ABC"));
  }
  @Test
  public void firstLetterToUpperCase(){
    assertNull(StringUtils.firstLetterToUpperCase(null));
    assertEquals("",StringUtils.firstLetterToUpperCase(""));
    assertEquals("Abc",StringUtils.firstLetterToUpperCase("abc"));
    assertEquals("Abc",StringUtils.firstLetterToUpperCase("Abc"));
    assertEquals("ABC",StringUtils.firstLetterToUpperCase("aBC"));
    assertEquals("ABC",StringUtils.firstLetterToUpperCase("ABC"));
  }
}