package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {
    private final Queue<String> queue = new ArrayDeque<>();
    private final int capacity;

    public BoundedQueueV2(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() >= capacity) {
            // lock을 들고 반납하지 않고 무한 대기하는 문제점 발생
            log("대기열이 가득찼습니다. 대기중...");
            sleep(1000);
        }
        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            // lock을 들고 반납하지 않고 무한 대기하는 문제점 발생
            log("대기열이 비어있습니다. 대기중...");
            sleep(1000);
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
