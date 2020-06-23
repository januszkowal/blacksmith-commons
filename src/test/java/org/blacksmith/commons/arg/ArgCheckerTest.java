package org.blacksmith.commons.arg;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ArgCheckerTest {


  @Test()
  void testNotNull() {
    assertThrows(IllegalArgumentException.class,()->{
      ArgChecker.notNull(null);
      ArgChecker.notNull(null,"null value");
      ArgChecker.notNull(null,()->"null value");
    });
    ArgChecker.notNull("");
  }

  @Test
  void notEmptyList() {
    ArgChecker.notEmpty(List.of(""));
    ArgChecker.notEmpty(List.of(""),"empty list");
    ArgChecker.notEmpty(List.of(""),()->"empty list");
    assertThrows(IllegalArgumentException.class,()->{
      ArgChecker.notEmpty(List.of());
    });

    assertThrows(IllegalArgumentException.class,()->{
      ArgChecker.notEmpty(List.of(),"empty list");
    });
    assertThrows(IllegalArgumentException.class,()->{
      ArgChecker.notEmpty(List.of(),()->"empty list");
    });
  }

  @Test
  void notEmptyArray() {
    ArgChecker.notEmpty(new String[]{""});
    ArgChecker.notEmpty(new String[]{""},"empty array");
    ArgChecker.notEmpty(new String[]{""},()->"empty array");
    assertThrows(IllegalArgumentException.class,()->{
      ArgChecker.notEmpty(new String[]{});
    });
    assertThrows(IllegalArgumentException.class,()->{
      ArgChecker.notEmpty(new String[]{},"empty array");
    });
    assertThrows(IllegalArgumentException.class,()->{
      ArgChecker.notEmpty(new String[]{},()->"empty array");
    });
  }

  @Test
  void testNotEmpty() {
  }

  @Test
  void testNotEmpty1() {
  }

  @Test
  void testNotEmpty2() {
  }

  @Test
  void testNotEmpty3() {
  }

  @Test
  void testNotEmpty4() {
  }

  @Test
  void inOrder() {
  }

  @Test
  void testInOrder() {
  }

  @Test
  void inOrderOrEqual() {
  }

  @Test
  void testInOrderOrEqual() {
  }

  @Test
  void checkStringLength() {
  }

  @Test
  void testCheckStringLength() {
  }

  @Test
  void testCheckStringLength1() {
  }

  @Test
  void testCheckStringLength2() {
  }

  @Test
  void testCheckStringLength3() {
  }

  @Test
  void testCheckStringLength4() {
  }
}