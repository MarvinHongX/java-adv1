package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV3 {
    public static void main(String[] args) {
//        ExecutorService es = Executors.newCachedThreadPool(); // 작업 요청이 오면 대기 큐에 쌓이지 않고, 초과 스레드로 바로바로 처리한다.
        ExecutorService es = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                3, TimeUnit.SECONDS,
                new SynchronousQueue<>());
        log("pool 생성");
        printState(es);

        for (int i = 1; i <= 4; i++) {
            es.execute(new RunnableTask("task" + i));
            printState(es, "task" + i);
        }
        log("== 작업 완료 ==");
        printState(es);

        sleep(8000);
        log("== maximum pool size 대기시간 초과 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
    }
}
