package org.blacksmith.commons.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KVSplitter {

  private final Pattern pattern;
  private final boolean trimResults;

  public KVSplitter(Pattern pattern, boolean trimResults) {
    this.pattern = pattern;
    this.trimResults = trimResults;
  }

  public static KVSplitterBuilder builder() {
    return new KVSplitterBuilder();
  }

  public Map<String, String> splitToMap(String text) {
    Matcher m = pattern.matcher(text);
    Map<String, String> result = new HashMap<>();
    while (m.find()) {
      String key = trimResults ? m.group(1).trim() : m.group(1);
      String value = trimResults ? m.group(2).trim() : m.group(2);
      result.put(key, value);
    }
    return result;
  }

  public List<Pair<String, String>> splitToList(String text) {
    Matcher m = pattern.matcher(text);
    List<Pair<String, String>> result = new ArrayList<>();
    while (m.find()) {
      String key = trimResults ? m.group(1).trim() : m.group(1);
      String value = trimResults ? m.group(2).trim() : m.group(2);
      result.add(Pair.of(key, value));
    }
    return result;
  }

  public static class KVSplitterBuilder {

    private String keyValueSeparator = "=";
    private String pairSeparator = ";";
    private boolean trimResults = false;

    public KVSplitterBuilder() {
    }

    public KVSplitterBuilder withKeyValueSeparator(char keyValueSeparator) {
      this.keyValueSeparator = String.valueOf(keyValueSeparator);
      return this;
    }

    public KVSplitterBuilder withKeyValueSeparator(String keyValueSeparator) {
      this.keyValueSeparator = keyValueSeparator;
      return this;
    }

    public KVSplitterBuilder withKeyValueWhiteSpaceSeparator() {
      this.keyValueSeparator = "\\s+";
      return this;
    }

    public KVSplitterBuilder withPairSeparator(char pairSeparator) {
      this.pairSeparator = String.valueOf(pairSeparator);
      return this;
    }

    public KVSplitterBuilder withPairSeparator(String pairSeparator) {
      this.pairSeparator = pairSeparator;
      return this;
    }

    public KVSplitterBuilder trimResults(boolean trimResults) {
      this.trimResults = trimResults;
      return this;
    }

    public KVSplitter build() {
      String pattern;
      if (keyValueSeparator.equals(" ") || keyValueSeparator.equals("\\s+")) {
        pattern = "(?:(\\w*)" + keyValueSeparator + "(\\w*\\s*))(?=" + pairSeparator + "|$)";
      } else {
        pattern = "(?:(\\s*\\w*\\s*)" + keyValueSeparator + "(\\s*\\w*\\s*))(?=" + pairSeparator + "|$)";
      }
      return new KVSplitter(Pattern.compile(pattern), trimResults);
    }
  }
}
