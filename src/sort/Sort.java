package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Sort {
    public static void main(String[] args) {

//        long MAX = 59084709587505l;
//        int count = 0;
//        for (long i = 0; Math.pow(3, i) < MAX; ++i)
//            for (long j = 0; Math.pow(5, j) < MAX; ++j)
//                for (long k = 0; Math.pow(7, k) < MAX; ++k)
//                    if (Math.pow(3, i) * Math.pow(5, j) * Math.pow(7, k) < MAX)
//                        count++;
//        System.out.println(count);


        for (int j = 0; j < 100; j++) {
            for (int i = 1; i < 20; i++) {
                int[] data = random(20, 200);
                countSort(data);
                if (data[i] < data[i - 1])
                    System.out.println("错误");
            }
        }
    }

    public static int[] random(int size, int f) {
        Random r = new Random();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = r.nextInt(f);
        }
        return res;
    }


    /**
     * 希尔排序
     * [1, 1241, 123, 1, 31, 4121, 515, 632]
     * [1, 1, 31, 632, 123, 1241, 515, 4121]
     * [1, 1, 31, 123, 515, 632, 1241, 4121]
     *
     * @param arr
     */
    public static void XierSort(int[] arr) {

        for (int inter = arr.length / 2; inter > 0; inter /= 2) { //不断地缩小增量,初始增量为数组长度的一半,控制增量每次增量为上次的一半
            for (int i = inter; i < arr.length; i++) {//初始化i值为增量开始，到数组结束，从数组后面往前找
                int target = arr[i];
                int j = i - inter;                      //i-增量就是寻找满足增量的一组数据
                while (j > -1 && target < arr[j]) {
                    arr[j + inter] = arr[j];
                    j -= inter;
                }
                arr[j + inter] = target;
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 冒泡排序法,外层循环i控制轮数，如果有N个元素一共就要进行N-1轮比较
     * 每轮比较出剩余最大的元素，并交换到最后，
     * 外层循环i控制轮数，并间接控制内层循环边界，每轮的边界就是num.length - i
     * O(n²)
     *
     * @param arr
     * @return
     */
    public static int[] BubbleSort(int[] arr) {
        int[] ints = Arrays.copyOf(arr, arr.length);
        int count = 0;
        for (int i = 1; i < ints.length; i++) {
            for (int j = 0; j < ints.length - i; j++) {
                if (ints[j] > ints[j + 1]) {
                    int temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                    count++;
                }
            }
        }
        System.out.println(count);
        return ints;
    }

    /**
     * 选择排序
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 重复第二步，直到所有元素均排序完毕。
     * <p>
     * 每轮找出最小元素，放在数组前面，外层循环i控制边界，代表已经有序了的元素
     * 外层i还代表最小元素已经交换到哪个位置了
     * 内层循环，往后比较，找出剩余最小元素的索引，在外层循环交换元素
     * O(n²)
     *
     * @param arr
     * @return
     */
    public static int[] selectSort(int[] arr) {
        int[] nums = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int temp = nums[i];
                nums[i] = nums[min];
                nums[min] = temp;
            }
        }
        return nums;
    }

    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {
        int[] nums = Arrays.copyOf(arr, arr.length);
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i;
            while (j > 0 && temp < nums[j - 1]) {
                nums[j] = nums[j - 1];
                j--;
            }
            if (j != i) {
                nums[j] = temp;
            }
        }
        return nums;
    }

    /**
     * 快速排序法，单边扫描法
     *
     * @param arr 要排序的数组
     * @param l   左边界索引
     * @param r   右边界索引
     */
    public static void quickSortByOne(int[] arr, int l, int r) {
        if (l < r) {
            int q = part(arr, l, r);
            quickSortByOne(arr, l, q - 1);
            quickSortByOne(arr, q + 1, r);
        }
    }

    public static int part(int[] arr, int l, int r) {
        int pivot = arr[l];
        int search = l + 1;
        int big = r;
        while (search <= big) {
            if (arr[search] <= pivot)
                search++;
            else {
                swap(arr, search, big);
                big--;
            }
        }
        swap(arr, l, big);
        //最后结束时，big指向最后一个小于等于基准元素的
        //search指向第一个大于等于基准元素的
        return big;
    }

    //交换2个元素
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 快速排序法，双向扫描分区法
     *
     * @param arr
     * @param l
     * @param r
     */
    public static void quickSortByTwo(int[] arr, int l, int r) {
        if (l < r) {
            int f = doubleParter(arr, l, r);
            quickSortByTwo(arr, l, f - 1);
            quickSortByTwo(arr, f + 1, r);
        }
    }

    public static int doublePart(int[] arr, int l, int r) {
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

    //快速排序法，三点中值优化
    public static int doubleParter(int[] arr, int l, int r) {
        int midIndex = l + ((r - l) >> 1);
        int midValueIndex = -1;//中值的下标
        if (arr[l] <= arr[midIndex] && arr[l] >= arr[r]) {
            midValueIndex = l;
        } else if (arr[r] <= arr[midIndex] && arr[r] >= arr[l]) {
            midValueIndex = r;
        } else {
            midValueIndex = midIndex;
        }
        swap(arr, l, midValueIndex);
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

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = l + ((r - l) >> 1);
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr, mid, l, r);
        }
    }

    public static int[] help;

    public static void merge(int[] arr, int mid, int l, int r) {
        help = Arrays.copyOf(arr, arr.length);
        int left = l;//左侧队伍的头部指针，指向待比较的元素
        int right = mid + 1;//右侧队伍的头部指针，指向待比较的元素
        int cur = l;//原数组的指针，指向待填入数据的位置

        while (left <= mid && right <= r) {
            if (help[left] <= help[right]) {
                arr[cur] = help[left];
                cur++;
                left++;
            } else {
                arr[cur] = help[right];
                cur++;
                right++;
            }
        }
        while (left <= mid) {
            arr[cur] = help[left];
            cur++;
            left++;
        }
    }

    /**
     * 计数排序
     * 数组范围很大，会导致辅助空间很大，很稀疏，造成空间浪费,不能有负数
     * 范围小的，重复元素多是最快的
     * <p>
     * O(2N+K)K是数组中的最大值
     *
     * @param arr
     * @return
     */
    public static void countSort(int[] arr) {
        int max = -1;
        for (int i : arr) {
            if (i > max)
                max = i;
        }
        int[] help = new int[max + 1];
        for (int i : arr) {
            help[i]++;
        }
        int k = 0;
        for (int i = 0; i < help.length; i++) {
            while (help[i] > 0) {
                arr[k++] = i;
                help[i]--;
            }
        }
    }

    public static ArrayList<Integer>[] radix;

    /**
     * 桶排序
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {
        radix = new ArrayList[10];

    }
}