package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 51.N皇后
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位
 *
 */
public class NQueens_51 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList();
        // 初始化棋盘, '.'表示空, 'Q'表示皇后
        char[][] board = new char[n][n];
        // 初始化棋盘所有元素为'.'
        for (char[] line : board) {
            Arrays.fill(line, '.');
        }
        // 决策树的每一层代表棋盘的每一行, 从第0行开始
        backtrack(result, board, 0);
        return result;
    }

    /**
     * 路径: board中小于row的那些行都已经成功放置了皇后
     * 选择列表: 第row行的所有列
     * 结束条件: row超过board的最后一行
     */
    private void backtrack(List<List<String>> result, char[][] board, int row) {
        if (row == board.length) {
            // char二维数组转为String一维数组
            List<String> scheme = new LinkedList();
            for (char[] line : board) {
                scheme.add(String.valueOf(line));
            }
            result.add(scheme);
            return;
        }
        for (int col = 0; col < board.length; col++) {
            // 排除不合法的选择
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            board[row][col] = 'Q';
            // 进入下一层决策树
            backtrack(result, board, row + 1);
            // 撤销选择
            board[row][col] = '.';
        }
    }

    /**
     * 是否可以在board[row][col]放置皇后?
     */
    private boolean isValid(char[][] board, int row, int col) {
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        // 检查右上方是否有皇后冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // 检查左上方是否有皇后冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // 无冲突
        return true;
    }
}
