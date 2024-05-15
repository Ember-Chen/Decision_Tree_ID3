import java.util.*;

class Main{
    public static void main(String[] args) {
        List<List<String>> dataSet = new ArrayList<>();
        dataSet.add(Arrays.asList("老年", "短发", "平底", "深色", "男性"));
        dataSet.add(Arrays.asList("老年", "短发", "平底", "浅色", "男性"));
        dataSet.add(Arrays.asList("老年", "中发", "平底", "花色", "女性"));
        dataSet.add(Arrays.asList("老年", "长发", "高跟", "浅色", "女性"));
        dataSet.add(Arrays.asList("老年", "短发", "平底", "深色", "男性"));
        dataSet.add(Arrays.asList("中年", "短发", "平底", "浅色", "男性"));
        dataSet.add(Arrays.asList("中年", "短发", "平底", "浅色", "男性"));
        dataSet.add(Arrays.asList("中年", "长发", "高跟", "花色", "女性"));
        dataSet.add(Arrays.asList("中年", "中发", "高跟", "深色", "女性"));
        dataSet.add(Arrays.asList("中年", "中发", "平底", "深色", "男性"));
        dataSet.add(Arrays.asList("青年", "长发", "高跟", "浅色", "女性"));
        dataSet.add(Arrays.asList("青年", "短发", "平底", "浅色", "女性"));
        dataSet.add(Arrays.asList("青年", "长发", "平底", "深色", "男性"));
        dataSet.add(Arrays.asList("青年", "短发", "平底", "花色", "男性"));
        dataSet.add(Arrays.asList("青年", "中发", "高跟", "深色", "女性"));

        DecisionTree dt = new DecisionTree();
        TreeNode root = dt.build(dataSet);
        Utils.printTree(root, new StringBuilder());
    }
}