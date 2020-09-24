package dp;

import java.util.Arrays;

/**
 * 322.零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数
 * 如果没有任何一种硬币组合能组成总金额，返回 -1
 *
 * 示例:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 */
public class CoinChange_322 {

    /**
     * dp穷举(自顶向下)
     */
    public int coinChange_dp(int[] coins, int amount) {
        // base case
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        // 由于凑成amount金额的硬币数最多只可能等于amount, 所以初始化一个amount + 1相当于正无穷, 用于后续对比取出最小值
        int result = amount + 1;
        for (int coin : coins) {
            int subResult = coinChange_dp(coins, amount - coin);
            // 自问题无解, 直接跳过当前选择列表
            if (subResult == -1) {
                continue;
            }
            // 做选择, 选出需要硬币最少的结果
            result = Math.min(result, subResult + 1);
        }
        // 结果等于amount + 1则代表无解, 返回-1
        return result == amount + 1 ? -1 : result;
    }

    /**
     * dp备忘录(自顶向下)
     */
    public int coinChange_memo(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        dp(coins, amount, memo);
        return memo[amount];
    }

    private int dp(int[] coins, int amount, int[] memo) {
        // base case
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        // 备忘录中没有结果则进行计算记录对应结果
        if (memo[amount] == 0) {
            int result = amount + 1;
            for (int coin : coins) {
                int subResult = dp(coins, amount - coin, memo);
                if (subResult == -1) {
                    continue;
                }
                result = Math.min(result, subResult + 1);
            }
            memo[amount] = (result == amount + 1 ? -1 : result);
        }
        return memo[amount];
    }

    /**
     * dp迭代(自底向上)
     */
    public int coinChange_iterator(int[] coins, int amount) {
        // 创建金额所需硬币数数组并初始化每一位的结果为amount + 1, 用于后续对比取出最小结果
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        // 设置base case结果, 即金额0需要0枚硬币
        dp[0] = 0;
        // 迭代从0～amount所有金额的结果
        for (int curAmount = 1; curAmount < amount + 1; curAmount++) {
            for (int coin : coins) {
                // 当前硬币面值比当前金额大, 无解直接跳过
                if (curAmount - coin < 0) {
                    continue;
                }
                // 选出需要硬币最少的结果
                dp[curAmount] = Math.min(dp[curAmount], 1 + dp[curAmount - coin]);
            }
        }
        // 结果等于amount + 1则代表无解, 返回-1
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
