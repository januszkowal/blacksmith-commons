package org.blacksmith.commons.string;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SplitterTest {

  @Test
  public void test1() {
    var result = KVSplitter.builder().trimResults(false).build().splitToMap("key1 = value1x ;key2=value2");
    System.out.println(result);

    Assertions.assertThat(result)
        .containsExactlyInAnyOrderEntriesOf(Map.of("key1 ", " value1x ", "key2", "value2"));
  }

  @Test
  public void test2() {
    var result = KVSplitter.builder().trimResults(true).withKeyValueWhiteSpaceSeparator().build()
        .splitToMap("key1 value1;key2 value2");
    System.out.println(result);
    Assertions.assertThat(result)
        .containsExactlyInAnyOrderEntriesOf(Map.of("key1", "value1", "key2", "value2"));
  }
}
