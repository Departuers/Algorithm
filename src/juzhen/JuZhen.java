package juzhen;

public class JuZhen {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        print(arr);
    }

    /**
     * 顺时针打印二维数组
     *
     * @param arr
     */
    public static void print(int[][] arr) {
        int leftUpRow = 0, leftUpCol = 0, rightDownRow = arr.length - 1, rightDownCol = arr[0].length - 1;
        //左上角行，左上角列，右下角行，右下角列
        while (leftUpCol <= rightDownCol) {
            System.out.print(arr[leftUpRow][leftUpCol++] + " ");
        }
        //恢复
        leftUpCol = rightDownCol;
        leftUpRow++;//行数往下走
        while (leftUpRow <= rightDownRow)//行变列不变
        {
            System.out.print(arr[leftUpRow++][rightDownRow] + " ");
        }
        leftUpRow = rightDownRow;//恢复
        leftUpCol--;
        while (leftUpCol >= rightDownCol) {
        }
    }
}
