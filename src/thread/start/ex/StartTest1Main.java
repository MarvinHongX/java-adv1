package thread.start.ex;

import static util.MyLogger.log;

public class StartTest1Main {
    public static void main(String[] args) {
        log("main start");
        CounterThread thread = new CounterThread();
        thread.start();
        log("main end");
    }

    static class CounterThread extends Thread {
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
