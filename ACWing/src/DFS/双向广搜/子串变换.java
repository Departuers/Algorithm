package DFS.双向广搜;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;

/**
 * https://blog.csdn.net/qq_30277239/article/details/104723891
 * 双向广搜,显然直接bfs会超时
 */
//@SuppressWarnings("all")
public class 子串变换 {
    public static void main(String[] args) {
        String A, B;
        Scanner sc = new Scanner(System.in);
        A = sc.next();
        B = sc.next();
        n = 0;
        while (sc.hasNext()) {
            aaa[n] = sc.next();
            bbb[n] = sc.next();
            n++;
        }
        int step = bfs(A, B);
        if (step > 10) System.out.println("No");
        else {
            System.out.println(step);
        }
    }

    static int n;
    static ArrayDeque<String> qa = new ArrayDeque<String>(), qb = new ArrayDeque<String>();


    private static int bfs(String a, String b) {
        HashMap<String, Integer> da = new HashMap<String, Integer>();
        HashMap<String, Integer> db = new HashMap<String, Integer>();
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

    private static int extend(ArrayDeque<String> q, HashMap<String, Integer> da, HashMap<String, Integer> db, String a, String b) {
        String t = q.poll();
        for (int i = 0; i < (t != null ? t.length() : 0); i++) {
            for (int j = 0; j < n; j++) {
                if (!t.substring(i, aaa[j].length()).equals(aaa[j])) continue;
                String u = t.substring(0, i) + bbb[j] + t.substring(i + bbb[j].length());
                if (db.containsKey(u)) return da.get(t) + 1 + db.get(u);
                if (da.containsKey(u)) continue;
                da.put(u, da.get(t) + 1);
//                if ()
            }
        }
        return -1;
    }

    static int e;
    static int N = 6;
    static String[] aaa = new String[N], bbb = new String[N];
}
