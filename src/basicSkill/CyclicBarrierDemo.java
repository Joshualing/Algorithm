package basicSkill;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("集齐七个龙珠，召唤神龙！");
        });

        for (int i = 1; i <= 7; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    System.out.println("收集到第"+ finalI +"颗龙珠.");
                    cyclicBarrier.await();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
