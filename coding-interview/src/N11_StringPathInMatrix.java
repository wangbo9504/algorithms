/**
 * 11.矩阵中的路径
 *
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子
 *
 * 例如
 *       a b c e
 *       s f c s   矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子，路径不能再次进入
 *       a d e e
 *
 */
public class N11_StringPathInMatrix {

    /**
     * 解法1 递归回溯, 开辟一个数组判断元素是否被访问过
     */

    private static boolean[][] visited;

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        // 将矩阵转为二维
        char[][] board = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = matrix[i * cols + j];
            }
        }
        // 初始化visited数组
        visited = new boolean[rows][cols];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 从[i, j]这个坐标开始查找
                if (dfs(board, str, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] words, int row, int cols, int index) {
        // 边界条件判断, 如果越界直接返回false; 如果这个字符不等于board[row][cols], 说明这条路径走不通, 也返回false
        if (row >= board.length || row < 0 || cols >= board[0].length || cols < 0 || board[row][cols] != words[index] || visited[row][cols]) {
            return false;
        }
        // 如果word的每个字符都检查完了, 说明完全匹配成功, 返回true
        if (words.length - 1 == index) {
            return true;
        }
        visited[row][cols] = true;
        // drill down, 沿着当前坐标的上下左右4个方向查找
        boolean result = dfs(board, words, row + 1, cols, index + 1)
                || dfs(board, words, row - 1, cols, index + 1)
                || dfs(board, words, row, cols + 1, index + 1)
                || dfs(board, words, row, cols - 1, index + 1);
        // 回溯
        visited[row][cols] = false;
        return result;
    }

    /**
     * 解法2. 不浪费额外空间, 在查找的过程中为当前坐标设置一个目标字符串不包含的特殊字符来防止重复访问
     */

    public boolean hasPath_2(char[] matrix, int rows, int cols, char[] str) {
        // 将矩阵转为二维
        char[][] board = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = matrix[i * cols + j];
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 从[i, j]这个坐标开始查找
                if (dfs_2(board, str, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs_2(char[][] board, char[] words, int row, int cols, int index) {
        // 边界条件判断, 如果越界直接返回false; 如果这个字符不等于board[row][cols], 说明这条路径走不通, 也返回false
        if (row >= board.length || row < 0 || cols >= board[0].length || cols < 0 || board[row][cols] != words[index]) {
            return false;
        }
        // 如果word的每个字符都检查完了, 说明完全匹配成功, 返回true
        if (words.length - 1 == index) {
            return true;
        }
        // 将当前坐标值保留下来, 之后再复原
        char tmp = board[row][cols];
        // 修改当前坐标的值为字符串中不会包含的字符, 防止重复访问
        board[row][cols] = '/';
        // drill down, 沿着当前坐标的上下左右4个方向查找
        boolean result = dfs_2(board, words, row + 1, cols, index + 1)
                || dfs_2(board, words, row - 1, cols, index + 1)
                || dfs_2(board, words, row, cols + 1, index + 1)
                || dfs_2(board, words, row, cols - 1, index + 1);
        // 复原当前坐标值, 回溯
        board[row][cols] = tmp;
        return result;
    }
}
