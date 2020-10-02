package DFS.搜索;

/**
 * 给定一个数n,让你找出一个只由0,1组成的十进制数m,
 * 要求这个正整数m可以被n整除
 * 最多19位
 * 比如   2|10
 * 不能有前导0
 * input:
 * 2
 * out:
 * 10
 */
public class 找数字 {
    public static void main(String[] args) {
        dfs(1, 0);

    }

    static int n = 2, ok = -1;

    static void dfs(long sum, int cnt) {
        if (cnt >= 19) return;//最多要求19位
        if (ok == 1) return;
        if (sum % n == 0) {
            ok = 1;
            System.out.println(sum);
            return;
        }
        dfs(sum * 10, cnt + 1);
        dfs(sum * 10 + 1, cnt + 1);
    }
}
