package DFS.剪枝;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.System.in;

/**
 * https://blog.csdn.net/qq_30277239/article/details/105739196
 * 乔治拿来一组等长的木棒，将它们随机地砍断，使得每一节木棍的长度都不超过50个长度单位。
 * 然后他又想把这些木棍恢复到为裁截前的状态，但忘记了初始时有多少木棒以及木棒的初始长度。
 * 请你设计一个程序，帮助乔治计算木棒的可能最小长度。
 * 每一节木棍的长度都用大于零的整数表示。
 * 输入格式
 * 输入包含多组数据，每组数据包括两行。
 * 第一行是一个不超过64的整数，表示砍断之后共有多少节木棍。
 * 第二行是截断以后，所得到的各节木棍的长度。
 * 在最后一组数据之后，是一个零。
 * 输出格式
 * 为每组数据，分别输出原始木棒的可能最小长度，每组数据占一行。
 * 数据范围
 * 数据保证每一节木棍的长度均不大于50。
 * 输入样例：
 * 9
 * 5 2 1 5 2 1 5 2 1
 * 4
 * 1 2 3 4
 * 0
 * 输出样例：
 * 6
 * 5
 * 分析：
 * 本题是将一些数进行分组，使得每个分组元素之和相等，并且分组数最大。
 * 分组数最大，每个分组的元素之和自然就最小。
 * <p>
 * 显然，每个分组的元素和应该是所有元素和的约数，所以可以从最大的元素枚举到元素总和，直至找到最小的分组和。
 * 搜索顺序,可以优先开始不优的点,会使得分支变少
 * <p>
 * 下面考虑dfs的设计。首先，分组和是len的时候，满足什么条件说明成功分组了？
 * 自然是出现了u个分组，并且u*len==sum了，
 * 为此，dfs的形参应该有个u表示已经分好的组数。
 * 另外，当前正在分的组里面已有的元素和res需要记录下来，
 * 当分组元素和达到len是就可以考虑下一个分组了。现在，dfs已经有两个形参了。
 * 如果就此进行搜索，步骤应该是：当len*u等于sum时，分组成功；当res==len时，
 * 进行下一个分组；否则，从还没有被选的元素中选出一个加入当前分组中。
 * <p>
 * 本题的重点在于剪枝。
 * 首先，优化搜索顺序，在小猫爬山问题中，我们是按照猫的重量从大到小去选取猫的，
 * 本题也是一样，先选取较大的元素有助于搜索分支数的减少。
 * 在最小分组问题中，按照从大到小的顺序去搜索也是一种经验了。
 * 然后，排除等效冗余：
 * 按照组合数进行枚举/
 * 1.比如说在排序后，第一次分组选取了4和1，搜索后发现没有可行解，
 * 然后在回溯过程中第一次分组先选了1，就没必要再在这个分组中选4了，
 * 因为abc组合不行，bac组合必然也是不行的。所以每次搜索，只需要搜当前分组选取的元素之后的元素了，
 * 为此，需要在dfs的形参中加上一个st，表示搜索开始的位置。
 * <p>
 * 2.如果当前元素加入了分组后得不到可行解，那么它后面与它相等的元素加入也无济于事。 显然
 * 比如说选取的顺序是4 4 2 1，在现在的分组中加上4，后面的搜索过程中必然有一种是不把第2个4加进分组的情况，
 * 如果这种情况得不到解，那么尝试不放第一个4而把第二个4放进分组的策略必然也是得不到解的。
 * <p>
 * 3.如果一个分组放进第一个元素就得不到解，那么必然得不到合适的分组。
 * 也就是说，第k个分组开始的时候，如果一个元素放进分组后搜索得不到可行解，
 * 那么把该元素放进后面的分组肯定也是得不到解的。这里可能会有疑问，比如说我分好了两个组，
 * 分第三组时放进一个3，然后找不到解，固然我是在还没分组的元素中无法得到可行解，
 * 但是如果把已经分好组的元素抽一个出来和3组合，说不定就能得到可行解了呢？
 * 假设从已经分好组的元素中抽出一个x使得当前分组凑齐了，为了补上缺口，
 * 就要从还没选取的元素中抽若干个元素凑成和是x的组合，既然后面的元素可以凑成和是x的组合，
 * 那么一开始从已分组中抽出x就没有意义了，不如直接用后面的元素去和3组合。这就是矛盾的。
 * <p>
 * 显然:如果一个分组放进第一个元素就得不到解，如果能放在其他位置可以得到最优解,
 * 但元素的顺序无关,所以是可以把那第一个元素放在第一个位置,就与开头矛盾
 * <p>
 * 4.如果加入一个元素后某个分组和等于len了，但是继续搜索失败了，
 * 则没有可行解。和3是等价的，如果当前分组最后一个元素加入得不到解，
 * 则这个元素作为后面其他组的最后一个元素也都是得不到解的。
 * 第3点和第4点本质上是说，如果已经凑齐了k组，
 * 剩下元素中不论怎么组合都凑不成和为len的下一组，则不管再怎么重新分组也都是无法分组成功的。
 * <p>
 * 倒数第二个可能剪掉最优解
 * <p>
 * 最后，只要搜索到解就停止的dfs问题，
 * 一般用bool类型作为返回值，因为这样，搜到最优解就返回true，
 * 用dfs的返回值作为条件，就可以瞬间退出递归了。比上一题中专门设置标志变量的方法要更好。
 */
public class 木棒 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            Arrays.fill(st, false);
            w = new Integer[N];
            n = sc.nextInt();
            sum = 0;
            if (n == 0) break;
            for (int i = 0; i < n; i++) {
                w[i] = sc.nextInt();
                sum += w[i];//求和,只用枚举所有的约数
            }
            Arrays.sort(w, 0, n, new Comparator<Integer>() {
                @Override
                public int compare(Integer t2, Integer t1) {
                    return t1 - t2;
                }
            });
            len = 1;
            while (true) {
                //只枚举约数
                if (sum % len == 0 && dfs(0, 0, 0)) {
                    //
                    System.out.println(len);
                    break;
                }
                len++;
            }
        }
    }

    /**
     * @param u     当前位置
     * @param s     当前长度
     * @param start 每次起始点
     * @return
     */
    static boolean dfs(int u, int s, int start) {
        if (u * len == sum) {//找到合法解
            return true;
        }
        if (s == len) return dfs(u + 1, 0, 0);

        //按照组合数枚举,不考虑顺序
        for (int i = start; i < n; i++) {
            if (st[i]) continue;
            if (s + w[i] > len) continue;//可行性剪枝,超长了

            st[i] = true;
            if (dfs(u, s + w[i], i + 1)) return true;
            st[i] = false;

            //如果是大棍的第一根失败了,
            if (s == 0) return false;
            //如果是最后一根木棍失败了
            if (s + w[i] == len) return false;

            //过滤相等的木棍
            int j = i;
            while (j < n && w[j].equals(w[i])) j++;
            i = j - 1;
        }
        return false;
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    static StringTokenizer tokenizer = new StringTokenizer("");

    static String nextLine() throws IOException {// 读取下一行字符串
        return reader.readLine();
    }

    static String next() throws IOException {// 读取下一个字符串
        while (!tokenizer.hasMoreTokens()) {
            //如果没有字符了,就是下一个,使用空格拆分,
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {// 读取下一个int型数值
        return Integer.parseInt(next());
    }

    static double nextDouble() throws IOException {// 读取下一个double型数值
        return Double.parseDouble(next());
    }

    static int n, len, sum, N = 70;
    static Integer[] w = new Integer[N];
    static boolean[] st = new boolean[N];

}
