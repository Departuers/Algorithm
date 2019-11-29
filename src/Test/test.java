package Test;

public class test {
    public static void main(String[] args) {

        int i=6;//要分5次所以从6开始计算
        while(true)
        {
            if(i%5==1)//第一次分会多出一个
            {
                int x1=i-(i-1)/5;//第一次均分后 都会少一份被拿走
                x1=x1-1;//对了，不能忘记每次多出来的香蕉也被醒过来的猴子偷吃了
                if(x1%5==2)
                {
                    int x2=x1-(x1-2)/5;
                    x2=x2-2;
                    if(x2%5==3)
                    {
                        int x3=x2-(x2-3)/5;
                        x3=x3-3;
                        if(x3%5==4)
                        {
                            int x4=x3-(x3-4)/5;
                            x4=x4-4;
                            if(x4%5==0 && x4!=0)//要确保到最后一个猴子醒过来的时候还有香蕉！且正好可以被均分
                            {
                                System.out.println(i);
                                break;
                            }
                        }
                    }
                }
            }
            i++;
        }
    }
}
