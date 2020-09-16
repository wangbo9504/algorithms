/**
 * 14.二进制中1的个数
 *
 * 输入一个整数，输出该数32位二进制表示中1的个数。其中负数用补码表示
 **/

public class N14_NumberOf1InBinary {

    // 解法1.构造一个掩码每次左移一位与目标值进行与运算, 判断目标值每一个二进制位是否为1
    public int numberOf1_1(int n) {
        int result = 0;
        // 初始化掩码为1
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            // 判断当前二进制为是否为1
            if ((n & mask) != 0) {
                result++;
            }
            // 掩码左移一位
            mask = mask << 1;
        }
        return result;
    }

    // 解法2. 通过 n & (n - 1)的操作可以将n中最低位的1变成0, 循环这个操作直到所有位为0, 循环的次数即为结果
    public int numberOf1_2(int n) {
        int result = 0;
        while (n != 0) {
            n &= n - 1;
            result++;
        }
        return result;
    }
}
