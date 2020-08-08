import java.util.HashSet;
import java.util.Set;

/**
 * 1.找出数组中重复的数字
 *
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 *
 * 示例:
 * 输入：numbers = {2, 3, 1, 0, 2, 5, 3}, length = 7
 * 输出：true & duplication[0] = 2
 */
public class N01_DuplicationInArray {

    /**
     * 解法1. 哈希法
     *
     * 空间复杂度 O(n)
     * 时间复杂度 O(n)
     */
    public boolean duplicate_1(int[] numbers, int length, int[] duplication) {
        if (numbers == null || numbers.length < 1) return false;
        Set<Integer> set = new HashSet();
        for (int i = 0; i < length; i++) {
            if (set.contains(numbers[i])) {
                duplication[0] = numbers[i];
                return true;
            }
            set.add(numbers[i]);
        }
        return false;
    }

    /**
     * 解法2. 由于数组长度为n, 元素范围也为n, 如果没有重复元素那么排序后数字i将出现在数组下标为i的位置。
     * 从头到尾遍历数组，当扫描到下标 i 的数字 nums[i];
     * 如果等于 i，继续向下扫描;
     * 如果不等于 i，拿它与第 nums[i] 个数进行比较，如果相等，说明有重复值，返回 nums[i]。如果不相等，就把第 i 个数 和第 nums[i] 个数交换。重复这个比较交换的过程
     *
     * 空间复杂度 O(1)
     * 时间复杂度 O(n)
     */
    public boolean duplicate_2(int[] numbers, int length, int[] duplication) {
        if (numbers == null || numbers.length < 1) return false;
        for (int i = 0; i < length; i++) {
            // 首先判断第i个数字(用m表示)是否等于i, 是则接着扫描下一个数字
            int m = numbers[i];
            while (i != m) {
                // 如果不是, 则拿m和第m个数字进行比较, 如果相同则说明有重复值
                if (m == numbers[m]) {
                    duplication[0] = m;
                    return true;
                } else {
                    // 如果不同则将m和第m个数字进行交换并重复这个过程
                    swap(numbers, m, numbers[m]);
                }
            }
        }
        return false;
    }

    private void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
