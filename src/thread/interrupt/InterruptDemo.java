package thread.interrupt;

public class InterruptDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(new BlockingTask());
        thread.start();

        // inside BlockingTask we can get InterruptedException
        thread.interrupt();
    }

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            // do work
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }
}
