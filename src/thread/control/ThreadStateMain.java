package thread.control;

import static util.MyLogger.log;

public class ThreadStateMain {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new MyRunnable(), "myThread");
        log("myThread.getState() #1: " + myThread.getState()); // NEW

        log("myThread start");
        myThread.start();

        Thread.sleep(1000);
        log("myThread.getState() #3: " + myThread.getState()); // TIMED_WAITING

        Thread.sleep(4000);
        log("myThread.getState() #5: " + myThread.getState()); // TERMINATED
        log("end");
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {
                log("start");
                log("myThread.getState() #2: " + Thread.currentThread().getState());

                log("sleep start");
                Thread.sleep(3000);
                log("sleep end");

                log("myThread.getState() #4: " + Thread.currentThread().getState());
                log("end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
