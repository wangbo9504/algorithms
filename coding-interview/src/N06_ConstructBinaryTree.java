import java.util.HashMap;
import java.util.Map;

/**
 * 6.重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列 {1,2,4,7,3,5,6,8} 和中序遍历序列 {4,7,2,1,5,3,8,6}，则重建二叉树并返回
 */
public class N06_ConstructBinaryTree {

    /**
     * 解法1
     */
    public TreeNode reConstructBinaryTree_1(int [] preorder, int [] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        int n = preorder.length;
        // 递归遍历
        return constructBinaryTree_1(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    private TreeNode constructBinaryTree_1(int[] preorder, int startPre, int endPre, int[] inorder, int startIn, int endIn) {
        TreeNode node = new TreeNode(preorder[startPre]);

        int inOrder = startIn;
        if (startPre == endPre) {
            if (startIn == endIn) {
                return node;
            }
            throw new IllegalArgumentException("Invalid input!");
        }
        while (inorder[inOrder] != preorder[startPre]) {
            ++inOrder;
            if (inOrder > endIn) {
                throw new IllegalArgumentException("Invalid input!");
            }
        }
        int len = inOrder - startIn;
        if (len > 0) {
            // 递归构建左子树
            node.left = constructBinaryTree_1(preorder, startPre + 1, startPre + len, inorder, startIn, inOrder - 1);
        }
        if (inOrder < endIn) {
            // 递归构建右子数
            node.right = constructBinaryTree_1(preorder, startPre + len + 1, endPre, inorder, inOrder + 1, endIn);
        }
        return node;
    }

    /**
     * 解法2
     */
    private int preIndex = 0;

    public TreeNode reConstructBinaryTree_2(int [] preorder, int [] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> inMap = new HashMap();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return constructBinaryTree_2(preorder, inMap, 0, preorder.length);
    }

    private TreeNode constructBinaryTree_2(int[] preorder, Map<Integer, Integer> inMap, int left, int right) {
        if (left == right) {
            return null;
        }
        int head =  preorder[preIndex++];
        TreeNode node = new TreeNode(head);
        int mid = inMap.get(head);
        node.left = constructBinaryTree_2(preorder, inMap, left, mid);
        node.right = constructBinaryTree_2(preorder, inMap, mid + 1, right);
        return node;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
