package Test;

public class sdgf {
    public static void main(String[] args) {
        int n = 1000;
        int[] x = new int[1000];
        int sum = 1;
        for (int i = 0; sum < n; i++) {
            sum = i + sum;
            x[i] = sum;//sum自增
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(x[i]);
        }
        sum = 1;
        int k = 0;
        for (int i = 0; sum < n; i++) {
            sum = x[i] + sum;
            k++;
        }
        System.out.println(k);
    }
}