package 进制转换;

public class 进制转换 {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(123));
        System.out.println(change(123, 2));
    }

    public static char[] arr = {'a', 'b'};

    /**
     * 给定一个i,转换成j进制,适用于正数
     *
     * @param i
     * @param j
     * @return
     */
    public static String change(int i, int j) {
        char[] pos = new char[100];
        int count = 0;
        while (i != 0) {
            pos[count++] = arr[i % j];
            i /= j;
        }
        StringBuilder sv = new StringBuilder();

        for (int l = count - 1; l >= 0; l--) {
            sv.append(pos[l]);
        }
        return sv.toString();
    }

    /**
     * 测试进制转换,
     */
    public static void Testchange() {
        for (int i = 1; i < 1000000; i++) {
            if (!change(i, 2).equals(Integer.toBinaryString(i))) {
                System.out.println("ooo");
                System.out.println(i);
            }
        }
    }

    /**
     * @param n 需要转的10进制数字
     */
    public static void Ten转16进制(int n) {
        char[] arr = new char[2576];
        int cur = n;
        int count = 0;
        while (cur != 0) {
            if (cur % 16 >= 10)
                arr[count++] = (char) (cur % 16 - 10 + 'A');
            else
                arr[count++] = (char) (cur % 16 + '0');
            cur /= 16;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = count - 1; i >= 0; i--) {
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }

    /**
     * @param n 需要转的16进制数
     */
    public static void Sixto10进制(String n) {
        long res = 0;
        int t = 0;
        int c = 0;
        for (int i = n.length() - 1; i >= 0; i--) {
            if (n.charAt(i) >= 'A') {
                t = n.charAt(i) - 'A' + 10;
            } else {
                t = n.charAt(i) - '0';
            }
            res += t * Math.pow(16, c++);
        }
        System.out.println(res);
    }
}
