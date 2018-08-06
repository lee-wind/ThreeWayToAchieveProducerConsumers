package ReentrantLock;

public class Producer implements Runnable{

    private Resource resource;

    Producer(Resource resource) {

        this.resource = resource;
    }

    @Override
    public void run() {

        for(int i = 1; i<=20; i++){
            try {
                resource.add(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
