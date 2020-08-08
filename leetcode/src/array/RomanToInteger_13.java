package array;

/**
 * 13.罗马数字转整数
 *
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M
 *
 * 字符          数值
 *  I             1
 *  V             5
 *  X             10
 *  L             50
 *  C             100
 *  D             500
 *  M             1000
 *
 *  例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II
 *  通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV，这个特殊的规则只适用于以下六种情况
 *  I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9
 *  X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 *  C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900
 *  给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内
 *
 *  示例:
 *  输入: "LVIII"
 *  输出: 58
 */
public class RomanToInteger_13 {

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public int romanToInt(String s) {
        if (s.isEmpty()) return 0;
        int result = 0;
        char[] romanArr = s.toCharArray();
        for (int index = 0; index < romanArr.length; index++) {
            // 判断当前罗马数字是否比下一位小, 是则做减法, 否则做加法
            int currentNum = getIntByRoman(romanArr[index]);
            if (index + 1 < romanArr.length && getIntByRoman(romanArr[index + 1]) > currentNum) {
                result -= currentNum;
            } else {
                result += currentNum;
            }
        }
        return result;
    }

    // 创建罗马数字与阿拉伯数字的对照表
    private int getIntByRoman(char roman) {
        switch (roman) {
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
            default : return 0;
        }
    }
}
