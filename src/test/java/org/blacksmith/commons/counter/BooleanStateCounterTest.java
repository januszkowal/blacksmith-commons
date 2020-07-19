package org.blacksmith.commons.counter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BooleanStateCounterTest {

  @Test
  public void newCounter() {
    var counter = new BooleanStateCounter();
    assertFalse(counter.hasFalse());
    assertFalse(counter.hasTrue());
    assertEquals(0, counter.getCount(true));
    assertEquals(0, counter.getCount(false));
  }

  @Test
  public void shouldHaveChanges() {
    var counter = new BooleanStateCounter();
    counter.update(true);
    counter.update(true);
    counter.update(true);
    counter.update(false);
    counter.update(false);
    assertTrue(counter.hasFalse());
    assertTrue(counter.hasTrue());
    assertEquals(3, counter.getCount(true));
    assertEquals(2, counter.getCount(false));
  }

}