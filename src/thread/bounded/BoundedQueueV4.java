package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV4 implements BoundedQueue {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private final Queue<String> queue = new ArrayDeque<>();
    private final int capacity;

    public BoundedQueueV4(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try {
            while (queue.size() >= capacity) {
                log("대기열이 가득찼습니다.");
                try {
                    log("대기상태로 진입합니다.");
                    condition.await();
                    log("대기상태 해제합니다. 재시도 중...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            log("생산을 마무리 하고, 대기 중인 스레드를 깨웁니다.");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                log("대기열이 비어있습니다.");
                try {
                    log("대기상태로 진입합니다.");
                    condition.await();
                    log("대기상태 해제합니다. 재시도 중...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String polled = queue.poll();
            log("소비를 마무리 하고, 대기 중인 스레드를 깨웁니다.");
            condition.signal();
            return polled;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
