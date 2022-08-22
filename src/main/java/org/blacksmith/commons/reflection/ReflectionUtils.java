package org.blacksmith.commons.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtils {
  private ReflectionUtils() {
  }

  public static Class<?> getGenericParameterType(Class<?> clazz, Class<?> expectedRawType) {
    return getGenericParameterType(clazz, expectedRawType, 0);
  }

  public static Class<?> getGenericParameterType(Class<?> clazz, Class<?> expectedRawType, int parameterNo) {
    Type[] genericInterfaces = clazz.getGenericInterfaces();
    ParameterizedType type = findByRawType(genericInterfaces, expectedRawType);
    return (Class<?>) type.getActualTypeArguments()[parameterNo];
  }

  private static ParameterizedType findByRawType(Type[] genericInterfaces, Class<?> expectedRawType) {
    for (Type type : genericInterfaces) {
      if (type instanceof ParameterizedType parametrized && expectedRawType.getClass().equals(parametrized.getRawType().getClass())) {
        return parametrized;
      }
    }
    throw new IllegalArgumentException("Invalid class definition");
  }
}
