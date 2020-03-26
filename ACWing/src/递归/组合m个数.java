package 递归;

/**
 * 93. 递归实现组合型枚举
 * 从 1~n 这 n 个整数中随机选出 m 个，输出所有可能的选择方案。
 * 输入格式
 * 两个整数 n,m ,在同一行用空格隔开。
 * 输出格式
 * 按照从小到大的顺序输出所有方案，每行1个。
 * 首先，同一行内的数升序排列，相邻两个数用一个空格隔开。
 * 其次，对于两个不同的行，对应下标的数一一比较，字典序较小的排在前面（例如1 3 5 7排在1 3 6 8前面）。
 * 数据范围
 * n>0 ,
 * 0≤m≤n ,
 * n+(n−m)≤25
 * 输入样例：
 * 5 3
 * 输出样例：
 * 1 2 3
 * 1 2 4
 * 1 2 5
 * 1 3 4
 * 1 3 5
 * 1 4 5
 * 2 3 4
 * 2 3 5
 * 2 4 5
 * 3 4 5
 */
public class 组合m个数 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(in);
//        n = sc.nextInt();
//        m = sc.nextInt();
        long l = System.nanoTime();
        dfs(0, 0, 0);
        long e = System.nanoTime();
        System.out.println(e - l);

    }

    static int m = 15, n = 10;

    //枚举到第u位 ,sum是当前选了几个,state是vis数组
    static void dfs(int u, int sum, int state) {
        if (sum + (n - u) < m) return;//就是n-u就是剩余还没有选的数字的数量,
        // sum+(n-u)<m就是剩余的数字都选上,也不够m个数字,所以剪枝
        if (sum == m) {//选了m个就输出
            for (int i = 0; i < n; i++) {
                if (((state >> i) & 1) == 1)
                    System.out.print(i + 1 + " ");
            }
            System.out.println();
            return;
        }
        if (u == n) return;//所有数字都选完了
        dfs(u + 1, sum + 1, state | (1 << u));//能选就先选,保持字典序!!!
        dfs(u + 1, sum, state);//选不选第u个数
    }
}
