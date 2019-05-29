package synchronizeddemo.Case01;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/28 9:23
 **/

/**
 * 实现一个容器，提供两个方法 add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素，当元素到5的时候，线程2给出提示饼结束
 *
 *
 */
public class Demo01 {
    List lists = new ArrayList();

    public void add(Object o){
        lists.add(o );
    }
    public int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        Demo01 d = new Demo01();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                d.add(new Object());
                System.out.println("add "+ i);

                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        },"d1").start();

        /**
         * 线程2获得的d.size()永远是0
         * System.out.println(d.size());//0
         */
        new Thread(()->{
            while (true){
                if(d.size()==5){
                    break;
                }
            }
            System.out.println("t2 结束");
        },"t2").start();
    }
}
