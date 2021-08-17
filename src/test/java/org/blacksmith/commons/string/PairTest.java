package org.blacksmith.commons.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.blacksmith.commons.vo.Pair;
import org.junit.jupiter.api.Test;

class PairTest {

  @Test
  public void integerPairTest() {
    Pair<Integer, Integer> pair = Pair.of(1, 2);
    assertEquals(1, pair.getLeft());
    assertEquals(2, pair.getRight());
  }

  @Test
  public void stringPairTest() {
    Pair<String, String> pair = Pair.of("ala", "kot");
    assertEquals("ala", pair.getLeft());
    assertEquals("kot", pair.getRight());
  }

  @Test
  public void combinedPairTest() {
    Pair<String, Integer> pair = Pair.of("ala", 25);
    assertEquals("ala", pair.getLeft());
    assertEquals(25, pair.getRight());
  }
}
