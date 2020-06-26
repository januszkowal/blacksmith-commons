package org.blacksmith.commons.object;

@SuppressWarnings("unused")
public class Dummy1 {
  private String s1;
  private final String s2;
  public Dummy1(String s1, String s2) {
    this.s1 = s1;
    this.s2 = s2;
  }
  public String getS1() {return s1;}
  public void setS1(String s1) {
    this.s1 = s1;
  }
  public String getS2() {return s2;}
}
