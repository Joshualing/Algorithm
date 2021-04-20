package basicSkill.ClassLoad;

class parent{
    private static int a;
    static {
        a=1;
        System.out.println(a);
        System.out.println("parent static init");
    }

    public parent() {
        System.out.println("parent init");
    }
}

class son extends parent{
    private static int a;
    static {
        a=2;
        System.out.println(a);
        System.out.println("son static init");
    }

    public son() {
        System.out.println("son init");
    }
}

/**
 * 有java虚拟机生成的clinit方法（static块）是先于构造方法的
 */
public class InitialOrder {
    int a;
    public static void main(String[] args) {
        new son();
    }
}
