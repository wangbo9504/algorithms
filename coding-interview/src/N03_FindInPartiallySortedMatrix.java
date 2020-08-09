/**
 * 3.二维数组中的查找
 *
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
 */
public class N03_FindInPartiallySortedMatrix {

    /**
     * 从数组左下角(或右上角, 因为这两个位置可以同时增大或缩小范围, 另外两个只能递减或递增)开始遍历元素值
     * 若target等于元素值, 返回true
     * 若target大于元素值, 加列保证数组元素递增
     * 若target小于元素值, 减行保证数组元素递减
     */
    public boolean find(int target, int[][] array) {
        if (array == null || array.length == 0) return false;
        // 获取最大行
        int maxRow = array.length - 1;
        // 获取最大列
        int maxColumn = array[0].length - 1;
        for (int row = maxRow, column = 0; row >= 0 && column <= maxColumn; ) {
            if (target > array[row][column]) {
                column++;
            } else if (target < array[row][column]) {
                row--;
            } else {
                return true;
            }
        }
        return false;
    }
}
