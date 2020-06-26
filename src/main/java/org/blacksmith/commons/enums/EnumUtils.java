package org.blacksmith.commons.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumUtils {
  private EnumUtils() {}
  public static <E extends Enum<E>> E getEnumByName(Class<E> enumClass, String enumName) {
    if (enumName == null) {
      return null;
    }
    try {
      return Enum.valueOf(enumClass, enumName.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      return null;
    }
  }

  public static <E extends Enum<E>> E getEnumByName(Class<E> enumClass, String enumName, E defaultValue) {
    if (enumName == null) {
      return defaultValue;
    }
    try {
      return Enum.valueOf(enumClass, enumName.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      return defaultValue;
    }
  }

  /*
  * Returns map containing:
  * key - value returned by keyExtractor
  * value - enum
  * */
  public static <K,E extends Enum<E>> Map<K, E> getAttrEnumMap(Class<E> enumClass, Function<E,K> keyExtractor) {
    return Stream.of(enumClass.getEnumConstants()).collect(Collectors.toMap(keyExtractor,e->e));
  }

  /*
   * Returns map containing:
   * key - enum
   * value - value returned by valueExtractor
   * */
  public static <E extends Enum<E>,K> EnumMap<E,K> getEnumAttrMap(Class<E> enumClass, Function<E,K> valueExtractor)
  {
    return Stream.of(enumClass.getEnumConstants())
        .collect(Collectors.toMap(e->e, valueExtractor,(e1,e2)->e1,()->new EnumMap<>(enumClass)));
  }

  public static <E extends Enum<E>> EnumMap<E,String> getEnumNameMap(Class<E> enumClass)
  {
    return Stream.of(enumClass.getEnumConstants())
        .collect(Collectors.toMap(e->e, Enum::name,(e1,e2)->e1,()->new EnumMap<>(enumClass)));
  }

  public static <E extends Enum<E>> EnumSet<E> getEnumSet(Class<E> enumClass)
  {
    return EnumSet.allOf(enumClass);
  }


  public static <E extends Enum<E>> List<E> getEnumList(Class<E> enumClass)
  {
    return new ArrayList<>(Arrays.asList(enumClass.getEnumConstants()));
  }

  public static <E extends Enum<E>> List<String> getEnumNames(Collection<E> enums)
  {
    return enums.stream().map(Enum::name).collect(Collectors.toList());
  }

  public static <E extends Enum<E>> List<String> getEnumNamesList(Class<E> enumClass)
  {
    return Stream.of(enumClass.getEnumConstants()).map(Enum::name).collect(Collectors.toList());
  }

  public static <E extends Enum<E>> String[] getEnumNamesArray(Class<E> enumClass)
  {
    return getEnumNamesList(enumClass).toArray(new String[0]);
  }

  public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass,
      String enumName) {
    if (enumName == null) {
      return false;
    }
    try {
      Enum.valueOf(enumClass, enumName);
      return true;
    } catch (IllegalArgumentException ex) {
      return false;
    }
  }

  public static <E extends Enum<E>> String getName(E enumItem) {
    return (enumItem==null) ? null : enumItem.name();
  }

  public static <E extends Enum<E>>boolean inList(E item, List<E> list)
  {
    return list.contains(item);
  }

  @SafeVarargs
  public static <E extends Enum<E>>boolean inArray(E item, E ... arr)
  {
    return Arrays.asList(arr).contains(item);
  }

}
