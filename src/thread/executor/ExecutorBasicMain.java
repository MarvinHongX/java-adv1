package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ExecutorBasicMain {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        log("== 초기 상태 ==");
        printState(es);


        es.execute(new RunnableTask("taskA")); // BlockingQueue에 작업을 전달
        es.execute(new RunnableTask("taskB")); // BlockingQueue에 작업을 전달
        es.execute(new RunnableTask("taskC")); // BlockingQueue에 작업을 전달
        es.execute(new RunnableTask("taskD")); // BlockingQueue에 작업을 전달
        es.execute(new RunnableTask("taskE")); // BlockingQueue에 작업을 전달
        log("== 작업 중 ==");
        printState(es);

        sleep(5000);
        log("== 작업 완료 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
