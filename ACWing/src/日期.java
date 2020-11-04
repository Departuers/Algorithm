import java.util.Calendar;

//显然的日期
public class 日期 {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(2000, Calendar.JANUARY, 1);
        int res = 0;
        while (true) {
            if (c.get(Calendar.YEAR) == 2020 && c.get(Calendar.MONTH) == Calendar.OCTOBER && c.get(Calendar.DATE) == 2)
                break;
            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY || c.get(Calendar.DAY_OF_MONTH) == 1) {
                res += 2;
            } else res += 1;
            c.add(Calendar.DAY_OF_YEAR, 1);
        }
        System.out.println(res);
        System.out.println(c.get(Calendar.DAY_OF_WEEK));
    }
}
