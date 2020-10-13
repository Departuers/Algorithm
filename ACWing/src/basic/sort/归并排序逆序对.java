package basic.sort;

public class 归并排序逆序对 {
    public static void main(String[] args) {
        q = new int[]{13, 6, 9, 11, 5};
        System.out.println();
        long f = merge(0, 4);
        for (int i = 0; i < 5; i++) {
            System.out.print(q[i] + " ");
        }
        System.out.println(f);
    }

    /**
     * 与归并排序类似，分而治之。
     * <p>
     * 第一步：将原问题递归的划分为两个子问题L和R；
     * <p>
     * 第二步：递归的求出子问题L和R中逆序对的个数；
     * <p>
     * 第三步：合并子问题的解得到总问题的解。
     * <p>
     * 唯一的问题在于子问题解的合并上，比如L上有x个逆序对，R上有y个逆序对，
     * 则LR上的逆序对并不是x与y简单的相加，还要加上两边的逆序对，
     * 比如L排好序后是1 3 5，R排好序后是2 4 6，L的排序消除了x个逆序对，R的排序消除了y个逆序对，
     * 此时原问题的状态变为1 3 5 2 4 6，可见还是有逆序对存在的，剩下的逆序对需要合并操作来消除。
     * 同样比较两个子问题指针指向元素的大小，1 < 2，1是最小的元素，
     * 不会存在逆序对，3 > 2,子问题L为1 3 5，既然3都大于2，则有序序列L 3右边的数也都必然大于2，
     * 所以L中大于2的数为L的右边界mid减去指向3的指针i加上1，即由2构成逆序对的数目。
     * 本题代码只需在归并排序代码上稍作修改即可得到答案，总的代码如下：
     *
     * @param l
     * @param r
     * @return
     */
    static long merge(int l, int r) {
        if (l == r) return 0;
        int mid = l + r >> 1;
        long res = merge(l, mid) + merge(mid + 1, r);
        int k = 0, i = l, j = mid + 1;
        while (i <= mid && j <= r) {
            if (q[i] <= q[j]) {
                tep[k++] = q[i++];
            } else {
                tep[k++] = q[j++];
                res += mid - i + 1;
            }
        }
        while (i <= mid) tep[k++] = q[i++];
        while (j <= r) tep[k++] = q[j++];
        for (i = l, j = 0; i <= r; i++, j++) {
            q[i] = tep[j];
        }
        return res;
    }

    static int N = 10000;
    static int[] q, tep = new int[N];

    class Solution {
        public int inversePairs(int[] nums) {
            return merge(0, nums.length - 1);
        }

        int[] temp = new int[9999999];

        int merge(int l, int r) {
            if (l == r) return 0;
            int mid = l + r >> 1;
            int res = merge(l, mid) + merge(mid + 1, r);
            int k = 0, i = l, j = mid + 1;
            while (i <= mid && j <= r) {
                if (q[i] <= q[j]) {
                    temp[k++] = q[i++];
                } else {
                    temp[k++] = q[j++];
                    res += mid - i + 1;
                }
            }
            while (i <= mid) temp[k++] = q[i++];
            while (j <= r) temp[k++] = q[j++];
            for (i = l, j = 0; i <= r; i++, j++) {
                q[i] = temp[j];
            }
            return res;
        }
    }
}
