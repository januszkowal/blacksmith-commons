package org.blacksmith.commons.collections;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class DebitCredit {
  BigDecimal dr;
  BigDecimal cr;

  public DebitCredit(BigDecimal dr, BigDecimal cr) {
    this.dr = dr;
    this.cr = cr;
  }

  public static DebitCredit of(BigDecimal dr, BigDecimal cr) {
    return new DebitCredit(dr, cr);
  }

  public BigDecimal getDr() {
    return dr;
  }

  public BigDecimal getCr() {
    return cr;
  }

  public DebitCredit add(DebitCredit augend) {
    return new DebitCredit(this.dr.add(augend.dr), this.cr.add(augend.cr));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DebitCredit that = (DebitCredit) o;
    return dr.equals(that.dr) && cr.equals(that.cr);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DebitCredit.class.getSimpleName() + "[", "]")
        .add("dr=" + dr)
        .add("cr=" + cr)
        .toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(dr, cr);
  }
}
