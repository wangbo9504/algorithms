/**
 * 15.数值的整数次方
 *
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方
 */
public class N15_Power {

    public double power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        double result = base;
        // 将base循环乘以exponent的绝对值 - 1次
        for (int i = 0; i < Math.abs(exponent) - 1; i++) {
            base *= result;
        }
        // 如果幂为负数则取倒数, 否则返回结果
        return exponent > 0 ? base : 1/base;
    }
}
