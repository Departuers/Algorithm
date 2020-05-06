package dp.树形dp;

public class 二叉苹果树 {
    public static void main(String[] args) {

    }

    static int n, m, cnt = 1;
    static int[] he = new int[100];
    static int[] ne = new int[100];
    static int[] e = new int[100];

    static void add(int a, int b) {
        e[cnt] = b;
        ne[cnt] = he[a];
        he[a] = cnt++;
    }
}
