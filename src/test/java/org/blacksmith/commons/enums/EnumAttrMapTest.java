package org.blacksmith.commons.enums;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnumAttrMapTest {
  public enum Eat {
    A("aX"),
    B("By"),
    c("CU");

    private final String value;

    private static final EnumValueConverter<String, Eat> enumConverter = EnumValueConverter.of(Eat.class, Eat::getValue);

    Eat(String value) {
      this.value = value;
    }

    public String getValue() {
      return this.value;
    }

    public static Eat fromValue(String value) {
      return enumConverter.convert(value);
    }
  }

  @Test
  public void shouldGetEnum() {
    assertThat(Eat.fromValue("aX")).isEqualTo(Eat.A);
  }

  @Test
  public void shouldThrowException() {
    Assertions.assertThrows(EnumConversionException.class, () -> {
      Eat.fromValue("a");
    });
  }

}
