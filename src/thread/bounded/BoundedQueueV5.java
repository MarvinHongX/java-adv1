package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue {
    private final Lock lock = new ReentrantLock();
    private final Condition producerCond = lock.newCondition();
    private final Condition consumerCond = lock.newCondition();

    private final Queue<String> queue = new ArrayDeque<>();
    private final int capacity;

    public BoundedQueueV5(int capacity) {
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
                    producerCond.await();
                    log("대기상태 해제합니다. 재시도 중...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            log("생산을 마무리 하고, 대기 중인 소비자를 깨웁니다.");
            consumerCond.signal();
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
                    consumerCond.await();
                    log("대기상태 해제합니다. 재시도 중...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String polled = queue.poll();
            log("소비를 마무리 하고, 대기 중인 생산자를 깨웁니다.");
            producerCond.signal();
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
