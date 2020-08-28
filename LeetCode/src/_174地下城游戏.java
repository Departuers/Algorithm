import java.util.Arrays;

/**
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由M x N 个房间组成的二维网格。
 * 我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；
 * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 * -2 (K)	-3	3
 * -5	-10	1
 * 10	30	-5 (P)
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dungeon-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * f[i,j]表示从[i,j]走到[n,n]这个位置的最小花费,
 * 从终点走向起点
 * 属性min
 * 状态划分:f[i+1,j] 上面走过来
 * f[i,j+1] 左面走过来
 * 状态计算:f[i,j]=max( min(f[i+1,j],f[i,j+1]) - dungeon[i][j] , 1)
 * https://leetcode-cn.com/problems/dungeon-game/solution/cong-hui-su-dao-ji-yi-hua-sou-suo-dao-dong-tai-gui/
 */
public class _174地下城游戏 {
    public static void main(String[] args) {

    }

    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int[][] f = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(f[i], 1 << 30);
        }
        f[n][n - 1] = f[n - 1][n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int min = Math.min(f[i + 1][j], f[i][j + 1]);
                f[i][j] = Math.max(min - dungeon[i][j], 1);
            }
        }
        return f[0][0];
    }

    static int dfs(int row, int col, int[][] mem) {
        if (mem[row][col] != -1) {
            return mem[row][col];
        }
        int right = dfs(row, col + 1, mem);
        int down = dfs(row + 1, col, mem);
        int needMin=Math.min(right,down);
        return 1;
    }



}
