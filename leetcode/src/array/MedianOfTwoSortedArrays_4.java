package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 4.寻找两个正序数组的中位数
 *
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))
 * 你可以假设 nums1 和 nums2 不会同时为空你可以假设 nums1 和 nums2 不会同时为空
 *
 * 示例:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays_4 {

    /**
     * 合并数组后排序取中值
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(n)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList();
        // 将2个数组转为List并进行合并 (插入操作时间复杂度为数组长度之和)
        list.addAll(Arrays.stream(nums1).boxed().collect(Collectors.toList()));
        list.addAll(Arrays.stream(nums2).boxed().collect(Collectors.toList()));
        // 将合并后的集合进行排序 (jdk自带的TimSort采用归并+插入排序, 时间复杂度为nlogn)
        List<Integer> sortedList = list.stream().sorted().collect(Collectors.toList());
        // 计算合并后集合的中间元素索引
        int length = nums1.length + nums2.length;
        int midNum = length / 2;
        // 如果合并数组的长度为偶数值, 则用中间索引+前一个索引的元素值取平均值, 否则即为中间索引对应的元素值
        return length % 2 == 0 ? (sortedList.get(midNum - 1) + sortedList.get(midNum)) / 2.0 : sortedList.get(midNum);
    }

    /**
     * 手动归并
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * 由于给出的数组已经是有序状态, 所以可以直接进行归并操作, 减少TimSort中排序的消耗
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList();
        int m = nums1.length;
        int n = nums2.length;
        /**
         * 使用两个指针i, j分别遍历2个数组
         */
        for (int i = 0, j = 0; i < m || j < n; ) {
            // 当j指针用完 或 i指针所指元素小于j指针所指元素时, 插入i指针元素
            if (j == n || (i < m && (nums1[i] <= nums2[j]))) {
                list.add(nums1[i++]);
            } else if (i == m || (j < n && (nums2[j] <= nums1[i]))) {
                // 当i指针用完 或 j指针所指元素小于i指针所指元素时, 插入j指针元素
                list.add(nums2[j++]);
            }
        }
        int length = m + n;
        int midNum = length / 2;
        return length % 2 == 0 ? (list.get(midNum - 1) + list.get(midNum)) / 2.0 : list.get(midNum);
    }

    /**
     * 手动归并+指针扫描
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * 由于题目只需要找出中位数的值, 所以不需要归并完所有的数组元素, 而是找到中位数后返回即可
     * 同时不需要使用List存储全部元素，只需要多加2个指针遍历出中位索引前后的元素值即可, 这样可以将空间复杂度降低至常数级
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int length = m + n;
        // 创建2个索引用于记录中位索引及其前一个索引
        int midIndex = length / 2;
        int midPreIndex = midIndex - 1;
        // 初始化2个变量用于记录索引所指的元素指
        int midNum, midPreNum = 0;
        for (int i = 0, j = 0; i < m || j < n; ) {
            if (j == n || (i < m && (nums1[i] <= nums2[j]))) {
                if (i + j == midPreIndex) {
                    midPreNum = nums1[i];
                }
                // 当i指针索引到达中位索引后, 根据数组总长的奇偶情况计算中位值并返回
                if (i + j == midIndex) {
                    midNum = nums1[i];
                    return length % 2 == 0 ? (midPreNum + midNum) / 2.0 : midNum;
                }
                i++;
            } else if (i == m || (j < n && (nums2[j] <= nums1[i]))) {
                if (i + j == midPreIndex) {
                    midPreNum = nums2[j];
                }
                // 当j指针索引到达中位索引后, 根据数组总长的奇偶情况计算中位值并返回
                if (i + j == midIndex) {
                    midNum = nums2[j];
                    return length % 2 == 0 ? (midPreNum + midNum) / 2.0 : midNum;
                }
                j++;
            }
        }
        // 数组为空
        return 0;
    }

    /**
     * 二分查找法
     * 时间复杂度 O(log(min(m, n)))
     * 空间复杂度 O(1)
     *
     * 上述方法都不符合题目中时间复杂度小于log(m + n)的要求, 对于log级别的复杂度, 很容易联想到使用二分法来解决问题
     */
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        // 为了让搜索范围更小，我们始终让 num1 是那个更短的数组
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        // 上述交换保证了 m <= n，在更短的区间 [0, m] 中搜索，会更快一些
        int m = nums1.length;
        int n = nums2.length;

        // 使用二分查找算法在数组 nums1 中搜索一个索引i
        int left = 0;
        int right = m;
        while (left <= right) {
            // 尝试要找的索引，在区间里完成二分，为了保证语义，这里就不定义成 mid 了
            // 用加号和右移是安全的做法，即使在溢出的时候都能保证结果正确
            int i = (left + right) >>> 1;
            int j = ((m + n + 1) >>> 1) - i;

            // 边界值的特殊取法
            int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];

            int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

            // 交叉小于等于关系成立，那么中位数就可以从"边界线"两边的数得到
            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // 已经找到解了，分数组之和是奇数还是偶数得到不同的结果
                if (((m + n) & 1) == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                } else {
                    return (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
                }
            } else if (nums2LeftMax > nums1RightMin) {
                // 这个分支缩短边界
                left = i + 1;
            } else {
                right = i - 1;
            }
        }
        return 0;
    }
}
