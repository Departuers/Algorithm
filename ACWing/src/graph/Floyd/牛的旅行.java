package graph.Floyd;

/**
 * https://blog.csdn.net/qq_30277239/article/details/107301496
 * https://www.cnblogs.com/ctyakwf/p/12829458.html
 */
public class 牛的旅行 {
    public static void main(String[] args) {
        long i = 0;
        long s = System.nanoTime();
        long t = 0;
        for (i = 0; ; i++) {
            if (i % 100000 == 0 && (System.nanoTime() - s) / 1e9 >= 0.98)
                break;
        }
        System.out.println(i);
    }
}
