package org.blacksmith.commons.datetime;

import java.sql.Timestamp;
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
    return (date == null) ? null : createZonedDateTimeFromMillis(date.getTime()).toLocalDate();
  }

  /* java.util.Date->java.sql.Date */
  public static java.sql.Date convertDateToSqlDate(java.util.Date date) {
    return (date == null) ? null : createSqlDateFromMillis(date.getTime());
  }

  /* java.sql.Date->java.time.LocalDate */
  public static LocalDate convertSqlDateToLocalDate(java.sql.Date date) {
    return (date == null) ? null : date.toLocalDate();
  }

  /* java.util.Date->java.time.LocalDateTime */
  public static LocalDateTime convertDateToLocalDateTime(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromMillis(date.getTime()).toLocalDateTime();
  }

  /* java.sql.Date->java.time.LocalDateTime */
  public static LocalDateTime convertSqlDateToLocalDateTime(java.sql.Date date) {
    return (date == null) ? null : createZonedDateTimeFromMillis(date.getTime()).toLocalDateTime();
  }

  /* java.sql.Date->java.time.LocalDateTime */
  public static LocalDateTime createLocalDateTimeFromMillis(long milLis) {
    return createZonedDateTimeFromMillis(milLis).toLocalDateTime();
  }

  /* java.util.Date->java.sql.Timestamp */
  public static java.sql.Timestamp convertDateToSqlTimestamp(java.util.Date date) {
    return (date == null) ? null : createSqlTimestampFromDateViaMillis(date);
  }

  /* java.util.Date->java.time.LocalTime */
  public static java.time.LocalTime convertDateToLocalTime(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromMillis(date.getTime()).toLocalTime();
  }

  /* java.util.Date->java.time.LocalDate */
  public static ZonedDateTime convertDateToZonedDateTime(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromMillis(date.getTime());
  }

  /* java.util.Date->java.time.ZonedDateTime */
  public static ZonedDateTime convertDateToZonedDateTime(java.util.Date date, ZoneId zone) {
    return (date == null) ? null : createZonedDateTimeFromMillis(date.getTime(),zone);
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
    return createZonedDateTimeFromDateViaInstant(date,ZoneId.systemDefault());
  }

  /* Via Instant */
  public static ZonedDateTime createZonedDateTimeFromDateViaInstant(java.util.Date date, ZoneId zone) {
    /* don't pass java.sql.Date - it violates Liskov Substitution Principle */
    return date.toInstant().atZone(zone);
  }

  public static java.sql.Timestamp createSqlTimestampFromDateViaMillis(java.util.Date date) {
    return new java.sql.Timestamp(date.getTime());
  }

  public static java.sql.Date createSqlDateFromMillis(long millis) {
    return new java.sql.Date(millis);
  }
  
  public static ZonedDateTime createZonedDateTimeFromMillis(long milis) {
    return createZonedDateTimeFromMillis(milis,ZoneId.systemDefault());
  }

  public static ZonedDateTime createZonedDateTimeFromMillis(long milis, ZoneId zone) {
    return Instant.ofEpochMilli(milis).atZone(zone);
  }

  public static ZonedDateTime createZonedDateTimeFromTimestamp(Timestamp dateTime, ZoneId zone) {
    return ZonedDateTime.of(dateTime.toLocalDateTime(), zone);
  }

  public static ZonedDateTime createZonedDateTimeFromLocalTime(LocalDateTime dateTime, ZoneId zone) {
    // eq ZoneDateTime.of
    return dateTime.atZone(zone);
  }

  public static Timestamp createTimestampFromZonedDateTime(ZonedDateTime zonedDateTime, ZoneId zone) {
    return Timestamp.valueOf(zonedDateTime.withZoneSameInstant(zone).toLocalDateTime());
  }
}
