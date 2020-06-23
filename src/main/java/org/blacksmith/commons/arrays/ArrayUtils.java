package org.blacksmith.commons.arrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayUtils {
  /**
   * Class contains only static methods.
   */
  private ArrayUtils() {}

  /**
   * Transform a multidimensional array into a one-dimensional list.
   *
   * @param array Array (possibly multidimensional).
   * @return an array of all the {@code Object} instances contained in
   * {@code array}.
   */
  public static Object[] flatten(Object[] array) {
    return flattenArrayToList(array).toArray();
  }

  /**
   * Transform a multidimensional array into a one-dimensional list.
   *
   * @param array Array (possibly multidimensional).
   * @return a list of all the {@code Object} instances contained in
   * {@code array}.
   */
  public static List<Object> flattenArrayToList(Object[] array) {
    final List<Object> list = new ArrayList<>();
    if (array != null) {
      for (Object o : array) {
        if (o instanceof Object[]) {
          list.addAll(flattenArrayToList((Object[]) o));
        } else {
          list.add(o);
        }
      }
    }
    return list;
  }

  public static <C, T extends C> C[] listToArray(Class<C> componentType, List<T> list) {
    @SuppressWarnings("unchecked")
    //C[] array = (C[]) Array.newInstance(componentType, list.size());
    C[] array = (C[]) Array.newInstance(componentType, 0);
    return list.toArray(array);
  }
  
  @SafeVarargs
  public static <T> List<T> varArgsToList(T ... args) {
    return Stream.of(args).collect(Collectors.toList());
  }
    
}
