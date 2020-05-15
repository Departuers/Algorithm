package DFS.双向广搜;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104723891
 */
public class 子串变换 {
    public static void main(String[] args) {
        String A, B;
        Scanner sc = new Scanner(System.in);
        A = sc.next();
        B = sc.next();
        int n = 0;
        while (sc.hasNext()) {
            a[n] = sc.next();
            b[n] = sc.next();
            n++;
        }
        int step = bfs(A, B);
        if (step > 10) System.out.println("No");
        else {

        }
    }

    static ArrayDeque<String> qa = new ArrayDeque<String>(), qb = new ArrayDeque<String>();

    private static int bfs(String a, String b) {
        Map<String, Integer> da = new HashMap<String, Integer>();
        Map<String, Integer> db = new HashMap<String, Integer>();
        qa.add(a);
        qb.add(b);
        da.put(a, 0);
        db.put(b, 0);
        while (!qa.isEmpty() && !qb.isEmpty()) {
            int t = 0;
            if (qa.size() <= qb.size()) {
                t = extend(qa, da, db, a, b);
            } else t = extend(qb, da, db, b, a);
            if (t <= 10) return t;
        }
        return 11;
    }

    private static int extend(ArrayDeque<String> q, Map<String, Integer> da, Map<String, Integer> db, String a, String b) {
        String t = q.poll();
        for (int i = 0; i < t.length(); i++) {

        }
        return 0;
    }

    static int N = 6;
    static String[] a = new String[N], b = new String[N];
}
