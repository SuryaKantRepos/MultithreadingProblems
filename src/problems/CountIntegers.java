package problems;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/***  This problem is about: Counting Integers from 1 to 1000000 using 5 threads....*/









public class CountIntegers {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Counter counter = new Counter();

        for (int i = 0; i < 1000000 ; i++) {
            executorService.execute(counter);
            /// Please note that executorService runs a set of 5 separate threads apart from the main thread.
            /// So the main thread has to wait until the 5 threads complete their work.
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Counter.getCount());
    }


}

class Counter implements Runnable{
    public static AtomicInteger count = new AtomicInteger();

    @Override
    public void run() {
        count.incrementAndGet();
       // System.out.println(Thread.currentThread().getName() + " " + count.get());
    }

    public static Integer getCount() {
        return count.get();
    }
}
