package org.blacksmith.commons.object;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.blacksmith.commons.string.Pair;
import org.junit.jupiter.api.Test;

class ClassPropertyUtilsTest {

  @Test
  void getPropertyFromMethod() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Dummy1 p = new Dummy1("Qaz","Pou");
    assertEquals("Qaz",ClassPropertyUtils.getProperty(p, "s1"));
    assertEquals("Pou",ClassPropertyUtils.getProperty(p, "s2"));
  }

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
}