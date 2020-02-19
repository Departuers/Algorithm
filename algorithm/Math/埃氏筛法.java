package Math;

public class 埃氏筛法 {
    public static void main(String[] args) {
        System.out.println(AIshi(4));
    }

    /**
     * 埃氏筛法,判断一群数是不是质数,或者求第多少个是素数
     * 剩余定理:非常牛逼
     */
    public static int AIshi(int N) {
        int n = 1000;
        while ((n / Math.log(n)) < N) {
            n++;
        }//在整数X范围内有X/log(X)个素数,
        //逆推第N个素数的范围
        int[] arr = new int[n];
        int x = 2;
        while (x < n) {
            if (arr[x] != 0) {
                x++;
                continue;
            }
            int k = 2;
            while (x * k < n) {
                arr[x * k] = -1;
                k++;
            }
            x++;
        }
        int res = 0;
        for (int i = 2; i < arr.length; i++) {

            if (arr[i] != -1) {
                res++;
            }
            if (res == N) {
                return i;
            }
        }
        return -1;
    }
}
