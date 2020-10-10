package DFS.递归;

/**
 * 3阶幻方,横竖之和都是15,对角线之和也是15
 */
public class q {
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            arr[i] = i + 1;
        }
        long s = System.nanoTime();
        dfs(0, 0);
        long t = System.nanoTime();
        System.out.println((t - s) / 1e9);
    }

    static int[] arr = new int[9];
    static int tem = 0;

    static void dfs(int u, int k) {
        if (u == 9) {
            tem = 0;
            for (int i = 0; i < 9; i++) {
                tem += arr[i];
                if (i % 3 == 2) {
                    if (tem != 15) return;
                    tem = 0;
                }
            }
            tem = 0;
            for (int i = 0; i <= 2; i++) {
                for (int j = i; j <= i + 6; j += 3) {
                    tem += arr[j];
                }
                if (tem != 15) return;
                tem = 0;
            }
            if (arr[0] + arr[4] + arr[8] != 15 || arr[2] + arr[4] + arr[6] != 15) return;
            for (int i = 0; i < 9; i++) {
                System.out.print(arr[i] + " ");
                if (i % 3 == 2) System.out.println();
            }
            System.out.println();
            return;
        }
        for (int i = k; i < 9; i++) {
            int t = arr[i];
            arr[i] = arr[k];
            arr[k] = t;
            dfs(u + 1, k + 1);
            t = arr[i];
            arr[i] = arr[k];
            arr[k] = t;
        }
    }
}
