package basicSkill;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println("第"+ finalI+"辆车进入停车场了");
                    Thread.sleep(3*1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }finally {
                    semaphore.release();
                    System.out.println("第"+ finalI+"辆车离开停车场了");
                }
            },String.valueOf(i)).start();
        }
    }
}
