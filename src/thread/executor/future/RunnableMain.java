package thread.executor.future;

import java.util.Random;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class RunnableMain {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread-1");
        thread.start();
        thread.join();
        int result = task.getValue();
        log(result);
    }

    private static class MyRunnable implements Runnable {
        private int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(100);
            log("Runnable 종료, 무작위 값은 " + value);
        }

        public int getValue() {
            return value;
        }
    }
}
