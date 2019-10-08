package org.blacksmith.commons.datetime;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

public class DateUtils {
  private DateUtils() {}

  public static int daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
    return Math.toIntExact(ChronoUnit.DAYS.between(startInclusive,endExclusive));
  }

  public static int yearsBetween(LocalDate startInclusive, LocalDate endExclusive) {
    return Math.toIntExact(ChronoUnit.YEARS.between(startInclusive,endExclusive));
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
   * Finds the next leap day after the input datetime.
   *
   * @param input  the input datetime
   * @return the next leap day datetime
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
   * Finds the next leap day on or after the input datetime.
   * <p>
   * If the input datetime is February 29, the input datetime is returned unaltered.
   * Otherwise, the adjuster returns the next occurrence of February 29 after the input datetime.
   *
   * @param input  the input datetime
   * @return the next leap day datetime
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

  public static Stream<LocalDate> stream(DateRange range) {
    long numOfDaysBetween = ChronoUnit.DAYS.between(range.getLowerInclusive(),range.getUpperInclusive())+1;
    return Stream.iterate(range.getLowerInclusive(),date->date.plusDays(1))
        .limit(numOfDaysBetween);
  }
}
