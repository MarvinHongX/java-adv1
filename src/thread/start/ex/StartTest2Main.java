package thread.start.ex;

import static util.MyLogger.log;

public class StartTest2Main {
    public static void main(String[] args) {
        log("main start");
        Runnable runnable = new CounterRunnable();
        Thread thread = new Thread(runnable, "counter");
        thread.start();
        log("main end");
    }

    static class CounterRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                log(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
