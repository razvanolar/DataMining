package sample.utils.id3;

import sample.models.*;
import sample.utils.repository.JDBCDao;
import sample.utils.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by razvanolar on 15.12.2016
 */
public class DecisionTree {
  private Repository repo;
  private List<FormattedEntry> entries;
  private List<TreeNode> path = new ArrayList<>();
  private Node root;

  public DecisionTree(Repository repo) {
    this.repo = repo;
    loadData();
  }

  private void loadData() {
    ID3Utils.namesMap = repo.getNamesMap();
    ID3Utils.ATTR_NUMBER = ID3Utils.namesMap.size() - 1;
    ID3Utils.attributeRangeMap = repo.getAttributeRange();
    List<String> strings = repo.getAllValuesAsStringList();
    entries = new ArrayList<>();
    for (String string : strings) {
      entries.add(new FormattedEntry(string));
    }
  }

  public void run() {
    double responseEntropy = ID3Utils.calculateResultEntropy(entries);
    boolean[] valid_attrs = ID3Utils.createBoolArray();
    Node root = ID3Utils.getBestNode(valid_attrs, entries, null, responseEntropy);
    generateTree(root, responseEntropy);
    this.root = root;
  }

  public void generateTree(Node root, double entropy) {
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      if (ID3Utils.calculateEntropyForNode(node) > 0) {
        boolean[] availableAttr = ID3Utils.getUpdatedBoolArray(node.getAvailableAttr(), node.getNodeIndex());
        for (NodeValue nodeValue : node.getNodeValues()) {
          if (ID3Utils.calculateEntropyForNodeValue(nodeValue) > 0) {
            Node newNode = ID3Utils.getBestNode(availableAttr, nodeValue.getEntries(), nodeValue, entropy);
            nodeValue.setChild(newNode);
            if (newNode != null) {
              stack.push(newNode);
            }
          }
        }
      }
    }
    System.out.println("finished...");
  }

  //DFS
  private void print(TreeNode node) {
    if (node == null) {
      printPath();
      return;
    }

    if (node.getChildren() == null || node.getChildren().isEmpty()) {
      path.add(node);
      printPath();
      path.remove(node);
      return;
    }
    path.add(node);
    if (node.getChildren() != null) {
      for (TreeNode treeNode : node.getChildren()) {
          print(treeNode);
      }
    }
    path.remove(node);
  }

  private void printPath() {
    String toPrint = "if (";
    for (int i=0; i<path.size(); i++) {
      TreeNode node = path.get(i);
      if (i % 2 == 0) {
        toPrint += node.getDisplayName() + " == ";
      } else {
        if (i < path.size() - 1) {
          toPrint += node.getDisplayName() + " AND ";
        } else {
          toPrint += node.getDisplayName() + ") -> ";
        }
      }
    }
    toPrint += "" + path.get(path.size() - 1).getResult();
    System.out.println(toPrint);
  }

  public Node getRoot() {
    return root;
  }

  public static void main(String[] args) {
    try {
      long time = System.currentTimeMillis();
//      Repository repo = new FileUtil("D:\\Java Workspace\\DecisionTree\\src\\data\\entries.txt", "D:\\Java Workspace\\DecisionTree\\src\\data\\attribute_range.txt");
      Repository repo = new JDBCDao();

      DecisionTree tree = new DecisionTree(repo);
      tree.run();
      System.out.println(System.currentTimeMillis() - time);
      time = System.currentTimeMillis();
      tree.print(tree.getRoot());
      System.out.println(System.currentTimeMillis() - time);
      System.out.println("Result: " + tree.find(tree.getRoot(), new FormattedEntry("24,1.75,80.7,1,4,5,13.5,0")));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Float find(TreeNode root, FormattedEntry formattedEntry) {
    TreeNode node = root;
    boolean childFound = true;
    while (node != null && node.getChildren() != null && childFound) {  // node = Node
      childFound = false;
      for (TreeNode child : node.getChildren()) { //child = NodeValue
        if (formattedEntry.getValues().get(node.getIndex()).equalsIgnoreCase(child.getDisplayName())) {
          if (child.getChildren() != null) {
            node = child.getChildren().get(0);  //node = Node
            childFound = true;
            break;
          }
          else {
            return child.getResult();
          }
        }
      }
    }
    return -1f;
  }
}
