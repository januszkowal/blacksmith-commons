package org.blacksmith.commons.enums;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumCacheTest {

  @Test
  public void shouldTrowException() {
    Assertions.assertThrows(EnumConversionException.class, () -> {
      XEnumWithLazyCache.fromValue("a");
    });
  }

  @Test
  public void shouldFind() {
    assertNotNull(XEnumWithLazyCache.fromValue("aX"));
    assertNotNull(XEnumWithLazyCache.fromValue("By"));
    assertNotNull(XEnumWithLazyCache.fromValue("CU"));
  }
}
