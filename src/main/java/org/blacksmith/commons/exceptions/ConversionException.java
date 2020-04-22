package org.blacksmith.commons.exceptions;

import java.text.MessageFormat;

public class ConversionException extends RuntimeException {
  
  private static final long serialVersionUID = -1174947128676270564L;
  
  public ConversionException(String message) {
    super(message);
  }
  public ConversionException(String message, Throwable t) {
    super(message,t);
  }
  public static ConversionException ofMessageFormat(String format, Object...args) {
    return new ConversionException(MessageFormat.format(format, args));
  }
  public static ConversionException ofThrowableMessageFormat(Throwable t, String format, Object...args) {
    return new ConversionException(MessageFormat.format(format, args),t);
  }
}
