package org.blacksmith.commons.enums;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValueMap<V, E extends Enum<E>> {
  private final Class<E> enumType;
  private final Map<V, E> valueMap;

  public EnumValueMap(Class<E> enumType, Function<E, V> valueExtractor) {
    this.enumType = enumType;
    this.valueMap = Stream.of(enumType.getEnumConstants())
        .collect(Collectors.toMap(valueExtractor, e -> e));
  }

  public static <V, E extends Enum<E>> EnumValueMap<V, E> of(Class<E> enumType, Function<E, V> valueExtractor) {
    return new EnumValueMap<>(enumType, valueExtractor);
  }

  public E toEnum(V value) {
    E result = valueMap.get(value);
    if (result == null) {
      throw new EnumConversionException(enumType, value);
    }
    return result;
  }

}
