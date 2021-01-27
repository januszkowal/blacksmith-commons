package org.blacksmith.commons.datetime;

import java.time.temporal.Temporal;

public interface DateOperation {

  <T extends Temporal> T addTo(T t, int q);

  default <T extends Temporal> T addTo(T t) {
    return addTo(t, 1);
  }

  <T extends Temporal> T addToWithEomAdjust(T t, int q, boolean eomAdjust);

  default <T extends Temporal> T addToWithEomAdjust(T t, boolean eomAdjust) {
    return addToWithEomAdjust(t, 1, eomAdjust);
  }

  <T extends Temporal> T minusFrom(T t, int q);

  default <T extends Temporal> T minusFrom(T t) {
    return minusFrom(t, 1);
  }
}
