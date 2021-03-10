package basicSkill;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {
   static Lock lock=new ReentrantLock();
   static Condition condition=lock.newCondition();

    /**
     * 不需要先park然后unpark，也不需要lock，但是必须保证unpark的时候，线程已经在run
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Thread AAA = new Thread(() -> {
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒");
        }, "AAA");

        AAA.start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"通知AAA");
            LockSupport.unpark(AAA);
        },"BBB").start();
    }

    /**
     * 一定要先wait然后notify
     * @param args
     */
    public static void main1(String[] args) {
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"come in");
                condition.await();
                System.out.println(Thread.currentThread().getName()+"被唤醒");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"AAA").start();

        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName()+"通知AAA");
            } finally {
                lock.unlock();
            }
        },"BBB").start();
    }
}
