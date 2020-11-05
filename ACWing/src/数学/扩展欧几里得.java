package 数学;

public class 扩展欧几里得 {
    public static void main(String[] args) {
        
    }

    static class Int {
        int v;

        public Int(int v) {
            this.v = v;
        }
    }

    static int exgcd(int a, int b, Int x, Int y) {
        if (b == 0) {
            x.v = 1;
            y.v = 0;
            return a;
        }
        int ed = exgcd(b, a % b, y, x);
        int c = y.v;
        y.v = x.v - (a / b) * y.v;
        x.v = c;
        return ed;
    }

    static int x, y;

    static int gcd(int a, int b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }
        return gcd(b, a % b);
    }
}
