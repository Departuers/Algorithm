package greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 2.快速渡河
 * A group of N people wishes to go across a river with only one boat, which can at most carry two persons.
 * Therefore some sort of shuttle arrangement must be arranged in order to row the boat back and forth so that all people may cross.
 * Each person has a different rowing speed; the speed of a couple is determined by the speed of the slower one.
 * Your job is to determine a strategy that minimizes the time for these people to get across.
 * Input
 * The first line of the input contains a single integer T (1 <= T <= 20), the number of test cases.
 * Then T cases follow.
 * The first line of each case contains N, and the second line contains N integers giving the time for each people to cross the river.
 * Each case is preceded by a blank line. There won't be more than 1000 people and nobody takes more than 100 seconds to cross.
 * Output
 * For each test case, print a line containing the total number of seconds required for all the N people to cross the river.
 * Sample Input
 * 1     测试用例数量
 * 4     有几个人
 * 1 2 5 10   各自的重量,和速度成反比,重量越重越慢
 * 两个人渡河每次花的时间是最重的那个人的重量
 * 1,10->       10
 * <-1          1
 * 1,5->        5
 * <-1          1
 * 1,2->        2
 *          一共是19
 * 第一个思路(非最优),每次让划得最快(最轻)的人带重的人渡河
 * 最优思路:
 * 1,2->        2
 * <-1          1
 * 5,10->       5
 * <-2          2
 * 1,2->        2
 *          一共是17
 * 如何找到问题的贪心策略
 * 设a,b,c,d是4个人从小到大排列
 * a,b->
 * <-a
 * c,d->
 * <-b
 * a,b->
 * 则按照上述策略时间为:a+3b+d
 *
 * 第一种非最优思路//让最轻(速度最快的人)去带,重的人
 * a,d->
 * <-a
 * a,c->
 * <-a
 * a,b->
 * 则上述时间:2a+b+c+d
 * 则两者相消除:a+3b+d:2a+b+c+d
 * a+c?2b,取两者中最小的那个,作为最优策略
 * 假设c=b, 则a+c<2b
 * 如:1,2,2,3  1+2<2+2
 * 其他情况,看a+c?2b取最小
 *
 * Sample Output
 * 17
 *
 */
public class 快速渡河 {
    public static void main(String[] args) {
        K();
    }

    public static void K() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] speed = new int[n];
        for (int i = 0; i < n; i++) {
            speed[i]=sc.nextInt();
        }
        Arrays.sort(speed);
        f(n,speed);
    }

    public static void f(int n, int[] speed) {
        int left = n;
        int ans = 0;
        while (left > 0) {
            if (left == 1) {
                ans += speed[0];
                break;
            } else if (left == 2) {
                ans += speed[1];
                break;
            } else if (left == 3) {
                ans += speed[1] + speed[0] + speed[2];
                break;
            } else {
                //左侧作为渡河起点
                //1,2出发,1返回,最后两名出发,2返回
                //1,2河流左侧----最后两个重的河流右边,
                //送了两个人过去,1和2又回到了起点
                int s1 = speed[1] + speed[0] + speed[left - 1] + speed[1];
                int s2 = speed[left - 1] + speed[left - 2] + 2 * speed[0];
                //1,3出发,1返回,1,4出发,1返回,由速度快的(体重轻的)来带最重的
                //1河的左边----最后2个河的右侧,
                ans += Math.min(s1, s2);//取最快的
                left -= 2;//left代表还在河流左侧(起点)的剩余人数
            }
        }
        System.out.println(ans);
    }
}
