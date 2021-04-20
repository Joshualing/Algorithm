package basicSkill.myThreadDemo;

import javax.print.attribute.standard.PrinterResolution;

public class DeadLockDemo {
    private static String A="A";
    private static String B="B";

    public static void main(String[] args) throws InterruptedException {
        new DeadLockDemo().deadLock();
    }

    private void deadLock() throws InterruptedException {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    System.out.println("线程1 获取A");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("线程1 获取B");
                    }
                }
            }
        },"t1线程");

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    System.out.println("线程2 获取B");
                    synchronized (A){
                        System.out.println("线程2 获取A");
                    }
                }
            }
        },"t2线程");

        t1.start();
        Thread.sleep(10);
        t2.start();
    }
}
