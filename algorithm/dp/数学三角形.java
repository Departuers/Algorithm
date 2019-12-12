package dp;

public class 数学三角形 {
    public static void main(String[] args) {
        int[][] data = {
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        };
        MaxSum(data, 0, 0);
    }

    public static void MaxSum(int[][] tri, int i, int j) {
        int rowCount = tri.length;//行数
        int columnCount = tri[rowCount - 1].length;//最后一行的列数,也就是最后一排有多少个
        int[][] dp = new int[rowCount][columnCount];//
        for (int k = 0; k < columnCount; k++) {
            dp[rowCount - 1][k] = tri[rowCount - 1][k];//初始化最后一行
        }
        for (int k = rowCount - 2; k >= 0; k--) {
            for (int l = 0; l <= k; l++) {
                dp[k][l] = tri[k][l] + Math.max(dp[k + 1][l], dp[k + 1][l + 1]);
            }
        }
        System.out.println(dp[0][0]);
    }

    /**
     * 使用一维数组的解法
     * @param tri
     * @param i
     * @param j
     */
    public static void MaxSumByOne(int[][] tri,int i,int j){

    }
}
