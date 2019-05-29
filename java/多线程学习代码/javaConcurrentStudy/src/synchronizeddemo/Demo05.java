package synchronizeddemo;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/24 9:29
 **/
public class Demo05 implements Runnable {
    private int count = 10;

    /**
     *
     若加了synchronized，则运行结果是
     第0个线程 count = 9
     第3个线程 count = 8
     第4个线程 count = 7
     第2个线程 count = 6
     第1个线程 count = 5

     run()方法如果不加synchronized，则运行结果是
     第3个线程 count = 8
     第4个线程 count = 5
     第0个线程 count = 6
     第1个线程 count = 7
     第2个线程 count = 8

     发生这种情况是因为一个线程刚执行了count--,还没来得及输出，另一个线程进来又执行了count--
     */
    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+" count = "+count);
    }

    public static void main(String[] args) {
        Demo05 d = new Demo05();
        for (int i = 0; i <5 ; i++) {
            new Thread(d,"第"+i+"个线程").start();
        }
    }
}
