package basicSkill;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask=new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("call...");
                return 1024;
            }
        });

        new Thread(futureTask,"A").start();

        System.out.println(futureTask.get());
        //new  Thread()
    }
}
