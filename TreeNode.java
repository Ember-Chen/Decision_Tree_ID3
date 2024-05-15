import java.util.*;

class TreeNode {
    String val;
    List<TreeNode> children = new ArrayList<>();
    boolean isLeaf;
    String label;

    public TreeNode(boolean isLeaf, String label) {
        this.isLeaf = isLeaf;
        this.label = label;
    }
}