package thread.sync.ex;

import static util.MyLogger.log;

public class SyncTest2Main {
    public static void main(String[] args) {
        MyCounter myCounter = new MyCounter();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                myCounter.count();
            }
        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }

    private static class MyCounter {
        public void count() {
            int localValue = 0;
            for (int i = 0; i < 1000; i++) {
                localValue += 1;
            }
            log("결과: " + localValue);
        }
    }
}
