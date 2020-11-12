package night;


import java.util.ArrayList;
import java.util.List;

public class 序列求和 {

    public static void main(String[] args) {
        List list = new ArrayList();
        int sum = 0;
        //遍历循环60次，即S60
        for(int i = 1; i <= 60;i++) {
            //每次都进行爆破
            for(int j = 1; ; j++ ) {
                //在每次进入时清除list集合，下步使用
                list.clear();
                //寻找约数
                for(int k = 1; k <= j;k++) {
                    //判断约数
                    if(j%k==0) {
                        //如果是把约数加进list集合
                        list.add(k);
                    }

                    //判断因数个数是否等于i
                    if(list.size() == i) {
                        sum += j;
                        break;
                    }
                }
                //如果等于i即跳出循环
                if(list.size() == i) {
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    static int N = (int) 1e8;
    static int[] cnt = new int[N];
}