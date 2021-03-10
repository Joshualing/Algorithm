package basicSkill.myThreadDemo;

public class ThreadLocalDemo {
    ThreadLocal<Integer> t1=new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo threadLocalDemo=new ThreadLocalDemo();

        Thread A=new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+" t1:"+threadLocalDemo.t1.get());
            threadLocalDemo.t1.set(111);
            System.out.println(Thread.currentThread().getName()+" t1:"+threadLocalDemo.t1.get());
        },"A线程");

        Thread B=new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+" t1:"+threadLocalDemo.t1.get());
            threadLocalDemo.t1.set(222);
            System.out.println(Thread.currentThread().getName()+" t1:"+threadLocalDemo.t1.get());
        },"B线程");

        A.start();

        Thread.sleep(100);

        B.start();

    }
}
