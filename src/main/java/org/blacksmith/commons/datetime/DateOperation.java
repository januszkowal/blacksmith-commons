package org.blacksmith.commons.datetime;

import java.time.LocalDate;

public interface DateOperation {
  LocalDate plus(LocalDate date, int amount);
  LocalDate minus(LocalDate date, int amount);
}
