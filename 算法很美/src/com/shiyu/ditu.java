package com.shiyu;

public class ditu {
    public static void main(String[] args) {
        get();
    }

    /**
     * 遍历每一个点
     */
    public static void get() {
        double[][] arr = { { 2, 1 }, { 2, 3.1 }, { 2, 3 }, { 2, 5 } };
        Double mindix = Double.MAX_VALUE;
        int minI = 11;
        int minJ = 11;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                double x1 = arr[i][0];
                double y1 = arr[j][0];
                double x2 = arr[i][1];
                double y2 = arr[j][1];
                double dis = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
                if (dis < mindix) {
                    mindix = dis;
                    minI = i;
                    minJ = j;
                }
            }
        }
        System.out.println(mindix + "i:==" + minI + "j==" + minJ);
    }
}