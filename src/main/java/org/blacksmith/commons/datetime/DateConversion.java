package org.blacksmith.commons.datetime;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateConversion {
  private DateConversion() {
  }

  /* Via instant*/
  public static Date convertLocalDateToDate(LocalDate localDate) {
    return java.util.Date.from(localDate.atStartOfDay()
        .atZone(ZoneId.systemDefault()).toInstant());
  }

  /* Via SqlDate */
  public static Date convertLocalDateToDateViaSqlDate(LocalDate localDate) {
    return convertLocalDateToSqlDate(localDate);
  }

  public static java.sql.Date convertLocalDateToSqlDate(LocalDate localDate) {
    return java.sql.Date.valueOf(localDate);
  }

  /* Via Instant */
  public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
    return java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date convertLocalDateTimeViaSqlTimestamp(LocalDateTime localDateTime) {
    return convertLocalDateTimeToSqlTimestamp(localDateTime);
  }

  public static Timestamp convertLocalDateTimeToSqlTimestamp(LocalDateTime localDateTime) {
    return java.sql.Timestamp.valueOf(localDateTime);
  }

  /* Via instant*/
  public static LocalDate convertDateToLocalDate(Date date) {
    return date.toInstant()
        .atZone(ZoneId.systemDefault()).toLocalDate();
  }

  /* via milisecond */
  public static LocalDate convertDateToLocalDateViaMilisecond(Date date) {
    return Instant.ofEpochMilli(date.getTime())
        .atZone(ZoneId.systemDefault()).toLocalDate();
  }

  /* Via SqlDate */
  public static LocalDate convertDateToLocalDateViaSqlDate(Date date) {
    return convertDateToSqlDate(date).toLocalDate();
  }

  public static java.sql.Date convertDateToSqlDate(Date date) {
    return new java.sql.Date(date.getTime());
  }

  /* Via instant*/
  public static LocalDateTime convertDateToLocalDateTime(Date date) {
    return date.toInstant()
        .atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  /* via milisecond */
  public static LocalDateTime convertDateToLocalDateTimeViaMilisecond(Date date) {
    return Instant.ofEpochMilli(date.getTime())
        .atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  /* Via SqlTimestamp */
  public static LocalDateTime convertDateToLocalDateTimeViaSqlTimestamp(Date date) {
    return convertDateToLocalDateTimeToSqlTimestamp(date).toLocalDateTime();
  }

  public static Timestamp convertDateToLocalDateTimeToSqlTimestamp(Date date) {
    return new java.sql.Timestamp(date.getTime());
  }
}
