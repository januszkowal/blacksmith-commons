package org.blacksmith.commons.enums;

import java.util.function.Function;
import org.blacksmith.commons.arg.ArgChecker;

public class EnumValueConverter<V, E extends Enum<E>> extends AbstractEnumValueConverter<V, E>
    implements EnumConverter<V, E> {

  public EnumValueConverter(Class<E> enumClass, Function<E, V> valueExtractor) {
    super(enumClass, valueExtractor);
    this.valueMap = EnumUtils.getValueEnumUnmodifiableMap(enumClass, valueExtractor);
  }

  public static <K, E extends Enum<E>> EnumValueConverter<K, E> of(Class<E> enumType, Function<E, K> valueExtractor) {
    return new EnumValueConverter<>(enumType, valueExtractor);
  }

  @Override
  public E convert(V value) {
    ArgChecker.notNull(value);
    E result = valueMap.get(value);
    if (result == null) {
      throw new EnumConversionException(enumClass, value);
    }
    return result;
  }
}
