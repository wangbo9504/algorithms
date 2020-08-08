package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 145.给定一个二叉树，返回它的 后序 遍历
 *
 * 示例:
 * 输入: [1,null,2,3]
 * 1
 *  \
 *   2
 *  /
 * 3
 * 输出: [3,2,1]
 */
public class BinaryTreePostorderTraversal_145 {

    /**
     * 递归实现
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode root, List<Integer> result) {
        // 递归结束条件
        if (root == null) return;
        // 后序遍历的访问顺序为: 左 -> 右 -> 根
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }

    /**
     * 利用双端队列实现
     */
    public static List<Integer> postorderTraversalByStackAndDeque(TreeNode root) {
        LinkedList<Integer> result = new LinkedList();
        if (root == null) return result;
        LinkedList<TreeNode> deque = new LinkedList();
        // 将根结点插入到树节点队列尾部
        deque.add(root);
        while (!deque.isEmpty()) {
            // 获取树节点队列尾部元素
            TreeNode node = deque.pollLast();
            // 将结果放入结果队列头部(插入顺序为 根 -> 右 -> 左, 由于是逆序插入所以再最后展示时顺序为 左 -> 右 -> 根)
            result.addFirst(node.val);
            if (node.left != null) {
                // 将左右节点依次压入树节点队列尾部(由于结果队列是按逆序插入, 所以需要最后再插入右节点以便于在下次获取的时候先获取到右节点)
                deque.add(node.left);
            }
            if (node.right != null) {
                deque.add(node.right);
            }
        }
        return result;
    }
}
