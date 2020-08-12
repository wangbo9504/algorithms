/**
 * 7.二叉树的下一个结点
 *
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针
 */
public class N07_NextNodeInBinaryTrees {

    /**
     * 1、如果一个结点pNode有右子树，那么它的下一个结点就是它的 【右子树的最左子结点】
     * 2、如果一个结点pNode没有右子树，则判断它与父结点pNode.next的位置关系：
     *      如果它是父结点的左子结点，那么它的下一个结点就是他的 【父结点】pNode.next
     *      如果它是父结点的右子结点，那么就需要沿着它的父结点向上遍历，直到找到某个结点【它是它父结点的左孩子】那么该父结点就是pNode的下一个结点
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        if (pNode.right != null) {
            TreeLinkNode node = pNode.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        if (pNode.next != null && pNode.next.left == pNode) {
            return pNode.next;
        }
        while (pNode.next != null) {
            if (pNode == pNode.next.left) {
                return pNode.next;
            }
            pNode = pNode.next;
        }
        return null;
    }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
