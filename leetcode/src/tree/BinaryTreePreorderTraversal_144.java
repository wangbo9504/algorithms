package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144.二叉树的前序遍历
 *
 * 给定一个二叉树，返回它的 前序 遍历
 *
 * 示例:
 * 输入: [1,null,2,3]
 * 1
 *  \
 *   2
 *  /
 * 3
 * 输出: [1,2,3]
 */
public class BinaryTreePreorderTraversal_144 {

    /**
     * 递归实现
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode root, List<Integer> result) {
        // 递归结束条件
        if (root == null) return;
        /**
         * 前序遍历的访问顺序为: 根 -> 左 -> 右
         * 先记录当前节点值
         */
        result.add(root.val);
        // drill down
        preorder(root.left, result);
        preorder(root.right, result);
    }

    /**
     * 显式调用栈DFS版本1
     */
    public List<Integer> preorderTraversalByStack1(TreeNode root) {
        List<Integer> result = new ArrayList();
        // 空节点直接返回
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack();
        // 创建一个指针指向当前节点
        TreeNode current = root;
        while (current != null || !stack.empty()) {
            // 若当前节点不为空
            while (current != null) {
                // 记录当前节点的值并入栈, 然后循环访问左子树
                result.add(current.val);
                stack.push(current);
                current = current.left;
            }
            // 若当前节点为空, 说明没有左子树了, 弹出栈中父节点遍历其右子树
            current = stack.pop().right;
        }
        return result;
    }

    /**
     * 显式调用栈DFS版本2
     */
    public List<Integer> preorderTraversalByStack2(TreeNode root) {
        List<Integer> result = new ArrayList();
        // 空节点直接返回
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack();
        // 先压入根结点
        stack.push(root);
        while (!stack.empty()) {
            // 弹出并记录当前节点的值
            TreeNode node = stack.pop();
            result.add(node.val);
            // 先压入右节点、再压入左节点(因为栈是先进后出的, 为了弹出的时候先访问左节点, 所以先压入右节点)
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }
}
