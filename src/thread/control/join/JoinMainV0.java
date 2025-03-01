package thread.control.join;

import static util.ThreadUtils.sleep;
import static util.MyLogger.log;

public class JoinMainV0 {
    public static void main(String[] args) {
        log("start");
        Thread thread1 = new Thread(new Job(), "thread-1");
        Thread thread2 = new Thread(new Job(), "thread-2");

        thread1.start();
        thread2.start();
        log("end");
    }

    private static class Job implements Runnable {
        @Override
        public void run() {
            log("작업시작");
            sleep(2000); // 2초짜리 작업을 한다고 가정
            log("작업완료");
        }
    }
}
