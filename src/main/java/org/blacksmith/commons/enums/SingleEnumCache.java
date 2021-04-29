package org.blacksmith.commons.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.blacksmith.commons.arg.ArgChecker;

public class SingleEnumCache<V, E extends Enum<E>> {

  private Map<V, E> cache;
  private final Function<E, V> getter;
  private final Function<V, E> outputGetter;

  public SingleEnumCache(Class<E> enumClass, Function<E, V> getter, boolean lazyInit) {
    this.getter = getter;
    if (lazyInit) {
      this.cache = new ConcurrentHashMap<>();
      this.outputGetter = value -> cache.computeIfAbsent(value, getValue(enumClass, value));
    } else {
      this.cache = EnumUtils.getAttrEnumMap(enumClass, getter);
      this.outputGetter = value -> cache.computeIfAbsent(value, throwException(enumClass, value));
    }
  }

  public Map<V, E> getAllValues(Class<E> enumClass) {
    return Stream.of(enumClass.getEnumConstants()).collect(Collectors.toMap(getter::apply, e -> e));
  }

  private Function<V, E> getValue(Class<E> enumClass, V value) {
    return input -> Arrays.stream(enumClass.getEnumConstants())
        .filter(enumConstant -> getter.apply(enumConstant).equals(value)).findFirst()
        .orElseThrow(() -> new EnumConversionException(enumClass, value));
  }

  private Function<V, E> throwException(Class<E> enumClass, V value) {
    return input -> {
      throw new EnumConversionException(enumClass, value);
    };
  }

  public E get(V value) {
    ArgChecker.notNull(value);
    return outputGetter.apply(value);
  }
}
