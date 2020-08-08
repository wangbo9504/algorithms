package queue;

import java.util.LinkedList;

/**
 * 239.滑动窗口最大值
 *
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 */
public class SlidingWindowMaximum_239 {

    /**
     * 双端队列法
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 数组为空或窗口为1直接返回本身
        if (nums.length == 0 || k == 1) return nums;
        // 窗口滑动次数为nums.length - k + 1
        int[] result = new int[nums.length - k + 1];
        // 创建双端队列(可以用O(1)的时间在头尾添加或删除元素) 存储数组下标值
        LinkedList<Integer> deque = new LinkedList();
        for (int i = 0; i < nums.length; i++) {
            // 遍历数组, 将当前元素值与队列中的比较, 从队列尾部弹出所有比当前元素小的值, 保证队列头部存储总是当前窗口中最大的值
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
            // 判断队列头部的最大值元素是否在当前窗口内, 不在则移除
            if (i - k + 1 > deque.peekFirst()) {
                deque.pollFirst();
            }
            // 判断窗口是否开始滑动, 是则将队列头部最大元素写入结果数组
            if (i - k + 1 >= 0) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
