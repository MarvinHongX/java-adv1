package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class CallableMainV1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(new MyCallable()); // BlockingQueue에 Callable 작업을 전달: ExecutorService가 제공하는 submit 메서드를 통해...
        Integer result = future.get();
        log(result);
        es.close();
    }

    private static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() {
            log("Callable 시작");
            sleep(2000);
            int value = new Random().nextInt(100);
            log("Callable 종료, 무작위 값은 " + value);
            return value;
        }
    }
}
