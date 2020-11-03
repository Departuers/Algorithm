package 数学;

/**
 *
 */
public class 埃氏筛法 {
    public static void main(String[] args) {
        long s = System.nanoTime();
        e(12412561);
        long t = System.nanoTime();
        System.out.println((t - s) / 1e8);
        s = System.nanoTime();
        euler(12412561);
        t = System.nanoTime();
        System.out.println((t - s) / 1e8);
        System.out.println();
    }

    static int[] primer = new int[99999991];

    /**
     * 埃氏筛法求n以内的质数
     * 调和级数极限为 ln n+C  C为euler 常数
     * 不加优化是O(n log n) 加上是O(n log log n)
     * 1+1/2+1/3+...
     * 第一个数2会筛n/2次
     * 为什么筛法是正确的呢
     * 对于一个数P而言,如果前面2~P-1都没有把它筛掉
     * 也就是2~P-1中没有P的约数
     * 所以P就是一个质数
     * 对于P来说,2~P-1之间只要所有的质数,不是P的约数即可
     * 也可以判断P是质数
     * @param n
     */
    static void e(int n) {
        int cnt = 0;
        boolean[] st = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!st[i]) {//只需要筛所有质数的倍数
                primer[cnt++] = i;
                for (int j = 2 * i; j <= n; j += i) {
                    st[j] = true;
                }
            }
        }
        System.out.println(primer[cnt - 1]);
    }
    /**
     * 线性筛法,1.正确性,2.原理
     * 合数是否一定会被筛掉
     * 一个数N只会被它的最小质因子筛掉
     * 对于一个合数X
     * 合数X一定存在最小质因子,假设p[j]是X的最小质因子
     * 当i枚举到X/primer[j],就会把X给筛掉
     * @param n
     */
    static void euler(int n) {
        boolean[] st = new boolean[n + 1];
        int cnt = 0;
        System.out.println();
        for (int i = 2; i <= n; i++) {
            if (!st[i]) primer[cnt++] = i;
            //把i的质数倍筛出去
            for (int j = 0; primer[j] * i <= n; j++) {//从小到大枚举所有的质数
                st[primer[j] * i] = true;//primer[j]也一定是primer[j]*i的最小质因子
                if (i % primer[j] == 0) break;//意味着,primer[j]一定是i的最小质因子

            }
        }
        System.out.println(primer[cnt - 1]);
    }
}
