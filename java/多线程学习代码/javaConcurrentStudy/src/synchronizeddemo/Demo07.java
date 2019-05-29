package synchronizeddemo;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/27 14:52
 **/

/**
 * 一个同步方法可以调用另一个同步方法，一个线程已经拥有某一个对象的时候，再次申请的时候仍然可以得到改对象的锁
 * 也就是说synchronized获得的锁是可重入的
 * 子类可以调用父类的同步方法
 */
public class Demo07 {

    synchronized void m1(){
        System.out.println("m1");
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2() {
        try{
            TimeUnit.SECONDS.sleep(2);

        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("m2");
    }

    public static void main(String[] args) {
        Demo07 d = new Demo07();
        new Thread(()->d.m1(),"d1").start();
    }
}
