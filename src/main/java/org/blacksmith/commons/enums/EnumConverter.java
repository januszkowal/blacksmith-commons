package org.blacksmith.commons.enums;

public interface EnumConverter<V, E extends Enum<E>> {

  E convert(V value);
}
