package org.blacksmith.commons.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class ValueEnumCache {

  private final static Map<Class, Map<String, Object>> cache = new ConcurrentHashMap<>();

  static <E extends ValueEnum> E forValue(Class<E> enumType, String value) {
    return (E) cache.computeIfAbsent(enumType, clazz -> new ConcurrentHashMap<>())
        .computeIfAbsent(value, convertToEnum(enumType, value));
  }

  private static <E extends ValueEnum> Function<String, E> convertToEnum(Class<E> enumType, String value) {
    return input -> Arrays.stream(enumType.getEnumConstants())
        .filter(enumConstant -> enumConstant.getValue().equals(value))
        .findFirst()
        .orElseThrow(() -> new EnumConversionException(enumType, value));
  }
}
