package array;

/**
 * 443.压缩字符串
 *
 * 给定一组字符, 使用原地算法将其压缩, 压缩后的长度必须始终小于或等于原数组长度.
 * 数组的每个元素应该是长度为1 的字符(不是 int 整数类型), 在完成原地修改输入数组后，返回数组的新长度.
 *
 * 示例:
 * 输入: ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * 输出: 4, 输入数组的前4个字符应该是：["a","b","1","2"]
 */
public class StringCompression_443 {

    /**
     * 双指针遍历法
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public int compress(char[] chars) {
        // 定义写入数组的长度
        int size = 0;
        // 定义左、右两个指针循环遍历(由于最后一个字符也需要判断, 所以右指针最大范围需要放到数组之外一格)
        for (int left = 0, right = 0; right <= chars.length; right++) {
            // 当遍历完成或左右指针所指元素不相等时写入压缩元素
            if (right == chars.length || chars[left] != chars[right]) {
                chars[size++] = chars[left];
                // 当字符重复次数超过1时, 写入压缩字符
                if (right - left > 1) {
                    // 由于重复次数可能超过1位, 所以需要将数字的每一位进行循环写入
                    for (char c : String.valueOf(right - left).toCharArray()) {
                        chars[size++] = c;
                    }
                }
                // 左指针元素写入完毕, 将左指针指向右指针继续遍历
                left = right;
            }
        }
        // 返回数组长度
        return size;
    }
}
