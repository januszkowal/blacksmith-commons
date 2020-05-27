package org.blacksmith.commons.arg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidateTest {


  @Test()
  void notNull() {
    assertThrows(IllegalArgumentException.class,()->{
      Validate.notNull(null);
      Validate.notNull(null,"null value");
      Validate.notNull(null,()->"null value");
    });
    Validate.notNull("");
  }

  @Test
  void testNotNull() {
  }

  @Test
  void testNotNull1() {
  }

  @Test
  void notEmpty() {
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