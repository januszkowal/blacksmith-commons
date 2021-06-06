package org.blacksmith.commons.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Stream;
import org.blacksmith.commons.arg.ArgChecker;

public class EnumValueConverterLazy<K, E extends Enum<E>> implements EnumConverter<K, E> {

  private final Class<E> enumClass;
  private final Map<K, E> attrMap = new ConcurrentHashMap<>();
  private final Function<E, K> attrExtractor;

  public EnumValueConverterLazy(Class<E> enumClass, Function<E, K> attrExtractor) {
    this.enumClass = enumClass;
    this.attrExtractor = attrExtractor;
  }

  public static <K, E extends Enum<E>> EnumValueConverterLazy<K, E> of(Class<E> enumType, Function<E, K> valueExtractor) {
    return new EnumValueConverterLazy<>(enumType, valueExtractor);
  }

  @Override
  public E fromValue(K value) {
    ArgChecker.notNull(value);
    return attrMap.computeIfAbsent(value, this::fromEnumValue);
  }

  private E fromEnumValue(K key) {
    return Stream.of(enumClass.getEnumConstants())
        .filter(enumConstant -> attrExtractor.apply(enumConstant).equals(key))
        .findFirst()
        .orElseThrow(() -> new EnumConversionException(enumClass, null));
  }
}
