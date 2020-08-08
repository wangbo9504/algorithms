package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94.二叉树的中序遍历
 *
 * 给定一个二叉树，返回它的中序 遍历
 *
 * 示例:
 * 输入: [1,null,2,3]
 * 1
 *  \
 *   2
 *  /
 * 3
 * 输出: [1,3,2]
 */
public class BinaryTreeInorderTraversal_94 {

    /**
     * 递归实现
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        // 递归结束条件
        if (root == null) return;
        // 中序遍历的访问顺序为: 左 -> 根 -> 右
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    /**
     * 显式调用栈DFS版本
     */
    public List<Integer> inorderTraversalByStack(TreeNode root) {
        List<Integer> result = new ArrayList();
        // 空节点直接返回
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack();
        // 创建一个指针指向当前节点
        TreeNode current = root;
        while (current != null || !stack.empty()) {
            // 若当前节点不为空
            while (current != null) {
                // 当前节点入栈, 然后循环访问左子树
                stack.push(current);
                current = current.left;
            }
            // 若当前节点为空, 说明没有左子树了, 弹出栈中父节点记录值后遍历其右子树
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }
}
