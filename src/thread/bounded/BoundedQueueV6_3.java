package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class BoundedQueueV6_3 implements BoundedQueue {
    private BlockingQueue<String> queue;

    public BoundedQueueV6_3(int capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void put(String data) {
        // 큐가 가득차있으면 지정된 시간 이후에는 스레드가 대기하지 않고 즉시 false 반환한다
        try {
            if (!queue.offer(data, 1, TimeUnit.NANOSECONDS)) {
                log("대기열이 가득찼습니다.");
                return;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        // 큐가 비어있으면 지정된 시간 이후에는 스레드가 대기하지 않고 즉시 null 반환한다
        String polled = null;
        try {
            polled = queue.poll(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (polled == null) {
            log("대기열이 비어있습니다.");
        }
        return polled;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
