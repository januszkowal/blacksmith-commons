package org.blacksmith.commons.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PropertyUpdaterTest {

  @Test
  public void update1() {
    Dummy obj = new Dummy();
    PropertyUpdater<Dummy, Long> updater = new PropertyUpdater<>(Dummy::getProp1, Dummy::setProp1);
    assertTrue(updater.set(obj, 7L));
    assertEquals(7L, obj.getProp1());

    assertTrue(updater.set(obj, 3L));
    assertEquals(3L, obj.getProp1());
    assertFalse(updater.set(obj, 3L));

    assertTrue(updater.set(obj, null));
    assertNull(obj.getProp1());
    assertFalse(updater.set(obj, null));
  }

  @Test
  public void update2() {
    Dummy obj = new Dummy();
    PropertyUpdater<Dummy, Long> updater = new PropertyUpdater<>(Dummy::getProp2, Dummy::setProp2);
    updater.set(obj, 7L);
    assertEquals(7L, obj.getProp2());
    updater.set(obj, 3L);
    assertEquals(3L, obj.getProp2());
    updater.set(obj, null);
    assertNull(obj.getProp2());
  }

  private static class Dummy {

    private Long prop1;
    private Long prop2;

    public Long getProp1() {
      return this.prop1;
    }

    public void setProp1(Long prop1) {
      this.prop1 = prop1;
    }

    public Long getProp2() {
      return this.prop2;
    }

    public Long setProp2(Long prop2) {
      this.prop2 = prop2;
      return prop2;
    }
  }
}
