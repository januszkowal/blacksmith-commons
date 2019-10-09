package org.blacksmith.commons.datetime;

import java.time.temporal.Temporal;

public interface DateOperation {
  <R extends Temporal> R plus(R t, int amount);
  <R extends Temporal> R minus(R t, int amount);
}
