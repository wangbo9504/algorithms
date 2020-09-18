import java.util.Arrays;

/**
 * 16.打印从1到最大的n位数
 **/

public class N16_Print1ToMaxOfNDigits {

    // 不考虑大数问题解法
    public int[] printNumbers_1(int n) {
        int max = (int) Math.pow(10, n) - 1;
        int[] result = new int[max];
        for (int i = 0; i < max; ) {
            result[i] = ++i;
        }
        return result;
    }

    // 大数打印解法
    public void printNumbers_2(int n) {
        // 创建存储大数的字符数组
        char[] number = new char[n];
        Arrays.fill(number, '0');
        while (!incrementNumber(number)) {
            printNumber(number);
        }
    }

    private boolean incrementNumber(char[] number) {
        boolean isOverflow = false;
        int length = number.length;
        // 创建进位标识
        int carryFlag = 0;
        // 大数高低位与数组存放顺序相反, 由于需要从低位开始加和, 所以从数组高位开始遍历
        for (int n = length - 1; n >= 0; n--) {
            // 取出第n位字符转换为数字
            int nSum = number[n] - '0' + carryFlag;
            // 如果第n位是最低位, 直接加1
            if (n == length - 1) {
                ++nSum;
            }
            // 当加1后的值等于10的时候, 需要进行进位判断
            if (nSum == 10) {
                if (n == 0) {
                    // 如果进位为大数的最高位说明到达溢出临界值
                    isOverflow = true;
                } else {
                    // 否则初始化该位为0, 并设置进位为1
                    number[n] = '0';
                    carryFlag = 1;
                }
            } else {
                // 当加1后的值小于10的时候, 直接将数组转换为字符写入第n位
                number[n] = (char) (nSum + '0');
                break;
            }
        }
        return isOverflow;
    }

    private void printNumber(char[] number) {
        boolean isBegin0 = true;
        for (char c : number) {
            // 循环判断是否到达第一个不为0的值
            if (c != '0') {
                isBegin0 = false;
            }
            if (!isBegin0) {
                // 到达后开始打印
                System.out.print(c);
            }
        }
        System.out.println();
    }
}
