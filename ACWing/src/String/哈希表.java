package String;

/**
 * Hash Table
 * 存储[213,12,412,3,12,3,12,3,12,3123]用数组存查询会O(n)
 * 也可以排序O(n log n),使用二分O(log n)
 * 查询数字存在与否,用[1...3133]这么大的空间存数字是否出现,查找时间O(1) 空间太大
 * 所以使用hash table
 * 建立hash函数 h(key)= key%23
 * 只用A[0..22]数组快速查询,但可能会哈希冲突,
 * 不同key使用h(key)算出来的值是可能一样的
 * 对A[0...22]每个数组开个链表,到链表去查询,
 * 也可以再来个hash函数再算得到新的位置
 */
public class 哈希表 {
    static class node {
        int next, info;

        public node(int next, int info) {
            this.next = next;
            this.info = info;
        }
    }

    public static void main(String[] args) {
        add(99123, 23);
        add(99123, 22);

        System.out.println(find(99123,23));
    }

    static void add(int key, int info) {
        int u = key % M;
        nodes[tot] = new node(h[u], info);
        h[u] = tot++;
    }

    static int find(int key, int info) {
        int u = key % M;
        for (int i = h[u]; i != 0; i = nodes[i].next) {
            if (nodes[i].info == info) {
                return 1;
            }
        }
        return 0;
    }

    static int N = 123, tot = 1, M = 23;
    static int[] h = new int[N];
    static node[] nodes = new node[N];

}
