package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class FutureCancelMain {
//    private static boolean mayInterruptIfRunning = true;
    private static boolean mayInterruptIfRunning = false; // cancel를 해도 interrupt를 걸지 않으므로 이미 실행중인 작업은 그냥 둔다.

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("Future state: " + future.state());

        sleep(3000);

        log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        log("취소 결과: " + cancelResult);

        try {
            log("future: " + future.get());
        } catch (CancellationException e) {
            log("future는 이미 취소되었습니다.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        es.close();
    }

    private static class MyTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                for (int i = 0; i < 10; i++) {
                    log("작업 중: " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                log("interrupt 발생");
                return "interrupted";
            }
            return "completed";
        }
    }
}
