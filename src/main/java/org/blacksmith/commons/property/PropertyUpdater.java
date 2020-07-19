package org.blacksmith.commons.property;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PropertyUpdater<O,P> {

  private final Function<O,P> getter;
  private final BiConsumer<O,P> setter;

  public PropertyUpdater(Function<O,P> getter, BiConsumer<O,P> setter) {
    this.getter = getter;
    this.setter = setter;
  }

  public boolean set(O object, P newValue) {
    P oldValue = getter.apply(object);
    if (oldValue==null && newValue==null) {
      return false;
    }
    else if (oldValue==null || newValue==null) {
      setter.accept(object,newValue);
      return true;
    }
    else if (!oldValue.equals(newValue)){
      setter.accept(object,newValue);
      return true;
    }
    return false;
  }
}