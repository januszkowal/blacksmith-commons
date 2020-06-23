package org.blacksmith.commons.datetime;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DateUtilsTest {
  @Test
  public void isValidDate() {
    assertTrue(DateUtils.isValidDate(2019,1,28));
    assertTrue(DateUtils.isValidDate(2019,1,29));
    assertTrue(DateUtils.isValidDate(2019,1,30));
    assertTrue(DateUtils.isValidDate(2019,1,31));
    assertFalse(DateUtils.isValidDate(2019,1,32));

    assertTrue(DateUtils.isValidDate(2019,2,28));
    assertFalse(DateUtils.isValidDate(2019,2,29));
    assertFalse(DateUtils.isValidDate(2019,2,30));

    assertTrue(DateUtils.isValidDate(2020,2,28));
    assertTrue(DateUtils.isValidDate(2020,2,29));
    assertFalse(DateUtils.isValidDate(2020,2,30));
  }
  @Test
  public void min() {
    assertEquals(LocalDate.parse("2020-01-01"),DateUtils.min(LocalDate.parse("2020-01-01"),LocalDate.parse("2020-01-02")));
    assertEquals(LocalDate.parse("2020-01-01"),DateUtils.min(LocalDate.parse("2020-01-02"),LocalDate.parse("2020-01-01")));
    assertEquals(LocalDate.parse("2003-01-10"),DateUtils.min(LocalDate.parse("2003-01-10"),LocalDate.parse("2003-07-15")));
    assertEquals(LocalDate.parse("2003-01-10"),DateUtils.min(LocalDate.parse("2003-07-15"),LocalDate.parse("2003-01-10")));
  }
  @Test
  public void max() {
    assertEquals(LocalDate.parse("2020-01-02"),DateUtils.max(LocalDate.parse("2020-01-01"),LocalDate.parse("2020-01-02")));
    assertEquals(LocalDate.parse("2020-01-02"),DateUtils.max(LocalDate.parse("2020-01-02"),LocalDate.parse("2020-01-01")));
    assertEquals(LocalDate.parse("2003-07-15"),DateUtils.max(LocalDate.parse("2003-01-10"),LocalDate.parse("2003-07-15")));
    assertEquals(LocalDate.parse("2003-07-15"),DateUtils.max(LocalDate.parse("2003-07-15"),LocalDate.parse("2003-01-10")));
  }
}