package linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 141.环形链表
 *
 * 给定一个链表，判断链表中是否有环
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始），如果 pos 是 -1，则在该链表中没有环
 *
 * 示例:
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点
 */
public class LinkedListCycle_141 {

    /**
     * 快慢指针法
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * 定义两个迭代速度不同的快慢指针(相当于环形赛道上的两名运动员),如果链表有环,则快指针在之后的迭代过程中一定会追上慢指针
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        //
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    /**
     * 哈希法
     * 时间复杂度 O(n)
     * 空间复杂度 O(n)
     *
     * 遍历链表的过程中将元素存入哈希表,如果链表有环则新迭代的元素必然会重复出现在哈希表中
     */
    public boolean hasCycleByHash(ListNode head) {
        Set<ListNode> set = new HashSet();
        while (head.next != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }
}
