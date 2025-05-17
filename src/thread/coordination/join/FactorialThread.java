package thread.coordination.join;

import java.math.BigInteger;

public class FactorialThread extends Thread {
    private long inputNumber;
    private BigInteger factorial = BigInteger.ZERO;
    private boolean isFinished = false;

    public FactorialThread(long inputNumber) {
        this.inputNumber = inputNumber;
    }

    @Override
    public void run() {
        this.factorial = factorial(inputNumber);
        isFinished = true;
    }

    public BigInteger factorial(long number) {
        BigInteger result = BigInteger.ONE;

        for (long i = number; i > 0; i--) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public BigInteger getFactorial() {
        return factorial;
    }
}
