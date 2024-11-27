package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV4 {
//    private static final int TASK_SIZE = 1100; // 일반
//    private static final int TASK_SIZE = 1200; // 긴급
    private static final int TASK_SIZE = 1201; // 거절

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(100, 200,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000));
        printState(es);

        long startMs = System.currentTimeMillis();

        for (int i = 1; i <= TASK_SIZE; i++) {
            try {
                es.execute(new RunnableTask("task" + i));
                printState(es, "task" + i);
            } catch (RejectedExecutionException e) {
                log("task" + i + " rejected: " + e);
            }

        }
        es.close();
        long endMs = System.currentTimeMillis();
        log("걸린시간: " + (endMs - startMs));
    }
}
