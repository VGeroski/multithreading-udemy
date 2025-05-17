package thread.coordination.join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        List<Long> inputNumbers = Arrays.asList(100L, 3435L, 35435L, 2324L, 4656L, 23L, 2435L, 5566L);

        List<FactorialThread> threads = new ArrayList<>();

        // calculate factorial of each element
        for (Long number : inputNumbers) {
            threads.add(new FactorialThread(number));
        }

        for (FactorialThread thread : threads) {
            // this will terminate application, even if not all threads are joined after 2 sec
            thread.setDaemon(true);
            thread.start();
        }

        // without join, race condition will happen, and we may not see result in main
        for (Thread thread : threads) {
            thread.join(2000); // we will wait 2 [sec]
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread thread = threads.get(i);
            if (thread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is finished " + thread.getFactorial());

            } else {
                System.out.println("The calculation of the factorial of "
                        + inputNumbers.get(i) + " is still in progress");
            }
        }
    }
}
