package algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15.三数之和
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组
 * 注意: 答案中不可以包含重复的三元组
 *
 * 示例:
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum_15 {

    /**
     * 排序 + 双指针法
     * 时间复杂度 O(n^2)
     * 空间复杂度 O(1)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        // 处理边界情况
        if (nums == null || nums.length <= 2) return result;
        // 先将数组从小到大排序,方便去重
        Arrays.sort(nums);
        // 固定一个迭代指针，从头开始遍历
        for (int index = 0; index < nums.length - 2; index++) {
            // 当数组元素大于0时, 说明后面的元素加合也永远大于0，直接跳出循环
            if (nums[index] > 0) {
                break;
            }
            // 若当前迭代的数组元素等于前一个元素时，跳过当前循环，防止产生重复三元组
            if (index > 0 && nums[index] == nums[index - 1]) {
                continue;
            }
            // 定义一个左指针,一个右指针,通过夹逼法计算3数之和
            int left = index + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[index] + nums[left] + nums[right];
                // 当sum小于0时向右移动左指针并跳过重复值, 扩大sum
                if (sum < 0) {
                    while (left < right && nums[left++] == nums[left]);
                } else if (sum > 0) {
                    // 当sum大于0时向左移动右指针并跳过重复值, 缩小sum
                    while (left < right && nums[right--] == nums[right]);
                } else {
                    // 当sum等于0时记录当前三元组,并同时移动左右指针,跳过重复值
                    result.add(Arrays.asList(nums[index], nums[left], nums[right]));
                    while (left < right && nums[left++] == nums[left]);
                    while (left < right && nums[right--] == nums[right]);
                }
            }
        }
        return result;
    }
}
