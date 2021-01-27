package org.blacksmith.commons.property;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class PropertyUpdater<O, P> {

  private final Function<O, P> getter;
  private final BiConsumer<O, P> setter;

  public PropertyUpdater(Function<O, P> getter, BiConsumer<O, P> setter) {
    this.getter = getter;
    this.setter = setter;
  }

  public boolean set(O object, P newValue) {
    P oldValue = getter.apply(object);
    if (oldValue == newValue) {
      return false;
    }
    if (oldValue == null || newValue == null) {
      setter.accept(object, newValue);
      return true;
    }
    if (!oldValue.equals(newValue)) {
      setter.accept(object, newValue);
      return true;
    }
    return false;
  }
}
