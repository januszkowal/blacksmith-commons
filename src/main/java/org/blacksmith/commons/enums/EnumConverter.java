package org.blacksmith.commons.enums;

public interface EnumConverter<K, E extends Enum<E>>{
  E fromValue(K value);
}
