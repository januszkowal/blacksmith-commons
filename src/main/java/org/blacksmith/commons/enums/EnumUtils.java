package org.blacksmith.commons.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnumUtils {
  private EnumUtils() {}
  public static <E extends Enum<E>> E getEnumByName(Class<E> enumClass, String enumName) {
    if (enumName == null) {
      return null;
    }
    try {
      return Enum.valueOf(enumClass, enumName.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {

    }
    return null;
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

  public static <E extends Enum<E>> Map<String, E> getEnumMap(Class<E> enumClass) {
    return Arrays.stream(enumClass.getEnumConstants()).collect(Collectors.toMap(E::name,e->e));
  }

  public static <E extends Enum<E>> EnumMap<E,String> getEnumNameMap(Class<E> enumClass)
  {
    return new EnumMap<E, String>(enumClass);
  }


  public static <E extends Enum<E>> List<E> getEnumList(Class<E> enumClass)
  {
    return new ArrayList<E>(Arrays.asList(enumClass.getEnumConstants()));
  }

  public static <E extends Enum<E>> List<String> getEnumNames(List<E> enums)
  {
    return enums.stream().map(Enum::name).collect(Collectors.toList());
  }

  public static <E extends Enum<E>> List<String> getEnumNamesList(Class<E> enumClass)
  {
    return Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).collect(Collectors.toList());
  }

  public static <E extends Enum<E>> String[] getEnumNamesArray(Class<E> enumClass)
  {
    List<String> lst = getEnumNamesList(enumClass);
    return lst.toArray(new String[lst.size()]);
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
  public static <E extends Enum<E>>boolean inArray(E item, E[] arr)
  {
    return Arrays.asList(arr).contains(item);
  }

  public static <E extends Enum<E>>boolean inList(E item, List<E> list)
  {
    return list.contains(item);
  }

  @SafeVarargs
  public static <E extends Enum<E>>boolean inVArray(E item, E ... list)
  {
    return Arrays.asList(list).contains(item);
  }

}
