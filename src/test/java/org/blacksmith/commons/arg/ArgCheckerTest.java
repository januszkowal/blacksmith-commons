package org.blacksmith.commons.arg;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class ArgCheckerTest {

  @SuppressWarnings("ObviousNullCheck")
  @Test()
  void testNotNull() {
    assertThrows(IllegalArgumentException.class,()->{
      ArgChecker.notNull(null);
      ArgChecker.notNull(null,"null value");
      ArgChecker.notNull(null,()->"null value");
    });
    ArgChecker.notNull("");
    ArgChecker.notNull(Integer.parseInt("3"));
  }

  @SuppressWarnings("ObviousNullCheck")
  @Test
  void notEmptyArray() {
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(new String[]{}));
    ArgChecker.notEmpty(new String[]{""});
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(new String[]{},"empty array"));
    ArgChecker.notEmpty(new String[]{""},"empty array");
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(new String[]{},()->"empty array"));
    ArgChecker.notEmpty(new String[]{""},()->"empty array");
  }

  @Test
  void notEmptyList() {
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(List.of()));
    ArgChecker.notEmpty(List.of(""));
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(List.of(),"empty list"));
    ArgChecker.notEmpty(List.of(""),"Empty list");
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(List.of(),()->"empty list"));
    ArgChecker.notEmpty(List.of(""),()->"Empty list");
  }

  @Test
  void inOrderOrEqual() {
    ArgChecker.inOrderOrEqual(LocalDate.parse("2019-01-01"),LocalDate.parse("2019-01-01"),"Dates must be in order");
    ArgChecker.inOrderOrEqual(LocalDate.parse("2019-01-01"),LocalDate.parse("2019-01-01"),()->"Dates must be in order");
    ArgChecker.inOrderOrEqual(LocalDate.parse("2019-01-01"),LocalDate.parse("2019-01-02"),"Dates must be in order");
    ArgChecker.inOrderOrEqual(LocalDate.parse("2019-01-01"),LocalDate.parse("2019-01-02"),()->"Dates must be in order");
    assertThrows(IllegalArgumentException.class,
        ()-> ArgChecker.inOrderOrEqual(LocalDate.parse("2019-01-02"),LocalDate.parse("2019-01-01"),"Dates must be in order"));
    assertThrows(IllegalArgumentException.class,
        ()-> ArgChecker.inOrderOrEqual(LocalDate.parse("2019-01-02"),LocalDate.parse("2019-01-01"),()->"Dates must be in order"));

  }

  @Test
  void inOrderNotEqual() {
    ArgChecker.inOrderNotEqual(LocalDate.parse("2019-01-01"),LocalDate.parse("2019-01-02"),"Dates must be in order");
    ArgChecker.inOrderNotEqual(LocalDate.parse("2019-01-01"),LocalDate.parse("2019-01-02"),()->"Dates must be in order");
    assertThrows(IllegalArgumentException.class,
        ()-> ArgChecker.inOrderNotEqual(LocalDate.parse("2019-01-01"),LocalDate.parse("2019-01-01"),"Dates must be in order"));
    assertThrows(IllegalArgumentException.class,
        ()-> ArgChecker.inOrderNotEqual(LocalDate.parse("2019-01-01"),LocalDate.parse("2019-01-01"),()->"Dates must be in order"));
    assertThrows(IllegalArgumentException.class,
        ()-> ArgChecker.inOrderNotEqual(LocalDate.parse("2019-01-02"),LocalDate.parse("2019-01-01"),"Dates must be in order"));
    assertThrows(IllegalArgumentException.class,
        ()-> ArgChecker.inOrderNotEqual(LocalDate.parse("2019-01-02"),LocalDate.parse("2019-01-01"),()->"Dates must be in order"));
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