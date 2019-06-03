package org.blacksmith.commons.date;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

public class DateUtils {

  public static long daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
    return ChronoUnit.DAYS.between(startInclusive,endExclusive);
  }

  public static int dayOfYear(LocalDate date) {
    return date.getDayOfYear();
  }

  public static boolean isLastDayOfFebruary(LocalDate date) {
    return date.getMonthValue() == 2 && date.getDayOfMonth() == date.lengthOfMonth();
  }

  public static boolean isLastDayOfMonth(LocalDate date) {
    return date.getDayOfMonth()==date.lengthOfMonth();
  }

  public static boolean isLeapDayInPeriod(LocalDate startInclusive, LocalDate endExclusive) {
    LocalDate nextLeap = nextOrSameLeapDay(startInclusive);
    return nextLeap.isBefore(endExclusive);
  }

  /**
   * Finds the next leap day after the input date.
   *
   * @param input  the input date
   * @return the next leap day date
   */
  public static LocalDate nextLeapDay(LocalDate input) {
    //already a leap year
    if (input.isLeapYear()) {
      //january of leap year - return this year
      if (input.getMonthValue() < 2) {
        return LocalDate.of(input.getYear(), 2, 29);
      }
      //february before 29 - return this year
      else if (input.getMonthValue() == 2 && input.getDayOfMonth() < 29) {
        return LocalDate.of(input.getYear(), 2, 29);
      }
      else {
        //february 29 or after - return next leap year
        return ensureLeapDay(input.getYear() + 4);
      }
    }
    else {
      return ensureLeapDay(((input.getYear() / 4) * 4) + 4);
    }
  }

  /**
   * Finds the next leap day on or after the input date.
   * <p>
   * If the input date is February 29, the input date is returned unaltered.
   * Otherwise, the adjuster returns the next occurrence of February 29 after the input date.
   *
   * @param input  the input date
   * @return the next leap day date
   */
  public static LocalDate nextOrSameLeapDay(LocalDate input) {
    //already a leap year
    if (input.isLeapYear()) {
      //january of leap year - return this year
      if (input.getMonthValue() <= 2) {
        return LocalDate.of(input.getYear(), 2, 29);
      }
      else {
        return ensureLeapDay(input.getYear() + 4);
      }
    }
    else {
      return ensureLeapDay(((input.getYear() / 4) * 4) + 4);
    }
  }

  // handle 2100, which is not a leap year
  private static LocalDate ensureLeapDay(int possibleLeapYear) {
    if (Year.isLeap(possibleLeapYear)) {
      return LocalDate.of(possibleLeapYear, 2, 29);
    } else {
      return LocalDate.of(possibleLeapYear + 4, 2, 29);
    }
  }

  public static Stream<LocalDate> stream(LocalDate startInclusive, LocalDate endExclusive) {
    long numOfDaysBetween = ChronoUnit.DAYS.between(startInclusive,endExclusive);
    return Stream.iterate(startInclusive,date->date.plusDays(1))
        .limit(numOfDaysBetween);
  }
}
