package ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args){

        Resource resource = new Resource();

        new Thread(new Producer(resource)).start();

        for(int i = 1; i <= 20; i++){
            new Thread(new Consumer(resource)).start();
        }
    }
}
