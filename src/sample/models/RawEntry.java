package sample.models;

import java.util.ArrayList;

/**
 * Created by Cristi on 12/17/2016.
 */
public class RawEntry extends Entry {

  public RawEntry(String line) {
    String[] split = line.split(",");
    values = new ArrayList<>(split.length - 1);
    for (int i = 0; i < split.length - 1; i++) {
      values.add(split[i]);
    }
    result = split[split.length - 1];
    resultValue = Float.parseFloat(result);
  }
}
