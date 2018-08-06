package Synchronized;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Resource {

    private final ArrayList<Integer> arrayList = new ArrayList<>();

    void add(int i) throws InterruptedException {

        synchronized(arrayList){
            if(arrayList.size() == 10){
                System.out.println("容器满了，生产者等待");
                arrayList.wait();
            }
            arrayList.add(i);
            arrayList.notifyAll();
            System.out.println("生产者：" + Thread.currentThread().getName()
                    + "生产：" + i + "，当前资源池有" + arrayList.size() + "个资源");
        }
    }

    synchronized void remove() throws InterruptedException {

        synchronized(arrayList){
            if(arrayList.isEmpty()){
                System.out.println("容器空了，消费者等待");
                arrayList.wait();
            }
            int i = arrayList.remove(0);
            arrayList.notifyAll();
            System.out.println("消费者者：" + Thread.currentThread().getName()
                    + "消费者：" + i + "，当前资源池有" + arrayList.size() + "个资源");
        }
    }
}
