package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV2 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2); // 스레드 수가 고정되므로 cpu, 메모리 리소스가 예측 가능. 단, 서버 자원은 여유가 있는데, 사용자만 느려지는 현상이 발생할 수 있다.
        log("pool 생성");
        printState(es);

        for (int i = 1; i <= 6; i++) {
            es.execute(new RunnableTask("task" + i));
            printState(es, "task" + i);
        }

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
