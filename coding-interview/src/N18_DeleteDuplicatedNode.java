/**
 * 18.删除链表中重复的节点
 *
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针
 *
 * 示例:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 */
public class N18_DeleteDuplicatedNode {

    /**
     * 双指针法
     */
    public ListNode deleteDuplication_1(ListNode head) {
        // 由于头节点也有可能是重复节点, 所以需要创建一个空的辅助节点作为真正不会删除的头节点
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        // 由于需要返回头指针, 所以头节点不能移动, 再创建一个移动节点进行遍历
        ListNode curr = dummyNode;
        while (curr.next != null && curr.next.next != null) {
            // 当下一个节点与下下个节点的值相同时
            if (curr.next.val == curr.next.next.val) {
                // 构建一个新的临时指针指向下一个节点(即第一个重复的元素), 然后循环遍历所有重复的元素, 让临时指针最终指向最后一个重复的元素
                ListNode temp = curr.next;
                while (temp != null && temp.next != null && temp.val == temp.next.val) {
                    temp = temp.next;
                }
                // 跳过所有重复元素, 将下一个节点指向最后一个重复元素的下一个元素
                curr.next = temp.next;
            } else {
                curr = curr.next;
            }
        }
        return dummyNode.next;
    }

    /**
     * 递归法
     */
    public ListNode deleteDuplication_2(ListNode head) {
        // 过滤特殊情况, 即头节点为null或头节点的下一个节点为null直接返回
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            // 如果头节点与头节点下一节点值相等, 则循环遍历跳过所有重复节点
            while (head != null && head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            // 递归调用判断最后一个跳过节点的后一节点
            return deleteDuplication_2(head.next);
        } else {
            // 如果头节点与头节点下一个节点值不等, 将下一节点指向不重复的后一节点
            head.next = deleteDuplication_2(head.next);
            return head;
        }
    }
}
