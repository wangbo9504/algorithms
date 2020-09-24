/**
 * 9.斐波那契数列
 *
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第 n 项（从 0 开始，第 0 项为 0）。n<=39
 */
public class N09_Fibonacci {

    /**
     * 备忘录法(自顶向下)
     */
    public int fibonacci_memo(int n) {
        if (n < 1) {
            return 0;
        }
        int[] memo = new int[n + 1];
        return helper(memo, n);
    }

    private int helper(int[] memo, int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (memo[n] != 0) {
            return memo[n];
        } else {
            memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        }
        return memo[n];
    }

    /**
     * dp动态规划(自底向上)
     */
    public int fibonacci_dp(int n) {
        if (n < 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 由于当前状态只和之前的2个状态有关, 所以只需要使用两个指针存储最近2个状态的值即可
     */
    public int fibonacci_best(int n) {
        if (n < 1) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int prev = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int result = prev + curr;
            prev = curr;
            curr = result;
        }
        return curr;
    }
}
