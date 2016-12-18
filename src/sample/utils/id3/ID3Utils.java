package sample.utils.id3;

import sample.models.AttributeRange;
import sample.models.FormattedEntry;
import sample.models.Node;
import sample.models.NodeValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by razvanolar on 15.12.2016
 */
public class ID3Utils {

  public static int ATTR_NUMBER;

  public static Map<Long, List<AttributeRange>> attributeRangeMap;

  public static Map<Long, String> namesMap;

  public static Node getBestNode(boolean[] valid_attrs, List<FormattedEntry> entries, NodeValue parentNodeValue, double globalEntropy) {
    List<Node> nodes = new ArrayList<>(getValidAttributesNumber(valid_attrs));

    for (int i = 0; i < valid_attrs.length; i ++) { // i is representing the node index (attribute index)
      if (!valid_attrs[i]) // false if the attribute was already used
        continue;
      List<NodeValue> nodeValues = new ArrayList<>();
      Node node = new Node(nodeValues, i, parentNodeValue, copyBoolArray(valid_attrs));
      for (FormattedEntry formattedEntry : entries) { // get all entries for the current attribute
        String value = formattedEntry.getValues().get(i);
        NodeValue nodeValue = getNodeValue(nodeValues, value, i);
        if (nodeValue == null) {
          nodeValue = new NodeValue(value, node, formattedEntry);
          nodeValues.add(nodeValue);
        } else {
          nodeValue.addEntry(formattedEntry);
        }
      }
      nodes.add(node);
    }

    double maxGain = 0;
    Node n = null;

    for (Node node : nodes) {
      double entropy = calculateEntropyForNode(node);
      double gain = globalEntropy - entropy;
      if (maxGain < gain) {
        maxGain = gain;
        n = node;
      }
    }

    return n;
  }

  public static double calculateEntropyForNode(Node node) {
    double total = 0;
    double[][] sumAndEntropyMatrix = new double[node.getNodeValues().size()][2];
    for (int i = 0; i < node.getNodeValues().size(); i ++) {
      NodeValue nodeValue = node.getNodeValues().get(i);
      total += (double) nodeValue.getEntries().size();
      sumAndEntropyMatrix[i][0] = (double) nodeValue.getEntries().size();
      sumAndEntropyMatrix[i][1] = calculateEntropyForNodeValue(nodeValue);
    }

    double result = 0;
    for (int i = 0; i < node.getNodeValues().size(); i ++) {
      result += (sumAndEntropyMatrix[i][0] / total) * sumAndEntropyMatrix[i][1];
    }
    return result;
  }

  /**
   * Calculate the entropy for a NodeValue
   */
  public static double calculateEntropyForNodeValue(NodeValue nodeValue) {
    return calculateResultEntropy(nodeValue.getEntries());
  }

  /**
   * Calculate the entropy of the result
   */
  public static double calculateResultEntropy(List<FormattedEntry> entries) {
    int[] response_array = new int[FormattedEntry.POSSIBLE_RESULTS.size()];
    for (FormattedEntry formattedEntry : entries)
      response_array[getIndexForResult(formattedEntry.getResult())] ++;
    return calculateSimpleEntropy(Arrays.stream(response_array).boxed().collect(Collectors.toList()));
  }

  /**
   * Calculate the entropy for the received values
   */
  public static double calculateSimpleEntropy(List<Integer> values) {
    int sum = 0;
    for (int val : values)
      sum += val;

    double result = 0;
    for (int val : values) {
      double p = (double) val / (double) sum;
      if (p > 0.0)
        result += -(p) * (Math.log(p) / Math.log(2));
    }

    return result;
  }

  public static String getResultByIndex(int index) {
    return FormattedEntry.POSSIBLE_RESULTS.get(index);
  }

  public static int getIndexForResult(String result) {
    return FormattedEntry.POSSIBLE_RESULTS.indexOf(result);
  }

  public static boolean[] createBoolArray() {
    boolean[] array = new boolean[ATTR_NUMBER];
    for (int i = 0; i < ATTR_NUMBER; i ++)
      array[i] = true;
    return array;
  }

  public static boolean[] copyBoolArray(boolean[] array) {
    boolean[] copy = new boolean[array.length];
    for (int i = 0; i < array.length; i ++)
      copy[i] = array[i];
    return copy;
  }

  public static boolean[] getUpdatedBoolArray(boolean[] array, int falsePosition) {
    boolean[] copy = copyBoolArray(array);
    copy[falsePosition] = false;
    return copy;
  }

//

  private static int getValidAttributesNumber(boolean[] valid_attrs) {
    int count = 0;
    for (boolean b : valid_attrs)
      count ++;
    return count;
  }

  private static NodeValue getNodeValue(List<NodeValue> nodeValues, String value, int nodeIndex) {
    for (NodeValue nodeValue : nodeValues) {
      if (nodeValue.getValue().equals(value) && nodeValue.getParent().getNodeIndex() == nodeIndex)
        return nodeValue;
    }
    return null;
  }
}
