package greedy;

import java.util.Arrays;

/**
 * 有n个人，第i个人的重量为Wi，
 * 每艘船的最大载重量为C，
 * 且最多只能乘坐2个人，用最少的船装载所有人
 * <p>
 * 贪心策略：考虑最轻的人，如果每个人都无法和他一起坐船（重量超过C）
 * 则唯一的方案是每个人都坐一艘,否则他应该从能和他坐船的人中选择,
 * 其中最重的那个,
 */
public class 乘船 {
    public static void main(String[] args) {
        int[] w = new int[10];
        for (int i = 0; i < 10; i++) {
            w[i] = i + 1;
        }

        int n = w.length;
        int c = 10;
        Arrays.sort(w);

        int cntOfPerson = n;//剩余人数
        int cntOfBoot = 0;//船的数量
        int p1 = 0;
        int p2 = n - 1;

        while (cntOfPerson > 0) {
            if (w[p1] + w[p2] > c) {//如果第一个(p1)和最后一个(p2)不可以一起坐船走
                p2--;//右边标记左移
                cntOfPerson--;//剩余人数减少一个,因为最重的人只能单独坐
                cntOfBoot++; //船只数量+1
            } else {//如果p1可以和p2一起走,就让他们坐同一艘船
                p1++;//左边标记右移
                p2--;//右边标记左移
                cntOfPerson -= 2;//走了两个人
                cntOfBoot++;//船只数量+1
            }
        }
        System.out.println(cntOfBoot);
    }

}
