import java.util.Stack;

/**
 * 8.用两个栈实现队列
 *
 * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。队列中的元素为 int 类型
 */
public class N08_QueueWithTwoStacks {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 每次新增元素都插入stack1中
     */
    public void push(int node) {
        stack1.push(node);
    }

    /**
     * 当第一次需要删除元素时，由于元素全部以先进后出的方式压入stack1中，而stack2此时为空，所以可以将stack1中的元素全部弹出后压入stack2，此时stack2中元素即为先进先出顺序，然后直接弹出stack2栈顶元素即可
     * 之后再删除元素时，如果stack2中有值则顺序一定比后面压入stack1的元素高，所以直接弹出即可，如果stack2为空，则重复之前的操作继续将stack1的元素全部弹出后压入stack2再弹出stack2的栈顶元素即可
     */
    public int pop() {
        if (stack1.empty() && stack2.empty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
