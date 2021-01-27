package org.blacksmith.commons.datetime;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;

class DateConversionTest {
  final LocalDate d1= LocalDate.of(2020,6,15);
  final LocalDateTime dt1= LocalDateTime.of(2020,6,15,12,35,14,123);
  final LocalDateTime dt1n = LocalDateTime.of(2020,6,15,12,35,14);
  @Test
  void convertLocalDateToDate() {
    assertEquals(DateFactory.createDate(d1),
        DateConversion.convertLocalDateToDate(d1));
  }

  @Test
  void convertLocalDateToSqlDate() {
    assertEquals(DateFactory.createSqlDate(d1),
        DateConversion.convertLocalDateToSqlDate(d1));
  }

  @Test
  void convertLocalDateTimeToDate() {
    assertEquals(DateFactory.createDate(dt1),
        DateConversion.convertLocalDateTimeToDate(dt1));
  }

  @Test
  void convertLocalDateTimeToSqlTimestamp() {
    assertEquals(DateFactory.createSqlTimestamp(dt1),
        DateConversion.convertLocalDateTimeToSqlTimestamp(dt1));
  }

  @Test
  void convertDateToLocalDate() {
    assertEquals(d1,
        DateConversion.convertDateToLocalDate(DateFactory.createDate(d1)));
  }

  @Test
  void convertDateToSqlDate() {
    assertEquals(DateFactory.createSqlDate(d1),
        DateConversion.convertDateToSqlDate(DateFactory.createDate(d1)));
  }

  @Test
  void convertSqlDateToLocalDate() {
    assertEquals(d1,
        DateConversion.convertSqlDateToLocalDate(DateFactory.createSqlDate(d1)));
    assertEquals(d1,
        DateConversion.convertSqlDateToLocalDate(DateFactory.createSqlDate(dt1)));
  }

  @Test
  void convertDateToLocalDateTime() {
    assertEquals(d1.atStartOfDay(),
        DateConversion.convertDateToLocalDateTime(DateFactory.createDate(d1)));
  }

  @Test
  void convertSqlDateToLocalDateTime() {
    assertEquals(d1.atStartOfDay(),
        DateConversion.convertSqlDateToLocalDateTime(DateFactory.createSqlDate(d1)));
    assertEquals(dt1n,
        DateConversion.convertSqlDateToLocalDateTime(DateFactory.createSqlDate(dt1)));
    assertEquals(dt1n,
        DateConversion.convertSqlDateToLocalDateTime(DateFactory.createSqlDate(dt1n)));
  }

  @Test
  void createLocalDateTimeFromMillis() {
    assertEquals(d1.atStartOfDay(),
        DateConversion.createLocalDateTimeFromMillis(DateFactory.createDate(d1).getTime()));
    assertEquals(dt1n,
        DateConversion.createLocalDateTimeFromMillis(DateFactory.createDate(dt1n).getTime()));
  }

  @Test
  void convertDateToSqlTimestamp() {
    assertEquals(DateFactory.createSqlTimestampWithoutNanos(dt1),
        DateConversion.convertDateToSqlTimestamp(DateFactory.createDate(dt1)));
  }

  @Test
  void convertDateToLocalTime() {
    assertEquals(dt1n.toLocalTime(),
        DateConversion.convertDateToLocalTime(DateFactory.createDate(dt1)));
  }

  @Test
  void convertDateToZonedDateTime() {
    ZoneId defaultZone = ZoneId.systemDefault();
    ZoneId warsawZone = ZoneId.of("Europe/Warsaw");
    assertEquals(ZonedDateTime.of(dt1n,defaultZone),
        DateConversion.convertDateToZonedDateTime(DateFactory.createDate(dt1n)));
    assertEquals(ZonedDateTime.of(dt1n,defaultZone),
        DateConversion.convertDateToZonedDateTime(DateFactory.createDate(dt1n), defaultZone));
    assertEquals(ZonedDateTime.of(dt1n,warsawZone),
        DateConversion.convertDateToZonedDateTime(DateFactory.createDate(dt1n),warsawZone));
  }

  @Test
  void createZonedDateTimeFromDateViaInstant() {
    ZoneId defaultZone = ZoneId.systemDefault();
    ZoneId warsawZone = ZoneId.of("Europe/Warsaw");
    assertEquals(ZonedDateTime.of(dt1n,defaultZone),
        DateConversion.createZonedDateTimeFromDateViaInstant(DateFactory.createDate(dt1n)));
    assertEquals(ZonedDateTime.of(dt1n,defaultZone),
        DateConversion.createZonedDateTimeFromDateViaInstant(DateFactory.createDate(dt1n), defaultZone));
    assertEquals(ZonedDateTime.of(dt1n,warsawZone),
        DateConversion.createZonedDateTimeFromDateViaInstant(DateFactory.createDate(dt1n),warsawZone));
  }

}
