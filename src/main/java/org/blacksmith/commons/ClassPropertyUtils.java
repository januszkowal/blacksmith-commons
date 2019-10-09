package org.blacksmith.commons;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.beanutils.PropertyUtils;

public class ClassPropertyUtils
{
  private ClassPropertyUtils() {}
  public static String getPropertyFromMethod(Method method)
  {
    return BsStringUtils.firstLetterToLowerCase(method.getName().substring(3));
  }

  public static Field getPropertyField(Class<?> type, String name) throws NoSuchFieldException
  {
    if (type == null)
      return null;
    try
    {
      return type.getDeclaredField(name);
    }
    catch (NoSuchFieldException nf)
    {
      return getPropertyField(type.getSuperclass(), name);
    }
    catch (SecurityException se)
    {
      return null;
    }
  }

  public static boolean isGetter(Method method)
  {
    if (!method.getName().startsWith("get"))
      return false;
    else if (method.getParameterTypes().length != 0)
      return false;
    else if (void.class.equals(method.getReturnType()))
      return false;
    else
      return true;
  }

  public static boolean isSetter(Method method)
  {
    if (!method.getName().startsWith("set"))
      return false;
    else if (method.getParameterTypes().length != 1)
      return false;
    else return true;
  }

  public static Object getProperty(Object object, String path) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
  {
    String[] patha = path.split("\\.");
    Object o = object;
    for (String pe: patha)//Splitter.on(".").splitToList(path))
    {
      o = PropertyUtils.getSimpleProperty(o, pe);
      if (o==null) break;
    }
    return o;
  }
}
