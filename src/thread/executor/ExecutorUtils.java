package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    // 공통된 부분을 별도의 private 메서드로 추출
    private static void printThreadPoolState(ThreadPoolExecutor threadPoolExecutor, String taskName) {
        int poolSize = threadPoolExecutor.getPoolSize();
        int activeCount = threadPoolExecutor.getActiveCount();
        int queueSize = threadPoolExecutor.getQueue().size();
        long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();

        // taskName이 제공되면 taskName을 포함하고, 그렇지 않으면 기본 로그
        String message = (taskName != null)
                ? taskName + " -> [pool=" + poolSize + ", activeCount=" + activeCount + ", queueSize=" + queueSize + ", completedTaskCount=" + completedTaskCount + "]"
                : "[pool=" + poolSize + ", activeCount=" + activeCount + ", queueSize=" + queueSize + ", completedTaskCount=" + completedTaskCount + "]";

        log(message);
    }

    // taskName 없이 호출할 경우
    public static void printState(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor threadPoolExecutor) {
            printThreadPoolState(threadPoolExecutor, null);
        } else {
            log(executorService);
        }
    }

    // taskName과 함께 호출할 경우
    public static void printState(ExecutorService executorService, String taskName) {
        if (executorService instanceof ThreadPoolExecutor threadPoolExecutor) {
            printThreadPoolState(threadPoolExecutor, taskName);
        } else {
            log(executorService);
        }
    }
}
