package com.shiyu.sort;

import java.util.Arrays;

public class lizi {
    public static void main(String[] args) {
        int[] data = {1, 1, 2, 2, 2, 2, 1, 3};
//        System.out.println(selectK(data, 0, data.length - 1, 7));
//        Arrays.sort(data);
//        System.out.println(Arrays.toString(data));
        //       System.out.println(shu(data));
        int[] c = {1, 2, 5};
        System.out.println(zuiii(c,0,c.length-1));


    }

    /**
     * 1.奇数在左
     * 给一个整数数组，调整数组数字的位置，
     * 使所有奇数放在数组前半部分，所有偶数位放在数组后半部分，
     * 要求时间复杂度为O(n)
     * 思路:2个指针一个指向奇数，一个指向偶数，从头开始，
     * 一个while循环，左边指针找到偶数，右边指针找到奇数就交换，
     * 如果找不到左边指针往右，右边指针往左,两个相交就交换完了
     *
     * @param arr
     */
    public static void jishuzaizuo(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            if (arr[left] % 2 == 0 && arr[right] % 2 == 1) {
                swap(arr, left, right);
                left++;
                right--;
            }
            if (arr[left] % 2 == 1) {
                left++;
            }
            if (arr[right] % 2 == 0) {
                right--;
            }
        }
    }

    public static void swap(int[] arr, int q, int p) {
        int temp = arr[q];
        arr[q] = arr[p];
        arr[p] = temp;
    }

    /**
     * 2.第K个元素
     * 以高效率求出一个乱序数组中，按数值顺序的第k个元素值
     * 思路:快排的分治思想能找到基准元素在有序情况下的下标，通过这个来缩小范围
     *
     * @param arr
     * @param l
     * @param r
     * @param k
     * @return
     */

    public static int selectK(int[] arr, int l, int r, int k) {
        if (arr.length < k || k <= 0)
            throw new IllegalArgumentException("out");

        int temp = part(arr, l, r);
        int qK = temp - l + 1;//期望值
        if (k == qK)
            return arr[temp];
        else if (k < qK) {
            return selectK(arr, l, temp - 1, k);
        } else {
            return selectK(arr, temp + 1, r, k - qK);
        }
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int l, int r) {
        int temp = part(arr, l, r);
        quickSort(arr, l, temp - 1);
        quickSort(arr, temp + 1, r);
    }

    private static int part(int[] arr, int l, int r) {
        int pivot = arr[l];
        int left = l + 1;
        int right = r;
        while (left <= right) {
            //left一直往右走，循环退出left一定指向第一个大于基准元素的位置
            while (left <= right && arr[left] <= pivot) {//要控制边界
                left++;
            }
            //right一直往左走，循环退出left一定指向第一个大于基准元素的位置
            while (left <= right && arr[right] > pivot) {//要控制边界
                right--;
            }
            if (left < right)//要控制边界
                swap(arr, left, right);
        }
        //最外层循环退出，right指向最后一个小于等于基准元素的位置，也是基准元素应该在的位置
        swap(arr, l, right);
        return right;
    }

    /**
     * 3. 超过一半的数
     * 数组中有一个数超过了数组长度的一半，找出这个元素
     * 思路1:排序出来，找arr.length/2，
     * 中间那个肯定是超过数组长度一半元素
     * 思路2:或者用上题找第K个元素，找第arr.length/2大的元素，需要改动数组的内容
     * 最佳思路3:消掉不同的数，将整个数组看做两类，
     * 比如8出现次数超过数组一半，非8这一类，跟8对消，消到最后剩的肯定是那个超过一半的数
     * <p>
     * 思路4:hash统计
     * 用超过一半的数，来消除其他数。
     *
     * @return
     */
    public static int cha(int[] arr) {
        int candidate = arr[0];
        int times = 1;
        for (int i = 1; i < arr.length; i++) {
            if (times == 0) {
                candidate = arr[i];
                times = 1;
                continue;
            }
            if (arr[i] == candidate) {
                times++;
            } else {
                times--;
            }
        }
        return candidate;
    }

    /**
     * 4.发帖水王加强版
     * 数组有一个数出现了数组长度的一半，找出这个数
     * 思路:长度的一半说明数组长度为偶数。一般每隔一个数就是水王，那么两两不同相消除
     * 最后会消成0，那么水王可能是最后一个元素，也可能是第一个元素。每次扫描多一个动作
     * 和最后一个数作比较，单独计数。如果不是最后一个元素。那么水王就是留下的那个candidate。
     *
     * @param arr
     * @return
     */
    public static int shu(int[] arr) {
        int candidate = arr[0];
        int nTimes = 0;
        int countOfLast = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            if (arr[i] == arr[N - 1])
                countOfLast++;//记录每个数与最后一个数相同的个数
            if (nTimes == 0) {
                candidate = arr[i];
                nTimes = 1;
                continue;
            }
            if (arr[i] == candidate) {
                nTimes++;
            } else {
                nTimes--;
            }
        }
        //如果最后一个元素出现次数是数组长度的一半
        if (countOfLast == N / 2)
            return arr[N - 1];
        else
            return candidate;
    }

    /**
     * 最小可用ID
     * 在非负数组中(乱序)找到最小可分配的ID，从1开始编号，数据量100万
     * 比如，1,2,3,5,6,7最小可用id就是4。
     * 如1,2,3,4,5最小可用ID就是6
     * <p>
     * 思路1:找一个辅助数组，找到最小id,浪费空间。出现大于数组长度值跳过。
     * 这个最垃圾，数据一大就暴毙
     *
     * @return
     */
    public static int zui(int[] arr) {
        int n = arr.length;
        int[] help = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (arr[i] <= n)//出现大于数组长度值跳过!!!
                help[arr[i]] = 1;
        }
        for (int i = 1; i <= n; i++) {
            if (help[i] == 0)
                return i;
        }
        return n + 1;
    }

    /**
     * 最小可用ID,
     * 思路2:先排序,再找，从1开始，所以对应下标，初始i+1=1对应arr[0]是1
     * O(nlogn)也很快，次优，但1000万级别数会超过1秒
     *
     * @param arr
     * @return
     */
    public static int zuii(int[] arr) {
        Arrays.sort(arr);//先排序
        int i = 0;
        while (i < arr.length) {
            if (i + 1 != arr[i]) {//因为从1开始，所以对应下标，初始i+1=1对应arr[0]是1
                return i + 1;//
            }
        }
        return i + 1;
    }

    /**
     * 最小可用ID,
     * 最优思路3：但挺复杂。会改变原数组元素
     * 最佳思路，用快排，分治思想，求出中间元素。查看对应值
     * 1   2   40  60  61        假设数组有序，
     * 0   1   2   3   4
     * 找到索引2，本该对应3，却为4，说明左边稀疏，最小id在左边。
     * 如果左侧稀疏。即使2的索引值为40，也不关键。它最小可用ID是1-3，因为有序。
     * 找到索引2，如果对应了3，说明左侧都是稠密的，最小id在右边。
     * 边界出口挺难推,能找到数组中，最后一个值对应数组下标的元素。l和r都会指向这个元素
     * 返回l左边界+1就行了
     *
     * @param arr
     * @return
     */
    public static int zuiii(int[] arr, int l, int r) {
        if (l > r)
            return l + 1;
        int mid = l + ((r - l) >> 1);
        int t = mid + 1;//期望得到这个值
        int q = selectK(arr, l, r, mid - l + 1);
        if (q == t) {//右侧稀疏
            return zuiii(arr, mid + 1, r);
        } else {//左侧稀疏
            return zuiii(arr, l, mid - 1);
        }
    }

    /**
     * 6.合并有序数组:
     * 给定两个排序后的数组A数组B,其中A的末端有足够的缓冲空间容纳B
     * 编写一个方法，将B合并到A中去并排序。
     * 思路:归并的思维，两个指针指向数值的最后一个。对比这两个指针，往数组末端放
     */
    public static void hebing() {

    }

}