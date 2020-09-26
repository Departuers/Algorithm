package graph.欧拉路径;

import java.util.Scanner;

/**
 * https://blog.csdn.net/linwenqidbk/article/details/103546246
 * 随着白天越来越短夜晚越来越长，我们不得不考虑铲雪问题了。
 * 整个城市所有的道路都是双向车道,道路的两个方向均需要铲雪。
 * 因为城市预算的削减，整个城市只有 1 辆铲雪车。
 * 铲雪车只能把它开过的地方（车道）的雪铲干净，无论哪儿有雪，
 * 铲雪车都得从停放的地方出发，游历整个城市的街道。
 * 现在的问题是：最少要花多少时间去铲掉所有道路上的雪呢？
 * 输入格式
 * 输入数据的第 1 行表示铲雪车的停放坐标 (x,y)，x,y 为整数，单位为米。
 * 下面最多有4000行，每行给出了一条街道的起点坐标和终点坐标，坐标均为整数，
 * 所有街道都是笔直的，且都是双向车道。
 * 铲雪车可以在任意交叉口、或任何街道的末尾任意转向，包括转 U 型弯。
 * 铲雪车铲雪时前进速度为
 * 20 千米/时，
 * 不铲雪时前进速度为
 * 50 千米/时。
 * 保证：铲雪车从起点一定可以到达任何街道。
 * 输出格式
 * 输出铲掉所有街道上的雪并且返回出发点的最短时间，精确到分钟，四舍五入到整数。
 * 输出格式为”hours:minutes”，minutes不足两位数时需要补前导零。
 * 具体格式参照样例。
 * 数据范围
 * −106106≤ x,y ≤ 106
 * 所有位置坐标绝对值不超过 106
 * 输入样例：
 * 0 0
 * 0 0 10000 10000
 * 5000 -10000 5000 10000
 * 5000 10000 10000 10000
 * 输出样例：
 * 3:55
 * 每个双向街道,看成有向图,两条单向边
 * 这张图是所有入度等于出度的,图连通
 * 存在欧拉回路
 * 直接算时间就彳亍了
 * 所有边长度的2倍/20km/h
 */
public class 铲雪车 {
    public static void main(String[] args) {
        double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        Scanner sc = new Scanner(System.in);
        x1 = sc.nextDouble();
        y1 = sc.nextDouble();
        double sum = 0;
        while (sc.hasNext()) {
            x1 = sc.nextDouble();
            y1 = sc.nextDouble();
            x2 = sc.nextDouble();
            y2 = sc.nextDouble();
            double dx = x1 - x2;
            double dy = y1 - y2;
            sum += Math.sqrt(dx * dx + dy * dy) * 2;
        }
        int minutes = (int) Math.round(sum / 1000 / 20 * 60);
        int hours = minutes / 60;
        minutes %= 60;
        System.out.println(hours + ":" + minutes);
    }
}