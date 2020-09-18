package 数学;

/**
 * https://blog.csdn.net/qq_30277239/article/details/103681134
 * 质数的判定是数论中最基础的质数。
 * 首先，1既不是质数也不是合数，只能被1和自身整除的整数称为质数。
 * 所以一般采用试除法来判断质数，常规的是从2到n枚举除数，一旦n能被i整除，
 * 说明n不是质数。这种办法的时间复杂度为O（n），而且往往需要特判n为2的情形。
 * 不妨先回忆下整除的定义：若整数b除以非零整数a，商为整数，且余数为零，
 * 我们就说b能被a整除（或说a能整除b），b为被除数，a为除数，即a|b（“|”是整除符号），
 * 读作“a整除b”或“b能被a整除”。a叫做b的约数（或因数），b叫做a的倍数。
 * 如果i|n 则推出 (n/i) | n     这是显然  如3|6 那么2|6
 * 如果n 有一个约数数小于根号n的,那么必然存在另一个约数大于根号n,反之
 * 试除法过程中除到根号n还没找到n的因子,那么大于根号n的数也不会有n的因子,
 * 故可以只枚举根号n个数
 * 循环的写法：for(int i = 2;i <= n / i;i++)。下面说下其它几种写法的缺点，for(int i = 2;i <= sqrt(n);i++)，
 * 每次循环都要执行sqrt函数，效率低，当然可以先令t = sqrt(n),循环时i不超过t即可。
 * 另一种写法是for(int i = 2;i * i <= n;i++)这种情况当n足够大时，乘法运算可能发生溢出。
 * 所以一般写出i <= n / i的形式或者先求出sqrt(n)。
 */
public class 质数 {
    public static void main(String[] args) {
        System.out.println(f(17));
    }

    /**
     *
     */
    static boolean f(int x) {
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return false;
    }
}
