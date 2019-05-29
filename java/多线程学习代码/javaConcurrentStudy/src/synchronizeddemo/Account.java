package synchronizeddemo;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lijing
 * @date 2019/5/24 11:10
 **/

/**
 *对业务写方法加锁，对业务读方法不加锁
 * 容易造成脏读问题（脏读是一个事务读取了另一个事务未提交的数据）
 */
public class Account {

   private  String name;
   private double balance;

   public synchronized void set(String name,double balance){
       this.name = name;
       this.balance = balance;
   }
   public /*synchronized*/ double getBalance(String name){
       return  this.balance;
   }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(()->a.set("huahua", 100.0)).start();
        try{
            TimeUnit.SECONDS.sleep(1);

        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(a.getBalance("huahua"));

        try{
            TimeUnit.SECONDS.sleep(2);

        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(a.getBalance("huahua"));
    }

}
