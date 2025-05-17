package thread.interrupt;

import java.math.BigInteger;

public class LongComputationDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("20000"), new BigInteger("1000000")));

        // thread.setDaemon(true);
        thread.start();

        // for this to work, we need to check inside thread if thread is interrupted
        // otherwise thread will continue executing, and app will not stop executing
        // other option is to set thread.setDaemon(true),
        // then we dont need check if thread is interrupted
        thread.interrupt();
    }

    private static class LongComputationTask implements Runnable {

        private final BigInteger base;
        private final BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("LongComputationTask is interrupted");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
