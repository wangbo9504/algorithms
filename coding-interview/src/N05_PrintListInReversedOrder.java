import java.util.ArrayList;
import java.util.Stack;

/**
 * 5.从尾到头打印链表
 *
 * 从尾到头打印链表
 */
public class N05_PrintListInReversedOrder {

    /**
     * 解法1. 先进后出, 使用栈进行存储
     */
    public ArrayList<Integer> printListFromTailToHead_1(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList();
        if (listNode == null) return result;
        Stack<ListNode> stack = new Stack();
        ListNode current = listNode;
        while (current != null) {
            stack.add(current);
            current = current.next;
        }
        while (!stack.empty()) {
            result.add(stack.pop().val);
        }
        return result;
    }

    /**
     * 解法2. 递归遍历
     */
    public ArrayList<Integer> printListFromTailToHead_2(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList();
        if (listNode == null) return result;
        addElement(listNode, result);
        return result;
    }

    private void addElement(ListNode listNode, ArrayList<Integer> result) {
        if (listNode.next != null) {
            addElement(listNode.next, result);
        }
        result.add(listNode.val);
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}