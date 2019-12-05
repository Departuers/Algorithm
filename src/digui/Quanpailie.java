package digui;

import java.util.ArrayList;

/**
 * 全排列
 */
public class Quanpailie {
    public static void main(String[] args) {

    }

    public static ArrayList<String> Quanpailie(String arr) {
        int n = arr.length();
        ArrayList<String> res = new ArrayList<>();
        res.add(arr.charAt(0) + "");//初始化，第一个字符
        for (int i = 1; i < n; i++) {
            ArrayList<String> temp = new ArrayList<>();
            char c = arr.charAt(i);
            for (String str : res) {
                String newTemp = c + str;//加在前面
                temp.add(newTemp);

                newTemp = str + c;
                temp.add(newTemp);//加在后面

                for (int j = 1; j < str.length(); j++) {
                    newTemp = str.substring(0, j) + c + str.substring(j);
                    temp.add(newTemp);//加在中间
                }

            }
            res = temp;
        }
        return res;
    }
}
