package synchronizeddemo;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/27 16:53
 **/

/**
 * volatile关键字，使一个变量在多个线程间可见
 * A B线程都用到一个变量，java默认A线程中保留一份copy变量，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 *
 * 在下面的代码中，flag是存在于堆内存的d对象中
 * 当线程d1开始运行的时候，会把flag值从内存中读到d1线程的工作区，在运行过程中直接使用这个copy,并不会每次都去读取堆内存，
 * 这样，当主线程修改flag的值之后，d1线程感知不到，所以不糊停止运行
 *
 * 使用volatile,将会强制所有线程都去堆内存中读取flag的值
 *
 * volatile并不能保证多个线程共同修改flag变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 *
 * volatile只能保证可见性，synchronized可以保证可见性和原子性
 */
public class Demo09 {
    volatile boolean flag = true;
    int count  =0;
     void m() {
        System.out.println("m start");
        while (flag){

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        Demo09 d = new Demo09();
        new Thread(d::m,"d1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        d.flag = false;
    }
}
