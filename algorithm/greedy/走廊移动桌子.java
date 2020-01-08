package greedy;

import java.util.Scanner;

/**
 * https://www.cnblogs.com/yxh-amysear/p/8393920.html
 * For each room, at most one table will be either moved in or moved out. Now, the manager seeks out a method to minimize the time to move all the tables. Your job is to write a program to solve the manager’s problem.
 * Input
 * The input consists of T test cases. The number of test cases ) (T is given in the first line of the input. Each test case begins with a line containing an integer N , 1<=N<=200 , that represents the number of tables to move. Each of the following N lines contains two positive integers s and t, representing that a table is to move from room number s to room number t (each room number appears at most once in the N lines). From the N+3-rd line, the remaining test cases are listed in the same manner as above.
 * Output
 * The output should contain the minimum time in minutes to complete the moving, one per line.
 * Sample Input 第一个表示n个测试用例,我只做一个
 * 3
 * 4
 * 10 20 30 40 50 60 70 80
 * 2
 * 1 3 2 200
 * 3
 * 10 100 20 80 30 50
 * Sample Output
 * 10
 * 20
 * 30
 * 题意：在一个狭窄的走廊里将桌子从一个房间移动到另一个房间，走廊的宽度只能允许一个桌子通过。给出t，
 * 表示有t组测试数据。再给出n，表示要移动n个桌子。n下面有n行，每行两个数字，表示将桌子从a房间
 * 移到b房间。走廊的分布图如一图所示，每移动一个桌子到达目的地房间需要花10分钟，问移动n个桌子
 * 所需要的时间。
 * 解题思路：若移动多个桌子时，所需要经过的走廊没有重合处，即可以同时移动。若有一段走廊有m个桌子都
 * 要经过，一次只能经过一个桌子，则需要m*10的时间移动桌子。设一个数组，下标值即为房间号。
 * 桌子经过房间时，该房间号为下标对应的数组值即加10。最后找到最大的数组值，即为移动完桌子
 * 需要的最短时间。
 */
public class 走廊移动桌子 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();//几张桌子
        int ans = 0;
        int map[] = new int[210];
        for (int i = 0; i < count; i++) {

            int a = sc.nextInt();//从c起始移动
            int b = sc.nextInt();//移动到b房间
            if (a > b) {
                swap(map, a, b);
            }
            for (int j = (a + 1) / 2; j <= (b + 1) / 2; j++) {//找出移动桌子占用部分的走廊,
                // (a+1)/2如果a是奇数,需要(a+1)/2,,如果a是偶数(a+1)/2也不会影响结果
                map[j]++;
            }
            /**
             * room1  room3   room5  ... root397   room399     //都是奇数
             *                   中间是走廊
             * room2  room4   room6  ... room398   root400     //都是偶数     b+1/2
             */
            for (int j = 0; j < 201; j++) {
                ans = Math.max(ans, map[j]);//找出最大冲突的移动桌子
            }
        }
        System.out.println(ans * 10);
    }

    public static void swap(int[] a, int b, int c) {
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }
}
