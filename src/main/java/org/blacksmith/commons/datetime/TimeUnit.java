package org.blacksmith.commons.datetime;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum TimeUnit implements DateOperation {
  DAY("D","Day"){
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusDays(amount);
    }
    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusDays(amount);
    }
  },
  WEEK("W","Week"){
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusWeeks(amount);
    }

    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusWeeks(amount);
    }
  },
  MONTH("M","Month"){
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusMonths(amount);
    }

    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusMonths(amount);
    }
  },
  QUARTER("Q","Quarter"){
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusMonths(3L*amount);
    }

    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusMonths(3L*amount);
    }
  },
  HALF_YEAR("H","Half-Year"){
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusMonths(6L*amount);
    }

    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusMonths(6L*amount);
    }
  },
  YEAR("Y","Year"){
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusYears(amount);
    }

    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusYears(amount);
    }
  };
  private String symbol;
  private String symbolName;
  private Map<String,TimeUnit> unitMap= Arrays.stream(TimeUnit.values()).collect(Collectors.toMap(TimeUnit::symbol, e -> e));
  TimeUnit(String symbol, String symbolName) {
    this.symbol = symbol;
    this.symbolName = symbolName;
  }
  public String symbol() {
    return this.symbol;
  }
  public String symbolName() {
    return this.symbolName;
  }
  public TimeUnit of(String symbol) {
    return unitMap.get(symbol);
  }
}
