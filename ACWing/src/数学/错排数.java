package 数学;

public class 错排数 {
    public static void main(String[] args) {
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < 1000; i++) {
            f[i] = (i - 1) * (f[i - 1] + f[i - 2]);
        }
    }

    static int[] f = new int[101001];

}
