package synchronizeddemo.Case01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/29 9:12
 **/
public class Demo02 {
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


        new Thread(()->{
            while (true){
                if(d.size()==5){
                    break;
                }
            }
            System.out.println("d2 结束");
        },"d2").start();
    }
}
