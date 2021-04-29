package org.blacksmith.commons.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class ValueEnumCache {

  private final static Map<Class, Map<String, Object>> cache = new ConcurrentHashMap<>();

  static <E extends ValueEnum> E forCode(Class<E> enumType, String code) {
    return (E) cache.computeIfAbsent(enumType, clazz -> new ConcurrentHashMap<>())
        .computeIfAbsent(code, convertToEnum(enumType, code));
  }

  private static <E extends ValueEnum> Function<String, E> convertToEnum(Class<E> enumType, String code) {
    return input -> Arrays.stream(enumType.getEnumConstants())
        .filter(enumConstant -> enumConstant.getValue().equals(code)).findFirst()
        .orElseThrow(() -> new EnumConversionException(enumType, code));
  }
}
