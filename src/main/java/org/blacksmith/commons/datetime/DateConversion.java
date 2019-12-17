package org.blacksmith.commons.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateConversion {
  private DateConversion() {}

  /* Via instant */
  public static java.util.Date convertLocalDateToDate(LocalDate localDate) {
    return (localDate == null) ? null : java.util.Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  /* Via SqlDate */
  public static java.util.Date convertLocalDateToDateViaSqlDate(LocalDate localDate) {
    return convertLocalDateToSqlDate(localDate);
  }

  public static java.sql.Date convertLocalDateToSqlDate(LocalDate localDate) {
    return (localDate == null) ? null : java.sql.Date.valueOf(localDate);
  }

  /* Via Instant */
  public static java.util.Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
    return (localDateTime == null) ? null : java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static java.util.Date convertLocalDateTimeViaSqlTimestamp(LocalDateTime localDateTime) {
    return convertLocalDateTimeToSqlTimestamp(localDateTime);
  }

  public static java.sql.Timestamp convertLocalDateTimeToSqlTimestamp(LocalDateTime localDateTime) {
    return (localDateTime == null) ? null : java.sql.Timestamp.valueOf(localDateTime);
  }

  public static java.time.LocalDate convertDateToLocalDate(java.sql.Date date) {
    return (date == null) ? null : date.toLocalDate();
  } 
  
  /* Via instant */
  public static java.time.LocalDate convertDateToLocalDate(java.util.Date date) {
    return (date == null) ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }  

  /* via milisecond */
  public static java.time.LocalDate convertDateToLocalDateViaMilisecond(java.util.Date date) {
    return (date == null) ? null : Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  /* Via SqlDate */
  public static java.time.LocalDate convertDateToLocalDateViaSqlDate(java.util.Date date) {
    return (date == null) ? null : convertDateToSqlDate(date).toLocalDate();
  }

  public static java.sql.Date convertDateToSqlDate(java.util.Date date) {
    return (date == null) ? null : new java.sql.Date(date.getTime());
  }

  public static java.time.LocalDateTime convertDateToLocalDateTime(java.sql.Date date) {
    return (date == null) ? null : Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    //return (date == null) ? null : convertDateToSqlTimestamp(date).toLocalDateTime();
  }
  
  /* Via instant */
  public static java.time.LocalDateTime convertDateToLocalDateTime(java.util.Date date) {
    return (date == null) ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  /* via milisecond */
  public static java.time.LocalDateTime convertDateToLocalDateTimeViaMilisecond(java.util.Date date) {
    return (date == null) ? null : Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  /* Via SqlTimestamp */
  public static java.time.LocalDateTime convertDateToLocalDateTimeViaSqlTimestamp(java.util.Date date) {
    return (date == null) ? null : convertDateToSqlTimestamp(date).toLocalDateTime();
  }

  public static java.sql.Timestamp convertDateToSqlTimestamp(java.util.Date date) {
    return (date == null) ? null : new java.sql.Timestamp(date.getTime());
  }
}
