import java.util.Arrays;

/**
 * LeetCode 785. 判断二分图
 * 给定一个无向图graph，当这个图为二分图时返回true。
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 * <p>
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * 注意:
 * <p>
 * graph 的长度范围为 [1, 100]。
 * graph[i] 中的元素的范围为 [0, graph.length - 1]。
 * graph[i] 不会包含 i 或者有重复的值。
 * 图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 * 非常重要!!!
 */
public class Bipartite {
    public static int[][] graph;
    public static boolean[] visited;
    public static int[] colors;

    public static void main(String[] args) {
        int[][] g =
                {{0, 1, 1, 0, 0, 0, 0},
                        {1, 0, 0, 1, 1, 0, 0},
                        {1, 0, 0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0, 0, 0}};
        System.out.println(isBipartite(g));
        System.out.println(Arrays.toString(colors));
    }

    /**
     * @param graph 注意这里不是邻接矩阵,而是邻接表!!!
     * @return 是否是二分图
     */
    public static boolean isBipartite(int[][] graph) {
        int V = graph.length;
        colors = new int[V];
        Arrays.fill(colors, -1);
        visited = new boolean[V];
        for (int v = 0; v < V; v++) {
            if (!visited[v])
                if (!dfs(v, 0)) {
                    return false;
                }
        }
        return true;
    }

    private static boolean dfs(int v, int color) {
        colors[v] = color;
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color))
                    return false;
                //原来的颜色是1,传进去的就是0就是(1-1),原来颜色是0,那么穿进去的颜色就是1,就是(1-0)
            } else if (colors[v] == colors[w]) {//如果w这个顶点已经被访问过了,就说明w有颜色,
                //如果相连的两个顶点颜色相同,那么就不是一个二分图
                return false;
            }
        }
        return true;
    }
}
