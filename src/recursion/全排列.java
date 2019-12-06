package recursion;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 全排列
 */
public class 全排列 {
    public static void main(String[] args) {
//        System.out.println(Quanpailie("abc"));
        System.out.println(getPermutation("abc"));
    }


    /**
     * @param arr 需要进行全排列的字符串
     * @return
     */
    public static ArrayList<String> Quanpailie(String arr) {
        int n = arr.length();
        ArrayList<String> res = new ArrayList<>();
        res.add(arr.charAt(0) + "");//初始化，第一个字符
        for (int i = 1; i < n; i++) {
            ArrayList<String> temp = new ArrayList<>();
            char c = arr.charAt(i);
            for (String str : res) {
                String newTemp = c + str;//加在前面
                temp.add(newTemp);

                newTemp = str + c;
                temp.add(newTemp);//加在后面

                for (int j = 1; j < str.length(); j++) {
                    newTemp = str.substring(0, j) + c + str.substring(j);
                    temp.add(newTemp);//加在中间
                }
            }
            res = temp;
        }
        return res;
    }

    public static ArrayList<String> res = new ArrayList<>();

    /**
     *
     * @param A     需要进行全排列的字符串
     * @return      返回一个ArrayList
     */
    public static ArrayList<String> getPermutation(String A) {
        char[] arr = A.toCharArray();
        Arrays.sort(arr);//从小到大排序
        getPermutationCore(arr, 0);
        return res;
    }

    public static void getPermutationCore(char[] arr, int k) {
        //排好了一种情况
        if (k == arr.length) {
            res.add(new String(arr));
        }

        for (int i = k; i < arr.length; i++) {
            swap(arr, k, i);
            getPermutationCore(arr, k + 1);
            swap(arr, k, i);//回溯
        }
    }

    public static void swap(char[] arr, int k, int p) {
        if (k==p)
            return;
        char temp = arr[k];
        arr[k] = arr[p];
        arr[p] = temp;
    }


}
