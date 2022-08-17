package problems;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/***  This problem is about: Counting Integers from 1 to 1000000 using 5 threads....
 * 
 * A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes.
 * A CountDownLatch is initialized with a given count. The await methods block until the current count reaches zero due to invocations of the countDown() method, after which all waiting threads are released and any subsequent invocations of await return immediately. This is a one-shot phenomenon -- the count cannot be reset.
 * A CountDownLatch is a versatile synchronization tool and can be used for a number of purposes.
 */
public class CountIntegers {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(1000000);

        Counter counter = new Counter();
        counter.setLatch(latch);

        for (int i = 0; i < 1000000 ; i++) {
            executorService.execute(counter);
            /// Please note that executorService runs a set of 5 separate threads apart from the main thread.
            /// So the main thread has to wait until the 5 threads complete their work.
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Counter.getCount());
    }


}

class Counter implements Runnable{
    public static AtomicInteger count = new AtomicInteger();
    private CountDownLatch latch;

    @Override
    public void run() {
        count.incrementAndGet();
       // System.out.println(Thread.currentThread().getName() + " " + count.get());
        latch.countDown();
    }

    public static Integer getCount() {
        return count.get();
    }

    public void setLatch(CountDownLatch latch){
        this.latch = latch;
    }

    public boolean isLatched(){
        return this.latch != null;
    }
}
