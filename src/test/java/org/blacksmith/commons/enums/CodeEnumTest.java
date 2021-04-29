package org.blacksmith.commons.enums;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CodeEnumTest {

  @Test
  public void shouldTrowException() {
    Assertions.assertThrows(EnumConversionException.class, () -> {
      XEnum.fromValue("a");
    });
  }

  @Test
  public void shouldFind() {
    assertNotNull(XEnum.fromValue("aX"));
    assertNotNull(XEnum.fromValue("By"));
    assertNotNull(XEnum.fromValue("CU"));
  }
}
