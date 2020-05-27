package org.blacksmith.commons.arg;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ValidateTest {


  @Test()
  void testNotNull() {
    assertThrows(IllegalArgumentException.class,()->{
      Validate.notNull(null);
      Validate.notNull(null,"null value");
      Validate.notNull(null,()->"null value");
    });
    Validate.notNull("");
  }

  @Test
  void notEmpty() {
    assertThrows(IllegalArgumentException.class,()->{
      Validate.notEmpty(List.of());
    });
    assertThrows(IllegalArgumentException.class,()->{
      Validate.notEmpty(List.of(),"empty list");
    });
    assertThrows(IllegalArgumentException.class,()->{
      Validate.notEmpty(List.of(),()->"empty list");
    });
    assertThrows(IllegalArgumentException.class,()->{
      Validate.notEmpty(new String[]{});
    });
    assertThrows(IllegalArgumentException.class,()->{
      Validate.notEmpty(new String[]{},"empty array");
    });
    assertThrows(IllegalArgumentException.class,()->{
      Validate.notEmpty(new String[]{},()->"empty array");
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