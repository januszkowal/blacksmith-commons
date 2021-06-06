package org.blacksmith.commons.enums;

public enum XEnumWithLazyCache {

  A("aX"),
  B("By"),
  c("CU");

  private static EnumConverter<String, XEnumWithLazyCache> cache = EnumConverterFactory.of(XEnumWithLazyCache.class, XEnumWithLazyCache::getValue, true);
  private final String value;

  XEnumWithLazyCache(String value) {
    this.value = value;
  }

  public static XEnumWithLazyCache fromValue(String value) {
    return cache.fromValue(value);
  }

//  @Override
  public String getValue() {
    return this.value;
  }
}
