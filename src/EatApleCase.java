import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、不可以使用继承的方法继承每次都会开辟新的空间
 * 2、采佣实现Runnable接口
 * 3、保证其同步的话要 1、同步代码块
 * 2、同步方法
 * 3、获取同步锁
 * Created by 没想法的岁月 on 2018/4/3.
 */
class ThreadDeomo implements Runnable {
    private int size = 50;

    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            //同步代码块
//                synchronized(this) {
//                if (size > 0) {
//                    System.out.println(Thread.currentThread().getName() + size);
//                    try {
//                        //模拟网络延迟
//                        Thread.currentThread().sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    size--;
//                }
//            }
            //同步方法
//            eat();
            //lock 获取锁
            lockeak();
        }
    }

    private void lockeak() {
        lock.lock();
        if (size > 0) {
            System.out.println(Thread.currentThread().getName() + size);
            try {
                //模拟网络延迟
                Thread.currentThread().sleep(100);
                size--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }

    //同步方法
    private synchronized void eat() {
        if (size > 0) {
            System.out.println(Thread.currentThread().getName() + size);
            try {
                //模拟网络延迟
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            size--;
        }
    }
}

public class EatApleCase {
    public static void main(String[] args) {
        ThreadDeomo t = new ThreadDeomo();
        new Thread(t, "小A").start();
        new Thread(t, "小B").start();
        new Thread(t, "小C").start();
    }

}
