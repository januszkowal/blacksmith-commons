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
  @Test
  public void dayOfYear() {
    assertEquals(197,DateUtils.dayOfYear(LocalDate.parse("1997-07-16")));
  }

  @Test
  public void isLastDayOfFebruary() {
    assertFalse(DateUtils.isLastDayOfFebruary(LocalDate.parse("2020-01-30")));
    assertFalse(DateUtils.isLastDayOfFebruary(LocalDate.parse("2020-01-31")));
    assertFalse(DateUtils.isLastDayOfFebruary(LocalDate.parse("2020-02-28")));
    assertTrue(DateUtils.isLastDayOfFebruary(LocalDate.parse("2020-02-29")));
    assertFalse(DateUtils.isLastDayOfFebruary(LocalDate.parse("2020-02-27")));
    assertTrue(DateUtils.isLastDayOfFebruary(LocalDate.parse("2019-02-28")));
  }

  @Test
  public void isLastDayOfMonth() {
    assertFalse(DateUtils.isLastDayOfMonth(LocalDate.parse("2020-01-30")));
    assertTrue(DateUtils.isLastDayOfMonth(LocalDate.parse("2020-01-31")));
    assertFalse(DateUtils.isLastDayOfMonth(LocalDate.parse("2020-02-28")));
    assertTrue(DateUtils.isLastDayOfMonth(LocalDate.parse("2020-02-29")));
    assertFalse(DateUtils.isLastDayOfMonth(LocalDate.parse("2020-02-27")));
    assertTrue(DateUtils.isLastDayOfMonth(LocalDate.parse("2019-02-28")));
  }

  @Test
  public void isLeapDayInPeriod() {
    assertTrue(DateUtils.isLeapDayInPeriod(LocalDate.parse("2020-02-28"),LocalDate.parse("2020-03-01")));
    assertFalse(DateUtils.isLeapDayInPeriod(LocalDate.parse("2020-01-29"),LocalDate.parse("2020-02-29")));
    assertTrue(DateUtils.isLeapDayInPeriod(LocalDate.parse("2020-01-29"),LocalDate.parse("2020-03-01")));
    //
    assertFalse(DateUtils.isLeapDayInPeriodCC(LocalDate.parse("2020-02-28"),LocalDate.parse("2020-02-28")));
    assertTrue(DateUtils.isLeapDayInPeriodCC(LocalDate.parse("2020-02-28"),LocalDate.parse("2020-02-29")));
    assertTrue(DateUtils.isLeapDayInPeriodCC(LocalDate.parse("2020-02-28"),LocalDate.parse("2020-03-01")));
  }

  @Test
  public void yearsBetween() {
    assertEquals(0,DateUtils.yearsBetween(LocalDate.parse("2020-01-10"),LocalDate.parse("2021-01-09")));
    assertEquals(1,DateUtils.yearsBetween(LocalDate.parse("2020-01-10"),LocalDate.parse("2021-01-10")));
    assertEquals(0,DateUtils.yearsBetween(LocalDate.parse("2020-02-29"),LocalDate.parse("2021-02-28")));
  }

  @Test
  public void isLeapDay() {
    assertFalse(DateUtils.isLeapDay(LocalDate.parse("2019-02-27")));
    assertFalse(DateUtils.isLeapDay(LocalDate.parse("2019-02-28")));
    assertFalse(DateUtils.isLeapDay(LocalDate.parse("2019-03-30")));
    assertFalse(DateUtils.isLeapDay(LocalDate.parse("2019-03-31")));
    //
    assertFalse(DateUtils.isLeapDay(LocalDate.parse("2020-02-28")));
    assertTrue(DateUtils.isLeapDay(LocalDate.parse("2020-02-29")));
    assertFalse(DateUtils.isLeapDay(LocalDate.parse("2020-03-30")));
    assertFalse(DateUtils.isLeapDay(LocalDate.parse("2020-03-31")));
  }
}