/**
 * 10.旋转数组的最小数字
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾, 我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转, 输出旋转数组的最小元素。
 * 例如, 数组{3, 4, 5, 1, 2}为{1, 2, 3, 4, 5}的一个旋转, 该数组的最小元素值为1。
 */
public class N10_MinNumberInRotatedArray {

    /**
     * 解法1. 顺序查找, 找到最小的元素并返回
     */
    public int minNumberInRotateArray_1(int [] array) {
        if (array.length == 0) {
            return 0;
        }
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < result) {
                result = array[i];
            }
        }
        return result;
    }

    /**
     * 解法2. 二分法, 由于旋转之后的数组实际上可以划分为两个排序的子数组, 且前面子数组的元素都大于等于后面子数组的元素, 而最小元素正好是两个子数组的分界线
     * 因此可以使用两个指针分别指向第一个元素和最后一个元素, 正常情况下第一个元素会大于或等于最后一个元素(有特殊情况)
     * 接着可以找到数组中间的元素, 如果它大于等于第一个指针指向的元素, 说明它位于第一个递增子数组中, 因此可以将第一个指针指向该元素缩小查询范围
     * 如果它小于等于最后一个指针指向的元素, 说明它位于第二个递增子数组中, 因此可以将第二个指针指向该元素缩小查询范围
     * 最终第一个指针将指向第一个子数组的最后一个元素, 而第二个指针会指向第二个子数组的第一个元素, 也就是他们会指向两个相邻的元素, 而第二个指针指向的刚好是最小元素, 这就是循环结束条件.
     *
     * 特殊情况是第一个元素, 最后一个元素和中间元素相同时, 无法确定中间元素位于第一个子数组还是第二个子数组, 此时就需要使用顺序查找.
     */
    public int minNumberInRotateArray_2(int [] array) {
        if (array.length == 0) {
            return 0;
        }
        // 创建左右两个指针分别指向第一个元素和最后一个元素
        int left = 0, right = array.length - 1;
        int mid = left;
        while (array[left] >= array[right]) {
            // 当两个指针指向的元素相邻时, 第二个指针所指元素即为结果
            if (right - left == 1) {
                mid = right;
                break;
            }
            // 计算中间元素索引
            mid = (left + right) >> 1;
            // 当left、mid、right指针指向的元素都相同时, 使用顺序查找
            if (array[left] == array[mid] && array[mid] == array[right]) {
                return minInOrder(array, left, right);
            }
            // 如果中间元素大于等于左指针指向的元素, 则该中间元素位于第一个子数组, 移动左指针
            if (array[mid] >= array[left]) {
                left = mid;
            // 如果中间元素小于等于右指针指向的元素, 则该中间元素位于第二个子数组, 移动右指针
            } else if (array[mid] <= array[right]) {
                right = mid;
            }
        }
        return  array[mid];
    }

    private int minInOrder(int[] array, int left, int right) {
        int result = array[left];
        for (int i = left + 1; i <= right; i++) {
            if (result > array[i]) {
                result = array[i];
            }
        }
        return result;
    }
}
