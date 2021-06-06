package org.blacksmith.commons.enums;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.blacksmith.commons.arg.ArgChecker;

public class EnumValueConverter<K, E extends Enum<E>> implements EnumConverter<K, E> {

  private final Class<E> enumClass;
  private final Map<K, E> attrMap;

  public EnumValueConverter(Class<E> enumClass, Function<E, K> valueExtractor) {
    this.enumClass = enumClass;
    this.attrMap = Stream.of(enumClass.getEnumConstants())
        .collect(Collectors.toUnmodifiableMap(valueExtractor, e -> e));
  }

  public static <K, E extends Enum<E>> EnumValueConverter<K, E> of(Class<E> enumType, Function<E, K> valueExtractor) {
    return new EnumValueConverter<>(enumType, valueExtractor);
  }

  @Override
  public E fromValue(K value) {
    ArgChecker.notNull(value);
    E result = attrMap.get(value);
    if (result == null) {
      throw new EnumConversionException(enumClass, value);
    }
    return result;
  }
}
