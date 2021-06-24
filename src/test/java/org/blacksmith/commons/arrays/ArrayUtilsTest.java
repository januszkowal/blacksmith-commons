package org.blacksmith.commons.arrays;

import static org.assertj.core.api.Assertions.assertThat;
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

  @Test
  void leftPad() {
    double[] emptyArray = {};
    double[] oneCellAray = {1.};
    double[] threeCellsArray = {1., 2., 3.};
    //0
    assertThat(ArrayUtils.leftPad(emptyArray, 0)).containsExactly();
    assertThat(ArrayUtils.leftPad(oneCellAray, 0)).containsExactly();
    assertThat(ArrayUtils.leftPad(threeCellsArray, 0)).containsExactly();
    assertThat(ArrayUtils.leftPad(emptyArray, 0, 7)).containsExactly();
    assertThat(ArrayUtils.leftPad(oneCellAray, 0, 7)).containsExactly();
    assertThat(ArrayUtils.leftPad(threeCellsArray, 0, 7)).containsExactly();
    //1
    assertThat(ArrayUtils.leftPad(emptyArray, 1)).containsExactly(0);
    assertThat(ArrayUtils.leftPad(oneCellAray, 1)).containsExactly(1.);
    assertThat(ArrayUtils.leftPad(threeCellsArray,  1)).containsExactly(3.);
    assertThat(ArrayUtils.leftPad(emptyArray, 1, 7)).containsExactly(7.);
    assertThat(ArrayUtils.leftPad(oneCellAray, 1, 7)).containsExactly(1.);
    assertThat(ArrayUtils.leftPad(threeCellsArray,  1, 7)).containsExactly(3.);
    //2
    assertThat(ArrayUtils.leftPad(emptyArray, 2)).containsExactly(0., 0.);
    assertThat(ArrayUtils.leftPad(oneCellAray, 2)).containsExactly(0., 1.);
    assertThat(ArrayUtils.leftPad(threeCellsArray,  2)).containsExactly(2., 3.);
    assertThat(ArrayUtils.leftPad(emptyArray, 2, 7)).containsExactly(7., 7.);
    assertThat(ArrayUtils.leftPad(oneCellAray, 2, 7)).containsExactly(7., 1.);
    assertThat(ArrayUtils.leftPad(threeCellsArray,  2, 7)).containsExactly(2., 3.);
    //3
    assertThat(ArrayUtils.leftPad(emptyArray, 3)).containsExactly(0., 0., 0.);
    assertThat(ArrayUtils.leftPad(oneCellAray, 3)).containsExactly(0., 0., 1.);
    assertThat(ArrayUtils.leftPad(threeCellsArray,  3)).containsExactly(1., 2., 3.);
    assertThat(ArrayUtils.leftPad(emptyArray, 3, 7)).containsExactly(7., 7., 7.);
    assertThat(ArrayUtils.leftPad(oneCellAray, 3, 7)).containsExactly(7., 7., 1.);
    assertThat(ArrayUtils.leftPad(threeCellsArray,  3, 7)).containsExactly(1., 2., 3.);
    //4
    assertThat(ArrayUtils.leftPad(emptyArray, 4)).containsExactly(0., 0., 0., 0.);
    assertThat(ArrayUtils.leftPad(oneCellAray, 4)).containsExactly(0., 0., 0., 1.);
    assertThat(ArrayUtils.leftPad(threeCellsArray,  4)).containsExactly(0., 1., 2., 3.);
    assertThat(ArrayUtils.leftPad(emptyArray, 4, 7)).containsExactly(7., 7., 7., 7.);
    assertThat(ArrayUtils.leftPad(oneCellAray, 4, 7)).containsExactly(7., 7., 7., 1.);
    assertThat(ArrayUtils.leftPad(threeCellsArray,  4, 7)).containsExactly(7., 1., 2., 3.);
    //5
    assertThat(ArrayUtils.leftPad(emptyArray, 5)).containsExactly(0., 0., 0., 0., 0.);
    assertThat(ArrayUtils.leftPad(oneCellAray, 5)).containsExactly(0., 0., 0., 0., 1.);
    assertThat(ArrayUtils.leftPad(threeCellsArray,  5)).containsExactly(0., 0., 1., 2., 3.);
    assertThat(ArrayUtils.leftPad(emptyArray, 5, 7)).containsExactly(7., 7., 7., 7., 7.);
    assertThat(ArrayUtils.leftPad(oneCellAray, 5, 7)).containsExactly(7., 7., 7., 7., 1.);
    assertThat(ArrayUtils.leftPad(threeCellsArray,  5, 7)).containsExactly(7., 7., 1., 2., 3.);
  }

  @Test
  void rightPad() {
    double[] emptyArray = {};
    double[] oneCellAray = {1.};
    double[] threeCellsArray = {1., 2., 3.};
    //0
    assertThat(ArrayUtils.rightPad(emptyArray, 0)).containsExactly();
    assertThat(ArrayUtils.rightPad(oneCellAray, 0)).containsExactly();
    assertThat(ArrayUtils.rightPad(threeCellsArray, 0)).containsExactly();
    assertThat(ArrayUtils.rightPad(emptyArray, 0, 7)).containsExactly();
    assertThat(ArrayUtils.rightPad(oneCellAray, 0, 7)).containsExactly();
    assertThat(ArrayUtils.rightPad(threeCellsArray, 0, 7)).containsExactly();
    //1
    assertThat(ArrayUtils.rightPad(emptyArray, 1)).containsExactly(0);
    assertThat(ArrayUtils.rightPad(oneCellAray, 1)).containsExactly(1.);
    assertThat(ArrayUtils.rightPad(threeCellsArray,  1)).containsExactly(1.);
    assertThat(ArrayUtils.rightPad(emptyArray, 1, 7)).containsExactly(7.);
    assertThat(ArrayUtils.rightPad(oneCellAray, 1, 7)).containsExactly(1.);
    assertThat(ArrayUtils.rightPad(threeCellsArray,  1, 7)).containsExactly(1.);
    //2
    assertThat(ArrayUtils.rightPad(emptyArray, 2)).containsExactly(0., 0.);
    assertThat(ArrayUtils.rightPad(oneCellAray, 2)).containsExactly(1., 0.);
    assertThat(ArrayUtils.rightPad(threeCellsArray,  2)).containsExactly(1., 2.);
    assertThat(ArrayUtils.rightPad(emptyArray, 2, 7)).containsExactly(7., 7.);
    assertThat(ArrayUtils.rightPad(oneCellAray, 2, 7)).containsExactly(1., 7.);
    assertThat(ArrayUtils.rightPad(threeCellsArray,  2, 7)).containsExactly(1., 2.);
    //3
    assertThat(ArrayUtils.rightPad(emptyArray, 3)).containsExactly(0., 0., 0.);
    assertThat(ArrayUtils.rightPad(oneCellAray, 3)).containsExactly(1., 0., 0.);
    assertThat(ArrayUtils.rightPad(threeCellsArray,  3)).containsExactly(1., 2., 3.);
    assertThat(ArrayUtils.rightPad(emptyArray, 3, 7)).containsExactly(7., 7., 7.);
    assertThat(ArrayUtils.rightPad(oneCellAray, 3, 7)).containsExactly(1., 7., 7.);
    assertThat(ArrayUtils.rightPad(threeCellsArray,  3, 7)).containsExactly(1., 2., 3.);
    //4
    assertThat(ArrayUtils.rightPad(emptyArray, 4)).containsExactly(0., 0., 0., 0.);
    assertThat(ArrayUtils.rightPad(oneCellAray, 4)).containsExactly(1., 0., 0., 0.);
    assertThat(ArrayUtils.rightPad(threeCellsArray,  4)).containsExactly(1., 2., 3., 0);
    assertThat(ArrayUtils.rightPad(emptyArray, 4, 7)).containsExactly(7., 7., 7., 7.);
    assertThat(ArrayUtils.rightPad(oneCellAray, 4, 7)).containsExactly(1., 7., 7., 7.);
    assertThat(ArrayUtils.rightPad(threeCellsArray,  4, 7)).containsExactly(1., 2., 3., 7.);
    //5
    assertThat(ArrayUtils.rightPad(emptyArray, 5)).containsExactly(0., 0., 0., 0., 0.);
    assertThat(ArrayUtils.rightPad(oneCellAray, 5)).containsExactly(1., 0., 0., 0., 0.);
    assertThat(ArrayUtils.rightPad(threeCellsArray,  5)).containsExactly(1., 2., 3., 0., 0.);
    assertThat(ArrayUtils.rightPad(emptyArray, 5, 7)).containsExactly(7., 7., 7., 7., 7.);
    assertThat(ArrayUtils.rightPad(oneCellAray, 5, 7)).containsExactly(1., 7., 7., 7., 7.);
    assertThat(ArrayUtils.rightPad(threeCellsArray,  5, 7)).containsExactly(1., 2., 3., 7., 7.);
  }
}
