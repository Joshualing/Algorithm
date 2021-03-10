package basicSkill;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key,Object value){
        rwLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t"+"正在写入 key:"+key);
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t"+"写入完成 key:"+key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key){
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t"+"正在读取 key:"+key);
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t"+"读取完成 value:"+value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache=new MyCache();
        for(int i=1;i<=5;i++){
            int finalI = i;
            new Thread(() -> myCache.put(finalI +"", finalI +""),String.valueOf(i)).start();
        }

        for(int i=1;i<=5;i++){
            int finalI = i;
            new Thread(() -> myCache.get(finalI +""),String.valueOf(i)).start();
        }
    }
}
