import java.util.*;

class TreeNode {
    boolean isLeaf;
    String attr;
    Object attrVal;
    List<TreeNode> children = new ArrayList<>();
    String label;
    String path;

    public TreeNode(boolean isLeaf, String label) {
        this.isLeaf = isLeaf;
        this.label = label;
    }
}