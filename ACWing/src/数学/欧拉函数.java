package 数学;
//https://blog.csdn.net/weixin_43237242/article/details/97388834
public class 欧拉函数 {
    public static void main(String[] args) {
        System.out.println(euler(6));
    }

    static long euler(long n) {
        long ans = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                ans = ans / i * (i - 1);
                while (n % i == 0) {
                    n/=i;
                }
            }
        }
        if (n>1)ans=ans/n*(n-1);
        return ans;
    }
}
