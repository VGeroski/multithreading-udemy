package thread.creation.example;

public class ThreadedClassDemo {

    public static void main(String[] args) {
        Thread thread = new NewThread("New Thread");
        thread.start();
    }

    private static class NewThread extends Thread {

        public NewThread(String name) {
            this.setName(name);
        }

        @Override
        public void run() {
            System.out.println("We are now in thread " + this.getName());
        }
    }
}
