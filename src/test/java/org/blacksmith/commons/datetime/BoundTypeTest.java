package org.blacksmith.commons.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BoundTypeTest {

  @Test
  void flip() {
    assertEquals(BoundType.OPEN, BoundType.CLOSED.flip());
    assertEquals(BoundType.CLOSED, BoundType.OPEN.flip());
  }
}
