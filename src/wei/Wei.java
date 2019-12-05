package wei;

public class Wei {
    public static void main(String[] args) {
//        System.out.println(isj(11));
//
//        int[] ar = {12, 31, 14};
//        changeb(ar, 1, 2);
//        for (int i = 0; i < ar.length; i++) {
//            System.out.println(ar[i]);
//        }
//
//        System.out.println(qu(6));
//        System.out.println(getTwo(0.625));
        int[] arr = {11, 11, 11, 12, 534, 534, 123, 123, 123, 534};
        System.out.println(getK(arr, 3));


    }

    //1.判断是不是偶数
    public static boolean isj(int ele) {
        return (ele & 1) == 0;
    }

    //3.交换2个整数的值
    public static void change(int[] arr, int a, int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[b] ^ arr[a];
    }

    //3.交换2个整数的值
    public static void changeb(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    //6.将整数的奇偶位互换，比如1001换成0110
    public static int qu(int K) {
        int ji = K & 0X55555555;    // 01010101 求奇数位
        //32位可以用16进制数字表示
        int os = K & 0Xaaaaaaaa;   //10101010  求偶数位
        return (ji << 1) ^ (os >> 1);   //奇偶交换,奇数左移1位，偶数右移1位
    }

    /**
     * 7.二进制小数，给定一个小数，给出其二进制形式
     * (比如给出0.625，其二进制形式为0.0101)
     *
     * @param num
     * @return
     */
    public static String getTwo(double num) {
        StringBuilder sb = new StringBuilder();
        sb.append("0.");
        double r = 0;
        while (num > 0) {
            r = num * 2;
            if (r >= 1) {
                sb.append('1');
                num = r - 1;
            } else {
                sb.append('0');
                num = r;
            }
            if (sb.length() > 34) {
                System.out.println("error");
                return "Error";
            }
        }
        return sb.toString();
    }

    /**
     * 8.数组中有一个数出现了1次，其他数都出现了k次，
     * 请输出只出现一次的数字...
     * 2个2进制相同的数字， 做不进位加法结果为0
     * 10个10进制相同的数字，做不进位加法结果为0
     * K个K进制数字做不进位加法，结果为0
     *
     * 用于练习进制，实际上用map，每次添加key，value+1最后遍历Value最简单
     *
     * @param arr
     * @param k
     * @return
     */
    public static int getK(int[] arr, int k) {
        int len = arr.length;
        char[][] temp = new char[len][];
        int maxlen = 0;
        for (int i = 0; i < len; i++) {
            //求每个数字的k进制字符串并翻转，然后转为字符数组
            temp[i] = new StringBuilder(Integer.toString(arr[i], k)).reverse().toString().toCharArray();
            if (temp[i].length > maxlen) {
                maxlen = temp[i].length;
            }
        }
        //做不进位加法
        int[] resArr = new int[maxlen];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < maxlen; j++) {
                if (j >= temp[i].length)
                    resArr[j] += 0;
                else
                    resArr[j] += (temp[i][j] - '0');
            }
        }
        //转回10进制
        int res = 0;
        for (int i = 0; i < maxlen; i++) {
            res += (resArr[i] % k) * Math.pow(k, i);
        }
        return res;
    }
}