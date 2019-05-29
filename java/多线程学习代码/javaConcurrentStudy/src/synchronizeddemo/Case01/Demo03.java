package synchronizeddemo.Case01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/29 10:02
 **/

/**
 * 这里使用wait和notify,wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证d2线程先执行，也就是首先让d2监听才可以
 *
 * 运行下方代码  输出结果并不是size==5时t2退出，而是t1结束时t2才收到通知退出
 *
 * 如果将① ② 注释掉的代码放开会得到想要的结果，整个过程比较繁琐
 */
public class Demo03 {
    //添加volatile,使t2能够得到通知
    volatile List lists = new ArrayList();

    public void add(Object o){
        lists.add(o );
    }
    public  int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        Demo01 d = new Demo01();
        final Object lock = new Object();
        new Thread(()->{
            synchronized (lock) {
                System.out.println("t2启动");
                if(d.size()!=5){

                try{
                    lock.wait();//当前线程进入等待状态并释放锁
                }catch(Exception e){
                    e.printStackTrace();
                }
                    System.out.println("t2结束");
                }
                //lock.notify();//通知d1线程继续执行  ①
            }
        },"d2").start();


        new Thread(()->{
           synchronized (lock){
               System.out.println("d1 开始");
               for (int i = 0; i <10 ; i++) {
                   d.add(new Object());
                   System.out.println("add "+i);
                   if(d.size()==5){
                       lock.notify();//启动在这个对象上等待的另一个线程
                        //②
                       /*
                       try {
                           lock.wait();//释放锁
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }*/
                   }
               }
           }
            System.out.println("d1 结束");
        },"d1").start();
    }
}
