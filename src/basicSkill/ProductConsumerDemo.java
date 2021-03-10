package basicSkill;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class SharedResource {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public SharedResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void procuct() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue=blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"插入数据:"+data+" 成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"插入数据:"+data+" 失败");
            }
            Thread.sleep(1*1000);
        }
        System.out.println("生产结束");
    }

    public void consume() throws InterruptedException {
        while (FLAG) {
            String data = blockingQueue.poll(2, TimeUnit.SECONDS);
            if(data==null||data.equalsIgnoreCase("")){
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"超过两秒读取数据:"+data+" 失败");
                System.out.println("退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"读取数据:"+data+" 成功");
        }
        System.out.println("生产结束");
    }

    public void stop(){
        FLAG=false;
    }
}

public class ProductConsumerDemo {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource=new SharedResource(new ArrayBlockingQueue<>(10));
        for(int i=1;i<=1;i++){
            new Thread(()->{
                try {
                    sharedResource.procuct();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        for(int i=2;i<=2;i++){
            new Thread(()->{
                try {
                    sharedResource.consume();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        Thread.sleep(20*1000);
        sharedResource.stop();
    }
}
