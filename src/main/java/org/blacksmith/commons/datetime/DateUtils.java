package org.blacksmith.commons.datetime;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;
/*
  Methods designed to use for financial periods calculations
  Methods with date range as parameters:
  -  most methods with use closedOpen convention (inclusive,exclusive)
  -  methods *CC use closedClosed convention (inclusive, exclusive)
* */
public class DateUtils {
  private DateUtils() {}

  public static int daysBetween2(LocalDate startInclusive, LocalDate endExclusive) {
    return Math.toIntExact(ChronoUnit.DAYS.between(startInclusive,endExclusive));
  }

  public static int daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
    return Math.toIntExact(endExclusive.toEpochDay()-startInclusive.toEpochDay());
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

  public static boolean isLeapDayInPeriodCC(LocalDate startInclusive, LocalDate endInclusive) {
    LocalDate nextLeap = nextOrSameLeapDay(startInclusive);
    return nextLeap.compareTo(endInclusive)<=0;
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
        return nextLeapDay(input.getYear());
      }
    }
    else {
      return nextLeapDay(input.getYear());
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
        return nextLeapDay(input.getYear());
      }
    }
    else {
      return nextLeapDay(input.getYear());
    }
  }

  public static boolean isLeapDay(LocalDate input) {
    return input.getMonthValue()==2 && input.getDayOfMonth()==29;
  }

  /**
   * Calculates number of leap days in period
   * @param startInclusive period start date
   * @param endExclusive period start date
   * @return the true if period contains leap year
   */
  public static int numberOfLeapDays(LocalDate startInclusive, LocalDate endExclusive) {
    return numberOfLeapDaysCC(startInclusive,endExclusive.minusDays(1));
  }

  /**
   * Calculates number of leap days in period
   * @param startInclusive period start date
   * @param endInclusive period start date
   * @return the true if period contains leap year
   */
  public static int numberOfLeapDaysCC(LocalDate startInclusive, LocalDate endInclusive) {
    int count = 0;
    for (int y=startInclusive.getYear(); y<=endInclusive.getYear();y++) {
      if (Year.isLeap(y)) {
        if (startInclusive.isBefore(LocalDate.of(y,3,1)) &&
            endInclusive.isAfter(LocalDate.of(y,2,28))) {
          count++;
        }
      }
    }
    return count;
  }

  public static LocalDate nextLeapDay(int year) {
    int possibleLeapYear = year + 4 - year%4;
    if (Year.isLeap(possibleLeapYear)) {
      return LocalDate.of(possibleLeapYear, 2, 29);
    } else {
      return LocalDate.of(possibleLeapYear + 4, 2, 29);
    }
  }

  private static LocalDate ensureLeapDay(int possibleLeapYear) {
    if (Year.isLeap(possibleLeapYear)) {
      return LocalDate.of(possibleLeapYear, 2, 29);
    } else {
      return LocalDate.of(possibleLeapYear + 4, 2, 29);
    }
  }

  public static Stream<LocalDate> streamOfDates(DateRange range) {
    long numOfDaysBetween = range.numberOfDays();
    return Stream.iterate(range.getLowerInclusive(),date->date.plusDays(1))
        .limit(numOfDaysBetween);
  }

  public static LocalDate min(LocalDate d1, LocalDate d2) {
    return d1.compareTo(d2)<=0 ? d1 : d2;
  }

  public static LocalDate max(LocalDate d1, LocalDate d2) {
    return d1.compareTo(d2)>=0 ? d1 : d2;
  }

  public static boolean isValidDate(int year, int month, int day) {
    try {
      LocalDate.of(year,month,day);
      return true;
    }
    catch (DateTimeException e) {
      return false;
    }
  }
}
