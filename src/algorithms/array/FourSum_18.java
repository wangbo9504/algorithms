package algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18.四数之和
 *
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组
 * 注意: 答案中不可以包含重复的三元组
 *
 * 示例:
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0
 * 满足要求的三元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class FourSum_18 {

    /**
     * 排序 + 双指针法
     * 时间复杂度 O(n^3)
     * 空间复杂度 O(1)
     *
     * 相比三数之和多了一重循环,固定a、b两个最小的指针在左边,再用双指针法夹逼计算四数之和
     * (N数之和都可以通过固定N-2个左边界后将问题转化为2Sum使用双指针夹逼法解决,当N较大时可以增加剪枝条件提前跳出循环优化查询效率)
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList();
        Arrays.sort(nums);
        for (int a = 0; a < nums.length - 3; a++) {
            // 跳过重复元素防止重复的四元组产生
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            // 获取当前循环能遍历到的最小总和, 如果大于目标值, 则直接跳出循环(后面元素的加合只会让结果越来越大)
            if(nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3] > target){
                break;
            }
            // 获取当前循环能遍历到的最大总和，如果小于目标值, 则跳过当前循环将指针向右移动, 扩大总和值
            if(nums[a] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target){
                continue;
            }
            for (int b = a + 1; b < nums.length - 2; b++) {
                // 限制b > a + 1(b = a + 1时num[b]==num[b-1]==num[a]不影响结果, 如[0,0,0,0]->0、[1,1,2,2]->6)
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                int left = b + 1;
                int right = nums.length - 1;
                // 获取当前循环能遍历到的最小总和, 如果大于目标值, 则直接跳出循环(后面元素的加合只会让结果越来越大)
                if(nums[a] + nums[b] + nums[left] + nums[left + 1] > target){
                    break;
                }
                // 获取当前循环能遍历到的最大总和，如果小于目标值, 则跳过当前循环将指针向右移动, 扩大总和值
                if(nums[a] + nums[b] + nums[right - 1] + nums[right] < target){
                    continue;
                }
                while (left < right) {
                    int sum = nums[a] + nums[b] + nums[left] + nums[right];
                    if (sum < target) {
                        while (left < right && nums[left++] == nums[left]);
                    } else if (sum > target) {
                        while (left < right && nums[right--] == nums[right]);
                    } else {
                        result.add(Arrays.asList(nums[a], nums[b], nums[left], nums[right]));
                        while (left < right && nums[left++] == nums[left]);
                        while (left < right && nums[right--] == nums[right]);
                    }
                }
            }
        }
        return result;
    }
}
