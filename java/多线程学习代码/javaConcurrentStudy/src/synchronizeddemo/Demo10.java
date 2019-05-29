package synchronizeddemo;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/27 17:31
 **/

/**
 * synchronized优化： 同步代码块里语句越少越好
 */
public class Demo10 {
    int count = 0;
    synchronized void m1(){
        System.out.println(Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        System.out.println(Thread.currentThread().getName()+" count= "+count);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2(){
        System.out.println(Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //业务逻辑只有下面这句话需要synchronized,这时不应该给整个方法上锁
        //采用细粒度的锁，可以使线程争用时间变短，从而提高效率
        synchronized (this){
            count++;
        }
        System.out.println(Thread.currentThread().getName()+" count= "+count);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo10 d = new Demo10();
       /* new Thread(d::m1,"d1").start();
        new Thread(d::m1,"d2").start();*/

       //执行效率真的比上面两行快多了
        new Thread(d::m2,"d1").start();
        new Thread(d::m2,"d2").start();

    }

}
