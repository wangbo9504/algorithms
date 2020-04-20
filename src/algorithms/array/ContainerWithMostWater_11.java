package algorithms.array;

/**
 * 11.盛最多水的容器
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (left, ai) 。在坐标内画 n 条垂直线
 * 垂直线 left 的两个端点分别为 (left, ai) 和 (left, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水
 * 说明：你不能倾斜容器，且 n 的值至少为 2
 *
 * 示例:
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class ContainerWithMostWater_11 {

    /**
     * 双指针夹逼法
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public int maxArea(int[] height) {
        int result = 0;
        // 定义两个指针分别指向头部元素和尾部元素
        for (int left = 0, right = height.length - 1; left < right; ) {
            // 计算容量(容器的容量=较短的柱子 * 柱子的间距)
            int minHeight = Math.min(height[left], height[right]);
            result = Math.max(minHeight * (right - left), result);
            /**
             * 如果头部元素较短则将左指针向右移动，否则右指针向左移动
             * 由于指针是向内收窄的，所以较短柱子计算出的容量一定是越来越小的，所以哪个指针对应的柱子短则移动哪个指针
             */
            //
            if (minHeight == height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    /**
     * 精简版
     */
    public int liteVersion(int[] height) {
        int max = 0;
        for (int left = 0, right = height.length - 1; left < right; ) {
            int minHeight = height[left] <= height[right] ? height[left++] : height[right--];
            max = Math.max(minHeight*(right-left+1), max);
        }
        return max;
    }
}
