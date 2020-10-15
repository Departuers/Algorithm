package night;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 双指针
 * https://www.acwing.com/solution/content/5622/
 * 大概思路：
 * 假设某店铺拿到订单的时刻分别为t1, t2, t3, t4(假设已经按照非递减序排序)，问第t4时刻店铺的优先级(根据题目t4>=t1,t2,t3)
 * 解：
 * 总共需要计算2个部分:
 * 第一部分：1 ~ t1-1， t1+1 ~ t2-1， t2+1 ~ t3-1， t3+1 ~ t4-1， t4+1 ~ T，这几个部分是没有订单的时刻,需要减去这些个时刻
 * 第二部分：t1, t2, t3,t4 这几个时刻是接到接到订单的时刻，这几个时刻分别加2
 * 需要注意的是，如果同一时间有多个订单，假如上述的非递减序列中，t2 == t3，思路如下：
 * 第一部分：1 ~ t1-1， t1+1 ~ t2-1， t3+1 ~ t4-1， t4+1 ~ T，这几个部分是没有订单的时刻,需要减去这些个时刻
 * 第二部分：t1, t2, t4 这几个时刻是接到接到订单的时刻，这几个时刻分别加上该时刻订单数量cnt*2（cnt[t1] = 1, cnt[t2] = 2, cnt[t4] = 1）
 * 具体步骤：
 * 输入的mm个订单信息，排序（时间tt为第一优先级，订单idid为第二优先级）
 * forfor遍历订单信息（记得此时订单大体是按照时间顺序排的）。
 * 假设当前订单为第ii个，循环判断后面有没有相同的订单(即tt和idid相等)。（有的话一定连续）
 * 当到第jj个时订单不相同，此时相同订单的数量为cnt=j−icnt=j−i
 * 下一次forfor循环从jj处开始遍历
 * 记录此时的tt和idid, 计算idid的优先权，有两部分
 * 第一部分，是上一个拿到订单的时间last[id]last[id]和tt之间，中间没订单所以要−1−1，没订单的数量是t−last[i]−1t−last[i]−1 (比如第3和第6时刻都有订单，没有订单的时候就是4,5)
 * 计算优先权，如果为负值更新为0。如果小于等于3，更新优先缓存st[id]=falsest[id]=false
 * 第二部分，是此时，tt时刻拿到订单，并且拿到的数量为cntcnt，要加上2∗cnt2∗cnt
 * 计算优先权，如果大于5，更新优先缓存st[id]=truest[id]=true
 * 解释上面那个，因为此时这几个相同的订单都计算过了不需要再计算了，所以下一次循环要从jj开始
 * 循环最后，上一次拿到订单的时间last[id]last[id]更新为tt
 * 如果最后一个订单时刻为T，则没问题。如果不是T，那么最后一个拿到订单时刻到T时刻的这部分减法需要手动计算，即减去最后一个订单时刻与T的差值。换而言之，如果上一个拿到订单的时间last[id]小于TT，则优先权 减去 T−last[id]T−last[id]。注意这里不减1，因为T时刻也没订单。如果小于等于3，更新优先缓存

 */
public class 外卖店优先级 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        T = sc.nextInt();
        int time, id, cnt;
        for (int i = 0; i < m; i++) {
            time = sc.nextInt();
            id = sc.nextInt();
            orders[i] = new my(time, id);
        }
        Arrays.sort(orders, 0, m);
        for (int i = 0; i < m; ) {//不需要i++
            int j = i;
            while (j < m && orders[i].time == orders[j].time && orders[i].id == orders[j].id) {
                j++;//找到相同订单
            }
            id = orders[i].id;
            time = orders[i].time;
            cnt = j - i;//cnt表示相同的数量
            i = j;
            scores[id] -= time - last[id] - 1;//中间没有订单的数量
            if (scores[id] < 0) scores[id] = 0;
            if (scores[id] <= 3) st[id] = false;
            scores[id] += cnt * 2;
            if (scores[id] > 5) st[id] = true;
            last[id] = time;
        }
        //最后一个订单t时刻到结尾T那段距离，需要手动计算
        for (int i = 1; i <= n; i++) {
            if (last[i] < T) {
                scores[i] -= T - last[i];
                if (scores[i] <= 3) st[i] = false;
            }
        }
        //计算最终答案
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (st[i])
                res++;
        }
        System.out.println(res);
    }

    static int n, N = 100100, m, T;
    static int[] scores = new int[N]; //第i个店优先级
    static int[] last = new int[N]; // 第i个店上一个有订单的时刻t = last[id]
    static boolean[] st = new boolean[N];  //是否在优先队列
    static my[] orders = new my[N]; //结构体数组

    static class my implements Comparable<my> {
        int time, id;

        public my(int time, int id) {
            this.time = time;
            this.id = id;
        }

        @Override
        public int compareTo(my my) {
            if (my.time == time) {
                return id - my.id;
            }
            return time - my.time;
        }
    }
}
