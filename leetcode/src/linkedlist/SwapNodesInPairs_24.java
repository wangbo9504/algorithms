package linkedlist;

/**
 * 24.两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
 *
 * 示例:
 * 输入: 1->2->3->4
 * 输出: 2->1->4->3
 */
public class SwapNodesInPairs_24 {

    /**
     * 递归
     */
    public ListNode swapPairs_1(ListNode head) {
        // 递归中止条件, 无节点或只剩一个
        if (head == null || head.next == null) {
            return head;
        }
        // 设置要交换的两个节点
        ListNode firstNode = head;
        ListNode secondNode = head.next;
        // 第一个节点连接后面完成的子链表
        firstNode.next = swapPairs_1(secondNode.next);
        // 第二个节点直接连接第一个节点
        secondNode.next = firstNode;
        // 返回secondNode, 因为它是交换后的新头
        return secondNode;
    }

    /**
     * 迭代解法
     */
    public ListNode swapPairs_2(ListNode head) {
        // 创建一个空的头节点
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        // 创建一个临时节点指向头节点
        ListNode prev = dummyNode;
        while (prev.next != null && prev.next.next != null) {
            // 设置要交换的两个节点
            ListNode left = prev.next;
            ListNode right = prev.next.next;
            /**
             * 预期效果的顺序是pre节点之后是right, right之后是left, left之后是right的next
             * 由于链表的单向性, 只能从前往后访问节点, 而反转链表则需要逆序执行前面的顺序, 即从右边开始修改, 再慢慢覆盖左边
             * 所以需要先操作left、再操作right、最后操作prev
             */
            left.next = right.next;
            right.next = left;
            prev.next = right;
            // 继续循环
            prev = left;
        }
        return dummyNode.next;
    }
}
