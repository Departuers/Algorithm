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

    static int exgcd(int a, int b) {
        if (b == 0) {
            x= 1;
            y = 0;
            return a;
        }
        int ed = exgcd(b, a % b);
        int c = y;
        y = x - (a / b) * y;
        x = c;
        return ed;
    }

    static int x, y;

}
