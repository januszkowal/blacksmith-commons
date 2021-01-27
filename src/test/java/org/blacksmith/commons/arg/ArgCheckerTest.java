package org.blacksmith.commons.arg;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.MessageFormat;
import java.util.List;
import org.blacksmith.commons.arg.ArgChecker.String1Arg1ParSupplier;
import org.blacksmith.commons.arg.ArgChecker.String1Arg2ParSupplier;
import org.blacksmith.commons.arg.ArgChecker.String2ParSupplier;
import org.blacksmith.commons.arg.ArgChecker.StringSupplier;
import org.junit.jupiter.api.Test;

@SuppressWarnings("SpellCheckingInspection")
class ArgCheckerTest {


  @SuppressWarnings("ObviousNullCheck")
  @Test()
  void testNotNull() {
    assertThrows(IllegalArgumentException.class, () -> {
      ArgChecker.notNull(null);
      ArgChecker.notNull(null, "null value");
      ArgChecker.notNull(null, () -> "null value");
    });
    ArgChecker.notNull("");
  }

  @Test
  void notEmptyList() {
    ArgChecker.notEmpty(List.of(""));
    ArgChecker.notEmpty(List.of(""), "empty list");
    ArgChecker.notEmpty(List.of(""), () -> "empty list");
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.notEmpty(List.of()));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.notEmpty(List.of(), "empty list"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.notEmpty(List.of(), () -> "empty list"));
  }

  @SuppressWarnings("ObviousNullCheck")
  @Test
  void notEmptyArray() {
    ArgChecker.notEmpty(new String[]{""});
    ArgChecker.notEmpty(new String[]{""}, "empty array");
    ArgChecker.notEmpty(new String[]{""}, () -> "empty array");
    assertThrows(IllegalArgumentException.class,
        () -> ArgChecker.notEmpty(new String[]{}));
    //noinspection ObviousNullCheck
    assertThrows(IllegalArgumentException.class,
        () -> ArgChecker.notEmpty(new String[]{}, "empty array"));
    assertThrows(IllegalArgumentException.class,
        () -> ArgChecker.notEmpty(new String[]{}, () -> "empty array"));
  }

  @Test
  void notEmptyString() {
    ArgChecker.notEmpty("x");
    ArgChecker.notEmpty("x", "empty array");
    ArgChecker.notEmpty("x", () -> "empty array");
    assertThrows(IllegalArgumentException.class,
        () -> ArgChecker.notEmpty(""));
    assertThrows(IllegalArgumentException.class,
        () -> ArgChecker.notEmpty("", "empty astring"));
    assertThrows(IllegalArgumentException.class,
        () -> ArgChecker.notEmpty("", () -> "empty string"));
  }

  @Test
  public void checkStringLength1() {
    ArgChecker.checkStringLength("abcde", 5);
    ArgChecker.checkStringLength("abcde", 5, "Invalid length");
    ArgChecker.checkStringLength("abcde", 5, () -> "Invalid length");
    String1Arg1ParSupplier<Integer> stringPar1Supplier = (s, l) -> MessageFormat
        .format("String {0} should have length {1}", s, l);

    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength(null, 5));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength(null, 5, "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength(null, 5, () -> "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength(null, 5, stringPar1Supplier));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 5));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 5, "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 5, () -> "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 5, stringPar1Supplier));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("abcd", 5));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("abcd", 5, "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("abcd", 5, () -> "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("abcd", 5, stringPar1Supplier));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("abcdef", 5));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("abcdef", 5, "Invalid length"));
    assertThrows(IllegalArgumentException.class,
        () -> ArgChecker.checkStringLength("abcdef", 5, () -> "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("abcdef", 5, stringPar1Supplier));
  }

  @Test
  public void checkStringLength2() {
    ArgChecker.checkStringLength("abcde", 1, 5);
    ArgChecker.checkStringLength("abcde", 1, 5, "Invalid length");
    ArgChecker.checkStringLength("abcde", 1, 5, () -> "Invalid length");
    String1Arg2ParSupplier<Integer> stringPar2Supplier = (s, l1, l2) -> MessageFormat
        .format("String {0} should have length between {1} and {2}", s, l1, l2);

    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength(null, 1, 5));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength(null, 1, 5, "Invalid length"));
    assertThrows(IllegalArgumentException.class,
        () -> ArgChecker.checkStringLength(null, 1, 5, () -> "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength(null, 1, 5, stringPar2Supplier));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 1, 5));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 1, 5, "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 1, 5, () -> "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 1, 5, stringPar2Supplier));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 1, 5));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 1, 5, "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 1, 5, () -> "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("", 1, 5, stringPar2Supplier));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("a", 2, 5));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("a", 2, 5, "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("a", 2, 5, () -> "Invalid length"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("a", 2, 5, stringPar2Supplier));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("abcdef", 1, 5));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkStringLength("abcdef", 1, 5, "Invalid length"));
    assertThrows(IllegalArgumentException.class,
        () -> ArgChecker.checkStringLength("abcdef", 1, 5, () -> "Invalid length"));
    assertThrows(IllegalArgumentException.class,
        () -> ArgChecker.checkStringLength("abcdef", 1, 5, stringPar2Supplier));
  }

  @Test
  void isTrue() {
    ArgChecker.isTrue(true);
    ArgChecker.isTrue(true, "is false");
    ArgChecker.isTrue(true, () -> "is false");
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.isTrue(false));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.isTrue(false, "is false"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.isTrue(false, () -> "is false"));
  }

  @Test
  void isFalse() {
    ArgChecker.isFalse(false);
    ArgChecker.isFalse(false, "is true");
    ArgChecker.isFalse(false, () -> "is true");
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.isFalse(true));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.isFalse(true, "is false"));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.isFalse(true, () -> "is false"));
  }

  @Test
  public void inOrderOrEqual() {
    String message = "Arguments must be in order";
    StringSupplier ssu1 = () -> message;
    String2ParSupplier<Integer> ssu2 = (a1, a2) -> MessageFormat.format("Arguments {0}, {1} must be in order", a1, a2);
    Integer a1 = 1;
    Integer a2 = 3;
    ArgChecker.inOrderOrEqual(a1, a1);
    ArgChecker.inOrderOrEqual(a1, a1, message);
    ArgChecker.inOrderOrEqual(a1, a1, ssu1);
    ArgChecker.inOrderOrEqual(a1, a1, ssu2);

    ArgChecker.inOrderOrEqual(a1, a2);
    ArgChecker.inOrderOrEqual(a1, a2, message);
    ArgChecker.inOrderOrEqual(a1, a2, ssu1);
    ArgChecker.inOrderOrEqual(a1, a2, ssu2);
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderOrEqual(a2, a1));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderOrEqual(a2, a1, message));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderOrEqual(a2, a1, ssu1));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderOrEqual(a2, a1, ssu2));
  }

  @Test
  public void inOrderNotEqual() {
    String message = "Arguments must be in order";
    StringSupplier ssu1 = () -> message;
    String2ParSupplier<Integer> ssu2 = (a1, a2) -> MessageFormat.format("Arguments {0}, {1} must be in order", a1, a2);
    Integer a1 = 1;
    Integer a2 = 3;
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderNotEqual(a1, a1));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderNotEqual(a1, a1, message));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderNotEqual(a1, a1, ssu1));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderNotEqual(a1, a1, ssu2));

    ArgChecker.inOrderNotEqual(a1, a2);
    ArgChecker.inOrderNotEqual(a1, a2, message);
    ArgChecker.inOrderNotEqual(a1, a2, ssu1);
    ArgChecker.inOrderNotEqual(a1, a2, ssu2);
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderNotEqual(a2, a1));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderNotEqual(a2, a1, message));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderNotEqual(a2, a1, ssu1));
    assertThrows(IllegalArgumentException.class, () -> ArgChecker.inOrderNotEqual(a2, a1, ssu2));
  }
}
