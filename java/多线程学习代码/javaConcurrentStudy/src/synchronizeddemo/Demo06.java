package synchronizeddemo;

import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/24 10:54
 **/

/**
 * 同步方法和非同步放是可以同时调用的
 */
public class Demo06 implements Runnable{
    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" m1 start...");
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m1 end...");
    }

    public void m2(){
        System.out.println(Thread.currentThread().getName()+" m2 start...");
        try{
            Thread.sleep(5000);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2 end...");
    }

    public static void main(String[] args) {
        Demo06 d = new Demo06();
       /* new Thread(d::m1,"d1").start();
        new Thread(d::m2,"d2").start();*/

        new Thread(()->d.m1(),"d1").start();
        new Thread(()->d.m2(),"d2").start();


    }

    @Override
    public void run() {

    }
}
