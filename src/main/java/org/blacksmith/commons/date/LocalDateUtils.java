package org.blacksmith.commons.date;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LocalDateUtils {
  public static LocalDate plusDays(LocalDate date, int daysToAdd) {
    if (daysToAdd == 0) {
      return date;
    }
    // add the days to the current day-of-month
    // if it is guaranteed to be in this month or the next month then fast path it
    // (59th Jan is 28th Feb, 59th Feb is 31st Mar)
    long dom = date.getDayOfMonth() + daysToAdd;
    if (dom > 0 && dom <= 59) {
      int monthLen = date.lengthOfMonth();
      int month = date.getMonthValue();
      int year = date.getYear();
      if (dom <= monthLen) {
        return LocalDate.of(year, month, (int) dom);
      } else if (month < 12) {
        return LocalDate.of(year, month + 1, (int) (dom - monthLen));
      } else {
        return LocalDate.of(year + 1, 1, (int) (dom - monthLen));
      }
    }
    long mjDay = Math.addExact(date.toEpochDay(), daysToAdd);
    return LocalDate.ofEpochDay(mjDay);
  }
  public static Stream<LocalDate> stream(LocalDate startInclusive, LocalDate endExclusive) {
    Iterator<LocalDate> it = new Iterator<LocalDate>() {
      private LocalDate current = startInclusive;

      @Override
      public LocalDate next() {
        LocalDate result = current;
        current = plusDays(current, 1);
        return result;
      }

      @Override
      public boolean hasNext() {
        return current.isBefore(endExclusive);
      }
    };
    long count = endExclusive.toEpochDay() - startInclusive.toEpochDay() + 1;
    Spliterator<LocalDate> spliterator = Spliterators.spliterator(it, count,
        Spliterator.IMMUTABLE | Spliterator.NONNULL |
            Spliterator.DISTINCT | Spliterator.ORDERED | Spliterator.SORTED |
            Spliterator.SIZED | Spliterator.SUBSIZED);
    return StreamSupport.stream(spliterator, false);
  }}
