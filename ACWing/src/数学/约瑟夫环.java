package 数学;

public class 约瑟夫环 {
    public static void main(String[] args) {
        jos(5, 3);
    }

    static int jos(int n, int k) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = (res + k) % i;
        }
        return res;
    }
}
