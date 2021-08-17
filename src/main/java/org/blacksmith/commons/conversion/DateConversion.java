package org.blacksmith.commons.conversion;

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
    return (localDate == null) ? null : java.util.Date.from(localDateToInstant(localDate));
  }

  /* java.time.LocalDate->java.util.Date */
  public static java.util.Date convertLocalDateToDate(LocalDate localDate, ZoneId zoneId) {
    return (localDate == null) ? null
        : java.util.Date.from(localDateToInstant(localDate, zoneId));
  }

  /* java.time.LocalDate->java.sql.Date */
  public static java.sql.Date convertLocalDateToSqlDate(LocalDate localDate) {
    return (localDate == null) ? null : java.sql.Date.valueOf(localDate);
  }

  /* java.time.LocalDate->java.sql.Date */
  public static java.sql.Date convertLocalDateToSqlDate(LocalDate localDate, ZoneId zoneId) {
    return (localDate == null) ? null
        : java.sql.Date.valueOf(localDateToZonedDateTime(localDate, zoneId).toLocalDate());
  }

  /* java.time.LocalDate->java.sql.Timestamp */
  public static java.sql.Timestamp convertLocalDateToSqlTimestamp(LocalDate localDate) {
    return (localDate == null) ? null
        : java.sql.Timestamp.from(localDateToInstant(localDate));
  }

  /* java.time.LocalDate->java.sql.Timestamp */
  public static java.sql.Timestamp convertLocalDateToSqlTimestamp(LocalDate localDate, ZoneId zoneId) {
    return (localDate == null) ? null
        : java.sql.Timestamp.from(localDateToInstant(localDate, zoneId));
  }


  /* java.time.LocalDate->java.time.ZonedDateTime */
  public static ZonedDateTime convertLocalDateToZonedDateTime(LocalDate localDate) {
    return (localDate == null) ? null : localDateToZonedDateTime(localDate);
  }

  /* java.time.LocalDate->java.time.ZonedDateTime */
  public static ZonedDateTime convertLocalDateToZonedDateTime(LocalDate localDate, ZoneId zoneId) {
    return (localDate == null) ? null : localDateToZonedDateTime(localDate, zoneId);
  }

  /* java.time.LocalDateTime->java.util.Date */
  public static java.util.Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
    return (localDateTime == null) ? null
        : java.util.Date.from(localDateTimeToInstant(localDateTime));
  }

  /* java.time.LocalDateTime->java.util.Date */
  public static java.util.Date convertLocalDateTimeToDate(LocalDateTime localDateTime, ZoneId zoneId) {
    return (localDateTime == null) ? null
        : java.util.Date.from(localDateTimeToInstant(localDateTime, zoneId));
  }

  /* java.time.LocalDateTime->java.sql.Date */
  public static java.sql.Date convertLocalDateTimeToSqlDate(LocalDateTime localDateTime) {
    return (localDateTime == null) ? null
        : java.sql.Date.valueOf(localDateTime.toLocalDate());
  }

  /* java.time.LocalDateTime->java.sql.Date */
  public static java.sql.Date convertLocalDateTimeToSqlDate(LocalDateTime localDateTime, ZoneId zoneId) {
    return (localDateTime == null) ? null
        : java.sql.Date.valueOf(localDateTimeToZonedDateTime(localDateTime, zoneId).toLocalDate());
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
        : java.sql.Timestamp.from(localDateTimeToInstant(localDateTime, zoneId));
  }

  /* java.time.LocalDateTime->java.time.ZonedDateTime */
  public static ZonedDateTime convertLocalDateTimeToZonedDateTime(LocalDateTime localDateTime) {
    return (localDateTime == null) ? null : localDateTimeToZonedDateTime(localDateTime);
  }

  /* java.time.LocalDateTime->java.time.ZonedDateTime */
  public static ZonedDateTime convertLocalDateTimeToZonedDateTime(LocalDateTime localDateTime, ZoneId zoneId) {
    return (localDateTime == null) ? null : localDateTimeToZonedDateTime(localDateTime, zoneId);
  }

  /* java.util.Date->java.time.LocalDate */
  public static LocalDate convertDateToLocalDate(java.util.Date date) {
    return (date == null) ? null : dateToZonedDateTimeViaMilis(date).toLocalDate();
  }

  /* java.util.Date->java.time.LocalDate */
  public static LocalDate convertDateToLocalDate(java.util.Date date, ZoneId zoneId) {
    return (date == null) ? null : dateToZonedDateTimeViaMilis(date, zoneId).toLocalDate();
  }

  /* java.sql.Date->java.time.LocalDate */
  /* java.util.Date->java.time.LocalDateTime */
  public static LocalDateTime convertDateToLocalDateTime(java.util.Date date) {
    return (date == null) ? null : dateToZonedDateTimeViaMilis(date).toLocalDateTime();
  }

  /* java.util.Date->java.time.LocalDateTime */
  public static LocalDateTime convertDateToLocalDateTime(java.util.Date date, ZoneId zoneId) {
    return (date == null) ? null : dateToZonedDateTimeViaMilis(date, zoneId).toLocalDateTime();
  }

  /* java.util.Date->java.sql.Date */
  public static java.sql.Date convertDateToSqlDate(java.util.Date date) {
    return (date == null) ? null : milisToSqlDate(date.getTime());
  }

  /* java.util.Date->java.sql.Timestamp */
  public static java.sql.Timestamp convertDateToSqlTimestamp(java.util.Date date) {
    return (date == null) ? null : dateToTimestampViaMillis(date);
  }

  /* java.util.Date->java.time.ZonedDateTime */
  public static java.sql.Timestamp convertDateToTimestamp(java.util.Date date, ZoneId zoneId) {
    return (date == null) ? null : dateToTimestamp(date, zoneId);
  }

  /* java.util.Date->java.time.LocalTime */
  public static LocalTime convertDateToLocalTime(java.util.Date date) {
    return (date == null) ? null : dateToZonedDateTimeViaMilis(date).toLocalTime();
  }

  /* java.util.Date->java.time.ZonedDateTime */
  public static ZonedDateTime convertDateToZonedDateTime(java.util.Date date) {
    return (date == null) ? null : dateToZonedDateTimeViaMilis(date);
  }

  /* java.util.Date->java.time.ZonedDateTime */
  public static ZonedDateTime convertDateToZonedDateTime(java.util.Date date, ZoneId zoneId) {
    return (date == null) ? null : dateToZonedDateTimeViaMilis(date, zoneId);
  }

  public static LocalDate convertSqlDateToLocalDate(java.sql.Date date) {
    return (date == null) ? null : date.toLocalDate();
  }

  /* java.sql.Date->java.time.LocalDateTime */
  public static LocalDateTime convertSqlDateToLocalDateTime(java.sql.Date date) {
    return (date == null) ? null : dateToZonedDateTimeViaMilis(date).toLocalDateTime();
  }

  /* java.sql.Date->java.time.LocalDateTime */
  public static LocalDateTime convertSqlDateToLocalDateTime(java.sql.Date date, ZoneId zoneId) {
    return (date == null) ? null : dateToZonedDateTimeViaMilis(date, zoneId).toLocalDateTime();
  }

  /* pure conversion routines*/

  /* long->java.time.LocalDateTime */
  public static LocalDateTime milisToLocalDateTime(long milLis) {
    return milisToZonedDateTime(milLis).toLocalDateTime();
  }

  public static ZonedDateTime localDateToZonedDateTime(LocalDate localDate) {
    return localDate.atStartOfDay(ZoneId.systemDefault());
  }

  public static ZonedDateTime localDateToZonedDateTime(LocalDate localDate, ZoneId zoneId) {
    return localDate.atStartOfDay(zoneId);
  }

  public static ZonedDateTime localDateTimeToZonedDateTime(LocalDateTime localdateTime) {
    return localdateTime.atZone(ZoneId.systemDefault());
  }

  public static ZonedDateTime localDateTimeToZonedDateTime(LocalDateTime localdateTime, ZoneId zoneId) {
    return localdateTime.atZone(zoneId);
  }

  public static Instant localDateToInstant(LocalDate localDate) {
    return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
  }

  public static Instant localDateToInstant(LocalDate localDate, ZoneId zoneId) {
    return localDate.atStartOfDay(zoneId).toInstant();
  }

  public static Instant localDateTimeToInstant(LocalDateTime localdateTime) {
    return localdateTime.atZone(ZoneId.systemDefault()).toInstant();
  }

  public static Instant localDateTimeToInstant(LocalDateTime localdateTime, ZoneId zoneId) {
    return localdateTime.atZone(zoneId).toInstant();
  }

  /* Via Instant */
  public static ZonedDateTime dateToZonedDateTimeViaInstant(java.util.Date date) {
    /* don't pass java.sql.Date - it violates Liskov Substitution Principle */
    return dateToZonedDateTimeViaInstant(date, ZoneId.systemDefault());
  }

  /* Via Instant */
  public static ZonedDateTime dateToZonedDateTimeViaInstant(java.util.Date date, ZoneId zoneId) {
    /* don't pass java.sql.Date - it violates Liskov Substitution Principle */
    return date.toInstant().atZone(zoneId);
  }

  /* Via Milis */
  public static ZonedDateTime dateToZonedDateTimeViaMilis(java.util.Date date) {
    /* don't pass java.sql.Date - it violates Liskov Substitution Principle */
    return dateToZonedDateTimeViaMilis(date, ZoneId.systemDefault());
  }

  /* Via Milis */
  public static ZonedDateTime dateToZonedDateTimeViaMilis(java.util.Date date, ZoneId zoneId) {
    return Instant.ofEpochMilli(date.getTime()).atZone(zoneId);
  }

  public static java.sql.Timestamp dateToTimestampViaMillis(java.util.Date date) {
    return new java.sql.Timestamp(date.getTime());
  }

  public static java.sql.Date milisToSqlDate(long millis) {
    return new java.sql.Date(millis);
  }

  public static ZonedDateTime milisToZonedDateTime(long milis) {
    return milisToZonedDateTime(milis, ZoneId.systemDefault());
  }

  public static ZonedDateTime milisToZonedDateTime(long milis, ZoneId zoneId) {
    return Instant.ofEpochMilli(milis).atZone(zoneId);
  }

  public static ZonedDateTime timestampToZonedDateTime(Timestamp timestamp, ZoneId zoneId) {
    return ZonedDateTime.of(timestamp.toLocalDateTime(), zoneId);
  }

  public static ZonedDateTime localTimeToZonedDateTime(LocalDateTime dateTime, ZoneId zoneId) {
    // eq ZoneDateTime.of
    return dateTime.atZone(zoneId);
  }

  public static Timestamp zonedDateTimeToTimestamp(ZonedDateTime zonedDateTime, ZoneId zoneId) {
    return Timestamp.valueOf(zonedDateTime.withZoneSameInstant(zoneId).toLocalDateTime());
  }

  public static Timestamp zonedDateTimeToTimestamp1(ZonedDateTime zonedDateTime, ZoneId zoneId) {
    return Timestamp.from(zonedDateToInstant(zonedDateTime, zoneId));
  }

  public static Timestamp dateToTimestamp(java.util.Date date, ZoneId zoneId) {
    return Timestamp.valueOf(dateToZonedDateTimeViaInstant(date, zoneId).toLocalDateTime());
  }

  public static Instant zonedDateToInstant(ZonedDateTime zonedDateTime, ZoneId zoneId) {
    return zonedDateTime.withZoneSameInstant(zoneId).toInstant();
  }
}
