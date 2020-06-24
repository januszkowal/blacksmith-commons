package org.blacksmith.commons.datetime;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;

class DateConversionTest {
  final LocalDate d1= LocalDate.of(2020,6,15);
  final LocalDateTime dt1= LocalDateTime.of(2020,6,15,12,35,14,123);
  @Test
  void convertLocalDateToDate() {
    assertEquals(createDate(d1),
        DateConversion.convertLocalDateToDate(d1));
  }

  @Test
  void convertLocalDateToSqlDate() {
    assertEquals(createSqlDate(d1),
        DateConversion.convertLocalDateToSqlDate(d1));
  }

  @Test
  void convertLocalDateTimeToDate() {
    assertEquals(createDate(dt1),
        DateConversion.convertLocalDateTimeToDate(dt1));
  }

  @Test
  void convertLocalDateTimeToSqlTimestamp() {
    assertEquals(createSqlTimestamp(dt1),
        DateConversion.convertLocalDateTimeToSqlTimestamp(dt1));
  }

  @Test
  void convertDateToLocalDate() {
    assertEquals(d1,
        DateConversion.convertDateToLocalDate(createDate(d1)));
  }

  @Test
  void convertDateToSqlDate() {
    assertEquals(createSqlDate(d1),
        DateConversion.convertDateToSqlDate(createDate(d1)));
  }

  @Test
  void convertSqlDateToLocalDate() {
    assertEquals(d1,
        DateConversion.convertSqlDateToLocalDate(createSqlDate(d1)));
  }

  @Test
  void convertDateToLocalDateTime() {
    assertEquals(d1.atStartOfDay(),
        DateConversion.convertDateToLocalDateTime(createDate(d1)));
  }

  @Test
  void convertSqlDateToLocalDateTime() {
    assertEquals(d1.atStartOfDay(),
        DateConversion.convertSqlDateToLocalDateTime(createSqlDate(d1)));
  }

  @Test
  void createLocalDateTimeFromMillis() {
    assertEquals(dt1,
        DateConversion.createLocalDateTimeFromMillis(createDate(d1).getTime()));
  }

  @Test
  void convertDateToSqlTimestamp() {
    assertEquals(createSqlTimestamp(dt1),
        DateConversion.convertDateToSqlTimestamp(createDate(dt1)));
  }

  @Test
  void convertDateToLocalTime() {
    assertEquals(dt1.toLocalTime(),
        DateConversion.convertDateToLocalTime(createDate(dt1)));
  }

  private java.util.Date createDate(LocalDate d) {
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(Calendar.YEAR,d.getYear());
    cal.set(Calendar.MONTH,d.getMonthValue()-1);
    cal.set(Calendar.DAY_OF_MONTH,d.getDayOfMonth());
    return new Date(cal.toInstant().toEpochMilli());
  }

  private java.util.Date createDate(LocalDateTime dt) {
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(Calendar.YEAR,dt.getYear());
    cal.set(Calendar.MONTH,dt.getMonthValue()-1);
    cal.set(Calendar.DAY_OF_MONTH,dt.getDayOfMonth());
    cal.set(Calendar.HOUR_OF_DAY,dt.getHour());
    cal.set(Calendar.MINUTE,dt.getMinute());
    cal.set(Calendar.SECOND,dt.getSecond());
    return new Date(cal.toInstant().toEpochMilli());
  }

  private java.sql.Date createSqlDate(LocalDate d) {
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(Calendar.YEAR,d.getYear());
    cal.set(Calendar.MONTH,d.getMonthValue()-1);
    cal.set(Calendar.DAY_OF_MONTH,d.getDayOfMonth());
    return new java.sql.Date(cal.toInstant().toEpochMilli());
  }

  private java.sql.Date createSqlDate(LocalDateTime dt) {
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(Calendar.YEAR,dt.getYear());
    cal.set(Calendar.MONTH,dt.getMonthValue()-1);
    cal.set(Calendar.DAY_OF_MONTH,dt.getDayOfMonth());
    cal.set(Calendar.HOUR_OF_DAY,dt.getHour());
    cal.set(Calendar.MINUTE,dt.getMinute());
    cal.set(Calendar.SECOND,dt.getSecond());
    return new java.sql.Date(cal.toInstant().toEpochMilli());
  }

  private java.sql.Timestamp createSqlTimestamp(LocalDateTime dt) {
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(Calendar.YEAR,dt.getYear());
    cal.set(Calendar.MONTH,dt.getMonthValue()-1);
    cal.set(Calendar.DAY_OF_MONTH,dt.getDayOfMonth());
    cal.set(Calendar.HOUR_OF_DAY,dt.getHour());
    cal.set(Calendar.MINUTE,dt.getMinute());
    cal.set(Calendar.SECOND,dt.getSecond());
    Timestamp result = new Timestamp(cal.toInstant().toEpochMilli());
    result.setNanos(dt.getNano());
    return result;
  }
}