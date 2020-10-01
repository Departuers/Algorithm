package basic.sort;

/**
 * 快排,思想基于分治
 * 第一步,确定一个分界点 q[l] q[(l+r)/2] q[r] 随机
 * 第二步:调整区间,使得分界点左半部分都小于等于它
 * 分界点右边部分都大于等于它
 * 第三步:递归的处理左右两个部分
 * 只要左边这个大区间排好序了,右边这个区间排好序了,就都排好序了
 * 废弃跑不通
 */
public class quickSort {
    public static void main(String[] args) {
        q[0] = 5;
        q[1] = 4;
        quick(1, 5);
        System.out.println(q[0]);
        System.out.println(q[1]);

    }

    static int[] q = new int[1000005];
    static int t;

    static void quick(int l, int r) {
        if (l == r) return;
        int x = q[(l + r + 1) / 2], i = l - 1, j = r + 1;
        while (i < j) {
            do {
                i++;
            } while (q[i] < x);
            do {
                j++;
            } while (q[j] > x);
            if (i < j) {
                t = q[i];
                q[i] = q[j];
                q[j] = t;
            }
        }
        quick(l, i - 1);
        quick(i, r);
    }
}
