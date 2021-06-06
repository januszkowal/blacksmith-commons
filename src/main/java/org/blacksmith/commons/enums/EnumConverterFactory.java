package org.blacksmith.commons.enums;

import java.util.function.Function;

public class EnumConverterFactory {

  private EnumConverterFactory() {}

  public static <V, E extends Enum<E>> EnumConverter<V, E> of(Class<E> enumType, Function<E, V> valueExtractor, boolean lazyInit) {
    if (lazyInit) {
      return EnumValueConverterLazy.of(enumType, valueExtractor);
    } else {
      return EnumValueConverter.of(enumType, valueExtractor);
    }
  }
}
