package org.blacksmith.commons.datetime;

import java.time.LocalDate;

public enum TimeUnit implements DateOperation {
  DAY{
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusDays(amount);
    }
    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusDays(amount);
    }
  },
  WEEK{
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusWeeks(amount);
    }

    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusWeeks(amount);
    }
  },
  MONTH{
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusMonths(amount);
    }

    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusMonths(amount);
    }
  },
  QUARTER{
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusMonths(3L*amount);
    }

    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusMonths(3L*amount);
    }
  },
  HALF_YEAR{
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusMonths(6L*amount);
    }

    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusMonths(6L*amount);
    }
  },
  YEAR{
    @Override public LocalDate plus(LocalDate date, int amount) {
      return date.plusYears(amount);
    }

    @Override public LocalDate minus(LocalDate date, int amount) {
      return date.minusYears(amount);
    }
  };
}
