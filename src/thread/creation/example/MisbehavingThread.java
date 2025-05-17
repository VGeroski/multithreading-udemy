package thread.creation.example;

public class MisbehavingThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                throw new RuntimeException("Intentional Exception");
            }
        });

        thread.setName("Misbehaving thread");

        // if some error occurs in thread, and it is not caught anywhere
        // we can set UncaughtExceptionHandler to deal with that kind of error
        // realistic example is to clean up resources here
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {

                System.out.println("A critical error happened in thread " + thread.getName()
                    + ": " + throwable.getMessage());
            }
        });

        thread.start();
    }
}
