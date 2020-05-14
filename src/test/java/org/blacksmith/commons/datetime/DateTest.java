package org.blacksmith.commons.datetime;

import java.time.LocalDate;
import org.blacksmith.test.TimingExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Date Utils")
@ExtendWith(TimingExtension.class)
public class DateTest {

  @Test
  @DisplayName("Test jdk plusDays")
  public void speed1() {
    LocalDate ref = LocalDate.of(2019, 03, 15);
    for (int i = 0; i < 100000000; i++) {
      LocalDate x = ref.plusDays(i);
    }
  }

  @Test
  @DisplayName("Test ref plusDays")
  public void speed2() {
    LocalDate ref = LocalDate.of(2019, 03, 15);
    for (int i = 0; i < 100000000; i++) {
      LocalDate x = LocalDateUtilsRef.plusDays(ref,i);
    }
  }

  @Test
  @DisplayName("Test datetime streamx")
  public void dateStreamX() {
    System.out.println("stream1:" + LocalDateUtilsRef.stream(LocalDate.now(),LocalDate.now().plusDays(10000)).count());
  }
  @Test
  @DisplayName("Test datetime stream")
  public void dateStream() {
    System.out.println("stream2:"+ DateUtils.stream(DateRange.closed(LocalDate.now(),LocalDate.now().plusDays(10000))).count());
  }
  @Test
  @DisplayName("LeapDay test")
  public void leapDayTest() {
    assertEquals(LocalDate.of(2020,02,29), DateUtils.nextLeapDay(LocalDate.of(2019,03,01)));
    assertEquals(LocalDate.of(2020,02,29), DateUtils.nextLeapDay(LocalDate.of(2019,01,29)));
    assertEquals(LocalDate.of(2020,02,29), DateUtils.nextLeapDay(LocalDate.of(2020,02,28)));
    assertEquals(LocalDate.of(2024,02,29), DateUtils.nextLeapDay(LocalDate.of(2020,02,29)));
    assertEquals(LocalDate.of(2024,02,29), DateUtils.nextLeapDay(LocalDate.of(2020,03,29)));
  }
  @Test
  @DisplayName("LeapDayRange test")
  public void LeapDayRange() {
    assertEquals(0,DateUtils.numberOfLeapDays(LocalDate.of(2020,3,1),LocalDate.of(2023,2,1)));
    assertEquals(0,DateUtils.numberOfLeapDays(LocalDate.of(2020,3,1),LocalDate.of(2024,2,28)));
    assertEquals(1,DateUtils.numberOfLeapDays(LocalDate.of(2020,2,1),LocalDate.of(2024,2,28)));
    assertEquals(1,DateUtils.numberOfLeapDays(LocalDate.of(2020,2,28),LocalDate.of(2024,2,28)));
    assertEquals(1,DateUtils.numberOfLeapDays(LocalDate.of(2020,2,29),LocalDate.of(2024,2,28)));
    assertEquals(2,DateUtils.numberOfLeapDays(LocalDate.of(2020,2,29),LocalDate.of(2024,2,29)));
  }
  @Test
  @DisplayName("LeapDayRange1Test")
  public void LeapDayRange1Test() {
    System.out.println("leap days1="+DateUtils.numberOfLeapDays(LocalDate.of(1900,1,1),LocalDate.of(2100,1,1)));
  }
  @Test
  @DisplayName("LeapDayRange2Test")
  public void LeapDayRange2Test() {
    System.out.println("leap days2="+DateUtils.numberOfLeapDays2(LocalDate.of(1900,1,1),LocalDate.of(2100,1,1)));
  }
  @Test
  @DisplayName("TimeUnitOperationTest")
  public void TimeUnitOperationTest() {
    assertEquals(LocalDate.of(2019, 1, 4),TimeUnit.DAY.plus(LocalDate.of(2019, 1,1),3));
  }
}
