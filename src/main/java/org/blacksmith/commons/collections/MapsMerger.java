package org.blacksmith.commons.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MapsMerger {
  private MapsMerger() {}
  public static <K1, K2, T> Map<K1, Map<K2, T>> mergeTwoLevelMaps(Map<K1, Map<K2, T>> map1, Map<K1, Map<K2, T>> map2,
      BiFunction<T, T, T> merger) {
    Map<K1, Map<K2, T>> result = new HashMap<>();
    map1.forEach((kId, eId) -> {
      var dateMap = result.computeIfAbsent(kId, map -> new HashMap<>());
      eId.forEach(dateMap::put);
    });

    map2.forEach((kId, eId) -> {
      var dateMap = result.computeIfAbsent(kId, map -> new HashMap<>());
      eId.forEach((kDate, eCr) -> dateMap.merge(kDate, eCr, merger));
    });
    return result;
  }
}
