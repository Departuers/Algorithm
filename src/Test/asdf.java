package Test;

public class asdf {
    public static void main(String[] args) {
        System.out.println(dpp(5));
    }

    public static int dpp(int n) {
        if (n == 1)
            return 1;
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 3; i <= n; i++) {

            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}
