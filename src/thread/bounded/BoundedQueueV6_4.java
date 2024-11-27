package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class BoundedQueueV6_4 implements BoundedQueue {
    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void put(String data) {
        // 큐가 가득차있으면 예외를 터트린다.
        queue.add(data); // java.lang.IllegalStateException (runtime exception)
    }

    @Override
    public String take() {
        // 큐가 비어있으면 예외를 터트린다.
        return queue.remove(); // java.util.NoSuchElementException (runtime exception)
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}