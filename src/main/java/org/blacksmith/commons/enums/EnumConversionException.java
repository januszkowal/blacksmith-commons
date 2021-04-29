package org.blacksmith.commons.enums;

class EnumConversionException extends RuntimeException {

  private static final long serialVersionUID = -6860200757890336618L;

  public EnumConversionException(Class<?> enumType, Object code) {
    super("Enum value of '" + enumType.getName() + "' for code \"" + code + "\" has not been found!");
  }

}
