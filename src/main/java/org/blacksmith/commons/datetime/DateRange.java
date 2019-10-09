package org.blacksmith.commons.datetime;

import java.time.LocalDate;
import java.time.Period;
import org.apache.commons.lang3.Validate;

/*
*
 * <blockquote>
 *
 * <table>
 * <caption>Range Types</caption>
 * <tr><th>Notation        <th>Definition               <th>Factory method
 * <tr><td>{@code (a..b)}  <td>{@code {x | a < x < b}}  <td>{@link DateRange#open open}
 * <tr><td>{@code [a..b]}  <td>{@code {x | a <= x <= b}}<td>{@link DateRange#closed closed}
 * <tr><td>{@code (a..b]}  <td>{@code {x | a < x <= b}} <td>{@link DateRange#openClosed openClosed}
 * <tr><td>{@code [a..b)}  <td>{@code {x | a <= x < b}} <td>{@link DateRange#closedOpen closedOpen}
 * <tr><td>{@code (a..+∞)} <td>{@code {x | x > a}}      <td>{@link DateRange#greaterThan greaterThan}
 * <tr><td>{@code [a..+∞)} <td>{@code {x | x >= a}}     <td>{@link DateRange#atLeast atLeast}
 * <tr><td>{@code (-∞..b)} <td>{@code {x | x < b}}      <td>{@link DateRange#lessThan lessThan}
 * <tr><td>{@code (-∞..b]} <td>{@code {x | x <= b}}     <td>{@link DateRange#atMost atMost}
 * <tr><td>{@code (-∞..+∞)}<td>{@code {x}}              <td>{@link DateRange#all all}
 * </table>
 *
 * </blockquote>
* */
public class DateRange {
  public static final String LOWER_DATE_MUST_BE_NOT_NULL = "Lower date must be not null";
  public static final String UPPER_DATE_MUST_BE_NOT_NULL = "Upper date must be not null";
  private LocalDate lowerInclusive;
  private LocalDate upperInclusive;

  public DateRange(LocalDate lowerInclusive, LocalDate upperInclusive) {
    this.lowerInclusive = lowerInclusive;
    this.upperInclusive = upperInclusive;
  }

  public static DateRange closed(LocalDate lowerInclusive, LocalDate upperInclusive) {
    Validate.notNull(lowerInclusive, LOWER_DATE_MUST_BE_NOT_NULL);
    Validate.notNull(upperInclusive, UPPER_DATE_MUST_BE_NOT_NULL);
    return create(lowerInclusive, upperInclusive);
  }
  public static DateRange open(LocalDate lowerExclusive, LocalDate upperExclusive) {
    Validate.notNull(lowerExclusive, LOWER_DATE_MUST_BE_NOT_NULL);
    Validate.notNull(upperExclusive, UPPER_DATE_MUST_BE_NOT_NULL);
    return create(lowerExclusive.plusDays(1), upperExclusive.minusDays(1));
  }
  public static DateRange openClosed(LocalDate lowerExclusive, LocalDate upperInclusive) {
    Validate.notNull(lowerExclusive, LOWER_DATE_MUST_BE_NOT_NULL);
    Validate.notNull(upperInclusive, UPPER_DATE_MUST_BE_NOT_NULL);
    return create(lowerExclusive.plusDays(1), upperInclusive);
  }
  public static DateRange closedOpen(LocalDate lowerInclusive, LocalDate upperExclusive) {
    Validate.notNull(lowerInclusive, LOWER_DATE_MUST_BE_NOT_NULL);
    Validate.notNull(upperExclusive, UPPER_DATE_MUST_BE_NOT_NULL);
    return create(lowerInclusive, upperExclusive.minusDays(1));
  }

  public static DateRange range(LocalDate lower, BoundType lowerBound, LocalDate upper, BoundType upperBound) {
    Validate.notNull(lower, LOWER_DATE_MUST_BE_NOT_NULL);
    Validate.notNull(upper, UPPER_DATE_MUST_BE_NOT_NULL);
    LocalDate startInclusive = lowerBound==BoundType.OPEN ? lower.plusDays(1) : lower;
    LocalDate endInclusive   = upperBound==BoundType.OPEN ? upper.minusDays(1) : upper;
    return create(startInclusive, endInclusive);
  }

  private static DateRange create(LocalDate lowerInclusive, LocalDate upperInclusive) {
    return new DateRange(lowerInclusive, upperInclusive);
  }

  public LocalDate getLowerInclusive() {
    return this.lowerInclusive;
  }
  public LocalDate getLowerExclusive() {
    return this.lowerInclusive.minusDays(1);
  }
  public LocalDate getUpperInclusive() {
    return this.upperInclusive;
  }
  public LocalDate getUpperExclusive() {
    return this.upperInclusive.plusDays(1);
  }

  /**
   * Checks whether this range contains specified date.
   *
   * @param other  the other date to compare to, not null
   * @return true if this range contains date
   */
  public boolean contains(LocalDate other) {
    Validate.notNull(other);
    return getLowerExclusive().isBefore(other) && getUpperExclusive().isAfter(other);
  }

  /**
   * Checks whether this range is after the specified date.
   *
   * @param other  the other date to compare to, not null
   * @return true if this range is entirely after the specified date
   */

  public boolean isRangeAfter(LocalDate other) {
    Validate.notNull(other);
    return lowerInclusive.isAfter(other);
  }
  /**
   * Checks whether specified date is before range.
   *
   * @param other  the other date to compare to, not null
   * @return true if this range is entirely before the specified date
   */
  public boolean isRangeBefore(LocalDate other) {
    Validate.notNull(other);
    return upperInclusive.isBefore(other);
  }
  /**
   * Checks whether specified date is after range.
   *
   * @param other  the other date to compare to, not null
   * @return true if specified date is entirely after this range
   */
  public boolean isDateAfterRange(LocalDate other) {
    Validate.notNull(other);
    return upperInclusive.isBefore(other);
  }
  /**
   * Checks whether specified date is before range.
   *
   * @param other  the other date to compare to, not null
   * @return true if specified date is entirely before this range
   */
  public boolean isDateBeforeRange(LocalDate other) {
    Validate.notNull(other);
    return lowerInclusive.isAfter(other);
  }
}
