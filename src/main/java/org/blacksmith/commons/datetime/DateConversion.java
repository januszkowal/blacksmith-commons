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
  public static java.time.LocalDate convertDateToLocalDate(java.util.Date date) {
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date).toLocalDate();
  }  

  /* java.util.Date->java.sql.Date */
  public static java.sql.Date convertDateToSqlDate(java.util.Date date) {
    return (date == null) ? null : createSqlDateFromDateViaMilis(date);
  }
  
  /* java.sql.Date->java.time.LocalDate */
  public static java.time.LocalDate convertSqlDateToLocalDate(java.sql.Date date) {
    return (date == null) ? null : date.toLocalDate();
  }
  
  /* java.util.Date->java.time.LocalDateTime */
  public static java.time.LocalDateTime convertDateToLocalDateTime(java.util.Date date) {
    //return (date == null) ? null : createZonedDateTimeFromDateViaInstant(date).toLocalDateTime();
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date).toLocalDateTime();
  }

  /* java.sql.Date->java.time.LocalDateTime */
  public static java.time.LocalDateTime convertSqlDateToLocalDateTime(java.sql.Date date) {
    return (date == null) ? null : createZonedDateTimeFromDateViaMilis(date).toLocalDateTime();
    //return (date == null) ? null : convertDateToSqlTimestamp(date).toLocalDateTime();
  }

  /* java.util.Date->java.sql.Timestamp */
  public static java.sql.Timestamp convertDateToSqlTimestamp(java.util.Date date) {
    return (date == null) ? null : createSqlTimestampFromDateViaMilis(date);
  }
  
  /* java.util.Date->java.time.LocalTime */
  public static java.time.LocalTime convertDateToLocalTime(java.util.Date date) {
    return createZonedDateTimeFromDateViaMilis(date).toLocalTime();
  }

  public static ZonedDateTime createZonedDateTimeFromLocalDate(java.time.LocalDate localDate) {
    //return localDate.atStartOfDay(ZoneId.systemDefault());
    //return localDate.atStartOfDay().atZone(ZoneId.systemDefault());
    return localDate.atStartOfDay(ZoneId.systemDefault());
  }

  public static ZonedDateTime createZonedDateTimeFromLocalDateTime(java.time.LocalDateTime localdateTime) {
    return localdateTime.atZone(ZoneId.systemDefault());
  }

  /* Via Instant*/
  public static ZonedDateTime createZonedDateTimeFromDateViaInstant(java.util.Date date) {
    /* don't pass java.sql.Date - it violates Liscov Substitution Principle*/
    return date.toInstant().atZone(ZoneId.systemDefault());
  }

  /* Via Miliseconds*/
  public static ZonedDateTime createZonedDateTimeFromDateViaMilis(java.util.Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault());
  }
  
  public static java.sql.Timestamp createSqlTimestampFromDateViaMilis(java.util.Date date) {
    return new java.sql.Timestamp(date.getTime());
  }
  
  public static java.sql.Date createSqlDateFromDateViaMilis(java.util.Date date) {
    return new java.sql.Date(date.getTime());
  }
}
