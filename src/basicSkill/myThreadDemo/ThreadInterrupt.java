package basicSkill.myThreadDemo;

/**
 * interrupt：给调用此方法的线程设置一个中断标志，当前线程依旧可以继续运行。
 * 如果当前线程实例被标记为interrupted，则在调用wait、join或者sleep相关方法，并且正在阻塞状态中时，此中断状态会被清除，并触发InterruptedException。
 */
public class ThreadInterrupt {
    static class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println("MyThread run.");
            System.out.println("被打断之前，threadBeInterrrupt线程的interupt标志位:" + Thread.currentThread().isInterrupted());
            //Thread.currentThread().interrupt();
            synchronized (this) {
                try {
                    Thread.sleep(5 * 1000);
                    System.out.println("sleep之后，threadBeInterrrupt线程的interupt标志位:" + Thread.currentThread().isInterrupted());
                    //wait();
                } catch (InterruptedException exception) {
                    System.out.println("catch语句块中，threadBeInterrrupt线程的interupt标志位:" + Thread.currentThread().isInterrupted());
                    exception.printStackTrace();
                }
            }
            System.out.println("即使被打断，打出异常继续运行.");
            System.out.println("被打断之后，threadBeInterrrupt线程的interupt标志位:" + Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().isInterrupted());
        Thread threadBeInterrrupt = new Thread(new MyThread(), "A");
        threadBeInterrrupt.start();
        Thread.sleep(2 * 1000);
        System.out.println("main线程打断" + "threadBeInterrrupt线程");
        threadBeInterrrupt.interrupt();
        System.out.println("main线程打断之后，threadBeInterrrupt线程的interupt标志位:"+threadBeInterrrupt.isInterrupted());
        threadBeInterrrupt.join();
        //System.out.println(threadBeInterrrupt.isInterrupted());
    }
}
