package sample.models;

import sample.utils.id3.ID3Utils;

import java.util.List;

/**
 * Created by razvanolar on 15.12.2016
 */
public class Node extends TreeNode {

  private NodeValue parentNodeValue;
  private List<NodeValue> nodeValues;
  private int nodeIndex;
  private boolean[] availableAttr;
  private String displayName;

  public Node(List<NodeValue> nodeValues, int nodeIndex, NodeValue parentNodeValue, boolean[] availableAttr) {
    this.nodeValues = nodeValues;
    this.nodeIndex = nodeIndex;
    this.parentNodeValue = parentNodeValue;
    this.availableAttr = availableAttr;
    this.displayName = ID3Utils.namesMap.get((long) nodeIndex);
  }

  public List<NodeValue> getNodeValues() {
    return nodeValues;
  }

  public int getNodeIndex() {
    return nodeIndex;
  }

  public NodeValue getParentNodeValue() {
    return parentNodeValue;
  }

  public boolean[] getAvailableAttr() {
    return availableAttr;
  }

  @Override
  public String toString() {
    return "" + nodeIndex + " " + displayName;
  }

  @Override
  public List<TreeNode> getChildren() {
    return (List<TreeNode>)(List<?>) nodeValues;
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public float getResult() {
    return 0f;
  }

  @Override
  public int getIndex() {
    return nodeIndex;
  }
}
