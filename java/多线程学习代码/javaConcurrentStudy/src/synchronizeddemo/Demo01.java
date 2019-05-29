package synchronizeddemo;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/24 9:09
 **/

import org.junit.Test;

/**
 * synchronized关键字 对某个对象加锁
 */
public class Demo01 {
    private int count = 10;
    private Object o = new Object();
    @Test
    public  void m(){
        synchronized (o){ //任何线程要执行下面的代码，必须先拿到o的锁
            count--;
            System.out.println(Thread.currentThread().getName() +" count = "+count);//main count = 9
        }
    }

}
