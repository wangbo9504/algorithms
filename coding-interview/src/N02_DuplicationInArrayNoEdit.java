/**
 * 2.不修改数组找出重复的数字
 *
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 *
 * 示例:
 * 输入：numbers = {2, 3, 1, 0, 2, 5, 3}, length = 7
 * 输出：true & duplication[0] = 2
 */
public class N02_DuplicationInArrayNoEdit {

    /**
     * 数组元素的取值范围是[1, n]，对该范围对半划分, 分成[1, middle]和[middle, n]
     * 计算数组中有多少个元素count落在[1, middle]内, 如果count > middle - 1 + 1, 那么说明这个区间内有重复元素
     * 否则在另一个区间内重复进行该操作
     *
     * 空间复杂度 O(1)
     * 时间复杂度 O(nlogn)
     */
    public int getDuplicate(int[] numbers, int length) {
        if (numbers == null || numbers.length < 1) return -1;
        int start = 1;
        int end = length - 1;
        while (end >= start) {
            int middle = ((end - start) >> 1) + start;
            int count = countRange(numbers, start, middle);
            if (start == end) {
                if (count > 1) {
                    return start;
                }
                break;
            } else {
                if (count > (middle - start) + 1) {
                    end = middle;
                } else {
                    start = middle + 1;
                }
            }
        }
        return  -1;
    }

    private int countRange(int[] numbers, int start, int end) {
        if (numbers == null) return 0;
        int count = 0;
        for (int num : numbers) {
            if (num >= start && num <= end) {
                ++count;
            }
        }
        return count;
    }
}
