package sample.utils.id3;

import sample.models.*;
import sample.utils.repository.FileRepository;
import sample.utils.repository.JDBCRepository;
import sample.utils.repository.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DecisionTree {
  private Repository repo;
  private List<FormattedEntry> entries;
  private List<TreeNode> path = new ArrayList<>();
  private Node root;
  private String printPathList = "";

  public DecisionTree(List<FormattedEntry> entries) {
    this.entries = entries;
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
  public void generateIfClauses(TreeNode node) {
    if (node == null) {
      addIfClause();
      return;
    }

    if (node.getChildren() == null || node.getChildren().isEmpty()) {
      path.add(node);
      addIfClause();
      path.remove(node);
      return;
    }
    path.add(node);
    if (node.getChildren() != null) {
      for (TreeNode treeNode : node.getChildren()) {
          generateIfClauses(treeNode);
      }
    }
    path.remove(node);
  }

  private void addIfClause() {
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
    printPathList += toPrint + "\n";
  }

  public Node getRoot() {
    return root;
  }
//
//  public static void main(String[] args) {
//    try {
//      long time = System.currentTimeMillis();
////      Repository repo = new FileRepository("D:\\Java Workspace\\DecisionTree\\src\\data\\entries.txt", "D:\\Java Workspace\\DecisionTree\\src\\data\\attribute_range.txt");
//      Repository repo = new JDBCRepository();
//      System.out.println(System.currentTimeMillis() - time);
//      time = System.currentTimeMillis();
//      DecisionTree tree = new DecisionTree(repo.getFormattedEntries());
//      repo.setTree(tree);
//      tree.run();
//      System.out.println(System.currentTimeMillis() - time);
//      time = System.currentTimeMillis();
//      tree.generateIfClauses(tree.getRoot());
//      System.out.println(System.currentTimeMillis() - time);
//      System.out.println("Result: " + tree.find(new FormattedEntry("24,1.75,80.7,1,4,5,13.5,0")));
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }

  public static void main(String[] args) {
    System.out.println("Validating arguments");
    if (args == null || args.length != 2) {
      System.out.println("Incorrect provided parameters.");
      return;
    }
    try {
      File file = new File(args[0]);
      if (!file.exists()) {
        System.out.println("Specified entries file does not exists.");
        return;
      }
      file = new File(args[1]);
      if (!file.exists()) {
        System.out.println("Specified attribute range file does not exist");
        return;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("arg0 entries file");
      System.out.println("arg0 attribute range file");
      return;
    }
    Repository repo = new FileRepository(args[0], args[1]);
    DecisionTree tree = new DecisionTree(repo.getFormattedEntries());
    repo.setTree(tree);
    tree.run();
    tree.generateIfClauses(tree.getRoot());
    System.out.println(tree.getPrintPathList());
  }

  public Float find(FormattedEntry formattedEntry) {
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

  public String getPrintPathList() {
    printPathList = "";
    generateIfClauses(root);
    return printPathList;
  }

  public String findPath(Entry value) {
    TreeNode node = root;
    boolean childFound = true;
    String result = "if (";
    while (node != null && node.getChildren() != null && childFound) {  // node = Node
      childFound = false;
      result += node.getDisplayName();
      for (TreeNode child : node.getChildren()) { //child = NodeValue
        if (value.getValues().get(node.getIndex()).equalsIgnoreCase(child.getDisplayName())) {
          if (child.getChildren() != null) {
            node = child.getChildren().get(0);  //node = Node
            result += "==" + child.getDisplayName() + " AND ";
            childFound = true;
            break;
          }
          else {
            result += "==" + child.getDisplayName();
            result += ") -> " + child.getResult();
            return result;
          }
        }
      }
    }
    return "No available rule";
  }
}
