public class _670最大交换 {
    public static void main(String[] args) {

    }

    //ac
    public static int maximumSwap(int num) {
        int res = num;
        StringBuilder s = new StringBuilder().append(num);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                s = new StringBuilder().append(num);
                char t = s.charAt(i);
                s.setCharAt(i, s.charAt(j));
                s.setCharAt(j, t);
                res = Math.max(res, Integer.parseInt(s.toString()));
            }
        }
        return res;
    }
}
