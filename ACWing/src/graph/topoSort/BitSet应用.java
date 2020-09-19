package graph.topoSort;

import java.util.BitSet;
import java.util.Scanner;

/**
 * 问题重述：一个最多包含n个正整数的文件，每个数都小于n，其中n=107，并且没有重复。
 * 最多有1MB内存可用。要求用最快方式将它们排序并按升序输出。
 */
public class BitSet应用 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BitSet bitSet = new BitSet();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            bitSet.set(sc.nextInt(),true);
        }
        for (int i = 0; i < bitSet.size(); i++) {
            if (bitSet.get(i))
                System.out.println(i + " ");
        }
    }
}
