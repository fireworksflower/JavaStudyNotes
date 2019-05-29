package synchronizeddemo;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/27 17:50
 **/

/**
 *不要以字符串常量作为锁定对象
 * 在下面的例子中，m1和m2其实锁定的是同一个对象
 * 这种情况还会发生比较诡异的现象，比如你用到一个类库，在该类库中代码锁定了字符串“hh”,
 * 但是你读不到源码，所以你在自己的代码中也锁定了“hh”,这时候就有可能发生非常诡异的死锁问题
 * 因为你的程序和你用到的类库不经意间使用了同一把锁
 *
 */
public class Demo12 {
    String s1  ="hh";
    String s2  ="hh";

    void  m1(){
        synchronized (s1){
            System.out.println(Thread.currentThread().getName()+" m1 start");
        }
    }

    void  m2(){
        synchronized (s2){
            System.out.println(Thread.currentThread().getName()+" m2 start");
        }

    }

    public static void main(String[] args) {
        Demo12 d = new Demo12();
        new Thread(d::m1,"d1").start();
        new Thread(d::m2,"d2").start();
    }
}
