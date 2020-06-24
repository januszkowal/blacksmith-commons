package org.blacksmith.commons.object;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.blacksmith.commons.string.Pair;
import org.junit.jupiter.api.Test;

class ClassPropertyUtilsTest {

  @Test
  void getPropertyField() throws IllegalAccessException {
    Dummy2 p = new Dummy2("Qaz","Pou");
    Field pf = ClassPropertyUtils.getPropertyField(Dummy2.class, "s2");
    assertNotNull(pf);
    assertEquals("Pou",pf.get(p));
  }

  @Test
  void getProperty() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Pair<String,Integer> p = Pair.of("Qaz",8);
    assertEquals("Qaz",ClassPropertyUtils.getProperty(p,"left"));
    assertEquals(8,ClassPropertyUtils.getProperty(p,"right"));
  }

  @Test
  void getPropertyFromMethod() throws NoSuchMethodException {
    Method ms1 = Dummy2.class.getMethod("getS1");
    assertEquals("s1",ClassPropertyUtils.getPropertyFromMethod(ms1));
    assertTrue(ClassPropertyUtils.isGetter(ms1));
    assertFalse(ClassPropertyUtils.isSetter(ms1));
    Method ms2 = Dummy2.class.getMethod("setS1",String.class);
    assertFalse(ClassPropertyUtils.isGetter(ms2));
    assertTrue(ClassPropertyUtils.isSetter(ms2));
  }
}