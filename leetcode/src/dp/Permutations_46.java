package dp;

import java.util.*;

/**
 * 46.全排列
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列
 *
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations_46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        // 记录[路径]
        LinkedList track = new LinkedList();
        backtrack(nums, track, result);
        return result;
    }

    private void backtrack(int[] nums, LinkedList track, List<List<Integer>> result) {
        // 结束条件
        if (track.size() == nums.length) {
            // 这里为了防止track对象被重复引用, 使用new新生成一个结果
            result.add(new ArrayList(track));
            return;
        }
        for (int num : nums) {
            // 已做过选择的路径无法继续选择
            if (track.contains(num)) {
                continue;
            }
            // 做选择, 添加路径
            track.add(num);
            // 移动节点, 进入下一层决策树
            backtrack(nums, track, result);
            // 撤销选择
            track.removeLast();
        }
    }

    /**
     * 使用数组优化track.contains这个O(n)的操作
     */
    public List<List<Integer>> permute_2(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        // 记录[路径]
        LinkedList track = new LinkedList();
        // 创建数组记录是否被访问
        boolean[] visited = new boolean[nums.length];
        backtrack_2(nums, visited, track, result);
        return result;
    }

    private void backtrack_2(int[] nums, boolean[] visited, LinkedList track, List<List<Integer>> result) {
        // 结束条件
        if (track.size() == nums.length) {
            // 这里为了防止track对象被重复引用, 使用new新生成一个结果
            result.add(new ArrayList(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 已做过选择的路径无法继续选择
            if (visited[i]) {
                continue;
            }
            // 做选择, 添加路径
            track.add(nums[i]);
            // 记录访问
            visited[i] = true;
            // 移动节点, 进入下一层决策树
            backtrack_2(nums, visited, track, result);
            // 撤销选择
            track.removeLast();
            // 撤销访问
            visited[i] = false;
        }
    }
}
