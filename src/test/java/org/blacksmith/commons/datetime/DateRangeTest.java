package org.blacksmith.commons.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DateRangeTest {

  final LocalDate d1 = LocalDate.parse("2020-06-01");
  final LocalDate d2 = LocalDate.parse("2020-06-10");

  @Test
  void closed() {
    DateRange range = DateRange.closed(d1, d2);
    assertEquals(d1, range.getLowerInclusive());
    assertEquals(d2, range.getUpperInclusive());
    assertEquals(range.numberOfDays(), 10);
    range = DateRange.range(d1, BoundType.CLOSED, d2, BoundType.CLOSED);
    assertEquals(d1, range.getLowerInclusive());
    assertEquals(d2, range.getUpperInclusive());
    assertEquals(range.numberOfDays(), 10);
  }

  @Test
  void open() {
    DateRange range = DateRange.open(d1, d2);
    assertEquals(d1.plusDays(1), range.getLowerInclusive());
    assertEquals(d2.minusDays(1), range.getUpperInclusive());
    assertEquals(range.numberOfDays(), 8);
    range = DateRange.range(d1, BoundType.OPEN, d2, BoundType.OPEN);
    assertEquals(d1.plusDays(1), range.getLowerInclusive());
    assertEquals(d2.minusDays(1), range.getUpperInclusive());
    assertEquals(range.numberOfDays(), 8);
  }

  @Test
  void openClosed() {
    DateRange range = DateRange.openClosed(d1, d2);
    assertEquals(d1.plusDays(1), range.getLowerInclusive());
    assertEquals(d2, range.getUpperInclusive());
    assertEquals(range.numberOfDays(), 9);
    range = DateRange.range(d1, BoundType.OPEN, d2, BoundType.CLOSED);
    assertEquals(d1.plusDays(1), range.getLowerInclusive());
    assertEquals(d2, range.getUpperInclusive());
    assertEquals(range.numberOfDays(), 9);
  }

  @Test
  void closedOpen() {
    DateRange range = DateRange.closedOpen(d1, d2);
    assertEquals(d1, range.getLowerInclusive());
    assertEquals(d2.minusDays(1), range.getUpperInclusive());
    assertEquals(range.numberOfDays(), 9);
    range = DateRange.range(d1, BoundType.CLOSED, d2, BoundType.OPEN);
    assertEquals(d1, range.getLowerInclusive());
    assertEquals(d2.minusDays(1), range.getUpperInclusive());
    assertEquals(range.numberOfDays(), 9);
  }

  @Test
  void borders() {
    DateRange range = DateRange.closed(d1, d2);
    assertEquals(d1, range.getLowerInclusive());
    assertEquals(d1.minusDays(1), range.getLowerExclusive());
    assertEquals(d2, range.getUpperInclusive());
    assertEquals(d2.plusDays(1), range.getUpperExclusive());
    assertTrue(range.isRangeAfter(d1.minusDays(1)));
    assertFalse(range.isRangeAfter(d1));
    assertTrue(range.isRangeBefore(d2.plusDays(1)));
    assertFalse(range.isRangeBefore(d2));
    assertTrue(range.isDateBeforeRange(d1.minusDays(1)));
    assertFalse(range.isDateBeforeRange(d1));
    assertTrue(range.isDateAfterRange(d2.plusDays(1)));
    assertFalse(range.isDateAfterRange(d2));
    assertTrue(range.contains(d1));
    assertTrue(range.contains(d1.plusDays(1)));
    assertFalse(range.contains(d1.minusDays(1)));
    assertTrue(range.contains(d2));
    assertTrue(range.contains(d2.minusDays(1)));
    assertFalse(range.contains(d2.plusDays(1)));
  }
}
