package org.blacksmith.commons.enums;

import java.util.function.Function;

public class EnumConverterFactory {

  private EnumConverterFactory() {}

  public static <K, E extends Enum<E>> EnumConverter<K, E> of(Class<E> enumType, Function<E, K> valueExtractor, boolean lazyInit) {
    if (lazyInit) {
      return EnumValueConverterLazy.of(enumType, valueExtractor);
    } else {
      return EnumValueConverter.of(enumType, valueExtractor);
    }
  }
}
