package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV3 implements BoundedQueue {
    private final Queue<String> queue = new ArrayDeque<>();
    private final int capacity;

    public BoundedQueueV3(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() >= capacity) {
            log("대기열이 가득찼습니다.");
            try {
                log("대기상태로 진입합니다.");
                wait(1000); // RUNNABLE -> WAITING, LOCK 반납
                log("대기상태 해제합니다. 재시도 중...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(data);
        log("생산을 마무리 하고, 대기 중인 스레드를 깨웁니다.");
        notify(); // 대기 중이던 스레드 하나가 WAIT -> BLOCKED
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("대기열이 비어있습니다.");
            try {
                log("대기상태로 진입합니다.");
                wait(1000); // RUNNABLE -> WAITING, LOCK 반납
                log("대기상태 해제합니다. 재시도 중...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String polled = queue.poll();
        log("소비를 마무리 하고, 대기 중인 스레드를 깨웁니다.");
        notify();
        return polled;

    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
