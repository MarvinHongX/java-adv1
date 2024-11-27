package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

public class BoundedQueueV6_2 implements BoundedQueue {
    private BlockingQueue<String> queue;

    public BoundedQueueV6_2(int capacity) {
        queue = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void put(String data) {
        // 큐가 가득차있으면 스레드가 대기하지 않고 즉시 false 반환한다
        if (!queue.offer(data)) {
            log("대기열이 가득찼습니다.");
            return;
        }
    }

    @Override
    public String take() {
        // 큐가 비어있으면 스레드가 대기하지 않고 즉시 null 반환한다
        String polled = queue.poll();
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
