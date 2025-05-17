package thread.coordination.join;

import java.math.BigInteger;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;

        PowerCalculatingThread res1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread res2 = new PowerCalculatingThread(base2, power2);

        res1.start();
        res2.start();

        // before adding results from two threads
        // we want to wait both results to be ready
        try {
            res1.join();
            res2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        result = res1.getResult().add(res2.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() { return result; }
    }

    public static void main(String[] args) {
        BigInteger base = BigInteger.valueOf(32);
        BigInteger power = BigInteger.valueOf(50);

        ComplexCalculation complexCalculation = new ComplexCalculation();
        BigInteger result = complexCalculation.calculateResult(base, power, base.multiply(base), power);

        System.out.println(result);
    }
}
