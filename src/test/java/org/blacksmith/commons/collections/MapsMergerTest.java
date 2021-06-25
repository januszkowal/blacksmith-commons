package org.blacksmith.commons.collections;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MapsMergerTest {

  public static DebitCredit vvv(double dr, double cr) {
    return new DebitCredit(BigDecimal.valueOf(dr), BigDecimal.valueOf(cr));
  }

  @Test
  public void combine1() {
    Map<Long, Map<LocalDate, DebitCredit>> map1 = Map.of(
        1L, Map.of(
            LocalDate.of(2021, 1, 1), vvv(1, 7),
            LocalDate.of(2021, 1, 2), vvv(3, 2)),
        2L, Map.of(
            LocalDate.of(2021, 1, 1), vvv(3, 1),
            LocalDate.of(2021, 1, 2), vvv(4, 2)));
    Map<Long, Map<LocalDate, DebitCredit>> map2 = Map.of(
        1L, Map.of(
            LocalDate.of(2021, 1, 1), vvv(2, 8)//,
            //            LocalDate.of(2021, 1, 2), vvv(3, 2)
        ),
        3L, Map.of(
            LocalDate.of(2021, 1, 1), vvv(3, 1),
            LocalDate.of(2021, 1, 2), vvv(4, 2)));

    var mergeResult = MapsMerger.mergeTwoLevelMaps(map1, map2, DebitCredit::add);
    Assertions.assertThat(mergeResult).containsExactly(
        Map.entry(
            1L, Map.of(
                LocalDate.of(2021, 1, 1), vvv(3, 15),
                LocalDate.of(2021, 1, 2), vvv(3, 2))
        ),
        Map.entry(
            2L, Map.of(
                LocalDate.of(2021, 1, 1), vvv(3, 1),
                LocalDate.of(2021, 1, 2), vvv(4, 2))
        ),
        Map.entry(
            3L, Map.of(
                LocalDate.of(2021, 1, 1), vvv(3, 1),
                LocalDate.of(2021, 1, 2), vvv(4, 2))
        ));
  }

}
