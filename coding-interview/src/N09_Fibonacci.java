/**
 * 9.斐波那契数列
 *
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第 n 项（从 0 开始，第 0 项为 0）。n<=39
 */
public class N09_Fibonacci {

    /**
     * 迭代法，从下往上计算
     */
    public int Fibonacci(int n) {
        if (n < 2) {
            return n;
        }
        int result = 0;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }
}
