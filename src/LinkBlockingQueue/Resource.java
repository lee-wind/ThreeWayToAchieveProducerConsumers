package LinkBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Resource {

    private BlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>(10);;

    void add(int i) throws InterruptedException {

        linkedBlockingQueue.put(i);
        System.out.println("生产者：" + Thread.currentThread().getName()
                + "生产：" + i);
    }

    void remove() throws InterruptedException {

        int i = linkedBlockingQueue.take();
        System.out.println("消费者：" + Thread.currentThread().getName() +
                "消费：" + i);
    }
}
