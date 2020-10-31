package DFS.搜索;

public class N皇后 {
    static int[] rec = new int[999];

    static int n = 4, ans = 0;

    public static void main(String[] args) {
        df(0);
        System.out.println(ans);
    }

    static void df(int row) {
        if (row == n) {
            ans++;
            return;
        }
        //枚举放在第row行的哪一列
        for (int col = 0; col < n; col++) {
            boolean f = true;
            for (int j = 0; j < row; j++) {//枚举前面每一行
                // 前面皇后的坐标是(j,rec[j]),前面一共有row个皇后
                if (rec[j] == col || j + rec[j] == row + col || j - rec[j] == row - col) {
                    f = false;
                    break;
                }
            }
            if (f) {
                rec[row] = col;
                df(row + 1);
            }
        }
    }
}
