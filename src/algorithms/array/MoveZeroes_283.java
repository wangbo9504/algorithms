package algorithms.array;

/**
 * 283.移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
 *
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class MoveZeroes_283 {

    /**
     * 双指针法
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public void moveZeroes(int[] nums) {
        // 定义一个左指针left, 一个右指针right
        for (int left = 0, right = 0; right < nums.length; right++) {
            // 当右指针所指元素不为0的时候, 与左指针所指元素进行交换, 否则继续移动右指针
            if (nums[right] != 0) {
                nums[left] = nums[right];
                /**
                 * 每次交换后可以直接将右指针所指元素置为0
                 * 否则需要在right指针遍历完后，再写一个循环从left指针遍历到结尾进行补0操作
                 */
                if (left++ < right) {
                    nums[right] = 0;
                }
            }
        }
    }
}
