package synchronizeddemo;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/24 9:16
 **/
public class Demo02 {
    private int count = 10;
    @Test
    public void m(){
        synchronized (this){//因为是锁的对象，this指向对象，所以这里可以锁this
            count--;
            System.out.println(Thread.currentThread().getName()+" count = "+count);//main count = 9
        }
    }
}
