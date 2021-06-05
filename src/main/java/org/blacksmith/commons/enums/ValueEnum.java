package org.blacksmith.commons.enums;

import java.util.function.Function;
import java.util.stream.Stream;

public interface ValueEnum<E> {

  static <E extends ValueEnum> Function<String, E> valueToEnum(Class<E> enumType, String value) {
    return input -> Stream.of(enumType.getEnumConstants())
        .filter(enumConstant -> enumConstant.getValue().equals(value))
        .findFirst()
        .orElseThrow(() -> new EnumConversionException(enumType, value));
  }

  String getValue();
}
