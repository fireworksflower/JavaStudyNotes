package synchronizeddemo;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/27 16:31
 **/

/**
 * 程序在执行过程中，如果出现异常，默认情况下锁会被释放
 * 所以，在并发处理过程中，有异常要小心，不然可能出现不一致的情况
 * 比如，在一个web app处理过程中，多个service线程共同访问同一个资源，这时如果异常处理不合适
 * 在第一个线程抛出异常，其他线程就会进入同步代码区，有可能会访问到异常时产生的数据
 * 因此要非常小心的处理同步业务逻辑中的异常
 *
 */
public class Demo08 {
    int count = 0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName()+" start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count= " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (count == 5) {
                int i = 1 / 0;//此处抛异常，锁被释放，要想不被释放，可以在这里进行catch,然后循环继续
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Demo08 d = new Demo08();
        new Thread(()->d.m(),"d1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->d.m(),"d2").start();


        //启动线程的另一种方法
     /*  Runnable r =  new Runnable(){
            public  void run(){
               d.m();
            }
        };
       new Thread(r,"r1").start();*/
    }

}
