package org.blacksmith.commons.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class MapsMerger {

  private MapsMerger() {
  }

  /* creates new map as a result of merge */
  public static <K1, K2, V, M extends Map<K1, Map<K2, V>>> M mergeTwoLevelMaps(M m1, M m2,
      BinaryOperator<V> merger) {
    Map<K1, Map<K2, V>> result = new HashMap<>();
    m1.forEach((m1k1, m1v1) -> {
      var dateMap = result.computeIfAbsent(m1k1, map -> new HashMap<>());
      m1v1.forEach(dateMap::put);
    });

    m2.forEach((m2k1, m2v1) -> {
      var dateMap = result.computeIfAbsent(m2k1, map -> new HashMap<>());
      m2v1.forEach((m2k2, m2v2) -> dateMap.merge(m2k2, m2v2, merger));
    });
    return (M) result;
  }

  public static <K1, K2, V, M extends Map<K1, Map<K2, V>>> BinaryOperator<M> twoLevelMapsMerger(
      BinaryOperator<V> mergeFunction) {
    return (m1, m2) -> {
      m2.forEach((m2k1, m2v1) -> {
        var dateMap = m1.computeIfAbsent(m2k1, map -> new HashMap<>());
        m2v1.forEach((m2k2, m2v2) -> dateMap.merge(m2k2, m2v2, mergeFunction));
      });
      return m1;
    };
  }
}
