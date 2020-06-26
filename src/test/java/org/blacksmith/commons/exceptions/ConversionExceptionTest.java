package org.blacksmith.commons.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConversionExceptionTest {
  @Test
  public void exceptionOfMessageFormat() {
    try {
      ConversionException.ofMessageFormat("Wrong arguments: %s, %d","Qaz",123);
    }
    catch (ConversionException e) {
      assertEquals("Wrong arguments: Qaz, 123",e.getMessage());
    }
  }

  @Test
  public void exceptionOfThrowableMessageFormat() {
    try {
      Throwable t = new IllegalArgumentException("Value to large");
      ConversionException.ofThrowableMessageFormat(t,"Wrong arguments: {0}, {1}","Qaz",123);
    }
    catch (ConversionException e) {
      assertEquals("Wrong arguments: Qaz, 123",e.getMessage());
      assertEquals("Value to large",e.getCause().getMessage());
    }
  }
}