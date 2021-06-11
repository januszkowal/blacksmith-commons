package org.blacksmith.commons.enums;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class AbstractEnumValueConverter<V, E extends Enum<E>> {

  protected final Class<E> enumClass;
  protected final Function<E, V> valueExtractor;
  protected Map<V, E> valueMap;

  public AbstractEnumValueConverter(Class<E> enumClass, Function<E, V> valueExtractor) {
    this.enumClass = enumClass;
    this.valueExtractor = valueExtractor;
  }

  protected E valueToEnum(V value) {
    return Stream.of(enumClass.getEnumConstants())
        .filter(enumConstant -> valueExtractor.apply(enumConstant).equals(value))
        .findFirst()
        .orElseThrow(() -> new EnumConversionException(enumClass, value));
  }
}
