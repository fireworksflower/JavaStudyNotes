package synchronizeddemo;

import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/24 9:20
 **/
public class Demo03 {
    private int count = 10;
    @Test
    public synchronized void m(){ //这种方法等同于Demo02类中在方法的代码执行时要加synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName()+" count = "+count);//main count = 9
    }
}
