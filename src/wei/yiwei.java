package wei;

import java.util.Random;

/**
 * 题解，将1-1000这1000个数放在含有1001个元素的数组中， 只有唯一一个元素重复，
 * A^A^B^B^C^C^C=C前面的都抵消了，消除重复，
 * 不用辅助空间，每个元素只能访问一次，如果没说数组内容就，无法算出1-1000的异或值，
 * 不能用异或只能辅助数组 A^A=0 B^0=B
 * 给补成2个消除重复 把偶数个变成奇数个，1-1000的异或值算出来再异或数组，k有3个，2个自动消掉，剩下k
 */

public class yiwei {
    public static void main(String[] args) {
        int N = 11;
        int[] arr = new int[N];
        for (int i = 0; i <= N - 1; i++) {
            arr[i] = i + 1;
        }
        arr[arr.length - 1] = new Random().nextInt(N - 1) + 1;// 放随机数到最后，
        for (int value : arr)
            System.out.print(value + "->");//遍历原数组
        System.out.println();
        int x = 0;
        for (int i = 1; i <= N - 1; i++) {
            x = x ^ i;
        }//算出1-1000的异或值
        for (int i = 0; i < N; i++) {
            x = x ^ arr[i];
        }//最后剩的就是唯一值
        System.out.println(x);
        System.out.println("------");
        fuzhu(1000, arr);
    }

    /**
     * 使用辅助数组，数组下标即为元素数量， 再遍历一遍辅助数组找到数量为2的就是，唯一重复元素 数组元素的值作为辅助数组的下标
     * 哈希函数，key值直接对应索引下标转换，无需转换
     *
     * @param x
     * @param arr
     */
    public static void fuzhu(int x, int[] arr) {
        int[] help = new int[x];
        for (int i = 0; i < x; i++) {
            help[arr[i]]++;
        }
        for (int i = 0; i < x; i++) {
            if (help[i] == 2) {
                System.out.println(i);
                break;
            }
        }
    }
}