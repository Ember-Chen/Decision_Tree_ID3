import java.util.*;
import java.lang.Math;

public class Utils {
    // 计算数据集的熵 Ent(D)
    public static double getEnt(List<List<String>> dataSet) {
        int dataSetSize = dataSet.size();
        Map<String, Integer> labelMap = new HashMap<>();
        
        // 统计各个标签出现的次数
        for (List<String> sample : dataSet) {
            String label = sample.get(sample.size() - 1); // 标签是样本的最后一位
            labelMap.put(label, labelMap.getOrDefault(label, 0) + 1);
        }
        
        double Ent = 0.0;
        // 计算熵值
        for (String label : labelMap.keySet()) {
            double ratio = (double) labelMap.get(label) / dataSetSize;
            Ent -= ratio * Math.log(ratio) / Math.log(2); // 熵的计算公式
        }
        return Ent;
    }

    // 依据某个属性的值来分割数据集
    public static List<List<String>> splitDataSet(List<List<String>> dataSet, int attrIndex, String attrValue) {
        List<List<String>> res = new ArrayList<>();
        for (List<String> sample : dataSet) {
            if (sample.get(attrIndex).equals(attrValue)) {
                // 移除 attrIndex 位置的属性值，形成新的样本
                List<String> reducedSample = new ArrayList<>(sample.subList(0, attrIndex));
                reducedSample.addAll(sample.subList(attrIndex + 1, sample.size()));
                res.add(reducedSample);
            }
        }
        return res;
    }

    // 获取某个属性的所有可能取值
    public static Set<String> getAttrValSet(List<List<String>> dataSet, int attrIndex) {
        Set<String> valueSet = new HashSet<>();
        for (List<String> sample : dataSet) {
            valueSet.add(sample.get(attrIndex)); // 添加属性值到集合中
        }
        return valueSet;
    }

    // 计算某个属性的信息增益 Gain(D,a)
    public static double getGain(List<List<String>> dataSet, int attrIndex, double EntD) {
        Set<String> valueSet = getAttrValSet(dataSet, attrIndex);
        double Gain = EntD;
        
        // 遍历属性的所有取值，计算每个取值对应的子集熵
        for (String value : valueSet) {
            List<List<String>> subDataSet = splitDataSet(dataSet, attrIndex, value);
            double EntDv = getEnt(subDataSet);
            double ratio = (double) subDataSet.size() / dataSet.size();
            Gain -= ratio * EntDv; // 信息增益公式
        }
        return Gain;
    }

    // 得到“最优划分属性”
    public static int getBestAttrIndex(List<List<String>> dataSet) {
        double EntD = getEnt(dataSet); // 计算数据集的熵
        int attrSize = dataSet.get(0).size() - 1; // 属性数量
        double bestGain = 0.0;
        int bestAttrIndex = -1;

        // 遍历所有属性，找到信息增益最大的属性
        for (int attrIndex = 0; attrIndex < attrSize; attrIndex++) {
            double tmpGain = getGain(dataSet, attrIndex, EntD);
            if (tmpGain > bestGain) {
                bestGain = tmpGain;
                bestAttrIndex = attrIndex;
            }
        }
        return bestAttrIndex;
    }

    // 获得标签列表
    public static List<String> getLabelList(List<List<String>> dataSet) {
        List<String> labelList = new ArrayList<>();
        for (List<String> sample : dataSet) {
            labelList.add(sample.get(sample.size() - 1)); // 标签是样本的最后一位
        }
        return labelList;
    }

    // 判断标签列表中的标签是否全部相同
    public static boolean allSameLabel(List<String> labelList) {
        String firstLabel = labelList.get(0);
        for (String label : labelList) {
            if (!label.equals(firstLabel)) {
                return false;
            }
        }
        return true;
    }

    // 判断当前样本集中的“多数标签”（数量最多的那个标签）
    public static String getMajorLabel(List<String> labelList) {
        Map<String, Integer> labelCnt = new HashMap<>();
        for (String label : labelList) {
            labelCnt.put(label, labelCnt.getOrDefault(label, 0) + 1);
        }
        String majorLabel = null;
        int maxCnt = 0;
        // 找到出现次数最多的标签
        for (Map.Entry<String, Integer> entry : labelCnt.entrySet()) {
            if (entry.getValue() > maxCnt) {
                maxCnt = entry.getValue();
                majorLabel = entry.getKey();
            }
        }
        return majorLabel;
    }

    // 打印决策树
    public static void printTree(TreeNode cur, StringBuilder sb) {
        if (cur.isLeaf) {
            sb.append("->" + cur.label);
            System.out.println(sb);
            return;
        }

        // 遍历孩子节点并递归打印
        for (TreeNode node : cur.children) {
            int oldLen = sb.length();
            sb.append(node.val);
            printTree(node, sb);
            sb.setLength(oldLen); // 回溯
        }
    }
}
