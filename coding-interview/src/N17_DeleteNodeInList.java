/**
 * 17.删除链表的节点
 *
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点
 * 返回删除后的链表的头节点
 *
 * 示例:
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 **/

public class N17_DeleteNodeInList {
    public ListNode deleteNode(ListNode head, int val) {
        // 特殊情况: 头节点即位要删除的节点, 直接返回下一个节点
        if (head.val == val) {
            return head.next;
        }
        // 定义两个指针, 一个指向前一节点, 一个指向当前节点
        ListNode prev = head, curr = head.next;
        // 当前节点不是删除节点时循环遍历
        while (curr != null && curr.val != val) {
            prev = curr;
            curr = curr.next;
        }
        // 当前节点为删除节点时, 直接将前一节点的下一个指针指向当前节点的下一个指针
        if (curr.val == val) {
            prev.next = curr.next;
        }
        return head;
    }
}