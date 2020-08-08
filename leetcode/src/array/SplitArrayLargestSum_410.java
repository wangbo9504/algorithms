package array;

/**
 * 410.分割数组的最大值
 *
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小
 *
 * 注意:
 * 数组长度 n 满足以下条件:
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 *
 * 示例:
 * 输入: nums = [7,2,5,10,8] m = 2
 * 输出: 18
 */
public class SplitArrayLargestSum_410 {

    /**
     * 二分查找 + 贪心法
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(1)
     *
     * 二分搜索一个区间和的总和的最大值, 如果值偏小, 组数量会不够, 如果值偏大, 组的数量会用不完, 最后二分可以找到这个总和的最小上界
     */
    public int splitArray(int[] nums, int m) {
        // 通过二分法进行查找, 首先确定搜索空间, [max(nums), sum(nums)] (单独一个数组 ～ 不分割数组)
        long left = 0;
        long right = 0;
        for(int num : nums) {
            left = Math.max(left, num);
            right += num;
        }
        while (left < right) {
            long mid = (left + right) / 2;
            if (splitCount(nums, mid) > m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return (int) right;
    }

    // 贪心算法: 返回将nums划分为和小于等于mid的子数组的最少数量
    private int splitCount(int[] nums, long mid) {
        int count = 1;
        long sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > mid) {
                count++;
                sum = num;
            }
        }
        return count;
    }
}
