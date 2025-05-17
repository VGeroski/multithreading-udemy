package thread.creation.example;

public class ThreadCreationDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // code that will run in a new thread
                System.out.println("We are now in thread " + Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            }
        });

        // name is given by default, but it is good practice to give thread a name
        thread.setName("New Worker Thread");

        // we can set priority 0 - 10
        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");

        Thread.sleep(1000);
    }
}
