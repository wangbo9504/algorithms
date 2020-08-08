package stack;

import java.util.Stack;

/**
 * 20.有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效
 * 有效字符串需满足:
 *     1、左括号必须用相同类型的右括号闭合
 *     2、左括号必须以正确的顺序闭合
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例:
 * 输入: "{[]}"
 * 输出: true
 */
public class ValidParentheses_20 {

    /**
     *  解法一
     *  时间复杂度 O(n)
     *  空间复杂度 O(n)
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            // 将左括号都压入栈中
            if (stack.empty() || (c == '(' || c == '{' || c == '[')) {
                stack.push(c);
            } else {
                // 将栈中左括号弹出并与当前遍历的右括号进行匹配(参数按序传入)
                if (isSymmetry(stack.pop(), c)) {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    private boolean isSymmetry(char c1, char c2) { // 传参时第一个值为左括号，第二个值为右括号
        if ((c1 == '(' && c2 == ')')
                || (c1 == '{') && (c2 == '}')
                || (c1 == '[' && c2 == ']')) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Most Votes解法
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     */
    public boolean mostVotesVersion(String s) {
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            /**
             * 将左括号全部转为右括号压入栈中(左转右的好处是当第一个字符为右括号时可以直接跳出循环)
             */
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.empty() || c != stack.pop()) {
                // 当栈为空(最左侧字符为右括号)或栈中弹出的右括号与当前右括号不匹配时，直接返回false结果
                return false;
            }
        }
        return stack.empty();
    }
}
