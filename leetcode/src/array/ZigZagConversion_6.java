package array;

/**
 * 6.Z字形变换
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下:
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如: "LCIRETOESIIGEDHN"
 *
 * 示例:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 */
public class ZigZagConversion_6 {

    /**
     * 轨迹遍历法
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public String convert(String s, int numRows) {
        // 处理边界情况
        if (numRows == 1) return s;
        // 创建一个可变字符串数组对象并进行初始化
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
        // Z形变换的轨迹以 2n-1 次为一个周期, 即正着走完n下, 再倒着走n-1下, 所以轨迹周期为0 ～ 2n-2
        int period = (numRows - 1) * 2;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 用字符数组下标对轨迹周期取余, 若余数小于行数代表处于正向轨迹, 反之则代表逆向轨迹
            int index = i % period < numRows ? i % period : period - (i % period);
            sbs[index].append(c);
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : sbs) {
            result.append(sb);
        }
        return result.toString();
    }
}
