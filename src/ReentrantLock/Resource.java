package ReentrantLock;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {

    private ArrayList<Integer> arrayList = new ArrayList<>();

    private Lock lock = new ReentrantLock();
    private Condition producerCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();

    void add(int i) throws InterruptedException {

        lock.lock();
        try{
            while(arrayList.size() == 10){
                System.out.println("容器满了，" + "生产者：" + Thread.currentThread().getName() + "等待");
                producerCondition.await();
            }
            arrayList.add(i);
            consumerCondition.signalAll();
            System.out.println("生产者：" + Thread.currentThread().getName()
                    + "生产：" + i + "，当前资源池有" + arrayList.size() + "个资源");
        }finally {
            lock.unlock();
        }
    }

    void remove() throws InterruptedException {

        lock.lock();
        try{
            while(arrayList.isEmpty()){
                System.out.println("容器空了，消费者：" + Thread.currentThread().getName() + "等待");
                consumerCondition.await();
            }
            int i = arrayList.remove(0);
            producerCondition.signalAll();
            System.out.println("消费者者：" + Thread.currentThread().getName()
                    + "消费者：" + i + "，当前资源池有" + arrayList.size() + "个资源");
        }finally {
            lock.unlock();
        }
    }

}
