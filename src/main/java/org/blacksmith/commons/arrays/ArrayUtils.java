package org.blacksmith.commons.arrays;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {
  /**
   * Class contains only static methods.
   */
  private ArrayUtils() {}

  /**
   * Transform a multidimensional array into a one-dimensional list.
   *
   * @param array Array (possibly multidimensional).
   * @return a list of all the {@code Object} instances contained in
   * {@code array}.
   */
  public static Object[] flatten(Object[] array) {
    final List<Object> list = new ArrayList<Object>();
    if (array != null) {
      for (Object o : array) {
        if (o instanceof Object[]) {
          for (Object oR : flatten((Object[]) o)) {
            list.add(oR);
          }
        } else {
          list.add(o);
        }
      }
    }
    return list.toArray();
  }

  public static <C, T extends C> C[] listToArray(Class<C> componentType, List<T> list) {
    @SuppressWarnings("unchecked")
    C[] array = (C[]) Array.newInstance(componentType, list.size());
    return list.toArray(array);
  }
}
