package basicSkill.myThreadDemo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AddShutdownhookDemo {
    private static ThreadPoolExecutor myThreadExecutor =new ThreadPoolExecutor(5,10,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(),new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            myThreadExecutor.submit(()->{
                try {
                    Thread.sleep(finalI*3*1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":"+finalI);
            });
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if(null!=myThreadExecutor)
                myThreadExecutor.shutdown();
        }));

        Thread.sleep(100);

        System.exit(0);

        //

        //Runtime.getRuntime().exit(0);

    }
}
