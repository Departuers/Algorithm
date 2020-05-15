package DFS.启发式搜索;

import java.util.Scanner;

/**
 *
 */
public class 八数码 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String start, seq;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            s.append(sc.nextLine());
        }
        start = s.toString().replace(" ", "");
        System.out.println(start);
    }

}
