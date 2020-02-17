package 进制转换;

public class 进制转换 {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(123));
        System.out.println(change(123,2));
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
}
