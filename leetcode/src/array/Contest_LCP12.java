package array;

/**
 * LCP 12.小张刷题计划
 *
 * 为了提高自己的代码能力，小张制定了 LeetCode 刷题计划，他选中了 LeetCode 题库中的 n 道题，编号从 0 到 n-1，
 * 并计划在 m 天内按照题目编号顺序刷完所有的题目（注意，小张不能用多天完成同一题）。
 * 在小张刷题计划中，小张需要用 time[i] 的时间完成编号 i 的题目。
 * 此外，小张还可以使用场外求助功能，通过询问他的好朋友小杨题目的解法，可以省去该题的做题时间。
 * 为了防止“小张刷题计划”变成“小杨刷题计划”，小张每天最多使用一次求助。
 * 我们定义 m 天中做题时间最多的一天耗时为 T（小杨完成的题目不计入做题总时间）。请你帮小张求出最小的 T是多少。
 *
 * 示例:
 * 输入: time = [1,2,3,3], m = 2
 * 输出: 3
 */
public class Contest_LCP12 {

    /**
     * 二分查找 + 贪心法
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(1)
     *
     * 类似于410题, 都是二分求最大值最小化(即分割的m个子数组中)
     */
    public int minTime(int[] time, int m) {
        // 当天数大于等于题数时, 小张可以每天都找小杨完成题目, 因此耗时为0
        if (m >= time.length) return 0;
        // 通过二分法进行查找, 首先确定搜索空间, [min(nums), sum(nums)]
        int left = 0;
        int right = 0;
        for (int t : time) {
            left = Math.min(t, left);
            right += t;
        }
        while (left < right) {
            // 假设给定mid作为最大值进行拆分(mid越大则分割的数组越少)
            int mid = (left + right) / 2;
            /**
             * 如果拆分出的子数组个数大于m, 说明mid值过小, 划分的子数组多了, 排除左侧的条件
             * 否则说明mid值过大, 划分的子数组少了, 排除右测的条件
             */
            if (splitCount(time, mid) > m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    /**
     * 将nums数组分割为元素和不超过target+max的count个子数组
     */
    private int splitCount(int[] nums, int target) {
        int count = 1;
        int sum = 0;
        // 记录遍历过程中的最大元素(小杨可以解决掉用时最长的元素), 求和时除去
        int max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
            // 当sum-max大于目标值时, 分割新的数组
            if (sum - max > target) {
                // 分割数+1
                count++;
                // 重置总和与最大值
                sum = num;
                max = num;
            }
        }
        return count;
    }
}
