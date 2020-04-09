package RMQ;

public class ST表最优写法 {
    public static void main(String[] args) {
        n = 7;
        init();
        for (int i = 1; i <= 7; i++) {
            System.out.println(query(1, i));
        }
    }

    static int n, m;
    static int[] log;
    static int[][] st;
    static int[] arr = {22, 23, 8, 67, 7, 7, 2};

    static void init() {
        log = new int[n + 2];
        log[1] = 0;
        for (int i = 2; i <= n + 1; i++) {
            log[i] = log[i / 2] + 1;
        }
        st = new int[n + 2][log[n] + 1];
        for (int i = 1; i <= n; i++) {
            st[i][0] = arr[i - 1];
        }
        for (int j = 1; 1 << j <= n; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << j - 1)][j - 1]);
            }
        }
    }

    static int query(int l, int r) {
        int k = log[r - l + 1];
        return Math.min(st[l][k], st[r - (1 << k) + 1][k]);
    }
}
