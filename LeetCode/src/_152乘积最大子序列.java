import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 */
public class _152乘积最大子序列 {
    public static void main(String[] args) throws ScriptException {
        dfs(0, 0, "");
        System.out.println(Calculation("1+3"));
    }


    static void dfs(int s, int state, String g) throws ScriptException {
        if (s == 9) {
            if (Calculation(g) == 1) {
                System.out.println(g + "=1");
            }
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if (((state >> i) & 1) != 1) {
                state += 1 << i;
                for (int j = 0; j < 4; j++) {
                    if (s == 8) {
                        String t = g;
                        dfs(s + 1, state, g + i);
                        g = t;
                    } else {
                        String t = g;
                        dfs(s + 1, state, g + i + op[j]);
                        g = t;
                    }
                }
            }
        }
    }

    static char[] op = {'+', '-', '*', '/'};

    public static Double Calculation(String formula) {
        Double result = null; //计算结果
        ScriptEngineManager manager = new ScriptEngineManager();  //创建一个ScriptEngineManager对象
        ScriptEngine engine = manager.getEngineByName("js"); //通过ScriptEngineManager获得ScriptEngine对象
        try {
            result = (Double) engine.eval(formula); //用ScriptEngine的eval方法执行脚本
        } catch (ScriptException e) {
            result = Double.NaN;
            return result;
        }
        return result;
    }
}
