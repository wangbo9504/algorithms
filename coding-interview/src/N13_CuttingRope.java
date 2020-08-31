/**
 * 13.剪绳子
 *
 * 给你一根长度为n的绳子，请把绳子剪成整数长的m段(m、n都是整数，n>1并且m>1，m<=n),每段绳子的长度记为k[1],...,k[m]。
 * 请问k[1]x...xk[m]可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
 **/

public class N13_CuttingRope {

    /**
     * 解法1. 动态规划
     * 定义f(n)为把长度为n的绳子剪成若干段后各段长度乘积的最大值;
     * 当剪第一刀时, 我们有n - 1种可能的选择, 也就是剪出来一段绳子可能的长度分别为1, 2, ..., n - 1, 因此f(n) = max(f(i) * f(n - i));
     * 所以可以通过自下而上的计算消除重复自问题, 先得到f(2), 再得到f(3)...直到f(n)
     */
    public int cutRope_1(int target) {
        if (target < 2) {
            return 0;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        int[] products = new int[target + 1];
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;
        for (int i = 4; i <= target; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                max = Math.max(max, products[j] * products[i - j]);
                products[i] = max;
            }
        }
        return products[target];
    }

    /**
     * 贪婪算法
     * 当n >= 5时, 尽可能多的剪长度为3的绳子(并); 当剩下的绳子长度为4时, 把绳子剪成2段长度为2的绳子(因为2 * 2 > 1 * 3)
     */
    public int cutRope_2(int target) {
        if (target < 2) {
            return 0;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        // 尽可能多的剪去长度为3的绳子段数
        int timeOf3 = target / 3;
        // 当最后一段等于4的时候, 减去一次长度为3的绳子段数, 将它记录到剪为2段的绳子段数中
        if (target - timeOf3 * 3 == 1) {
            timeOf3 -= 1;
        }
        int timeOf2 = (target - timeOf3 * 3) / 2;
        Double result = Math.pow(3, timeOf3) * Math.pow(2, timeOf2);
        return result.intValue();
    }
}
