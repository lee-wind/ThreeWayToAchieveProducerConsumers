package LinkBlockingQueue;

public class Consumer implements Runnable{

    private Resource resource;

    Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {

        try {
            resource.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
