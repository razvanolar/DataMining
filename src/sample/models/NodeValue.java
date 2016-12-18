package sample.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by razvanolar on 15.12.2016
 */
public class NodeValue extends TreeNode {

  private String value;
  private Node parent;
  private Node child;
  private List<FormattedEntry> entries; // all the entries for this value

  public NodeValue(String value, Node parent, FormattedEntry formattedEntry) {
    this.value = value;
    this.parent = parent;
    this.entries = new ArrayList<>();
    this.entries.add(formattedEntry);
  }

  public void addEntry(FormattedEntry formattedEntry) {
    this.entries.add(formattedEntry);
  }

  public String getValue() {
    return value;
  }

  public Node getParent() {
    return parent;
  }

  public List<FormattedEntry> getEntries() {
    return entries;
  }

  public Node getChild() {
    return child;
  }

  public void setChild(Node child) {
    this.child = child;
  }

  public boolean isLeaf() {
    return child == null;
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public List<TreeNode> getChildren() {
    if (child == null)
      return null;
    List<TreeNode> children = new ArrayList<>();
    children.add(child);
    return children;
  }

  @Override
  public String getDisplayName() {
    return value;
  }

  @Override
  public float getResult() {
    float sum = 0F;
    for (FormattedEntry formattedEntry : entries) {
      sum += formattedEntry.getResultValue();
    }
    return sum / (entries == null ? 1f : (float) entries.size());

  }

  @Override
  public int getIndex() {
    return 0;
  }
}
