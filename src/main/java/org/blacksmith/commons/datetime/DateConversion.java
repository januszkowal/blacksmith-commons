package org.blacksmith.commons.datetime;

import java.time.LocalDate;
import java.time.ZoneId;

public class DateConversion {
  private DateConversion() {}
  public static java.util.Date convertLocalDateToDate(LocalDate date) {
    return java.util.Date.from(date.atStartOfDay()
        .atZone(ZoneId.systemDefault())
        .toInstant());
  }
}
