package 线段树;

import java.util.ArrayList;

/**
 * 积分思想
 * 分成几个小块扫描
 */
public class 亚特兰蒂斯扫描线 {
    public static void main(String[] args) {

    }

    static class seg implements Comparable<seg> {
        double x, y1, y2;
        int k;

        public seg(double x, double y1, double y2) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
        }

        @Override
        public int compareTo(seg seg) {
            return (int) (x * 1000000 - seg.x * 1000000);
        }
    }

    static int N = 100010;
    static seg[] seg = new seg[N * 2];

    static class node {
        int l, r;
        int cnt = 0;
        double len;

    }

    static node[] nodes = new node[N * 8];
    static ArrayList<Integer> ys = new ArrayList<Integer>();

}
