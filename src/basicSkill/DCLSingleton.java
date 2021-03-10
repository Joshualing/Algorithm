package basicSkill;

public class DCLSingleton {

    private volatile static DCLSingleton instance;

    private DCLSingleton(){
        System.out.println("构造函数"+Thread.currentThread().getName());
    }

    public  static DCLSingleton  getInstance(){
        if(instance==null)
        {
            synchronized (DCLSingleton.class){
                if(instance==null)
                    instance=new DCLSingleton();
            }
        }
        return instance;
    }



    public static void main(String[] args) {
        for(int i=1;i<=1000;i++){
            new Thread(()->{
                System.out.println(DCLSingleton.getInstance());
            },String.valueOf(i)).start();
        }
    }
}
