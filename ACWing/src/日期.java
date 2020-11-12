import java.util.Calendar;

//显然的日期
public class 日期 {
//    public static void main(String[] args) {
//        Calendar c = Calendar.getInstance();
//        c.set(2000, Calendar.JANUARY, 1);
//        int res = 0;
//        while (true) {
//
//            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY || c.get(Calendar.DAY_OF_MONTH) == 1) {
//                res += 2;
//            } else res += 1;//包含第一天
//
//            c.add(Calendar.DAY_OF_YEAR, 1);//增加一天
//            if (c.get(Calendar.YEAR) == 2020 && c.get(Calendar.MONTH) == Calendar.OCTOBER && c.get(Calendar.DATE) == 2)
//                break;
//        }
//        System.out.println(res);
//    }
static int cnt;

    public static void main(String[] args) {
        dfs(2019, 0);
        System.out.println(cnt);
    }

    static void dfs(int num, int start) {
        if (num < 0) return;
        if (num == 0) cnt++;
        else
            for (int i = start + 1, high = (int)Math.sqrt(num); i <= high; i++)
                dfs(num - i * i, i);
    }
}
