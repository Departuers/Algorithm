package Math;

import java.util.ArrayList;
import java.util.List;

public class mathtest {
    public static void main(String[] args) {
        //ex(19);
        int[] arr = {2, 3, 2};
        boolean nim = Nim(arr);
        System.out.println(nim);

    }

    /**
     * 1.巧用进制
     * <p>
     * 天平称重，我们希望用尽可能少的砝码称出尽可能多的重量
     * 如果有无限个砝码，但他们的重量分别是1,3，9,27，81等3的指数幂，
     * 神奇之处在于它们的组合可以称出任意的重量（砝码允许放在左右两边的盘中）
     * <p>
     * 重量<1000000
     * 例如
     * 用户输入
     * 5
     * 程序输出
     * 9-3-1
     * <p>
     * 思路:题目指出是3的指数次幂，可以将用户输入的数字转换成3进制
     * 比如，19转换成三进制201，1代表选，0代表不选，2代表需要进位，201转换成1-101
     * 1*3^3-1*3^2-1=27-9+1=19
     * <p>
     * 同理如果是2的指数次幂，转换成2进制，1代表选，0代表不选，
     *
     * @param n
     * @return
     */
    public static String ex(int n) {
        final String s = Integer.toString(n, 3);
        char[] arr = new StringBuilder(s).reverse().toString().toCharArray();
        System.out.println(arr);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '2') {
                list.add(0, -1);//-1插在开头
                if (i == arr.length - 1) {
                    list.add(0, 1);//最后一个字符进位
                } else
                    ++arr[i + 1];
            } else if (arr[i] == '3') {
                list.add(0, 0);
                if (i == arr.length - 1) {
                    list.add(0, 1);
                } else {
                    ++arr[i + 1];
                }
            } else {
                list.add(0, arr[i] - '0');
            }
        }
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) == 1)
                sb.append("+").append((int) (Math.pow(3, list.size() - j - 1)));
            if (list.get(j) == -1)
                sb.append("-").append((int) (Math.pow(3, list.size() - j - 1)));
        }
        System.out.println(sb.substring(1));
        return sb.substring(1);
    }

    public static boolean Nim(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
        }
        return res != 0;
    }
}
