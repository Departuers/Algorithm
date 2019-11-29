package Test;

public class Tsts {
    public static void main(String[] args) {
        System.out.println(Math.floor(12));
        s s = () -> System.out.println("nihao");
        s.sou();
    }
}

interface s {
    void sou();
}
