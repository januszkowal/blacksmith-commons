package org.blacksmith.commons.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class BooleanStateCounter {

  private AtomicInteger trueCounter = new AtomicInteger();
  private AtomicInteger falseCounter = new AtomicInteger();

  public void update(boolean value) {
    if (value) {
      trueCounter.incrementAndGet();
    } else {
      falseCounter.incrementAndGet();
    }
  }

  public void updateTrue() {
    trueCounter.incrementAndGet();
  }

  public void updateFalse() {
    falseCounter.incrementAndGet();
  }

  public boolean hasTrue() {
    return trueCounter.get() > 0;
  }

  public boolean hasFalse() {
    return falseCounter.get() > 0;
  }

  public boolean hasValue(boolean value) {
    return getCount(value) > 0;
  }

  public int getCount(boolean value) {
    return value ? trueCounter.get() : falseCounter.get();
  }
}
