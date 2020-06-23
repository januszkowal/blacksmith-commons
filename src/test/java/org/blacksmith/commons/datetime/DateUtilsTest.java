package org.blacksmith.commons.datetime;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DateUtilsTest {

  @Test
  void min() {
    assertEquals(LocalDate.parse("2020-01-01"),DateUtils.min(LocalDate.parse("2020-01-01"),LocalDate.parse("2020-01-01")));
    assertEquals(LocalDate.parse("2020-01-01"),DateUtils.min(LocalDate.parse("2020-01-01"),LocalDate.parse("2020-01-02")));
  }

  @Test
  void max() {
    assertEquals(LocalDate.parse("2020-01-01"),DateUtils.max(LocalDate.parse("2020-01-01"),LocalDate.parse("2020-01-01")));
    assertEquals(LocalDate.parse("2020-01-02"),DateUtils.max(LocalDate.parse("2020-01-01"),LocalDate.parse("2020-01-02")));
  }

  @Test
  void isValidDate() {
    assertTrue(DateUtils.isValidDate(2019, 1, 1));
    assertTrue(DateUtils.isValidDate(2019, 1,30));
    assertTrue(DateUtils.isValidDate(2019, 1,31));
    assertFalse(DateUtils.isValidDate(2019, 1,32));
    //
    assertTrue(DateUtils.isValidDate(2019, 2,28));
    assertFalse(DateUtils.isValidDate(2019, 2,29));
    assertFalse(DateUtils.isValidDate(2019, 2,30));
    assertFalse(DateUtils.isValidDate(2019, 2,31));
    assertFalse(DateUtils.isValidDate(2019, 2,32));
    //
    assertTrue(DateUtils.isValidDate(2020, 2,28));
    assertTrue(DateUtils.isValidDate(2020, 2,29));
    assertFalse(DateUtils.isValidDate(2020, 2,30));
    assertFalse(DateUtils.isValidDate(2020, 2,31));
    assertFalse(DateUtils.isValidDate(2020, 2,32));
    //
    assertTrue(DateUtils.isValidDate(2019, 3,28));
    assertTrue(DateUtils.isValidDate(2019, 3,29));
    assertTrue(DateUtils.isValidDate(2019, 3,30));
    assertTrue(DateUtils.isValidDate(2019, 3,31));
    assertFalse(DateUtils.isValidDate(2019, 3,32));
    //
    assertTrue(DateUtils.isValidDate(2019, 4,28));
    assertTrue(DateUtils.isValidDate(2019, 4,29));
    assertTrue(DateUtils.isValidDate(2019, 4,30));
    assertFalse(DateUtils.isValidDate(2019, 4,31));
    assertFalse(DateUtils.isValidDate(2019, 4,32));
  }

  @Test
  public void dayOfYear() {
    assertEquals(1,DateUtils.dayOfYear(LocalDate.of(2020,1,1)));
    assertEquals(10,DateUtils.dayOfYear(LocalDate.of(2020,1,10)));
    assertEquals(75,DateUtils.dayOfYear(LocalDate.of(2020,3,15)));
  }

  @Test
  public void isLastDayOfMonth() {
    assertFalse(DateUtils.isLastDayOfMonth(LocalDate.of(2019,2,27)));
    assertTrue(DateUtils.isLastDayOfMonth(LocalDate.of(2019,2,28)));

    assertFalse(DateUtils.isLastDayOfMonth(LocalDate.of(2020,1,1)));
    assertFalse(DateUtils.isLastDayOfMonth(LocalDate.of(2020,1,28)));
    assertFalse(DateUtils.isLastDayOfMonth(LocalDate.of(2020,1,28)));
    assertFalse(DateUtils.isLastDayOfMonth(LocalDate.of(2020,1,29)));
    assertFalse(DateUtils.isLastDayOfMonth(LocalDate.of(2020,1,30)));
    assertTrue(DateUtils.isLastDayOfMonth(LocalDate.of(2020,1,31)));

    assertFalse(DateUtils.isLastDayOfMonth(LocalDate.of(2020,2,28)));
    assertFalse(DateUtils.isLastDayOfMonth(LocalDate.of(2020,2,28)));
    assertTrue(DateUtils.isLastDayOfMonth(LocalDate.of(2020,2,29)));
  }
}