package thread.bounded;

import static util.MyLogger.log;

public class ConsumerTask implements Runnable {
    private BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[소비 시도] " + queue);
        String taken = queue.take();
        log("[소비 완료] " + taken + " <- " + queue);
    }
}
