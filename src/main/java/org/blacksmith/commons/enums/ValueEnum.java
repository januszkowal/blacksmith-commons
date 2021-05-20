package org.blacksmith.commons.enums;

import java.util.function.Function;
import java.util.stream.Stream;

public interface ValueEnum<E> {

  static <E extends ValueEnum> Function<String, E> valueToEnum(Class<E> enumType, String code) {
    return input -> Stream.of(enumType.getEnumConstants()).filter(enumConstant -> enumConstant.getValue().equals(code))
        .findFirst()
        .orElseThrow(() -> new EnumConversionException(enumType, code));
  }

  String getValue();
}
