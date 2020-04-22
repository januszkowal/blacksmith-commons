package org.blacksmith.commons.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateConversion {
  private DateConversion() {}

  /* java.time.LocalDate->java.util.Date */
  public static java.util.Date convertLocalDateToDate(LocalDate localDate) {
    return (localDate == null) ? null : java.util.Date.from(createZonedDateTimeFromLocalDate(localDate).toInstant());
  }

  /* java.time.LocalDate->java.sql.Date */
  public static java.sql.Date convertLocalDateToSqlDate(LocalDate localDate) {
    return (localDate == null) ? null : java.sql.Date.valueOf(localDate);
  }

  /* java.time.LocalDateTime->java.util.Date */
  public static java.util.Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
    return (localDateTime == null) ? null : java.util.Date.from(createZonedDateTimeFromLocalDateTime(localDateTime).toInstant());
  }

  /* java.time.LocalDateTime->java.util.Date */
  /* java.time.LocalDateTime->java.sql.Timestamp */
  public static java.sql.Timestamp convertLocalDateTimeToSqlTimestamp(LocalDateTime localDateTime) {
    return (localDateTime == null) ? null : java.sql.Timestamp.valueOf(localDateTime);
  }

  /* java.util.Date->java.time.LocalDate */
  public static LocalDate convertDateToLocalDate(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromMilis(date.getTime()).toLocalDate();
  }

  /* java.util.Date->java.sql.Date */
  public static java.sql.Date convertDateToSqlDate(java.util.Date date) {
    return (date == null) ? null : createSqlDateFromMilis(date.getTime());
  }

  /* java.sql.Date->java.time.LocalDate */
  public static LocalDate convertSqlDateToLocalDate(java.sql.Date date) {
    return (date == null) ? null : date.toLocalDate();
  }

  /* java.util.Date->java.time.LocalDateTime */
  public static LocalDateTime convertDateToLocalDateTime(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromMilis(date.getTime()).toLocalDateTime();
  }

  /* java.sql.Date->java.time.LocalDateTime */
  public static LocalDateTime convertSqlDateToLocalDateTime(java.sql.Date date) {
    return (date == null) ? null : createZonedDateTimeFromMilis(date.getTime()).toLocalDateTime();
  }

  /* java.sql.Date->java.time.LocalDateTime */
  public static LocalDateTime createLocalDateTimeFromMilis(long milis) {
    return createZonedDateTimeFromMilis(milis).toLocalDateTime();
  }

  /* java.util.Date->java.sql.Timestamp */
  public static java.sql.Timestamp convertDateToSqlTimestamp(java.util.Date date) {
    return (date == null) ? null : createSqlTimestampFromDateViaMilis(date);
  }

  /* java.util.Date->java.time.LocalTime */
  public static java.time.LocalTime convertDateToLocalTime(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromMilis(date.getTime()).toLocalTime();
  }

  /* java.util.Date->java.time.LocalDate */
  public static ZonedDateTime convertDateToZonedDateTime(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromMilis(date.getTime());
  }


  public static ZonedDateTime createZonedDateTimeFromLocalDate(LocalDate localDate) {
    return localDate.atStartOfDay(ZoneId.systemDefault());
  }

  public static ZonedDateTime createZonedDateTimeFromLocalDateTime(LocalDateTime localdateTime) {
    return localdateTime.atZone(ZoneId.systemDefault());
  }

  /* Via Instant */
  public static ZonedDateTime createZonedDateTimeFromDateViaInstant(java.util.Date date) {
    /* don't pass java.sql.Date - it violates Liskov Substitution Principle */
    return date.toInstant().atZone(ZoneId.systemDefault());
  }

  /* Via Miliseconds */
  public static ZonedDateTime createZonedDateFromMilis(long milis) {
    return Instant.ofEpochMilli(milis).atZone(ZoneId.systemDefault());
  }

  public static java.sql.Timestamp createSqlTimestampFromDateViaMilis(java.util.Date date) {
    return new java.sql.Timestamp(date.getTime());
  }

  public static java.sql.Date createSqlDateFromMilis(long milis) {
    return new java.sql.Date(milis);
  }
  
  public static ZonedDateTime createZonedDateTimeFromMilis(long milis) {
    return Instant.ofEpochMilli(milis).atZone(ZoneId.systemDefault());
  }
}
