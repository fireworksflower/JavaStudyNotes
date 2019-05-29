package synchronizeddemo;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/27 17:40
 **/

/**
 * 锁定某对象o,如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生变化
 * 应该避免将锁定对象的引用变成另外的对象
 */
public class Demo11 {
    Object o = new Object();
    void m(){

        synchronized (o){
            System.out.println(Thread.currentThread().getName()+" start");
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        Demo11 d = new Demo11();
        new Thread(d::m,"d1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t = new Thread(d::m,"d2");
       // d.o = new Object(); //锁对象发生变化，所以d2线程得以执行，如果注释掉这句话，线程d2永远不会执行
        t.start();
    }

}
