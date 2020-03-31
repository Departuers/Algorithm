package 第七章枚举;

class A {
    static void permu(char[] data, int cur) {
        if (cur == data.length - 1) {
            System.out.println(new String(data));
            return;
        }

        for (int i = cur; i < data.length; i++) {
            char tmp = data[i];
            for (int j = i - 1; j >= cur; j--) data[j + 1] = data[j];
            data[cur] = tmp;

            permu(data, cur + 1);

            tmp = data[cur];

            for (int j = cur; j < i; j++) data[j] = data[j + 1];
            data[i] = tmp;
        }
    }

    static void permu(String x) {
        permu(x.toCharArray(), 0);
    }

    public static void main(String[] args) {
        permu("1234");
    }
}