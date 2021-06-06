package org.blacksmith.commons.enums;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import org.blacksmith.commons.arg.ArgChecker;

public class EnumValueConverterLazy<V, E extends Enum<E>> extends AbstractEnumValueConverter<V, E>
    implements EnumConverter<V, E> {

  public EnumValueConverterLazy(Class<E> enumClass, Function<E, V> attrExtractor) {
    super(enumClass, attrExtractor);
    this.valueMap = new ConcurrentHashMap<>();
  }

  public static <V, E extends Enum<E>> EnumValueConverterLazy<V, E> of(Class<E> enumType,
      Function<E, V> valueExtractor) {
    return new EnumValueConverterLazy<>(enumType, valueExtractor);
  }

  @Override
  public E convert(V value) {
    ArgChecker.notNull(value);
    return valueMap.computeIfAbsent(value, this::valueToEnum);
  }
}
