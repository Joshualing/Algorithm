package basicSkill;

import java.util.concurrent.atomic.AtomicReference;

public class SpinDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        boolean flag = false;
        System.out.println(Thread.currentThread() + " lock..");
        do {
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            flag = atomicReference.compareAndSet(null, Thread.currentThread());
        } while (!flag);

    }

    public void myUnLock() {
        boolean flag = false;
        do {
            flag = atomicReference.compareAndSet(Thread.currentThread(), null);
        } while (!flag);
        System.out.println(Thread.currentThread() + " unLock..");
    }

    public static void main(String[] args) {
        SpinDemo spinDemo = new SpinDemo();
        Thread thread1 = new Thread(() -> {
            spinDemo.myLock();

            spinDemo.myUnLock();
        }, "AAA");
        thread1.start();
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        Thread thread2 = new Thread(() -> {
            spinDemo.myLock();
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            spinDemo.myUnLock();
        }, "BBB");
        thread2.start();
    }
}
