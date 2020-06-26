package org.blacksmith.commons.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtils {

  public static ParameterizedType findByRawType(Type[] genericInterfaces, Class<?> expectedRawType) {
    for (Type type : genericInterfaces) {
      if (type instanceof ParameterizedType) {
        ParameterizedType parametrized = (ParameterizedType) type;
        if (expectedRawType.equals(parametrized.getRawType())) {
          return parametrized;
        }
      }
    }
    throw new RuntimeException();
  }

  public static ParameterizedType findByRawType(Class<?> clazz, Class<?> expectedRawType) {
    return findByRawType(clazz.getGenericInterfaces(), expectedRawType);
  }
}
