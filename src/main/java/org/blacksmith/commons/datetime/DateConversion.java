package org.blacksmith.commons.datetime;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateConversion {

  private DateConversion() {
  }

  /* java.time.LocalDate->java.util.Date */
  public static java.util.Date convertLocalDateToDate(LocalDate localDate) {
    return (localDate == null) ? null : java.util.Date.from(createZonedDateTimeFromLocalDate(localDate).toInstant());
  }

  /* java.time.LocalDate->java.util.Date */
  public static java.util.Date convertLocalDateToDate(LocalDate localDate, ZoneId zoneId) {
    return (localDate == null) ? null
        : java.util.Date.from(createZonedDateTimeFromLocalDate(localDate, zoneId).toInstant());
  }

  /* java.time.LocalDate->java.sql.Date */
  public static java.sql.Date convertLocalDateToSqlDate(LocalDate localDate) {
    return (localDate == null) ? null : java.sql.Date.valueOf(localDate);
  }

  /* java.time.LocalDate->java.sql.Date */
  public static java.sql.Date convertLocalDateToSqlDate(LocalDate localDate, ZoneId zoneId) {
    return (localDate == null) ? null
        : java.sql.Date.valueOf(createZonedDateTimeFromLocalDate(localDate, zoneId).toLocalDate());
  }

  /* java.time.LocalDate->java.sql.Timestamp */
  public static java.sql.Timestamp convertLocalDateToSqlTimestamp(LocalDate localDate) {
    return (localDate == null) ? null
        : java.sql.Timestamp.from(createZonedDateTimeFromLocalDate(localDate).toInstant());
  }

  /* java.time.LocalDate->java.sql.Timestamp */
  public static java.sql.Timestamp convertLocalDateToSqlTimestamp(LocalDate localDate, ZoneId zoneId) {
    return (localDate == null) ? null
        : java.sql.Timestamp.from(createZonedDateTimeFromLocalDate(localDate, zoneId).toInstant());
  }


  /* java.time.LocalDate->java.time.ZonedDateTime */
  public static ZonedDateTime convertLocalDateToZonedDateTime(LocalDate localDate) {
    return (localDate == null) ? null : createZonedDateTimeFromLocalDate(localDate);
  }

  /* java.time.LocalDate->java.time.ZonedDateTime */
  public static ZonedDateTime convertLocalDateToZonedDateTime(LocalDate localDate, ZoneId zoneId) {
    return (localDate == null) ? null : createZonedDateTimeFromLocalDate(localDate, zoneId);
  }

  /* java.time.LocalDateTime->java.util.Date */
  public static java.util.Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
    return (localDateTime == null) ? null
        : java.util.Date.from(createZonedDateTimeFromLocalDateTime(localDateTime).toInstant());
  }

  /* java.time.LocalDateTime->java.util.Date */
  public static java.util.Date convertLocalDateTimeToDate(LocalDateTime localDateTime, ZoneId zoneId) {
    return (localDateTime == null) ? null
        : java.util.Date.from(createZonedDateTimeFromLocalDateTime(localDateTime, zoneId).toInstant());
  }

  /* java.time.LocalDateTime->java.sql.Date */
  public static java.sql.Date convertLocalDateTimeToSqlDate(LocalDateTime localDateTime) {
    return (localDateTime == null) ? null
        : java.sql.Date.valueOf(localDateTime.toLocalDate());
  }

  /* java.time.LocalDateTime->java.sql.Date */
  public static java.sql.Date convertLocalDateTimeToSqlDate(LocalDateTime localDateTime, ZoneId zoneId) {
    return (localDateTime == null) ? null
        : java.sql.Date.valueOf(createZonedDateTimeFromLocalDateTime(localDateTime, zoneId).toLocalDate());
  }

  /* java.time.LocalDateTime->java.util.Date */
  /* java.time.LocalDateTime->java.sql.Timestamp */
  public static java.sql.Timestamp convertLocalDateTimeToSqlTimestamp(LocalDateTime localDateTime) {
    return (localDateTime == null) ? null : java.sql.Timestamp.valueOf(localDateTime);
  }

  /* java.time.LocalDateTime->java.util.Date */
  /* java.time.LocalDateTime->java.sql.Timestamp */
  public static java.sql.Timestamp convertLocalDateTimeToSqlTimestamp(LocalDateTime localDateTime, ZoneId zoneId) {
    return (localDateTime == null) ? null
        : java.sql.Timestamp.from(createZonedDateTimeFromLocalDateTime(localDateTime, zoneId).toInstant());
  }

  /* java.time.LocalDateTime->java.time.ZonedDateTime */
  public static ZonedDateTime convertLocalDateTimeToZonedDateTime(LocalDateTime localDateTime) {
    return (localDateTime == null) ? null : createZonedDateTimeFromLocalDateTime(localDateTime);
  }

  /* java.time.LocalDateTime->java.time.ZonedDateTime */
  public static ZonedDateTime convertLocalDateTimeToZonedDateTime(LocalDateTime localDateTime, ZoneId zoneId) {
    return (localDateTime == null) ? null : createZonedDateTimeFromLocalDateTime(localDateTime, zoneId);
  }

  /* java.util.Date->java.time.LocalDate */
  public static LocalDate convertDateToLocalDate(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date).toLocalDate();
  }

  /* java.util.Date->java.time.LocalDate */
  public static LocalDate convertDateToLocalDate(java.util.Date date, ZoneId zoneId) {
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date, zoneId).toLocalDate();
  }

  /* java.sql.Date->java.time.LocalDate */
  /* java.util.Date->java.time.LocalDateTime */
  public static LocalDateTime convertDateToLocalDateTime(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date).toLocalDateTime();
  }

  /* java.util.Date->java.time.LocalDateTime */
  public static LocalDateTime convertDateToLocalDateTime(java.util.Date date, ZoneId zoneId) {
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date, zoneId).toLocalDateTime();
  }

  /* java.util.Date->java.sql.Date */
  public static java.sql.Date convertDateToSqlDate(java.util.Date date) {
    return (date == null) ? null : createSqlDateFromMillis(date.getTime());
  }

  /* java.util.Date->java.sql.Timestamp */
  public static java.sql.Timestamp convertDateToSqlTimestamp(java.util.Date date) {
    return (date == null) ? null : createSqlTimestampFromDateViaMillis(date);
  }

  /* java.util.Date->java.time.ZonedDateTime */
  public static java.sql.Timestamp convertDateToTimestamp(java.util.Date date, ZoneId zoneId) {
    return (date == null) ? null : createTimestampFromDate(date, zoneId);
  }

  /* java.util.Date->java.time.LocalTime */
  public static LocalTime convertDateToLocalTime(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date).toLocalTime();
  }

  /* java.util.Date->java.time.ZonedDateTime */
  public static ZonedDateTime convertDateToZonedDateTime(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date);
  }

  /* java.util.Date->java.time.ZonedDateTime */
  public static ZonedDateTime convertDateToZonedDateTime(java.util.Date date, ZoneId zoneId) {
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date, zoneId);
  }

  public static LocalDate convertSqlDateToLocalDate(java.sql.Date date) {
    return (date == null) ? null : date.toLocalDate();
  }

  /* java.sql.Date->java.time.LocalDateTime */
  public static LocalDateTime convertSqlDateToLocalDateTime(java.sql.Date date) {
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date).toLocalDateTime();
  }

  /* java.sql.Date->java.time.LocalDateTime */
  public static LocalDateTime convertSqlDateToLocalDateTime(java.sql.Date date, ZoneId zoneId) {
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date, zoneId).toLocalDateTime();
  }

  /* pure conversion routines*/

  /* java.sql.Date->java.time.LocalDateTime */
  public static LocalDateTime createLocalDateTimeFromMillis(long milLis) {
    return createZonedDateTimeFromMillis(milLis).toLocalDateTime();
  }

  public static ZonedDateTime createZonedDateTimeFromLocalDate(LocalDate localDate) {
    return localDate.atStartOfDay(ZoneId.systemDefault());
  }

  public static ZonedDateTime createZonedDateTimeFromLocalDate(LocalDate localDate, ZoneId zoneId) {
    return localDate.atStartOfDay(zoneId);
  }

  public static ZonedDateTime createZonedDateTimeFromLocalDateTime(LocalDateTime localdateTime) {
    return localdateTime.atZone(ZoneId.systemDefault());
  }

  public static ZonedDateTime createZonedDateTimeFromLocalDateTime(LocalDateTime localdateTime, ZoneId zoneId) {
    return localdateTime.atZone(zoneId);
  }

  /* Via Instant */
  public static ZonedDateTime createZonedDateTimeFromDateViaInstant(java.util.Date date) {
    /* don't pass java.sql.Date - it violates Liskov Substitution Principle */
    return createZonedDateTimeFromDateViaInstant(date, ZoneId.systemDefault());
  }

  /* Via Instant */
  public static ZonedDateTime createZonedDateTimeFromDateViaInstant(java.util.Date date, ZoneId zoneId) {
    /* don't pass java.sql.Date - it violates Liskov Substitution Principle */
    return date.toInstant().atZone(zoneId);
  }

  /* Via Milis */
  public static ZonedDateTime createZonedDateTimeFromDateViaMilis(java.util.Date date) {
    /* don't pass java.sql.Date - it violates Liskov Substitution Principle */
    return createZonedDateTimeFromDateViaMilis(date, ZoneId.systemDefault());
  }

  /* Via Milis */
  public static ZonedDateTime createZonedDateTimeFromDateViaMilis(java.util.Date date, ZoneId zoneId) {
    return Instant.ofEpochMilli(date.getTime()).atZone(zoneId);
  }

  public static java.sql.Timestamp createSqlTimestampFromDateViaMillis(java.util.Date date) {
    return new java.sql.Timestamp(date.getTime());
  }

  public static java.sql.Date createSqlDateFromMillis(long millis) {
    return new java.sql.Date(millis);
  }

  public static ZonedDateTime createZonedDateTimeFromMillis(long milis) {
    return createZonedDateTimeFromMillis(milis, ZoneId.systemDefault());
  }

  public static ZonedDateTime createZonedDateTimeFromMillis(long milis, ZoneId zoneId) {
    return Instant.ofEpochMilli(milis).atZone(zoneId);
  }

  public static ZonedDateTime createZonedDateTimeFromTimestamp(Timestamp dateTime, ZoneId zoneId) {
    return ZonedDateTime.of(dateTime.toLocalDateTime(), zoneId);
  }

  public static ZonedDateTime createZonedDateTimeFromLocalTime(LocalDateTime dateTime, ZoneId zoneId) {
    // eq ZoneDateTime.of
    return dateTime.atZone(zoneId);
  }

  public static Timestamp createTimestampFromZonedDateTime(ZonedDateTime zonedDateTime, ZoneId zoneId) {
    return Timestamp.valueOf(zonedDateTime.withZoneSameInstant(zoneId).toLocalDateTime());
  }

  public static Timestamp createTimestampFromDate(java.util.Date date, ZoneId zoneId) {
    return Timestamp.valueOf(createZonedDateTimeFromDateViaInstant(date, zoneId).toLocalDateTime());
  }
}
