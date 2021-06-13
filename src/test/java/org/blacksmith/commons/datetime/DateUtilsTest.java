package org.blacksmith.commons.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

class DateUtilsTest {

  @Test
  public void isValidDate() {
    assertTrue(DateUtils.isValidDate(2019, 1, 28));
    assertTrue(DateUtils.isValidDate(2019, 1, 29));
    assertTrue(DateUtils.isValidDate(2019, 1, 30));
    assertTrue(DateUtils.isValidDate(2019, 1, 31));
    assertFalse(DateUtils.isValidDate(2019, 1, 32));

    assertTrue(DateUtils.isValidDate(2019, 2, 28));
    assertFalse(DateUtils.isValidDate(2019, 2, 29));
    assertFalse(DateUtils.isValidDate(2019, 2, 30));

    assertTrue(DateUtils.isValidDate(2020, 2, 28));
    assertTrue(DateUtils.isValidDate(2020, 2, 29));
    assertFalse(DateUtils.isValidDate(2020, 2, 30));
  }

  @Test
  public void min() {
    assertEquals(LocalDate.parse("2020-01-01"),
        DateUtils.min(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02")));
    assertEquals(LocalDate.parse("2020-01-01"),
        DateUtils.min(LocalDate.parse("2020-01-02"), LocalDate.parse("2020-01-01")));
    assertEquals(LocalDate.parse("2003-01-10"),
        DateUtils.min(LocalDate.parse("2003-01-10"), LocalDate.parse("2003-07-15")));
    assertEquals(LocalDate.parse("2003-01-10"),
        DateUtils.min(LocalDate.parse("2003-07-15"), LocalDate.parse("2003-01-10")));
  }

  @Test
  public void max() {
    assertEquals(LocalDate.parse("2020-01-02"),
        DateUtils.max(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02")));
    assertEquals(LocalDate.parse("2020-01-02"),
        DateUtils.max(LocalDate.parse("2020-01-02"), LocalDate.parse("2020-01-01")));
    assertEquals(LocalDate.parse("2003-07-15"),
        DateUtils.max(LocalDate.parse("2003-01-10"), LocalDate.parse("2003-07-15")));
    assertEquals(LocalDate.parse("2003-07-15"),
        DateUtils.max(LocalDate.parse("2003-07-15"), LocalDate.parse("2003-01-10")));
  }

  @Test
  public void dayOfYear() {
    assertEquals(197, DateUtils.dayOfYear(LocalDate.parse("1997-07-16")));
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
    assertTrue(DateUtils.isLeapDayInPeriod(LocalDate.parse("2020-02-28"), LocalDate.parse("2020-03-01")));
    assertFalse(DateUtils.isLeapDayInPeriod(LocalDate.parse("2020-01-29"), LocalDate.parse("2020-02-29")));
    assertTrue(DateUtils.isLeapDayInPeriod(LocalDate.parse("2020-01-29"), LocalDate.parse("2020-03-01")));
    //
    assertFalse(DateUtils.isLeapDayInPeriodExcl(LocalDate.parse("2020-02-28"), LocalDate.parse("2020-02-28")));
    assertTrue(DateUtils.isLeapDayInPeriodExcl(LocalDate.parse("2020-02-28"), LocalDate.parse("2020-02-29")));
    assertTrue(DateUtils.isLeapDayInPeriodExcl(LocalDate.parse("2020-02-28"), LocalDate.parse("2020-03-01")));
  }

  @Test
  public void daysBetween() {
    assertEquals(0, DateUtils.daysBetween(LocalDate.parse("2019-01-01"), LocalDate.parse("2019-01-01")));
    assertEquals(0, DateUtils.daysBetween2(LocalDate.parse("2019-01-01"), LocalDate.parse("2019-01-01")));
    assertEquals(1, DateUtils.daysBetween(LocalDate.parse("2019-01-01"), LocalDate.parse("2019-01-02")));
    assertEquals(1, DateUtils.daysBetween2(LocalDate.parse("2019-01-01"), LocalDate.parse("2019-01-02")));
    assertEquals(365, DateUtils.daysBetween(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-01-01")));
    assertEquals(365, DateUtils.daysBetween2(LocalDate.parse("2019-01-01"), LocalDate.parse("2020-01-01")));
    assertEquals(0, DateUtils.daysBetween(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-01")));
    assertEquals(0, DateUtils.daysBetween2(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-01")));
    assertEquals(1, DateUtils.daysBetween(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02")));
    assertEquals(1, DateUtils.daysBetween2(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-02")));
    assertEquals(366, DateUtils.daysBetween(LocalDate.parse("2020-01-01"), LocalDate.parse("2021-01-01")));
    assertEquals(366, DateUtils.daysBetween2(LocalDate.parse("2020-01-01"), LocalDate.parse("2021-01-01")));
  }

  @Test
  public void nextLeapDay() {
    LocalDate d01 = LocalDate.parse("2019-01-14");
    LocalDate d02 = LocalDate.parse("2019-02-01");
    LocalDate d03 = LocalDate.parse("2019-02-28");
    LocalDate d04 = LocalDate.parse("2019-03-01");
    LocalDate d05 = LocalDate.parse("2019-03-02");
    LocalDate d11 = LocalDate.parse("2020-01-14");
    LocalDate d12 = LocalDate.parse("2020-02-01");
    LocalDate d13 = LocalDate.parse("2020-02-28");
    LocalDate d14 = LocalDate.parse("2020-02-29");
    LocalDate d15 = LocalDate.parse("2020-03-01");
    LocalDate d16 = LocalDate.parse("2020-03-02");
    LocalDate d21 = LocalDate.parse("2021-01-14");
    LocalDate d22 = LocalDate.parse("2021-02-01");
    LocalDate d23 = LocalDate.parse("2021-02-28");
    LocalDate d24 = LocalDate.parse("2021-03-01");
    LocalDate d25 = LocalDate.parse("2021-03-02");
    LocalDate leap1 = LocalDate.parse("2020-02-29");
    LocalDate leap2 = LocalDate.parse("2024-02-29");
    assertEquals(leap1, DateUtils.nextLeapDay(d01));
    assertEquals(leap1, DateUtils.nextLeapDay(d02));
    assertEquals(leap1, DateUtils.nextLeapDay(d03));
    assertEquals(leap1, DateUtils.nextLeapDay(d04));
    assertEquals(leap1, DateUtils.nextLeapDay(d05));
    assertEquals(leap1, DateUtils.nextOrSameLeapDay(d01));
    assertEquals(leap1, DateUtils.nextOrSameLeapDay(d02));
    assertEquals(leap1, DateUtils.nextOrSameLeapDay(d03));
    assertEquals(leap1, DateUtils.nextOrSameLeapDay(d04));
    assertEquals(leap1, DateUtils.nextOrSameLeapDay(d05));

    //
    assertEquals(leap1, DateUtils.nextLeapDay(d11));
    assertEquals(leap1, DateUtils.nextLeapDay(d12));
    assertEquals(leap1, DateUtils.nextLeapDay(d13));
    assertEquals(leap2, DateUtils.nextLeapDay(d14));
    assertEquals(leap2, DateUtils.nextLeapDay(d15));
    assertEquals(leap2, DateUtils.nextLeapDay(d16));
    assertEquals(leap1, DateUtils.nextLeapDay(d11));
    assertEquals(leap1, DateUtils.nextOrSameLeapDay(d12));
    assertEquals(leap1, DateUtils.nextOrSameLeapDay(d13));
    assertEquals(leap1, DateUtils.nextOrSameLeapDay(d14));
    assertEquals(leap2, DateUtils.nextOrSameLeapDay(d15));
    assertEquals(leap2, DateUtils.nextOrSameLeapDay(d16));
    //
    assertEquals(leap2, DateUtils.nextLeapDay(d21));
    assertEquals(leap2, DateUtils.nextLeapDay(d22));
    assertEquals(leap2, DateUtils.nextLeapDay(d23));
    assertEquals(leap2, DateUtils.nextLeapDay(d24));
    assertEquals(leap2, DateUtils.nextLeapDay(d25));
    assertEquals(leap2, DateUtils.nextOrSameLeapDay(d21));
    assertEquals(leap2, DateUtils.nextOrSameLeapDay(d22));
    assertEquals(leap2, DateUtils.nextOrSameLeapDay(d23));
    assertEquals(leap2, DateUtils.nextOrSameLeapDay(d24));
    assertEquals(leap2, DateUtils.nextOrSameLeapDay(d25));
  }

  @Test
  public void numberOfLeapDays() {
    assertEquals(0, DateUtils.numberOfLeapDaysCC(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-01-01")));
    assertEquals(0, DateUtils.numberOfLeapDaysCC(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-02-28")));
    assertEquals(0, DateUtils.numberOfLeapDays(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-02-29")));
    //
    assertEquals(1, DateUtils.numberOfLeapDaysCC(LocalDate.parse("2020-02-28"), LocalDate.parse("2020-02-29")));
    assertEquals(1, DateUtils.numberOfLeapDays(LocalDate.parse("2020-02-28"), LocalDate.parse("2020-03-01")));
    assertEquals(1, DateUtils.numberOfLeapDaysCC(LocalDate.parse("2020-02-29"), LocalDate.parse("2020-02-29")));
    assertEquals(1, DateUtils.numberOfLeapDays(LocalDate.parse("2020-02-29"), LocalDate.parse("2020-03-01")));
    assertEquals(1, DateUtils.numberOfLeapDaysCC(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-02-29")));
    assertEquals(1, DateUtils.numberOfLeapDays(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-03-01")));
    assertEquals(1, DateUtils.numberOfLeapDaysCC(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-03-01")));
    assertEquals(1, DateUtils.numberOfLeapDays(LocalDate.parse("2020-01-01"), LocalDate.parse("2020-03-02")));
    assertEquals(1, DateUtils.numberOfLeapDaysCC(LocalDate.parse("2020-01-01"), LocalDate.parse("2024-02-28")));
    assertEquals(1, DateUtils.numberOfLeapDays(LocalDate.parse("2020-01-01"), LocalDate.parse("2024-02-29")));
    assertEquals(2, DateUtils.numberOfLeapDaysCC(LocalDate.parse("2020-01-01"), LocalDate.parse("2024-02-29")));
    assertEquals(2, DateUtils.numberOfLeapDays(LocalDate.parse("2020-01-01"), LocalDate.parse("2024-03-01")));
    assertEquals(2, DateUtils.numberOfLeapDaysCC(LocalDate.parse("2020-01-01"), LocalDate.parse("2024-03-01")));
    assertEquals(2, DateUtils.numberOfLeapDays(LocalDate.parse("2020-01-01"), LocalDate.parse("2024-03-02")));
  }

  @Test
  public void yearsBetween() {
    assertEquals(0, DateUtils.yearsBetween(LocalDate.parse("2020-01-10"), LocalDate.parse("2021-01-09")));
    assertEquals(1, DateUtils.yearsBetween(LocalDate.parse("2020-01-10"), LocalDate.parse("2021-01-10")));
    assertEquals(0, DateUtils.yearsBetween(LocalDate.parse("2020-02-29"), LocalDate.parse("2021-02-28")));
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

  @Test
  public void streamOfDateRange() {
    assertEquals(3,
        DateUtils.streamOfDates(DateRange.closed(LocalDate.parse("2020-06-01"), LocalDate.parse("2020-06-03")))
            .count());
  }

  @Test
  public void toPeriod() {
    LocalDate d1 = LocalDate.of(2020, 4, 30);
    LocalDate d2 = d1.plusMonths(2).minusDays(1);
    assertEquals("P1M30D", DateUtils.periodBetween(d1, d2).toString());
  }
}
