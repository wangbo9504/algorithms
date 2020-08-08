package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.两数之和
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum_1 {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        // 将数组元素值作为Key，下标作为Value存入哈希表
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            // 计算目标差值
            int diff = target - nums[i];
            // 在哈希表中查找目标差值
            if (map.containsKey(diff)) {
                return new int[] { map.get(diff), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No solution");
    }
}
