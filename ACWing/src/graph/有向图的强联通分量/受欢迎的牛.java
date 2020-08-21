package graph.有向图的强联通分量;

/**
 *
 */
@SuppressWarnings("all")
public class 受欢迎的牛 {
    public static void main(String[] args) {

    }

//    static void tarjar(int u) {
//        dfu[u] = low[u] = ++time;
//        stk[++top] = u;
//        in_stk[u] = true;
//        for (int i = he[u]; i != 0; i = ne[i]) {
//            int j=e[i];
//            if (!dfn[j]){
//                tarjar(j);
//                low[u]=Math.min(low[u],low[j]);
//            }else if (in_stk[j])
//                low[u]=Math.min(low[u],dfn[j]);
//        }
//        if (dfn[u]==low[u]){
//            int y;
//            ++scc_cnt;
//            do {
//                y=stk[top--];
//                in_stk[y]=false;
//                id[y]=scc_cnt;
//            }while (y!=u);
//        }
//    }
}
