package synchronizeddemo;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/24 9:23
 **/
public class Demo04 {
    private static int count = 10;

    public synchronized static void m(){
        count--;
        System.out.println(Thread.currentThread().getName()+ " count ="+count);
    }

    /**
     *  这里不能用synchronized (this)来替换synchronized (Demo04.class)
     *  因为m1是静态方法，类直接可以调用，不需要new对象，故没有this
     */
    public static void m1(){
        //synchronized (this){
        synchronized (Demo04.class){
            count--;
            System.out.println(Thread.currentThread().getName()+ " count ="+count);
        }
    }

    public static void main(String[] args) {
        Demo04.m();//maincount  =9
        Demo04.m1();//main count =8
    }
}
