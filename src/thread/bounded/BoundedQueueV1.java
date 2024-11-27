package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue {
    private final Queue<String> queue = new ArrayDeque<>();
    private final int capacity;

    public BoundedQueueV1(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized void put(String data) {
        if (queue.size() >= capacity) {
            log("대기열이 가득찼습니다.");
            return;
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        if (queue.isEmpty()) {
            log("대기열이 비어있습니다.");
            return null;
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
