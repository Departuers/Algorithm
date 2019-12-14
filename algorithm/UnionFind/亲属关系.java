package UnionFind;

import java.util.Scanner;

/**
 * 某个家族过于庞大,判断两个人是不是亲戚,给出亲戚关系图,求判断两个人是不是亲戚
 * 如果x和y是亲戚,y和z是亲戚,那么x和z也是亲戚
 * 输入示例:
 * 三个整数n,p,m,代表有n个人,m个亲戚关系,询问多少对亲戚关系
 * 接下来m行,每行2个数m1和m2,表明m1和m2有亲戚关系
 * 接下来p行,每行2个数p1和p2,询问这2个人是否有亲戚关系
 * 亲戚(relation) YBT1346
 * 时间限制: 1000 ms 内存限制: 65536 KB
 * 【题目描述】
 * 或许你并不知道，你的某个朋友是你的亲戚。他可能是你的曾祖父的外公的女婿的外甥女的表姐的孙子。如果能得到完整的家谱，判断两个人是否是亲戚应该是可行的，但如果两个人的最近公共祖先与他们相隔好几代，使得家谱十分庞大，那么检验亲戚关系实非人力所能及。在这种情况下，最好的帮手就是计算机。为了将问题简化，你将得到一些亲戚关系的信息，如Marry和Tom是亲戚，Tom和Ben是亲戚，等等。从这些信息中，你可以推出Marry和Ben是亲戚。请写一个程序，对于我们的关于亲戚关系的提问，以最快的速度给出答案。
 *
 * 【输入】
 * 输入由两部分组成。
 *
 * 第一部分以N，M开始。N为问题涉及的人的个数(1<=N<=20000)。这些人的编号为1,2,3,…, N。下面有M行(1<=M<=1000000)，每行有两个数ai, bi，表示已知ai和bi是亲戚。
 *
 * 第二部分以Q开始。以下Q行有Q个询问(1<= Q <=1000000)，每行为ci, di，表示询问ci和di是否为亲戚。
 *
 * 【输出】
 * 对于每个询问ci, di，输出一行：若ci和di为亲戚，则输出“Yes”，否则输出“No”。
 *
 * 【输入样例】
 * 10 7
 * 2 4
 * 5 7
 * 1 3
 * 8 9
 * 1 2
 * 5 6
 * 2 3
 * 3
 * 3 4
 * 7 10
 * 8 9
 * 【输出样例】
 * Yes
 * No
 * Yes
 */
public class 亲属关系 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int p = sc.nextInt();
        UnionFind s = new UnionFind(n + 1);
        for (int i = 0; i < m; i++) {
            int m1 = sc.nextInt();
            int m2 = sc.nextInt();
            s.Union(m1, m2);
        }
        for (int i = 0; i < p; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();
            if (s.isCon(p1,p2))
                System.out.println("Yes");
            else
                System.out.println("No");
        }

    }
}
