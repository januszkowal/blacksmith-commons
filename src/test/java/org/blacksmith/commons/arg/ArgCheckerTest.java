package org.blacksmith.commons.arg;

import static org.junit.jupiter.api.Assertions.*;

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
  }

  @Test
  void notEmptyList() {
    ArgChecker.notEmpty(List.of(""));
    ArgChecker.notEmpty(List.of(""),"empty list");
    ArgChecker.notEmpty(List.of(""),()->"empty list");
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(List.of()));
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(List.of(),"empty list"));
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(List.of(),()->"empty list"));
  }

  @SuppressWarnings("ObviousNullCheck")
  @Test
  void notEmptyArray() {
    ArgChecker.notEmpty(new String[]{""});
    ArgChecker.notEmpty(new String[]{""},"empty array");
    ArgChecker.notEmpty(new String[]{""},()->"empty array");
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(new String[]{}));
    //noinspection ObviousNullCheck
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(new String[]{},"empty array"));
    assertThrows(IllegalArgumentException.class,()-> ArgChecker.notEmpty(new String[]{},()->"empty array"));
  }
}