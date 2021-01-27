package org.blacksmith.commons.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class KVSplitterTest {

  @Test
  public void testDefault() {
    var splitter = KVSplitter.builder().build();
    var result = splitter.splitToList("v1=abc;v2=def");
    assertThat(result).extracting(Pair::getLeft).containsExactly("v1", "v2");
    assertThat(result).extracting(Pair::getRight).containsExactly("abc", "def");
  }

  @Test
  public void testDefaultAndTrim() {
    var splitter = KVSplitter.builder().trimResults(true).build();
    var result = splitter.splitToList("v1= abc ;v2= def ");
    assertThat(result).extracting(Pair::getLeft).containsExactly("v1", "v2");
    assertThat(result).extracting(Pair::getRight).containsExactly("abc", "def");
  }

  @Test
  public void testCustom1() {
    var splitter = KVSplitter.builder().withPairSeparator("|").withKeyValueSeparator("@").build();
    var result = splitter.splitToList("v1@abc|v2@def");
    assertThat(result).extracting(Pair::getLeft).containsExactly("v1", "v2");
    assertThat(result).extracting(Pair::getRight).containsExactly("abc", "def");
  }

  @Test
  public void testCustom2() {
    var splitter = KVSplitter.builder().withPairSeparator('|').withKeyValueSeparator('@').build();
    var result = splitter.splitToList("v1@abc|v2@def");
    assertThat(result).extracting(Pair::getLeft).containsExactly("v1", "v2");
    assertThat(result).extracting(Pair::getRight).containsExactly("abc", "def");
  }

}
