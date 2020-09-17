package bit;

/**
 * 231.2的幂
 *
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方
 *
 * 示例:
 * 输入: 16
 * 输出: true
 */
public class PowerOfTwo_231 {

    /**
     * 如果一个数是2的幂次方, 说明这个数的二进制位有且只有一位是1(通过n & n - 1的操作可以将n最地位的1变为0)
     */
    public boolean isPowerOfTwo(int n) {
        // -2147483648为最高二进制为1的额特殊情况, 需要通过n > 0进行剔除
        return n > 0 && (n & n - 1) == 0;
    }
}
