package sample.models;

import sample.utils.id3.ID3Utils;

import java.util.*;

/**
 * Created by razvanolar on 15.12.2016
 */
public class FormattedEntry extends Entry {

  public FormattedEntry(String line) {
    String[] split = line.split(",");
    values = new ArrayList<>(split.length - 1);
    for (int i = 0; i < split.length - 1; i++) {
      if (ID3Utils.attributeRangeMap.get((long) i) != null) {
        values.add(getValueName(i, Float.parseFloat(split[i])));
      } else {
        values.add(split[i]);
      }
    }
    result = split[split.length - 1];
    resultValue = Float.parseFloat(result);
    if (ID3Utils.attributeRangeMap.get((long) split.length - 1) != null)
      result = getValueName(split.length - 1, resultValue);

    if (!POSSIBLE_RESULTS.contains(result))
      POSSIBLE_RESULTS.add(result);
  }
}
