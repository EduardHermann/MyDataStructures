package pack;

public class MyBinaryTree {

  public class TreeNode {

    public int key;
    public String name;
    public TreeNode leftNode;
    public TreeNode rightNode;

    public TreeNode(int key, String name) {
      this.key = key;
      this.name = name;
    }

    public String toString() {
      return "" + key + " | " + name;
    }
  }

  public TreeNode root;

  public void addNode(int key, String name) {
    TreeNode node = new TreeNode(key, name);
    if (root == null) {
      root = node;
    } else {
      TreeNode focusNode = root;
      TreeNode parent;
      while (true) {
        parent = focusNode;
        if (node.key < focusNode.key) {
          focusNode = focusNode.leftNode;
          if (focusNode == null) {
            parent.leftNode = node;
            return;
          }
        } else {
          focusNode = focusNode.rightNode;
          if (focusNode == null) {
            parent.rightNode = node;
            return;
          }
        }
      }
    }
  }

  public TreeNode findTreeNode(int key) {
    TreeNode focusNode = root;
    while (key != focusNode.key) {
      if (key < focusNode.key) {
        focusNode = focusNode.leftNode;
      } else {
        focusNode = focusNode.rightNode;
      }
      if (focusNode == null) {
        return null;
      }
    }
    return focusNode;
  }

  public boolean remove(int key) {
    TreeNode focusNode = root;
    TreeNode parent = root;
    boolean isLeft = true;
    while (key != focusNode.key) {
      parent = focusNode;
      if (key < focusNode.key) {
        focusNode = focusNode.leftNode;
        isLeft = true;
      } else {
        focusNode = focusNode.rightNode;
        isLeft = false;
      }
      if (focusNode == null) {
        return false;
      }
    }
    if (focusNode.leftNode == null && focusNode.rightNode == null) {
      if (focusNode == root) {
        root = null;
      } else if (isLeft) {
        parent.leftNode = null;
      } else {
        parent.rightNode = null;
      }
    } else if (focusNode.leftNode == null) {
      if (focusNode == root) {
        root = focusNode.rightNode;
      } else if (isLeft) {
        parent.leftNode = focusNode.rightNode;
      } else {
        parent.rightNode = focusNode.rightNode;
      }
    } else if (focusNode.rightNode == null) {
      if (focusNode == root) {
        root = focusNode.leftNode;
      } else if (isLeft) {
        parent.leftNode = focusNode.leftNode;
      } else {
        parent.rightNode = focusNode.leftNode;
      }
    } else {
      TreeNode replace = getReplaceNode(focusNode);
      if (focusNode == root) {
        root = replace;
      } else if (isLeft) {
        parent.leftNode = replace;
      } else {
        parent.rightNode = replace;
      }
      replace.leftNode = focusNode.leftNode;
    }
    return true;
  }

  public void inOrderPrint(TreeNode node) {
    if (node != null) {
      inOrderPrint(node.leftNode);
      System.out.println(node.toString());
      inOrderPrint(node.rightNode);
    }
  }

  public void preOrderPrint(TreeNode node) {
    if (node != null) {
      System.out.println(node.toString());
      preOrderPrint(node.leftNode);
      preOrderPrint(node.rightNode);
    }
  }

  public void postOrderPrint(TreeNode node) {
    if (node != null) {
      postOrderPrint(node.leftNode);
      postOrderPrint(node.rightNode);
      System.out.println(node.toString());
    }
  }

  public static void main(String[] args) {
    MyBinaryTree tree = new MyBinaryTree();
    tree.addNode(50, "Edi");
    tree.addNode(25, "Tom");
    tree.addNode(75, "Lukas");
    tree.addNode(15, "Noah");
    tree.addNode(30, "Niklas");
    tree.addNode(85, "Paul");
    tree.addNode(60, "Paul");
    tree.addNode(80, "Paul");
    tree.addNode(100, "Paul");


    tree.preOrderPrint(tree.root);
    tree.remove(75);
    System.out.println("-----------------");
    tree.preOrderPrint(tree.root);
  }

  // -------------------------private_methods-------------------------

  private TreeNode getReplaceNode(TreeNode node) {
    TreeNode repParent = node;
    TreeNode replace = node;
    TreeNode focusNode = node.rightNode;

    while (focusNode != null) {
      repParent = replace;
      replace = focusNode;
      focusNode = focusNode.leftNode;
    }

    if (replace != node.rightNode) {
      repParent.leftNode = replace.rightNode;
      replace.rightNode = node.rightNode;
    }
    return replace;
  }
}
