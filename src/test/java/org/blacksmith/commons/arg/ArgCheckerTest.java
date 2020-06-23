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

  @Test
  public void checkStringLength() {
    ArgChecker.checkStringLength("abcde",5);
    ArgChecker.checkStringLength("abcde",5,"Invalid length");
    ArgChecker.checkStringLength("abcde",5,()->"Invalid length");
    assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength(null,5));
    assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength(null,5,"Invalid length"));
    assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength(null,5,()->"Invalid length"));
    assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength("",5));
    assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength("",5,"Invalid length"));
    assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength("",5,()->"Invalid length"));
    assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength("abcd",5));
    assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength("abcd",5,"Invalid length"));
    assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength("abcd",5,()->"Invalid length"));assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength("abcdef",5));
    assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength("abcdef",5,"Invalid length"));
    assertThrows(IllegalArgumentException.class,()->ArgChecker.checkStringLength("abcdef",5,()->"Invalid length"));
  }
}