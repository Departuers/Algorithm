package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class KruskalByEdge {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        m = s.nextInt();
        for (int i = 1; i < 1e5 + 7; i++) {
            p[i] = i;
        }
        for (int i = 0; i < m; i++) {
            q.add(new edge(s.nextInt(), s.nextInt(), s.nextInt()));
        }
        int res = 0;
        int cnt = 0;
        Collections.sort(q);
        for (int i = 0; i < q.size(); i++) {
            edge e = q.get(i);
            int a = find(e.x), b = find(e.y), c = e.z;
            if (a != b) {
                p[a] = b;
                res += c;
                cnt++;
            }
        }
        if (cnt != n - 1) System.out.println("INF");
        System.out.println(res);
    }

    static int find(int x) {
        if (x == p[x]) return x;
        return p[x] = find(p[x]);
    }

    static ArrayList<edge> q = new ArrayList<edge>();
    static int[] p = new int[(int) (1e5 + 10)];

    static class edge implements Comparable<edge> {
        int x, y, z;

        public edge(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(edge edge) {
            return z - edge.z;
        }
    }

    static int n, m;
}
