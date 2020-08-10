/**
 * 4.替换空格
 *
 * 请实现一个函数，将一个字符串中的每个空格替换成 %20
 *
 * 示例:
 * 输入: We Are Happy
 * 输出: We%20Are%20Happy
 */
public class N04_ReplaceSpaces {

    /**
     * 解法1. 创建StrinBuffer, 遍历原字符串, 遇到空格则append %20 否则直接append 原字符
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public String replaceSpace_1(StringBuffer str) {
        if (str == null || str.length() == 0) return "";
        StringBuffer result = new StringBuffer();
        for (char c : str.toString().toCharArray()) {
            result.append(c == ' ' ? "%20" : c);
        }
        return result.toString();
    }

    /**
     * 解法2. 先遍历原字符串，遇到空格，则在原字符串末尾 append 任意两个字符，如两个空格
     * 用指针 p 指向原字符串末尾，q 指向现字符串末尾，p, q 从后往前遍历，当 p 遇到空格，q 位置依次要 append '02%'，若不是空格，直接 append p 指向的字符
     *
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     */
    public String replaceSpace_2(StringBuffer str) {
        if (str == null || str.length() == 0) return "";
        int len = str.length();
        for (char c : str.toString().toCharArray()) {
            if (c == ' ') {
                str.append("  ");
            }
        }
        int p = len - 1;
        int q = str.length() - 1;
        while (p >= 0) {
            char c = str.charAt(p--);
            if (c == ' ') {
                str.setCharAt(q--, '0');
                str.setCharAt(q--, '2');
                str.setCharAt(q--, '%');
            } else {
                str.setCharAt(q--, c);
            }
        }
        return str.toString();
    }
}
