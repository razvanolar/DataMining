package sample.models;

import sample.utils.id3.ID3Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristi on 12/17/2016.
 */
public abstract  class Entry {
  public static List<String> POSSIBLE_RESULTS = new ArrayList<>();

  protected List<String> values;
  protected String result;
  protected Float resultValue;

  protected String getValueName(int i, Float value) {
    for (AttributeRange attributeRange : ID3Utils.attributeRangeMap.get((long) i)) {
      if ( (attributeRange.getMin() == null || value >= attributeRange.getMin()) && (attributeRange.getMax() == null || value < attributeRange.getMax())) {
        return attributeRange.getDisplayName();
      }
    }
    return "noName";
  }

  public List<String> getValues() {
    return values;
  }

  public int getResultIndex() {
    return FormattedEntry.POSSIBLE_RESULTS.indexOf(result);
  }

  public String getResult() {
    return result;
  }

  public String getByIndex(int index) {
    if (values == null || index < 0 || index > values.size())
      return "";
    if (index == values.size())
      return result;
    return values.get(index);
  }

  @Override
  public String toString() {
    String res = "[";
    for (int i = 0; i < values.size(); i ++) {
      res += values.get(i);
      if (i < values.size() - 1)
        res += ", ";
    }
    res += "]";
    return res + " -> [" + result + "]";
  }

  public Float getResultValue() {
    return resultValue;
  }
}
