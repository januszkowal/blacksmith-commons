package org.blacksmith.commons.arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("SpellCheckingInspection")
class ArrayUtilsTest {

  final String[][] array = {{"ala", "ma"}, {"bolek", "jest"}};
  final String[] flattArray = {"ala", "ma", "bolek", "jest"};

  @Test
  void flatten() {
    assertArrayEquals(flattArray, ArrayUtils.flatten(array));
  }

  @Test
  void flattenArrayToList() {
    assertIterableEquals(List.of(flattArray), ArrayUtils.flattenArrayToList(array));
  }

  @Test
  void listToArray() {
    assertArrayEquals(flattArray, ArrayUtils.listToArray(String.class, List.of(flattArray)));
  }

  @Test
  void varArgsToList() {
    assertIterableEquals(List.of(flattArray), ArrayUtils.varArgsToList(flattArray));
  }
}
