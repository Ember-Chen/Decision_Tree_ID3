import java.util.*;

public class DecisionTree {
    // 构建决策树
    public TreeNode build(List<List<String>> dataSet) {
        List<String> labelList = Utils.getLabelList(dataSet);
        int attrSize = dataSet.get(0).size() - 1;

        // base case 1: 当没有更多属性可以分割时，返回多数标签作为叶子节点
        if (attrSize == 0) {
            String label = Utils.getMajorLabel(labelList);
            return new TreeNode(true, label);
        }

        // base case 2: 当所有标签都相同时，返回当前标签作为叶子节点
        if (Utils.allSameLabel(labelList)) {
            String label = labelList.get(0);
            return new TreeNode(true, label);
        }

        TreeNode cur = new TreeNode(false, null);
        int bestAttrIndex = Utils.getBestAttrIndex(dataSet);
        Set<String> attrValSet = Utils.getAttrValSet(dataSet, bestAttrIndex);
        cur.val = String.valueOf(bestAttrIndex);

        // 遍历最佳属性的所有可能取值，递归构建子树
        for (String val : attrValSet) {
            List<List<String>> subDataSet = Utils.splitDataSet(dataSet, bestAttrIndex, val);
            TreeNode node = build(subDataSet);
            node.val = val;
            cur.children.add(node);
        }

        return cur;
    }
}
